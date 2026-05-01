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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Converts YAML pipeline definitions to NiFi API format and vice versa.
 *
 * <p>This class handles the conversion of YAML-defined pipelines into the format
 * expected by the NiFi REST API. It supports processors, connections, funnels,
 * input/output ports, process groups, and remote process groups.</p>
 *
 * <p>Each component is assigned a generated UUID and default values are applied
 * where not specified in the YAML definition.</p>
 *
 * <p>Usage example:
 * <pre>{@code
 * PipelineConverter converter = new PipelineConverter();
 * Map<String, Object> result = converter.convertFromYaml(new File("pipeline.yaml"));
 * }</pre>
 *
 * @author GiWi
 * @version 1.0-SNAPSHOT
 */
public class PipelineConverter {
    private static final Logger log = LoggerFactory.getLogger(PipelineConverter.class);
    private final ObjectMapper mapper;

    public PipelineConverter() {
        this.mapper = new ObjectMapper(new YAMLFactory());
        this.mapper.findAndRegisterModules();
    }

    /**
     * Converts a YAML file to NiFi API format.
     *
     * @param yamlFile The YAML file containing the pipeline definition
     * @return A Map representing the pipeline in NiFi API format
     * @throws IOException if the file cannot be read or parsed
     */
    public Map<String, Object> convertFromYaml(File yamlFile) throws IOException {
        if (yamlFile == null || yamlFile.length() == 0) {
            return convertFromMap(new HashMap<>());
        }
        Map<String, Object> yaml = mapper.readValue(yamlFile, new TypeReference<>() {
        });
        if (yaml == null) {
            yaml = new HashMap<>();
        }
        return convertFromMap(yaml);
    }

    /**
     * Converts a Map (parsed from YAML) to NiFi API format.
     *
     * <p>Transforms YAML pipeline definition into the structure expected by NiFi's
     * REST API. Handles processors, connections, ports, process groups, remote
     * process groups, and funnels.</p>
     *
     * @param yaml The parsed YAML as a Map structure
     * @return A Map representing the pipeline in NiFi API format
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> convertFromMap(Map<String, Object> yaml) {
        Map<String, Object> result = new HashMap<>();
        log.debug("Converting pipeline: {}", yaml.getOrDefault("name", "Untitled"));

        result.put("id", generateUuid());
        result.put("name", getString(yaml, "name", "Untitled Pipeline"));
        result.put("parentGroupId", getString(yaml, "parentGroupId", "root"));

        if (yaml.containsKey("processors")) {
            result.put("processors", convertProcessors((List<Map<String, Object>>) yaml.get("processors")));
            log.debug("Converted {} processors", ((List<?>) yaml.get("processors")).size());
        }

        if (yaml.containsKey("connections")) {
            result.put("connections", convertConnections((List<Map<String, Object>>) yaml.get("connections")));
            log.debug("Converted {} connections", ((List<?>) yaml.get("connections")).size());
        }

        if (yaml.containsKey("inputPorts")) {
            result.put("inputPorts", convertInputPorts((List<Map<String, Object>>) yaml.get("inputPorts")));
            log.debug("Converted {} input ports", ((List<?>) yaml.get("inputPorts")).size());
        }

        if (yaml.containsKey("outputPorts")) {
            result.put("outputPorts", convertOutputPorts((List<Map<String, Object>>) yaml.get("outputPorts")));
            log.debug("Converted {} output ports", ((List<?>) yaml.get("outputPorts")).size());
        }

        if (yaml.containsKey("processGroups")) {
            result.put("processGroups", convertProcessGroups((List<Map<String, Object>>) yaml.get("processGroups")));
            log.debug("Converted {} process groups", ((List<?>) yaml.get("processGroups")).size());
        }

        if (yaml.containsKey("remoteProcessGroups")) {
            result.put("remoteProcessGroups", convertRemoteProcessGroups((List<Map<String, Object>>) yaml.get("remoteProcessGroups")));
            log.debug("Converted {} remote process groups", ((List<?>) yaml.get("remoteProcessGroups")).size());
        }

        if (yaml.containsKey("funnels")) {
            result.put("funnels", convertFunnels((List<Map<String, Object>>) yaml.get("funnels")));
            log.debug("Converted {} funnels", ((List<?>) yaml.get("funnels")).size());
        }

        return result;
    }

    /**
     * Converts a list of processor definitions from YAML format to NiFi API format.
     *
     * <p>Each processor is assigned a UUID, and default values are applied for
     * state (STOPPED) and position (0,0) if not specified.</p>
     *
     * @param processors List of processor maps from YAML
     * @return List of processor entities in NiFi API format
     */
    private List<Map<String, Object>> convertProcessors(List<Map<String, Object>> processors) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (processors == null) return result;

        for (Map<String, Object> proc : processors) {
            Map<String, Object> entity = new HashMap<>();

            Map<String, Object> dto = new HashMap<>();
            dto.put("id", generateUuid());
            dto.put("type", getString(proc, "type"));
            dto.put("name", getString(proc, "name"));
            dto.put("state", getString(proc, "state", "STOPPED"));

            // Scheduling properties
            if (proc.containsKey("schedulingStrategy")) {
                dto.put("schedulingStrategy", getString(proc, "schedulingStrategy"));
            }
            if (proc.containsKey("schedulingPeriod")) {
                dto.put("schedulingPeriod", getString(proc, "schedulingPeriod"));
            }
            if (proc.containsKey("concurrentlySchedulableTaskCount")) {
                dto.put("concurrentlySchedulableTaskCount", proc.get("concurrentlySchedulableTaskCount"));
            }
            if (proc.containsKey("runDurationMillis")) {
                dto.put("runDurationMillis", proc.get("runDurationMillis"));
            }

            // Auto-terminated relationships
            if (proc.containsKey("autoTerminatedRelationships")) {
                dto.put("autoTerminatedRelationships", proc.get("autoTerminatedRelationships"));
            }

            Map<String, Object> pos = new HashMap<>();
            pos.put("x", getDouble(proc, "x", 0.0));
            pos.put("y", getDouble(proc, "y", 0.0));
            dto.put("position", pos);

            if (proc.containsKey("properties")) {
                dto.put("properties", proc.get("properties"));
            }

            dto.put("parentGroupId", getString(proc, "parentGroupId", "root"));

            Map<String, Object> revision = new HashMap<>();
            revision.put("version", 0L);
            dto.put("revision", revision);

            entity.put("processor", dto);
            result.add(entity);
        }
        return result;
    }
    /**
     * Converts connection list from YAML format to NiFi API format.
     * Handles selected relationships, flow file expiration, and back pressure settings.
     *
     * @param connections List of connection maps from YAML
     * @return List of connection entities in NiFi API format
     */
    private List<Map<String, Object>> convertConnections(List<Map<String, Object>> connections) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (connections == null) return result;

        for (Map<String, Object> conn : connections) {
            Map<String, Object> entity = new HashMap<>();

            Map<String, Object> dto = new HashMap<>();
            dto.put("id", generateUuid());
            dto.put("name", getString(conn, "name"));
            dto.put("sourceId", getString(conn, "sourceId"));
            dto.put("sourceType", getString(conn, "sourceType", "PROCESSOR"));
            dto.put("destinationId", getString(conn, "destinationId"));
            dto.put("destinationType", getString(conn, "destinationType", "PROCESSOR"));

            if (conn.containsKey("relationships")) {
                dto.put("selectedRelationships", conn.get("relationships"));
            }

            dto.put("flowFileExpiration", getString(conn, "flowFileExpiration", "0ms"));
            dto.put("backPressureDataSizeThreshold", getString(conn, "backPressureDataSizeThreshold", "1 GB"));
            dto.put("backPressureObjectThreshold", getString(conn, "backPressureObjectThreshold", "10000"));
            dto.put("parentGroupId", getString(conn, "parentGroupId", "root"));

            Map<String, Object> pos = new HashMap<>();
            pos.put("x", getDouble(conn, "x", 0.0));
            pos.put("y", getDouble(conn, "y", 0.0));
            dto.put("position", pos);

            // Add bends support (control points for connection line)
            if (conn.containsKey("bends")) {
                List<Map<String, Object>> bendList = (List<Map<String, Object>>) conn.get("bends");
                List<Map<String, Object>> bends = new ArrayList<>();
                for (Map<String, Object> bendMap : bendList) {
                    Map<String, Object> bend = new HashMap<>();
                    bend.put("x", getDouble(bendMap, "x", 0.0));
                    bend.put("y", getDouble(bendMap, "y", 0.0));
                    bends.add(bend);
                }
                dto.put("bends", bends);
            }

            Map<String, Object> revision = new HashMap<>();
            revision.put("version", 0L);
            dto.put("revision", revision);

            entity.put("connection", dto);
            result.add(entity);
        }
        return result;
    }

    /**
     * Converts input port definitions from YAML format to NiFi API format.
     *
     * @param ports List of input port maps from YAML
     * @return List of input port entities in NiFi API format
     */
    private List<Map<String, Object>> convertInputPorts(List<Map<String, Object>> ports) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (ports == null) return result;

        for (Map<String, Object> port : ports) {
            Map<String, Object> entity = new HashMap<>();

            Map<String, Object> dto = new HashMap<>();
            dto.put("id", generateUuid());
            dto.put("name", getString(port, "name"));
            dto.put("state", getString(port, "state", "STOPPED"));
            dto.put("type", "INPUT_PORT");
            dto.put("parentGroupId", getString(port, "parentGroupId", "root"));

            Map<String, Object> pos = new HashMap<>();
            pos.put("x", getDouble(port, "x", 0.0));
            pos.put("y", getDouble(port, "y", 0.0));
            dto.put("position", pos);

            Map<String, Object> revision = new HashMap<>();
            revision.put("version", 0L);
            dto.put("revision", revision);

            entity.put("port", dto);
            result.add(entity);
        }
        return result;
    }

    /**
     * Converts output port definitions from YAML format to NiFi API format.
     *
     * @param ports List of output port maps from YAML
     * @return List of output port entities in NiFi API format
     */
    private List<Map<String, Object>> convertOutputPorts(List<Map<String, Object>> ports) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (ports == null) return result;

        for (Map<String, Object> port : ports) {
            Map<String, Object> entity = new HashMap<>();

            Map<String, Object> dto = new HashMap<>();
            dto.put("id", generateUuid());
            dto.put("name", getString(port, "name"));
            dto.put("state", getString(port, "state", "STOPPED"));
            dto.put("type", "OUTPUT_PORT");
            dto.put("parentGroupId", getString(port, "parentGroupId", "root"));

            Map<String, Object> pos = new HashMap<>();
            pos.put("x", getDouble(port, "x", 0.0));
            pos.put("y", getDouble(port, "y", 0.0));
            dto.put("position", pos);

            Map<String, Object> revision = new HashMap<>();
            revision.put("version", 0L);
            dto.put("revision", revision);

            entity.put("port", dto);
            result.add(entity);
        }
        return result;
    }

    /**
     * Converts process group definitions from YAML format to NiFi API format.
     *
     * @param groups List of process group maps from YAML
     * @return List of process group entities in NiFi API format
     */
    private List<Map<String, Object>> convertProcessGroups(List<Map<String, Object>> groups) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (groups == null) return result;

        for (Map<String, Object> group : groups) {
            Map<String, Object> entity = new HashMap<>();

            Map<String, Object> dto = new HashMap<>();
            dto.put("id", generateUuid());
            dto.put("name", getString(group, "name"));
            dto.put("parentGroupId", getString(group, "parentGroupId", "root"));

            Map<String, Object> pos = new HashMap<>();
            pos.put("x", getDouble(group, "x", 0.0));
            pos.put("y", getDouble(group, "y", 0.0));
            dto.put("position", pos);

            Map<String, Object> revision = new HashMap<>();
            revision.put("version", 0L);
            dto.put("revision", revision);

            entity.put("component", dto);
            result.add(entity);
        }
        return result;
    }

    /**
     * Converts remote process group definitions from YAML format to NiFi API format.
     *
     * @param groups List of remote process group maps from YAML
     * @return List of remote process group entities in NiFi API format
     */
    private List<Map<String, Object>> convertRemoteProcessGroups(List<Map<String, Object>> groups) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (groups == null) return result;

        for (Map<String, Object> group : groups) {
            Map<String, Object> entity = new HashMap<>();

            Map<String, Object> dto = new HashMap<>();
            dto.put("id", generateUuid());
            dto.put("name", getString(group, "name"));
            dto.put("targetUris", getString(group, "targetUris"));
            dto.put("communicationsTimeout", getString(group, "communicationsTimeout", "30 secs"));
            dto.put("yieldDuration", getString(group, "yieldDuration", "10 secs"));

            Map<String, Object> revision = new HashMap<>();
            revision.put("version", 0L);
            dto.put("revision", revision);

            entity.put("component", dto);
            result.add(entity);
        }
        return result;
    }

    /**
     * Converts funnel definitions from YAML format to NiFi API format.
     *
     * @param funnels List of funnel maps from YAML
     * @return List of funnel entities in NiFi API format
     */
    private List<Map<String, Object>> convertFunnels(List<Map<String, Object>> funnels) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (funnels == null) return result;

        for (Map<String, Object> funnel : funnels) {
            Map<String, Object> entity = new HashMap<>();

            Map<String, Object> dto = new HashMap<>();
            dto.put("id", generateUuid());
            dto.put("parentGroupId", getString(funnel, "parentGroupId", "root"));

            Map<String, Object> pos = new HashMap<>();
            pos.put("x", getDouble(funnel, "x", 0.0));
            pos.put("y", getDouble(funnel, "y", 0.0));
            dto.put("position", pos);

            Map<String, Object> revision = new HashMap<>();
            revision.put("version", 0L);
            dto.put("revision", revision);

            entity.put("component", dto);
            result.add(entity);
        }
        return result;
    }

    /**
     * Converts a pipeline map to a YAML string.
     *
     * @param pipeline The pipeline map in NiFi API format
     * @return YAML string representation of the pipeline
     * @throws IOException if the conversion fails
     */
    public String convertToYaml(Map<String, Object> pipeline) throws IOException {
        return mapper.writeValueAsString(pipeline);
    }

    /**
     * Converts a NiFi JSON export file to YAML format.
     *
     * <p>NiFi exports (from UI or /process-groups/{id}/download) contain a flow object
     * with processors, connections, etc. This method extracts and converts them to
     * the simplified YAML format used by this project.</p>
     *
     * @param jsonFile The NiFi JSON export file
     * @return YAML string representation of the pipeline
     * @throws IOException if the file cannot be read or parsed
     */
    @SuppressWarnings("unchecked")
    public String convertNiFiJsonToYaml(File jsonFile) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        Map<String, Object> nifiExport = jsonMapper.readValue(jsonFile, new TypeReference<>() {});

        Map<String, Object> yamlPipeline = new HashMap<>();

        // Extract flow from NiFi export (handle both direct flow and nested structure)
        Map<String, Object> flow = null;
        if (nifiExport.containsKey("flow")) {
            flow = (Map<String, Object>) nifiExport.get("flow");
        } else {
            flow = nifiExport;
        }

        // Get process group name from breadcrumb or use default
        String pipelineName = "Imported Pipeline";
        if (nifiExport.containsKey("breadcrumb")) {
            Map<String, Object> breadcrumb = (Map<String, Object>) nifiExport.get("breadcrumb");
            if (breadcrumb.containsKey("permissions") && breadcrumb.containsKey("component")) {
                Map<String, Object> component = (Map<String, Object>) breadcrumb.get("component");
                if (component.containsKey("name")) {
                    pipelineName = component.get("name").toString();
                }
            }
        }
        yamlPipeline.put("name", pipelineName);
        yamlPipeline.put("parentGroupId", "root");

        // Convert processors
        if (flow.containsKey("processors")) {
            List<Map<String, Object>> processors = (List<Map<String, Object>>) flow.get("processors");
            yamlPipeline.put("processors", convertNiFiProcessors(processors));
        }

        // Convert connections
        if (flow.containsKey("connections")) {
            List<Map<String, Object>> connections = (List<Map<String, Object>>) flow.get("connections");
            yamlPipeline.put("connections", convertNiFiConnections(connections));
        }

        // Convert funnels
        if (flow.containsKey("funnels")) {
            List<Map<String, Object>> funnels = (List<Map<String, Object>>) flow.get("funnels");
            yamlPipeline.put("funnels", convertNiFiFunnels(funnels));
        }

        // Convert input ports
        if (flow.containsKey("inputPorts")) {
            List<Map<String, Object>> ports = (List<Map<String, Object>>) flow.get("inputPorts");
            yamlPipeline.put("inputPorts", convertNiFiPorts(ports, "input"));
        }

        // Convert output ports
        if (flow.containsKey("outputPorts")) {
            List<Map<String, Object>> ports = (List<Map<String, Object>>) flow.get("outputPorts");
            yamlPipeline.put("outputPorts", convertNiFiPorts(ports, "output"));
        }

        // Convert process groups (nested)
        if (flow.containsKey("processGroups")) {
            List<Map<String, Object>> groups = (List<Map<String, Object>>) flow.get("processGroups");
            yamlPipeline.put("processGroups", convertNiFiProcessGroups(groups));
        }

        // Convert remote process groups
        if (flow.containsKey("remoteProcessGroups")) {
            List<Map<String, Object>> groups = (List<Map<String, Object>>) flow.get("remoteProcessGroups");
            yamlPipeline.put("remoteProcessGroups", convertNiFiRemoteProcessGroups(groups));
        }

        return mapper.writeValueAsString(yamlPipeline);
    }

    /**
     * Converts NiFi processor entities to YAML format.
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> convertNiFiProcessors(List<Map<String, Object>> nifiProcessors) {
        return nifiProcessors.stream().map(processorEntity -> {
            Map<String, Object> dto = (Map<String, Object>) processorEntity.get("component");
            Map<String, Object> yaml = new HashMap<>();
            yaml.put("name", dto.get("name"));
            yaml.put("type", dto.get("type"));

            if (dto.containsKey("position")) {
                Map<String, Object> pos = (Map<String, Object>) dto.get("position");
                yaml.put("x", pos.get("x"));
                yaml.put("y", pos.get("y"));
            }

            if (dto.containsKey("schedulingStrategy")) {
                yaml.put("schedulingStrategy", dto.get("schedulingStrategy"));
            }
            if (dto.containsKey("schedulingPeriod")) {
                yaml.put("schedulingPeriod", dto.get("schedulingPeriod"));
            }
            if (dto.containsKey("concurrentlySchedulableTaskCount")) {
                yaml.put("concurrentlySchedulableTaskCount", dto.get("concurrentlySchedulableTaskCount"));
            }
            if (dto.containsKey("runDurationMillis")) {
                yaml.put("runDurationMillis", dto.get("runDurationMillis"));
            }
            if (dto.containsKey("state")) {
                yaml.put("state", dto.get("state"));
            }
            if (dto.containsKey("autoTerminatedRelationships") && dto.get("autoTerminatedRelationships") instanceof List) {
                yaml.put("autoTerminatedRelationships", dto.get("autoTerminatedRelationships"));
            }
            if (dto.containsKey("properties") && dto.get("properties") instanceof Map) {
                yaml.put("properties", dto.get("properties"));
            }

            return yaml;
        }).collect(Collectors.toList());
    }

    /**
     * Converts NiFi connection entities to YAML format.
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> convertNiFiConnections(List<Map<String, Object>> nifiConnections) {
        return nifiConnections.stream().map(connEntity -> {
            Map<String, Object> dto = (Map<String, Object>) connEntity.get("component");
            Map<String, Object> yaml = new HashMap<>();

            yaml.put("name", dto.getOrDefault("name", ""));
            yaml.put("sourceId", "${" + getComponentName(dto, "source") + "}");
            yaml.put("destinationId", "${" + getComponentName(dto, "destination") + "}");

            if (dto.containsKey("selectedRelationships")) {
                yaml.put("relationships", dto.get("selectedRelationships"));
            }

            if (dto.containsKey("flowFileExpiration")) {
                yaml.put("flowFileExpiration", dto.get("flowFileExpiration"));
            }
            if (dto.containsKey("backPressureDataSizeThreshold")) {
                yaml.put("backPressureDataSizeThreshold", dto.get("backPressureDataSizeThreshold"));
            }
            if (dto.containsKey("backPressureObjectThreshold")) {
                yaml.put("backPressureObjectThreshold", dto.get("backPressureObjectThreshold"));
            }

            if (dto.containsKey("bends")) {
                yaml.put("bends", dto.get("bends"));
            }
            if (dto.containsKey("labelIndex")) {
                Map<String, Object> pos = new HashMap<>();
                pos.put("x", dto.getOrDefault("labelIndex", 0));
                pos.put("y", 0);
                yaml.put("position", pos);
            }

            return yaml;
        }).collect(Collectors.toList());
    }

    /**
     * Gets the name of a component reference (source or destination).
     */
    @SuppressWarnings("unchecked")
    private String getComponentName(Map<String, Object> dto, String key) {
        if (dto.containsKey(key)) {
            Map<String, Object> component = (Map<String, Object>) dto.get(key);
            if (component.containsKey("name")) {
                return component.get("name").toString();
            }
            if (component.containsKey("id")) {
                return component.get("id").toString();
            }
        }
        return "unknown";
    }

    /**
     * Converts NiFi funnel entities to YAML format.
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> convertNiFiFunnels(List<Map<String, Object>> nifiFunnels) {
        return nifiFunnels.stream().map(funnelEntity -> {
            Map<String, Object> dto = (Map<String, Object>) funnelEntity.get("component");
            Map<String, Object> yaml = new HashMap<>();
            yaml.put("name", dto.getOrDefault("name", "Funnel"));
            if (dto.containsKey("position")) {
                Map<String, Object> pos = (Map<String, Object>) dto.get("position");
                yaml.put("x", pos.get("x"));
                yaml.put("y", pos.get("y"));
            }
            return yaml;
        }).collect(Collectors.toList());
    }

    /**
     * Converts NiFi port entities to YAML format.
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> convertNiFiPorts(List<Map<String, Object>> nifiPorts, String type) {
        return nifiPorts.stream().map(portEntity -> {
            Map<String, Object> dto = (Map<String, Object>) portEntity.get("component");
            Map<String, Object> yaml = new HashMap<>();
            yaml.put("name", dto.get("name"));
            if (dto.containsKey("position")) {
                Map<String, Object> pos = (Map<String, Object>) dto.get("position");
                yaml.put("x", pos.get("x"));
                yaml.put("y", pos.get("y"));
            }
            return yaml;
        }).collect(Collectors.toList());
    }

    /**
     * Converts NiFi process group entities to YAML format.
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> convertNiFiProcessGroups(List<Map<String, Object>> nifiGroups) {
        return nifiGroups.stream().map(groupEntity -> {
            Map<String, Object> dto = (Map<String, Object>) groupEntity.get("component");
            Map<String, Object> yaml = new HashMap<>();
            yaml.put("name", dto.get("name"));
            if (dto.containsKey("position")) {
                Map<String, Object> pos = (Map<String, Object>) dto.get("position");
                yaml.put("x", pos.get("x"));
                yaml.put("y", pos.get("y"));
            }
            return yaml;
        }).collect(Collectors.toList());
    }

    /**
     * Converts NiFi remote process group entities to YAML format.
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> convertNiFiRemoteProcessGroups(List<Map<String, Object>> nifiGroups) {
        return nifiGroups.stream().map(groupEntity -> {
            Map<String, Object> dto = (Map<String, Object>) groupEntity.get("component");
            Map<String, Object> yaml = new HashMap<>();
            yaml.put("name", dto.get("name"));
            yaml.put("targetUris", dto.get("targetUris"));
            if (dto.containsKey("position")) {
                Map<String, Object> pos = (Map<String, Object>) dto.get("position");
                yaml.put("x", pos.get("x"));
                yaml.put("y", pos.get("y"));
            }
            return yaml;
        }).collect(Collectors.toList());
    }

    /**
     * Returns the pipeline map as-is (identity function for API compatibility).
     *
     * @param pipeline The pipeline map
     * @return The same pipeline map
     */
    public Map<String, Object> convertToMap(Map<String, Object> pipeline) {
        return pipeline;
    }

    /**
     * Generates a random UUID string.
     *
     * @return A UUID string for use as component ID
     */
    private String generateUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Gets a string value from a map, returning null if not found.
     *
     * @param map The map to retrieve from
     * @param key The key to look up
     * @return The string value, or null if not found
     */
    private String getString(Map<String, Object> map, String key) {
        return getString(map, key, null);
    }

    /**
     * Gets a string value from a map with a default value.
     *
     * @param map          The map to retrieve from
     * @param key          The key to look up
     * @param defaultValue The default value if key is not found
     * @return The string value, or defaultValue if not found
     */
    private String getString(Map<String, Object> map, String key, String defaultValue) {
        Object value = map.get(key);
        return value != null ? value.toString() : defaultValue;
    }

    /**
     * Gets a double value from a map with a default value.
     *
     * @param map          The map to retrieve from
     * @param key          The key to look up
     * @param defaultValue The default value if key is not found or not a number
     * @return The double value, or defaultValue if not found/invalid
     */
    private Double getDouble(Map<String, Object> map, String key, Double defaultValue) {
        Object value = map.get(key);
        if (value == null) return defaultValue;
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return defaultValue;
    }
}