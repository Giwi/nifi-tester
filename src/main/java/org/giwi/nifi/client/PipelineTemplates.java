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
package org.giwi.nifi.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages YAML pipeline templates for reusable snippets.
 *
 * <p>Allows users to save and load reusable pipeline components
 * such as common processor configurations or connection patterns.</p>
 *
 * <p>Usage example:
 * <pre>{@code
 * PipelineTemplates templates = new PipelineTemplates();
 * templates.saveTemplate("my-processor", yamlSnippet);
 * String snippet = templates.loadTemplate("my-processor");
 * }</pre>
 *
 * @author GiWi
 * @version 1.0-SNAPSHOT
 */
public class PipelineTemplates {
    private static final String TEMPLATES_DIR = ".nifi-tester-templates";
    private final ObjectMapper mapper;

    public PipelineTemplates() {
        this.mapper = new ObjectMapper(new YAMLFactory());
        this.mapper.findAndRegisterModules();
    }

    /**
     * Saves a YAML snippet as a named template.
     *
     * @param name The template name
     * @param yamlSnippet The YAML content to save
     * @throws IOException if the file cannot be written
     */
    public void saveTemplate(String name, String yamlSnippet) throws IOException {
        File dir = new File(System.getProperty("user.home"), TEMPLATES_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File templateFile = new File(dir, name + ".yaml");
        try (FileWriter fw = new FileWriter(templateFile)) {
            fw.write(yamlSnippet);
        }
    }

    /**
     * Loads a named template.
     *
     * @param name The template name
     * @return The YAML content of the template
     * @throws IOException if the file cannot be read
     */
    public String loadTemplate(String name) throws IOException {
        File dir = new File(System.getProperty("user.home"), TEMPLATES_DIR);
        File templateFile = new File(dir, name + ".yaml");

        if (!templateFile.exists()) {
            throw new IOException("Template not found: " + name);
        }

        return mapper.readValue(templateFile, Map.class).toString();
    }

    /**
     * Lists all available templates.
     *
     * @return Array of template names (without .yaml extension)
     */
    public String[] listTemplates() {
        File dir = new File(System.getProperty("user.home"), TEMPLATES_DIR);
        if (!dir.exists()) {
            return new String[0];
        }

        return dir.list((d, n) -> n.endsWith(".yaml") || n.endsWith(".yml"));
    }

    /**
     * Deletes a template.
     *
     * @param name The template name to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteTemplate(String name) {
        File dir = new File(System.getProperty("user.home"), TEMPLATES_DIR);
        File templateFile = new File(dir, name + ".yaml");

        if (templateFile.exists()) {
            return templateFile.delete();
        }
        return false;
    }
}
