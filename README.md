# NiFi Tester

Java client library for Apache NiFi REST API with YAML pipeline support.

## Features

- **NiFi API Client**: Auto-generated REST client from OpenAPI spec
- **Pipeline Converter**: Convert YAML pipelines to NiFi API format
- **Pipeline Tester**: Deploy and test pipelines on remote NiFi instances
- **Test Infrastructure**: JUnit 5 integration with `@NiFiConnection` annotation
- **CLI Support**: Deploy pipelines from command line
- **Dry-Run Mode**: Validate pipelines without deploying
- **Retry Logic**: Automatic retry for transient API failures
- **Controller Services**: Support for JDBC, MongoDB, etc.
- **AutoCloseable**: Use with try-with-resources for auto-cleanup

## Installation

```bash
# Build
./gradlew build

# Run tests
./gradlew test

# Run with custom NiFi
./gradlew test -Dnifi.url=http://localhost:8080/nifi-api -Dnifi.user=admin -Dnifi.pass=password
```

## Quick Start

### Command-Line Deployment

```bash
# Build the project
./gradlew build

# Deploy a pipeline to a running NiFi instance
./gradlew run --args='--url https://localhost:8443/nifi-api --user admin --pass admin1234567 --file pipeline.yaml'

# Or use the generated scripts directly
./build/install/nifi-tester/bin/nifi-tester --url https://localhost:8443/nifi-api --file pipeline.yaml

# Convert a NiFi JSON export to YAML format
./gradlew run --args='--convert nifi-export.json'
./gradlew run --args='--convert nifi-export.json --output pipeline.yaml'

# Options
#   --url <url>       NiFi API URL (default: https://localhost:8443/nifi-api)
#   --user <username> NiFi username (default: admin)
#   --pass <password> NiFi password (default: admin)
#   --file <path>     Path to YAML pipeline file (for deployment)
#   --parent <id>     Parent process group ID (default: root)
#   --convert <path>  Convert NiFi JSON export to YAML format
#   --output <path>   Output file for --convert (default: stdout)
#   --dry-run         Validate without deploying (optional)
```

### Java API

```java
// Connect to NiFi
PipelineTester tester = new PipelineTester(
    "http://localhost:8080/nifi-api",
    "admin",
    "password"
);

// Enable dry-run mode (validate without deploying)
tester.setDryRun(true);

// Deploy pipeline from YAML
PipelineTester.PipelineTesterResult result = tester.deployPipeline(
    new File("pipeline.yaml"),
    "root"
);

// Use with try-with-resources for auto-cleanup
try (PipelineTester tester = new PipelineTester(url, username, password)) {
    PipelineTesterResult result = tester.deployPipeline(yamlFile, "root");
    if (result.isSuccess()) {
        System.out.println("Created: " + result.getProcessGroupId());
    }
} // Auto-closes and cleans up

// Or manually
if (result.isSuccess()) {
    System.out.println("Created: " + result.getProcessGroupId());
    
    // Get process group
    var group = tester.getProcessGroup(result.getProcessGroupId());
    
    // Delete when done
    tester.deleteProcessGroup(result.getProcessGroupId());
}
```

## Pipeline YAML Format

Full YAML schema with all supported fields:

```yaml
name: My Pipeline              # (Required) Pipeline name
parentGroupId: root             # (Optional) Parent process group ID (default: "root")

processors:                       # (Optional) List of processors
  - name: GenerateFlowFile        # (Required) Unique processor name
    type: org.apache.nifi.processors.standard.GenerateFlowFile  # (Required) Processor type
    x: 100                       # (Optional) X coordinate (default: 0)
    y: 100                       # (Optional) Y coordinate (default: 0)
    schedulingStrategy: TIMER_DRIVEN  # (Optional) TIMER_DRIVEN, CRON_DRIVEN, PRIMARY_NODE_ONLY
    schedulingPeriod: "1 sec"        # (Optional) e.g., "1 sec", "5 mins"
    concurrentlySchedulableTaskCount: 1 # (Optional) Number of concurrent tasks
    runDurationMillis: 0           # (Optional) Run duration in milliseconds
    state: STOPPED                 # (Optional) STOPPED or RUNNING (default: STOPPED)
    autoTerminatedRelationships:      # (Optional) Relationships to auto-terminate
      - success
    properties:                    # (Optional) Processor-specific properties
      File Size: 1KB
      Text: Hello World

connections:                       # (Optional) List of connections
  - name: To Log                  # (Optional) Connection name
    sourceId: ${GenerateFlowFile}   # (Required) Source processor name (use ${name})
    destinationId: ${LogAttribute}    # (Required) Destination processor name
    relationships:                  # (Required) Relationships to connect
      - success
    flowFileExpiration: "0 ms"        # (Optional) Default: "0 ms"
    backPressureDataSizeThreshold: "1 GB" # (Optional) Default: "1 GB"
    backPressureObjectThreshold: 10000    # (Optional) Default: 10000
    x: 200                         # (Optional) Connection label X position
    y: 150                         # (Optional) Connection label Y position
    bends:                          # (Optional) Control points for connection line
      - x: 150
        y: 125
      - x: 175
        y: 140

funnels:                           # (Optional) List of funnels
  - name: MyFunnel
    x: 200
    y: 200

inputPorts:                        # (Optional) Input ports
  - name: Input
    x: 50
    y: 100

outputPorts:                       # (Optional) Output ports
  - name: Output
    x: 400
    y: 100

processGroups:                      # (Optional) Nested process groups
  - name: SubProcessGroup
    x: 300
    y: 300

remoteProcessGroups:                 # (Optional) Remote process groups
  - name: RemoteGroup
    targetUris: http://remote-nifi:8080/nifi-api
    x: 500
    y: 500
```

Example:

```yaml
name: My Pipeline
parentGroupId: root

processors:
  - name: GenerateFlowFile
    type: org.apache.nifi.processors.standard.GenerateFlowFile
    x: 100
    y: 100
    properties:
      File Size: 1KB
      Text: Hello World

  - name: LogAttribute
    type: org.apache.nifi.processors.standard.LogAttribute
    x: 300
    y: 100

connections:
  - name: To Log
    sourceId: ${GenerateFlowFile}
    destinationId: ${LogAttribute}
    relationships:
      - success
```

## Testing

### Unit Tests

```java
PipelineConverter converter = new PipelineConverter();

Map<String, Object> result = converter.convertFromYaml(new File("pipeline.yaml"));
assertNotNull(result.get("processors"));
```

### Integration Tests
```java
@NiFiConnection(url = "https://localhost:8443/nifi-api", user = "admin", password = "admin1234567")
class MyIntegrationTest {

    @Test
    void testDeploy() {
        PipelineTester tester = new PipelineTester(getClass());
        PipelineTesterResult result = tester.deployPipeline(new File("pipeline.yaml"), "root");
        assertTrue(result.isSuccess());
    }
}
```

### Run Tests

```bash
# Unit tests only (fast, no NiFi required)
./gradlew test

# Integration tests (requires running NiFi instance)
./gradlew integrationTest

# Integration tests with custom NiFi
./gradlew integrationTest -Dnifi.url=https://localhost:8443/nifi-api -Dnifi.user=admin -Dnifi.pass=admin1234567

# Specific test class
./gradlew test --tests "org.giwi.nifi.client.PipelineConverterTest"
./gradlew integrationTest --tests "org.giwi.nifi.client.NiFiIntegrationTest"
./gradlew integrationTest --tests "org.giwi.nifi.client.PipelineIntegrationTest"
```

## Project Structure

```
src/main/java/org/giwi/nifi/client/
├── PipelineConverter.java      # YAML to NiFi API converter
├── PipelineTester.java         # NiFi deployment client
├── NiFiConnection.java         # @NiFiConnection annotation
├── api/                       # Auto-generated API clients
├── model/                     # Generated API models
└── invoker/                  # API client infrastructure

src/test/java/org/giwi/nifi/client/
├── PipelineConverterTest.java  # Converter unit tests
├── SamplePipelineTest.java    # YAML sample tests
├── NiFiIntegrationTest.java   # NiFi integration tests
├── PipelineIntegrationTest.java # Pipeline integration tests
└── NiFiConnectionExtension.java # JUnit extension

src/test/resources/pipelines/
├── sample-generate-pipeline.yaml      # Single processor test
├── sample-deployment-pipeline.yaml   # Complex pipeline with 6 processors
└── test-pipeline-with-ports.yaml      # Pipeline with input/output ports
```

## Dependencies

- Java 17
- Spring Boot 3.4.1 (Web, JSON)
- Jackson (YAML, JSR310, Nullable)
- OpenAPI Generator 7.14.0
- JUnit 5 (Jupiter)
- Gradle 9.4.1+

## Configuration

| System Property | Default | Description |
|-----------------|---------|-------------|
| `nifi.url` | `http://localhost:8080/nifi-api` | NiFi API URL |
| `nifi.user` | `admin` | Username |
| `nifi.pass` | `admin` | Password |

## License

Apache License 2.0