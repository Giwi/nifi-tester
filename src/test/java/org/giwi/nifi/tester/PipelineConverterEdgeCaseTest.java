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

class PipelineConverterEdgeCaseTest {

    private PipelineConverter converter;

    @BeforeEach
    void setUp() {
        converter = new PipelineConverter();
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
    @DisplayName("Convert empty file returns default")
    void testEmptyFile() throws Exception {
        File emptyFile = createTempFile("");
        Map<String, Object> result = converter.convertFromYaml(emptyFile);
        assertNotNull(result);
        assertEquals("Untitled Pipeline", result.get("name"));
    }

    @Test
    @DisplayName("Convert empty map returns defaults")
    void testEmptyMap() {
        Map<String, Object> result = converter.convertFromMap(new HashMap<>());
        assertNotNull(result);
        assertEquals("Untitled Pipeline", result.get("name"));
        assertEquals("root", result.get("parentGroupId"));
    }

    @Test
    @DisplayName("Convert with all component types")
    void testAllComponentTypes() throws Exception {
        String yaml = """
            name: Full Pipeline
            parentGroupId: root
            processors:
              - name: Proc1
                type: org.apache.nifi.processors.standard.GenerateFlowFile
            connections:
              - name: Conn1
                sourceId: ${Proc1}
                destinationId: ${Proc1}
                relationships:
                  - success
            inputPorts:
              - name: Input
            outputPorts:
              - name: Output
            processGroups:
              - name: SubGroup
            remoteProcessGroups:
              - name: Remote
                targetUris: http://remote:8080/nifi
            funnels:
              - name: Funnel1
            """;

        Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));

        assertNotNull(result.get("processors"));
        assertNotNull(result.get("connections"));
        assertNotNull(result.get("inputPorts"));
        assertNotNull(result.get("outputPorts"));
        assertNotNull(result.get("processGroups"));
        assertNotNull(result.get("remoteProcessGroups"));
        assertNotNull(result.get("funnels"));
    }

    @Test
    @DisplayName("Convert processor with all optional fields")
    void testProcessorAllFields() throws Exception {
        String yaml = """
            name: Test
            processors:
              - name: Proc1
                type: org.apache.nifi.processors.standard.GenerateFlowFile
                state: RUNNING
                schedulingStrategy: TIMER_DRIVEN
                schedulingPeriod: 1 sec
                concurrentlySchedulableTaskCount: 2
                runDurationMillis: 1000
                autoTerminatedRelationships:
                  - failure
                  - success
                x: 500.0
                y: 300.0
                properties:
                  File Size: 1 KB
            """;

        Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
        List<?> processors = (List<?>) result.get("processors");
        Map<String, Object> proc = (Map<String, Object>) ((Map<String, Object>) processors.get(0)).get("processor");
        Map<String, Object> dto = proc;

        assertEquals("RUNNING", dto.get("state"));
        assertEquals("TIMER_DRIVEN", dto.get("schedulingStrategy"));
        assertEquals("1 sec", dto.get("schedulingPeriod"));
        assertEquals(2, dto.get("concurrentlySchedulableTaskCount"));
        assertEquals(1000, dto.get("runDurationMillis"));
    }

    @Test
    @DisplayName("Convert connection with bends")
    void testConnectionWithBends() throws Exception {
        String yaml = """
            name: Test
            connections:
              - name: Conn1
                sourceId: ${P1}
                destinationId: ${P2}
                relationships:
                  - success
                bends:
                  - x: 100.0
                    y: 200.0
                  - x: 300.0
                    y: 400.0
            """;

        Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
        List<?> connections = (List<?>) result.get("connections");
        Map<String, Object> conn = (Map<String, Object>) ((Map<String, Object>) connections.get(0)).get("connection");

        assertNotNull(conn.get("bends"));
    }

    @Test
    @DisplayName("Convert to YAML and back")
    void testConvertToYamlAndBack() throws Exception {
        String yaml = "name: TestPipe\nprocessors:\n  - name: P1\n    type: org.apache.nifi.processors.standard.LogAttribute\n";
        Map<String, Object> result1 = converter.convertFromYaml(createTempFile(yaml));

        String yamlOut = converter.convertToYaml(result1);
        assertNotNull(yamlOut);
        assertTrue(yamlOut.contains("TestPipe"));
    }

    @Test
    @DisplayName("Convert map with null name uses default")
    void testNullNameInMap() {
        Map<String, Object> yaml = new HashMap<>();
        yaml.put("name", null);

        Map<String, Object> result = converter.convertFromMap(yaml);
        assertEquals("Untitled Pipeline", result.get("name"));
    }

    @Test
    @DisplayName("Convert processor with missing type")
    void testProcessorMissingType() throws Exception {
        String yaml = "name: Test\nprocessors:\n  - name: Proc1\n";
        Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));

        List<?> processors = (List<?>) result.get("processors");
        assertEquals(1, processors.size());
    }

    @Test
    @DisplayName("Convert connection with back pressure settings")
    void testConnectionBackPressure() throws Exception {
        String yaml = """
            name: Test
            connections:
              - name: Conn1
                sourceId: ${P1}
                destinationId: ${P2}
                relationships:
                  - success
                flowFileExpiration: 30 sec
                backPressureDataSizeThreshold: 500 MB
                backPressureObjectThreshold: 5000
            """;

        Map<String, Object> result = converter.convertFromYaml(createTempFile(yaml));
        List<?> connections = (List<?>) result.get("connections");
        Map<String, Object> conn = (Map<String, Object>) ((Map<String, Object>) connections.get(0)).get("connection");

        assertEquals("30 sec", conn.get("flowFileExpiration"));
        assertEquals("500 MB", conn.get("backPressureDataSizeThreshold"));
        assertEquals("5000", conn.get("backPressureObjectThreshold"));
    }
}
