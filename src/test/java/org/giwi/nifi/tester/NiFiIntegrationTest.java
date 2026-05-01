package org.giwi.nifi.tester;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class NiFiIntegrationTest {

    private PipelineTester tester;

    @BeforeEach
    void setUp() throws Exception {
        tester = new PipelineTester(NiFiTestContainer.getApiUrl(), NiFiTestContainer.getUsername(), NiFiTestContainer.getPassword());
    }

    @Test
    @DisplayName("Login to NiFi")
    void testLogin() throws Exception {
        String token = tester.getAccessToken();
        assertNotNull(token);
    }

    @Test
    @DisplayName("Get root process group")
    void testGetRoot() throws Exception {
        var group = tester.getProcessGroup("root");
        assertNotNull(group);
    }

    @Test
    @DisplayName("Deploy sample pipeline to NiFi")
    void testDeployPipeline() throws Exception {
        var result = tester.deployPipeline(
            new java.io.File("src/test/resources/pipelines/sample-generate-pipeline.yaml"),
            "root"
        );
        
        assertNotNull(result);
        assertTrue(result.isSuccess(), "Deployment failed: " + result.getMessage());
        
        if (result.isSuccess()) {
            String pgId = result.getProcessGroupId();
            var group = tester.getProcessGroup(pgId);
            assertNotNull(group);
            tester.deleteProcessGroup(pgId);
        }
    }

    @Test
    @DisplayName("Deploy comprehensive pipeline to NiFi")
    void testDeployComprehensivePipeline() throws Exception {
        var result = tester.deployPipeline(
            new java.io.File("src/test/resources/pipelines/sample-deployment-pipeline.yaml"),
            "root"
        );

        assertNotNull(result);
        assertTrue(result.isSuccess(), "Deployment failed: " + result.getMessage());

        if (result.isSuccess()) {
            String pgId = result.getProcessGroupId();
            var group = tester.getProcessGroup(pgId);
            assertNotNull(group);

            // Cleanup
            tester.deleteProcessGroup(pgId);
        }
    }

    @Test
    @DisplayName("Deploy Groovy script pipeline")
    void testGroovyScriptPipeline() throws Exception {
        var result = tester.deployPipeline(
            new java.io.File("src/test/resources/pipelines/groovy-script-pipeline.yaml"),
            "root"
        );

        assertNotNull(result);
        assertTrue(result.isSuccess(), "Deployment failed: " + result.getMessage());

        if (result.isSuccess()) {
            String pgId = result.getProcessGroupId();
            var group = tester.getProcessGroup(pgId);
            assertNotNull(group);

            // Start the process group
            tester.startProcessGroup(pgId);

            // Stop LogResult so flow file stays in the queue before it
            String logResultId = tester.findProcessorIdByName(pgId, "LogResult");
            if (logResultId != null) {
                tester.stopProcessor(logResultId);
            }

            // Find the input port
            String inputPortId = tester.findInputPortIdByName(pgId, "Input");
            assertNotNull(inputPortId, "Input port not found");

            // Wait for port to start up before pushing data via Site-to-Site
            Thread.sleep(5000);

            // Inject "hello" into the input port
            tester.injectFlowFile(inputPortId, "hello");

            // Find the connection between AppendWorld and LogResult
            String connId = tester.findConnectionId(pgId, "AppendWorld", "LogResult");
            assertNotNull(connId, "Connection not found");

            // Wait for processing
            long start = System.currentTimeMillis();
            boolean found = false;
            while (System.currentTimeMillis() - start < 15000) {
                var contents = tester.getFlowFileContentsAsString(connId);
                for (String content : contents) {
                    if (content.contains("hello world")) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
                Thread.sleep(500);
            }
            assertTrue(found, "Expected to find 'hello world' in flow file content");

            // Stop the process group
            tester.stopProcessGroup(pgId);

            // Cleanup
            tester.deleteProcessGroup(pgId);
        }
    }
}