package org.giwi.nifi.client.api;

import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.invoker.BaseApi;

import org.giwi.nifi.client.model.SnippetEntity;

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
public class SnippetsApi extends BaseApi {

    public SnippetsApi() {
        super(new ApiClient());
    }

    public SnippetsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Creates a snippet. The snippet will be automatically discarded if not used in a subsequent request after 1 minute.
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param snippetEntity The snippet configuration details. (required)
     * @return SnippetEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SnippetEntity createSnippet(SnippetEntity snippetEntity) throws RestClientException {
        return createSnippetWithHttpInfo(snippetEntity).getBody();
    }

    /**
     * Creates a snippet. The snippet will be automatically discarded if not used in a subsequent request after 1 minute.
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param snippetEntity The snippet configuration details. (required)
     * @return ResponseEntity&lt;SnippetEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SnippetEntity> createSnippetWithHttpInfo(SnippetEntity snippetEntity) throws RestClientException {
        Object localVarPostBody = snippetEntity;
        
        // verify the required parameter 'snippetEntity' is set
        if (snippetEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'snippetEntity' when calling createSnippet");
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

        ParameterizedTypeReference<SnippetEntity> localReturnType = new ParameterizedTypeReference<SnippetEntity>() {};
        return apiClient.invokeAPI("/snippets", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes the components in a snippet and discards the snippet
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The snippet id. (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return SnippetEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SnippetEntity deleteSnippet(String id, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return deleteSnippetWithHttpInfo(id, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes the components in a snippet and discards the snippet
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The snippet id. (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;SnippetEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SnippetEntity> deleteSnippetWithHttpInfo(String id, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteSnippet");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "disconnectedNodeAcknowledged", disconnectedNodeAcknowledged));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<SnippetEntity> localReturnType = new ParameterizedTypeReference<SnippetEntity>() {};
        return apiClient.invokeAPI("/snippets/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Move&#39;s the components in this Snippet into a new Process Group and discards the snippet
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The snippet id. (required)
     * @param snippetEntity The snippet configuration details. (required)
     * @return SnippetEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SnippetEntity updateSnippet(String id, SnippetEntity snippetEntity) throws RestClientException {
        return updateSnippetWithHttpInfo(id, snippetEntity).getBody();
    }

    /**
     * Move&#39;s the components in this Snippet into a new Process Group and discards the snippet
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The snippet id. (required)
     * @param snippetEntity The snippet configuration details. (required)
     * @return ResponseEntity&lt;SnippetEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SnippetEntity> updateSnippetWithHttpInfo(String id, SnippetEntity snippetEntity) throws RestClientException {
        Object localVarPostBody = snippetEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateSnippet");
        }
        
        // verify the required parameter 'snippetEntity' is set
        if (snippetEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'snippetEntity' when calling updateSnippet");
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

        ParameterizedTypeReference<SnippetEntity> localReturnType = new ParameterizedTypeReference<SnippetEntity>() {};
        return apiClient.invokeAPI("/snippets/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
