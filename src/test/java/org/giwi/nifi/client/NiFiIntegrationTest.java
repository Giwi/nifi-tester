package org.giwi.nifi.client;

import org.giwi.nifi.client.NiFiConnection;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@NiFiConnection(url = "https://localhost:8443/nifi-api", user = "admin", password = "admin1234567")
class NiFiIntegrationTest {

    private PipelineTester tester;

    @BeforeEach
    void setUp() throws Exception {
        tester = new PipelineTester(getClass());
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
        assertTrue(result.isSuccess());
        
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
        assertTrue(result.isSuccess());

        if (result.isSuccess()) {
            String pgId = result.getProcessGroupId();
            var group = tester.getProcessGroup(pgId);
            assertNotNull(group);
            
            // Cleanup
            tester.deleteProcessGroup(pgId);
        }
    }
}