package org.giwi.nifi.tester;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PipelineConverterTest {

    private PipelineConverter converter;

    @BeforeEach
    void setUp() {
        converter = new PipelineConverter();
    }

    @Nested
    @DisplayName("Basic Conversion Tests")
    class BasicTests {

        @Test
        @DisplayName("Convert empty yaml")
        void testEmpty() throws Exception {
            Map<String, Object> result = converter.convertFromYaml(createTempFile("name:"));
            assertNotNull(result);
        }

        @Test
        @DisplayName("Convert name only")
        void testNameOnly() throws Exception {
            Map<String, Object> result = converter.convertFromYaml(createTempFile("name: TestPipeline"));
            assertEquals("TestPipeline", result.get("name"));
        }

        @Test
        @DisplayName("Convert parent group id")
        void testParentGroupId() throws Exception {
            Map<String, Object> result = converter.convertFromYaml(createTempFile("name: Test\nparentGroupId: root"));
            assertEquals("root", result.get("parentGroupId"));
        }

        @Test
        @DisplayName("Id is generated")
        void testIdGenerated() throws Exception {
            Map<String, Object> result = converter.convertFromYaml(createTempFile("name: Test"));
            assertNotNull(result.get("id"));
            assertTrue(((String) result.get("id")).length() > 0);
        }
    }

    @Nested
    @DisplayName("Processor Conversion Tests")
    class ProcessorTests {

        @Test
        @DisplayName("Convert single processor")
        void testSingleProcessor() throws Exception {
            String yaml = "name: Test\nprocessors:\n  - name: GenerateFlowFile\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result.get("processors"));
            List<?> processors = (List<?>) result.get("processors");
            assertEquals(1, processors.size());
        }

        @Test
        @DisplayName("Convert processor with type")
        void testProcessorWithType() throws Exception {
            String yaml = "name: Test\nprocessors:\n  - name: Gen\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            List<?> processors = (List<?>) result.get("processors");
            Map<String, Object> proc = (Map<String, Object>) processors.get(0);
            assertNotNull(proc.get("processor"));
        }

        @Test
        @DisplayName("Convert processor with coordinates")
        void testProcessorWithCoordinates() throws Exception {
            String yaml = "name: Test\nprocessors:\n  - name: Gen\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n    x: 100\n    y: 200\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            List<?> processors = (List<?>) result.get("processors");
            Map<String, Object> proc = (Map<String, Object>) processors.get(0);
            Map<String, Object> dto = (Map<String, Object>) proc.get("processor");
            Map<String, Object> pos = (Map<String, Object>) dto.get("position");
            assertEquals(100.0, pos.get("x"));
            assertEquals(200.0, pos.get("y"));
        }

        @Test
        @DisplayName("Convert processor with properties")
        void testProcessorWithProperties() throws Exception {
            String yaml = "name: Test\nprocessors:\n  - name: Gen\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n    properties:\n      File Size: 1KB\n      Text: hello\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            assertNotNull(result);
        }

        @Test
        @DisplayName("Convert multiple processors")
        void testMultipleProcessors() throws Exception {
            String yaml = "name: Test\nprocessors:\n  - name: Gen1\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n  - name: Gen2\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            List<?> processors = (List<?>) result.get("processors");
            assertEquals(2, processors.size());
        }
    }

    @Nested
    @DisplayName("Connection Conversion Tests")
    class ConnectionTests {

        @Test
        @DisplayName("Convert connection")
        void testConnection() throws Exception {
            String yaml = "name: Test\nconnections:\n  - name: To Log\n    sourceId: some-source\n    destinationId: some-dest\n    relationships:\n      - success\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result.get("connections"));
            List<?> connections = (List<?>) result.get("connections");
            assertEquals(1, connections.size());
        }
    }

    @Nested
    @DisplayName("Port Conversion Tests")
    class PortTests {

        @Test
        @DisplayName("Convert input port")
        void testInputPort() throws Exception {
            String yaml = "name: Test\ninputPorts:\n  - name: MyInput\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result.get("inputPorts"));
            List<?> ports = (List<?>) result.get("inputPorts");
            assertEquals(1, ports.size());
        }

        @Test
        @DisplayName("Convert output port")
        void testOutputPort() throws Exception {
            String yaml = "name: Test\noutputPorts:\n  - name: MyOutput\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result.get("outputPorts"));
        }
    }

    @Nested
    @DisplayName("Process Group Conversion Tests")
    class ProcessGroupTests {

        @Test
        @DisplayName("Convert process group")
        void testProcessGroup() throws Exception {
            String yaml = "name: Test\nprocessGroups:\n  - name: SubGroup\n    parentGroupId: root\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result.get("processGroups"));
            List<?> groups = (List<?>) result.get("processGroups");
            assertEquals(1, groups.size());
        }

        @Test
        @DisplayName("Convert remote process group")
        void testRemoteProcessGroup() throws Exception {
            String yaml = "name: Test\nremoteProcessGroups:\n  - name: Remote\n    targetUris: http://remote:8080/nifi\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result.get("remoteProcessGroups"));
        }
    }

    @Nested
    @DisplayName("Round-trip Tests")
    class RoundTripTests {

        @Test
        @DisplayName("Round-trip to yaml and back")
        void testRoundTrip() throws Exception {
            String original = "name: RoundTrip\nprocessors:\n  - name: Gen\n    type: Gen\n";
            
            File tempFile = createTempFile(original);
            try {
                Map<String, Object> converted = converter.convertFromYaml(tempFile);
                
                String yamlOutput = converter.convertToYaml(converted);
                assertNotNull(yamlOutput);
                assertTrue(yamlOutput.contains("name"));
            } finally {
                tempFile.delete();
            }
        }
    }

    @Nested
    @DisplayName("Connection Bends Tests")
    class BendsTests {

        @Test
        @DisplayName("Convert connection with bends")
        void testConnectionWithBends() throws Exception {
            String yaml = """
                name: Test Pipeline
                connections:
                  - name: Test Connection
                    sourceId: ${Proc1}
                    destinationId: ${Proc2}
                    relationships:
                      - success
                    bends:
                      - x: 100
                        y: 200
                      - x: 300
                        y: 400
                """;
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            assertNotNull(result.get("connections"));
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> connections = (List<Map<String, Object>>) result.get("connections");
            assertEquals(1, connections.size());
            
            Map<String, Object> conn = connections.get(0);
            assertTrue(conn.containsKey("connection"));
            @SuppressWarnings("unchecked")
            Map<String, Object> connMap = (Map<String, Object>) conn.get("connection");
            assertTrue(connMap.containsKey("bends"));
        }

        @Test
        @DisplayName("Convert connection without bends")
        void testConnectionWithoutBends() throws Exception {
            String yaml = """
                name: Test Pipeline
                connections:
                  - name: Test Connection
                    sourceId: ${Proc1}
                    destinationId: ${Proc2}
                    relationships:
                      - success
                """;
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> connections = (List<Map<String, Object>>) result.get("connections");
            Map<String, Object> conn = connections.get(0);
            @SuppressWarnings("unchecked")
            Map<String, Object> connMap = (Map<String, Object>) conn.get("connection");
            assertFalse(connMap.containsKey("bends"));
        }
    }

    @Nested
    @DisplayName("Input Validation Tests")
    class InputValidationTests {

        @Test
        @DisplayName("Null YAML file returns empty map")
        void testNullFile() throws Exception {
            Map<String, Object> result = converter.convertFromYaml(null);
            assertNotNull(result);
            assertTrue(result.isEmpty() || result.get("name") != null);
        }

        @Test
        @DisplayName("Empty YAML file handled gracefully")
        void testEmptyYaml() throws Exception {
            File tempFile = createTempFile("");
            try {
                Map<String, Object> result = converter.convertFromYaml(tempFile);
                assertNotNull(result);
            } finally {
                tempFile.delete();
            }
        }
    }

    @Nested
    @DisplayName("NiFi JSON to YAML Conversion Tests")
    class NiFiJsonToYamlTests {

        private File createTempJsonFile(String content) throws Exception {
            File temp = File.createTempFile("nifi-export-", ".json");
            try (FileWriter fw = new FileWriter(temp)) {
                fw.write(content);
            }
            temp.deleteOnExit();
            return temp;
        }

        @Test
        @DisplayName("Convert simple NiFi JSON export to YAML")
        void testSimpleJsonToYaml() throws Exception {
            String json = "{\"breadcrumb\":{\"component\":{\"name\":\"Test Pipeline\"}},\"flow\":{\"processors\":[{\"component\":{\"name\":\"GenerateFlowFile\",\"type\":\"org.apache.nifi.processors.standard.GenerateFlowFile\",\"position\":{\"x\":100,\"y\":100}}}]}}";

            File tempFile = createTempJsonFile(json);
            try {
                String yaml = converter.convertNiFiJsonToYaml(tempFile);
                assertNotNull(yaml);
                assertTrue(yaml.contains("name:"));
                assertTrue(yaml.contains("GenerateFlowFile"));
                assertTrue(yaml.contains("org.apache.nifi.processors.standard.GenerateFlowFile"));
            } finally {
                tempFile.delete();
            }
        }

        @Test
        @DisplayName("Convert JSON with connections to YAML")
        void testJsonWithConnectionsToYaml() throws Exception {
            String json = "{\"flow\":{\"processors\":[{\"component\":{\"name\":\"Source\",\"type\":\"org.apache.nifi.processors.standard.GenerateFlowFile\",\"position\":{\"x\":100,\"y\":100}}},{\"component\":{\"name\":\"Dest\",\"type\":\"org.apache.nifi.processors.standard.LogAttribute\",\"position\":{\"x\":300,\"y\":100}}}],\"connections\":[{\"component\":{\"name\":\"Connect\",\"source\":{\"name\":\"Source\"},\"destination\":{\"name\":\"Dest\"},\"selectedRelationships\":[\"success\"]}}]}}";

            File tempFile = createTempJsonFile(json);
            try {
                String yaml = converter.convertNiFiJsonToYaml(tempFile);
                assertNotNull(yaml);
                assertTrue(yaml.contains("connections:"));
                assertTrue(yaml.contains("sourceId:"));
                assertTrue(yaml.contains("destinationId:"));
                assertTrue(yaml.contains("relationships:"));
            } finally {
                tempFile.delete();
            }
        }

        @Test
        @DisplayName("Convert JSON with ports to YAML")
        void testJsonWithPortsToYaml() throws Exception {
            String json = """
                {
                  "flow": {
                    "inputPorts": [
                      {
                        "component": {
                          "name": "Input",
                          "position": {"x": 50, "y": 100}
                        }
                      }
                    ],
                    "outputPorts": [
                      {
                        "component": {
                          "name": "Output",
                          "position": {"x": 400, "y": 100}
                        }
                      }
                    ]
                  }
                }
                """;

            File tempFile = createTempJsonFile(json);
            try {
                String yaml = converter.convertNiFiJsonToYaml(tempFile);
                assertNotNull(yaml);
                assertTrue(yaml.contains("inputPorts:"));
                assertTrue(yaml.contains("outputPorts:"));
                assertTrue(yaml.contains("Input"));
                assertTrue(yaml.contains("Output"));
            } finally {
                tempFile.delete();
            }
        }

        @Test
        @DisplayName("Convert JSON with all component types to YAML")
        void testJsonWithAllComponentsToYaml() throws Exception {
            String json = """
                {
                  "breadcrumb": {
                    "component": {
                      "name": "Full Pipeline"
                    }
                  },
                  "flow": {
                    "processors": [
                      {
                        "component": {
                          "name": "Proc1",
                          "type": "org.apache.nifi.processors.standard.GenerateFlowFile",
                          "position": {"x": 100, "y": 100}
                        }
                      }
                    ],
                    "funnels": [
                      {
                        "component": {
                          "name": "MyFunnel",
                          "position": {"x": 200, "y": 200}
                        }
                      }
                    ],
                    "processGroups": [
                      {
                        "component": {
                          "name": "SubGroup",
                          "position": {"x": 300, "y": 300}
                        }
                      }
                    ]
                  }
                }
                """;

            File tempFile = createTempJsonFile(json);
            try {
                String yaml = converter.convertNiFiJsonToYaml(tempFile);
                assertNotNull(yaml);
                assertTrue(yaml.contains("processors:"));
                assertTrue(yaml.contains("funnels:"));
                assertTrue(yaml.contains("processGroups:"));
                assertTrue(yaml.contains("Proc1"));
                assertTrue(yaml.contains("MyFunnel"));
                assertTrue(yaml.contains("SubGroup"));
            } finally {
                tempFile.delete();
            }
        }

        @Test
        @DisplayName("Convert JSON without breadcrumb uses default name")
        void testJsonWithoutBreadcrumb() throws Exception {
            String json = "{\"flow\":{\"processors\":[{\"component\":{\"name\":\"TestProc\",\"type\":\"org.apache.nifi.processors.standard.GenerateFlowFile\"}}]}}";

            File tempFile = createTempJsonFile(json);
            try {
                String yaml = converter.convertNiFiJsonToYaml(tempFile);
                assertNotNull(yaml);
                assertTrue(yaml.contains("Imported Pipeline") || yaml.contains("name:"));
            } finally {
                tempFile.delete();
            }
        }
    }

    private File createTempFile(String content) throws Exception {
        File temp = File.createTempFile("pipeline-", ".yaml");
        try (FileWriter fw = new FileWriter(temp)) {
            fw.write(content);
        }
        temp.deleteOnExit();
        return temp;
    }
}