package org.giwi.nifi.tester.api;

import org.giwi.nifi.tester.invoker.ApiClient;
import org.giwi.nifi.tester.invoker.BaseApi;

import org.giwi.nifi.tester.model.AboutEntity;
import org.giwi.nifi.tester.model.ActionEntity;
import org.giwi.nifi.tester.model.ActivateControllerServicesEntity;
import org.giwi.nifi.tester.model.AdditionalDetailsEntity;
import org.giwi.nifi.tester.model.BannerEntity;
import org.giwi.nifi.tester.model.BulletinBoardEntity;
import org.giwi.nifi.tester.model.BulletinBoardPatternParameter;
import org.giwi.nifi.tester.model.ClearBulletinsForGroupRequestEntity;
import org.giwi.nifi.tester.model.ClearBulletinsForGroupResultsEntity;
import org.giwi.nifi.tester.model.ClusterSearchResultsEntity;
import org.giwi.nifi.tester.model.ClusterSummaryEntity;
import org.giwi.nifi.tester.model.ComponentHistoryEntity;
import org.giwi.nifi.tester.model.ConnectionStatisticsEntity;
import org.giwi.nifi.tester.model.ConnectionStatusEntity;
import org.giwi.nifi.tester.model.ConnectorDefinition;
import org.giwi.nifi.tester.model.ConnectorTypesEntity;
import org.giwi.nifi.tester.model.ConnectorsEntity;
import org.giwi.nifi.tester.model.ContentViewerEntity;
import org.giwi.nifi.tester.model.ControllerBulletinsEntity;
import org.giwi.nifi.tester.model.ControllerServiceDefinition;
import org.giwi.nifi.tester.model.ControllerServiceTypesEntity;
import org.giwi.nifi.tester.model.ControllerServicesEntity;
import org.giwi.nifi.tester.model.ControllerStatusEntity;
import org.giwi.nifi.tester.model.CurrentUserEntity;
import org.giwi.nifi.tester.model.DateTimeParameter;
import org.giwi.nifi.tester.model.FlowAnalysisResultEntity;
import org.giwi.nifi.tester.model.FlowAnalysisRuleDefinition;
import org.giwi.nifi.tester.model.FlowAnalysisRuleTypesEntity;
import org.giwi.nifi.tester.model.FlowBreadcrumbEntity;
import org.giwi.nifi.tester.model.FlowComparisonEntity;
import org.giwi.nifi.tester.model.FlowConfigurationEntity;
import org.giwi.nifi.tester.model.FlowRegistryBranchesEntity;
import org.giwi.nifi.tester.model.FlowRegistryBucketsEntity;
import org.giwi.nifi.tester.model.FlowRegistryClientDefinition;
import org.giwi.nifi.tester.model.FlowRegistryClientsEntity;
import org.giwi.nifi.tester.model.HistoryEntity;
import org.giwi.nifi.tester.model.IntegerParameter;
import org.giwi.nifi.tester.model.ListenPortsEntity;
import org.giwi.nifi.tester.model.LongParameter;
import org.giwi.nifi.tester.model.ParameterContextsEntity;
import org.giwi.nifi.tester.model.ParameterProviderDefinition;
import org.giwi.nifi.tester.model.ParameterProviderTypesEntity;
import org.giwi.nifi.tester.model.ParameterProvidersEntity;
import org.giwi.nifi.tester.model.PortStatusEntity;
import org.giwi.nifi.tester.model.PrioritizerTypesEntity;
import org.giwi.nifi.tester.model.ProcessGroupFlowEntity;
import org.giwi.nifi.tester.model.ProcessGroupStatusEntity;
import org.giwi.nifi.tester.model.ProcessorDefinition;
import org.giwi.nifi.tester.model.ProcessorStatusEntity;
import org.giwi.nifi.tester.model.ProcessorTypesEntity;
import org.giwi.nifi.tester.model.RemoteProcessGroupStatusEntity;
import org.giwi.nifi.tester.model.ReportingTaskDefinition;
import org.giwi.nifi.tester.model.ReportingTaskTypesEntity;
import org.giwi.nifi.tester.model.ReportingTasksEntity;
import org.giwi.nifi.tester.model.RuntimeManifestEntity;
import org.giwi.nifi.tester.model.ScheduleComponentsEntity;
import org.giwi.nifi.tester.model.SearchResultsEntity;
import org.giwi.nifi.tester.model.StatusHistoryEntity;
import org.giwi.nifi.tester.model.StepDocumentationEntity;
import org.giwi.nifi.tester.model.VersionedFlowEntity;
import org.giwi.nifi.tester.model.VersionedFlowSnapshotMetadataSetEntity;
import org.giwi.nifi.tester.model.VersionedFlowsEntity;
import org.giwi.nifi.tester.model.VersionedReportingTaskSnapshot;

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
public class FlowApi extends BaseApi {

    public FlowApi() {
        super(new ApiClient());
    }

    public FlowApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Enable or disable Controller Services in the specified Process Group.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param activateControllerServicesEntity The request to schedule or unschedule. If the components in the request are not specified, all authorized components will be considered. (required)
     * @return ActivateControllerServicesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ActivateControllerServicesEntity activateControllerServices(String id, ActivateControllerServicesEntity activateControllerServicesEntity) throws RestClientException {
        return activateControllerServicesWithHttpInfo(id, activateControllerServicesEntity).getBody();
    }

    /**
     * Enable or disable Controller Services in the specified Process Group.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param activateControllerServicesEntity The request to schedule or unschedule. If the components in the request are not specified, all authorized components will be considered. (required)
     * @return ResponseEntity&lt;ActivateControllerServicesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ActivateControllerServicesEntity> activateControllerServicesWithHttpInfo(String id, ActivateControllerServicesEntity activateControllerServicesEntity) throws RestClientException {
        Object localVarPostBody = activateControllerServicesEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling activateControllerServices");
        }
        
        // verify the required parameter 'activateControllerServicesEntity' is set
        if (activateControllerServicesEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'activateControllerServicesEntity' when calling activateControllerServices");
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

        ParameterizedTypeReference<ActivateControllerServicesEntity> localReturnType = new ParameterizedTypeReference<ActivateControllerServicesEntity>() {};
        return apiClient.invokeAPI("/flow/process-groups/{id}/controller-services", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clears bulletins for components in the specified Process Group.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param clearBulletinsForGroupRequestEntity The request to clear bulletins. If the components in the request are not specified, all authorized components will be considered. (required)
     * @return ClearBulletinsForGroupResultsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ClearBulletinsForGroupResultsEntity clearBulletins1(String id, ClearBulletinsForGroupRequestEntity clearBulletinsForGroupRequestEntity) throws RestClientException {
        return clearBulletins1WithHttpInfo(id, clearBulletinsForGroupRequestEntity).getBody();
    }

    /**
     * Clears bulletins for components in the specified Process Group.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param clearBulletinsForGroupRequestEntity The request to clear bulletins. If the components in the request are not specified, all authorized components will be considered. (required)
     * @return ResponseEntity&lt;ClearBulletinsForGroupResultsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ClearBulletinsForGroupResultsEntity> clearBulletins1WithHttpInfo(String id, ClearBulletinsForGroupRequestEntity clearBulletinsForGroupRequestEntity) throws RestClientException {
        Object localVarPostBody = clearBulletinsForGroupRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearBulletins1");
        }
        
        // verify the required parameter 'clearBulletinsForGroupRequestEntity' is set
        if (clearBulletinsForGroupRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'clearBulletinsForGroupRequestEntity' when calling clearBulletins1");
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

        ParameterizedTypeReference<ClearBulletinsForGroupResultsEntity> localReturnType = new ParameterizedTypeReference<ClearBulletinsForGroupResultsEntity>() {};
        return apiClient.invokeAPI("/flow/process-groups/{id}/bulletins/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Download a snapshot of the given reporting tasks and any controller services they use
     * 
     * <p><b>200</b>
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param reportingTaskId Specifies a reporting task id to export. If not specified, all reporting tasks will be exported. (optional)
     * @return byte[]
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public byte[] downloadReportingTaskSnapshot(String reportingTaskId) throws RestClientException {
        return downloadReportingTaskSnapshotWithHttpInfo(reportingTaskId).getBody();
    }

    /**
     * Download a snapshot of the given reporting tasks and any controller services they use
     * 
     * <p><b>200</b>
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param reportingTaskId Specifies a reporting task id to export. If not specified, all reporting tasks will be exported. (optional)
     * @return ResponseEntity&lt;byte[]&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<byte[]> downloadReportingTaskSnapshotWithHttpInfo(String reportingTaskId) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "reportingTaskId", reportingTaskId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<byte[]> localReturnType = new ParameterizedTypeReference<byte[]>() {};
        return apiClient.invokeAPI("/flow/reporting-tasks/download", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Generates a client id.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String generateClientId() throws RestClientException {
        return generateClientIdWithHttpInfo().getBody();
    }

    /**
     * Generates a client id.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> generateClientIdWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "text/plain"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<String> localReturnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI("/flow/client-id", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves details about this NiFi to put in the About dialog
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return AboutEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AboutEntity getAboutInfo() throws RestClientException {
        return getAboutInfoWithHttpInfo().getBody();
    }

    /**
     * Retrieves details about this NiFi to put in the About dialog
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;AboutEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AboutEntity> getAboutInfoWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<AboutEntity> localReturnType = new ParameterizedTypeReference<AboutEntity>() {};
        return apiClient.invokeAPI("/flow/about", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets an action
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The action id. (required)
     * @return ActionEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ActionEntity getAction(IntegerParameter id) throws RestClientException {
        return getActionWithHttpInfo(id).getBody();
    }

    /**
     * Gets an action
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The action id. (required)
     * @return ResponseEntity&lt;ActionEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ActionEntity> getActionWithHttpInfo(IntegerParameter id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAction");
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

        ParameterizedTypeReference<ActionEntity> localReturnType = new ParameterizedTypeReference<ActionEntity>() {};
        return apiClient.invokeAPI("/flow/history/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the additional details for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The additional details for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The processor type (required)
     * @return AdditionalDetailsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AdditionalDetailsEntity getAdditionalDetails(String group, String artifact, String version, String type) throws RestClientException {
        return getAdditionalDetailsWithHttpInfo(group, artifact, version, type).getBody();
    }

    /**
     * Retrieves the additional details for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The additional details for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The processor type (required)
     * @return ResponseEntity&lt;AdditionalDetailsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AdditionalDetailsEntity> getAdditionalDetailsWithHttpInfo(String group, String artifact, String version, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling getAdditionalDetails");
        }
        
        // verify the required parameter 'artifact' is set
        if (artifact == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'artifact' when calling getAdditionalDetails");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling getAdditionalDetails");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getAdditionalDetails");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("group", group);
        uriVariables.put("artifact", artifact);
        uriVariables.put("version", version);
        uriVariables.put("type", type);

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

        ParameterizedTypeReference<AdditionalDetailsEntity> localReturnType = new ParameterizedTypeReference<AdditionalDetailsEntity>() {};
        return apiClient.invokeAPI("/flow/additional-details/{group}/{artifact}/{version}/{type}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns all flow analysis results currently in effect
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return FlowAnalysisResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisResultEntity getAllFlowAnalysisResults() throws RestClientException {
        return getAllFlowAnalysisResultsWithHttpInfo().getBody();
    }

    /**
     * Returns all flow analysis results currently in effect
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;FlowAnalysisResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisResultEntity> getAllFlowAnalysisResultsWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<FlowAnalysisResultEntity> localReturnType = new ParameterizedTypeReference<FlowAnalysisResultEntity>() {};
        return apiClient.invokeAPI("/flow/flow-analysis/results", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the banners for this NiFi
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return BannerEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BannerEntity getBanners() throws RestClientException {
        return getBannersWithHttpInfo().getBody();
    }

    /**
     * Retrieves the banners for this NiFi
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;BannerEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BannerEntity> getBannersWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<BannerEntity> localReturnType = new ParameterizedTypeReference<BannerEntity>() {};
        return apiClient.invokeAPI("/flow/banners", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the branches from the specified registry for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry id. (required)
     * @return FlowRegistryBranchesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowRegistryBranchesEntity getBranches(String id) throws RestClientException {
        return getBranchesWithHttpInfo(id).getBody();
    }

    /**
     * Gets the branches from the specified registry for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry id. (required)
     * @return ResponseEntity&lt;FlowRegistryBranchesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowRegistryBranchesEntity> getBranchesWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getBranches");
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

        ParameterizedTypeReference<FlowRegistryBranchesEntity> localReturnType = new ParameterizedTypeReference<FlowRegistryBranchesEntity>() {};
        return apiClient.invokeAPI("/flow/registries/{id}/branches", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the breadcrumbs for a process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return FlowBreadcrumbEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowBreadcrumbEntity getBreadcrumbs(String id) throws RestClientException {
        return getBreadcrumbsWithHttpInfo(id).getBody();
    }

    /**
     * Gets the breadcrumbs for a process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;FlowBreadcrumbEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowBreadcrumbEntity> getBreadcrumbsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getBreadcrumbs");
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

        ParameterizedTypeReference<FlowBreadcrumbEntity> localReturnType = new ParameterizedTypeReference<FlowBreadcrumbEntity>() {};
        return apiClient.invokeAPI("/flow/process-groups/{id}/breadcrumbs", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the buckets from the specified registry for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry id. (required)
     * @param branch The name of a branch to get the buckets from. If not specified the default branch of the registry client will be used. (optional)
     * @return FlowRegistryBucketsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowRegistryBucketsEntity getBuckets(String id, String branch) throws RestClientException {
        return getBucketsWithHttpInfo(id, branch).getBody();
    }

    /**
     * Gets the buckets from the specified registry for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry id. (required)
     * @param branch The name of a branch to get the buckets from. If not specified the default branch of the registry client will be used. (optional)
     * @return ResponseEntity&lt;FlowRegistryBucketsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowRegistryBucketsEntity> getBucketsWithHttpInfo(String id, String branch) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getBuckets");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "branch", branch));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<FlowRegistryBucketsEntity> localReturnType = new ParameterizedTypeReference<FlowRegistryBucketsEntity>() {};
        return apiClient.invokeAPI("/flow/registries/{id}/buckets", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets current bulletins
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param after Includes bulletins with an id after this value. (optional)
     * @param sourceName Includes bulletins originating from this sources whose name match this regular expression. (optional)
     * @param message Includes bulletins whose message that match this regular expression. (optional)
     * @param sourceId Includes bulletins originating from this sources whose id match this regular expression. (optional)
     * @param groupId Includes bulletins originating from this sources whose group id match this regular expression. (optional)
     * @param limit The number of bulletins to limit the response to. Optional, default is no limit. (optional)
     * @return BulletinBoardEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BulletinBoardEntity getBulletinBoard(LongParameter after, BulletinBoardPatternParameter sourceName, BulletinBoardPatternParameter message, BulletinBoardPatternParameter sourceId, BulletinBoardPatternParameter groupId, IntegerParameter limit) throws RestClientException {
        return getBulletinBoardWithHttpInfo(after, sourceName, message, sourceId, groupId, limit).getBody();
    }

    /**
     * Gets current bulletins
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param after Includes bulletins with an id after this value. (optional)
     * @param sourceName Includes bulletins originating from this sources whose name match this regular expression. (optional)
     * @param message Includes bulletins whose message that match this regular expression. (optional)
     * @param sourceId Includes bulletins originating from this sources whose id match this regular expression. (optional)
     * @param groupId Includes bulletins originating from this sources whose group id match this regular expression. (optional)
     * @param limit The number of bulletins to limit the response to. Optional, default is no limit. (optional)
     * @return ResponseEntity&lt;BulletinBoardEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BulletinBoardEntity> getBulletinBoardWithHttpInfo(LongParameter after, BulletinBoardPatternParameter sourceName, BulletinBoardPatternParameter message, BulletinBoardPatternParameter sourceId, BulletinBoardPatternParameter groupId, IntegerParameter limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        
        if (after != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "long", after.getLong()));
        }
        if (sourceName != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "pattern", sourceName.getPattern()));
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "rawPattern", sourceName.getRawPattern()));
        }
        if (message != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "pattern", message.getPattern()));
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "rawPattern", message.getRawPattern()));
        }
        if (sourceId != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "pattern", sourceId.getPattern()));
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "rawPattern", sourceId.getRawPattern()));
        }
        if (groupId != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "pattern", groupId.getPattern()));
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "rawPattern", groupId.getRawPattern()));
        }
        if (limit != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "integer", limit.getInteger()));
        }

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<BulletinBoardEntity> localReturnType = new ParameterizedTypeReference<BulletinBoardEntity>() {};
        return apiClient.invokeAPI("/flow/bulletin-board", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves Controller level bulletins
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ControllerBulletinsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerBulletinsEntity getBulletins() throws RestClientException {
        return getBulletinsWithHttpInfo().getBody();
    }

    /**
     * Retrieves Controller level bulletins
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ControllerBulletinsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerBulletinsEntity> getBulletinsWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ControllerBulletinsEntity> localReturnType = new ParameterizedTypeReference<ControllerBulletinsEntity>() {};
        return apiClient.invokeAPI("/flow/controller/bulletins", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * The cluster summary for this NiFi
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ClusterSummaryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ClusterSummaryEntity getClusterSummary() throws RestClientException {
        return getClusterSummaryWithHttpInfo().getBody();
    }

    /**
     * The cluster summary for this NiFi
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ClusterSummaryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ClusterSummaryEntity> getClusterSummaryWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ClusterSummaryEntity> localReturnType = new ParameterizedTypeReference<ClusterSummaryEntity>() {};
        return apiClient.invokeAPI("/flow/cluster/summary", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets configuration history for a component
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param componentId The component id. (required)
     * @return ComponentHistoryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentHistoryEntity getComponentHistory(String componentId) throws RestClientException {
        return getComponentHistoryWithHttpInfo(componentId).getBody();
    }

    /**
     * Gets configuration history for a component
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param componentId The component id. (required)
     * @return ResponseEntity&lt;ComponentHistoryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentHistoryEntity> getComponentHistoryWithHttpInfo(String componentId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'componentId' is set
        if (componentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'componentId' when calling getComponentHistory");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("componentId", componentId);

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

        ParameterizedTypeReference<ComponentHistoryEntity> localReturnType = new ParameterizedTypeReference<ComponentHistoryEntity>() {};
        return apiClient.invokeAPI("/flow/history/components/{componentId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets statistics for a connection
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the statistics. (optional)
     * @return ConnectionStatisticsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectionStatisticsEntity getConnectionStatistics(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        return getConnectionStatisticsWithHttpInfo(id, nodewise, clusterNodeId).getBody();
    }

    /**
     * Gets statistics for a connection
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the statistics. (optional)
     * @return ResponseEntity&lt;ConnectionStatisticsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectionStatisticsEntity> getConnectionStatisticsWithHttpInfo(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnectionStatistics");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "nodewise", nodewise));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ConnectionStatisticsEntity> localReturnType = new ParameterizedTypeReference<ConnectionStatisticsEntity>() {};
        return apiClient.invokeAPI("/flow/connections/{id}/statistics", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets status for a connection
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ConnectionStatusEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectionStatusEntity getConnectionStatus(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        return getConnectionStatusWithHttpInfo(id, nodewise, clusterNodeId).getBody();
    }

    /**
     * Gets status for a connection
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ResponseEntity&lt;ConnectionStatusEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectionStatusEntity> getConnectionStatusWithHttpInfo(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnectionStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "nodewise", nodewise));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ConnectionStatusEntity> localReturnType = new ParameterizedTypeReference<ConnectionStatusEntity>() {};
        return apiClient.invokeAPI("/flow/connections/{id}/status", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the status history for a connection
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @return StatusHistoryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public StatusHistoryEntity getConnectionStatusHistory(String id) throws RestClientException {
        return getConnectionStatusHistoryWithHttpInfo(id).getBody();
    }

    /**
     * Gets the status history for a connection
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The connection id. (required)
     * @return ResponseEntity&lt;StatusHistoryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<StatusHistoryEntity> getConnectionStatusHistoryWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getConnectionStatusHistory");
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

        ParameterizedTypeReference<StatusHistoryEntity> localReturnType = new ParameterizedTypeReference<StatusHistoryEntity>() {};
        return apiClient.invokeAPI("/flow/connections/{id}/status/history", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the Connector Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The connector definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The connector type (required)
     * @return ConnectorDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorDefinition getConnectorDefinition(String group, String artifact, String version, String type) throws RestClientException {
        return getConnectorDefinitionWithHttpInfo(group, artifact, version, type).getBody();
    }

    /**
     * Retrieves the Connector Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The connector definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The connector type (required)
     * @return ResponseEntity&lt;ConnectorDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorDefinition> getConnectorDefinitionWithHttpInfo(String group, String artifact, String version, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling getConnectorDefinition");
        }
        
        // verify the required parameter 'artifact' is set
        if (artifact == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'artifact' when calling getConnectorDefinition");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling getConnectorDefinition");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getConnectorDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("group", group);
        uriVariables.put("artifact", artifact);
        uriVariables.put("version", version);
        uriVariables.put("type", type);

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

        ParameterizedTypeReference<ConnectorDefinition> localReturnType = new ParameterizedTypeReference<ConnectorDefinition>() {};
        return apiClient.invokeAPI("/flow/connector-definition/{group}/{artifact}/{version}/{type}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the types of connectors that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ConnectorTypesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorTypesEntity getConnectorTypes(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        return getConnectorTypesWithHttpInfo(bundleGroupFilter, bundleArtifactFilter, type).getBody();
    }

    /**
     * Retrieves the types of connectors that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ResponseEntity&lt;ConnectorTypesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorTypesEntity> getConnectorTypesWithHttpInfo(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleGroupFilter", bundleGroupFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleArtifactFilter", bundleArtifactFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ConnectorTypesEntity> localReturnType = new ParameterizedTypeReference<ConnectorTypesEntity>() {};
        return apiClient.invokeAPI("/flow/connector-types", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all connectors
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ConnectorsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConnectorsEntity getConnectors() throws RestClientException {
        return getConnectorsWithHttpInfo().getBody();
    }

    /**
     * Gets all connectors
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ConnectorsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConnectorsEntity> getConnectorsWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ConnectorsEntity> localReturnType = new ParameterizedTypeReference<ConnectorsEntity>() {};
        return apiClient.invokeAPI("/flow/connectors", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the registered content viewers
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ContentViewerEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ContentViewerEntity getContentViewers() throws RestClientException {
        return getContentViewersWithHttpInfo().getBody();
    }

    /**
     * Retrieves the registered content viewers
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ContentViewerEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ContentViewerEntity> getContentViewersWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ContentViewerEntity> localReturnType = new ParameterizedTypeReference<ContentViewerEntity>() {};
        return apiClient.invokeAPI("/flow/content-viewers", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the Controller Service Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The controller service definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The controller service type (required)
     * @return ControllerServiceDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerServiceDefinition getControllerServiceDefinition(String group, String artifact, String version, String type) throws RestClientException {
        return getControllerServiceDefinitionWithHttpInfo(group, artifact, version, type).getBody();
    }

    /**
     * Retrieves the Controller Service Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The controller service definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The controller service type (required)
     * @return ResponseEntity&lt;ControllerServiceDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerServiceDefinition> getControllerServiceDefinitionWithHttpInfo(String group, String artifact, String version, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling getControllerServiceDefinition");
        }
        
        // verify the required parameter 'artifact' is set
        if (artifact == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'artifact' when calling getControllerServiceDefinition");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling getControllerServiceDefinition");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getControllerServiceDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("group", group);
        uriVariables.put("artifact", artifact);
        uriVariables.put("version", version);
        uriVariables.put("type", type);

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

        ParameterizedTypeReference<ControllerServiceDefinition> localReturnType = new ParameterizedTypeReference<ControllerServiceDefinition>() {};
        return apiClient.invokeAPI("/flow/controller-service-definition/{group}/{artifact}/{version}/{type}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the types of controller services that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param serviceType If specified, will only return controller services that are compatible with this type of service. (optional)
     * @param serviceBundleGroup If serviceType specified, is the bundle group of the serviceType. (optional)
     * @param serviceBundleArtifact If serviceType specified, is the bundle artifact of the serviceType. (optional)
     * @param serviceBundleVersion If serviceType specified, is the bundle version of the serviceType. (optional)
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param typeFilter If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ControllerServiceTypesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerServiceTypesEntity getControllerServiceTypes(String serviceType, String serviceBundleGroup, String serviceBundleArtifact, String serviceBundleVersion, String bundleGroupFilter, String bundleArtifactFilter, String typeFilter) throws RestClientException {
        return getControllerServiceTypesWithHttpInfo(serviceType, serviceBundleGroup, serviceBundleArtifact, serviceBundleVersion, bundleGroupFilter, bundleArtifactFilter, typeFilter).getBody();
    }

    /**
     * Retrieves the types of controller services that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param serviceType If specified, will only return controller services that are compatible with this type of service. (optional)
     * @param serviceBundleGroup If serviceType specified, is the bundle group of the serviceType. (optional)
     * @param serviceBundleArtifact If serviceType specified, is the bundle artifact of the serviceType. (optional)
     * @param serviceBundleVersion If serviceType specified, is the bundle version of the serviceType. (optional)
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param typeFilter If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ResponseEntity&lt;ControllerServiceTypesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerServiceTypesEntity> getControllerServiceTypesWithHttpInfo(String serviceType, String serviceBundleGroup, String serviceBundleArtifact, String serviceBundleVersion, String bundleGroupFilter, String bundleArtifactFilter, String typeFilter) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "serviceType", serviceType));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "serviceBundleGroup", serviceBundleGroup));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "serviceBundleArtifact", serviceBundleArtifact));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "serviceBundleVersion", serviceBundleVersion));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleGroupFilter", bundleGroupFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleArtifactFilter", bundleArtifactFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "typeFilter", typeFilter));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ControllerServiceTypesEntity> localReturnType = new ParameterizedTypeReference<ControllerServiceTypesEntity>() {};
        return apiClient.invokeAPI("/flow/controller-service-types", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets controller services for reporting tasks
     * If the uiOnly query parameter is provided with a value of true, the returned entity may only contain fields that are necessary for rendering the NiFi User Interface. As such, the selected fields may change at any time, even during incremental releases, without warning. As a result, this parameter should not be provided by any client other than the UI.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param includeReferencingComponents Whether or not to include services&#39; referencing components in the response (optional, default to true)
     * @param uiOnly  (optional, default to false)
     * @return ControllerServicesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerServicesEntity getControllerServicesFromController(Boolean includeReferencingComponents, Boolean uiOnly) throws RestClientException {
        return getControllerServicesFromControllerWithHttpInfo(includeReferencingComponents, uiOnly).getBody();
    }

    /**
     * Gets controller services for reporting tasks
     * If the uiOnly query parameter is provided with a value of true, the returned entity may only contain fields that are necessary for rendering the NiFi User Interface. As such, the selected fields may change at any time, even during incremental releases, without warning. As a result, this parameter should not be provided by any client other than the UI.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param includeReferencingComponents Whether or not to include services&#39; referencing components in the response (optional, default to true)
     * @param uiOnly  (optional, default to false)
     * @return ResponseEntity&lt;ControllerServicesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerServicesEntity> getControllerServicesFromControllerWithHttpInfo(Boolean includeReferencingComponents, Boolean uiOnly) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeReferencingComponents", includeReferencingComponents));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "uiOnly", uiOnly));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ControllerServicesEntity> localReturnType = new ParameterizedTypeReference<ControllerServicesEntity>() {};
        return apiClient.invokeAPI("/flow/controller/controller-services", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all controller services
     * If the uiOnly query parameter is provided with a value of true, the returned entity may only contain fields that are necessary for rendering the NiFi User Interface. As such, the selected fields may change at any time, even during incremental releases, without warning. As a result, this parameter should not be provided by any client other than the UI.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param includeAncestorGroups Whether or not to include parent/ancestor process groups (optional, default to true)
     * @param includeDescendantGroups Whether or not to include descendant process groups (optional, default to false)
     * @param includeReferencingComponents Whether or not to include services&#39; referencing components in the response (optional, default to true)
     * @param uiOnly  (optional, default to false)
     * @return ControllerServicesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerServicesEntity getControllerServicesFromGroup(String id, Boolean includeAncestorGroups, Boolean includeDescendantGroups, Boolean includeReferencingComponents, Boolean uiOnly) throws RestClientException {
        return getControllerServicesFromGroupWithHttpInfo(id, includeAncestorGroups, includeDescendantGroups, includeReferencingComponents, uiOnly).getBody();
    }

    /**
     * Gets all controller services
     * If the uiOnly query parameter is provided with a value of true, the returned entity may only contain fields that are necessary for rendering the NiFi User Interface. As such, the selected fields may change at any time, even during incremental releases, without warning. As a result, this parameter should not be provided by any client other than the UI.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param includeAncestorGroups Whether or not to include parent/ancestor process groups (optional, default to true)
     * @param includeDescendantGroups Whether or not to include descendant process groups (optional, default to false)
     * @param includeReferencingComponents Whether or not to include services&#39; referencing components in the response (optional, default to true)
     * @param uiOnly  (optional, default to false)
     * @return ResponseEntity&lt;ControllerServicesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerServicesEntity> getControllerServicesFromGroupWithHttpInfo(String id, Boolean includeAncestorGroups, Boolean includeDescendantGroups, Boolean includeReferencingComponents, Boolean uiOnly) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getControllerServicesFromGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeAncestorGroups", includeAncestorGroups));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDescendantGroups", includeDescendantGroups));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeReferencingComponents", includeReferencingComponents));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "uiOnly", uiOnly));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ControllerServicesEntity> localReturnType = new ParameterizedTypeReference<ControllerServicesEntity>() {};
        return apiClient.invokeAPI("/flow/process-groups/{id}/controller-services", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the current status of this NiFi
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ControllerStatusEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerStatusEntity getControllerStatus() throws RestClientException {
        return getControllerStatusWithHttpInfo().getBody();
    }

    /**
     * Gets the current status of this NiFi
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ControllerStatusEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerStatusEntity> getControllerStatusWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ControllerStatusEntity> localReturnType = new ParameterizedTypeReference<ControllerStatusEntity>() {};
        return apiClient.invokeAPI("/flow/status", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the user identity of the user making the request
     * 
     * <p><b>0</b>
     * @return CurrentUserEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CurrentUserEntity getCurrentUser() throws RestClientException {
        return getCurrentUserWithHttpInfo().getBody();
    }

    /**
     * Retrieves the user identity of the user making the request
     * 
     * <p><b>0</b>
     * @return ResponseEntity&lt;CurrentUserEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CurrentUserEntity> getCurrentUserWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<CurrentUserEntity> localReturnType = new ParameterizedTypeReference<CurrentUserEntity>() {};
        return apiClient.invokeAPI("/flow/current-user", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the details of a flow from the specified registry and bucket for the specified flow for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param registryId The registry client id. (required)
     * @param bucketId The bucket id. (required)
     * @param flowId The flow id. (required)
     * @param branch The name of a branch to get the flow from. If not specified the default branch of the registry client will be used. (optional)
     * @return VersionedFlowEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VersionedFlowEntity getDetails(String registryId, String bucketId, String flowId, String branch) throws RestClientException {
        return getDetailsWithHttpInfo(registryId, bucketId, flowId, branch).getBody();
    }

    /**
     * Gets the details of a flow from the specified registry and bucket for the specified flow for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param registryId The registry client id. (required)
     * @param bucketId The bucket id. (required)
     * @param flowId The flow id. (required)
     * @param branch The name of a branch to get the flow from. If not specified the default branch of the registry client will be used. (optional)
     * @return ResponseEntity&lt;VersionedFlowEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VersionedFlowEntity> getDetailsWithHttpInfo(String registryId, String bucketId, String flowId, String branch) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'registryId' is set
        if (registryId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'registryId' when calling getDetails");
        }
        
        // verify the required parameter 'bucketId' is set
        if (bucketId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'bucketId' when calling getDetails");
        }
        
        // verify the required parameter 'flowId' is set
        if (flowId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowId' when calling getDetails");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("registry-id", registryId);
        uriVariables.put("bucket-id", bucketId);
        uriVariables.put("flow-id", flowId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "branch", branch));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<VersionedFlowEntity> localReturnType = new ParameterizedTypeReference<VersionedFlowEntity>() {};
        return apiClient.invokeAPI("/flow/registries/{registry-id}/buckets/{bucket-id}/flows/{flow-id}/details", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a process group
     * If the uiOnly query parameter is provided with a value of true, the returned entity may only contain fields that are necessary for rendering the NiFi User Interface. As such, the selected fields may change at any time, even during incremental releases, without warning. As a result, this parameter should not be provided by any client other than the UI.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param uiOnly  (optional, default to false)
     * @return ProcessGroupFlowEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupFlowEntity getFlow1(String id, Boolean uiOnly) throws RestClientException {
        return getFlow1WithHttpInfo(id, uiOnly).getBody();
    }

    /**
     * Gets a process group
     * If the uiOnly query parameter is provided with a value of true, the returned entity may only contain fields that are necessary for rendering the NiFi User Interface. As such, the selected fields may change at any time, even during incremental releases, without warning. As a result, this parameter should not be provided by any client other than the UI.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param uiOnly  (optional, default to false)
     * @return ResponseEntity&lt;ProcessGroupFlowEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupFlowEntity> getFlow1WithHttpInfo(String id, Boolean uiOnly) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFlow1");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

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
        return apiClient.invokeAPI("/flow/process-groups/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns flow analysis results produced by the analysis of a given process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param processGroupId The id of the process group representing (a part of) the flow to be analyzed. (required)
     * @return FlowAnalysisResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisResultEntity getFlowAnalysisResults(String processGroupId) throws RestClientException {
        return getFlowAnalysisResultsWithHttpInfo(processGroupId).getBody();
    }

    /**
     * Returns flow analysis results produced by the analysis of a given process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param processGroupId The id of the process group representing (a part of) the flow to be analyzed. (required)
     * @return ResponseEntity&lt;FlowAnalysisResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisResultEntity> getFlowAnalysisResultsWithHttpInfo(String processGroupId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'processGroupId' is set
        if (processGroupId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'processGroupId' when calling getFlowAnalysisResults");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("processGroupId", processGroupId);

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

        ParameterizedTypeReference<FlowAnalysisResultEntity> localReturnType = new ParameterizedTypeReference<FlowAnalysisResultEntity>() {};
        return apiClient.invokeAPI("/flow/flow-analysis/results/{processGroupId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the Flow Analysis Rule Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The flow analysis rule definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The flow analysis rule type (required)
     * @return FlowAnalysisRuleDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisRuleDefinition getFlowAnalysisRuleDefinition(String group, String artifact, String version, String type) throws RestClientException {
        return getFlowAnalysisRuleDefinitionWithHttpInfo(group, artifact, version, type).getBody();
    }

    /**
     * Retrieves the Flow Analysis Rule Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The flow analysis rule definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The flow analysis rule type (required)
     * @return ResponseEntity&lt;FlowAnalysisRuleDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisRuleDefinition> getFlowAnalysisRuleDefinitionWithHttpInfo(String group, String artifact, String version, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling getFlowAnalysisRuleDefinition");
        }
        
        // verify the required parameter 'artifact' is set
        if (artifact == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'artifact' when calling getFlowAnalysisRuleDefinition");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling getFlowAnalysisRuleDefinition");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getFlowAnalysisRuleDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("group", group);
        uriVariables.put("artifact", artifact);
        uriVariables.put("version", version);
        uriVariables.put("type", type);

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

        ParameterizedTypeReference<FlowAnalysisRuleDefinition> localReturnType = new ParameterizedTypeReference<FlowAnalysisRuleDefinition>() {};
        return apiClient.invokeAPI("/flow/flow-analysis-rule-definition/{group}/{artifact}/{version}/{type}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the types of available Flow Analysis Rules
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return FlowAnalysisRuleTypesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisRuleTypesEntity getFlowAnalysisRuleTypes(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        return getFlowAnalysisRuleTypesWithHttpInfo(bundleGroupFilter, bundleArtifactFilter, type).getBody();
    }

    /**
     * Retrieves the types of available Flow Analysis Rules
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ResponseEntity&lt;FlowAnalysisRuleTypesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisRuleTypesEntity> getFlowAnalysisRuleTypesWithHttpInfo(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleGroupFilter", bundleGroupFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleArtifactFilter", bundleArtifactFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<FlowAnalysisRuleTypesEntity> localReturnType = new ParameterizedTypeReference<FlowAnalysisRuleTypesEntity>() {};
        return apiClient.invokeAPI("/flow/flow-analysis-rule-types", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the configuration for this NiFi flow
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return FlowConfigurationEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowConfigurationEntity getFlowConfig() throws RestClientException {
        return getFlowConfigWithHttpInfo().getBody();
    }

    /**
     * Retrieves the configuration for this NiFi flow
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;FlowConfigurationEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowConfigurationEntity> getFlowConfigWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<FlowConfigurationEntity> localReturnType = new ParameterizedTypeReference<FlowConfigurationEntity>() {};
        return apiClient.invokeAPI("/flow/config", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all metrics for the flow from a particular node
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param producer The producer for flow file metrics. Each producer may have its own output format. (required)
     * @param includedRegistries Set of included metrics registries. Duplicate the parameter to include multiple registries. All registries are included by default. (optional)
     * @param sampleName Regular Expression Pattern to be applied against the sample name field (optional)
     * @param sampleLabelValue Regular Expression Pattern to be applied against the sample label value field (optional)
     * @param rootFieldName Name of the first field of JSON object. Applicable for JSON producer only. (optional)
     * @param flowMetricsReportingStrategy Flow metrics reporting strategy limits collected metrics (optional, default to ALL_COMPONENTS)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getFlowMetrics(String producer, String includedRegistries, String sampleName, String sampleLabelValue, String rootFieldName, String flowMetricsReportingStrategy) throws RestClientException {
        return getFlowMetricsWithHttpInfo(producer, includedRegistries, sampleName, sampleLabelValue, rootFieldName, flowMetricsReportingStrategy).getBody();
    }

    /**
     * Gets all metrics for the flow from a particular node
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param producer The producer for flow file metrics. Each producer may have its own output format. (required)
     * @param includedRegistries Set of included metrics registries. Duplicate the parameter to include multiple registries. All registries are included by default. (optional)
     * @param sampleName Regular Expression Pattern to be applied against the sample name field (optional)
     * @param sampleLabelValue Regular Expression Pattern to be applied against the sample label value field (optional)
     * @param rootFieldName Name of the first field of JSON object. Applicable for JSON producer only. (optional)
     * @param flowMetricsReportingStrategy Flow metrics reporting strategy limits collected metrics (optional, default to ALL_COMPONENTS)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getFlowMetricsWithHttpInfo(String producer, String includedRegistries, String sampleName, String sampleLabelValue, String rootFieldName, String flowMetricsReportingStrategy) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'producer' is set
        if (producer == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'producer' when calling getFlowMetrics");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("producer", producer);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "includedRegistries", includedRegistries));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sampleName", sampleName));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sampleLabelValue", sampleLabelValue));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootFieldName", rootFieldName));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "flowMetricsReportingStrategy", flowMetricsReportingStrategy));
        

        final String[] localVarAccepts = { 
            "*/*"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<Object> localReturnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI("/flow/metrics/{producer}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the Flow Registry Client Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The flow registry client definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The flow registry client type (required)
     * @return FlowRegistryClientDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowRegistryClientDefinition getFlowRegistryClientDefinition(String group, String artifact, String version, String type) throws RestClientException {
        return getFlowRegistryClientDefinitionWithHttpInfo(group, artifact, version, type).getBody();
    }

    /**
     * Retrieves the Flow Registry Client Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The flow registry client definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The flow registry client type (required)
     * @return ResponseEntity&lt;FlowRegistryClientDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowRegistryClientDefinition> getFlowRegistryClientDefinitionWithHttpInfo(String group, String artifact, String version, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling getFlowRegistryClientDefinition");
        }
        
        // verify the required parameter 'artifact' is set
        if (artifact == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'artifact' when calling getFlowRegistryClientDefinition");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling getFlowRegistryClientDefinition");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getFlowRegistryClientDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("group", group);
        uriVariables.put("artifact", artifact);
        uriVariables.put("version", version);
        uriVariables.put("type", type);

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

        ParameterizedTypeReference<FlowRegistryClientDefinition> localReturnType = new ParameterizedTypeReference<FlowRegistryClientDefinition>() {};
        return apiClient.invokeAPI("/flow/flow-registry-client-definition/{group}/{artifact}/{version}/{type}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the flows from the specified registry and bucket for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param registryId The registry client id. (required)
     * @param bucketId The bucket id. (required)
     * @param branch The name of a branch to get the flows from. If not specified the default branch of the registry client will be used. (optional)
     * @return VersionedFlowsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VersionedFlowsEntity getFlows(String registryId, String bucketId, String branch) throws RestClientException {
        return getFlowsWithHttpInfo(registryId, bucketId, branch).getBody();
    }

    /**
     * Gets the flows from the specified registry and bucket for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param registryId The registry client id. (required)
     * @param bucketId The bucket id. (required)
     * @param branch The name of a branch to get the flows from. If not specified the default branch of the registry client will be used. (optional)
     * @return ResponseEntity&lt;VersionedFlowsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VersionedFlowsEntity> getFlowsWithHttpInfo(String registryId, String bucketId, String branch) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'registryId' is set
        if (registryId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'registryId' when calling getFlows");
        }
        
        // verify the required parameter 'bucketId' is set
        if (bucketId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'bucketId' when calling getFlows");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("registry-id", registryId);
        uriVariables.put("bucket-id", bucketId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "branch", branch));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<VersionedFlowsEntity> localReturnType = new ParameterizedTypeReference<VersionedFlowsEntity>() {};
        return apiClient.invokeAPI("/flow/registries/{registry-id}/buckets/{bucket-id}/flows", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets status for an input port
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The input port id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return PortStatusEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PortStatusEntity getInputPortStatus(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        return getInputPortStatusWithHttpInfo(id, nodewise, clusterNodeId).getBody();
    }

    /**
     * Gets status for an input port
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The input port id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ResponseEntity&lt;PortStatusEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PortStatusEntity> getInputPortStatusWithHttpInfo(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getInputPortStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "nodewise", nodewise));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<PortStatusEntity> localReturnType = new ParameterizedTypeReference<PortStatusEntity>() {};
        return apiClient.invokeAPI("/flow/input-ports/{id}/status", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all listen ports configured on this NiFi that the current user has access to
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ListenPortsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListenPortsEntity getListenPorts() throws RestClientException {
        return getListenPortsWithHttpInfo().getBody();
    }

    /**
     * Gets all listen ports configured on this NiFi that the current user has access to
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ListenPortsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListenPortsEntity> getListenPortsWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ListenPortsEntity> localReturnType = new ParameterizedTypeReference<ListenPortsEntity>() {};
        return apiClient.invokeAPI("/flow/listen-ports", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets status for an output port
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The output port id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return PortStatusEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PortStatusEntity getOutputPortStatus(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        return getOutputPortStatusWithHttpInfo(id, nodewise, clusterNodeId).getBody();
    }

    /**
     * Gets status for an output port
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The output port id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ResponseEntity&lt;PortStatusEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PortStatusEntity> getOutputPortStatusWithHttpInfo(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getOutputPortStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "nodewise", nodewise));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<PortStatusEntity> localReturnType = new ParameterizedTypeReference<PortStatusEntity>() {};
        return apiClient.invokeAPI("/flow/output-ports/{id}/status", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all Parameter Contexts
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ParameterContextsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterContextsEntity getParameterContexts() throws RestClientException {
        return getParameterContextsWithHttpInfo().getBody();
    }

    /**
     * Gets all Parameter Contexts
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ParameterContextsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterContextsEntity> getParameterContextsWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ParameterContextsEntity> localReturnType = new ParameterizedTypeReference<ParameterContextsEntity>() {};
        return apiClient.invokeAPI("/flow/parameter-contexts", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the Parameter Provider Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The reporting task definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The parameter provider type (required)
     * @return ParameterProviderDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderDefinition getParameterProviderDefinition(String group, String artifact, String version, String type) throws RestClientException {
        return getParameterProviderDefinitionWithHttpInfo(group, artifact, version, type).getBody();
    }

    /**
     * Retrieves the Parameter Provider Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The reporting task definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The parameter provider type (required)
     * @return ResponseEntity&lt;ParameterProviderDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderDefinition> getParameterProviderDefinitionWithHttpInfo(String group, String artifact, String version, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling getParameterProviderDefinition");
        }
        
        // verify the required parameter 'artifact' is set
        if (artifact == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'artifact' when calling getParameterProviderDefinition");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling getParameterProviderDefinition");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getParameterProviderDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("group", group);
        uriVariables.put("artifact", artifact);
        uriVariables.put("version", version);
        uriVariables.put("type", type);

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

        ParameterizedTypeReference<ParameterProviderDefinition> localReturnType = new ParameterizedTypeReference<ParameterProviderDefinition>() {};
        return apiClient.invokeAPI("/flow/parameter-provider-definition/{group}/{artifact}/{version}/{type}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the types of parameter providers that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ParameterProviderTypesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderTypesEntity getParameterProviderTypes(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        return getParameterProviderTypesWithHttpInfo(bundleGroupFilter, bundleArtifactFilter, type).getBody();
    }

    /**
     * Retrieves the types of parameter providers that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ResponseEntity&lt;ParameterProviderTypesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderTypesEntity> getParameterProviderTypesWithHttpInfo(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleGroupFilter", bundleGroupFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleArtifactFilter", bundleArtifactFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ParameterProviderTypesEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderTypesEntity>() {};
        return apiClient.invokeAPI("/flow/parameter-provider-types", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all parameter providers
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ParameterProvidersEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProvidersEntity getParameterProviders() throws RestClientException {
        return getParameterProvidersWithHttpInfo().getBody();
    }

    /**
     * Gets all parameter providers
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ParameterProvidersEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProvidersEntity> getParameterProvidersWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ParameterProvidersEntity> localReturnType = new ParameterizedTypeReference<ParameterProvidersEntity>() {};
        return apiClient.invokeAPI("/flow/parameter-providers", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the types of prioritizers that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return PrioritizerTypesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PrioritizerTypesEntity getPrioritizers() throws RestClientException {
        return getPrioritizersWithHttpInfo().getBody();
    }

    /**
     * Retrieves the types of prioritizers that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;PrioritizerTypesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PrioritizerTypesEntity> getPrioritizersWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<PrioritizerTypesEntity> localReturnType = new ParameterizedTypeReference<PrioritizerTypesEntity>() {};
        return apiClient.invokeAPI("/flow/prioritizers", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the status for a process group
     * The status for a process group includes status for all descendent components. When invoked on the root group with recursive set to true, it will return the current status of every component in the flow.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param recursive Whether all descendant groups and the status of their content will be included. Optional, defaults to false (optional, default to false)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ProcessGroupStatusEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessGroupStatusEntity getProcessGroupStatus(String id, Boolean recursive, Boolean nodewise, String clusterNodeId) throws RestClientException {
        return getProcessGroupStatusWithHttpInfo(id, recursive, nodewise, clusterNodeId).getBody();
    }

    /**
     * Gets the status for a process group
     * The status for a process group includes status for all descendent components. When invoked on the root group with recursive set to true, it will return the current status of every component in the flow.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param recursive Whether all descendant groups and the status of their content will be included. Optional, defaults to false (optional, default to false)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ResponseEntity&lt;ProcessGroupStatusEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessGroupStatusEntity> getProcessGroupStatusWithHttpInfo(String id, Boolean recursive, Boolean nodewise, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessGroupStatus");
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
        return apiClient.invokeAPI("/flow/process-groups/{id}/status", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets status history for a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return StatusHistoryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public StatusHistoryEntity getProcessGroupStatusHistory(String id) throws RestClientException {
        return getProcessGroupStatusHistoryWithHttpInfo(id).getBody();
    }

    /**
     * Gets status history for a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @return ResponseEntity&lt;StatusHistoryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<StatusHistoryEntity> getProcessGroupStatusHistoryWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessGroupStatusHistory");
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

        ParameterizedTypeReference<StatusHistoryEntity> localReturnType = new ParameterizedTypeReference<StatusHistoryEntity>() {};
        return apiClient.invokeAPI("/flow/process-groups/{id}/status/history", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the Processor Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The processor definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The processor type (required)
     * @return ProcessorDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessorDefinition getProcessorDefinition(String group, String artifact, String version, String type) throws RestClientException {
        return getProcessorDefinitionWithHttpInfo(group, artifact, version, type).getBody();
    }

    /**
     * Retrieves the Processor Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The processor definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The processor type (required)
     * @return ResponseEntity&lt;ProcessorDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessorDefinition> getProcessorDefinitionWithHttpInfo(String group, String artifact, String version, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling getProcessorDefinition");
        }
        
        // verify the required parameter 'artifact' is set
        if (artifact == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'artifact' when calling getProcessorDefinition");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling getProcessorDefinition");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getProcessorDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("group", group);
        uriVariables.put("artifact", artifact);
        uriVariables.put("version", version);
        uriVariables.put("type", type);

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

        ParameterizedTypeReference<ProcessorDefinition> localReturnType = new ParameterizedTypeReference<ProcessorDefinition>() {};
        return apiClient.invokeAPI("/flow/processor-definition/{group}/{artifact}/{version}/{type}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets status for a processor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The processor id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ProcessorStatusEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessorStatusEntity getProcessorStatus(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        return getProcessorStatusWithHttpInfo(id, nodewise, clusterNodeId).getBody();
    }

    /**
     * Gets status for a processor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The processor id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ResponseEntity&lt;ProcessorStatusEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessorStatusEntity> getProcessorStatusWithHttpInfo(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessorStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "nodewise", nodewise));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ProcessorStatusEntity> localReturnType = new ParameterizedTypeReference<ProcessorStatusEntity>() {};
        return apiClient.invokeAPI("/flow/processors/{id}/status", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets status history for a processor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The processor id. (required)
     * @return StatusHistoryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public StatusHistoryEntity getProcessorStatusHistory(String id) throws RestClientException {
        return getProcessorStatusHistoryWithHttpInfo(id).getBody();
    }

    /**
     * Gets status history for a processor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The processor id. (required)
     * @return ResponseEntity&lt;StatusHistoryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<StatusHistoryEntity> getProcessorStatusHistoryWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessorStatusHistory");
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

        ParameterizedTypeReference<StatusHistoryEntity> localReturnType = new ParameterizedTypeReference<StatusHistoryEntity>() {};
        return apiClient.invokeAPI("/flow/processors/{id}/status/history", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the types of processors that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ProcessorTypesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessorTypesEntity getProcessorTypes(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        return getProcessorTypesWithHttpInfo(bundleGroupFilter, bundleArtifactFilter, type).getBody();
    }

    /**
     * Retrieves the types of processors that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ResponseEntity&lt;ProcessorTypesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessorTypesEntity> getProcessorTypesWithHttpInfo(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleGroupFilter", bundleGroupFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleArtifactFilter", bundleArtifactFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ProcessorTypesEntity> localReturnType = new ParameterizedTypeReference<ProcessorTypesEntity>() {};
        return apiClient.invokeAPI("/flow/processor-types", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the listing of available flow registry clients
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return FlowRegistryClientsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowRegistryClientsEntity getRegistryClients() throws RestClientException {
        return getRegistryClientsWithHttpInfo().getBody();
    }

    /**
     * Gets the listing of available flow registry clients
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;FlowRegistryClientsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowRegistryClientsEntity> getRegistryClientsWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<FlowRegistryClientsEntity> localReturnType = new ParameterizedTypeReference<FlowRegistryClientsEntity>() {};
        return apiClient.invokeAPI("/flow/registries", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets status for a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return RemoteProcessGroupStatusEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RemoteProcessGroupStatusEntity getRemoteProcessGroupStatus(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        return getRemoteProcessGroupStatusWithHttpInfo(id, nodewise, clusterNodeId).getBody();
    }

    /**
     * Gets status for a remote process group
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ResponseEntity&lt;RemoteProcessGroupStatusEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RemoteProcessGroupStatusEntity> getRemoteProcessGroupStatusWithHttpInfo(String id, Boolean nodewise, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRemoteProcessGroupStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "nodewise", nodewise));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<RemoteProcessGroupStatusEntity> localReturnType = new ParameterizedTypeReference<RemoteProcessGroupStatusEntity>() {};
        return apiClient.invokeAPI("/flow/remote-process-groups/{id}/status", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the status history
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @return StatusHistoryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public StatusHistoryEntity getRemoteProcessGroupStatusHistory(String id) throws RestClientException {
        return getRemoteProcessGroupStatusHistoryWithHttpInfo(id).getBody();
    }

    /**
     * Gets the status history
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The remote process group id. (required)
     * @return ResponseEntity&lt;StatusHistoryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<StatusHistoryEntity> getRemoteProcessGroupStatusHistoryWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRemoteProcessGroupStatusHistory");
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

        ParameterizedTypeReference<StatusHistoryEntity> localReturnType = new ParameterizedTypeReference<StatusHistoryEntity>() {};
        return apiClient.invokeAPI("/flow/remote-process-groups/{id}/status/history", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the Reporting Task Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The reporting task definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The reporting task type (required)
     * @return ReportingTaskDefinition
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ReportingTaskDefinition getReportingTaskDefinition(String group, String artifact, String version, String type) throws RestClientException {
        return getReportingTaskDefinitionWithHttpInfo(group, artifact, version, type).getBody();
    }

    /**
     * Retrieves the Reporting Task Definition for the specified component type.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The reporting task definition for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param type The reporting task type (required)
     * @return ResponseEntity&lt;ReportingTaskDefinition&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ReportingTaskDefinition> getReportingTaskDefinitionWithHttpInfo(String group, String artifact, String version, String type) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling getReportingTaskDefinition");
        }
        
        // verify the required parameter 'artifact' is set
        if (artifact == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'artifact' when calling getReportingTaskDefinition");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling getReportingTaskDefinition");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getReportingTaskDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("group", group);
        uriVariables.put("artifact", artifact);
        uriVariables.put("version", version);
        uriVariables.put("type", type);

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

        ParameterizedTypeReference<ReportingTaskDefinition> localReturnType = new ParameterizedTypeReference<ReportingTaskDefinition>() {};
        return apiClient.invokeAPI("/flow/reporting-task-definition/{group}/{artifact}/{version}/{type}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get a snapshot of the given reporting tasks and any controller services they use
     * 
     * <p><b>200</b>
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param reportingTaskId Specifies a reporting task id to export. If not specified, all reporting tasks will be exported. (optional)
     * @return VersionedReportingTaskSnapshot
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VersionedReportingTaskSnapshot getReportingTaskSnapshot(String reportingTaskId) throws RestClientException {
        return getReportingTaskSnapshotWithHttpInfo(reportingTaskId).getBody();
    }

    /**
     * Get a snapshot of the given reporting tasks and any controller services they use
     * 
     * <p><b>200</b>
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param reportingTaskId Specifies a reporting task id to export. If not specified, all reporting tasks will be exported. (optional)
     * @return ResponseEntity&lt;VersionedReportingTaskSnapshot&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VersionedReportingTaskSnapshot> getReportingTaskSnapshotWithHttpInfo(String reportingTaskId) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "reportingTaskId", reportingTaskId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<VersionedReportingTaskSnapshot> localReturnType = new ParameterizedTypeReference<VersionedReportingTaskSnapshot>() {};
        return apiClient.invokeAPI("/flow/reporting-tasks/snapshot", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the types of reporting tasks that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ReportingTaskTypesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ReportingTaskTypesEntity getReportingTaskTypes(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        return getReportingTaskTypesWithHttpInfo(bundleGroupFilter, bundleArtifactFilter, type).getBody();
    }

    /**
     * Retrieves the types of reporting tasks that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bundleGroupFilter If specified, will only return types that are a member of this bundle group. (optional)
     * @param bundleArtifactFilter If specified, will only return types that are a member of this bundle artifact. (optional)
     * @param type If specified, will only return types whose fully qualified classname matches. (optional)
     * @return ResponseEntity&lt;ReportingTaskTypesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ReportingTaskTypesEntity> getReportingTaskTypesWithHttpInfo(String bundleGroupFilter, String bundleArtifactFilter, String type) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleGroupFilter", bundleGroupFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "bundleArtifactFilter", bundleArtifactFilter));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<ReportingTaskTypesEntity> localReturnType = new ParameterizedTypeReference<ReportingTaskTypesEntity>() {};
        return apiClient.invokeAPI("/flow/reporting-task-types", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all reporting tasks
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ReportingTasksEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ReportingTasksEntity getReportingTasks() throws RestClientException {
        return getReportingTasksWithHttpInfo().getBody();
    }

    /**
     * Gets all reporting tasks
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ReportingTasksEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ReportingTasksEntity> getReportingTasksWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<ReportingTasksEntity> localReturnType = new ParameterizedTypeReference<ReportingTasksEntity>() {};
        return apiClient.invokeAPI("/flow/reporting-tasks", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the runtime manifest for this NiFi instance.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return RuntimeManifestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RuntimeManifestEntity getRuntimeManifest() throws RestClientException {
        return getRuntimeManifestWithHttpInfo().getBody();
    }

    /**
     * Retrieves the runtime manifest for this NiFi instance.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;RuntimeManifestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RuntimeManifestEntity> getRuntimeManifestWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

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

        ParameterizedTypeReference<RuntimeManifestEntity> localReturnType = new ParameterizedTypeReference<RuntimeManifestEntity>() {};
        return apiClient.invokeAPI("/flow/runtime-manifest", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the step documentation for the specified Connector configuration step.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The step documentation for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param connectorType The fully qualified Connector type (required)
     * @param stepName The configuration step name (required)
     * @return StepDocumentationEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public StepDocumentationEntity getStepDocumentation(String group, String artifact, String version, String connectorType, String stepName) throws RestClientException {
        return getStepDocumentationWithHttpInfo(group, artifact, version, connectorType, stepName).getBody();
    }

    /**
     * Retrieves the step documentation for the specified Connector configuration step.
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The step documentation for the coordinates could not be located.
     * @param group The bundle group (required)
     * @param artifact The bundle artifact (required)
     * @param version The bundle version (required)
     * @param connectorType The fully qualified Connector type (required)
     * @param stepName The configuration step name (required)
     * @return ResponseEntity&lt;StepDocumentationEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<StepDocumentationEntity> getStepDocumentationWithHttpInfo(String group, String artifact, String version, String connectorType, String stepName) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling getStepDocumentation");
        }
        
        // verify the required parameter 'artifact' is set
        if (artifact == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'artifact' when calling getStepDocumentation");
        }
        
        // verify the required parameter 'version' is set
        if (version == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'version' when calling getStepDocumentation");
        }
        
        // verify the required parameter 'connectorType' is set
        if (connectorType == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'connectorType' when calling getStepDocumentation");
        }
        
        // verify the required parameter 'stepName' is set
        if (stepName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'stepName' when calling getStepDocumentation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("group", group);
        uriVariables.put("artifact", artifact);
        uriVariables.put("version", version);
        uriVariables.put("connectorType", connectorType);
        uriVariables.put("stepName", stepName);

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

        ParameterizedTypeReference<StepDocumentationEntity> localReturnType = new ParameterizedTypeReference<StepDocumentationEntity>() {};
        return apiClient.invokeAPI("/flow/steps/{group}/{artifact}/{version}/{connectorType}/{stepName}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the differences between two versions of the same versioned flow, the basis of the comparison will be the first version
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param registryId The registry client id. (required)
     * @param branchIdA The branch id for the base version. (required)
     * @param bucketIdA The bucket id for the base version. (required)
     * @param flowIdA The flow id for the base version. (required)
     * @param versionA The base version. (required)
     * @param branchIdB The branch id for the compared version. (required)
     * @param bucketIdB The bucket id for the compared version. (required)
     * @param flowIdB The flow id for the compared version. (required)
     * @param versionB The compared version. (required)
     * @param offset Must be a non-negative number. Specifies the starting point of the listing. 0 means start from the beginning. (optional, default to 0)
     * @param limit Limits the number of differences listed. This might lead to partial result. 0 means no limitation is applied. (optional, default to 1000)
     * @return FlowComparisonEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowComparisonEntity getVersionDifferences(String registryId, String branchIdA, String bucketIdA, String flowIdA, String versionA, String branchIdB, String bucketIdB, String flowIdB, String versionB, Integer offset, Integer limit) throws RestClientException {
        return getVersionDifferencesWithHttpInfo(registryId, branchIdA, bucketIdA, flowIdA, versionA, branchIdB, bucketIdB, flowIdB, versionB, offset, limit).getBody();
    }

    /**
     * Gets the differences between two versions of the same versioned flow, the basis of the comparison will be the first version
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param registryId The registry client id. (required)
     * @param branchIdA The branch id for the base version. (required)
     * @param bucketIdA The bucket id for the base version. (required)
     * @param flowIdA The flow id for the base version. (required)
     * @param versionA The base version. (required)
     * @param branchIdB The branch id for the compared version. (required)
     * @param bucketIdB The bucket id for the compared version. (required)
     * @param flowIdB The flow id for the compared version. (required)
     * @param versionB The compared version. (required)
     * @param offset Must be a non-negative number. Specifies the starting point of the listing. 0 means start from the beginning. (optional, default to 0)
     * @param limit Limits the number of differences listed. This might lead to partial result. 0 means no limitation is applied. (optional, default to 1000)
     * @return ResponseEntity&lt;FlowComparisonEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowComparisonEntity> getVersionDifferencesWithHttpInfo(String registryId, String branchIdA, String bucketIdA, String flowIdA, String versionA, String branchIdB, String bucketIdB, String flowIdB, String versionB, Integer offset, Integer limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'registryId' is set
        if (registryId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'registryId' when calling getVersionDifferences");
        }
        
        // verify the required parameter 'branchIdA' is set
        if (branchIdA == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'branchIdA' when calling getVersionDifferences");
        }
        
        // verify the required parameter 'bucketIdA' is set
        if (bucketIdA == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'bucketIdA' when calling getVersionDifferences");
        }
        
        // verify the required parameter 'flowIdA' is set
        if (flowIdA == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowIdA' when calling getVersionDifferences");
        }
        
        // verify the required parameter 'versionA' is set
        if (versionA == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'versionA' when calling getVersionDifferences");
        }
        
        // verify the required parameter 'branchIdB' is set
        if (branchIdB == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'branchIdB' when calling getVersionDifferences");
        }
        
        // verify the required parameter 'bucketIdB' is set
        if (bucketIdB == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'bucketIdB' when calling getVersionDifferences");
        }
        
        // verify the required parameter 'flowIdB' is set
        if (flowIdB == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowIdB' when calling getVersionDifferences");
        }
        
        // verify the required parameter 'versionB' is set
        if (versionB == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'versionB' when calling getVersionDifferences");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("registry-id", registryId);
        uriVariables.put("branch-id-a", branchIdA);
        uriVariables.put("bucket-id-a", bucketIdA);
        uriVariables.put("flow-id-a", flowIdA);
        uriVariables.put("version-a", versionA);
        uriVariables.put("branch-id-b", branchIdB);
        uriVariables.put("bucket-id-b", bucketIdB);
        uriVariables.put("flow-id-b", flowIdB);
        uriVariables.put("version-b", versionB);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "offset", offset));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<FlowComparisonEntity> localReturnType = new ParameterizedTypeReference<FlowComparisonEntity>() {};
        return apiClient.invokeAPI("/flow/registries/{registry-id}/branches/{branch-id-a}/buckets/{bucket-id-a}/flows/{flow-id-a}/{version-a}/diff/branches/{branch-id-b}/buckets/{bucket-id-b}/flows/{flow-id-b}/{version-b}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the flow versions from the specified registry and bucket for the specified flow for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param registryId The registry client id. (required)
     * @param bucketId The bucket id. (required)
     * @param flowId The flow id. (required)
     * @param branch The name of a branch to get the flow versions from. If not specified the default branch of the registry client will be used. (optional)
     * @return VersionedFlowSnapshotMetadataSetEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VersionedFlowSnapshotMetadataSetEntity getVersions(String registryId, String bucketId, String flowId, String branch) throws RestClientException {
        return getVersionsWithHttpInfo(registryId, bucketId, flowId, branch).getBody();
    }

    /**
     * Gets the flow versions from the specified registry and bucket for the specified flow for the current user
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param registryId The registry client id. (required)
     * @param bucketId The bucket id. (required)
     * @param flowId The flow id. (required)
     * @param branch The name of a branch to get the flow versions from. If not specified the default branch of the registry client will be used. (optional)
     * @return ResponseEntity&lt;VersionedFlowSnapshotMetadataSetEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VersionedFlowSnapshotMetadataSetEntity> getVersionsWithHttpInfo(String registryId, String bucketId, String flowId, String branch) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'registryId' is set
        if (registryId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'registryId' when calling getVersions");
        }
        
        // verify the required parameter 'bucketId' is set
        if (bucketId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'bucketId' when calling getVersions");
        }
        
        // verify the required parameter 'flowId' is set
        if (flowId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowId' when calling getVersions");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("registry-id", registryId);
        uriVariables.put("bucket-id", bucketId);
        uriVariables.put("flow-id", flowId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "branch", branch));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<VersionedFlowSnapshotMetadataSetEntity> localReturnType = new ParameterizedTypeReference<VersionedFlowSnapshotMetadataSetEntity>() {};
        return apiClient.invokeAPI("/flow/registries/{registry-id}/buckets/{bucket-id}/flows/{flow-id}/versions", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets configuration history
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param offset The offset into the result set. (required)
     * @param count The number of actions to return. (required)
     * @param sortColumn The field to sort on. (optional)
     * @param sortOrder The direction to sort. (optional)
     * @param startDate Include actions after this date. (optional)
     * @param endDate Include actions before this date. (optional)
     * @param userIdentity Include actions performed by this user. (optional)
     * @param sourceId Include actions on this component. (optional)
     * @return HistoryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoryEntity queryHistory(IntegerParameter offset, IntegerParameter count, String sortColumn, String sortOrder, DateTimeParameter startDate, DateTimeParameter endDate, String userIdentity, String sourceId) throws RestClientException {
        return queryHistoryWithHttpInfo(offset, count, sortColumn, sortOrder, startDate, endDate, userIdentity, sourceId).getBody();
    }

    /**
     * Gets configuration history
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param offset The offset into the result set. (required)
     * @param count The number of actions to return. (required)
     * @param sortColumn The field to sort on. (optional)
     * @param sortOrder The direction to sort. (optional)
     * @param startDate Include actions after this date. (optional)
     * @param endDate Include actions before this date. (optional)
     * @param userIdentity Include actions performed by this user. (optional)
     * @param sourceId Include actions on this component. (optional)
     * @return ResponseEntity&lt;HistoryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoryEntity> queryHistoryWithHttpInfo(IntegerParameter offset, IntegerParameter count, String sortColumn, String sortOrder, DateTimeParameter startDate, DateTimeParameter endDate, String userIdentity, String sourceId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'offset' is set
        if (offset == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'offset' when calling queryHistory");
        }
        
        // verify the required parameter 'count' is set
        if (count == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'count' when calling queryHistory");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        
        if (offset != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "integer", offset.getInteger()));
        }
        if (count != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "integer", count.getInteger()));
        }localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortColumn", sortColumn));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        
        if (startDate != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTime", startDate.getDateTime()));
        }
        if (endDate != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTime", endDate.getDateTime()));
        }localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "userIdentity", userIdentity));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sourceId", sourceId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<HistoryEntity> localReturnType = new ParameterizedTypeReference<HistoryEntity>() {};
        return apiClient.invokeAPI("/flow/history", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Schedule or unschedule components in the specified Process Group.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param scheduleComponentsEntity The request to schedule or unschedule. If the components in the request are not specified, all authorized components will be considered. (required)
     * @return ScheduleComponentsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ScheduleComponentsEntity scheduleComponents(String id, ScheduleComponentsEntity scheduleComponentsEntity) throws RestClientException {
        return scheduleComponentsWithHttpInfo(id, scheduleComponentsEntity).getBody();
    }

    /**
     * Schedule or unschedule components in the specified Process Group.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The process group id. (required)
     * @param scheduleComponentsEntity The request to schedule or unschedule. If the components in the request are not specified, all authorized components will be considered. (required)
     * @return ResponseEntity&lt;ScheduleComponentsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ScheduleComponentsEntity> scheduleComponentsWithHttpInfo(String id, ScheduleComponentsEntity scheduleComponentsEntity) throws RestClientException {
        Object localVarPostBody = scheduleComponentsEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling scheduleComponents");
        }
        
        // verify the required parameter 'scheduleComponentsEntity' is set
        if (scheduleComponentsEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scheduleComponentsEntity' when calling scheduleComponents");
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

        ParameterizedTypeReference<ScheduleComponentsEntity> localReturnType = new ParameterizedTypeReference<ScheduleComponentsEntity>() {};
        return apiClient.invokeAPI("/flow/process-groups/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Searches the cluster for a node with the specified address
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param q Node address to search for. (required)
     * @return ClusterSearchResultsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ClusterSearchResultsEntity searchCluster(String q) throws RestClientException {
        return searchClusterWithHttpInfo(q).getBody();
    }

    /**
     * Searches the cluster for a node with the specified address
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param q Node address to search for. (required)
     * @return ResponseEntity&lt;ClusterSearchResultsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ClusterSearchResultsEntity> searchClusterWithHttpInfo(String q) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'q' is set
        if (q == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'q' when calling searchCluster");
        }
        

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

        ParameterizedTypeReference<ClusterSearchResultsEntity> localReturnType = new ParameterizedTypeReference<ClusterSearchResultsEntity>() {};
        return apiClient.invokeAPI("/flow/cluster/search-results", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Performs a search against this NiFi using the specified search term
     * Only search results from authorized components will be returned.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param q The search term. (optional, default to )
     * @param a The id of the currently visited process group. If not specified, then the root process group is used. (optional, default to )
     * @return SearchResultsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SearchResultsEntity searchFlow(String q, String a) throws RestClientException {
        return searchFlowWithHttpInfo(q, a).getBody();
    }

    /**
     * Performs a search against this NiFi using the specified search term
     * Only search results from authorized components will be returned.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param q The search term. (optional, default to )
     * @param a The id of the currently visited process group. If not specified, then the root process group is used. (optional, default to )
     * @return ResponseEntity&lt;SearchResultsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SearchResultsEntity> searchFlowWithHttpInfo(String q, String a) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "q", q));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "a", a));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<SearchResultsEntity> localReturnType = new ParameterizedTypeReference<SearchResultsEntity>() {};
        return apiClient.invokeAPI("/flow/search-results", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
