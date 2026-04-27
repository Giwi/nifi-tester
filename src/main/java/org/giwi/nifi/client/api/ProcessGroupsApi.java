package org.giwi.nifi.client.api;

import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.invoker.BaseApi;

import org.giwi.nifi.client.model.ClientIdParameter;
import org.giwi.nifi.client.model.ConnectionEntity;
import org.giwi.nifi.client.model.ConnectionsEntity;
import org.giwi.nifi.client.model.ControllerServiceEntity;
import org.giwi.nifi.client.model.CopyRequestEntity;
import org.giwi.nifi.client.model.CopyResponseEntity;
import org.giwi.nifi.client.model.CopySnippetRequestEntity;
import org.giwi.nifi.client.model.DropRequestEntity;
import org.giwi.nifi.client.model.FlowComparisonEntity;
import org.giwi.nifi.client.model.FlowEntity;
import org.giwi.nifi.client.model.FunnelEntity;
import org.giwi.nifi.client.model.FunnelsEntity;
import org.giwi.nifi.client.model.InputPortsEntity;
import org.giwi.nifi.client.model.LabelEntity;
import org.giwi.nifi.client.model.LabelsEntity;
import org.giwi.nifi.client.model.LongParameter;
import org.giwi.nifi.client.model.OutputPortsEntity;
import org.giwi.nifi.client.model.PasteRequestEntity;
import org.giwi.nifi.client.model.PasteResponseEntity;
import org.giwi.nifi.client.model.PortEntity;
import org.giwi.nifi.client.model.ProcessGroupEntity;
import org.giwi.nifi.client.model.ProcessGroupImportEntity;
import org.giwi.nifi.client.model.ProcessGroupReplaceRequestEntity;
import org.giwi.nifi.client.model.ProcessGroupUploadEntity;
import org.giwi.nifi.client.model.ProcessGroupsEntity;
import org.giwi.nifi.client.model.ProcessorEntity;
import org.giwi.nifi.client.model.ProcessorsEntity;
import org.giwi.nifi.client.model.RemoteProcessGroupEntity;
import org.giwi.nifi.client.model.RemoteProcessGroupsEntity;

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
public class ProcessGroupsApi extends BaseApi {

    public ProcessGroupsApi() {
        super(new ApiClient());
    }

    public ProcessGroupsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Generates a copy response for the given copy request
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param copyRequestEntity The request including the components to be copied from the specified Process Group. (required)
     * @return CopyResponseEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CopyResponseEntity copy(String id, CopyRequestEntity copyRequestEntity) throws RestClientException {
        return copyWithHttpInfo(id, copyRequestEntity).getBody();
    }

    /**
     * Generates a copy response for the given copy request
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param copyRequestEntity The request including the components to be copied from the specified Process Group. (required)
     * @return ResponseEntity&lt;CopyResponseEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CopyResponseEntity> copyWithHttpInfo(String id, CopyRequestEntity copyRequestEntity) throws RestClientException {
        Object localVarPostBody = copyRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling copy");
        }
        
        // verify the required parameter 'copyRequestEntity' is set
        if (copyRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'copyRequestEntity' when calling copy");
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

        ParameterizedTypeReference<CopyResponseEntity> localReturnType = new ParameterizedTypeReference<CopyResponseEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/copy", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Copies a snippet and discards it.
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param copySnippetRequestEntity The copy snippet request. (required)
     * @return FlowEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowEntity copySnippet(String id, CopySnippetRequestEntity copySnippetRequestEntity) throws RestClientException {
        return copySnippetWithHttpInfo(id, copySnippetRequestEntity).getBody();
    }

    /**
     * Copies a snippet and discards it.
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param copySnippetRequestEntity The copy snippet request. (required)
     * @return ResponseEntity&lt;FlowEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowEntity> copySnippetWithHttpInfo(String id, CopySnippetRequestEntity copySnippetRequestEntity) throws RestClientException {
        Object localVarPostBody = copySnippetRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling copySnippet");
        }
        
        // verify the required parameter 'copySnippetRequestEntity' is set
        if (copySnippetRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'copySnippetRequestEntity' when calling copySnippet");
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

        ParameterizedTypeReference<FlowEntity> localReturnType = new ParameterizedTypeReference<FlowEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/snippet-instance", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a connection
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param connectionEntity The connection configuration details. (required)
     * @return ConnectionEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectionEntity createConnection(String id, ConnectionEntity connectionEntity) throws RestClientException {
        return createConnectionWithHttpInfo(id, connectionEntity).getBody();
    }

    /**
     * Creates a connection
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param connectionEntity The connection configuration details. (required)
     * @return ResponseEntity&lt;ConnectionEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectionEntity> createConnectionWithHttpInfo(String id, ConnectionEntity connectionEntity) throws RestClientException {
        Object localVarPostBody = connectionEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createConnection");
        }
        
        // verify the required parameter 'connectionEntity' is set
        if (connectionEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'connectionEntity' when calling createConnection");
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

        ParameterizedTypeReference<ConnectionEntity> localReturnType = new ParameterizedTypeReference<ConnectionEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/connections", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new controller service
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param controllerServiceEntity The controller service configuration details. (required)
     * @return ControllerServiceEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerServiceEntity createControllerService1(String id, ControllerServiceEntity controllerServiceEntity) throws RestClientException {
        return createControllerService1WithHttpInfo(id, controllerServiceEntity).getBody();
    }

    /**
     * Creates a new controller service
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param controllerServiceEntity The controller service configuration details. (required)
     * @return ResponseEntity&lt;ControllerServiceEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerServiceEntity> createControllerService1WithHttpInfo(String id, ControllerServiceEntity controllerServiceEntity) throws RestClientException {
        Object localVarPostBody = controllerServiceEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createControllerService1");
        }
        
        // verify the required parameter 'controllerServiceEntity' is set
        if (controllerServiceEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'controllerServiceEntity' when calling createControllerService1");
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

        ParameterizedTypeReference<ControllerServiceEntity> localReturnType = new ParameterizedTypeReference<ControllerServiceEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/controller-services", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a request to drop all FlowFiles of all connection queues in this process group.
     * 
     * <p><b>202</b> - The request has been accepted. An HTTP response header will contain the URI where the status can be polled.
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return DropRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DropRequestEntity createEmptyAllConnectionsRequest(String id) throws RestClientException {
        return createEmptyAllConnectionsRequestWithHttpInfo(id).getBody();
    }

    /**
     * Creates a request to drop all FlowFiles of all connection queues in this process group.
     * 
     * <p><b>202</b> - The request has been accepted. An HTTP response header will contain the URI where the status can be polled.
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;DropRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DropRequestEntity> createEmptyAllConnectionsRequestWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createEmptyAllConnectionsRequest");
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
        return apiClient.invokeAPI("/process-groups/{id}/empty-all-connections-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a funnel
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param funnelEntity The funnel configuration details. (required)
     * @return FunnelEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FunnelEntity createFunnel(String id, FunnelEntity funnelEntity) throws RestClientException {
        return createFunnelWithHttpInfo(id, funnelEntity).getBody();
    }

    /**
     * Creates a funnel
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param funnelEntity The funnel configuration details. (required)
     * @return ResponseEntity&lt;FunnelEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FunnelEntity> createFunnelWithHttpInfo(String id, FunnelEntity funnelEntity) throws RestClientException {
        Object localVarPostBody = funnelEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createFunnel");
        }
        
        // verify the required parameter 'funnelEntity' is set
        if (funnelEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'funnelEntity' when calling createFunnel");
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

        ParameterizedTypeReference<FunnelEntity> localReturnType = new ParameterizedTypeReference<FunnelEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/funnels", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates an input port
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param portEntity The input port configuration details. (required)
     * @return PortEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PortEntity createInputPort(String id, PortEntity portEntity) throws RestClientException {
        return createInputPortWithHttpInfo(id, portEntity).getBody();
    }

    /**
     * Creates an input port
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param portEntity The input port configuration details. (required)
     * @return ResponseEntity&lt;PortEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PortEntity> createInputPortWithHttpInfo(String id, PortEntity portEntity) throws RestClientException {
        Object localVarPostBody = portEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createInputPort");
        }
        
        // verify the required parameter 'portEntity' is set
        if (portEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portEntity' when calling createInputPort");
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

        ParameterizedTypeReference<PortEntity> localReturnType = new ParameterizedTypeReference<PortEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/input-ports", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a label
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param labelEntity The label configuration details. (required)
     * @return LabelEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public LabelEntity createLabel(String id, LabelEntity labelEntity) throws RestClientException {
        return createLabelWithHttpInfo(id, labelEntity).getBody();
    }

    /**
     * Creates a label
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param labelEntity The label configuration details. (required)
     * @return ResponseEntity&lt;LabelEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<LabelEntity> createLabelWithHttpInfo(String id, LabelEntity labelEntity) throws RestClientException {
        Object localVarPostBody = labelEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createLabel");
        }
        
        // verify the required parameter 'labelEntity' is set
        if (labelEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'labelEntity' when calling createLabel");
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

        ParameterizedTypeReference<LabelEntity> localReturnType = new ParameterizedTypeReference<LabelEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/labels", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates an output port
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param portEntity The output port configuration. (required)
     * @return PortEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PortEntity createOutputPort(String id, PortEntity portEntity) throws RestClientException {
        return createOutputPortWithHttpInfo(id, portEntity).getBody();
    }

    /**
     * Creates an output port
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param portEntity The output port configuration. (required)
     * @return ResponseEntity&lt;PortEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PortEntity> createOutputPortWithHttpInfo(String id, PortEntity portEntity) throws RestClientException {
        Object localVarPostBody = portEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createOutputPort");
        }
        
        // verify the required parameter 'portEntity' is set
        if (portEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portEntity' when calling createOutputPort");
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

        ParameterizedTypeReference<PortEntity> localReturnType = new ParameterizedTypeReference<PortEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/output-ports", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a process group
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupEntity The process group configuration details. (required)
     * @param parameterContextHandlingStrategy Handling Strategy controls whether to keep or replace Parameter Contexts (optional, default to KEEP_EXISTING)
     * @return ProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupEntity createProcessGroup(String id, ProcessGroupEntity processGroupEntity, String parameterContextHandlingStrategy) throws RestClientException {
        return createProcessGroupWithHttpInfo(id, processGroupEntity, parameterContextHandlingStrategy).getBody();
    }

    /**
     * Creates a process group
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupEntity The process group configuration details. (required)
     * @param parameterContextHandlingStrategy Handling Strategy controls whether to keep or replace Parameter Contexts (optional, default to KEEP_EXISTING)
     * @return ResponseEntity&lt;ProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupEntity> createProcessGroupWithHttpInfo(String id, ProcessGroupEntity processGroupEntity, String parameterContextHandlingStrategy) throws RestClientException {
        Object localVarPostBody = processGroupEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createProcessGroup");
        }
        
        // verify the required parameter 'processGroupEntity' is set
        if (processGroupEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processGroupEntity' when calling createProcessGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "parameterContextHandlingStrategy", parameterContextHandlingStrategy));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ProcessGroupEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/process-groups", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new processor
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processorEntity The processor configuration details. (required)
     * @return ProcessorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessorEntity createProcessor(String id, ProcessorEntity processorEntity) throws RestClientException {
        return createProcessorWithHttpInfo(id, processorEntity).getBody();
    }

    /**
     * Creates a new processor
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processorEntity The processor configuration details. (required)
     * @return ResponseEntity&lt;ProcessorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessorEntity> createProcessorWithHttpInfo(String id, ProcessorEntity processorEntity) throws RestClientException {
        Object localVarPostBody = processorEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createProcessor");
        }
        
        // verify the required parameter 'processorEntity' is set
        if (processorEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processorEntity' when calling createProcessor");
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

        ParameterizedTypeReference<ProcessorEntity> localReturnType = new ParameterizedTypeReference<ProcessorEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/processors", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new process group
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param remoteProcessGroupEntity The remote process group configuration details. (required)
     * @return RemoteProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupEntity createRemoteProcessGroup(String id, RemoteProcessGroupEntity remoteProcessGroupEntity) throws RestClientException {
        return createRemoteProcessGroupWithHttpInfo(id, remoteProcessGroupEntity).getBody();
    }

    /**
     * Creates a new process group
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param remoteProcessGroupEntity The remote process group configuration details. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupEntity> createRemoteProcessGroupWithHttpInfo(String id, RemoteProcessGroupEntity remoteProcessGroupEntity) throws RestClientException {
        Object localVarPostBody = remoteProcessGroupEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createRemoteProcessGroup");
        }
        
        // verify the required parameter 'remoteProcessGroupEntity' is set
        if (remoteProcessGroupEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'remoteProcessGroupEntity' when calling createRemoteProcessGroup");
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
        return apiClient.invokeAPI("/process-groups/{id}/remote-process-groups", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes the Replace Request with the given ID
     * Deletes the Replace Request with the given ID. After a request is created via a POST to /process-groups/{id}/replace-requests, it is expected that the client will properly clean up the request by DELETE&#39;ing it, once the Replace process has completed. If the request is deleted before the request completes, then the Replace request will finish the step that it is currently performing and then will cancel any subsequent steps. Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The ID of the Update Request (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ProcessGroupReplaceRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupReplaceRequestEntity deleteReplaceProcessGroupRequest(String id, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return deleteReplaceProcessGroupRequestWithHttpInfo(id, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes the Replace Request with the given ID
     * Deletes the Replace Request with the given ID. After a request is created via a POST to /process-groups/{id}/replace-requests, it is expected that the client will properly clean up the request by DELETE&#39;ing it, once the Replace process has completed. If the request is deleted before the request completes, then the Replace request will finish the step that it is currently performing and then will cancel any subsequent steps. Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The ID of the Update Request (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;ProcessGroupReplaceRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupReplaceRequestEntity> deleteReplaceProcessGroupRequestWithHttpInfo(String id, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteReplaceProcessGroupRequest");
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

        ParameterizedTypeReference<ProcessGroupReplaceRequestEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupReplaceRequestEntity>() {};
        return apiClient.invokeAPI("/process-groups/replace-requests/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a process group for download
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param includeReferencedServices If referenced services from outside the target group should be included (optional, default to false)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String exportProcessGroup(String id, Boolean includeReferencedServices) throws RestClientException {
        return exportProcessGroupWithHttpInfo(id, includeReferencedServices).getBody();
    }

    /**
     * Gets a process group for download
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param includeReferencedServices If referenced services from outside the target group should be included (optional, default to false)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> exportProcessGroupWithHttpInfo(String id, Boolean includeReferencedServices) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling exportProcessGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeReferencedServices", includeReferencedServices));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<String> localReturnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI("/process-groups/{id}/download", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all connections
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ConnectionsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectionsEntity getConnections(String id) throws RestClientException {
        return getConnectionsWithHttpInfo(id).getBody();
    }

    /**
     * Gets all connections
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;ConnectionsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectionsEntity> getConnectionsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnections");
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

        ParameterizedTypeReference<ConnectionsEntity> localReturnType = new ParameterizedTypeReference<ConnectionsEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/connections", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the current status of a drop all FlowFiles request.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param dropRequestId The drop request id. (required)
     * @return DropRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DropRequestEntity getDropAllFlowfilesRequest(String id, String dropRequestId) throws RestClientException {
        return getDropAllFlowfilesRequestWithHttpInfo(id, dropRequestId).getBody();
    }

    /**
     * Gets the current status of a drop all FlowFiles request.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param dropRequestId The drop request id. (required)
     * @return ResponseEntity&lt;DropRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DropRequestEntity> getDropAllFlowfilesRequestWithHttpInfo(String id, String dropRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDropAllFlowfilesRequest");
        }
        
        // verify the required parameter 'dropRequestId' is set
        if (dropRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dropRequestId' when calling getDropAllFlowfilesRequest");
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
        return apiClient.invokeAPI("/process-groups/{id}/empty-all-connections-requests/{drop-request-id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all funnels
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return FunnelsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FunnelsEntity getFunnels(String id) throws RestClientException {
        return getFunnelsWithHttpInfo(id).getBody();
    }

    /**
     * Gets all funnels
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;FunnelsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FunnelsEntity> getFunnelsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFunnels");
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

        ParameterizedTypeReference<FunnelsEntity> localReturnType = new ParameterizedTypeReference<FunnelsEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/funnels", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all input ports
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return InputPortsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public InputPortsEntity getInputPorts(String id) throws RestClientException {
        return getInputPortsWithHttpInfo(id).getBody();
    }

    /**
     * Gets all input ports
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;InputPortsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<InputPortsEntity> getInputPortsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getInputPorts");
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

        ParameterizedTypeReference<InputPortsEntity> localReturnType = new ParameterizedTypeReference<InputPortsEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/input-ports", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all labels
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return LabelsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public LabelsEntity getLabels(String id) throws RestClientException {
        return getLabelsWithHttpInfo(id).getBody();
    }

    /**
     * Gets all labels
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;LabelsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<LabelsEntity> getLabelsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getLabels");
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

        ParameterizedTypeReference<LabelsEntity> localReturnType = new ParameterizedTypeReference<LabelsEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/labels", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a list of local modifications to the Process Group since it was last synchronized with the Flow Registry
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return FlowComparisonEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowComparisonEntity getLocalModifications(String id) throws RestClientException {
        return getLocalModificationsWithHttpInfo(id).getBody();
    }

    /**
     * Gets a list of local modifications to the Process Group since it was last synchronized with the Flow Registry
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;FlowComparisonEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowComparisonEntity> getLocalModificationsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getLocalModifications");
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

        ParameterizedTypeReference<FlowComparisonEntity> localReturnType = new ParameterizedTypeReference<FlowComparisonEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/local-modifications", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all output ports
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return OutputPortsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OutputPortsEntity getOutputPorts(String id) throws RestClientException {
        return getOutputPortsWithHttpInfo(id).getBody();
    }

    /**
     * Gets all output ports
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;OutputPortsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OutputPortsEntity> getOutputPortsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getOutputPorts");
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

        ParameterizedTypeReference<OutputPortsEntity> localReturnType = new ParameterizedTypeReference<OutputPortsEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/output-ports", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupEntity getProcessGroup(String id) throws RestClientException {
        return getProcessGroupWithHttpInfo(id).getBody();
    }

    /**
     * Gets a process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;ProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupEntity> getProcessGroupWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessGroup");
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

        ParameterizedTypeReference<ProcessGroupEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all process groups
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ProcessGroupsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupsEntity getProcessGroups(String id) throws RestClientException {
        return getProcessGroupsWithHttpInfo(id).getBody();
    }

    /**
     * Gets all process groups
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;ProcessGroupsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupsEntity> getProcessGroupsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessGroups");
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

        ParameterizedTypeReference<ProcessGroupsEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupsEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/process-groups", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all processors
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param includeDescendantGroups Whether or not to include processors from descendant process groups (optional, default to false)
     * @return ProcessorsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessorsEntity getProcessors(String id, Boolean includeDescendantGroups) throws RestClientException {
        return getProcessorsWithHttpInfo(id, includeDescendantGroups).getBody();
    }

    /**
     * Gets all processors
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param includeDescendantGroups Whether or not to include processors from descendant process groups (optional, default to false)
     * @return ResponseEntity&lt;ProcessorsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessorsEntity> getProcessorsWithHttpInfo(String id, Boolean includeDescendantGroups) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessors");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDescendantGroups", includeDescendantGroups));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ProcessorsEntity> localReturnType = new ParameterizedTypeReference<ProcessorsEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/processors", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all remote process groups
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return RemoteProcessGroupsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupsEntity getRemoteProcessGroups(String id) throws RestClientException {
        return getRemoteProcessGroupsWithHttpInfo(id).getBody();
    }

    /**
     * Gets all remote process groups
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;RemoteProcessGroupsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupsEntity> getRemoteProcessGroupsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRemoteProcessGroups");
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

        ParameterizedTypeReference<RemoteProcessGroupsEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupsEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/remote-process-groups", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns the Replace Request with the given ID
     * Returns the Replace Request with the given ID. Once a Replace Request has been created by performing a POST to /process-groups/{id}/replace-requests, that request can subsequently be retrieved via this endpoint, and the request that is fetched will contain the updated state, such as percent complete, the current state of the request, and any failures. Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The ID of the Replace Request (required)
     * @return ProcessGroupReplaceRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupReplaceRequestEntity getReplaceProcessGroupRequest(String id) throws RestClientException {
        return getReplaceProcessGroupRequestWithHttpInfo(id).getBody();
    }

    /**
     * Returns the Replace Request with the given ID
     * Returns the Replace Request with the given ID. Once a Replace Request has been created by performing a POST to /process-groups/{id}/replace-requests, that request can subsequently be retrieved via this endpoint, and the request that is fetched will contain the updated state, such as percent complete, the current state of the request, and any failures. Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The ID of the Replace Request (required)
     * @return ResponseEntity&lt;ProcessGroupReplaceRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupReplaceRequestEntity> getReplaceProcessGroupRequestWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getReplaceProcessGroupRequest");
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

        ParameterizedTypeReference<ProcessGroupReplaceRequestEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupReplaceRequestEntity>() {};
        return apiClient.invokeAPI("/process-groups/replace-requests/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Imports a specified process group
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupUploadEntity The Process Group Upload import details (optional)
     * @return ProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupEntity importProcessGroup(String id, ProcessGroupUploadEntity processGroupUploadEntity) throws RestClientException {
        return importProcessGroupWithHttpInfo(id, processGroupUploadEntity).getBody();
    }

    /**
     * Imports a specified process group
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupUploadEntity The Process Group Upload import details (optional)
     * @return ResponseEntity&lt;ProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupEntity> importProcessGroupWithHttpInfo(String id, ProcessGroupUploadEntity processGroupUploadEntity) throws RestClientException {
        Object localVarPostBody = processGroupUploadEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling importProcessGroup");
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

        ParameterizedTypeReference<ProcessGroupEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/process-groups/import", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Initiate the Replace Request of a Process Group with the given ID
     * This will initiate the action of replacing a process group with the given process group. This can be a lengthy process, as it will stop any Processors and disable any Controller Services necessary to perform the action and then restart them. As a result, the endpoint will immediately return a ProcessGroupReplaceRequestEntity, and the process of replacing the flow will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /process-groups/replace-requests/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /process-groups/replace-requests/{requestId}. Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupImportEntity The process group replace request entity (required)
     * @return ProcessGroupReplaceRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupReplaceRequestEntity initiateReplaceProcessGroup(String id, ProcessGroupImportEntity processGroupImportEntity) throws RestClientException {
        return initiateReplaceProcessGroupWithHttpInfo(id, processGroupImportEntity).getBody();
    }

    /**
     * Initiate the Replace Request of a Process Group with the given ID
     * This will initiate the action of replacing a process group with the given process group. This can be a lengthy process, as it will stop any Processors and disable any Controller Services necessary to perform the action and then restart them. As a result, the endpoint will immediately return a ProcessGroupReplaceRequestEntity, and the process of replacing the flow will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /process-groups/replace-requests/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /process-groups/replace-requests/{requestId}. Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupImportEntity The process group replace request entity (required)
     * @return ResponseEntity&lt;ProcessGroupReplaceRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupReplaceRequestEntity> initiateReplaceProcessGroupWithHttpInfo(String id, ProcessGroupImportEntity processGroupImportEntity) throws RestClientException {
        Object localVarPostBody = processGroupImportEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling initiateReplaceProcessGroup");
        }
        
        // verify the required parameter 'processGroupImportEntity' is set
        if (processGroupImportEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processGroupImportEntity' when calling initiateReplaceProcessGroup");
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

        ParameterizedTypeReference<ProcessGroupReplaceRequestEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupReplaceRequestEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/replace-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Pastes into the specified process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param pasteRequestEntity The request including the components to be pasted into the specified Process Group. (required)
     * @return PasteResponseEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PasteResponseEntity paste(String id, PasteRequestEntity pasteRequestEntity) throws RestClientException {
        return pasteWithHttpInfo(id, pasteRequestEntity).getBody();
    }

    /**
     * Pastes into the specified process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param pasteRequestEntity The request including the components to be pasted into the specified Process Group. (required)
     * @return ResponseEntity&lt;PasteResponseEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PasteResponseEntity> pasteWithHttpInfo(String id, PasteRequestEntity pasteRequestEntity) throws RestClientException {
        Object localVarPostBody = pasteRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling paste");
        }
        
        // verify the required parameter 'pasteRequestEntity' is set
        if (pasteRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pasteRequestEntity' when calling paste");
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

        ParameterizedTypeReference<PasteResponseEntity> localReturnType = new ParameterizedTypeReference<PasteResponseEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/paste", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Cancels and/or removes a request to drop all FlowFiles.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param dropRequestId The drop request id. (required)
     * @return DropRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DropRequestEntity removeDropRequest1(String id, String dropRequestId) throws RestClientException {
        return removeDropRequest1WithHttpInfo(id, dropRequestId).getBody();
    }

    /**
     * Cancels and/or removes a request to drop all FlowFiles.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param dropRequestId The drop request id. (required)
     * @return ResponseEntity&lt;DropRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DropRequestEntity> removeDropRequest1WithHttpInfo(String id, String dropRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling removeDropRequest1");
        }
        
        // verify the required parameter 'dropRequestId' is set
        if (dropRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dropRequestId' when calling removeDropRequest1");
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
        return apiClient.invokeAPI("/process-groups/{id}/empty-all-connections-requests/{drop-request-id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes a process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupEntity removeProcessGroup(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return removeProcessGroupWithHttpInfo(id, version, clientId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes a process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;ProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupEntity> removeProcessGroupWithHttpInfo(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling removeProcessGroup");
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

        ParameterizedTypeReference<ProcessGroupEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Replace Process Group contents with the given ID with the specified Process Group contents
     * This endpoint is used for replication within a cluster, when replacing a flow with a new flow. It expects that the flow beingreplaced is not under version control and that the given snapshot will not modify any Processor that is currently running or any Controller Service that is enabled. Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupImportEntity The process group replace request entity. (required)
     * @return ProcessGroupImportEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupImportEntity replaceProcessGroup(String id, ProcessGroupImportEntity processGroupImportEntity) throws RestClientException {
        return replaceProcessGroupWithHttpInfo(id, processGroupImportEntity).getBody();
    }

    /**
     * Replace Process Group contents with the given ID with the specified Process Group contents
     * This endpoint is used for replication within a cluster, when replacing a flow with a new flow. It expects that the flow beingreplaced is not under version control and that the given snapshot will not modify any Processor that is currently running or any Controller Service that is enabled. Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupImportEntity The process group replace request entity. (required)
     * @return ResponseEntity&lt;ProcessGroupImportEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupImportEntity> replaceProcessGroupWithHttpInfo(String id, ProcessGroupImportEntity processGroupImportEntity) throws RestClientException {
        Object localVarPostBody = processGroupImportEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling replaceProcessGroup");
        }
        
        // verify the required parameter 'processGroupImportEntity' is set
        if (processGroupImportEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processGroupImportEntity' when calling replaceProcessGroup");
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

        ParameterizedTypeReference<ProcessGroupImportEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupImportEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/flow-contents", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupEntity The process group configuration details. (required)
     * @return ProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupEntity updateProcessGroup(String id, ProcessGroupEntity processGroupEntity) throws RestClientException {
        return updateProcessGroupWithHttpInfo(id, processGroupEntity).getBody();
    }

    /**
     * Updates a process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param processGroupEntity The process group configuration details. (required)
     * @return ResponseEntity&lt;ProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupEntity> updateProcessGroupWithHttpInfo(String id, ProcessGroupEntity processGroupEntity) throws RestClientException {
        Object localVarPostBody = processGroupEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateProcessGroup");
        }
        
        // verify the required parameter 'processGroupEntity' is set
        if (processGroupEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processGroupEntity' when calling updateProcessGroup");
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

        ParameterizedTypeReference<ProcessGroupEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Uploads a versioned flow definition and creates a process group
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param clientId The client id. (required)
     * @param groupName The process group name. (required)
     * @param positionX The process group X position. (required)
     * @param positionY The process group Y position. (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @param _file The flow definition content (optional)
     * @return ProcessGroupEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupEntity uploadProcessGroup(String id, String clientId, String groupName, Double positionX, Double positionY, Boolean disconnectedNodeAcknowledged, Object _file) throws RestClientException {
        return uploadProcessGroupWithHttpInfo(id, clientId, groupName, positionX, positionY, disconnectedNodeAcknowledged, _file).getBody();
    }

    /**
     * Uploads a versioned flow definition and creates a process group
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param clientId The client id. (required)
     * @param groupName The process group name. (required)
     * @param positionX The process group X position. (required)
     * @param positionY The process group Y position. (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @param _file The flow definition content (optional)
     * @return ResponseEntity&lt;ProcessGroupEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupEntity> uploadProcessGroupWithHttpInfo(String id, String clientId, String groupName, Double positionX, Double positionY, Boolean disconnectedNodeAcknowledged, Object _file) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling uploadProcessGroup");
        }
        
        // verify the required parameter 'clientId' is set
        if (clientId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'clientId' when calling uploadProcessGroup");
        }
        
        // verify the required parameter 'groupName' is set
        if (groupName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'groupName' when calling uploadProcessGroup");
        }
        
        // verify the required parameter 'positionX' is set
        if (positionX == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'positionX' when calling uploadProcessGroup");
        }
        
        // verify the required parameter 'positionY' is set
        if (positionY == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'positionY' when calling uploadProcessGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (clientId != null)
            localVarFormParams.add("clientId", clientId);
        if (disconnectedNodeAcknowledged != null)
            localVarFormParams.add("disconnectedNodeAcknowledged", disconnectedNodeAcknowledged);
        if (_file != null)
            localVarFormParams.add("file", _file);
        if (groupName != null)
            localVarFormParams.add("groupName", groupName);
        if (positionX != null)
            localVarFormParams.add("positionX", positionX);
        if (positionY != null)
            localVarFormParams.add("positionY", positionY);

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ProcessGroupEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupEntity>() {};
        return apiClient.invokeAPI("/process-groups/{id}/process-groups/upload", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
