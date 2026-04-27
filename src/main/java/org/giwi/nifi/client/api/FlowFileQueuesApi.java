package org.giwi.nifi.client.api;

import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.invoker.BaseApi;

import org.giwi.nifi.client.model.ClientIdParameter;
import org.giwi.nifi.client.model.DropRequestEntity;
import org.giwi.nifi.client.model.FlowFileEntity;
import org.giwi.nifi.client.model.ListingRequestEntity;

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
public class FlowFileQueuesApi extends BaseApi {

    public FlowFileQueuesApi() {
        super(new ApiClient());
    }

    public FlowFileQueuesApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Creates a request to drop the contents of the queue in this connection.
     * 
     * <p><b>202</b> - The request has been accepted. A HTTP response header will contain the URI where the response can be polled.
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @return DropRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DropRequestEntity createDropRequest(String id) throws RestClientException {
        return createDropRequestWithHttpInfo(id).getBody();
    }

    /**
     * Creates a request to drop the contents of the queue in this connection.
     * 
     * <p><b>202</b> - The request has been accepted. A HTTP response header will contain the URI where the response can be polled.
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @return ResponseEntity&lt;DropRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DropRequestEntity> createDropRequestWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createDropRequest");
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

        ParameterizedTypeReference<DropRequestEntity> localReturnType = new ParameterizedTypeReference<DropRequestEntity>() {};
        return apiClient.invokeAPI("/flowfile-queues/{id}/drop-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Lists the contents of the queue in this connection.
     * 
     * <p><b>202</b> - The request has been accepted. A HTTP response header will contain the URI where the response can be polled.
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @return ListingRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListingRequestEntity createFlowFileListing(String id) throws RestClientException {
        return createFlowFileListingWithHttpInfo(id).getBody();
    }

    /**
     * Lists the contents of the queue in this connection.
     * 
     * <p><b>202</b> - The request has been accepted. A HTTP response header will contain the URI where the response can be polled.
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @return ResponseEntity&lt;ListingRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListingRequestEntity> createFlowFileListingWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createFlowFileListing");
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

        ParameterizedTypeReference<ListingRequestEntity> localReturnType = new ParameterizedTypeReference<ListingRequestEntity>() {};
        return apiClient.invokeAPI("/flowfile-queues/{id}/listing-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Cancels and/or removes a request to list the contents of this connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param listingRequestId The listing request id. (required)
     * @return ListingRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListingRequestEntity deleteListingRequest(String id, String listingRequestId) throws RestClientException {
        return deleteListingRequestWithHttpInfo(id, listingRequestId).getBody();
    }

    /**
     * Cancels and/or removes a request to list the contents of this connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param listingRequestId The listing request id. (required)
     * @return ResponseEntity&lt;ListingRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListingRequestEntity> deleteListingRequestWithHttpInfo(String id, String listingRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteListingRequest");
        }
        
        // verify the required parameter 'listingRequestId' is set
        if (listingRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'listingRequestId' when calling deleteListingRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("listing-request-id", listingRequestId);

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

        ParameterizedTypeReference<ListingRequestEntity> localReturnType = new ParameterizedTypeReference<ListingRequestEntity>() {};
        return apiClient.invokeAPI("/flowfile-queues/{id}/listing-requests/{listing-request-id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the content for a FlowFile in a Connection.
     * 
     * <p><b>200</b>
     * <p><b>206</b> - Partial Content with range of bytes requested
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>416</b> - Requested Range Not Satisfiable based on bytes requested
     * @param id The connection id. (required)
     * @param flowfileUuid The flowfile uuid. (required)
     * @param range Range of bytes requested (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param clusterNodeId The id of the node where the content exists if clustered. (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object downloadFlowFileContent(String id, String flowfileUuid, String range, ClientIdParameter clientId, String clusterNodeId) throws RestClientException {
        return downloadFlowFileContentWithHttpInfo(id, flowfileUuid, range, clientId, clusterNodeId).getBody();
    }

    /**
     * Gets the content for a FlowFile in a Connection.
     * 
     * <p><b>200</b>
     * <p><b>206</b> - Partial Content with range of bytes requested
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>416</b> - Requested Range Not Satisfiable based on bytes requested
     * @param id The connection id. (required)
     * @param flowfileUuid The flowfile uuid. (required)
     * @param range Range of bytes requested (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param clusterNodeId The id of the node where the content exists if clustered. (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> downloadFlowFileContentWithHttpInfo(String id, String flowfileUuid, String range, ClientIdParameter clientId, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling downloadFlowFileContent");
        }
        
        // verify the required parameter 'flowfileUuid' is set
        if (flowfileUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowfileUuid' when calling downloadFlowFileContent");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("flowfile-uuid", flowfileUuid);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        
        if (clientId != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clientId", clientId.getClientId()));
        }localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        if (range != null)
        localVarHeaderParams.add("Range", apiClient.parameterToString(range));

        final String[] localVarAccepts = { 
            "*/*"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<Object> localReturnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI("/flowfile-queues/{id}/flowfiles/{flowfile-uuid}/content", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the current status of a drop request for the specified connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param dropRequestId The drop request id. (required)
     * @return DropRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DropRequestEntity getDropRequest(String id, String dropRequestId) throws RestClientException {
        return getDropRequestWithHttpInfo(id, dropRequestId).getBody();
    }

    /**
     * Gets the current status of a drop request for the specified connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param dropRequestId The drop request id. (required)
     * @return ResponseEntity&lt;DropRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DropRequestEntity> getDropRequestWithHttpInfo(String id, String dropRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDropRequest");
        }
        
        // verify the required parameter 'dropRequestId' is set
        if (dropRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dropRequestId' when calling getDropRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("drop-request-id", dropRequestId);

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

        ParameterizedTypeReference<DropRequestEntity> localReturnType = new ParameterizedTypeReference<DropRequestEntity>() {};
        return apiClient.invokeAPI("/flowfile-queues/{id}/drop-requests/{drop-request-id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a FlowFile from a Connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param flowfileUuid The flowfile uuid. (required)
     * @param clusterNodeId The id of the node where the content exists if clustered. (optional)
     * @return FlowFileEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowFileEntity getFlowFile(String id, String flowfileUuid, String clusterNodeId) throws RestClientException {
        return getFlowFileWithHttpInfo(id, flowfileUuid, clusterNodeId).getBody();
    }

    /**
     * Gets a FlowFile from a Connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param flowfileUuid The flowfile uuid. (required)
     * @param clusterNodeId The id of the node where the content exists if clustered. (optional)
     * @return ResponseEntity&lt;FlowFileEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowFileEntity> getFlowFileWithHttpInfo(String id, String flowfileUuid, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFlowFile");
        }
        
        // verify the required parameter 'flowfileUuid' is set
        if (flowfileUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowfileUuid' when calling getFlowFile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("flowfile-uuid", flowfileUuid);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<FlowFileEntity> localReturnType = new ParameterizedTypeReference<FlowFileEntity>() {};
        return apiClient.invokeAPI("/flowfile-queues/{id}/flowfiles/{flowfile-uuid}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the current status of a listing request for the specified connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param listingRequestId The listing request id. (required)
     * @return ListingRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListingRequestEntity getListingRequest(String id, String listingRequestId) throws RestClientException {
        return getListingRequestWithHttpInfo(id, listingRequestId).getBody();
    }

    /**
     * Gets the current status of a listing request for the specified connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param listingRequestId The listing request id. (required)
     * @return ResponseEntity&lt;ListingRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListingRequestEntity> getListingRequestWithHttpInfo(String id, String listingRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getListingRequest");
        }
        
        // verify the required parameter 'listingRequestId' is set
        if (listingRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'listingRequestId' when calling getListingRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("listing-request-id", listingRequestId);

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

        ParameterizedTypeReference<ListingRequestEntity> localReturnType = new ParameterizedTypeReference<ListingRequestEntity>() {};
        return apiClient.invokeAPI("/flowfile-queues/{id}/listing-requests/{listing-request-id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Cancels and/or removes a request to drop the contents of this connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param dropRequestId The drop request id. (required)
     * @return DropRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DropRequestEntity removeDropRequest(String id, String dropRequestId) throws RestClientException {
        return removeDropRequestWithHttpInfo(id, dropRequestId).getBody();
    }

    /**
     * Cancels and/or removes a request to drop the contents of this connection.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param dropRequestId The drop request id. (required)
     * @return ResponseEntity&lt;DropRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DropRequestEntity> removeDropRequestWithHttpInfo(String id, String dropRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling removeDropRequest");
        }
        
        // verify the required parameter 'dropRequestId' is set
        if (dropRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dropRequestId' when calling removeDropRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("drop-request-id", dropRequestId);

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

        ParameterizedTypeReference<DropRequestEntity> localReturnType = new ParameterizedTypeReference<DropRequestEntity>() {};
        return apiClient.invokeAPI("/flowfile-queues/{id}/drop-requests/{drop-request-id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
