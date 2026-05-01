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
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PipelineValidatorTest {

    private PipelineValidator validator;
    private File tempDir;

    @BeforeEach
    void setUp(@TempDir File tempDir) {
        validator = new PipelineValidator();
        this.tempDir = tempDir;
    }

    private File createTempFile(String content) throws Exception {
        File temp = new File(tempDir, "test-pipeline.yaml");
        try (FileWriter fw = new FileWriter(temp)) {
            fw.write(content);
        }
        return temp;
    }

    @Test
    @DisplayName("Valid pipeline passes validation")
    void testValidPipeline() throws Exception {
        File tempFile = createTempFile("name: ValidPipeline\nprocessors:\n  - name: Proc1\n    type: org.apache.nifi.processors.standard.GenerateFlowFile");
        try {
            List<String> errors = validator.validate(tempFile);
            assertTrue(errors.isEmpty(), "Expected no errors but got: " + errors);
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Missing name fails validation")
    void testMissingName() throws Exception {
        File tempFile = createTempFile("processors:\n  - name: Proc1\n    type: org.apache.nifi.processors.standard.GenerateFlowFile");
        try {
            List<String> errors = validator.validate(tempFile);
            assertFalse(errors.isEmpty(), "Expected validation errors");
            assertTrue(errors.stream().anyMatch(e -> e.contains("name")), "Expected error containing 'name', got: " + errors);
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Missing processor name fails validation")
    void testMissingProcessorName() throws Exception {
        File tempFile = createTempFile("name: Test\nprocessors:\n  - type: org.apache.nifi.processors.standard.GenerateFlowFile");
        try {
            List<String> errors = validator.validate(tempFile);
            assertFalse(errors.isEmpty(), "Expected validation errors");
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Missing processor type fails validation")
    void testMissingProcessorType() throws Exception {
        File tempFile = createTempFile("name: Test\nprocessors:\n  - name: Proc1");
        try {
            List<String> errors = validator.validate(tempFile);
            assertFalse(errors.isEmpty(), "Expected validation errors");
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Invalid connection source fails validation")
    void testInvalidConnectionSource() throws Exception {
        String yaml = "name: Test\nprocessors:\n  - name: Proc1\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\nconnections:\n  - name: Conn1\n    sourceId: ${NonExistent}\n    destinationId: ${Proc1}\n    relationships:\n      - success";
        File tempFile = createTempFile(yaml);
        try {
            List<String> errors = validator.validate(tempFile);
            assertFalse(errors.isEmpty(), "Expected validation errors");
            assertTrue(errors.stream().anyMatch(e -> e.contains("NonExistent")), "Expected error containing 'NonExistent', got: " + errors);
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Invalid connection destination fails validation")
    void testInvalidConnectionDestination() throws Exception {
        String yaml = "name: Test\nprocessors:\n  - name: Proc1\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\nconnections:\n  - name: Conn1\n    sourceId: ${Proc1}\n    destinationId: ${NonExistent}\n    relationships:\n      - success";
        File tempFile = createTempFile(yaml);
        try {
            List<String> errors = validator.validate(tempFile);
            assertFalse(errors.isEmpty(), "Expected validation errors");
            assertTrue(errors.stream().anyMatch(e -> e.contains("NonExistent")), "Expected error containing 'NonExistent', got: " + errors);
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Missing relationship in connection fails")
    void testMissingRelationship() throws Exception {
        String yaml = "name: Test\nprocessors:\n  - name: Proc1\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\nconnections:\n  - name: Conn1\n    sourceId: ${Proc1}\n    destinationId: ${Proc1}";
        File tempFile = createTempFile(yaml);
        try {
            List<String> errors = validator.validate(tempFile);
            assertFalse(errors.isEmpty(), "Expected validation errors");
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Duplicate component names fail validation")
    void testDuplicateNames() throws Exception {
        String yaml = "name: Test\nprocessors:\n  - name: Proc1\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n  - name: Proc1\n    type: org.apache.nifi.processors.standard.LogAttribute";
        File tempFile = createTempFile(yaml);
        try {
            List<String> errors = validator.validate(tempFile);
            assertFalse(errors.isEmpty(), "Expected validation errors for duplicate names");
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Non-existent file fails validation")
    void testNonExistentFile() throws Exception {
        List<String> errors = validator.validate(new File("/non/existent/file.yaml"));
        assertFalse(errors.isEmpty(), "Expected validation errors");
    }

    @Test
    @DisplayName("Invalid YAML fails validation")
    void testInvalidYaml() throws Exception {
        File tempFile = createTempFile("invalid: [yaml: content");
        try {
            List<String> errors = validator.validate(tempFile);
            assertFalse(errors.isEmpty(), "Expected validation errors");
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Valid pipeline with ports passes")
    void testValidPipelineWithPorts() throws Exception {
        String yaml = "name: Test\ninputPorts:\n  - name: Input\noutputPorts:\n  - name: Output\nprocessors:\n  - name: Proc1\n    type: org.apache.nifi.processors.standard.GenerateFlowFile";
        File tempFile = createTempFile(yaml);
        try {
            List<String> errors = validator.validate(tempFile);
            assertTrue(errors.isEmpty(), "Expected no errors but got: " + errors);
        } finally {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("Valid pipeline with connections passes")
    void testValidPipelineWithConnections() throws Exception {
        String yaml = "name: Test\nprocessors:\n  - name: P1\n    type: org.apache.nifi.processors.standard.GenerateFlowFile\n  - name: P2\n    type: org.apache.nifi.processors.standard.LogAttribute\nconnections:\n  - name: C1\n    sourceId: ${P1}\n    destinationId: ${P2}\n    relationships:\n      - success";
        File tempFile = createTempFile(yaml);
        try {
            List<String> errors = validator.validate(tempFile);
            assertTrue(errors.isEmpty(), "Expected no errors but got: " + errors);
        } finally {
            tempFile.delete();
        }
    }
}
