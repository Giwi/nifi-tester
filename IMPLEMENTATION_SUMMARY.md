# NiFi Pipeline Deployment Java Client - Implementation Summary

## Overview

This project provides a Java client library for deploying Apache NiFi pipelines programmatically using the REST API. It includes YAML-based pipeline definitions and automatic processor bundle configuration.

## Completed Features

### 1. **API Client Generation**
- Auto-generated REST client from NiFi OpenAPI 2.9.0 specification
- Models for ProcessorDTO, ConnectionDTO, ProcessGroupDTO, etc.
- ApiClient with custom TrustManager for self-signed HTTPS certificates

### 2. **YAML Pipeline Converter**
- Converts YAML pipeline definitions to internal NiFi API format
- Supports processors, connections, funnels, ports, process groups, and remote process groups
- Automatic UUID generation for entities
- Default values for optional fields

### 3. **Pipeline Deployment**
- `PipelineTester` class for pipeline deployment and management
- Support for multiple constructor patterns:
  - URL-based: `new PipelineTester(url, username, password)`
  - Annotation-based: `new PipelineTester(testClass)` with `@NiFiConnection` annotation
- Login and token management with Bearer authentication
- Process group creation and management
- Pipeline deployment from YAML files

### 4. **Processor Creation**
- Automatic bundle detection based on processor type
- Position support (x, y coordinates)
- Property configuration via YAML
- Correct ProcessorDTO formation with bundle information

### 5. **Connection Creation**
- Source and destination resolution from processor names
- Relationship configuration
- Connection properties (flowFileExpiration, backPressure settings)
- Position information for visual layout

### 6. **Process Group Management**
- Create nested process groups
- Support for parent group IDs
- Proper hierarchy management

### 7. **Testing Utilities**
- Start/stop processors and process groups
- Wait for processors to become idle
- List and download flow file contents
- Assertion methods for pipeline output verification
- Queue management (clear, query)

## Code Architecture

```
src/main/java/org/giwi/nifi/client/
├── PipelineTester.java              # Main deployment orchestrator
├── PipelineConverter.java           # YAML to API format conversion
├── NiFiConnection.java              # Configuration annotation
├── api/                             # Auto-generated API clients
│   ├── ProcessGroupsApi.java
│   ├── ProcessorsApi.java
│   ├── ConnectionsApi.java
│   ├── AccessApi.java
│   └── ...
├── model/                           # Auto-generated DTOs
│   ├── ProcessorDTO.java
│   ├── ConnectionDTO.java
│   ├── ProcessGroupDTO.java
│   └── ...
└── invoker/
    └── ApiClient.java               # REST client with SSL config
```

## Key Implementation Details

### SSL/HTTPS Configuration (ApiClient.buildRestTemplate)
- Disables SSL certificate validation for self-signed certs
- Required because NiFi uses self-signed certificates on port 8443
- Custom TrustManager that trusts all certificates

### Processor Bundle Resolution (getBundleForProcessorType)
- Jolt processors: `nifi-jolt-nar`
- JSON processors: `nifi-json-nar`
- Standard/default: `nifi-standard-nar`
- Group: `org.apache.nifi`
- Version: `2.9.0`

### Processor ID Mapping
- Processor names from YAML are mapped to actual NiFi processor IDs
- Used for connection source/destination resolution
- Map built during processor creation phase

### Connection Entity Creation
- Properly unwraps wrapper format from converter
- Sets all required fields on ConnectionDTO
- Handles type conversion for numeric thresholds
- Configures ConnectableDTO objects with proper types

## YAML Format

### Basic Structure
```yaml
name: Pipeline Name
parentGroupId: root

processors:
  - name: ProcessorName
    type: org.apache.nifi.processors.standard.ProcessorType
    x: 100
    y: 100
    properties:
      Property1: value1
      Property2: value2
    schedulingStrategy: TIMER_DRIVEN
    schedulingPeriod: 1 sec
    autoTerminatedRelationships:
      - success

connections:
  - name: ConnectionName
    sourceId: ${SourceProcessorName}
    destinationId: ${DestProcessorName}
    relationships:
      - success
    flowFileExpiration: 0 ms
    backPressureDataSizeThreshold: 1 GB
    backPressureObjectThreshold: 10000
    x: 150
    y: 100

funnels:
  - name: FunnelName
    x: 200
    y: 200

inputPorts:
  - name: InputPort
    x: 50
    y: 100

outputPorts:
  - name: OutputPort
    x: 400
    y: 100

processGroups:
  - name: SubProcessGroup
    x: 300
    y: 300

remoteProcessGroups:
  - name: RemoteGroup
    targetUris: http://remote-nifi:8080/nifi-api
```

### Variable Resolution
- Processor references use template syntax: `${ProcessorName}`
- Resolved to actual processor IDs during deployment
- Allows YAML to be human-readable while maintaining proper linking

## Testing

### Unit Tests
- `PipelineConverterTest`: YAML conversion logic
- `SamplePipelineTest`: Tests with sample YAML files
- Tests for processors, connections, ports, process groups, funnels
- Run with: `./gradlew test`

### Integration Tests
- `NiFiIntegrationTest`: End-to-end deployment with single processor
- `PipelineIntegrationTest`: Comprehensive pipeline tests with multiple processors
- Requires NiFi server running at https://localhost:8443
- Credentials: admin / admin1234567
- Tests process group creation, processor deployment, connections, and cleanup
- Run with: `./gradlew integrationTest`

### Test Pipelines
- `sample-generate-pipeline.yaml`: Simple pipeline with 2 processors
- `sample-deployment-pipeline.yaml`: Complex pipeline with 6 processors and connections
- `groovy-script-pipeline.yaml`: Pipeline with Groovy script and input/output ports
- `test-pipeline-with-ports.yaml`: Pipeline with input/output ports

See TESTING_GUIDE.md for detailed testing instructions.

## Deployment Flow

```
1. Load YAML File
   ↓
2. Convert YAML to API Format (PipelineConverter)
   ↓
3. Create Process Group
   ↓
4. Create Processors (in order)
   ├── Store processor name → ID mapping
   └── Set bundle, position, properties
   ↓
5. Create Connections
   ├── Resolve source/destination IDs
   ├── Configure relationships
   └── Set connection properties
   ↓
6. Create Funnels (if needed)
   ↓
7. Return Result with Process Group ID
```

## Error Handling

Current implementation:
- Basic try-catch with result status
- Error message returned to caller
- Stack trace printed to console (for debugging)

Recommended improvements:
- Specific exception types for different failures
- Retry logic for transient failures
- Detailed validation before API calls
- Rollback on partial failure

## Known Limitations

1. **deleteProcessGroup()** - Currently mocked (returns true without deletion), but `deleteProcessGroupReal()` is available for actual deletion
2. **Process Group Cleanup** - No automatic cleanup on failure
3. **Connection Bends** - Not yet supported (uses empty list)
4. **Load Balancing** - Load balance strategy fields not yet configured
5. **Prioritizers** - Connection prioritizers not yet implemented
6. **Versioned Components** - Versioned component IDs not yet handled

## Recently Completed

1. ✅ Fixed Java compilation (Java 17 compatibility)
2. ✅ Improved connection creation with all fields
3. ✅ Tested with actual NiFi server
4. ✅ Fixed connection creation (no more 500 errors)
5. ✅ Added integration test configuration (separated from unit tests)
6. ✅ Added support for input/output ports in pipelines
7. ✅ Added Groovy script pipeline test example

## Next Steps

### Short Term
1. ✅ Fix Java compilation (Java 17 compatibility)
2. ✅ Improve connection creation with all fields
3. ✅ Test with actual NiFi server
4. ✅ Test connection creation and verify it doesn't return 500 error
5. Switch deleteProcessGroup() to use real implementation

### Medium Term
1. Add support for nested process groups
2. Add error recovery and rollback logic
3. Support for input/output ports
4. Remote process group support
5. Implement connection bends support

### Long Term
1. Template management
2. Versioning and snapshots
3. Pipeline validation before deployment
4. Performance optimization for large pipelines
5. WebSocket support for real-time updates

## Dependencies

- Java 17
- Spring Boot 3.4.1 (Web, JSON)
- Jackson (YAML, JSR310, Nullable)
- OpenAPI Generator 7.14.0
- JUnit 5 (Jupiter)
- Gradle 9.4.1+
- javax.annotation-api 1.3.2

## Building

```bash
# Build
./gradlew build

# Run tests
./gradlew test

# Run specific test
./gradlew test --tests "NiFiIntegrationTest.testDeployPipeline"

# Build JAR
./gradlew jar
```

## Usage Example

```java
// Create tester with annotation
@NiFiConnection(url = "https://localhost:8443/nifi-api", user = "admin", password = "admin1234567")
class MyTest {
    
    @Test
    void deployPipeline() throws Exception {
        PipelineTester tester = new PipelineTester(getClass());
        
        PipelineTesterResult result = tester.deployPipeline(
            new File("pipeline.yaml"),
            "root"
        );
        
        if (result.isSuccess()) {
            System.out.println("Deployed: " + result.getProcessGroupId());
        } else {
            System.out.println("Failed: " + result.getMessage());
        }
    }
}
```

## Documentation Files

- `README.md`: Project overview and quick start
- `TESTING_GUIDE.md`: NiFi setup and testing procedures
- `IMPLEMENTATION_SUMMARY.md` (this file): Detailed implementation information
