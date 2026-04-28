package org.giwi.nifi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PipelineConverter {
    private final ObjectMapper mapper;

    public PipelineConverter() {
        this.mapper = new ObjectMapper(new YAMLFactory());
        this.mapper.findAndRegisterModules();
    }

    public Map<String, Object> convertFromYaml(File yamlFile) throws IOException {
        Map<String, Object> yaml = mapper.readValue(yamlFile, new TypeReference<Map<String, Object>>() {});
        return convertFromMap(yaml);
    }

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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
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

    public String convertToYaml(Map<String, Object> pipeline) throws IOException {
        return mapper.writeValueAsString(pipeline);
    }

    public Map<String, Object> convertToMap(Map<String, Object> pipeline) {
        return pipeline;
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }

    private String getString(Map<String, Object> map, String key) {
        return getString(map, key, null);
    }

    private String getString(Map<String, Object> map, String key, String defaultValue) {
        Object value = map.get(key);
        return value != null ? value.toString() : defaultValue;
    }

    private Double getDouble(Map<String, Object> map, String key, Double defaultValue) {
        Object value = map.get(key);
        if (value == null) return defaultValue;
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return defaultValue;
    }
}