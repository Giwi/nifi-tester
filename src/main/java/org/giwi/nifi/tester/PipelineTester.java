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

import org.giwi.nifi.tester.api.*;
import org.giwi.nifi.tester.invoker.ApiClient;
import org.giwi.nifi.tester.model.*;
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

/**
 * Main entry point for deploying NiFi pipelines from YAML definitions.
 *
 * <p>This class provides:
 * <ul>
 *   <li>Connection to NiFi REST API with authentication</li>
 *   <li>Pipeline deployment from YAML files</li>
 *   <li>Processor and process group management (start/stop, query status)</li>
 *   <li>Flow file inspection and content retrieval</li>
 *   <li>Command-line interface for pipeline deployment</li>
 * </ul>
 *
 * <p>Usage examples:
 * <pre>{@code
 * // Connect to NiFi
 * PipelineTester tester = new PipelineTester(url, username, password);
 *
 * // Deploy pipeline
 * PipelineTesterResult result = tester.deployPipeline(yamlFile, "root");
 * }</pre>
 *
 * @author GiWi
 * @version 1.0-SNAPSHOT
 */
public class PipelineTester implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(PipelineTester.class);

    private final ApiClient client;
    private final PipelineConverter converter;
    private String accessToken;
    private int maxRetries = 3;
    private long retryDelayMs = 1000;
    private boolean dryRun = false;
    private String detectedNiFiVersion = null;

    /**
     * Enables or disables dry-run mode.
     * In dry-run mode, deployPipeline will only validate and log what would be created.
     *
     * @param dryRun true to enable dry-run mode, false to disable
     */
    public void setDryRun(boolean dryRun) {
        this.dryRun = dryRun;
    }

    public boolean isDryRun() {
        return dryRun;
    }

    // Retryable operations
    @FunctionalInterface
    public interface RetryableOperation<T> {
        T execute() throws Exception;
    }

    /**
     * Executes an operation with retry logic.
     *
     * @param operation The operation to execute
     * @param operationName Name of the operation for logging
     * @return The result of the operation
     * @throws Exception if all retries fail
     */
    private <T> T executeWithRetry(RetryableOperation<T> operation, String operationName) throws Exception {
        Exception lastException = null;
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                return operation.execute();
            } catch (Exception e) {
                lastException = e;
                if (attempt < maxRetries) {
                    log.warn("Attempt {} failed for {}: {}. Retrying...", attempt + 1, operationName, e.getMessage());
                    try {
                        Thread.sleep(retryDelayMs * (attempt + 1)); // Exponential backoff
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new Exception("Retry interrupted", ie);
                    }
                }
            }
        }
        throw new Exception("Operation " + operationName + " failed after " + maxRetries + " retries", lastException);
    }

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
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        AccessApi accessApi = new AccessApi(client);
        String token = accessApi.createAccessToken(password, username);
        this.accessToken = token;
        this.client.addDefaultHeader("Authorization", "Bearer " + token);
        log.info("Logged in to NiFi as user: {}", username);

        // Detect NiFi version after successful login
        detectNiFiVersion();
    }

    /**
     * Deploys a pipeline from a YAML file to the root process group.
     *
     * @param yamlFile The YAML file containing the pipeline definition
     * @return A PipelineTesterResult with deployment status
     * @throws IOException if the file cannot be read
     */
    public PipelineTesterResult deployPipeline(File yamlFile) throws IOException {
        if (yamlFile == null) {
            throw new IllegalArgumentException("YAML file cannot be null");
        }
        if (!yamlFile.exists() || !yamlFile.canRead()) {
            throw new IOException("YAML file does not exist or cannot be read: " + yamlFile.getPath());
        }
        return deployPipeline(yamlFile, "root");
    }

    public PipelineTesterResult deployPipeline(File yamlFile, String parentGroupId) throws IOException {
        if (yamlFile == null) {
            throw new IllegalArgumentException("YAML file cannot be null");
        }
        if (!yamlFile.exists() || !yamlFile.canRead()) {
            throw new IOException("YAML file does not exist or cannot be read: " + yamlFile.getPath());
        }

        // Validate pipeline before deployment
        PipelineValidator validator = new PipelineValidator();
        List<String> validationErrors = validator.validate(yamlFile);
        if (!validationErrors.isEmpty()) {
            PipelineTesterResult result = new PipelineTesterResult();
            result.setSuccess(false);
            result.setMessage("Validation failed:\n" + String.join("\n", validationErrors));
            log.error("Pipeline validation failed: {}", validationErrors);
            return result;
        }

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
        Map<String, String> inputPortIdMap = new HashMap<>();
        Map<String, String> outputPortIdMap = new HashMap<>();

        // Log what would be deployed (dry-run mode)
        if (dryRun) {
            log.info("DRY-RUN: Would deploy pipeline: {}", pipelineData.getOrDefault("name", "Untitled"));
            log.info("DRY-RUN: Parent group ID: {}", parentGroupId);
            if (pipelineData.containsKey("processors")) {
                List<Map<String, Object>> procs = (List<Map<String, Object>>) pipelineData.get("processors");
                log.info("DRY-RUN: Would create {} processors", procs.size());
            }
            if (pipelineData.containsKey("connections")) {
                List<Map<String, Object>> conns = (List<Map<String, Object>>) pipelineData.get("connections");
                log.info("DRY-RUN: Would create {} connections", conns.size());
            }
            result.setSuccess(true);
            result.setMessage("Dry-run completed successfully");
            return result;
        }

        try {
            ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
            String rootGroupId = parentGroupId != null ? parentGroupId : "root";

            // Create a new process group for this pipeline
            ProcessGroupEntity pgEntity = new ProcessGroupEntity();
            org.giwi.nifi.tester.model.ProcessGroupDTO pgDto = new org.giwi.nifi.tester.model.ProcessGroupDTO();
            pgDto.setName((String) pipelineData.getOrDefault("name", "Untitled Pipeline"));
            pgDto.setParentGroupId(rootGroupId);

            RevisionDTO revision = new RevisionDTO();
            revision.setVersion(0L);
            pgEntity.setRevision(revision);
            pgEntity.setComponent(pgDto);

            ProcessGroupEntity createdPg = pgApi.createProcessGroup(rootGroupId, pgEntity, null);
            java.util.Objects.requireNonNull(createdPg.getComponent(), "Failed to create process group");
            String newPgId = createdPg.getComponent().getId();
            result.setProcessGroupId(newPgId);
            log.info("Created process group: {} ({})", pipelineData.getOrDefault("name", "Untitled"), newPgId);

            try {
                // Create input ports
                if (pipelineData.containsKey("inputPorts")) {
                    List<Map<String, Object>> ports = (List<Map<String, Object>>) pipelineData.get("inputPorts");
                    for (Map<String, Object> portWrapper : ports) {
                        PortEntity portEntity = createPortEntity(portWrapper, newPgId, org.giwi.nifi.tester.model.PortDTO.TypeEnum.INPUT_PORT);
                        PortEntity created = pgApi.createInputPort(newPgId, portEntity);
                        java.util.Objects.requireNonNull(created.getComponent(), "Failed to create input port");
                        String portId = created.getComponent().getId();
                    Map<String, Object> port = portWrapper;
                    if (portWrapper.containsKey("port")) {
                        port = (Map<String, Object>) portWrapper.get("port");
                    }
                    inputPortIdMap.put((String) port.get("name"), portId);
                }
            }

                // Create output ports
                if (pipelineData.containsKey("outputPorts")) {
                    List<Map<String, Object>> ports = (List<Map<String, Object>>) pipelineData.get("outputPorts");
                    for (Map<String, Object> portWrapper : ports) {
                        PortEntity portEntity = createPortEntity(portWrapper, newPgId, org.giwi.nifi.tester.model.PortDTO.TypeEnum.OUTPUT_PORT);
                        PortEntity created = pgApi.createOutputPort(newPgId, portEntity);
                        java.util.Objects.requireNonNull(created.getComponent(), "Failed to create output port");
                        String portId = created.getComponent().getId();
                    Map<String, Object> port = portWrapper;
                    if (portWrapper.containsKey("port")) {
                        port = (Map<String, Object>) portWrapper.get("port");
                    }
                    outputPortIdMap.put((String) port.get("name"), portId);
                }
            }

                // Create processors
                if (pipelineData.containsKey("processors")) {
                    List<Map<String, Object>> processors = (List<Map<String, Object>>) pipelineData.get("processors");
                    for (Map<String, Object> procWrapper : processors) {
                        ProcessorEntity procEntity = createProcessorEntity(procWrapper, newPgId);
                        ProcessorEntity created = pgApi.createProcessor(newPgId, procEntity);
                        java.util.Objects.requireNonNull(created.getComponent(), "Failed to create processor");
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
                    org.giwi.nifi.tester.model.FunnelDTO funnelDto = new org.giwi.nifi.tester.model.FunnelDTO();
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
                    ConnectionEntity connEntity = createConnectionEntity(conn, newPgId, processorIdMap, inputPortIdMap, outputPortIdMap);
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
            } catch (Exception innerEx) {
                log.error("Pipeline deployment failed during component creation. Rolling back process group: {}", newPgId, innerEx);
                try {
                    deleteProcessGroup(newPgId);
                } catch (Exception rollbackEx) {
                    log.error("Rollback failed for process group: {}", newPgId, rollbackEx);
                }
                throw innerEx;
            }
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

    /**
     * Gets the detected NiFi version (if available).
     *
     * @return The detected NiFi version, or null if not yet detected
     */
    public String getDetectedNiFiVersion() {
        return detectedNiFiVersion;
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

                org.giwi.nifi.tester.model.ProcessorDTO procDTO = procEntity.getComponent();
                List<org.giwi.nifi.tester.model.RelationshipDTO> relationships = procDTO.getRelationships();
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
                for (org.giwi.nifi.tester.model.RelationshipDTO rel : relationships) {
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
                    java.util.Objects.requireNonNull(procEntity.getRevision(), "Processor revision cannot be null");
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
        org.giwi.nifi.tester.model.ProcessorDTO dto = new org.giwi.nifi.tester.model.ProcessorDTO();

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
            dto.setConfig(new org.giwi.nifi.tester.model.ProcessorConfigDTO());
            java.util.Objects.requireNonNull(dto.getConfig(), "Processor Config cannot be null");
            dto.getConfig().setProperties(props);
        } else {
            dto.setConfig(new org.giwi.nifi.tester.model.ProcessorConfigDTO());
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
            if (dto.getConfig() != null) {
                dto.getConfig().setAutoTerminatedRelationships(new java.util.LinkedHashSet<>(rels));
            }
        }

        // Set state
        if (dtoMap.containsKey("state")) {
            String stateStr = (String) dtoMap.get("state");
            dto.setState(org.giwi.nifi.tester.model.ProcessorDTO.StateEnum.fromValue(stateStr));
        }

        RevisionDTO revision = new RevisionDTO();
        revision.setVersion(0L);
        entity.setRevision(revision);
        entity.setComponent(dto);

        return entity;
    }

    private PortEntity createPortEntity(Map<String, Object> portWrapper, String parentGroupId, org.giwi.nifi.tester.model.PortDTO.TypeEnum portType) {
        Map<String, Object> portMap = portWrapper;
        if (portWrapper.containsKey("port")) {
            portMap = (Map<String, Object>) portWrapper.get("port");
        }

        PortEntity entity = new PortEntity();
        org.giwi.nifi.tester.model.PortDTO dto = new org.giwi.nifi.tester.model.PortDTO();
        dto.setParentGroupId(parentGroupId);

        // Generate unique port name by appending parent group ID suffix
        String baseName = (String) portMap.getOrDefault("name", portType == org.giwi.nifi.tester.model.PortDTO.TypeEnum.INPUT_PORT ? "Input" : "Output");
        String uniqueName = baseName + "-" + parentGroupId.substring(Math.max(0, parentGroupId.length() - 8));
        dto.setName(uniqueName);

        dto.setType(portType);

        if (portType == org.giwi.nifi.tester.model.PortDTO.TypeEnum.INPUT_PORT) {
            dto.setAllowRemoteAccess(true);
        }

        if (portMap.containsKey("position")) {
            Map<String, Object> posMap = (Map<String, Object>) portMap.get("position");
            PositionDTO pos = new PositionDTO();
            pos.setX(((Number) posMap.getOrDefault("x", 0)).doubleValue());
            pos.setY(((Number) posMap.getOrDefault("y", 0)).doubleValue());
            dto.setPosition(pos);
        } else if (portMap.containsKey("x") || portMap.containsKey("y")) {
            PositionDTO pos = new PositionDTO();
            pos.setX(((Number) portMap.getOrDefault("x", 0)).doubleValue());
            pos.setY(((Number) portMap.getOrDefault("y", 0)).doubleValue());
            dto.setPosition(pos);
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
        bundle.setVersion(detectedNiFiVersion != null ? detectedNiFiVersion : "2.0.0-M2");

        // Determine artifact based on processor type - check specific types first
        if (type != null) {
            String typeLower = type.toLowerCase();
            if (typeLower.contains("jolt")) {
                bundle.setArtifact("nifi-jolt-nar");
            } else if (typeLower.contains("json")) {
                bundle.setArtifact("nifi-json-nar");
            } else if (typeLower.contains("groovyx")) {
                // groovyx processors use nifi-groovyx-nar (not nifi-groovy-nar)
                bundle.setArtifact("nifi-groovyx-nar");
            } else if (typeLower.contains("groovy")) {
                bundle.setArtifact("nifi-groovy-nar");
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

    /**
     * Detects the NiFi version from the running instance.
     * Updates detectedNiFiVersion for use in bundle configuration.
     */
    private void detectNiFiVersion() {
        try {
            FlowApi flowApi = new FlowApi(client);
            AboutEntity aboutEntity = flowApi.getAboutInfo();
            if (aboutEntity != null && aboutEntity.getAbout() != null) {
                String version = aboutEntity.getAbout().getVersion();
                if (version != null && !version.isEmpty()) {
                    detectedNiFiVersion = version;
                    log.info("Detected NiFi version: {}", detectedNiFiVersion);
                }
            }
        } catch (Exception e) {
            log.warn("Failed to detect NiFi version, using default: {}", e.getMessage());
        }
    }

    private ConnectionEntity createConnectionEntity(Map<String, Object> conn, String parentGroupId, 
            Map<String, String> processorIdMap, Map<String, String> inputPortIdMap, Map<String, String> outputPortIdMap) {
        // Handle both flat YAML format and converted format
        Map<String, Object> connMap = conn;
        if (conn.containsKey("connection")) {
            connMap = (Map<String, Object>) conn.get("connection");
        }

        ConnectionEntity entity = new ConnectionEntity();
        org.giwi.nifi.tester.model.ConnectionDTO dto = new org.giwi.nifi.tester.model.ConnectionDTO();

        dto.setParentGroupId(parentGroupId);

        // Resolve source and destination IDs
        String sourceName = extractIdFromRef((String) connMap.get("sourceId"));
        String destName = extractIdFromRef((String) connMap.get("destinationId"));

        // Determine source type and ID
        String sourceId;
        ConnectableDTO.TypeEnum sourceType;
        if (inputPortIdMap.containsKey(sourceName)) {
            sourceId = inputPortIdMap.get(sourceName);
            sourceType = ConnectableDTO.TypeEnum.INPUT_PORT;
        } else if (outputPortIdMap.containsKey(sourceName)) {
            sourceId = outputPortIdMap.get(sourceName);
            sourceType = ConnectableDTO.TypeEnum.OUTPUT_PORT;
        } else {
            sourceId = processorIdMap.getOrDefault(sourceName, sourceName);
            sourceType = ConnectableDTO.TypeEnum.PROCESSOR;
        }

        // Determine destination type and ID
        String destId;
        ConnectableDTO.TypeEnum destType;
        if (inputPortIdMap.containsKey(destName)) {
            destId = inputPortIdMap.get(destName);
            destType = ConnectableDTO.TypeEnum.INPUT_PORT;
        } else if (outputPortIdMap.containsKey(destName)) {
            destId = outputPortIdMap.get(destName);
            destType = ConnectableDTO.TypeEnum.OUTPUT_PORT;
        } else {
            destId = processorIdMap.getOrDefault(destName, destName);
            destType = ConnectableDTO.TypeEnum.PROCESSOR;
        }

        ConnectableDTO source = new ConnectableDTO();
        source.setId(sourceId);
        source.setType(sourceType);
        source.setGroupId(parentGroupId);
        dto.setSource(source);

        ConnectableDTO dest = new ConnectableDTO();
        dest.setId(destId);
        dest.setType(destType);
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

        // Set bends (control points for connection line)
        if (connMap.containsKey("bends")) {
            List<Map<String, Object>> bendList = (List<Map<String, Object>>) connMap.get("bends");
            List<PositionDTO> bends = new ArrayList<>();
            for (Map<String, Object> bendMap : bendList) {
                PositionDTO bend = new PositionDTO();
                bend.setX(((Number) bendMap.getOrDefault("x", 0)).doubleValue());
                bend.setY(((Number) bendMap.getOrDefault("y", 0)).doubleValue());
                bends.add(bend);
            }
            dto.setBends(bends);
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

    public org.giwi.nifi.tester.model.ProcessGroupEntity getProcessGroup(String processGroupId) {
        ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
        return pgApi.getProcessGroup(processGroupId);
    }

    public boolean deleteProcessGroup(String processGroupId) {
        try {
            ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
            // Get current version first with retry
            ProcessGroupEntity pg = executeWithRetry(() -> pgApi.getProcessGroup(processGroupId), 
                "getProcessGroup for " + processGroupId);
            
            final LongParameter version;
            if (pg.getRevision() != null && pg.getRevision().getVersion() != null) {
                version = new LongParameter();
                version.setLong(pg.getRevision().getVersion());
            } else {
                version = null;
            }
            
            // Delete with retry
            final String pgId = processGroupId;
            executeWithRetry(() -> {
                pgApi.removeProcessGroup(pgId, version, null, null);
                return null;
            }, "deleteProcessGroup " + processGroupId);
            
            log.info("Deleted process group: {}", processGroupId);
            return true;
        } catch (Exception e) {
            log.error("Failed to delete process group: {}", processGroupId, e);
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
                if (proc.getComponent() != null && processorName.equals(proc.getComponent().getName())) {
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
            for (org.giwi.nifi.tester.model.ConnectionEntity conn : connections.getConnections()) {
                if (conn.getComponent() != null) {
                    org.giwi.nifi.tester.model.ConnectableDTO src = conn.getComponent().getSource();
                    org.giwi.nifi.tester.model.ConnectableDTO dst = conn.getComponent().getDestination();
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
            for (org.giwi.nifi.tester.model.ConnectionEntity conn : connections.getConnections()) {
                if (conn.getComponent() != null && conn.getComponent().getSource() != null) {
                    if (procId.equals(conn.getComponent().getSource().getId())) {
                        result.add(conn.getId());
                    }
                }
            }
        }
        return result;
    }

    private String findPortIdByName(String processGroupId, String portName, boolean isInput) {
        ProcessGroupsApi pgApi = new ProcessGroupsApi(client);
        if (isInput) {
            InputPortsEntity ports = pgApi.getInputPorts(processGroupId);
            if (ports.getInputPorts() != null) {
                for (org.giwi.nifi.tester.model.PortEntity port : ports.getInputPorts()) {
                    if (port.getComponent() != null && portName.equals(port.getComponent().getName())) {
                        return port.getComponent().getId();
                    }
                }
            }
        } else {
            OutputPortsEntity ports = pgApi.getOutputPorts(processGroupId);
            if (ports.getOutputPorts() != null) {
                for (org.giwi.nifi.tester.model.PortEntity port : ports.getOutputPorts()) {
                    if (port.getComponent() != null && portName.equals(port.getComponent().getName())) {
                        return port.getComponent().getId();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Find an input port ID by name
     */
    public String findInputPortIdByName(String processGroupId, String portName) {
        return findPortIdByName(processGroupId, portName, true);
    }

    /**
     * Find an output port ID by name
     */
    public String findOutputPortIdByName(String processGroupId, String portName) {
        return findPortIdByName(processGroupId, portName, false);
    }

    /**
     * Update processor properties for testing (e.g., set custom text in GenerateFlowFile)
     */
    public void updateProcessorProperties(String processorId, Map<String, String> properties) {
        ProcessorsApi procApi = new ProcessorsApi(client);
        ProcessorEntity procEntity = procApi.getProcessor(processorId);
        if (procEntity.getComponent() != null) {
            org.giwi.nifi.tester.model.ProcessorConfigDTO config = procEntity.getComponent().getConfig();
            if (config == null) {
                config = new org.giwi.nifi.tester.model.ProcessorConfigDTO();
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
        java.util.Objects.requireNonNull(procEntity.getComponent(), "Processor component cannot be null");
        procEntity.getComponent().setState(org.giwi.nifi.tester.model.ProcessorDTO.StateEnum.RUNNING);
        log.info("Started processor: {}", processorId);
        procApi.updateProcessor(processorId, procEntity);
    }

    /**
     * Stop a processor
     */
    public void stopProcessor(String processorId) {
        ProcessorsApi procApi = new ProcessorsApi(client);
        ProcessorEntity procEntity = procApi.getProcessor(processorId);
        java.util.Objects.requireNonNull(procEntity.getComponent(), "Processor component cannot be null");
        procEntity.getComponent().setState(org.giwi.nifi.tester.model.ProcessorDTO.StateEnum.STOPPED);
        log.info("Stopped processor: {}", processorId);
        procApi.updateProcessor(processorId, procEntity);
    }

    /**
     * Start all processors in a process group
     */
    public void startProcessGroup(String processGroupId) {
        FlowApi flowApi = new FlowApi(client);
        org.giwi.nifi.tester.model.ScheduleComponentsEntity schedule = new org.giwi.nifi.tester.model.ScheduleComponentsEntity();
        schedule.setState(org.giwi.nifi.tester.model.ScheduleComponentsEntity.StateEnum.RUNNING);
        schedule.setId(processGroupId);
        schedule.setComponents(null);
        flowApi.scheduleComponents(processGroupId, schedule);
        log.info("Started process group: {}", processGroupId);
    }

    /**
     * Stop all processors in a process group
     */
    public void stopProcessGroup(String processGroupId) {
        FlowApi flowApi = new FlowApi(client);
        org.giwi.nifi.tester.model.ScheduleComponentsEntity schedule = new org.giwi.nifi.tester.model.ScheduleComponentsEntity();
        schedule.setState(org.giwi.nifi.tester.model.ScheduleComponentsEntity.StateEnum.STOPPED);
        schedule.setId(processGroupId);
        schedule.setComponents(null);
        flowApi.scheduleComponents(processGroupId, schedule);
        log.info("Stopped process group: {}", processGroupId);
    }

    /**
     * Wait for a processor to be idle (no active threads)
     */
    public boolean waitForProcessorIdle(String processorId, long timeoutMs) throws Exception {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeoutMs) {
            FlowApi flowApi = new FlowApi(client);
            org.giwi.nifi.tester.model.ProcessorStatusEntity status = flowApi.getProcessorStatus(processorId, null, null);
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
            org.giwi.nifi.tester.model.ProcessGroupStatusEntity statusEntity = flowApi.getProcessGroupStatus(processGroupId, false, false, null);
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
    public List<org.giwi.nifi.tester.model.FlowFileSummaryDTO> listFlowFiles(String connectionId) throws Exception {
        FlowFileQueuesApi queueApi = new FlowFileQueuesApi(client);
        ListingRequestEntity listing = queueApi.createFlowFileListing(connectionId);

        // Wait for listing to complete
        java.util.Objects.requireNonNull(listing.getListingRequest(), "Listing request cannot be null");
        String listingId = listing.getListingRequest().getId();
        while (true) {
            java.util.Objects.requireNonNull(listing.getListingRequest(), "Listing request cannot be null");
            if ("COMPLETE".equals(listing.getListingRequest().getState()) ||
                "FAILURE".equals(listing.getListingRequest().getState())) {
                break;
            }
            Thread.sleep(200);
            listing = queueApi.getListingRequest(connectionId, listingId);
        }

        List<org.giwi.nifi.tester.model.FlowFileSummaryDTO> summaries = new ArrayList<>();
        if ("COMPLETE".equals(listing.getListingRequest().getState()) &&
                listing.getListingRequest().getFlowFileSummaries() != null) {
            summaries.addAll(listing.getListingRequest().getFlowFileSummaries());
        }
        
        // Always delete the listing request to avoid leaks
        queueApi.deleteListingRequest(connectionId, listingId);
        
        return summaries;
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
        List<org.giwi.nifi.tester.model.FlowFileSummaryDTO> flowFiles = listFlowFiles(connectionId);
        for (org.giwi.nifi.tester.model.FlowFileSummaryDTO ff : flowFiles) {
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

    /**
     * Inject a flow file with the given content into an input port
     */
    public void injectFlowFile(String inputPortId, String content) throws Exception {
        System.out.println("DEBUG: Starting injectFlowFile");
        client.addDefaultHeader("x-nifi-site-to-site-protocol-version", "1");
        
        DataTransferApi dataTransferApi = new DataTransferApi(client);

        System.out.println("DEBUG: Calling createPortTransaction");
        // Create a transaction and get the ID from the Location header
        org.springframework.http.ResponseEntity<org.giwi.nifi.tester.model.TransactionResultEntity> response = 
            dataTransferApi.createPortTransactionWithHttpInfo("input-ports", inputPortId, null);
            
        String location = response.getHeaders().getFirst("Location");
        if (location == null) {
            throw new Exception("Failed to get transaction location from NiFi");
        }
        String transactionId = location.substring(location.lastIndexOf('/') + 1);
        System.out.println("DEBUG: Transaction ID = " + transactionId);

        // Format content as Site-To-Site V1 DataPacket
        byte[] contentBytes = content.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.DataOutputStream dos = new java.io.DataOutputStream(baos);
        // 0 attributes
        dos.writeInt(0);
        // Content length
        dos.writeLong(contentBytes.length);
        // Content bytes
        dos.write(contentBytes);
        dos.flush();

        System.out.println("DEBUG: Calling receiveFlowFiles");
        // Send the flow file content
        dataTransferApi.receiveFlowFiles(inputPortId, transactionId, baos.toByteArray());
        System.out.println("DEBUG: Returned from receiveFlowFiles");

        System.out.println("DEBUG: Calling commitInputPortTransaction");
        // Commit the transaction (12 = CONFIRM_TRANSACTION)
        dataTransferApi.commitInputPortTransaction(12, inputPortId, transactionId, null);
        System.out.println("DEBUG: Returned from commitInputPortTransaction");
    }

    /**
     * Results from a pipeline deployment operation.
     * Contains success status, message, and created process group ID.
     */
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

    /**
     * Closes the PipelineTester and cleans up resources.
     * Logs out from NiFi if an access token exists.
     */
    @Override
    public void close() {
        if (accessToken != null) {
            try {
                AccessApi accessApi = new AccessApi(client);
                accessApi.logOut();
                log.info("Logged out from NiFi");
            } catch (Exception e) {
                log.warn("Failed to logout from NiFi: {}", e.getMessage());
            }
            accessToken = null;
        }
    }

    /**
     * Command-line interface to deploy a pipeline to a running NiFi instance.
     *
     * Usage: java -jar nifi-tester.jar [options]
     *   or: ./gradlew run --args='--url https://localhost:8443/nifi-api --user admin --pass admin1234567 --file pipeline.yaml'
     *
     * Options:
     *   --url <url>       NiFi API URL (default: https://localhost:8443/nifi-api)
     *   --user <username> NiFi username (default: admin)
     *   --pass <password> NiFi password (default: admin)
     *   --file <path>     Path to YAML pipeline file (required for deploy)
     *   --parent <id>     Parent process group ID (default: root)
     *   --convert <path>  Convert NiFi JSON export to YAML (output to stdout or file)
     *   --output <path>   Output file for --convert (optional, defaults to stdout)
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        String url = "https://localhost:8443/nifi-api";
        String user = "admin";
        String pass = "admin";
        String file = null;
        String parent = "root";
        String convertFile = null;
        String outputFile = null;
        boolean dryRunCli = false;

        // Load config file first
        ConfigFile configFile = new ConfigFile();
        Map<String, String> config = configFile.load();
        url = config.getOrDefault("url", url);
        user = config.getOrDefault("user", user);
        pass = config.getOrDefault("pass", pass);

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--url":
                    if (i + 1 < args.length) url = args[++i];
                    break;
                case "--user":
                    if (i + 1 < args.length) user = args[++i];
                    break;
                case "--pass":
                    if (i + 1 < args.length) pass = args[++i];
                    break;
                case "--file":
                    if (i + 1 < args.length) file = args[++i];
                    break;
                case "--parent":
                    if (i + 1 < args.length) parent = args[++i];
                    break;
                case "--convert":
                    if (i + 1 < args.length) convertFile = args[++i];
                    break;
                case "--output":
                    if (i + 1 < args.length) outputFile = args[++i];
                    break;
                case "--dry-run":
                    dryRunCli = true;
                    break;
                case "--config":
                    if (i + 1 < args.length) {
                        // Save config
                        String[] parts = args[++i].split(",");
                        if (parts.length >= 3) {
                            try {
                                configFile.save(parts[0], parts[1], parts[2]);
                                System.out.println("Config saved to ~/.nifi-tester.yml");
                            } catch (IOException e) {
                                System.err.println("Failed to save config: " + e.getMessage());
                            }
                            return;
                        }
                    }
                    break;
                case "--help":
                case "-h":
                    printUsage();
                    return;
            }
        }

        // Handle convert command
        if (convertFile != null) {
            try {
                PipelineConverter converter = new PipelineConverter();

                // Check for dry-run mode
                if (dryRunCli) {
                    System.out.println("Dry-run: Would convert " + convertFile + " to YAML format");
                    System.out.println("Use without --dry-run to perform actual conversion");
                } else {
                    String yaml = converter.convertNiFiJsonToYaml(new java.io.File(convertFile));

                    if (outputFile != null) {
                        java.nio.file.Files.writeString(java.nio.file.Paths.get(outputFile), yaml);
                        System.out.println("Converted successfully: " + convertFile + " -> " + outputFile);
                    } else {
                        System.out.println(yaml);
                    }
                }
            } catch (Exception e) {
                System.err.println("Conversion failed: " + e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
            return;
        }

        // Handle export command
        String exportPgId = null;
        for (int i = 0; i < args.length; i++) {
            if ("--export".equals(args[i]) && i + 1 < args.length && !args[i + 1].startsWith("--")) {
                exportPgId = args[++i];
                break;
            }
        }
        if (exportPgId != null) {
            try {
                PipelineExporter exporter = new PipelineExporter(url, user, pass);
                String yaml = exporter.exportProcessGroup(exportPgId);
                if (outputFile != null) {
                    java.nio.file.Files.writeString(java.nio.file.Paths.get(outputFile), yaml);
                    System.out.println("Exported successfully: " + exportPgId + " -> " + outputFile);
                } else {
                    System.out.println(yaml);
                }
                exporter.close();
            } catch (Exception e) {
                System.err.println("Export failed: " + e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
            return;
        }

        // Handle status command
        String statusPgId = null;
        for (int i = 0; i < args.length; i++) {
            if ("--status".equals(args[i]) && i + 1 < args.length) {
                statusPgId = args[++i];
                break;
            }
        }
        if (statusPgId != null) {
            try {
                PipelineTester tester = new PipelineTester(url, user, pass);
                org.giwi.nifi.tester.api.ProcessGroupsApi pgApi =
                    new org.giwi.nifi.tester.api.ProcessGroupsApi(tester.getClient());
                var pgEntity = pgApi.getProcessGroup(statusPgId);
                if (pgEntity != null && pgEntity.getComponent() != null) {
                    System.out.println("Process Group: " + pgEntity.getComponent().getName());
                    System.out.println("ID: " + statusPgId);
                    System.out.println("Running Count: " + pgEntity.getComponent().getRunningCount());
                } else {
                    System.out.println("Process group not found: " + statusPgId);
                }
                tester.close();
            } catch (Exception e) {
                System.err.println("Status check failed: " + e.getMessage());
                System.exit(1);
            }
            return;
        }

        if (file == null) {
            System.err.println("Error: --file argument is required for deployment");
            printUsage();
            System.exit(1);
        }

        try {
            System.out.println("Deploying pipeline to NiFi...");
            System.out.println("  URL: " + url);
            System.out.println("  File: " + file);
            System.out.println("  Parent Group: " + parent);

            PipelineTester tester = new PipelineTester(url, user, pass);
            PipelineTesterResult result = tester.deployPipeline(new java.io.File(file), parent);

            if (result.isSuccess()) {
                System.out.println("Pipeline deployed successfully!");
                System.out.println("Process Group ID: " + result.getProcessGroupId());
            } else {
                System.err.println("Deployment failed: " + result.getMessage());
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Prints the command-line usage help message.
     */
    private static void printUsage() {
        System.out.println("Usage: java -jar nifi-tester.jar [options]");
        System.out.println("");
        System.out.println("Options:");
        System.out.println("  --url <url>       NiFi API URL (default: https://localhost:8443/nifi-api)");
        System.out.println("  --user <username> NiFi username (default: admin)");
        System.out.println("  --pass <password> NiFi password (default: admin)");
        System.out.println("  --file <path>     Path to YAML pipeline file (for deployment)");
        System.out.println("  --parent <id>     Parent process group ID (default: root)");
        System.out.println("  --convert <path>  Convert NiFi JSON export to YAML format");
        System.out.println("  --export <id>     Export process group to YAML");
        System.out.println("  --status <id>     Show process group status");
        System.out.println("  --output <path>   Output file for --convert/--export (default: stdout)");
        System.out.println("  --config <url,user,pass> Save config to ~/.nifi-tester.yml");
        System.out.println("  --dry-run         Validate without deploying or converting");
        System.out.println("  --help, -h         Show this help message");
        System.out.println("");
        System.out.println("Config file: ~/.nifi-tester.yml (auto-loaded if exists)");
    }
}
