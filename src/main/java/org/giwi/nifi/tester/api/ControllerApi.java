package org.giwi.nifi.tester.api;

import org.giwi.nifi.tester.invoker.ApiClient;
import org.giwi.nifi.tester.invoker.BaseApi;

import org.giwi.nifi.tester.model.BulletinEntity;
import org.giwi.nifi.tester.model.ClearBulletinsRequestEntity;
import org.giwi.nifi.tester.model.ClearBulletinsResultEntity;
import org.giwi.nifi.tester.model.ClientIdParameter;
import org.giwi.nifi.tester.model.ClusterEntity;
import org.giwi.nifi.tester.model.ComponentHistoryEntity;
import org.giwi.nifi.tester.model.ComponentStateEntity;
import org.giwi.nifi.tester.model.ConfigurationAnalysisEntity;
import org.giwi.nifi.tester.model.ControllerConfigurationEntity;
import org.giwi.nifi.tester.model.ControllerServiceEntity;
import org.giwi.nifi.tester.model.DateTimeParameter;
import org.giwi.nifi.tester.model.FlowAnalysisRuleEntity;
import org.giwi.nifi.tester.model.FlowAnalysisRuleRunStatusEntity;
import org.giwi.nifi.tester.model.FlowAnalysisRulesEntity;
import org.giwi.nifi.tester.model.FlowRegistryClientEntity;
import org.giwi.nifi.tester.model.FlowRegistryClientTypesEntity;
import org.giwi.nifi.tester.model.FlowRegistryClientsEntity;
import org.giwi.nifi.tester.model.HistoryEntity;
import org.giwi.nifi.tester.model.LongParameter;
import org.giwi.nifi.tester.model.NarDetailsEntity;
import org.giwi.nifi.tester.model.NarSummariesEntity;
import org.giwi.nifi.tester.model.NarSummaryEntity;
import org.giwi.nifi.tester.model.NodeEntity;
import org.giwi.nifi.tester.model.ParameterProviderEntity;
import org.giwi.nifi.tester.model.PropertyDescriptorEntity;
import org.giwi.nifi.tester.model.ReportingTaskEntity;
import org.giwi.nifi.tester.model.VerifyConfigRequestEntity;
import org.giwi.nifi.tester.model.VersionedReportingTaskImportRequestEntity;
import org.giwi.nifi.tester.model.VersionedReportingTaskImportResponseEntity;

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
public class ControllerApi extends BaseApi {

    public ControllerApi() {
        super(new ApiClient());
    }

    public ControllerApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Performs analysis of the component&#39;s configuration, providing information about which attributes are referenced.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rules id. (required)
     * @param configurationAnalysisEntity The configuration analysis request. (required)
     * @return ConfigurationAnalysisEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConfigurationAnalysisEntity analyzeFlowAnalysisRuleConfiguration(String id, ConfigurationAnalysisEntity configurationAnalysisEntity) throws RestClientException {
        return analyzeFlowAnalysisRuleConfigurationWithHttpInfo(id, configurationAnalysisEntity).getBody();
    }

    /**
     * Performs analysis of the component&#39;s configuration, providing information about which attributes are referenced.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rules id. (required)
     * @param configurationAnalysisEntity The configuration analysis request. (required)
     * @return ResponseEntity&lt;ConfigurationAnalysisEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConfigurationAnalysisEntity> analyzeFlowAnalysisRuleConfigurationWithHttpInfo(String id, ConfigurationAnalysisEntity configurationAnalysisEntity) throws RestClientException {
        Object localVarPostBody = configurationAnalysisEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling analyzeFlowAnalysisRuleConfiguration");
        }
        
        // verify the required parameter 'configurationAnalysisEntity' is set
        if (configurationAnalysisEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationAnalysisEntity' when calling analyzeFlowAnalysisRuleConfiguration");
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

        ParameterizedTypeReference<ConfigurationAnalysisEntity> localReturnType = new ParameterizedTypeReference<ConfigurationAnalysisEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}/config/analysis", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Performs analysis of the component&#39;s configuration, providing information about which attributes are referenced.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry client id. (required)
     * @param configurationAnalysisEntity The configuration analysis request. (required)
     * @return ConfigurationAnalysisEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConfigurationAnalysisEntity analyzeFlowRegistryClientConfiguration(String id, ConfigurationAnalysisEntity configurationAnalysisEntity) throws RestClientException {
        return analyzeFlowRegistryClientConfigurationWithHttpInfo(id, configurationAnalysisEntity).getBody();
    }

    /**
     * Performs analysis of the component&#39;s configuration, providing information about which attributes are referenced.
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry client id. (required)
     * @param configurationAnalysisEntity The configuration analysis request. (required)
     * @return ResponseEntity&lt;ConfigurationAnalysisEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConfigurationAnalysisEntity> analyzeFlowRegistryClientConfigurationWithHttpInfo(String id, ConfigurationAnalysisEntity configurationAnalysisEntity) throws RestClientException {
        Object localVarPostBody = configurationAnalysisEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling analyzeFlowRegistryClientConfiguration");
        }
        
        // verify the required parameter 'configurationAnalysisEntity' is set
        if (configurationAnalysisEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationAnalysisEntity' when calling analyzeFlowRegistryClientConfiguration");
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

        ParameterizedTypeReference<ConfigurationAnalysisEntity> localReturnType = new ParameterizedTypeReference<ConfigurationAnalysisEntity>() {};
        return apiClient.invokeAPI("/controller/registry-clients/{id}/config/analysis", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clears bulletins for a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param clearBulletinsRequestEntity The request to clear bulletins. (required)
     * @return ClearBulletinsResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ClearBulletinsResultEntity clearFlowAnalysisRuleBulletins(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        return clearFlowAnalysisRuleBulletinsWithHttpInfo(id, clearBulletinsRequestEntity).getBody();
    }

    /**
     * Clears bulletins for a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param clearBulletinsRequestEntity The request to clear bulletins. (required)
     * @return ResponseEntity&lt;ClearBulletinsResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ClearBulletinsResultEntity> clearFlowAnalysisRuleBulletinsWithHttpInfo(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        Object localVarPostBody = clearBulletinsRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearFlowAnalysisRuleBulletins");
        }
        
        // verify the required parameter 'clearBulletinsRequestEntity' is set
        if (clearBulletinsRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'clearBulletinsRequestEntity' when calling clearFlowAnalysisRuleBulletins");
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
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}/bulletins/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clears bulletins for a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param clearBulletinsRequestEntity The request to clear bulletins. (required)
     * @return ClearBulletinsResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ClearBulletinsResultEntity clearParameterProviderBulletins(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        return clearParameterProviderBulletinsWithHttpInfo(id, clearBulletinsRequestEntity).getBody();
    }

    /**
     * Clears bulletins for a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param clearBulletinsRequestEntity The request to clear bulletins. (required)
     * @return ResponseEntity&lt;ClearBulletinsResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ClearBulletinsResultEntity> clearParameterProviderBulletinsWithHttpInfo(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        Object localVarPostBody = clearBulletinsRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearParameterProviderBulletins");
        }
        
        // verify the required parameter 'clearBulletinsRequestEntity' is set
        if (clearBulletinsRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'clearBulletinsRequestEntity' when calling clearParameterProviderBulletins");
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
        return apiClient.invokeAPI("/controller/parameter-providers/{id}/bulletins/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clears bulletins for a registry client
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry client id. (required)
     * @param clearBulletinsRequestEntity The request to clear bulletins. (required)
     * @return ClearBulletinsResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ClearBulletinsResultEntity clearRegistryClientBulletins(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        return clearRegistryClientBulletinsWithHttpInfo(id, clearBulletinsRequestEntity).getBody();
    }

    /**
     * Clears bulletins for a registry client
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry client id. (required)
     * @param clearBulletinsRequestEntity The request to clear bulletins. (required)
     * @return ResponseEntity&lt;ClearBulletinsResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ClearBulletinsResultEntity> clearRegistryClientBulletinsWithHttpInfo(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        Object localVarPostBody = clearBulletinsRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearRegistryClientBulletins");
        }
        
        // verify the required parameter 'clearBulletinsRequestEntity' is set
        if (clearBulletinsRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'clearBulletinsRequestEntity' when calling clearRegistryClientBulletins");
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
        return apiClient.invokeAPI("/controller/registry-clients/{id}/bulletins/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clears the state for a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param componentStateEntity Optional component state to perform a selective key removal. If omitted, clears all state. (optional)
     * @return ComponentStateEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentStateEntity clearState(String id, ComponentStateEntity componentStateEntity) throws RestClientException {
        return clearStateWithHttpInfo(id, componentStateEntity).getBody();
    }

    /**
     * Clears the state for a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param componentStateEntity Optional component state to perform a selective key removal. If omitted, clears all state. (optional)
     * @return ResponseEntity&lt;ComponentStateEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentStateEntity> clearStateWithHttpInfo(String id, ComponentStateEntity componentStateEntity) throws RestClientException {
        Object localVarPostBody = componentStateEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearState");
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

        ParameterizedTypeReference<ComponentStateEntity> localReturnType = new ParameterizedTypeReference<ComponentStateEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}/state/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new bulletin
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bulletinEntity The reporting task configuration details. (required)
     * @return BulletinEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BulletinEntity createBulletin(BulletinEntity bulletinEntity) throws RestClientException {
        return createBulletinWithHttpInfo(bulletinEntity).getBody();
    }

    /**
     * Creates a new bulletin
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param bulletinEntity The reporting task configuration details. (required)
     * @return ResponseEntity&lt;BulletinEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BulletinEntity> createBulletinWithHttpInfo(BulletinEntity bulletinEntity) throws RestClientException {
        Object localVarPostBody = bulletinEntity;
        
        // verify the required parameter 'bulletinEntity' is set
        if (bulletinEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'bulletinEntity' when calling createBulletin");
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

        ParameterizedTypeReference<BulletinEntity> localReturnType = new ParameterizedTypeReference<BulletinEntity>() {};
        return apiClient.invokeAPI("/controller/bulletin", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new controller service
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param controllerServiceEntity The controller service configuration details. (required)
     * @return ControllerServiceEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerServiceEntity createControllerService(ControllerServiceEntity controllerServiceEntity) throws RestClientException {
        return createControllerServiceWithHttpInfo(controllerServiceEntity).getBody();
    }

    /**
     * Creates a new controller service
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param controllerServiceEntity The controller service configuration details. (required)
     * @return ResponseEntity&lt;ControllerServiceEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerServiceEntity> createControllerServiceWithHttpInfo(ControllerServiceEntity controllerServiceEntity) throws RestClientException {
        Object localVarPostBody = controllerServiceEntity;
        
        // verify the required parameter 'controllerServiceEntity' is set
        if (controllerServiceEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'controllerServiceEntity' when calling createControllerService");
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

        ParameterizedTypeReference<ControllerServiceEntity> localReturnType = new ParameterizedTypeReference<ControllerServiceEntity>() {};
        return apiClient.invokeAPI("/controller/controller-services", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new flow analysis rule
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param flowAnalysisRuleEntity The flow analysis rule configuration details. (required)
     * @return FlowAnalysisRuleEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisRuleEntity createFlowAnalysisRule(FlowAnalysisRuleEntity flowAnalysisRuleEntity) throws RestClientException {
        return createFlowAnalysisRuleWithHttpInfo(flowAnalysisRuleEntity).getBody();
    }

    /**
     * Creates a new flow analysis rule
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param flowAnalysisRuleEntity The flow analysis rule configuration details. (required)
     * @return ResponseEntity&lt;FlowAnalysisRuleEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisRuleEntity> createFlowAnalysisRuleWithHttpInfo(FlowAnalysisRuleEntity flowAnalysisRuleEntity) throws RestClientException {
        Object localVarPostBody = flowAnalysisRuleEntity;
        
        // verify the required parameter 'flowAnalysisRuleEntity' is set
        if (flowAnalysisRuleEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowAnalysisRuleEntity' when calling createFlowAnalysisRule");
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

        ParameterizedTypeReference<FlowAnalysisRuleEntity> localReturnType = new ParameterizedTypeReference<FlowAnalysisRuleEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new flow registry client
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param flowRegistryClientEntity The flow registry client configuration details. (required)
     * @return FlowRegistryClientEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowRegistryClientEntity createFlowRegistryClient(FlowRegistryClientEntity flowRegistryClientEntity) throws RestClientException {
        return createFlowRegistryClientWithHttpInfo(flowRegistryClientEntity).getBody();
    }

    /**
     * Creates a new flow registry client
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param flowRegistryClientEntity The flow registry client configuration details. (required)
     * @return ResponseEntity&lt;FlowRegistryClientEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowRegistryClientEntity> createFlowRegistryClientWithHttpInfo(FlowRegistryClientEntity flowRegistryClientEntity) throws RestClientException {
        Object localVarPostBody = flowRegistryClientEntity;
        
        // verify the required parameter 'flowRegistryClientEntity' is set
        if (flowRegistryClientEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowRegistryClientEntity' when calling createFlowRegistryClient");
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

        ParameterizedTypeReference<FlowRegistryClientEntity> localReturnType = new ParameterizedTypeReference<FlowRegistryClientEntity>() {};
        return apiClient.invokeAPI("/controller/registry-clients", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new parameter provider
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param parameterProviderEntity The parameter provider configuration details. (required)
     * @return ParameterProviderEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderEntity createParameterProvider(ParameterProviderEntity parameterProviderEntity) throws RestClientException {
        return createParameterProviderWithHttpInfo(parameterProviderEntity).getBody();
    }

    /**
     * Creates a new parameter provider
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param parameterProviderEntity The parameter provider configuration details. (required)
     * @return ResponseEntity&lt;ParameterProviderEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderEntity> createParameterProviderWithHttpInfo(ParameterProviderEntity parameterProviderEntity) throws RestClientException {
        Object localVarPostBody = parameterProviderEntity;
        
        // verify the required parameter 'parameterProviderEntity' is set
        if (parameterProviderEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'parameterProviderEntity' when calling createParameterProvider");
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

        ParameterizedTypeReference<ParameterProviderEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderEntity>() {};
        return apiClient.invokeAPI("/controller/parameter-providers", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a new reporting task
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param reportingTaskEntity The reporting task configuration details. (required)
     * @return ReportingTaskEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ReportingTaskEntity createReportingTask(ReportingTaskEntity reportingTaskEntity) throws RestClientException {
        return createReportingTaskWithHttpInfo(reportingTaskEntity).getBody();
    }

    /**
     * Creates a new reporting task
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param reportingTaskEntity The reporting task configuration details. (required)
     * @return ResponseEntity&lt;ReportingTaskEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ReportingTaskEntity> createReportingTaskWithHttpInfo(ReportingTaskEntity reportingTaskEntity) throws RestClientException {
        Object localVarPostBody = reportingTaskEntity;
        
        // verify the required parameter 'reportingTaskEntity' is set
        if (reportingTaskEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'reportingTaskEntity' when calling createReportingTask");
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

        ParameterizedTypeReference<ReportingTaskEntity> localReturnType = new ParameterizedTypeReference<ReportingTaskEntity>() {};
        return apiClient.invokeAPI("/controller/reporting-tasks", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * @param id The ID of the Flow Analysis Rule (required)
     * @param requestId The ID of the Verification Request (required)
     * @return VerifyConfigRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConfigRequestEntity deleteFlowAnalysisRuleVerificationRequest(String id, String requestId) throws RestClientException {
        return deleteFlowAnalysisRuleVerificationRequestWithHttpInfo(id, requestId).getBody();
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
     * @param id The ID of the Flow Analysis Rule (required)
     * @param requestId The ID of the Verification Request (required)
     * @return ResponseEntity&lt;VerifyConfigRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConfigRequestEntity> deleteFlowAnalysisRuleVerificationRequestWithHttpInfo(String id, String requestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteFlowAnalysisRuleVerificationRequest");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling deleteFlowAnalysisRuleVerificationRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
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

        ParameterizedTypeReference<VerifyConfigRequestEntity> localReturnType = new ParameterizedTypeReference<VerifyConfigRequestEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}/config/verification-requests/{requestId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes a flow registry client
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow registry client id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return FlowRegistryClientEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowRegistryClientEntity deleteFlowRegistryClient(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return deleteFlowRegistryClientWithHttpInfo(id, version, clientId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes a flow registry client
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow registry client id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;FlowRegistryClientEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowRegistryClientEntity> deleteFlowRegistryClientWithHttpInfo(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteFlowRegistryClient");
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

        ParameterizedTypeReference<FlowRegistryClientEntity> localReturnType = new ParameterizedTypeReference<FlowRegistryClientEntity>() {};
        return apiClient.invokeAPI("/controller/registry-clients/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Purges history
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param endDate Purge actions before this date/time. (required)
     * @return HistoryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoryEntity deleteHistory(DateTimeParameter endDate) throws RestClientException {
        return deleteHistoryWithHttpInfo(endDate).getBody();
    }

    /**
     * Purges history
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param endDate Purge actions before this date/time. (required)
     * @return ResponseEntity&lt;HistoryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoryEntity> deleteHistoryWithHttpInfo(DateTimeParameter endDate) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'endDate' is set
        if (endDate == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'endDate' when calling deleteHistory");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        
        if (endDate != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTime", endDate.getDateTime()));
        }

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<HistoryEntity> localReturnType = new ParameterizedTypeReference<HistoryEntity>() {};
        return apiClient.invokeAPI("/controller/history", HttpMethod.DELETE, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes an installed NAR
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The id of the NAR. (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @param force Indicates if the NAR should be deleted even when components are instantiated. (optional, default to false)
     * @return NarSummaryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NarSummaryEntity deleteNar(String id, Boolean disconnectedNodeAcknowledged, Boolean force) throws RestClientException {
        return deleteNarWithHttpInfo(id, disconnectedNodeAcknowledged, force).getBody();
    }

    /**
     * Deletes an installed NAR
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The id of the NAR. (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @param force Indicates if the NAR should be deleted even when components are instantiated. (optional, default to false)
     * @return ResponseEntity&lt;NarSummaryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NarSummaryEntity> deleteNarWithHttpInfo(String id, Boolean disconnectedNodeAcknowledged, Boolean force) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteNar");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "disconnectedNodeAcknowledged", disconnectedNodeAcknowledged));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "force", force));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<NarSummaryEntity> localReturnType = new ParameterizedTypeReference<NarSummaryEntity>() {};
        return apiClient.invokeAPI("/controller/nar-manager/nars/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Removes a node from the cluster
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The node id. (required)
     * @return NodeEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NodeEntity deleteNode(String id) throws RestClientException {
        return deleteNodeWithHttpInfo(id).getBody();
    }

    /**
     * Removes a node from the cluster
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The node id. (required)
     * @return ResponseEntity&lt;NodeEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NodeEntity> deleteNodeWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteNode");
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

        ParameterizedTypeReference<NodeEntity> localReturnType = new ParameterizedTypeReference<NodeEntity>() {};
        return apiClient.invokeAPI("/controller/cluster/nodes/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * @param id The ID of the Registry Client (required)
     * @param requestId The ID of the Verification Request (required)
     * @return VerifyConfigRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConfigRequestEntity deleteRegistryClientVerificationRequest(String id, String requestId) throws RestClientException {
        return deleteRegistryClientVerificationRequestWithHttpInfo(id, requestId).getBody();
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
     * @param id The ID of the Registry Client (required)
     * @param requestId The ID of the Verification Request (required)
     * @return ResponseEntity&lt;VerifyConfigRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConfigRequestEntity> deleteRegistryClientVerificationRequestWithHttpInfo(String id, String requestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteRegistryClientVerificationRequest");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling deleteRegistryClientVerificationRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
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

        ParameterizedTypeReference<VerifyConfigRequestEntity> localReturnType = new ParameterizedTypeReference<VerifyConfigRequestEntity>() {};
        return apiClient.invokeAPI("/controller/registry-clients/{id}/config/verification-requests/{requestId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the content of the NAR with the given id
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The id of the NAR. (required)
     * @return byte[]
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public byte[] downloadNar(String id) throws RestClientException {
        return downloadNarWithHttpInfo(id).getBody();
    }

    /**
     * Retrieves the content of the NAR with the given id
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The id of the NAR. (required)
     * @return ResponseEntity&lt;byte[]&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<byte[]> downloadNarWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling downloadNar");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

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
        return apiClient.invokeAPI("/controller/nar-manager/nars/{id}/content", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the contents of the cluster
     * Returns the contents of the cluster including all nodes and their status.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ClusterEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ClusterEntity getCluster() throws RestClientException {
        return getClusterWithHttpInfo().getBody();
    }

    /**
     * Gets the contents of the cluster
     * Returns the contents of the cluster including all nodes and their status.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ClusterEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ClusterEntity> getClusterWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<ClusterEntity> localReturnType = new ParameterizedTypeReference<ClusterEntity>() {};
        return apiClient.invokeAPI("/controller/cluster", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the configuration for this NiFi Controller
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ControllerConfigurationEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerConfigurationEntity getControllerConfig() throws RestClientException {
        return getControllerConfigWithHttpInfo().getBody();
    }

    /**
     * Retrieves the configuration for this NiFi Controller
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ControllerConfigurationEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerConfigurationEntity> getControllerConfigWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<ControllerConfigurationEntity> localReturnType = new ParameterizedTypeReference<ControllerConfigurationEntity>() {};
        return apiClient.invokeAPI("/controller/config", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @return FlowAnalysisRuleEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisRuleEntity getFlowAnalysisRule(String id) throws RestClientException {
        return getFlowAnalysisRuleWithHttpInfo(id).getBody();
    }

    /**
     * Gets a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @return ResponseEntity&lt;FlowAnalysisRuleEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisRuleEntity> getFlowAnalysisRuleWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFlowAnalysisRule");
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

        ParameterizedTypeReference<FlowAnalysisRuleEntity> localReturnType = new ParameterizedTypeReference<FlowAnalysisRuleEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a flow analysis rule property descriptor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param propertyName The property name. (required)
     * @param sensitive Property Descriptor requested sensitive status (optional)
     * @return PropertyDescriptorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PropertyDescriptorEntity getFlowAnalysisRulePropertyDescriptor(String id, String propertyName, Boolean sensitive) throws RestClientException {
        return getFlowAnalysisRulePropertyDescriptorWithHttpInfo(id, propertyName, sensitive).getBody();
    }

    /**
     * Gets a flow analysis rule property descriptor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param propertyName The property name. (required)
     * @param sensitive Property Descriptor requested sensitive status (optional)
     * @return ResponseEntity&lt;PropertyDescriptorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PropertyDescriptorEntity> getFlowAnalysisRulePropertyDescriptorWithHttpInfo(String id, String propertyName, Boolean sensitive) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFlowAnalysisRulePropertyDescriptor");
        }
        
        // verify the required parameter 'propertyName' is set
        if (propertyName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'propertyName' when calling getFlowAnalysisRulePropertyDescriptor");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "propertyName", propertyName));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sensitive", sensitive));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<PropertyDescriptorEntity> localReturnType = new ParameterizedTypeReference<PropertyDescriptorEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}/descriptors", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the state for a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @return ComponentStateEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentStateEntity getFlowAnalysisRuleState(String id) throws RestClientException {
        return getFlowAnalysisRuleStateWithHttpInfo(id).getBody();
    }

    /**
     * Gets the state for a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @return ResponseEntity&lt;ComponentStateEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentStateEntity> getFlowAnalysisRuleStateWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFlowAnalysisRuleState");
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
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}/state", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns the Verification Request with the given ID
     * Returns the Verification Request with the given ID. Once an Verification Request has been created, that request can subsequently be retrieved via this endpoint, and the request that is fetched will contain the updated state, such as percent complete, the current state of the request, and any failures. 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The ID of the Flow Analysis Rule (required)
     * @param requestId The ID of the Verification Request (required)
     * @return VerifyConfigRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConfigRequestEntity getFlowAnalysisRuleVerificationRequest(String id, String requestId) throws RestClientException {
        return getFlowAnalysisRuleVerificationRequestWithHttpInfo(id, requestId).getBody();
    }

    /**
     * Returns the Verification Request with the given ID
     * Returns the Verification Request with the given ID. Once an Verification Request has been created, that request can subsequently be retrieved via this endpoint, and the request that is fetched will contain the updated state, such as percent complete, the current state of the request, and any failures. 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The ID of the Flow Analysis Rule (required)
     * @param requestId The ID of the Verification Request (required)
     * @return ResponseEntity&lt;VerifyConfigRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConfigRequestEntity> getFlowAnalysisRuleVerificationRequestWithHttpInfo(String id, String requestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFlowAnalysisRuleVerificationRequest");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling getFlowAnalysisRuleVerificationRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
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

        ParameterizedTypeReference<VerifyConfigRequestEntity> localReturnType = new ParameterizedTypeReference<VerifyConfigRequestEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}/config/verification-requests/{requestId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all flow analysis rules
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return FlowAnalysisRulesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisRulesEntity getFlowAnalysisRules() throws RestClientException {
        return getFlowAnalysisRulesWithHttpInfo().getBody();
    }

    /**
     * Gets all flow analysis rules
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;FlowAnalysisRulesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisRulesEntity> getFlowAnalysisRulesWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<FlowAnalysisRulesEntity> localReturnType = new ParameterizedTypeReference<FlowAnalysisRulesEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a flow registry client
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow registry client id. (required)
     * @return FlowRegistryClientEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowRegistryClientEntity getFlowRegistryClient(String id) throws RestClientException {
        return getFlowRegistryClientWithHttpInfo(id).getBody();
    }

    /**
     * Gets a flow registry client
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow registry client id. (required)
     * @return ResponseEntity&lt;FlowRegistryClientEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowRegistryClientEntity> getFlowRegistryClientWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFlowRegistryClient");
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

        ParameterizedTypeReference<FlowRegistryClientEntity> localReturnType = new ParameterizedTypeReference<FlowRegistryClientEntity>() {};
        return apiClient.invokeAPI("/controller/registry-clients/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
    public FlowRegistryClientsEntity getFlowRegistryClients() throws RestClientException {
        return getFlowRegistryClientsWithHttpInfo().getBody();
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
    public ResponseEntity<FlowRegistryClientsEntity> getFlowRegistryClientsWithHttpInfo() throws RestClientException {
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
        return apiClient.invokeAPI("/controller/registry-clients", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the component types available from the installed NARs
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The id of the NAR. (required)
     * @return NarDetailsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NarDetailsEntity getNarDetails(String id) throws RestClientException {
        return getNarDetailsWithHttpInfo(id).getBody();
    }

    /**
     * Retrieves the component types available from the installed NARs
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The id of the NAR. (required)
     * @return ResponseEntity&lt;NarDetailsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NarDetailsEntity> getNarDetailsWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getNarDetails");
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

        ParameterizedTypeReference<NarDetailsEntity> localReturnType = new ParameterizedTypeReference<NarDetailsEntity>() {};
        return apiClient.invokeAPI("/controller/nar-manager/nars/{id}/details", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves summary information for installed NARs
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return NarSummariesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NarSummariesEntity getNarSummaries() throws RestClientException {
        return getNarSummariesWithHttpInfo().getBody();
    }

    /**
     * Retrieves summary information for installed NARs
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;NarSummariesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NarSummariesEntity> getNarSummariesWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<NarSummariesEntity> localReturnType = new ParameterizedTypeReference<NarSummariesEntity>() {};
        return apiClient.invokeAPI("/controller/nar-manager/nars", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the summary information for the NAR with the given identifier
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The id of the NAR. (required)
     * @return NarDetailsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NarDetailsEntity getNarSummary(String id) throws RestClientException {
        return getNarSummaryWithHttpInfo(id).getBody();
    }

    /**
     * Retrieves the summary information for the NAR with the given identifier
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The id of the NAR. (required)
     * @return ResponseEntity&lt;NarDetailsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NarDetailsEntity> getNarSummaryWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getNarSummary");
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

        ParameterizedTypeReference<NarDetailsEntity> localReturnType = new ParameterizedTypeReference<NarDetailsEntity>() {};
        return apiClient.invokeAPI("/controller/nar-manager/nars/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a node in the cluster
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The node id. (required)
     * @return NodeEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NodeEntity getNode(String id) throws RestClientException {
        return getNodeWithHttpInfo(id).getBody();
    }

    /**
     * Gets a node in the cluster
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The node id. (required)
     * @return ResponseEntity&lt;NodeEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NodeEntity> getNodeWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getNode");
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

        ParameterizedTypeReference<NodeEntity> localReturnType = new ParameterizedTypeReference<NodeEntity>() {};
        return apiClient.invokeAPI("/controller/cluster/nodes/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets status history for the node
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ComponentHistoryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentHistoryEntity getNodeStatusHistory() throws RestClientException {
        return getNodeStatusHistoryWithHttpInfo().getBody();
    }

    /**
     * Gets status history for the node
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;ComponentHistoryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentHistoryEntity> getNodeStatusHistoryWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<ComponentHistoryEntity> localReturnType = new ParameterizedTypeReference<ComponentHistoryEntity>() {};
        return apiClient.invokeAPI("/controller/status/history", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a flow registry client property descriptor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow registry client id. (required)
     * @param propertyName The property name. (required)
     * @param sensitive Property Descriptor requested sensitive status (optional)
     * @return PropertyDescriptorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PropertyDescriptorEntity getPropertyDescriptor(String id, String propertyName, Boolean sensitive) throws RestClientException {
        return getPropertyDescriptorWithHttpInfo(id, propertyName, sensitive).getBody();
    }

    /**
     * Gets a flow registry client property descriptor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow registry client id. (required)
     * @param propertyName The property name. (required)
     * @param sensitive Property Descriptor requested sensitive status (optional)
     * @return ResponseEntity&lt;PropertyDescriptorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PropertyDescriptorEntity> getPropertyDescriptorWithHttpInfo(String id, String propertyName, Boolean sensitive) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getPropertyDescriptor");
        }
        
        // verify the required parameter 'propertyName' is set
        if (propertyName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'propertyName' when calling getPropertyDescriptor");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "propertyName", propertyName));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "sensitive", sensitive));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<PropertyDescriptorEntity> localReturnType = new ParameterizedTypeReference<PropertyDescriptorEntity>() {};
        return apiClient.invokeAPI("/controller/registry-clients/{id}/descriptors", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves the types of flow registry clients that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return FlowRegistryClientTypesEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowRegistryClientTypesEntity getRegistryClientTypes() throws RestClientException {
        return getRegistryClientTypesWithHttpInfo().getBody();
    }

    /**
     * Retrieves the types of flow registry clients that this NiFi supports
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @return ResponseEntity&lt;FlowRegistryClientTypesEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowRegistryClientTypesEntity> getRegistryClientTypesWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<FlowRegistryClientTypesEntity> localReturnType = new ParameterizedTypeReference<FlowRegistryClientTypesEntity>() {};
        return apiClient.invokeAPI("/controller/registry-types", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * @param id The ID of the Registry Client (required)
     * @param requestId The ID of the Verification Request (required)
     * @return VerifyConfigRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConfigRequestEntity getRegistryClientVerificationRequest(String id, String requestId) throws RestClientException {
        return getRegistryClientVerificationRequestWithHttpInfo(id, requestId).getBody();
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
     * @param id The ID of the Registry Client (required)
     * @param requestId The ID of the Verification Request (required)
     * @return ResponseEntity&lt;VerifyConfigRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConfigRequestEntity> getRegistryClientVerificationRequestWithHttpInfo(String id, String requestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRegistryClientVerificationRequest");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling getRegistryClientVerificationRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
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

        ParameterizedTypeReference<VerifyConfigRequestEntity> localReturnType = new ParameterizedTypeReference<VerifyConfigRequestEntity>() {};
        return apiClient.invokeAPI("/controller/registry-clients/{id}/config/verification-requests/{requestId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Imports a reporting task snapshot
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param versionedReportingTaskImportRequestEntity The import request containing the reporting task snapshot to import. (required)
     * @return VersionedReportingTaskImportResponseEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VersionedReportingTaskImportResponseEntity importReportingTaskSnapshot(VersionedReportingTaskImportRequestEntity versionedReportingTaskImportRequestEntity) throws RestClientException {
        return importReportingTaskSnapshotWithHttpInfo(versionedReportingTaskImportRequestEntity).getBody();
    }

    /**
     * Imports a reporting task snapshot
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param versionedReportingTaskImportRequestEntity The import request containing the reporting task snapshot to import. (required)
     * @return ResponseEntity&lt;VersionedReportingTaskImportResponseEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VersionedReportingTaskImportResponseEntity> importReportingTaskSnapshotWithHttpInfo(VersionedReportingTaskImportRequestEntity versionedReportingTaskImportRequestEntity) throws RestClientException {
        Object localVarPostBody = versionedReportingTaskImportRequestEntity;
        
        // verify the required parameter 'versionedReportingTaskImportRequestEntity' is set
        if (versionedReportingTaskImportRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'versionedReportingTaskImportRequestEntity' when calling importReportingTaskSnapshot");
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

        ParameterizedTypeReference<VersionedReportingTaskImportResponseEntity> localReturnType = new ParameterizedTypeReference<VersionedReportingTaskImportResponseEntity>() {};
        return apiClient.invokeAPI("/controller/reporting-tasks/import", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return FlowAnalysisRuleEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisRuleEntity removeFlowAnalysisRule(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return removeFlowAnalysisRuleWithHttpInfo(id, version, clientId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;FlowAnalysisRuleEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisRuleEntity> removeFlowAnalysisRuleWithHttpInfo(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling removeFlowAnalysisRule");
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

        ParameterizedTypeReference<FlowAnalysisRuleEntity> localReturnType = new ParameterizedTypeReference<FlowAnalysisRuleEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Performs verification of the Flow Analysis Rule&#39;s configuration
     * This will initiate the process of verifying a given Flow Analysis Rule configuration. This may be a long-running task. As a result, this endpoint will immediately return a FlowAnalysisRuleConfigVerificationRequestEntity, and the process of performing the verification will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /flow-analysis-rules/{taskId}/verification-requests/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /flow-analysis-rules/{serviceId}/verification-requests/{requestId}.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rules id. (required)
     * @param verifyConfigRequestEntity The flow analysis rules configuration verification request. (required)
     * @return VerifyConfigRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConfigRequestEntity submitFlowAnalysisRuleConfigVerificationRequest(String id, VerifyConfigRequestEntity verifyConfigRequestEntity) throws RestClientException {
        return submitFlowAnalysisRuleConfigVerificationRequestWithHttpInfo(id, verifyConfigRequestEntity).getBody();
    }

    /**
     * Performs verification of the Flow Analysis Rule&#39;s configuration
     * This will initiate the process of verifying a given Flow Analysis Rule configuration. This may be a long-running task. As a result, this endpoint will immediately return a FlowAnalysisRuleConfigVerificationRequestEntity, and the process of performing the verification will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /flow-analysis-rules/{taskId}/verification-requests/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /flow-analysis-rules/{serviceId}/verification-requests/{requestId}.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rules id. (required)
     * @param verifyConfigRequestEntity The flow analysis rules configuration verification request. (required)
     * @return ResponseEntity&lt;VerifyConfigRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConfigRequestEntity> submitFlowAnalysisRuleConfigVerificationRequestWithHttpInfo(String id, VerifyConfigRequestEntity verifyConfigRequestEntity) throws RestClientException {
        Object localVarPostBody = verifyConfigRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling submitFlowAnalysisRuleConfigVerificationRequest");
        }
        
        // verify the required parameter 'verifyConfigRequestEntity' is set
        if (verifyConfigRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'verifyConfigRequestEntity' when calling submitFlowAnalysisRuleConfigVerificationRequest");
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

        ParameterizedTypeReference<VerifyConfigRequestEntity> localReturnType = new ParameterizedTypeReference<VerifyConfigRequestEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}/config/verification-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Performs verification of the Registry Client&#39;s configuration
     * Initiates verification of a Registry Client configuration. The request returns immediately with a request entity while verification runs asynchronously. The client should poll /controller/registry-clients/{clientId}/config/verification-requests/{requestId} for status and DELETE the request once verification completes.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry client id. (required)
     * @param verifyConfigRequestEntity The registry client configuration verification request. (required)
     * @return VerifyConfigRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConfigRequestEntity submitRegistryClientConfigVerificationRequest(String id, VerifyConfigRequestEntity verifyConfigRequestEntity) throws RestClientException {
        return submitRegistryClientConfigVerificationRequestWithHttpInfo(id, verifyConfigRequestEntity).getBody();
    }

    /**
     * Performs verification of the Registry Client&#39;s configuration
     * Initiates verification of a Registry Client configuration. The request returns immediately with a request entity while verification runs asynchronously. The client should poll /controller/registry-clients/{clientId}/config/verification-requests/{requestId} for status and DELETE the request once verification completes.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The registry client id. (required)
     * @param verifyConfigRequestEntity The registry client configuration verification request. (required)
     * @return ResponseEntity&lt;VerifyConfigRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConfigRequestEntity> submitRegistryClientConfigVerificationRequestWithHttpInfo(String id, VerifyConfigRequestEntity verifyConfigRequestEntity) throws RestClientException {
        Object localVarPostBody = verifyConfigRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling submitRegistryClientConfigVerificationRequest");
        }
        
        // verify the required parameter 'verifyConfigRequestEntity' is set
        if (verifyConfigRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'verifyConfigRequestEntity' when calling submitRegistryClientConfigVerificationRequest");
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

        ParameterizedTypeReference<VerifyConfigRequestEntity> localReturnType = new ParameterizedTypeReference<VerifyConfigRequestEntity>() {};
        return apiClient.invokeAPI("/controller/registry-clients/{id}/config/verification-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates the configuration for this NiFi
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param controllerConfigurationEntity The controller configuration. (required)
     * @return ControllerConfigurationEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ControllerConfigurationEntity updateControllerConfig(ControllerConfigurationEntity controllerConfigurationEntity) throws RestClientException {
        return updateControllerConfigWithHttpInfo(controllerConfigurationEntity).getBody();
    }

    /**
     * Updates the configuration for this NiFi
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param controllerConfigurationEntity The controller configuration. (required)
     * @return ResponseEntity&lt;ControllerConfigurationEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ControllerConfigurationEntity> updateControllerConfigWithHttpInfo(ControllerConfigurationEntity controllerConfigurationEntity) throws RestClientException {
        Object localVarPostBody = controllerConfigurationEntity;
        
        // verify the required parameter 'controllerConfigurationEntity' is set
        if (controllerConfigurationEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'controllerConfigurationEntity' when calling updateControllerConfig");
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

        ParameterizedTypeReference<ControllerConfigurationEntity> localReturnType = new ParameterizedTypeReference<ControllerConfigurationEntity>() {};
        return apiClient.invokeAPI("/controller/config", HttpMethod.PUT, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param flowAnalysisRuleEntity The flow analysis rule configuration details. (required)
     * @return FlowAnalysisRuleEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisRuleEntity updateFlowAnalysisRule(String id, FlowAnalysisRuleEntity flowAnalysisRuleEntity) throws RestClientException {
        return updateFlowAnalysisRuleWithHttpInfo(id, flowAnalysisRuleEntity).getBody();
    }

    /**
     * Updates a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param flowAnalysisRuleEntity The flow analysis rule configuration details. (required)
     * @return ResponseEntity&lt;FlowAnalysisRuleEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisRuleEntity> updateFlowAnalysisRuleWithHttpInfo(String id, FlowAnalysisRuleEntity flowAnalysisRuleEntity) throws RestClientException {
        Object localVarPostBody = flowAnalysisRuleEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateFlowAnalysisRule");
        }
        
        // verify the required parameter 'flowAnalysisRuleEntity' is set
        if (flowAnalysisRuleEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowAnalysisRuleEntity' when calling updateFlowAnalysisRule");
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

        ParameterizedTypeReference<FlowAnalysisRuleEntity> localReturnType = new ParameterizedTypeReference<FlowAnalysisRuleEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a flow registry client
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow registry client id. (required)
     * @param flowRegistryClientEntity The flow registry client configuration details. (required)
     * @return FlowRegistryClientEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowRegistryClientEntity updateFlowRegistryClient(String id, FlowRegistryClientEntity flowRegistryClientEntity) throws RestClientException {
        return updateFlowRegistryClientWithHttpInfo(id, flowRegistryClientEntity).getBody();
    }

    /**
     * Updates a flow registry client
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow registry client id. (required)
     * @param flowRegistryClientEntity The flow registry client configuration details. (required)
     * @return ResponseEntity&lt;FlowRegistryClientEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowRegistryClientEntity> updateFlowRegistryClientWithHttpInfo(String id, FlowRegistryClientEntity flowRegistryClientEntity) throws RestClientException {
        Object localVarPostBody = flowRegistryClientEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateFlowRegistryClient");
        }
        
        // verify the required parameter 'flowRegistryClientEntity' is set
        if (flowRegistryClientEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowRegistryClientEntity' when calling updateFlowRegistryClient");
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

        ParameterizedTypeReference<FlowRegistryClientEntity> localReturnType = new ParameterizedTypeReference<FlowRegistryClientEntity>() {};
        return apiClient.invokeAPI("/controller/registry-clients/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a node in the cluster
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The node id. (required)
     * @param nodeEntity The node configuration. The only configuration that will be honored at this endpoint is the status. (required)
     * @return NodeEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NodeEntity updateNode(String id, NodeEntity nodeEntity) throws RestClientException {
        return updateNodeWithHttpInfo(id, nodeEntity).getBody();
    }

    /**
     * Updates a node in the cluster
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The node id. (required)
     * @param nodeEntity The node configuration. The only configuration that will be honored at this endpoint is the status. (required)
     * @return ResponseEntity&lt;NodeEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NodeEntity> updateNodeWithHttpInfo(String id, NodeEntity nodeEntity) throws RestClientException {
        Object localVarPostBody = nodeEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateNode");
        }
        
        // verify the required parameter 'nodeEntity' is set
        if (nodeEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'nodeEntity' when calling updateNode");
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

        ParameterizedTypeReference<NodeEntity> localReturnType = new ParameterizedTypeReference<NodeEntity>() {};
        return apiClient.invokeAPI("/controller/cluster/nodes/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates run status of a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param flowAnalysisRuleRunStatusEntity The flow analysis rule run status. (required)
     * @return FlowAnalysisRuleEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FlowAnalysisRuleEntity updateRunStatus1(String id, FlowAnalysisRuleRunStatusEntity flowAnalysisRuleRunStatusEntity) throws RestClientException {
        return updateRunStatus1WithHttpInfo(id, flowAnalysisRuleRunStatusEntity).getBody();
    }

    /**
     * Updates run status of a flow analysis rule
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The flow analysis rule id. (required)
     * @param flowAnalysisRuleRunStatusEntity The flow analysis rule run status. (required)
     * @return ResponseEntity&lt;FlowAnalysisRuleEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FlowAnalysisRuleEntity> updateRunStatus1WithHttpInfo(String id, FlowAnalysisRuleRunStatusEntity flowAnalysisRuleRunStatusEntity) throws RestClientException {
        Object localVarPostBody = flowAnalysisRuleRunStatusEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateRunStatus1");
        }
        
        // verify the required parameter 'flowAnalysisRuleRunStatusEntity' is set
        if (flowAnalysisRuleRunStatusEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'flowAnalysisRuleRunStatusEntity' when calling updateRunStatus1");
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

        ParameterizedTypeReference<FlowAnalysisRuleEntity> localReturnType = new ParameterizedTypeReference<FlowAnalysisRuleEntity>() {};
        return apiClient.invokeAPI("/controller/flow-analysis-rules/{id}/run-status", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Uploads a NAR and requests for it to be installed
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param body The contents of the NAR file. (required)
     * @param filename  (optional)
     * @return NarSummaryEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public NarSummaryEntity uploadNar(Object body, String filename) throws RestClientException {
        return uploadNarWithHttpInfo(body, filename).getBody();
    }

    /**
     * Uploads a NAR and requests for it to be installed
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param body The contents of the NAR file. (required)
     * @param filename  (optional)
     * @return ResponseEntity&lt;NarSummaryEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<NarSummaryEntity> uploadNarWithHttpInfo(Object body, String filename) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling uploadNar");
        }
        

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

        ParameterizedTypeReference<NarSummaryEntity> localReturnType = new ParameterizedTypeReference<NarSummaryEntity>() {};
        return apiClient.invokeAPI("/controller/nar-manager/nars/content", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
            "application/octet-stream"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
