package org.giwi.nifi.client;

import org.giwi.nifi.client.api.*;
import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PipelineTester {
    private static final Logger log = LoggerFactory.getLogger(PipelineTester.class);
    
    private final ApiClient client;
    private final PipelineConverter converter;
    private String accessToken;

    /**
     * Creates a PipelineTester with the specified NiFi URL.
     *
     * @param nifiUrl The NiFi API base URL
     */
    public PipelineTester(String nifiUrl) {
        this.client = new ApiClient();
        this.client.setBasePath(nifiUrl);
        this.converter = new PipelineConverter();
    }

    /**
     * Creates a PipelineTester using connection details from {@link NiFiConnection} annotation.
     *
     * @param element The annotated element (class or method) to read annotation from
     * @throws Exception if login fails
     */
    public PipelineTester(AnnotatedElement element) throws Exception {
        String url = "https://localhost:8443/nifi-api";
        String user = "admin";
        String password = "admin";

        NiFiConnection annotation = element.getAnnotation(NiFiConnection.class);

        if (annotation == null && element instanceof Method) {
            annotation = ((Method) element).getDeclaringClass().getAnnotation(NiFiConnection.class);
        }

        if (annotation != null) {
            url = annotation.url();
            user = annotation.user();
            password = annotation.password();
        }

        this.client = new ApiClient();
        this.client.setBasePath(url);
        this.converter = new PipelineConverter();
        login(user, password);
    }

    /**
     * Creates a PipelineTester with the specified NiFi URL and credentials.
     *
     * @param nifiUrl  The NiFi API base URL
     * @param username The username for authentication
     * @param password The password for authentication
     * @throws Exception if login fails
     */
    public PipelineTester(String nifiUrl, String username, String password) throws Exception {
        this(nifiUrl);
        login(username, password);
    }

    /**
     * Logs in to NiFi with the specified credentials.
     *
     * @param username The username
     * @param password The password
     */
    public void login(String username, String password) {
        AccessApi accessApi = new AccessApi(client);
        String token = accessApi.createAccessToken(password, username);
        this.accessToken = token;
        this.client.addDefaultHeader("Authorization", "Bearer " + token);
        log.info("Logged in to NiFi as user: {}", username);
    }

    /**
     * Deploys a pipeline from a YAML file to the root process group.
     *
     * @param yamlFile The YAML file containing the pipeline definition
     * @return A PipelineTesterResult with deployment status
     * @throws IOException if the file cannot be read
     */
    public PipelineTesterResult deployPipeline(File yamlFile) throws IOException {
        return deployPipeline(yamlFile, "root");
    }

    public PipelineTesterResult deployPipeline(File yamlFile, String parentGroupId) throws IOException {
        Map<String, Object> pipelineData = converter.convertFromYaml(yamlFile);
        if (parentGroupId != null) {
            pipelineData.put("parentGroupId", parentGroupId);
        }
        return deployPipeline(pipelineData, (String) pipelineData.getOrDefault("parentGroupId", "root"));
    }

    @SuppressWarnings("unchecked")
    public PipelineTesterResult deployPipeline(Map<String, Object> pipelineData, String parentGroupId) {
        PipelineTesterResult result = new PipelineTesterResult();
        Map<String, String> processorIdMap = new HashMap<>();

        try {
            ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
            String rootGroupId = parentGroupId != null ? parentGroupId : "root";

            // Create a new process group for this pipeline
            ProcessGroupEntity pgEntity = new ProcessGroupEntity();
            org.giwi.nifi.client.model.ProcessGroupDTO pgDto = new org.giwi.nifi.client.model.ProcessGroupDTO();
            pgDto.setName((String) pipelineData.getOrDefault("name", "Untitled Pipeline"));
            pgDto.setParentGroupId(rootGroupId);

            RevisionDTO revision = new RevisionDTO();
            revision.setVersion(0L);
            pgEntity.setRevision(revision);
            pgEntity.setComponent(pgDto);

            ProcessGroupEntity createdPg = pgApi.createProcessGroup(rootGroupId, pgEntity, null);
            assert createdPg.getComponent() != null;
            String newPgId = createdPg.getComponent().getId();
            result.setProcessGroupId(newPgId);
            log.info("Created process group: {} ({})", pipelineData.getOrDefault("name", "Untitled"), newPgId);

            // Create processors
            if (pipelineData.containsKey("processors")) {
                List<Map<String, Object>> processors = (List<Map<String, Object>>) pipelineData.get("processors");
                for (Map<String, Object> procWrapper : processors) {
                    ProcessorEntity procEntity = createProcessorEntity(procWrapper, newPgId);
                    ProcessorEntity created = pgApi.createProcessor(newPgId, procEntity);
                    assert created.getComponent() != null;
                    String procId = created.getComponent().getId();
                    Map<String, Object> proc = procWrapper;
                    if (procWrapper.containsKey("processor")) {
                        proc = (Map<String, Object>) procWrapper.get("processor");
                    }
                    processorIdMap.put((String) proc.get("name"), procId);
                }
            }

            // Create funnels
            if (pipelineData.containsKey("funnels")) {
                List<Map<String, Object>> funnels = (List<Map<String, Object>>) pipelineData.get("funnels");
                for (Map<String, Object> funnel : funnels) {
                    FunnelEntity funnelEntity = new FunnelEntity();
                    org.giwi.nifi.client.model.FunnelDTO funnelDto = new org.giwi.nifi.client.model.FunnelDTO();
                    funnelDto.setParentGroupId(newPgId);
                    PositionDTO pos = new PositionDTO();
                    pos.setX(((Number) funnel.getOrDefault("x", 0)).doubleValue());
                    pos.setY(((Number) funnel.getOrDefault("y", 0)).doubleValue());
                    funnelDto.setPosition(pos);
                    funnelEntity.setComponent(funnelDto);
                    RevisionDTO funnelRev = new RevisionDTO();
                    funnelRev.setVersion(0L);
                    funnelEntity.setRevision(funnelRev);
                    pgApi.createFunnel(newPgId, funnelEntity);
                }
            }

            // Create connections
            if (pipelineData.containsKey("connections")) {
                List<Map<String, Object>> connections = (List<Map<String, Object>>) pipelineData.get("connections");
                for (Map<String, Object> connWrapper : connections) {
                    // Handle both wrapper format (from converter) and flat format
                    Map<String, Object> conn = connWrapper;
                    if (connWrapper.containsKey("connection")) {
                        conn = (Map<String, Object>) connWrapper.get("connection");
                    }
                    ConnectionEntity connEntity = createConnectionEntity(conn, newPgId, processorIdMap);
                    if (connEntity != null) {
                        pgApi.createConnection(newPgId, connEntity);
                        log.debug("Created connection: {}", conn.getOrDefault("name", "unnamed"));
                    }
                }
            }

            // Auto-terminate unconnected relationships
            autoTerminateUnconnectedRelationships(pgApi, newPgId, processorIdMap, pipelineData);

            result.setSuccess(true);
            log.info("Pipeline deployed successfully: {} ({})", pipelineData.getOrDefault("name", "Untitled"), newPgId);
            result.setMessage("Pipeline deployed successfully: " + newPgId);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Deployment failed: " + e.getMessage());
            log.error("Pipeline deployment failed: {}", e.getMessage(), e);
        }

        return result;
    }


    public ApiClient getClient() {
        return client;
    }

    @SuppressWarnings("unchecked")
    private void autoTerminateUnconnectedRelationships(ProcessGroupsApi pgApi, String pgId, Map<String, String> processorIdMap, Map<String, Object> pipelineData) {
        try {
            ProcessorsApi procApi = new ProcessorsApi(client);

            // Collect all connected source relationships
            Map<String, java.util.Set<String>> connectedRelationships = new HashMap<>();
            if (pipelineData.containsKey("connections")) {
                List<Map<String, Object>> connections = (List<Map<String, Object>>) pipelineData.get("connections");
                for (Map<String, Object> connWrapper : connections) {
                    Map<String, Object> conn = connWrapper;
                    if (connWrapper.containsKey("connection")) {
                        conn = (Map<String, Object>) connWrapper.get("connection");
                    }
                    String sourceId = extractIdFromRef((String) conn.get("sourceId"));
                    List<String> rels = (List<String>) conn.getOrDefault("selectedRelationships", new ArrayList<>());
                    connectedRelationships.computeIfAbsent(sourceId, k -> new java.util.HashSet<>()).addAll(rels);
                }
            }

            // For each processor, check unconnected relationships
            for (Map.Entry<String, String> entry : processorIdMap.entrySet()) {
                String procName = entry.getKey();
                String procId = entry.getValue();

                // Get current processor to check relationships
                ProcessorEntity procEntity = procApi.getProcessor(procId);
                if (procEntity == null || procEntity.getComponent() == null) continue;

                org.giwi.nifi.client.model.ProcessorDTO procDTO = procEntity.getComponent();
                List<org.giwi.nifi.client.model.RelationshipDTO> relationships = procDTO.getRelationships();
                if (relationships == null) continue;

                // Get already auto-terminated relationships
                java.util.Set<String> autoTerminated = new java.util.LinkedHashSet<>();
                if (procDTO.getConfig() != null && procDTO.getConfig().getAutoTerminatedRelationships() != null) {
                    autoTerminated.addAll(procDTO.getConfig().getAutoTerminatedRelationships());
                }

                // Get connected relationships for this processor
                java.util.Set<String> connected = connectedRelationships.getOrDefault(procName, java.util.Collections.emptySet());

                // Find unconnected relationships
                boolean needsUpdate = false;
                for (org.giwi.nifi.client.model.RelationshipDTO rel : relationships) {
                    String relName = rel.getName();
                    if (!connected.contains(relName) && !autoTerminated.contains(relName)) {
                        autoTerminated.add(relName);
                        log.debug("Auto-terminating relationship: {} for processor: {}", relName, procName);
                        needsUpdate = true;
                    }
                }

                // Update processor if needed
                if (needsUpdate && procDTO.getConfig() != null) {
                    procDTO.getConfig().setAutoTerminatedRelationships(autoTerminated);

                    // Update revision
                    procEntity.getComponent().setConfig(procDTO.getConfig());
                    assert procEntity.getRevision() != null;
                    procEntity.getRevision().setVersion(procEntity.getRevision().getVersion());

                    procApi.updateProcessor(procId, procEntity);
                }
            }
        } catch (Exception e) {
            // Failed to auto-terminate relationships
        }
    }

    private ProcessorEntity createProcessorEntity(Map<String, Object> proc, String parentGroupId) {
        // Handle both flat YAML format and converted format
        Map<String, Object> dtoMap = proc;
        if (proc.containsKey("processor")) {
            dtoMap = (Map<String, Object>) proc.get("processor");
        }

        ProcessorEntity entity = new ProcessorEntity();
        org.giwi.nifi.client.model.ProcessorDTO dto = new org.giwi.nifi.client.model.ProcessorDTO();

        String type = (String) dtoMap.get("type");
        dto.setType(type);
        dto.setName((String) dtoMap.get("name"));
        dto.setParentGroupId(parentGroupId);

        // Set bundle based on processor type
        BundleDTO bundle = getBundleForProcessorType(type);
        dto.setBundle(bundle);

        // Handle position
        if (dtoMap.containsKey("position")) {
            Map<String, Object> posMap = (Map<String, Object>) dtoMap.get("position");
            PositionDTO pos = new PositionDTO();
            pos.setX(((Number) posMap.getOrDefault("x", 0)).doubleValue());
            pos.setY(((Number) posMap.getOrDefault("y", 0)).doubleValue());
            dto.setPosition(pos);
        } else {
            PositionDTO pos = new PositionDTO();
            pos.setX(((Number) dtoMap.getOrDefault("x", 0)).doubleValue());
            pos.setY(((Number) dtoMap.getOrDefault("y", 0)).doubleValue());
            dto.setPosition(pos);
        }

        // Set properties
        if (dtoMap.containsKey("properties")) {
            Map<String, String> props = new HashMap<>();
            Map<String, Object> rawProps = (Map<String, Object>) dtoMap.get("properties");
            for (Map.Entry<String, Object> entry : rawProps.entrySet()) {
                props.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
            dto.setConfig(new org.giwi.nifi.client.model.ProcessorConfigDTO());
            assert dto.getConfig() != null;
            dto.getConfig().setProperties(props);
        } else {
            dto.setConfig(new org.giwi.nifi.client.model.ProcessorConfigDTO());
        }

        // Set scheduling properties
        if (dtoMap.containsKey("schedulingStrategy")) {
            assert dto.getConfig() != null;
            dto.getConfig().setSchedulingStrategy((String) dtoMap.get("schedulingStrategy"));
        }
        if (dtoMap.containsKey("schedulingPeriod")) {
            assert dto.getConfig() != null;
            dto.getConfig().setSchedulingPeriod((String) dtoMap.get("schedulingPeriod"));
        }
        if (dtoMap.containsKey("concurrentlySchedulableTaskCount")) {
            Object count = dtoMap.get("concurrentlySchedulableTaskCount");
            if (count instanceof Number) {
                assert dto.getConfig() != null;
                dto.getConfig().setConcurrentlySchedulableTaskCount(((Number) count).intValue());
            }
        }
        if (dtoMap.containsKey("runDurationMillis")) {
            Object duration = dtoMap.get("runDurationMillis");
            if (duration instanceof Number) {
                assert dto.getConfig() != null;
                dto.getConfig().setRunDurationMillis(((Number) duration).longValue());
            }
        }

        // Set auto-terminated relationships
        if (dtoMap.containsKey("autoTerminatedRelationships")) {
            List<String> rels = (List<String>) dtoMap.get("autoTerminatedRelationships");
            assert dto.getConfig() != null;
            dto.getConfig().setAutoTerminatedRelationships(new java.util.LinkedHashSet<>(rels));
        }

        // Set state
        if (dtoMap.containsKey("state")) {
            String stateStr = (String) dtoMap.get("state");
            dto.setState(org.giwi.nifi.client.model.ProcessorDTO.StateEnum.fromValue(stateStr));
        }

        RevisionDTO revision = new RevisionDTO();
        revision.setVersion(0L);
        entity.setRevision(revision);
        entity.setComponent(dto);

        return entity;
    }

    private BundleDTO getBundleForProcessorType(String type) {
        BundleDTO bundle = new BundleDTO();
        bundle.setGroup("org.apache.nifi");
        bundle.setVersion("2.9.0");

        // Determine artifact based on processor type - check specific types first
        if (type != null) {
            String typeLower = type.toLowerCase();
            if (typeLower.contains("jolt")) {
                bundle.setArtifact("nifi-jolt-nar");
            } else if (typeLower.contains("json")) {
                bundle.setArtifact("nifi-json-nar");
            } else if (type.contains(".standard.")) {
                bundle.setArtifact("nifi-standard-nar");
            } else {
                // Default to standard
                bundle.setArtifact("nifi-standard-nar");
            }
        } else {
            bundle.setArtifact("nifi-standard-nar");
        }

        return bundle;
    }

    private ConnectionEntity createConnectionEntity(Map<String, Object> conn, String parentGroupId, Map<String, String> processorIdMap) {
        // Handle both flat YAML format and converted format
        Map<String, Object> connMap = conn;
        if (conn.containsKey("connection")) {
            connMap = (Map<String, Object>) conn.get("connection");
        }

        ConnectionEntity entity = new ConnectionEntity();
        org.giwi.nifi.client.model.ConnectionDTO dto = new org.giwi.nifi.client.model.ConnectionDTO();

        dto.setParentGroupId(parentGroupId);

        // Resolve source and destination IDs
        String sourceName = extractIdFromRef((String) connMap.get("sourceId"));
        String destName = extractIdFromRef((String) connMap.get("destinationId"));

        String sourceId = processorIdMap.getOrDefault(sourceName, sourceName);
        String destId = processorIdMap.getOrDefault(destName, destName);

        ConnectableDTO source = new ConnectableDTO();
        source.setId(sourceId);
        source.setType(ConnectableDTO.TypeEnum.PROCESSOR);
        source.setGroupId(parentGroupId);
        dto.setSource(source);

        ConnectableDTO dest = new ConnectableDTO();
        dest.setId(destId);
        dest.setType(ConnectableDTO.TypeEnum.PROCESSOR);
        dest.setGroupId(parentGroupId);
        dto.setDestination(dest);

        // Set relationships
        if (connMap.containsKey("selectedRelationships")) {
            List<String> rels = (List<String>) connMap.get("selectedRelationships");
            dto.setSelectedRelationships(new java.util.LinkedHashSet<>(rels));
        }

        // Set optional connection properties from converter
        if (connMap.containsKey("name")) {
            dto.setName((String) connMap.get("name"));
        }
        if (connMap.containsKey("flowFileExpiration")) {
            dto.setFlowFileExpiration((String) connMap.get("flowFileExpiration"));
        }
        if (connMap.containsKey("backPressureDataSizeThreshold")) {
            dto.setBackPressureDataSizeThreshold((String) connMap.get("backPressureDataSizeThreshold"));
        }
        if (connMap.containsKey("backPressureObjectThreshold")) {
            Object threshold = connMap.get("backPressureObjectThreshold");
            if (threshold != null) {
                if (threshold instanceof Number) {
                    dto.setBackPressureObjectThreshold(((Number) threshold).longValue());
                } else {
                    try {
                        dto.setBackPressureObjectThreshold(Long.parseLong(threshold.toString()));
                    } catch (NumberFormatException e) {
                        // Invalid threshold value, skip
                    }
                }
            }
        }
        if (connMap.containsKey("position")) {
            Map<String, Object> posMap = (Map<String, Object>) connMap.get("position");
            PositionDTO pos = new PositionDTO();
            pos.setX(((Number) posMap.getOrDefault("x", 0)).doubleValue());
            pos.setY(((Number) posMap.getOrDefault("y", 0)).doubleValue());
            dto.setPosition(pos);
        }

        RevisionDTO revision = new RevisionDTO();
        revision.setVersion(0L);
        entity.setRevision(revision);
        entity.setComponent(dto);

        return entity;
    }

    private String extractIdFromRef(String ref) {
        if (ref != null && ref.startsWith("${") && ref.endsWith("}")) {
            return ref.substring(2, ref.length() - 1);
        }
        return ref;
    }

    public org.giwi.nifi.client.model.ProcessGroupEntity getProcessGroup(String processGroupId) {
        ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
        return pgApi.getProcessGroup(processGroupId);
    }

    public boolean deleteProcessGroup(String processGroupId) {
        try {
            ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
            pgApi.removeProcessGroup(processGroupId, null, null, null);
            log.info("Deleted process group: {}", processGroupId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    // ==================== TESTING UTILITIES ====================

    /**
     * Find a processor ID by its name in a process group
     */
    public String findProcessorIdByName(String processGroupId, String processorName) {
        ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
        ProcessorsEntity processors = pgApi.getProcessors(processGroupId, false);
        if (processors.getProcessors() != null) {
            for (ProcessorEntity proc : processors.getProcessors()) {
                assert proc.getComponent() != null;
                if (processorName.equals(proc.getComponent().getName())) {
                    return proc.getComponent().getId();
                }
            }
        }
        return null;
    }

    /**
     * Find a connection ID by source and destination names
     */
    public String findConnectionId(String processGroupId, String sourceName, String destName) throws Exception {
        ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
        ConnectionsEntity connections = pgApi.getConnections(processGroupId);
        if (connections.getConnections() != null) {
            String sourceId = findProcessorIdByName(processGroupId, sourceName);
            String destId = findProcessorIdByName(processGroupId, destName);
            for (org.giwi.nifi.client.model.ConnectionEntity conn : connections.getConnections()) {
                if (conn.getComponent() != null) {
                    org.giwi.nifi.client.model.ConnectableDTO src = conn.getComponent().getSource();
                    org.giwi.nifi.client.model.ConnectableDTO dst = conn.getComponent().getDestination();
                    if (src != null && dst != null &&
                            src.getId().equals(sourceId) && dst.getId().equals(destId)) {
                        return conn.getComponent().getId();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get all connection IDs for a processor (as source)
     */
    public List<String> getConnectionIdsForProcessor(String processGroupId, String processorName) throws Exception {
        List<String> result = new ArrayList<>();
        ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
        ConnectionsEntity connections = pgApi.getConnections(processGroupId);
        String procId = findProcessorIdByName(processGroupId, processorName);
        if (connections.getConnections() != null && procId != null) {
            for (org.giwi.nifi.client.model.ConnectionEntity conn : connections.getConnections()) {
                if (conn.getComponent() != null && conn.getComponent().getSource() != null) {
                    if (procId.equals(conn.getComponent().getSource().getId())) {
                        result.add(conn.getId());
                    }
                }
            }
        }
        return result;
    }

    /**
     * Find an input port ID by name
     */
    public String findInputPortIdByName(String processGroupId, String portName) {
        ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
        InputPortsEntity ports = pgApi.getInputPorts(processGroupId);
        if (ports.getInputPorts() != null) {
            for (org.giwi.nifi.client.model.PortEntity port : ports.getInputPorts()) {
                assert port.getComponent() != null;
                if (portName.equals(port.getComponent().getName())) {
                    return port.getComponent().getId();
                }
            }
        }
        return null;
    }

    /**
     * Find an output port ID by name
     */
    public String findOutputPortIdByName(String processGroupId, String portName) {
        ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
        OutputPortsEntity ports = pgApi.getOutputPorts(processGroupId);
        if (ports.getOutputPorts() != null) {
            for (org.giwi.nifi.client.model.PortEntity port : ports.getOutputPorts()) {
                assert port.getComponent() != null;
                if (portName.equals(port.getComponent().getName())) {
                    return port.getComponent().getId();
                }
            }
        }
        return null;
    }

    /**
     * Update processor properties for testing (e.g., set custom text in GenerateFlowFile)
     */
    public void updateProcessorProperties(String processorId, Map<String, String> properties) {
        ProcessorsApi procApi = new ProcessorsApi(client);
        ProcessorEntity procEntity = procApi.getProcessor(processorId);
        if (procEntity.getComponent() != null) {
            org.giwi.nifi.client.model.ProcessorConfigDTO config = procEntity.getComponent().getConfig();
            if (config == null) {
                config = new org.giwi.nifi.client.model.ProcessorConfigDTO();
            }
            Map<String, String> currentProps = config.getProperties();
            if (currentProps == null) {
                currentProps = new HashMap<>();
            }
            currentProps.putAll(properties);
            config.setProperties(currentProps);
            procEntity.getComponent().setConfig(config);
            procApi.updateProcessor(processorId, procEntity);
        }
    }

    /**
     * Set the text content for a GenerateFlowFile processor
     */
    public void setGenerateFlowFileText(String processorId, String text) {
        Map<String, String> props = new HashMap<>();
        props.put("Text", text);
        props.put("Data Format", "Text");
        updateProcessorProperties(processorId, props);
    }

    /**
     * Start a processor
     */
    public void startProcessor(String processorId) {
        ProcessorsApi procApi = new ProcessorsApi(client);
        ProcessorEntity procEntity = procApi.getProcessor(processorId);
        assert procEntity.getComponent() != null;
        procEntity.getComponent().setState(org.giwi.nifi.client.model.ProcessorDTO.StateEnum.RUNNING);
        log.info("Started processor: {}", processorId);
        procApi.updateProcessor(processorId, procEntity);
    }

    /**
     * Stop a processor
     */
    public void stopProcessor(String processorId) {
        ProcessorsApi procApi = new ProcessorsApi(client);
        ProcessorEntity procEntity = procApi.getProcessor(processorId);
        assert procEntity.getComponent() != null;
        procEntity.getComponent().setState(org.giwi.nifi.client.model.ProcessorDTO.StateEnum.STOPPED);
        log.info("Stopped processor: {}", processorId);
        procApi.updateProcessor(processorId, procEntity);
    }

    /**
     * Start all processors in a process group
     */
    public void startProcessGroup(String processGroupId) {
        FlowApi flowApi = new FlowApi(client);
        org.giwi.nifi.client.model.ScheduleComponentsEntity schedule = new org.giwi.nifi.client.model.ScheduleComponentsEntity();
        schedule.setState(org.giwi.nifi.client.model.ScheduleComponentsEntity.StateEnum.RUNNING);
        schedule.setId(processGroupId);
        flowApi.scheduleComponents(processGroupId, schedule);
        log.info("Stopped process group: {}", processGroupId);
        log.info("Started process group: {}", processGroupId);
    }

    /**
     * Stop all processors in a process group
     */
    public void stopProcessGroup(String processGroupId) {
        FlowApi flowApi = new FlowApi(client);
        org.giwi.nifi.client.model.ScheduleComponentsEntity schedule = new org.giwi.nifi.client.model.ScheduleComponentsEntity();
        schedule.setState(org.giwi.nifi.client.model.ScheduleComponentsEntity.StateEnum.STOPPED);
        schedule.setId(processGroupId);
        flowApi.scheduleComponents(processGroupId, schedule);
        log.info("Stopped process group: {}", processGroupId);
        log.info("Started process group: {}", processGroupId);
    }

    /**
     * Wait for a processor to be idle (no active threads)
     */
    public boolean waitForProcessorIdle(String processorId, long timeoutMs) throws Exception {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeoutMs) {
            FlowApi flowApi = new FlowApi(client);
            org.giwi.nifi.client.model.ProcessorStatusEntity status = flowApi.getProcessorStatus(processorId, null, null);
            if (status.getProcessorStatus() != null && status.getProcessorStatus().getAggregateSnapshot() != null) {
                Integer activeThreads = status.getProcessorStatus().getAggregateSnapshot().getActiveThreadCount();
                if (activeThreads != null && activeThreads == 0) {
                    return true;
                }
            }
            Thread.sleep(500);
        }
        return false;
    }

    /**
     * Wait for all processors in a process group to be idle
     */
    public boolean waitForProcessGroupIdle(String processGroupId, long timeoutMs) throws Exception {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeoutMs) {
            FlowApi flowApi = new FlowApi(client);
            org.giwi.nifi.client.model.ProcessGroupStatusEntity statusEntity = flowApi.getProcessGroupStatus(processGroupId, false, false, null);
            if (statusEntity.getProcessGroupStatus() != null &&
                    statusEntity.getProcessGroupStatus().getAggregateSnapshot() != null) {
                Integer activeThreads = statusEntity.getProcessGroupStatus().getAggregateSnapshot().getActiveThreadCount();
                if (activeThreads != null && activeThreads == 0) {
                    return true;
                }
            }
            Thread.sleep(1000);
        }
        return false;
    }

    /**
     * List flow files in a connection queue
     */
    public List<org.giwi.nifi.client.model.FlowFileSummaryDTO> listFlowFiles(String connectionId) throws Exception {
        FlowFileQueuesApi queueApi = new FlowFileQueuesApi(client);
        ListingRequestEntity listing = queueApi.createFlowFileListing(connectionId);

        // Wait for listing to complete
        assert listing.getListingRequest() != null;
        String listingId = listing.getListingRequest().getId();
        while (true) {
            assert listing.getListingRequest() != null;
            if (!(!"COMPLETE".equals(listing.getListingRequest().getState()) &&
                            !"FAILURE".equals(listing.getListingRequest().getState()))) break;
            Thread.sleep(200);
            listing = queueApi.getListingRequest(connectionId, listingId);
        }

        if ("COMPLETE".equals(listing.getListingRequest().getState()) &&
                listing.getListingRequest().getFlowFileSummaries() != null) {
            return listing.getListingRequest().getFlowFileSummaries();
        }
        return new ArrayList<>();
    }

    /**
     * Download flow file content from a queue
     */
    public byte[] downloadFlowFileContent(String connectionId, String flowFileUuid) throws Exception {
        FlowFileQueuesApi queueApi = new FlowFileQueuesApi(client);
        Object content = queueApi.downloadFlowFileContent(connectionId, flowFileUuid, null, null, null);
        // Content is returned as raw bytes or string depending on the API
        if (content instanceof byte[]) {
            return (byte[]) content;
        } else if (content != null) {
            return content.toString().getBytes(StandardCharsets.UTF_8);
        }
        return new byte[0];
    }

    /**
     * Get the content of all flow files in a connection as strings
     */
    public List<String> getFlowFileContentsAsString(String connectionId) throws Exception {
        List<String> contents = new ArrayList<>();
        List<org.giwi.nifi.client.model.FlowFileSummaryDTO> flowFiles = listFlowFiles(connectionId);
        for (org.giwi.nifi.client.model.FlowFileSummaryDTO ff : flowFiles) {
            try {
                byte[] content = downloadFlowFileContent(connectionId, ff.getUuid());
                contents.add(new String(content, StandardCharsets.UTF_8));
            } catch (Exception e) {
                // Failed to download flow file content
            }
        }
        return contents;
    }

    /**
     * Assert that a connection queue contains expected content
     */
    public boolean assertQueueContains(String connectionId, String expectedContent) throws Exception {
        List<String> contents = getFlowFileContentsAsString(connectionId);
        for (String content : contents) {
            if (content.contains(expectedContent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Assert that output from a processor contains expected content
     */
    public boolean assertProcessorOutputContains(String processGroupId, String processorName, String expectedContent) throws Exception {
        List<String> connectionIds = getConnectionIdsForProcessor(processGroupId, processorName);
        for (String connId : connectionIds) {
            if (assertQueueContains(connId, expectedContent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Drop all flow files in a connection (clear the queue)
     */
    public void clearQueue(String connectionId) throws Exception {
        FlowFileQueuesApi queueApi = new FlowFileQueuesApi(client);
        queueApi.createDropRequest(connectionId);
        // Note: Should wait for drop to complete in production code
    }

    // ==================== END TESTING UTILITIES ====================

    public static class PipelineTesterResult {
        private boolean success;
        private String message;
        private String processGroupId;
        private Map<String, Object> details;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getProcessGroupId() {
            return processGroupId;
        }

        public void setProcessGroupId(String processGroupId) {
            this.processGroupId = processGroupId;
        }

        public Map<String, Object> getDetails() {
            return details;
        }

        public void setDetails(Map<String, Object> details) {
            this.details = details;
        }
    }
}
