package org.giwi.nifi.tester.api;

import org.giwi.nifi.tester.invoker.ApiClient;
import org.giwi.nifi.tester.invoker.BaseApi;

import org.giwi.nifi.tester.model.LatestProvenanceEventsEntity;
import org.giwi.nifi.tester.model.LongParameter;
import org.giwi.nifi.tester.model.ProvenanceEventEntity;
import org.giwi.nifi.tester.model.ReplayLastEventRequestEntity;
import org.giwi.nifi.tester.model.ReplayLastEventResponseEntity;
import org.giwi.nifi.tester.model.SubmitReplayRequestEntity;

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
public class ProvenanceEventsApi extends BaseApi {

    public ProvenanceEventsApi() {
        super(new ApiClient());
    }

    public ProvenanceEventsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Gets the input content for a provenance event
     * 
     * <p><b>200</b>
     * <p><b>206</b> - Partial Content with range of bytes requested
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>416</b> - Requested Range Not Satisfiable based on bytes requested
     * @param id The provenance event id. (required)
     * @param range Range of bytes requested (optional)
     * @param clusterNodeId The id of the node where the content exists if clustered. (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getInputContent(LongParameter id, String range, String clusterNodeId) throws RestClientException {
        return getInputContentWithHttpInfo(id, range, clusterNodeId).getBody();
    }

    /**
     * Gets the input content for a provenance event
     * 
     * <p><b>200</b>
     * <p><b>206</b> - Partial Content with range of bytes requested
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>416</b> - Requested Range Not Satisfiable based on bytes requested
     * @param id The provenance event id. (required)
     * @param range Range of bytes requested (optional)
     * @param clusterNodeId The id of the node where the content exists if clustered. (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getInputContentWithHttpInfo(LongParameter id, String range, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getInputContent");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

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
        return apiClient.invokeAPI("/provenance-events/{id}/content/input", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the latest cached Provenance Events for the specified component
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param componentId The ID of the component to retrieve the latest Provenance Events for. (required)
     * @param limit The number of events to limit the response to. Defaults to 10. (optional, default to 10)
     * @return LatestProvenanceEventsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public LatestProvenanceEventsEntity getLatestProvenanceEvents(String componentId, Integer limit) throws RestClientException {
        return getLatestProvenanceEventsWithHttpInfo(componentId, limit).getBody();
    }

    /**
     * Retrieves the latest cached Provenance Events for the specified component
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param componentId The ID of the component to retrieve the latest Provenance Events for. (required)
     * @param limit The number of events to limit the response to. Defaults to 10. (optional, default to 10)
     * @return ResponseEntity&lt;LatestProvenanceEventsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<LatestProvenanceEventsEntity> getLatestProvenanceEventsWithHttpInfo(String componentId, Integer limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'componentId' is set
        if (componentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'componentId' when calling getLatestProvenanceEvents");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("componentId", componentId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<LatestProvenanceEventsEntity> localReturnType = new ParameterizedTypeReference<LatestProvenanceEventsEntity>() {};
        return apiClient.invokeAPI("/provenance-events/latest/{componentId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the output content for a provenance event
     * 
     * <p><b>200</b>
     * <p><b>206</b> - Partial Content with range of bytes requested
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>416</b> - Requested Range Not Satisfiable based on bytes requested
     * @param id The provenance event id. (required)
     * @param range Range of bytes requested (optional)
     * @param clusterNodeId The id of the node where the content exists if clustered. (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getOutputContent(LongParameter id, String range, String clusterNodeId) throws RestClientException {
        return getOutputContentWithHttpInfo(id, range, clusterNodeId).getBody();
    }

    /**
     * Gets the output content for a provenance event
     * 
     * <p><b>200</b>
     * <p><b>206</b> - Partial Content with range of bytes requested
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>416</b> - Requested Range Not Satisfiable based on bytes requested
     * @param id The provenance event id. (required)
     * @param range Range of bytes requested (optional)
     * @param clusterNodeId The id of the node where the content exists if clustered. (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getOutputContentWithHttpInfo(LongParameter id, String range, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getOutputContent");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

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
        return apiClient.invokeAPI("/provenance-events/{id}/content/output", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a provenance event
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The provenance event id. (required)
     * @param clusterNodeId The id of the node where this event exists if clustered. (optional)
     * @return ProvenanceEventEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProvenanceEventEntity getProvenanceEvent(LongParameter id, String clusterNodeId) throws RestClientException {
        return getProvenanceEventWithHttpInfo(id, clusterNodeId).getBody();
    }

    /**
     * Gets a provenance event
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The provenance event id. (required)
     * @param clusterNodeId The id of the node where this event exists if clustered. (optional)
     * @return ResponseEntity&lt;ProvenanceEventEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProvenanceEventEntity> getProvenanceEventWithHttpInfo(LongParameter id, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProvenanceEvent");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

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

        ParameterizedTypeReference<ProvenanceEventEntity> localReturnType = new ParameterizedTypeReference<ProvenanceEventEntity>() {};
        return apiClient.invokeAPI("/provenance-events/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Replays content from a provenance event
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param submitReplayRequestEntity The replay request. (required)
     * @return ProvenanceEventEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProvenanceEventEntity submitReplay(SubmitReplayRequestEntity submitReplayRequestEntity) throws RestClientException {
        return submitReplayWithHttpInfo(submitReplayRequestEntity).getBody();
    }

    /**
     * Replays content from a provenance event
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param submitReplayRequestEntity The replay request. (required)
     * @return ResponseEntity&lt;ProvenanceEventEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProvenanceEventEntity> submitReplayWithHttpInfo(SubmitReplayRequestEntity submitReplayRequestEntity) throws RestClientException {
        Object localVarPostBody = submitReplayRequestEntity;
        
        // verify the required parameter 'submitReplayRequestEntity' is set
        if (submitReplayRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'submitReplayRequestEntity' when calling submitReplay");
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

        ParameterizedTypeReference<ProvenanceEventEntity> localReturnType = new ParameterizedTypeReference<ProvenanceEventEntity>() {};
        return apiClient.invokeAPI("/provenance-events/replays", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Replays content from a provenance event
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param replayLastEventRequestEntity The replay request. (required)
     * @return ReplayLastEventResponseEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ReplayLastEventResponseEntity submitReplayLatestEvent(ReplayLastEventRequestEntity replayLastEventRequestEntity) throws RestClientException {
        return submitReplayLatestEventWithHttpInfo(replayLastEventRequestEntity).getBody();
    }

    /**
     * Replays content from a provenance event
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param replayLastEventRequestEntity The replay request. (required)
     * @return ResponseEntity&lt;ReplayLastEventResponseEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ReplayLastEventResponseEntity> submitReplayLatestEventWithHttpInfo(ReplayLastEventRequestEntity replayLastEventRequestEntity) throws RestClientException {
        Object localVarPostBody = replayLastEventRequestEntity;
        
        // verify the required parameter 'replayLastEventRequestEntity' is set
        if (replayLastEventRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'replayLastEventRequestEntity' when calling submitReplayLatestEvent");
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

        ParameterizedTypeReference<ReplayLastEventResponseEntity> localReturnType = new ParameterizedTypeReference<ReplayLastEventResponseEntity>() {};
        return apiClient.invokeAPI("/provenance-events/latest/replays", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
