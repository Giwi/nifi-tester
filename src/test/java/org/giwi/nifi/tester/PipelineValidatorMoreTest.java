/*
 * Copyright 2026 GiWi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.giwi.nifi.tester;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PipelineValidatorMoreTest {

    private PipelineValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PipelineValidator();
    }

    private File createTempFile(String content) throws Exception {
        File temp = File.createTempFile("test-pipeline", ".yaml");
        try (FileWriter fw = new FileWriter(temp)) {
            fw.write(content);
        }
        temp.deleteOnExit();
        return temp;
    }

    @Test
    @DisplayName("Validate empty YAML map")
    void testValidateEmptyMap() {
        List<String> errors = validator.validatePipeline(new HashMap<>());
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("name")));
    }

    @Test
    @DisplayName("Validate null name in map")
    void testValidateNullName() {
        Map<String, Object> pipeline = new HashMap<>();
        pipeline.put("name", null);
        List<String> errors = validator.validatePipeline(pipeline);
        assertFalse(errors.isEmpty());
    }

    @Test
    @DisplayName("Validate with duplicate input port names")
    void testDuplicateInputPorts() throws Exception {
        String yaml = """
            name: Test
            inputPorts:
              - name: Port1
              - name: Port1
            """;
        List<String> errors = validator.validate(createTempFile(yaml));
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("duplicate")));
    }

    @Test
    @DisplayName("Validate with duplicate output port names")
    void testDuplicateOutputPorts() throws Exception {
        String yaml = """
            name: Test
            outputPorts:
              - name: Port1
              - name: Port1
            """;
        List<String> errors = validator.validate(createTempFile(yaml));
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("duplicate")));
    }

    @Test
    @DisplayName("Validate connection with missing source")
    void testConnectionMissingSource() throws Exception {
        String yaml = """
            name: Test
            connections:
              - name: Conn1
                destinationId: ${Proc1}
                relationships:
                  - success
            """;
        List<String> errors = validator.validate(createTempFile(yaml));
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("sourceId")));
    }

    @Test
    @DisplayName("Validate connection with missing destination")
    void testConnectionMissingDestination() throws Exception {
        String yaml = """
            name: Test
            connections:
              - name: Conn1
                sourceId: ${Proc1}
                relationships:
                  - success
            """;
        List<String> errors = validator.validate(createTempFile(yaml));
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("destinationId")));
    }

    @Test
    @DisplayName("Validate processor with invalid type format")
    void testInvalidProcessorType() throws Exception {
        String yaml = """
            name: Test
            processors:
              - name: Proc1
                type: InvalidType
            """;
        List<String> errors = validator.validate(createTempFile(yaml));
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("type format")));
    }

    @Test
    @DisplayName("Validate valid pipeline with all components")
    void testValidFullPipeline() throws Exception {
        String yaml = """
            name: Full Pipeline
            processors:
              - name: P1
                type: org.apache.nifi.processors.standard.GenerateFlowFile
            inputPorts:
              - name: Input
            outputPorts:
              - name: Output
            connections:
              - name: Conn1
                sourceId: ${P1}
                destinationId: ${Output}
                relationships:
                  - success
            processGroups:
              - name: SubGroup
            """;
        List<String> errors = validator.validate(createTempFile(yaml));
        assertTrue(errors.isEmpty(), "Expected no errors but got: " + errors);
    }

    @Test
    @DisplayName("Validate with invalid YAML syntax")
    void testInvalidYamlSyntax() throws Exception {
        File tempFile = createTempFile("invalid: [yaml: content");
        List<String> errors = validator.validate(tempFile);
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("YAML")));
    }

    @Test
    @DisplayName("Validate null file")
    void testNullFile() {
        List<String> errors = validator.validate(null);
        assertFalse(errors.isEmpty());
    }

    @Test
    @DisplayName("Validate non-existent file")
    void testNonExistentFile() {
        List<String> errors = validator.validate(new File("/non/existent/file.yaml"));
        assertFalse(errors.isEmpty());
    }
}
