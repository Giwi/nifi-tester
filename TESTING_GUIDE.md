# Testing Guide for NiFi Pipeline Deployment

## Prerequisites

### 1. Start NiFi Server

#### Option A: Docker (Recommended)

```bash
docker run -d \
  --name nifi \
  -p 8443:8443 \
  -e NIFI_WEB_HTTPS_PORT=8443 \
  -e NIFI_SECURITY_USER_LOGIN_IDENTITY_PROVIDER=single-user-provider \
  -e NIFI_SECURITY_USER_AUTHORIZER=single-user-authorizer \
  -e SINGLE_USER_CREDENTIALS_USERNAME=admin \
  -e SINGLE_USER_CREDENTIALS_PASSWORD=admin1234567 \
  apache/nifi:2.9.0
```

#### Option B: Local Installation

1. Download NiFi 2.9.0 from https://nifi.apache.org/download.html
2. Extract and navigate to nifi directory
3. Configure `conf/nifi.properties`:
   ```
   nifi.web.https.port=8443
   nifi.security.user.login.identity.provider=single-user-provider
   ```
4. Configure `conf/authorizers.xml` for single-user authentication
5. Run `./bin/nifi.sh start`

### 2. Verify NiFi is Running

```bash
# Check NiFi is accessible
curl -k https://localhost:8443/nifi-api/system-diagnostics

# Should return JSON with system diagnostics
```

## Running Integration Tests

### Test 1: Simple Pipeline (1 Processor)

```bash
./gradlew test --tests "NiFiIntegrationTest.testDeployPipeline"
```

Expected Result:
- Process group created
- Single GenerateFlowFile processor created
- Process group can be queried and deleted

### Test 2: Complex Pipeline (6 Processors, 5 Connections)

```bash
./gradlew test --tests "NiFiIntegrationTest.testDeployComprehensivePipeline"
```

Expected Result:
- Process group created
- All 6 processors created
- All 5 connections created successfully
- Process group can be queried

## Debugging Connection Errors

If connections fail with HTTP 500 error:

### 1. Check NiFi Logs

```bash
# Docker
docker logs nifi | tail -100

# Local installation
tail -100 logs/nifi-app.log
```

### 2. Inspect Created Connections

Via NiFi UI:
- Navigate to https://localhost:8443/nifi
- Find the deployed process group
- Check processor positions and connection routing

### 3. Manual Connection Test

Use curl to test connection creation:

```bash
# First, create two processors
# Then attempt to connect them manually via REST API

curl -k -X POST https://localhost:8443/nifi-api/process-groups/{pgId}/connections \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "revision": {"version": 0},
    "component": {
      "source": {"id": "{procId1}", "type": "PROCESSOR"},
      "destination": {"id": "{procId2}", "type": "PROCESSOR"},
      "selectedRelationships": ["success"],
      "backPressureDataSizeThreshold": "1 GB",
      "backPressureObjectThreshold": 10000,
      "flowFileExpiration": "0 ms",
      "parentGroupId": "{pgId}"
    }
  }'
```

## Known Issues and Solutions

### Issue: Self-Signed Certificate Error

**Solution**: The code includes SSL certificate validation bypass for self-signed certificates. Ensure `ApiClient.buildRestTemplate()` has the custom TrustManager.

### Issue: 500 Error on Connection Creation

**Possible Causes**:
1. Processor IDs are invalid or don't exist
2. Connection fields are missing or incorrectly formatted
3. NiFi API expects additional fields (e.g., bends, prioritizers)
4. Parent group ID is incorrect

**Solution**: Check the following in code order:
1. Verify processor IDs are correctly resolved in `processorIdMap`
2. Check ConnectionDTO has source and destination objects with correct ID and type
3. Add bends list if needed: `dto.setBends(new ArrayList<>())`
4. Verify parentGroupId matches the actual process group

### Issue: Relationships Not Recognized

**Solution**: Ensure relationship names match exactly with what the processor defines:
- GenerateFlowFile: "success"
- ExtractText: "success"
- RouteOnContent: "matched", "unmatched"
- LogAttribute: (any relationship)

## Next Steps After Testing

1. If all tests pass, mark the feature as complete
2. If tests fail, debug using the guide above
3. Add more complex pipeline scenarios (nested groups, ports, remote groups)
4. Add error handling and retry logic
5. Implement deleteProcessGroup() properly (currently mocked)
