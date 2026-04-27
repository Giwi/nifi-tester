package org.giwi.nifi.client.api;

import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.invoker.BaseApi;

import org.giwi.nifi.client.model.AssetEntity;
import org.giwi.nifi.client.model.AssetsEntity;
import org.giwi.nifi.client.model.ClientIdParameter;
import org.giwi.nifi.client.model.ComponentStateEntity;
import org.giwi.nifi.client.model.ConfigurationStepEntity;
import org.giwi.nifi.client.model.ConfigurationStepNamesEntity;
import org.giwi.nifi.client.model.ConnectorEntity;
import org.giwi.nifi.client.model.ConnectorPropertyAllowableValuesEntity;
import org.giwi.nifi.client.model.ConnectorRunStatusEntity;
import org.giwi.nifi.client.model.ControllerServicesEntity;
import org.giwi.nifi.client.model.DropRequestEntity;
import org.giwi.nifi.client.model.LongParameter;
import org.giwi.nifi.client.model.ProcessGroupFlowEntity;
import org.giwi.nifi.client.model.ProcessGroupStatusEntity;
import org.giwi.nifi.client.model.SearchResultsEntity;
import org.giwi.nifi.client.model.SecretsEntity;
import org.giwi.nifi.client.model.VerifyConnectorConfigStepRequestEntity;

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
public class ConnectorsApi extends BaseApi {

    public ConnectorsApi() {
        super(new ApiClient());
    }

    public ConnectorsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Applies an update to a connector
     * This will apply any pending configuration changes to the connector. The client can poll the connector endpoint to check when the update is complete.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param connectorEntity The connector configuration with revision. (required)
     * @return ConnectorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorEntity applyConnectorUpdate(String id, ConnectorEntity connectorEntity) throws RestClientException {
        return applyConnectorUpdateWithHttpInfo(id, connectorEntity).getBody();
    }

    /**
     * Applies an update to a connector
     * This will apply any pending configuration changes to the connector. The client can poll the connector endpoint to check when the update is complete.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param connectorEntity The connector configuration with revision. (required)
     * @return ResponseEntity&lt;ConnectorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorEntity> applyConnectorUpdateWithHttpInfo(String id, ConnectorEntity connectorEntity) throws RestClientException {
        Object localVarPostBody = connectorEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling applyConnectorUpdate");
        }
        
        // verify the required parameter 'connectorEntity' is set
        if (connectorEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'connectorEntity' when calling applyConnectorUpdate");
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

        ParameterizedTypeReference<ConnectorEntity> localReturnType = new ParameterizedTypeReference<ConnectorEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/apply-update", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Cancels the draining of FlowFiles for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ConnectorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorEntity cancelDrain(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return cancelDrainWithHttpInfo(id, version, clientId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Cancels the draining of FlowFiles for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;ConnectorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorEntity> cancelDrainWithHttpInfo(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling cancelDrain");
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

        ParameterizedTypeReference<ConnectorEntity> localReturnType = new ParameterizedTypeReference<ConnectorEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/drain", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clears the state for a controller service within a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param controllerServiceId The controller service id. (required)
     * @param componentStateEntity Optional component state to perform a selective key removal. If omitted, clears all state. (optional)
     * @return ComponentStateEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentStateEntity clearConnectorControllerServiceState(String id, String controllerServiceId, ComponentStateEntity componentStateEntity) throws RestClientException {
        return clearConnectorControllerServiceStateWithHttpInfo(id, controllerServiceId, componentStateEntity).getBody();
    }

    /**
     * Clears the state for a controller service within a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param controllerServiceId The controller service id. (required)
     * @param componentStateEntity Optional component state to perform a selective key removal. If omitted, clears all state. (optional)
     * @return ResponseEntity&lt;ComponentStateEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentStateEntity> clearConnectorControllerServiceStateWithHttpInfo(String id, String controllerServiceId, ComponentStateEntity componentStateEntity) throws RestClientException {
        Object localVarPostBody = componentStateEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearConnectorControllerServiceState");
        }
        
        // verify the required parameter 'controllerServiceId' is set
        if (controllerServiceId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'controllerServiceId' when calling clearConnectorControllerServiceState");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("controllerServiceId", controllerServiceId);

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

        ParameterizedTypeReference<ComponentStateEntity> localReturnType = new ParameterizedTypeReference<ComponentStateEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/controller-services/{controllerServiceId}/state/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clears the state for a processor within a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param processorId The processor id. (required)
     * @param componentStateEntity Optional component state to perform a selective key removal. If omitted, clears all state. (optional)
     * @return ComponentStateEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentStateEntity clearConnectorProcessorState(String id, String processorId, ComponentStateEntity componentStateEntity) throws RestClientException {
        return clearConnectorProcessorStateWithHttpInfo(id, processorId, componentStateEntity).getBody();
    }

    /**
     * Clears the state for a processor within a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param processorId The processor id. (required)
     * @param componentStateEntity Optional component state to perform a selective key removal. If omitted, clears all state. (optional)
     * @return ResponseEntity&lt;ComponentStateEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentStateEntity> clearConnectorProcessorStateWithHttpInfo(String id, String processorId, ComponentStateEntity componentStateEntity) throws RestClientException {
        Object localVarPostBody = componentStateEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearConnectorProcessorState");
        }
        
        // verify the required parameter 'processorId' is set
        if (processorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processorId' when calling clearConnectorProcessorState");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("processorId", processorId);

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

        ParameterizedTypeReference<ComponentStateEntity> localReturnType = new ParameterizedTypeReference<ComponentStateEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/processors/{processorId}/state/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new Asset in the given Connector
     * This endpoint will create a new Asset in the Connector. The Asset will be created with the given name and the contents of the file that is uploaded.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id  (required)
     * @param body The contents of the asset (required)
     * @param filename  (optional)
     * @return AssetEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AssetEntity createAsset(String id, Object body, String filename) throws RestClientException {
        return createAssetWithHttpInfo(id, body, filename).getBody();
    }

    /**
     * Creates a new Asset in the given Connector
     * This endpoint will create a new Asset in the Connector. The Asset will be created with the given name and the contents of the file that is uploaded.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id  (required)
     * @param body The contents of the asset (required)
     * @param filename  (optional)
     * @return ResponseEntity&lt;AssetEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AssetEntity> createAssetWithHttpInfo(String id, Object body, String filename) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createAsset");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling createAsset");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (filename != null)
        localVarHeaderParams.add("Filename", apiClient.parameterToString(filename));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/octet-stream"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<AssetEntity> localReturnType = new ParameterizedTypeReference<AssetEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/assets", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new connector
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param connectorEntity The connector configuration details. (required)
     * @return ConnectorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorEntity createConnector(ConnectorEntity connectorEntity) throws RestClientException {
        return createConnectorWithHttpInfo(connectorEntity).getBody();
    }

    /**
     * Creates a new connector
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param connectorEntity The connector configuration details. (required)
     * @return ResponseEntity&lt;ConnectorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorEntity> createConnectorWithHttpInfo(ConnectorEntity connectorEntity) throws RestClientException {
        Object localVarPostBody = connectorEntity;
        
        // verify the required parameter 'connectorEntity' is set
        if (connectorEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'connectorEntity' when calling createConnector");
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

        ParameterizedTypeReference<ConnectorEntity> localReturnType = new ParameterizedTypeReference<ConnectorEntity>() {};
        return apiClient.invokeAPI("/connectors", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a request to purge the FlowFiles for this connector
     * This will create a request to purge all FlowFiles from the connector. The connector must be in a STOPPED state before purging can begin. This is an asynchronous operation. The client should poll the returned URI to get the status of the purge request.
     * <p><b>202</b> - The request has been accepted. A HTTP response header will contain the URI where the response can be polled.
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return DropRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DropRequestEntity createPurgeRequest(String id) throws RestClientException {
        return createPurgeRequestWithHttpInfo(id).getBody();
    }

    /**
     * Creates a request to purge the FlowFiles for this connector
     * This will create a request to purge all FlowFiles from the connector. The connector must be in a STOPPED state before purging can begin. This is an asynchronous operation. The client should poll the returned URI to get the status of the purge request.
     * <p><b>202</b> - The request has been accepted. A HTTP response header will contain the URI where the response can be polled.
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return ResponseEntity&lt;DropRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DropRequestEntity> createPurgeRequestWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createPurgeRequest");
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
        return apiClient.invokeAPI("/connectors/{id}/purge-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes the Verification Request with the given ID
     * Deletes the Verification Request with the given ID. After a request is created, it is expected that the client will properly clean up the request by DELETE&#39;ing it, once the Verification process has completed. If the request is deleted before the request completes, then the Verification request will finish the step that it is currently performing and then will cancel any subsequent steps.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param requestId The ID of the Verification Request (required)
     * @return VerifyConnectorConfigStepRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConnectorConfigStepRequestEntity deleteConfigurationStepVerificationRequest(String id, String configurationStepName, String requestId) throws RestClientException {
        return deleteConfigurationStepVerificationRequestWithHttpInfo(id, configurationStepName, requestId).getBody();
    }

    /**
     * Deletes the Verification Request with the given ID
     * Deletes the Verification Request with the given ID. After a request is created, it is expected that the client will properly clean up the request by DELETE&#39;ing it, once the Verification process has completed. If the request is deleted before the request completes, then the Verification request will finish the step that it is currently performing and then will cancel any subsequent steps.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param requestId The ID of the Verification Request (required)
     * @return ResponseEntity&lt;VerifyConnectorConfigStepRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConnectorConfigStepRequestEntity> deleteConfigurationStepVerificationRequestWithHttpInfo(String id, String configurationStepName, String requestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteConfigurationStepVerificationRequest");
        }
        
        // verify the required parameter 'configurationStepName' is set
        if (configurationStepName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationStepName' when calling deleteConfigurationStepVerificationRequest");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling deleteConfigurationStepVerificationRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("configurationStepName", configurationStepName);
        uriVariables.put("requestId", requestId);

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

        ParameterizedTypeReference<VerifyConnectorConfigStepRequestEntity> localReturnType = new ParameterizedTypeReference<VerifyConnectorConfigStepRequestEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/configuration-steps/{configurationStepName}/verify-config/{requestId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ConnectorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorEntity deleteConnector(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return deleteConnectorWithHttpInfo(id, version, clientId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;ConnectorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorEntity> deleteConnectorWithHttpInfo(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteConnector");
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

        ParameterizedTypeReference<ConnectorEntity> localReturnType = new ParameterizedTypeReference<ConnectorEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Discards the working configuration of a connector
     * This will discard any pending configuration changes for the connector and revert to the last applied configuration.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ConnectorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorEntity discardConnectorUpdate(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return discardConnectorUpdateWithHttpInfo(id, version, clientId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Discards the working configuration of a connector
     * This will discard any pending configuration changes for the connector and revert to the last applied configuration.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;ConnectorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorEntity> discardConnectorUpdateWithHttpInfo(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling discardConnectorUpdate");
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

        ParameterizedTypeReference<ConnectorEntity> localReturnType = new ParameterizedTypeReference<ConnectorEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/working-configuration", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the content of the asset with the given id for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param assetId The asset id. (required)
     * @return byte[]
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public byte[] getAssetContent(String id, String assetId) throws RestClientException {
        return getAssetContentWithHttpInfo(id, assetId).getBody();
    }

    /**
     * Retrieves the content of the asset with the given id for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param assetId The asset id. (required)
     * @return ResponseEntity&lt;byte[]&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<byte[]> getAssetContentWithHttpInfo(String id, String assetId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAssetContent");
        }
        
        // verify the required parameter 'assetId' is set
        if (assetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'assetId' when calling getAssetContent");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("assetId", assetId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/octet-stream"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<byte[]> localReturnType = new ParameterizedTypeReference<byte[]>() {};
        return apiClient.invokeAPI("/connectors/{id}/assets/{assetId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Lists the assets that belong to the Connector with the given ID
     * Lists the assets that belong to the Connector with the given ID.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return AssetsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AssetsEntity getAssets(String id) throws RestClientException {
        return getAssetsWithHttpInfo(id).getBody();
    }

    /**
     * Lists the assets that belong to the Connector with the given ID
     * Lists the assets that belong to the Connector with the given ID.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return ResponseEntity&lt;AssetsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AssetsEntity> getAssetsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAssets");
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

        ParameterizedTypeReference<AssetsEntity> localReturnType = new ParameterizedTypeReference<AssetsEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/assets", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns the Verification Request with the given ID
     * Returns the Verification Request with the given ID. Once a Verification Request has been created, that request can subsequently be retrieved via this endpoint, and the request that is fetched will contain the updated state, such as percent complete, the current state of the request, and any failures.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param requestId The ID of the Verification Request (required)
     * @return VerifyConnectorConfigStepRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConnectorConfigStepRequestEntity getConfigurationStepVerificationRequest(String id, String configurationStepName, String requestId) throws RestClientException {
        return getConfigurationStepVerificationRequestWithHttpInfo(id, configurationStepName, requestId).getBody();
    }

    /**
     * Returns the Verification Request with the given ID
     * Returns the Verification Request with the given ID. Once a Verification Request has been created, that request can subsequently be retrieved via this endpoint, and the request that is fetched will contain the updated state, such as percent complete, the current state of the request, and any failures.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param requestId The ID of the Verification Request (required)
     * @return ResponseEntity&lt;VerifyConnectorConfigStepRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConnectorConfigStepRequestEntity> getConfigurationStepVerificationRequestWithHttpInfo(String id, String configurationStepName, String requestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConfigurationStepVerificationRequest");
        }
        
        // verify the required parameter 'configurationStepName' is set
        if (configurationStepName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationStepName' when calling getConfigurationStepVerificationRequest");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling getConfigurationStepVerificationRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("configurationStepName", configurationStepName);
        uriVariables.put("requestId", requestId);

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

        ParameterizedTypeReference<VerifyConnectorConfigStepRequestEntity> localReturnType = new ParameterizedTypeReference<VerifyConnectorConfigStepRequestEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/configuration-steps/{configurationStepName}/verify-config/{requestId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return ConnectorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorEntity getConnector(String id) throws RestClientException {
        return getConnectorWithHttpInfo(id).getBody();
    }

    /**
     * Gets a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return ResponseEntity&lt;ConnectorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorEntity> getConnectorWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnector");
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

        ParameterizedTypeReference<ConnectorEntity> localReturnType = new ParameterizedTypeReference<ConnectorEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a specific configuration step by name for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @return ConfigurationStepEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConfigurationStepEntity getConnectorConfigurationStep(String id, String configurationStepName) throws RestClientException {
        return getConnectorConfigurationStepWithHttpInfo(id, configurationStepName).getBody();
    }

    /**
     * Gets a specific configuration step by name for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @return ResponseEntity&lt;ConfigurationStepEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConfigurationStepEntity> getConnectorConfigurationStepWithHttpInfo(String id, String configurationStepName) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnectorConfigurationStep");
        }
        
        // verify the required parameter 'configurationStepName' is set
        if (configurationStepName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationStepName' when calling getConnectorConfigurationStep");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("configurationStepName", configurationStepName);

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

        ParameterizedTypeReference<ConfigurationStepEntity> localReturnType = new ParameterizedTypeReference<ConfigurationStepEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/configuration-steps/{configurationStepName}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all configuration step names for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return ConfigurationStepNamesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConfigurationStepNamesEntity getConnectorConfigurationSteps(String id) throws RestClientException {
        return getConnectorConfigurationStepsWithHttpInfo(id).getBody();
    }

    /**
     * Gets all configuration step names for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return ResponseEntity&lt;ConfigurationStepNamesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConfigurationStepNamesEntity> getConnectorConfigurationStepsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnectorConfigurationSteps");
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

        ParameterizedTypeReference<ConfigurationStepNamesEntity> localReturnType = new ParameterizedTypeReference<ConfigurationStepNamesEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/configuration-steps", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the state for a controller service within a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param controllerServiceId The controller service id. (required)
     * @return ComponentStateEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentStateEntity getConnectorControllerServiceState(String id, String controllerServiceId) throws RestClientException {
        return getConnectorControllerServiceStateWithHttpInfo(id, controllerServiceId).getBody();
    }

    /**
     * Gets the state for a controller service within a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param controllerServiceId The controller service id. (required)
     * @return ResponseEntity&lt;ComponentStateEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentStateEntity> getConnectorControllerServiceStateWithHttpInfo(String id, String controllerServiceId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnectorControllerServiceState");
        }
        
        // verify the required parameter 'controllerServiceId' is set
        if (controllerServiceId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'controllerServiceId' when calling getConnectorControllerServiceState");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("controllerServiceId", controllerServiceId);

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
        return apiClient.invokeAPI("/connectors/{id}/controller-services/{controllerServiceId}/state", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the state for a processor within a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param processorId The processor id. (required)
     * @return ComponentStateEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentStateEntity getConnectorProcessorState(String id, String processorId) throws RestClientException {
        return getConnectorProcessorStateWithHttpInfo(id, processorId).getBody();
    }

    /**
     * Gets the state for a processor within a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param processorId The processor id. (required)
     * @return ResponseEntity&lt;ComponentStateEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentStateEntity> getConnectorProcessorStateWithHttpInfo(String id, String processorId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnectorProcessorState");
        }
        
        // verify the required parameter 'processorId' is set
        if (processorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processorId' when calling getConnectorProcessorState");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("processorId", processorId);

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
        return apiClient.invokeAPI("/connectors/{id}/processors/{processorId}/state", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the allowable values for a specific property in a connector&#39;s configuration step
     * Gets the allowable values for a specific property that supports dynamic fetching of allowable values. The filter parameter can be used to narrow down the results based on the property&#39;s filtering logic.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param propertyGroupName The property group name. (required)
     * @param propertyName The property name. (required)
     * @param filter Optional filter to narrow down the allowable values. (optional)
     * @return ConnectorPropertyAllowableValuesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorPropertyAllowableValuesEntity getConnectorPropertyAllowableValues(String id, String configurationStepName, String propertyGroupName, String propertyName, String filter) throws RestClientException {
        return getConnectorPropertyAllowableValuesWithHttpInfo(id, configurationStepName, propertyGroupName, propertyName, filter).getBody();
    }

    /**
     * Gets the allowable values for a specific property in a connector&#39;s configuration step
     * Gets the allowable values for a specific property that supports dynamic fetching of allowable values. The filter parameter can be used to narrow down the results based on the property&#39;s filtering logic.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param propertyGroupName The property group name. (required)
     * @param propertyName The property name. (required)
     * @param filter Optional filter to narrow down the allowable values. (optional)
     * @return ResponseEntity&lt;ConnectorPropertyAllowableValuesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorPropertyAllowableValuesEntity> getConnectorPropertyAllowableValuesWithHttpInfo(String id, String configurationStepName, String propertyGroupName, String propertyName, String filter) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnectorPropertyAllowableValues");
        }
        
        // verify the required parameter 'configurationStepName' is set
        if (configurationStepName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationStepName' when calling getConnectorPropertyAllowableValues");
        }
        
        // verify the required parameter 'propertyGroupName' is set
        if (propertyGroupName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'propertyGroupName' when calling getConnectorPropertyAllowableValues");
        }
        
        // verify the required parameter 'propertyName' is set
        if (propertyName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'propertyName' when calling getConnectorPropertyAllowableValues");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("configurationStepName", configurationStepName);
        uriVariables.put("propertyGroupName", propertyGroupName);
        uriVariables.put("propertyName", propertyName);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ConnectorPropertyAllowableValuesEntity> localReturnType = new ParameterizedTypeReference<ConnectorPropertyAllowableValuesEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/configuration-steps/{configurationStepName}/property-groups/{propertyGroupName}/properties/{propertyName}/allowable-values", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the status for the process group managed by a connector
     * Returns the status for the process group managed by the specified connector. The status includes status for all descendent components. When invoked with recursive set to true, it will return the current status of every component in the connector&#39;s encapsulated flow.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param recursive Whether all descendant groups and the status of their content will be included. Optional, defaults to false (optional, default to false)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ProcessGroupStatusEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupStatusEntity getConnectorStatus(String id, Boolean recursive, Boolean nodewise, String clusterNodeId) throws RestClientException {
        return getConnectorStatusWithHttpInfo(id, recursive, nodewise, clusterNodeId).getBody();
    }

    /**
     * Gets the status for the process group managed by a connector
     * Returns the status for the process group managed by the specified connector. The status includes status for all descendent components. When invoked with recursive set to true, it will return the current status of every component in the connector&#39;s encapsulated flow.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param recursive Whether all descendant groups and the status of their content will be included. Optional, defaults to false (optional, default to false)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ResponseEntity&lt;ProcessGroupStatusEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupStatusEntity> getConnectorStatusWithHttpInfo(String id, Boolean recursive, Boolean nodewise, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnectorStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "recursive", recursive));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "nodewise", nodewise));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ProcessGroupStatusEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupStatusEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/status", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all controller services for a process group within a connector
     * Returns the controller services for the specified process group within the connector&#39;s hierarchy. The processGroupId can be obtained from the managedProcessGroupId field of the ConnectorDTO for the root process group, or from child process groups within the flow.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param connectorId The connector id. (required)
     * @param processGroupId The process group id. (required)
     * @param includeAncestorGroups Whether or not to include parent/ancestor process groups (optional, default to true)
     * @param includeDescendantGroups Whether or not to include descendant process groups (optional, default to false)
     * @param includeReferencingComponents Whether or not to include services&#39; referencing components in the response (optional, default to true)
     * @return ControllerServicesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerServicesEntity getControllerServicesFromConnectorProcessGroup(String connectorId, String processGroupId, Boolean includeAncestorGroups, Boolean includeDescendantGroups, Boolean includeReferencingComponents) throws RestClientException {
        return getControllerServicesFromConnectorProcessGroupWithHttpInfo(connectorId, processGroupId, includeAncestorGroups, includeDescendantGroups, includeReferencingComponents).getBody();
    }

    /**
     * Gets all controller services for a process group within a connector
     * Returns the controller services for the specified process group within the connector&#39;s hierarchy. The processGroupId can be obtained from the managedProcessGroupId field of the ConnectorDTO for the root process group, or from child process groups within the flow.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param connectorId The connector id. (required)
     * @param processGroupId The process group id. (required)
     * @param includeAncestorGroups Whether or not to include parent/ancestor process groups (optional, default to true)
     * @param includeDescendantGroups Whether or not to include descendant process groups (optional, default to false)
     * @param includeReferencingComponents Whether or not to include services&#39; referencing components in the response (optional, default to true)
     * @return ResponseEntity&lt;ControllerServicesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerServicesEntity> getControllerServicesFromConnectorProcessGroupWithHttpInfo(String connectorId, String processGroupId, Boolean includeAncestorGroups, Boolean includeDescendantGroups, Boolean includeReferencingComponents) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'connectorId' is set
        if (connectorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'connectorId' when calling getControllerServicesFromConnectorProcessGroup");
        }
        
        // verify the required parameter 'processGroupId' is set
        if (processGroupId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processGroupId' when calling getControllerServicesFromConnectorProcessGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("connectorId", connectorId);
        uriVariables.put("processGroupId", processGroupId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeAncestorGroups", includeAncestorGroups));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDescendantGroups", includeDescendantGroups));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeReferencingComponents", includeReferencingComponents));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ControllerServicesEntity> localReturnType = new ParameterizedTypeReference<ControllerServicesEntity>() {};
        return apiClient.invokeAPI("/connectors/{connectorId}/flow/process-groups/{processGroupId}/controller-services", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the flow for a process group within a connector
     * Returns the flow for the specified process group within the connector&#39;s hierarchy. The processGroupId can be obtained from the managedProcessGroupId field of the ConnectorDTO for the root process group, or from child process groups within the flow. If the uiOnly query parameter is provided with a value of true, the returned entity may only contain fields that are necessary for rendering the NiFi User Interface. As such, the selected fields may change at any time, even during incremental releases, without warning. As a result, this parameter should not be provided by any client other than the UI.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param connectorId The connector id. (required)
     * @param processGroupId The process group id. (required)
     * @param uiOnly Whether to return only UI-specific fields (optional, default to false)
     * @return ProcessGroupFlowEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupFlowEntity getFlow(String connectorId, String processGroupId, Boolean uiOnly) throws RestClientException {
        return getFlowWithHttpInfo(connectorId, processGroupId, uiOnly).getBody();
    }

    /**
     * Gets the flow for a process group within a connector
     * Returns the flow for the specified process group within the connector&#39;s hierarchy. The processGroupId can be obtained from the managedProcessGroupId field of the ConnectorDTO for the root process group, or from child process groups within the flow. If the uiOnly query parameter is provided with a value of true, the returned entity may only contain fields that are necessary for rendering the NiFi User Interface. As such, the selected fields may change at any time, even during incremental releases, without warning. As a result, this parameter should not be provided by any client other than the UI.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param connectorId The connector id. (required)
     * @param processGroupId The process group id. (required)
     * @param uiOnly Whether to return only UI-specific fields (optional, default to false)
     * @return ResponseEntity&lt;ProcessGroupFlowEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupFlowEntity> getFlowWithHttpInfo(String connectorId, String processGroupId, Boolean uiOnly) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'connectorId' is set
        if (connectorId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'connectorId' when calling getFlow");
        }
        
        // verify the required parameter 'processGroupId' is set
        if (processGroupId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processGroupId' when calling getFlow");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("connectorId", connectorId);
        uriVariables.put("processGroupId", processGroupId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "uiOnly", uiOnly));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ProcessGroupFlowEntity> localReturnType = new ParameterizedTypeReference<ProcessGroupFlowEntity>() {};
        return apiClient.invokeAPI("/connectors/{connectorId}/flow/process-groups/{processGroupId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the current status of a purge request for the specified connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param purgeRequestId The purge request id. (required)
     * @return DropRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DropRequestEntity getPurgeRequest(String id, String purgeRequestId) throws RestClientException {
        return getPurgeRequestWithHttpInfo(id, purgeRequestId).getBody();
    }

    /**
     * Gets the current status of a purge request for the specified connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param purgeRequestId The purge request id. (required)
     * @return ResponseEntity&lt;DropRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DropRequestEntity> getPurgeRequestWithHttpInfo(String id, String purgeRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getPurgeRequest");
        }
        
        // verify the required parameter 'purgeRequestId' is set
        if (purgeRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'purgeRequestId' when calling getPurgeRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("purge-request-id", purgeRequestId);

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
        return apiClient.invokeAPI("/connectors/{id}/purge-requests/{purge-request-id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all secrets available for configuring a connector
     * Returns metadata for all secrets available from all secret providers. This endpoint is used when configuring a connector to discover available secrets. Note: Actual secret values are not included in the response for security reasons.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return SecretsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SecretsEntity getSecrets(String id) throws RestClientException {
        return getSecretsWithHttpInfo(id).getBody();
    }

    /**
     * Gets all secrets available for configuring a connector
     * Returns metadata for all secrets available from all secret providers. This endpoint is used when configuring a connector to discover available secrets. Note: Actual secret values are not included in the response for security reasons.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @return ResponseEntity&lt;SecretsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SecretsEntity> getSecretsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getSecrets");
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

        ParameterizedTypeReference<SecretsEntity> localReturnType = new ParameterizedTypeReference<SecretsEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/secrets", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Initiates draining of FlowFiles for a connector
     * This will initiate draining of FlowFiles for a stopped connector. Draining allows the connector to process data that is currently in the flow but does not ingest any additional data. The connector must be in a STOPPED state before draining can begin. Once initiated, the connector will transition to a DRAINING state. Use the DELETE method on this endpoint to cancel an ongoing drain operation.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param connectorEntity The connector entity with revision. (required)
     * @return ConnectorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorEntity initiateDrain(String id, ConnectorEntity connectorEntity) throws RestClientException {
        return initiateDrainWithHttpInfo(id, connectorEntity).getBody();
    }

    /**
     * Initiates draining of FlowFiles for a connector
     * This will initiate draining of FlowFiles for a stopped connector. Draining allows the connector to process data that is currently in the flow but does not ingest any additional data. The connector must be in a STOPPED state before draining can begin. Once initiated, the connector will transition to a DRAINING state. Use the DELETE method on this endpoint to cancel an ongoing drain operation.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param connectorEntity The connector entity with revision. (required)
     * @return ResponseEntity&lt;ConnectorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorEntity> initiateDrainWithHttpInfo(String id, ConnectorEntity connectorEntity) throws RestClientException {
        Object localVarPostBody = connectorEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling initiateDrain");
        }
        
        // verify the required parameter 'connectorEntity' is set
        if (connectorEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'connectorEntity' when calling initiateDrain");
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

        ParameterizedTypeReference<ConnectorEntity> localReturnType = new ParameterizedTypeReference<ConnectorEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/drain", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Cancels and/or removes a request to purge the FlowFiles for this connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param purgeRequestId The purge request id. (required)
     * @return DropRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DropRequestEntity removePurgeRequest(String id, String purgeRequestId) throws RestClientException {
        return removePurgeRequestWithHttpInfo(id, purgeRequestId).getBody();
    }

    /**
     * Cancels and/or removes a request to purge the FlowFiles for this connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param purgeRequestId The purge request id. (required)
     * @return ResponseEntity&lt;DropRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DropRequestEntity> removePurgeRequestWithHttpInfo(String id, String purgeRequestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling removePurgeRequest");
        }
        
        // verify the required parameter 'purgeRequestId' is set
        if (purgeRequestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'purgeRequestId' when calling removePurgeRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("purge-request-id", purgeRequestId);

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
        return apiClient.invokeAPI("/connectors/{id}/purge-requests/{purge-request-id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Performs a search against the encapsulated process group of this connector using the specified search term
     * Only search results from authorized components will be returned.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param q The search term. (optional, default to )
     * @return SearchResultsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SearchResultsEntity searchConnector(String id, String q) throws RestClientException {
        return searchConnectorWithHttpInfo(id, q).getBody();
    }

    /**
     * Performs a search against the encapsulated process group of this connector using the specified search term
     * Only search results from authorized components will be returned.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param q The search term. (optional, default to )
     * @return ResponseEntity&lt;SearchResultsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SearchResultsEntity> searchConnectorWithHttpInfo(String id, String q) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling searchConnector");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "q", q));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<SearchResultsEntity> localReturnType = new ParameterizedTypeReference<SearchResultsEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/search-results", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Performs verification of a configuration step for a connector
     * This will initiate the process of verifying a given Connector Configuration Step. This may be a long-running task. As a result, this endpoint will immediately return a VerifyConnectorConfigStepRequestEntity, and the process of performing the verification will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /connectors/{connectorId}/configuration-steps/{stepName}/verify-config/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /connectors/{connectorId}/configuration-steps/{stepName}/verify-config/{requestId}.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param verifyConnectorConfigStepRequestEntity The verify config request entity containing the configuration step to verify. (required)
     * @return VerifyConnectorConfigStepRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConnectorConfigStepRequestEntity submitConfigurationStepVerificationRequest(String id, String configurationStepName, VerifyConnectorConfigStepRequestEntity verifyConnectorConfigStepRequestEntity) throws RestClientException {
        return submitConfigurationStepVerificationRequestWithHttpInfo(id, configurationStepName, verifyConnectorConfigStepRequestEntity).getBody();
    }

    /**
     * Performs verification of a configuration step for a connector
     * This will initiate the process of verifying a given Connector Configuration Step. This may be a long-running task. As a result, this endpoint will immediately return a VerifyConnectorConfigStepRequestEntity, and the process of performing the verification will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /connectors/{connectorId}/configuration-steps/{stepName}/verify-config/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /connectors/{connectorId}/configuration-steps/{stepName}/verify-config/{requestId}.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param verifyConnectorConfigStepRequestEntity The verify config request entity containing the configuration step to verify. (required)
     * @return ResponseEntity&lt;VerifyConnectorConfigStepRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConnectorConfigStepRequestEntity> submitConfigurationStepVerificationRequestWithHttpInfo(String id, String configurationStepName, VerifyConnectorConfigStepRequestEntity verifyConnectorConfigStepRequestEntity) throws RestClientException {
        Object localVarPostBody = verifyConnectorConfigStepRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling submitConfigurationStepVerificationRequest");
        }
        
        // verify the required parameter 'configurationStepName' is set
        if (configurationStepName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationStepName' when calling submitConfigurationStepVerificationRequest");
        }
        
        // verify the required parameter 'verifyConnectorConfigStepRequestEntity' is set
        if (verifyConnectorConfigStepRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'verifyConnectorConfigStepRequestEntity' when calling submitConfigurationStepVerificationRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("configurationStepName", configurationStepName);

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

        ParameterizedTypeReference<VerifyConnectorConfigStepRequestEntity> localReturnType = new ParameterizedTypeReference<VerifyConnectorConfigStepRequestEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/configuration-steps/{configurationStepName}/verify-config", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param connectorEntity The connector configuration details. (required)
     * @return ConnectorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorEntity updateConnector(String id, ConnectorEntity connectorEntity) throws RestClientException {
        return updateConnectorWithHttpInfo(id, connectorEntity).getBody();
    }

    /**
     * Updates a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param connectorEntity The connector configuration details. (required)
     * @return ResponseEntity&lt;ConnectorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorEntity> updateConnectorWithHttpInfo(String id, ConnectorEntity connectorEntity) throws RestClientException {
        Object localVarPostBody = connectorEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateConnector");
        }
        
        // verify the required parameter 'connectorEntity' is set
        if (connectorEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'connectorEntity' when calling updateConnector");
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

        ParameterizedTypeReference<ConnectorEntity> localReturnType = new ParameterizedTypeReference<ConnectorEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a specific configuration step by name for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param configurationStepEntity The configuration step configuration. (required)
     * @return ConfigurationStepEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConfigurationStepEntity updateConnectorConfigurationStep(String id, String configurationStepName, ConfigurationStepEntity configurationStepEntity) throws RestClientException {
        return updateConnectorConfigurationStepWithHttpInfo(id, configurationStepName, configurationStepEntity).getBody();
    }

    /**
     * Updates a specific configuration step by name for a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param configurationStepName The configuration step name. (required)
     * @param configurationStepEntity The configuration step configuration. (required)
     * @return ResponseEntity&lt;ConfigurationStepEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConfigurationStepEntity> updateConnectorConfigurationStepWithHttpInfo(String id, String configurationStepName, ConfigurationStepEntity configurationStepEntity) throws RestClientException {
        Object localVarPostBody = configurationStepEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateConnectorConfigurationStep");
        }
        
        // verify the required parameter 'configurationStepName' is set
        if (configurationStepName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationStepName' when calling updateConnectorConfigurationStep");
        }
        
        // verify the required parameter 'configurationStepEntity' is set
        if (configurationStepEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationStepEntity' when calling updateConnectorConfigurationStep");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("configurationStepName", configurationStepName);

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

        ParameterizedTypeReference<ConfigurationStepEntity> localReturnType = new ParameterizedTypeReference<ConfigurationStepEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/configuration-steps/{configurationStepName}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates run status of a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param connectorRunStatusEntity The connector run status. (required)
     * @return ConnectorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorEntity updateRunStatus(String id, ConnectorRunStatusEntity connectorRunStatusEntity) throws RestClientException {
        return updateRunStatusWithHttpInfo(id, connectorRunStatusEntity).getBody();
    }

    /**
     * Updates run status of a connector
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connector id. (required)
     * @param connectorRunStatusEntity The connector run status. (required)
     * @return ResponseEntity&lt;ConnectorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorEntity> updateRunStatusWithHttpInfo(String id, ConnectorRunStatusEntity connectorRunStatusEntity) throws RestClientException {
        Object localVarPostBody = connectorRunStatusEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRunStatus");
        }
        
        // verify the required parameter 'connectorRunStatusEntity' is set
        if (connectorRunStatusEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'connectorRunStatusEntity' when calling updateRunStatus");
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

        ParameterizedTypeReference<ConnectorEntity> localReturnType = new ParameterizedTypeReference<ConnectorEntity>() {};
        return apiClient.invokeAPI("/connectors/{id}/run-status", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
