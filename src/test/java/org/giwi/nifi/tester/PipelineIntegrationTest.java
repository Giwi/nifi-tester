package org.giwi.nifi.tester;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PipelineIntegrationTest {

    private PipelineConverter converter;
    private final List<String> cleanupIds = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception {
        converter = new PipelineConverter();
    }

    @Nested
    @DisplayName("Pipeline Converter Tests")
    class ConverterTests {

        @Test
        @DisplayName("Convert simple processor yaml")
        void testConvertSimpleProcessor() throws Exception {
            String yaml = "name: Test\nprocessors:\n  - name: Gen\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result);
            assertEquals("Test", result.get("name"));
            assertNotNull(result.get("processors"));
        }

        @Test
        @DisplayName("Convert processor with properties")
        void testConvertWithProperties() throws Exception {
            String yaml = "name: Prop Test\nprocessors:\n  - name: Gen\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n    properties:\n      File Size: 1KB\n      Text: hello\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result);
        }

        @Test
        @DisplayName("Convert processor with coordinates")
        void testConvertWithCoordinates() throws Exception {
            String yaml = "name: Coord Test\nprocessors:\n  - name: Gen\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n    x: 100\n    y: 200\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result);
        }

        @Test
        @DisplayName("Convert connections")
        void testConvertConnections() throws Exception {
            String yaml = "name: Connection Test\nprocessors:\n  - name: Gen\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n  - name: Log\n    type: org.apache.nifi.processors.standard.LogAttribute\nconnections:\n  - name: To Log\n    sourceId: ${Gen}\n    destinationId: ${Log}\n    relationships:\n      - success\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result);
            assertNotNull(result.get("connections"));
        }

        @Test
        @DisplayName("Convert input ports")
        void testConvertInputPorts() throws Exception {
            String yaml = "name: Input Ports Test\ninputPorts:\n  - name: Input\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result);
            assertNotNull(result.get("inputPorts"));
        }

        @Test
        @DisplayName("Convert output ports")
        void testConvertOutputPorts() throws Exception {
            String yaml = "name: Output Ports Test\noutputPorts:\n  - name: Output\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result);
            assertNotNull(result.get("outputPorts"));
        }

        @Test
        @DisplayName("Convert remote process groups")
        void testConvertRemoteProcessGroups() throws Exception {
            String yaml = "name: Remote Test\nremoteProcessGroups:\n  - name: Remote\n    targetUris: http://remote:8080/nifi\n";
            
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            
            assertNotNull(result);
            assertNotNull(result.get("remoteProcessGroups"));
        }
    }

    @Nested
    @DisplayName("Pipeline YAML Format Tests")
    class YamlFormatTests {

        @Test
        @DisplayName("Empty yaml")
        void testEmpty() throws Exception {
            String yaml = "";
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            assertNotNull(result);
        }

        @Test
        @DisplayName("Only name")
        void testNameOnly() throws Exception {
            String yaml = "name: TestOnly";
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            assertEquals("TestOnly", result.get("name"));
        }

        @Test
        @DisplayName("With comments")
        void testWithComments() throws Exception {
            String yaml = "# This is a comment\nname: CommentTest\n# Another comment\n";
            Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
            assertEquals("CommentTest", result.get("name"));
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