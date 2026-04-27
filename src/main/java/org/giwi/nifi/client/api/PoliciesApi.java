package org.giwi.nifi.client.api;

import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.invoker.BaseApi;

import org.giwi.nifi.client.model.AccessPolicyEntity;
import org.giwi.nifi.client.model.ClientIdParameter;
import org.giwi.nifi.client.model.LongParameter;

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
public class PoliciesApi extends BaseApi {

    public PoliciesApi() {
        super(new ApiClient());
    }

    public PoliciesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Creates an access policy
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param accessPolicyEntity The access policy configuration details. (required)
     * @return AccessPolicyEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AccessPolicyEntity createAccessPolicy(AccessPolicyEntity accessPolicyEntity) throws RestClientException {
        return createAccessPolicyWithHttpInfo(accessPolicyEntity).getBody();
    }

    /**
     * Creates an access policy
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param accessPolicyEntity The access policy configuration details. (required)
     * @return ResponseEntity&lt;AccessPolicyEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AccessPolicyEntity> createAccessPolicyWithHttpInfo(AccessPolicyEntity accessPolicyEntity) throws RestClientException {
        Object localVarPostBody = accessPolicyEntity;
        
        // verify the required parameter 'accessPolicyEntity' is set
        if (accessPolicyEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'accessPolicyEntity' when calling createAccessPolicy");
        }
        

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

        ParameterizedTypeReference<AccessPolicyEntity> localReturnType = new ParameterizedTypeReference<AccessPolicyEntity>() {};
        return apiClient.invokeAPI("/policies", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets an access policy
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The access policy id. (required)
     * @return AccessPolicyEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AccessPolicyEntity getAccessPolicy(String id) throws RestClientException {
        return getAccessPolicyWithHttpInfo(id).getBody();
    }

    /**
     * Gets an access policy
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The access policy id. (required)
     * @return ResponseEntity&lt;AccessPolicyEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AccessPolicyEntity> getAccessPolicyWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAccessPolicy");
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

        ParameterizedTypeReference<AccessPolicyEntity> localReturnType = new ParameterizedTypeReference<AccessPolicyEntity>() {};
        return apiClient.invokeAPI("/policies/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets an access policy for the specified action and resource
     * Will return the effective policy if no component specific policy exists for the specified action and resource. Must have Read permissions to the policy with the desired action and resource. Permissions for the policy that is returned will be indicated in the response. This means the client could be authorized to get the policy for a given component but the effective policy may be inherited from an ancestor Process Group. If the client does not have permissions to that policy, the response will not include the policy and the permissions in the response will be marked accordingly. If the client does not have permissions to the policy of the desired action and resource a 403 response will be returned.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param action The request action. (required)
     * @param resource The resource of the policy. (required)
     * @return AccessPolicyEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AccessPolicyEntity getAccessPolicyForResource(String action, String resource) throws RestClientException {
        return getAccessPolicyForResourceWithHttpInfo(action, resource).getBody();
    }

    /**
     * Gets an access policy for the specified action and resource
     * Will return the effective policy if no component specific policy exists for the specified action and resource. Must have Read permissions to the policy with the desired action and resource. Permissions for the policy that is returned will be indicated in the response. This means the client could be authorized to get the policy for a given component but the effective policy may be inherited from an ancestor Process Group. If the client does not have permissions to that policy, the response will not include the policy and the permissions in the response will be marked accordingly. If the client does not have permissions to the policy of the desired action and resource a 403 response will be returned.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param action The request action. (required)
     * @param resource The resource of the policy. (required)
     * @return ResponseEntity&lt;AccessPolicyEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AccessPolicyEntity> getAccessPolicyForResourceWithHttpInfo(String action, String resource) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'action' is set
        if (action == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'action' when calling getAccessPolicyForResource");
        }
        
        // verify the required parameter 'resource' is set
        if (resource == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resource' when calling getAccessPolicyForResource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("action", action);
        uriVariables.put("resource", resource);

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

        ParameterizedTypeReference<AccessPolicyEntity> localReturnType = new ParameterizedTypeReference<AccessPolicyEntity>() {};
        return apiClient.invokeAPI("/policies/{action}/{resource}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes an access policy
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The access policy id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return AccessPolicyEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AccessPolicyEntity removeAccessPolicy(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return removeAccessPolicyWithHttpInfo(id, version, clientId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes an access policy
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The access policy id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;AccessPolicyEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AccessPolicyEntity> removeAccessPolicyWithHttpInfo(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling removeAccessPolicy");
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

        ParameterizedTypeReference<AccessPolicyEntity> localReturnType = new ParameterizedTypeReference<AccessPolicyEntity>() {};
        return apiClient.invokeAPI("/policies/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a access policy
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The access policy id. (required)
     * @param accessPolicyEntity The access policy configuration details. (required)
     * @return AccessPolicyEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AccessPolicyEntity updateAccessPolicy(String id, AccessPolicyEntity accessPolicyEntity) throws RestClientException {
        return updateAccessPolicyWithHttpInfo(id, accessPolicyEntity).getBody();
    }

    /**
     * Updates a access policy
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The access policy id. (required)
     * @param accessPolicyEntity The access policy configuration details. (required)
     * @return ResponseEntity&lt;AccessPolicyEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AccessPolicyEntity> updateAccessPolicyWithHttpInfo(String id, AccessPolicyEntity accessPolicyEntity) throws RestClientException {
        Object localVarPostBody = accessPolicyEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateAccessPolicy");
        }
        
        // verify the required parameter 'accessPolicyEntity' is set
        if (accessPolicyEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'accessPolicyEntity' when calling updateAccessPolicy");
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

        ParameterizedTypeReference<AccessPolicyEntity> localReturnType = new ParameterizedTypeReference<AccessPolicyEntity>() {};
        return apiClient.invokeAPI("/policies/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
