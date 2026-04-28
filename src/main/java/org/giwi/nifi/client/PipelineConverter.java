package org.giwi.nifi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
 * @author GiWi
 * @version 1.0-SNAPSHOT
 */
public class PipelineConverter {
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
        Map<String, Object> yaml = mapper.readValue(yamlFile, new TypeReference<Map<String, Object>>() {
        });
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

        result.put("id", generateUuid());
        result.put("name", getString(yaml, "name", "Untitled Pipeline"));
        result.put("parentGroupId", getString(yaml, "parentGroupId", "root"));

        if (yaml.containsKey("processors")) {
            result.put("processors", convertProcessors((List<Map<String, Object>>) yaml.get("processors")));
        }

        if (yaml.containsKey("connections")) {
            result.put("connections", convertConnections((List<Map<String, Object>>) yaml.get("connections")));
        }

        if (yaml.containsKey("inputPorts")) {
            result.put("inputPorts", convertInputPorts((List<Map<String, Object>>) yaml.get("inputPorts")));
        }

        if (yaml.containsKey("outputPorts")) {
            result.put("outputPorts", convertOutputPorts((List<Map<String, Object>>) yaml.get("outputPorts")));
        }

        if (yaml.containsKey("processGroups")) {
            result.put("processGroups", convertProcessGroups((List<Map<String, Object>>) yaml.get("processGroups")));
        }

        if (yaml.containsKey("remoteProcessGroups")) {
            result.put("remoteProcessGroups", convertRemoteProcessGroups((List<Map<String, Object>>) yaml.get("remoteProcessGroups")));
        }

        if (yaml.containsKey("funnels")) {
            result.put("funnels", convertFunnels((List<Map<String, Object>>) yaml.get("funnels")));
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