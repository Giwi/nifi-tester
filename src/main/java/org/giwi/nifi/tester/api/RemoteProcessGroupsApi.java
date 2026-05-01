package org.giwi.nifi.tester.api;

import org.giwi.nifi.tester.invoker.ApiClient;
import org.giwi.nifi.tester.invoker.BaseApi;

import org.giwi.nifi.tester.model.ClearBulletinsRequestEntity;
import org.giwi.nifi.tester.model.ClearBulletinsResultEntity;
import org.giwi.nifi.tester.model.ClientIdParameter;
import org.giwi.nifi.tester.model.ComponentStateEntity;
import org.giwi.nifi.tester.model.LongParameter;
import org.giwi.nifi.tester.model.RemotePortRunStatusEntity;
import org.giwi.nifi.tester.model.RemoteProcessGroupEntity;
import org.giwi.nifi.tester.model.RemoteProcessGroupPortEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-04-27T21:33:06.905180638+02:00[Europe/Paris]", comments = "Generator version: 7.14.0")
public class RemoteProcessGroupsApi extends BaseApi {

    public RemoteProcessGroupsApi() {
        super(new ApiClient());
    }

    public RemoteProcessGroupsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Clears bulletins for a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param clearBulletinsRequestEntity The clear bulletin request. (required)
     * @return ClearBulletinsResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ClearBulletinsResultEntity clearBulletins6(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        return clearBulletins6WithHttpInfo(id, clearBulletinsRequestEntity).getBody();
    }

    /**
     * Clears bulletins for a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param clearBulletinsRequestEntity The clear bulletin request. (required)
     * @return ResponseEntity&lt;ClearBulletinsResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ClearBulletinsResultEntity> clearBulletins6WithHttpInfo(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        Object localVarPostBody = clearBulletinsRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearBulletins6");
        }
        
        // verify the required parameter 'clearBulletinsRequestEntity' is set
        if (clearBulletinsRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'clearBulletinsRequestEntity' when calling clearBulletins6");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ClearBulletinsResultEntity> localReturnType = new ParameterizedTypeReference<ClearBulletinsResultEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}/bulletins/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @return RemoteProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupEntity getRemoteProcessGroup(String id) throws RestClientException {
        return getRemoteProcessGroupWithHttpInfo(id).getBody();
    }

    /**
     * Gets a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupEntity> getRemoteProcessGroupWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRemoteProcessGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the state for a RemoteProcessGroup
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The processor id. (required)
     * @return ComponentStateEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentStateEntity getState3(String id) throws RestClientException {
        return getState3WithHttpInfo(id).getBody();
    }

    /**
     * Gets the state for a RemoteProcessGroup
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The processor id. (required)
     * @return ResponseEntity&lt;ComponentStateEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentStateEntity> getState3WithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getState3");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ComponentStateEntity> localReturnType = new ParameterizedTypeReference<ComponentStateEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}/state", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return RemoteProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupEntity removeRemoteProcessGroup(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return removeRemoteProcessGroupWithHttpInfo(id, version, clientId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;RemoteProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupEntity> removeRemoteProcessGroupWithHttpInfo(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling removeRemoteProcessGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        
        if (version != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "long", version.getLong()));
        }
        if (clientId != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clientId", clientId.getClientId()));
        }localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "disconnectedNodeAcknowledged", disconnectedNodeAcknowledged));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param remoteProcessGroupEntity The remote process group configuration details. (required)
     * @return RemoteProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupEntity updateRemoteProcessGroup(String id, RemoteProcessGroupEntity remoteProcessGroupEntity) throws RestClientException {
        return updateRemoteProcessGroupWithHttpInfo(id, remoteProcessGroupEntity).getBody();
    }

    /**
     * Updates a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param remoteProcessGroupEntity The remote process group configuration details. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupEntity> updateRemoteProcessGroupWithHttpInfo(String id, RemoteProcessGroupEntity remoteProcessGroupEntity) throws RestClientException {
        Object localVarPostBody = remoteProcessGroupEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRemoteProcessGroup");
        }
        
        // verify the required parameter 'remoteProcessGroupEntity' is set
        if (remoteProcessGroupEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'remoteProcessGroupEntity' when calling updateRemoteProcessGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a remote port
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param portId The remote process group port id. (required)
     * @param remoteProcessGroupPortEntity The remote process group port configuration details. (required)
     * @return RemoteProcessGroupPortEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupPortEntity updateRemoteProcessGroupInputPort(String id, String portId, RemoteProcessGroupPortEntity remoteProcessGroupPortEntity) throws RestClientException {
        return updateRemoteProcessGroupInputPortWithHttpInfo(id, portId, remoteProcessGroupPortEntity).getBody();
    }

    /**
     * Updates a remote port
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param portId The remote process group port id. (required)
     * @param remoteProcessGroupPortEntity The remote process group port configuration details. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupPortEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupPortEntity> updateRemoteProcessGroupInputPortWithHttpInfo(String id, String portId, RemoteProcessGroupPortEntity remoteProcessGroupPortEntity) throws RestClientException {
        Object localVarPostBody = remoteProcessGroupPortEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRemoteProcessGroupInputPort");
        }
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling updateRemoteProcessGroupInputPort");
        }
        
        // verify the required parameter 'remoteProcessGroupPortEntity' is set
        if (remoteProcessGroupPortEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'remoteProcessGroupPortEntity' when calling updateRemoteProcessGroupInputPort");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("port-id", portId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupPortEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupPortEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}/input-ports/{port-id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates run status of a remote input port
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param portId The remote process group port id. (required)
     * @param remotePortRunStatusEntity The remote process group port run status details. (required)
     * @return RemoteProcessGroupPortEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupPortEntity updateRemoteProcessGroupInputPortRunStatus(String id, String portId, RemotePortRunStatusEntity remotePortRunStatusEntity) throws RestClientException {
        return updateRemoteProcessGroupInputPortRunStatusWithHttpInfo(id, portId, remotePortRunStatusEntity).getBody();
    }

    /**
     * Updates run status of a remote input port
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param portId The remote process group port id. (required)
     * @param remotePortRunStatusEntity The remote process group port run status details. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupPortEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupPortEntity> updateRemoteProcessGroupInputPortRunStatusWithHttpInfo(String id, String portId, RemotePortRunStatusEntity remotePortRunStatusEntity) throws RestClientException {
        Object localVarPostBody = remotePortRunStatusEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRemoteProcessGroupInputPortRunStatus");
        }
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling updateRemoteProcessGroupInputPortRunStatus");
        }
        
        // verify the required parameter 'remotePortRunStatusEntity' is set
        if (remotePortRunStatusEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'remotePortRunStatusEntity' when calling updateRemoteProcessGroupInputPortRunStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("port-id", portId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupPortEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupPortEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}/input-ports/{port-id}/run-status", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a remote port
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param portId The remote process group port id. (required)
     * @param remoteProcessGroupPortEntity The remote process group port configuration details. (required)
     * @return RemoteProcessGroupPortEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupPortEntity updateRemoteProcessGroupOutputPort(String id, String portId, RemoteProcessGroupPortEntity remoteProcessGroupPortEntity) throws RestClientException {
        return updateRemoteProcessGroupOutputPortWithHttpInfo(id, portId, remoteProcessGroupPortEntity).getBody();
    }

    /**
     * Updates a remote port
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param portId The remote process group port id. (required)
     * @param remoteProcessGroupPortEntity The remote process group port configuration details. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupPortEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupPortEntity> updateRemoteProcessGroupOutputPortWithHttpInfo(String id, String portId, RemoteProcessGroupPortEntity remoteProcessGroupPortEntity) throws RestClientException {
        Object localVarPostBody = remoteProcessGroupPortEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRemoteProcessGroupOutputPort");
        }
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling updateRemoteProcessGroupOutputPort");
        }
        
        // verify the required parameter 'remoteProcessGroupPortEntity' is set
        if (remoteProcessGroupPortEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'remoteProcessGroupPortEntity' when calling updateRemoteProcessGroupOutputPort");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("port-id", portId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupPortEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupPortEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}/output-ports/{port-id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates run status of a remote output port
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param portId The remote process group port id. (required)
     * @param remotePortRunStatusEntity The remote process group port run status details. (required)
     * @return RemoteProcessGroupPortEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupPortEntity updateRemoteProcessGroupOutputPortRunStatus(String id, String portId, RemotePortRunStatusEntity remotePortRunStatusEntity) throws RestClientException {
        return updateRemoteProcessGroupOutputPortRunStatusWithHttpInfo(id, portId, remotePortRunStatusEntity).getBody();
    }

    /**
     * Updates run status of a remote output port
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param portId The remote process group port id. (required)
     * @param remotePortRunStatusEntity The remote process group port run status details. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupPortEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupPortEntity> updateRemoteProcessGroupOutputPortRunStatusWithHttpInfo(String id, String portId, RemotePortRunStatusEntity remotePortRunStatusEntity) throws RestClientException {
        Object localVarPostBody = remotePortRunStatusEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRemoteProcessGroupOutputPortRunStatus");
        }
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling updateRemoteProcessGroupOutputPortRunStatus");
        }
        
        // verify the required parameter 'remotePortRunStatusEntity' is set
        if (remotePortRunStatusEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'remotePortRunStatusEntity' when calling updateRemoteProcessGroupOutputPortRunStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("port-id", portId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupPortEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupPortEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}/output-ports/{port-id}/run-status", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates run status of a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param remotePortRunStatusEntity The remote process group run status. (required)
     * @return RemoteProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupEntity updateRemoteProcessGroupRunStatus(String id, RemotePortRunStatusEntity remotePortRunStatusEntity) throws RestClientException {
        return updateRemoteProcessGroupRunStatusWithHttpInfo(id, remotePortRunStatusEntity).getBody();
    }

    /**
     * Updates run status of a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param remotePortRunStatusEntity The remote process group run status. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupEntity> updateRemoteProcessGroupRunStatusWithHttpInfo(String id, RemotePortRunStatusEntity remotePortRunStatusEntity) throws RestClientException {
        Object localVarPostBody = remotePortRunStatusEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRemoteProcessGroupRunStatus");
        }
        
        // verify the required parameter 'remotePortRunStatusEntity' is set
        if (remotePortRunStatusEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'remotePortRunStatusEntity' when calling updateRemoteProcessGroupRunStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/{id}/run-status", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates run status of all remote process groups in a process group (recursively)
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param remotePortRunStatusEntity The remote process groups run status. (required)
     * @return RemoteProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupEntity updateRemoteProcessGroupRunStatuses(String id, RemotePortRunStatusEntity remotePortRunStatusEntity) throws RestClientException {
        return updateRemoteProcessGroupRunStatusesWithHttpInfo(id, remotePortRunStatusEntity).getBody();
    }

    /**
     * Updates run status of all remote process groups in a process group (recursively)
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param remotePortRunStatusEntity The remote process groups run status. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupEntity> updateRemoteProcessGroupRunStatusesWithHttpInfo(String id, RemotePortRunStatusEntity remotePortRunStatusEntity) throws RestClientException {
        Object localVarPostBody = remotePortRunStatusEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRemoteProcessGroupRunStatuses");
        }
        
        // verify the required parameter 'remotePortRunStatusEntity' is set
        if (remotePortRunStatusEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'remotePortRunStatusEntity' when calling updateRemoteProcessGroupRunStatuses");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupEntity>() {};
        return apiClient.invokeAPI("/remote-process-groups/process-group/{id}/run-status", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }

    @Override
    public <T> ResponseEntity<T> invokeAPI(String url, HttpMethod method, Object request, ParameterizedTypeReference<T> returnType) throws RestClientException {
        String localVarPath = url.replace(apiClient.getBasePath(), "");
        Object localVarPostBody = request;

        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
