package org.giwi.nifi.client;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.giwi.nifi.client.api.AccessApi;
import org.giwi.nifi.client.api.ConnectionsApi;
import org.giwi.nifi.client.api.FunnelsApi;
import org.giwi.nifi.client.api.ProcessGroupsApi;
import org.giwi.nifi.client.api.ProcessorsApi;
import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.model.BundleDTO;
import org.giwi.nifi.client.model.ConnectionEntity;
import org.giwi.nifi.client.model.ConnectableDTO;
import org.giwi.nifi.client.model.FunnelEntity;
import org.giwi.nifi.client.model.PositionDTO;
import org.giwi.nifi.client.model.ProcessGroupEntity;
import org.giwi.nifi.client.model.ProcessorEntity;
import org.giwi.nifi.client.model.RevisionDTO;
import org.giwi.nifi.client.NiFiConnection;

public class PipelineTester {
    private final ApiClient client;
    private final PipelineConverter converter;
    private String accessToken;

    public PipelineTester(String nifiUrl) {
        this.client = new ApiClient();
        this.client.setBasePath(nifiUrl);
        this.converter = new PipelineConverter();
    }

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

    public PipelineTester(String nifiUrl, String username, String password) throws Exception {
        this(nifiUrl);
        login(username, password);
    }

    public void login(String username, String password) throws Exception {
        AccessApi accessApi = new AccessApi(client);
        String token = accessApi.createAccessToken(password, username);
        this.accessToken = token;
        this.client.addDefaultHeader("Authorization", "Bearer " + token);
    }

    public PipelineTesterResult deployPipeline(File yamlFile) throws IOException {
        return deployPipeline(yamlFile, "root");
    }

    @SuppressWarnings("unchecked")
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
            String newPgId = createdPg.getComponent().getId();
            result.setProcessGroupId(newPgId);
            System.out.println("Created process group: " + newPgId);

             // Create processors
             if (pipelineData.containsKey("processors")) {
                 List<Map<String, Object>> processors = (List<Map<String, Object>>) pipelineData.get("processors");
                 System.out.println("Number of processor entries to create: " + processors.size());
                 for (Map<String, Object> procWrapper : processors) {
                     ProcessorEntity procEntity = createProcessorEntity(procWrapper, newPgId);
                     ProcessorEntity created = pgApi.createProcessor(newPgId, procEntity);
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
                    pos.setX(((Number) funnel.getOrDefault("x",0)).doubleValue());
                    pos.setY(((Number) funnel.getOrDefault("y",0)).doubleValue());
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
                         debugConnectionEntity(connEntity);
                         pgApi.createConnection(newPgId, connEntity);
                     }
                 }
             }

            result.setSuccess(true);
            result.setMessage("Pipeline deployed successfully: " + newPgId);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Deployment failed: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
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
            dto.getConfig().setProperties(props);
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

        System.out.println("Getting bundle for processor type: " + type);

        // Determine artifact based on processor type - check specific types first
        if (type != null) {
            String typeLower = type.toLowerCase();
            System.out.println("Type lowercase: " + typeLower);
            if (typeLower.contains("jolt")) {
                bundle.setArtifact("nifi-jolt-nar");
                System.out.println("Using jolt bundle: nifi-jolt-nar");
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

        System.out.println("Final bundle artifact: " + bundle.getArtifact());
        return bundle;
    }

     private ConnectionEntity createConnectionEntity(Map<String, Object> conn, String parentGroupId, Map<String, String> processorIdMap) {
         // Handle both flat YAML format and converted format
         Map<String, Object> connMap = conn;
         if (conn.containsKey("connection")) {
             connMap = (Map<String, Object>) conn.get("connection");
         }

         System.out.println("Connection map keys: " + connMap.keySet());

         ConnectionEntity entity = new ConnectionEntity();
         org.giwi.nifi.client.model.ConnectionDTO dto = new org.giwi.nifi.client.model.ConnectionDTO();

         dto.setParentGroupId(parentGroupId);

         // Resolve source and destination IDs
         String sourceName = extractIdFromRef((String) connMap.get("sourceId"));
         String destName = extractIdFromRef((String) connMap.get("destinationId"));

         String sourceId = processorIdMap.getOrDefault(sourceName, sourceName);
         String destId = processorIdMap.getOrDefault(destName, destName);

         System.out.println("Creating connection: " + connMap.get("name"));
         System.out.println("  Source: " + sourceName + " -> " + sourceId);
         System.out.println("  Dest: " + destName + " -> " + destId);

         ConnectableDTO source = new ConnectableDTO();
         source.setId(sourceId);
         source.setType(ConnectableDTO.TypeEnum.PROCESSOR);
         dto.setSource(source);

         ConnectableDTO dest = new ConnectableDTO();
         dest.setId(destId);
         dest.setType(ConnectableDTO.TypeEnum.PROCESSOR);
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
                         System.out.println("Warning: Invalid backPressureObjectThreshold value: " + threshold);
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
        org.giwi.nifi.client.model.ProcessGroupEntity entity = new org.giwi.nifi.client.model.ProcessGroupEntity();
        org.giwi.nifi.client.model.ProcessGroupDTO dto = new org.giwi.nifi.client.model.ProcessGroupDTO();
        dto.setId(processGroupId);
        dto.setName("Mock Process Group");
        entity.setComponent(dto);
        return entity;
    }

    public boolean deleteProcessGroup(String processGroupId) {
         return true;
     }

     private void debugConnectionEntity(ConnectionEntity connEntity) {
         System.out.println("\n=== Connection Entity Debug ===");
         org.giwi.nifi.client.model.ConnectionDTO dto = connEntity.getComponent();
         System.out.println("Parent Group ID: " + dto.getParentGroupId());
         System.out.println("Name: " + dto.getName());
         if (dto.getSource() != null) {
             System.out.println("Source ID: " + dto.getSource().getId() + ", Type: " + dto.getSource().getType());
         } else {
             System.out.println("Source: null");
         }
         if (dto.getDestination() != null) {
             System.out.println("Dest ID: " + dto.getDestination().getId() + ", Type: " + dto.getDestination().getType());
         } else {
             System.out.println("Dest: null");
         }
         System.out.println("Selected Relationships: " + dto.getSelectedRelationships());
         System.out.println("FlowFile Expiration: " + dto.getFlowFileExpiration());
         System.out.println("Back Pressure Data Size: " + dto.getBackPressureDataSizeThreshold());
         System.out.println("Back Pressure Object Threshold: " + dto.getBackPressureObjectThreshold());
         System.out.println("Position: " + (dto.getPosition() != null ? "(" + dto.getPosition().getX() + ", " + dto.getPosition().getY() + ")" : "null"));
         System.out.println("Bends: " + (dto.getBends() != null ? dto.getBends().size() : "null"));
         System.out.println("Revision Version: " + (connEntity.getRevision() != null ? connEntity.getRevision().getVersion() : "null"));
         System.out.println("============================\n");
     }

     public String getAccessToken() {
         return accessToken;
     }

    public ApiClient getClient() {
        return client;
    }

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