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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Validates YAML pipeline definitions before deployment.
 *
 * <p>Performs validation checks on pipeline YAML files:
 * <ul>
 *   <li>YAML syntax validation</li>
 *   <li>Required fields presence (name, processor names, types)</li>
 *   <li>Reference integrity (connection source/destination names exist)</li>
 *   <li>Valid processor types format</li>
 *   <li>No duplicate component names</li>
 * </ul>
 *
 * <p>Usage example:
 * <pre>{@code
 * PipelineValidator validator = new PipelineValidator();
 * List<String> errors = validator.validate(new File("pipeline.yaml"));
 * if (!errors.isEmpty()) {
 *     errors.forEach(System.err::println);
 * }
 * }</pre>
 *
 * @author GiWi
 * @version 1.0-SNAPSHOT
 */
public class PipelineValidator {
    private final ObjectMapper mapper;

    public PipelineValidator() {
        this.mapper = new ObjectMapper(new YAMLFactory());
        this.mapper.findAndRegisterModules();
    }

    /**
     * Validates a YAML pipeline file.
     *
     * @param yamlFile The YAML file to validate
     * @return List of validation errors (empty if valid)
     */
    public List<String> validate(File yamlFile) {
        List<String> errors = new ArrayList<>();

        if (yamlFile == null || !yamlFile.exists() || !yamlFile.canRead()) {
            errors.add("File does not exist or cannot be read: " + (yamlFile != null ? yamlFile.getPath() : "null"));
            return errors;
        }

        try {
            Map<String, Object> yaml = mapper.readValue(yamlFile, new TypeReference<>() {});
            if (yaml == null) {
                errors.add("YAML file is empty or invalid");
                return errors;
            }
            errors.addAll(validatePipeline(yaml));
        } catch (IOException e) {
            errors.add("YAML syntax error: " + e.getMessage());
        }

        return errors;
    }

    /**
     * Validates a pipeline map (already parsed).
     *
     * @param pipeline The pipeline map to validate
     * @return List of validation errors (empty if valid)
     */
    @SuppressWarnings("unchecked")
    public List<String> validatePipeline(Map<String, Object> pipeline) {
        List<String> errors = new ArrayList<>();

        // Check pipeline name
        if (!pipeline.containsKey("name") || pipeline.get("name") == null) {
            errors.add("Pipeline must have a 'name' field");
        }

        // Collect all component names for reference checking
        Set<String> componentNames = new HashSet<>();
        Map<String, String> nameToType = new HashMap<>();

        // Validate processors
        if (pipeline.containsKey("processors")) {
            errors.addAll(validateProcessors((List<Map<String, Object>>) pipeline.get("processors"), componentNames, nameToType));
        }

        // Validate input ports
        if (pipeline.containsKey("inputPorts")) {
            errors.addAll(validatePorts((List<Map<String, Object>>) pipeline.get("inputPorts"), componentNames, "inputPorts"));
        }

        // Validate output ports
        if (pipeline.containsKey("outputPorts")) {
            errors.addAll(validatePorts((List<Map<String, Object>>) pipeline.get("outputPorts"), componentNames, "outputPorts"));
        }

        // Validate connections (need component names)
        if (pipeline.containsKey("connections")) {
            errors.addAll(validateConnections((List<Map<String, Object>>) pipeline.get("connections"), componentNames));
        }

        // Validate process groups
        if (pipeline.containsKey("processGroups")) {
            errors.addAll(validateProcessGroups((List<Map<String, Object>>) pipeline.get("processGroups"), componentNames));
        }

        return errors;
    }

    @SuppressWarnings("unchecked")
    private List<String> validateProcessors(List<Map<String, Object>> processors, Set<String> componentNames, Map<String, String> nameToType) {
        List<String> errors = new ArrayList<>();
        if (processors == null) return errors;

        for (int i = 0; i < processors.size(); i++) {
            Map<String, Object> proc = processors.get(i);
            String prefix = "Processor[" + i + "]";

            // Check name
            if (!proc.containsKey("name") || proc.get("name") == null) {
                errors.add(prefix + ": missing 'name' field");
            } else {
                String name = proc.get("name").toString();
                if (!componentNames.add(name)) {
                    errors.add(prefix + ": duplicate component name '" + name + "'");
                }
                nameToType.put(name, proc.containsKey("type") ? proc.get("type").toString() : "unknown");
            }

            // Check type
            if (!proc.containsKey("type") || proc.get("type") == null) {
                errors.add(prefix + ": missing 'type' field");
            } else {
                String type = proc.get("type").toString();
                if (!type.contains(".")) {
                    errors.add(prefix + ": invalid processor type format '" + type + "' (should be like 'org.apache.nifi.processors.standard.GenerateFlowFile')");
                }
            }
        }

        return errors;
    }

    @SuppressWarnings("unchecked")
    private List<String> validatePorts(List<Map<String, Object>> ports, Set<String> componentNames, String portType) {
        List<String> errors = new ArrayList<>();
        if (ports == null) return errors;

        for (int i = 0; i < ports.size(); i++) {
            Map<String, Object> port = ports.get(i);
            String prefix = portType + "[" + i + "]";

            if (!port.containsKey("name") || port.get("name") == null) {
                errors.add(prefix + ": missing 'name' field");
            } else {
                String name = port.get("name").toString();
                if (!componentNames.add(name)) {
                    errors.add(prefix + ": duplicate component name '" + name + "'");
                }
            }
        }

        return errors;
    }

    @SuppressWarnings("unchecked")
    private List<String> validateConnections(List<Map<String, Object>> connections, Set<String> componentNames) {
        List<String> errors = new ArrayList<>();
        if (connections == null) return errors;

        for (int i = 0; i < connections.size(); i++) {
            Map<String, Object> conn = connections.get(i);
            // Handle both flat and wrapped format
            Map<String, Object> connMap = conn.containsKey("connection") ?
                (Map<String, Object>) conn.get("connection") : conn;
            String prefix = "Connection[" + i + "]";

            // Check source
            String sourceId = getRefName((String) connMap.get("sourceId"));
            if (sourceId == null || sourceId.isEmpty()) {
                errors.add(prefix + ": missing 'sourceId' field");
            } else if (!componentNames.contains(sourceId)) {
                errors.add(prefix + ": sourceId '" + sourceId + "' not found in pipeline components");
            }

            // Check destination
            String destId = getRefName((String) connMap.get("destinationId"));
            if (destId == null || destId.isEmpty()) {
                errors.add(prefix + ": missing 'destinationId' field");
            } else if (!componentNames.contains(destId)) {
                errors.add(prefix + ": destinationId '" + destId + "' not found in pipeline components");
            }

            // Check relationships
            List<String> relationships = connMap.containsKey("relationships") ?
                (List<String>) connMap.get("relationships") :
                (connMap.containsKey("selectedRelationships") ?
                    (List<String>) connMap.get("selectedRelationships") : null);
            if (relationships == null || relationships.isEmpty()) {
                errors.add(prefix + ": missing 'relationships' field");
            }
        }

        return errors;
    }

    @SuppressWarnings("unchecked")
    private List<String> validateProcessGroups(List<Map<String, Object>> groups, Set<String> componentNames) {
        List<String> errors = new ArrayList<>();
        if (groups == null) return errors;

        for (int i = 0; i < groups.size(); i++) {
            Map<String, Object> group = groups.get(i);
            String prefix = "ProcessGroup[" + i + "]";

            if (!group.containsKey("name") || group.get("name") == null) {
                errors.add(prefix + ": missing 'name' field");
            } else {
                String name = group.get("name").toString();
                if (!componentNames.add(name)) {
                    errors.add(prefix + ": duplicate component name '" + name + "'");
                }
            }
        }

        return errors;
    }

    /**
     * Extracts the name from a reference string like ${ProcessorName}.
     */
    private String getRefName(String ref) {
        if (ref == null) return null;
        if (ref.startsWith("${") && ref.endsWith("}")) {
            return ref.substring(2, ref.length() - 1);
        }
        return ref;
    }
}
