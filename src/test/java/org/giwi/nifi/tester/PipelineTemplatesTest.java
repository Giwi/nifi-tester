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

class PipelineTemplatesTest {

    private PipelineTemplates templates;
    private File tempDir;

    @BeforeEach
    void setUp(@TempDir File tempDir) {
        templates = new PipelineTemplates();
        this.tempDir = tempDir;
    }

    @Test
    @DisplayName("Save and load template")
    void testSaveAndLoadTemplate() throws Exception {
        String content = "name: Template1\nprocessors:\n  - name: Gen\n    type: org.apache.nifi.processors.standard.GenerateFlowFile";
        templates.saveTemplate("test-template", content);

        String loaded = templates.loadTemplate("test-template");
        assertNotNull(loaded);
        assertTrue(loaded.contains("Template1"));

        // Cleanup
        templates.deleteTemplate("test-template");
    }

    @Test
    @DisplayName("List templates")
    void testListTemplates() throws Exception {
        templates.saveTemplate("list-test1", "name: Test1");
        templates.saveTemplate("list-test2", "name: Test2");

        String[] templateList = templates.listTemplates();
        assertTrue(templateList.length >= 2);

        // Cleanup
        templates.deleteTemplate("list-test1");
        templates.deleteTemplate("list-test2");
    }

    @Test
    @DisplayName("Delete template")
    void testDeleteTemplate() throws Exception {
        templates.saveTemplate("to-delete", "name: DeleteMe");
        assertTrue(templates.deleteTemplate("to-delete"));
        assertFalse(templates.deleteTemplate("to-delete")); // Already deleted
    }

    @Test
    @DisplayName("Load non-existent template throws exception")
    void testLoadNonExistentTemplate() throws Exception {
        assertThrows(Exception.class, () -> templates.loadTemplate("non-existent"));
    }

    @Test
    @DisplayName("List templates when empty")
    void testListTemplatesEmpty() throws Exception {
        String[] templateList = templates.listTemplates();
        assertNotNull(templateList);
    }

    @Test
    @DisplayName("Overwrite existing template")
    void testOverwriteTemplate() throws Exception {
        String content1 = "name: Template1";
        String content2 = "name: Template2";
        templates.saveTemplate("overwrite-test", content1);
        templates.saveTemplate("overwrite-test", content2);

        String loaded = templates.loadTemplate("overwrite-test");
        assertTrue(loaded.contains("Template2"));

        // Cleanup
        templates.deleteTemplate("overwrite-test");
    }
}
