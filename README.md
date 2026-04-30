# NiFi Tester

Java client library for Apache NiFi REST API with YAML pipeline support.

## Features

- **NiFi API Client**: Auto-generated REST client from OpenAPI spec
- **Pipeline Converter**: Convert YAML pipelines to NiFi API format
- **Pipeline Tester**: Deploy and test pipelines on remote NiFi instances
- **Test Infrastructure**: JUnit 5 integration with `@NiFiConnection` annotation

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

# Options
#   --url <url>       NiFi API URL (default: https://localhost:8443/nifi-api)
#   --user <username> NiFi username (default: admin)
#   --pass <password> NiFi password (default: admin)
#   --file <path>     Path to YAML pipeline file (required)
#   --parent <id>     Parent process group ID (default: root)
```

### Java API

```java
// Connect to NiFi
PipelineTester tester = new PipelineTester(
    "http://localhost:8080/nifi-api",
    "admin",
    "password"
);

// Deploy pipeline from YAML
PipelineTester.PipelineTesterResult result = tester.deployPipeline(
    new File("pipeline.yaml"),
    "root"
);

if (result.isSuccess()) {
    System.out.println("Created: " + result.getProcessGroupId());
    
    // Get process group
    var group = tester.getProcessGroup(result.getProcessGroupId());
    
    // Delete when done
    tester.deleteProcessGroup(result.getProcessGroupId());
}
```

## Pipeline YAML Format

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