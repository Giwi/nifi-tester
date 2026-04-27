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
@NiFiConnection(url = "http://localhost:8080/nifi-api", user = "admin", password = "admin")
class MyIntegrationTest {
    
    @Test
    void testDeploy() {
        PipelineTester tester = new PipelineTester(url, user, pass);
        PipelineTesterResult result = tester.deployPipeline(new File("pipeline.yaml"));
        assertTrue(result.isSuccess());
    }
}
```

### Run Tests

```bash
# All unit tests
./gradlew test

# Integration tests with NiFi
./gradlew test -Dnifi.url=https://localhost:8443/nifi-api -Dnifi.user=admin -Dnifi.pass=admin

# Specific test class
./gradlew test --tests "org.giwi.nifi.client.SamplePipelineTest"
```

## Project Structure

```
src/main/java/org/giwi/nifi/client/
├── PipelineConverter.java      # YAML to NiFi API converter
├── PipelineTester.java         # NiFi deployment client
└── model/                       # Generated API models

src/test/java/org/giwi/nifi/client/
├── NiFiConnection.java          # @NiFiConnection annotation
├── PipelineConverterTest.java  # Converter unit tests
├── SamplePipelineTest.java    # YAML sample tests
└── NiFiIntegrationTest.java   # NiFi integration tests

src/test/resources/pipelines/
└── sample-generate-pipeline.yaml  # Sample pipeline
```

## Dependencies

- Spring Boot 3.4.1 (Web, JSON)
- Jackson (YAML, JSR310, Nullable)
- OpenAPI Generator 7.14.0
- JUnit 5

## Configuration

| System Property | Default | Description |
|-----------------|---------|-------------|
| `nifi.url` | `http://localhost:8080/nifi-api` | NiFi API URL |
| `nifi.user` | `admin` | Username |
| `nifi.pass` | `admin` | Password |

## License

Apache License 2.0