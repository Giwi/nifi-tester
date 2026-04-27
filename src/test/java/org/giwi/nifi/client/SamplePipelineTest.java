package org.giwi.nifi.client;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SamplePipelineTest {

    private PipelineConverter converter;

    @BeforeEach
    void setUp() {
        converter = new PipelineConverter();
    }

    @Nested
    @DisplayName("Sample Pipeline YAML Tests")
    class SampleTests {

        @Test
        @DisplayName("Load sample pipeline from classpath")
        void testLoadSamplePipeline() throws Exception {
            URL url = getClass().getClassLoader().getResource("pipelines/sample-generate-pipeline.yaml");
            assertNotNull(url, "Sample YAML file not found in classpath");
            
            File yamlFile = new File(url.toURI());
            assertTrue(yamlFile.exists());
            
            Map<String, Object> result = converter.convertFromYaml(yamlFile);
            assertEquals("Sample GenerateFlowFile Pipeline", result.get("name"));
        }

        @Test
        @DisplayName("Verify sample pipeline structure")
        void testVerifyPipelineStructure() throws Exception {
            URL url = getClass().getClassLoader().getResource("pipelines/sample-generate-pipeline.yaml");
            File yamlFile = new File(url.toURI());
            
            Map<String, Object> result = converter.convertFromYaml(yamlFile);
            
            assertNotNull(result.get("processors"));
            assertNotNull(result.get("connections"));
            
            List<?> processors = (List<?>) result.get("processors");
            assertEquals(2, processors.size());
            
            List<?> connections = (List<?>) result.get("connections");
            assertEquals(1, connections.size());
        }

        @Test
        @DisplayName("Verify processor types in sample pipeline")
        void testVerifyProcessorTypes() throws Exception {
            URL url = getClass().getClassLoader().getResource("pipelines/sample-generate-pipeline.yaml");
            File yamlFile = new File(url.toURI());
            
            Map<String, Object> result = converter.convertFromYaml(yamlFile);
            
            List<?> processors = (List<?>) result.get("processors");
            
            for (Object procObj : processors) {
                Map<String, Object> proc = (Map<String, Object>) procObj;
                Map<String, Object> dto = (Map<String, Object>) proc.get("processor");
                String type = (String) dto.get("type");
                assertTrue(type.startsWith("org.apache.nifi"));
            }
        }

        @Test
        @DisplayName("Verify connection relationships in sample pipeline")
        void testVerifyConnectionRelationships() throws Exception {
            URL url = getClass().getClassLoader().getResource("pipelines/sample-generate-pipeline.yaml");
            File yamlFile = new File(url.toURI());
            
            Map<String, Object> result = converter.convertFromYaml(yamlFile);
            
            List<?> connections = (List<?>) result.get("connections");
            Map<String, Object> conn = (Map<String, Object>) connections.get(0);
            Map<String, Object> dto = (Map<String, Object>) conn.get("connection");
            List<?> rels = (List<?>) dto.get("selectedRelationships");
            assertTrue(rels.contains("success"));
        }

        @Test
        @DisplayName("Convert sample pipeline to YAML")
        void testConvertToYaml() throws Exception {
            URL url = getClass().getClassLoader().getResource("pipelines/sample-generate-pipeline.yaml");
            File yamlFile = new File(url.toURI());
            
            Map<String, Object> result = converter.convertFromYaml(yamlFile);
            String yamlOutput = converter.convertToYaml(result);
            
            assertNotNull(yamlOutput);
            assertTrue(yamlOutput.contains("Sample GenerateFlowFile Pipeline"));
        }
    }

    @Nested
    @DisplayName("Parameterized Tests")
    class ParameterizedTests {

        @ParameterizedTest
        @ValueSource(strings = {"root", "PGM-123"})
        @DisplayName("Parent group test")
        void testParentGroups(String parentGroupId) throws Exception {
            URL url = getClass().getClassLoader().getResource("pipelines/sample-generate-pipeline.yaml");
            File yamlFile = new File(url.toURI());
            
            Map<String, Object> result = converter.convertFromYaml(yamlFile);
            assertNotNull(result);
        }
    }
}