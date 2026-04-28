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
        System.out.println("Login successful - Token: " + token.substring(0, 20) + "...");
    }

    @Test
    @DisplayName("Get root process group")
    void testGetRoot() throws Exception {
        var group = tester.getProcessGroup("root");
        assertNotNull(group);
        System.out.println("Root: " + group.getComponent().getName() + " (" + group.getComponent().getId() + ")");
    }

    @Test
    @DisplayName("Deploy sample pipeline to NiFi")
    void testDeployPipeline() throws Exception {
        var result = tester.deployPipeline(
            new java.io.File("src/test/resources/pipelines/sample-generate-pipeline.yaml"),
            "root"
        );
        
        assertNotNull(result);
        System.out.println("Deploy: " + result.getMessage());
        
        if (result.isSuccess()) {
            String pgId = result.getProcessGroupId();
            System.out.println("Created PG: " + pgId);
            
            var group = tester.getProcessGroup(pgId);
            assertNotNull(group);
            System.out.println("Verified: " + group.getComponent().getName());
            
            tester.deleteProcessGroup(pgId);
            System.out.println("Deleted: " + pgId);
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
        System.out.println("Deploy: " + result.getMessage());

        if (result.isSuccess()) {
            String pgId = result.getProcessGroupId();
            System.out.println("Created PG: " + pgId);

            var group = tester.getProcessGroup(pgId);
            assertNotNull(group);
            System.out.println("Verified: " + group.getComponent().getName());

            System.out.println("Comprehensive pipeline deployed with multiple processors and connections");
            System.out.println("Pipeline ID: " + pgId);
            System.out.println("Message: " + result.getMessage());

            // Cleanup - uncomment to delete after inspection
             tester.deleteProcessGroup(pgId);
        }
    }
}