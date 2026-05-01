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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Exports a deployed NiFi pipeline back to YAML format.
 *
 * <p>Connects to a running NiFi instance and exports an existing process group
 * to the YAML format used by this project.</p>
 *
 * <p>Usage example:
 * <pre>{@code
 * PipelineExporter exporter = new PipelineExporter(url, username, password);
 * String yaml = exporter.exportProcessGroup("process-group-id");
 * }</pre>
 *
 * @author GiWi
 * @version 1.0-SNAPSHOT
 */
public class PipelineExporter {
    private final PipelineTester tester;
    private final ObjectMapper mapper;

    /**
     * Creates a PipelineExporter with the specified NiFi credentials.
     *
     * @param nifiUrl  The NiFi API base URL
     * @param username The username for authentication
     * @param password The password for authentication
     * @throws Exception if login fails
     */
    public PipelineExporter(String nifiUrl, String username, String password) throws Exception {
        this.tester = new PipelineTester(nifiUrl, username, password);
        this.mapper = new ObjectMapper();
        this.mapper.findAndRegisterModules();
    }

    /**
     * Exports a process group to YAML format.
     *
     * @param processGroupId The process group ID to export
     * @return YAML string representation of the pipeline
     * @throws Exception if export fails
     */
    @SuppressWarnings("unchecked")
    public String exportProcessGroup(String processGroupId) throws Exception {
        // Get process group details
        org.giwi.nifi.tester.api.ProcessGroupsApi pgApi =
            new org.giwi.nifi.tester.api.ProcessGroupsApi(tester.getClient());

        var pgEntity = pgApi.getProcessGroup(processGroupId);
        if (pgEntity == null || pgEntity.getComponent() == null) {
            throw new RuntimeException("Process group not found: " + processGroupId);
        }

        Map<String, Object> yamlPipeline = new java.util.HashMap<>();
        yamlPipeline.put("name", pgEntity.getComponent().getName());
        yamlPipeline.put("parentGroupId",
            pgEntity.getComponent().getParentGroupId() != null ?
                pgEntity.getComponent().getParentGroupId() : "root");

        // Use exportProcessGroup to get the flow definition as JSON string
        String flowJson = pgApi.exportProcessGroup(processGroupId, null);
        if (flowJson != null && !flowJson.isEmpty()) {
            // Parse and convert to YAML using PipelineConverter
            PipelineConverter converter = new PipelineConverter();
            try {
                // Write JSON to temp file and convert
                java.io.File tempFile = java.io.File.createTempFile("nifi-export-", ".json");
                try (java.io.FileWriter fw = new java.io.FileWriter(tempFile)) {
                    fw.write(flowJson);
                }
                String yaml = converter.convertNiFiJsonToYaml(tempFile);
                tempFile.delete();
                return yaml;
            } catch (Exception e) {
                // Fall back to basic export
            }
        }

        return new PipelineConverter().convertToYaml(yamlPipeline);
    }

    /**
     * Closes the exporter and releases resources.
     */
    public void close() throws Exception {
        tester.close();
    }
}
