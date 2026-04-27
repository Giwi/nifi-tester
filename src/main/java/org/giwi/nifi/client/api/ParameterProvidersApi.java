package org.giwi.nifi.client.api;

import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.invoker.BaseApi;

import org.giwi.nifi.client.model.ClearBulletinsRequestEntity;
import org.giwi.nifi.client.model.ClearBulletinsResultEntity;
import org.giwi.nifi.client.model.ClientIdParameter;
import org.giwi.nifi.client.model.ComponentStateEntity;
import org.giwi.nifi.client.model.ConfigurationAnalysisEntity;
import org.giwi.nifi.client.model.LongParameter;
import org.giwi.nifi.client.model.ParameterProviderApplyParametersRequestEntity;
import org.giwi.nifi.client.model.ParameterProviderEntity;
import org.giwi.nifi.client.model.ParameterProviderParameterApplicationEntity;
import org.giwi.nifi.client.model.ParameterProviderParameterFetchEntity;
import org.giwi.nifi.client.model.ParameterProviderReferencingComponentsEntity;
import org.giwi.nifi.client.model.PropertyDescriptorEntity;
import org.giwi.nifi.client.model.VerifyConfigRequestEntity;

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
public class ParameterProvidersApi extends BaseApi {

    public ParameterProvidersApi() {
        super(new ApiClient());
    }

    public ParameterProvidersApi(ApiClient apiClient) {
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
     * @param id The parameter provider id. (required)
     * @param configurationAnalysisEntity The configuration analysis request. (required)
     * @return ConfigurationAnalysisEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConfigurationAnalysisEntity analyzeConfiguration1(String id, ConfigurationAnalysisEntity configurationAnalysisEntity) throws RestClientException {
        return analyzeConfiguration1WithHttpInfo(id, configurationAnalysisEntity).getBody();
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
     * @param id The parameter provider id. (required)
     * @param configurationAnalysisEntity The configuration analysis request. (required)
     * @return ResponseEntity&lt;ConfigurationAnalysisEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConfigurationAnalysisEntity> analyzeConfiguration1WithHttpInfo(String id, ConfigurationAnalysisEntity configurationAnalysisEntity) throws RestClientException {
        Object localVarPostBody = configurationAnalysisEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling analyzeConfiguration1");
        }
        
        // verify the required parameter 'configurationAnalysisEntity' is set
        if (configurationAnalysisEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'configurationAnalysisEntity' when calling analyzeConfiguration1");
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
        return apiClient.invokeAPI("/parameter-providers/{id}/config/analysis", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * @param clearBulletinsRequestEntity The clear bulletin request specifying the timestamp from which to clear bulletins. (required)
     * @return ClearBulletinsResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ClearBulletinsResultEntity clearBulletins4(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        return clearBulletins4WithHttpInfo(id, clearBulletinsRequestEntity).getBody();
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
     * @param clearBulletinsRequestEntity The clear bulletin request specifying the timestamp from which to clear bulletins. (required)
     * @return ResponseEntity&lt;ClearBulletinsResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ClearBulletinsResultEntity> clearBulletins4WithHttpInfo(String id, ClearBulletinsRequestEntity clearBulletinsRequestEntity) throws RestClientException {
        Object localVarPostBody = clearBulletinsRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearBulletins4");
        }
        
        // verify the required parameter 'clearBulletinsRequestEntity' is set
        if (clearBulletinsRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'clearBulletinsRequestEntity' when calling clearBulletins4");
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
        return apiClient.invokeAPI("/parameter-providers/{id}/bulletins/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Clears the state for a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param componentStateEntity Optional component state to perform a selective key removal. If omitted, clears all state. (optional)
     * @return ComponentStateEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentStateEntity clearState2(String id, ComponentStateEntity componentStateEntity) throws RestClientException {
        return clearState2WithHttpInfo(id, componentStateEntity).getBody();
    }

    /**
     * Clears the state for a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param componentStateEntity Optional component state to perform a selective key removal. If omitted, clears all state. (optional)
     * @return ResponseEntity&lt;ComponentStateEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentStateEntity> clearState2WithHttpInfo(String id, ComponentStateEntity componentStateEntity) throws RestClientException {
        Object localVarPostBody = componentStateEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearState2");
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
        return apiClient.invokeAPI("/parameter-providers/{id}/state/clear-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes the Apply Parameters Request with the given ID
     * Deletes the Apply Parameters Request with the given ID. After a request is created via a POST to /nifi-api/parameter-providers/apply-parameters-requests, it is expected that the client will properly clean up the request by DELETE&#39;ing it, once the Apply process has completed. If the request is deleted before the request completes, then the Apply Parameters Request will finish the step that it is currently performing and then will cancel any subsequent steps.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param providerId The ID of the Parameter Provider (required)
     * @param requestId The ID of the Apply Parameters Request (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ParameterProviderApplyParametersRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderApplyParametersRequestEntity deleteApplyParametersRequest(String providerId, String requestId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return deleteApplyParametersRequestWithHttpInfo(providerId, requestId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes the Apply Parameters Request with the given ID
     * Deletes the Apply Parameters Request with the given ID. After a request is created via a POST to /nifi-api/parameter-providers/apply-parameters-requests, it is expected that the client will properly clean up the request by DELETE&#39;ing it, once the Apply process has completed. If the request is deleted before the request completes, then the Apply Parameters Request will finish the step that it is currently performing and then will cancel any subsequent steps.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param providerId The ID of the Parameter Provider (required)
     * @param requestId The ID of the Apply Parameters Request (required)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;ParameterProviderApplyParametersRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderApplyParametersRequestEntity> deleteApplyParametersRequestWithHttpInfo(String providerId, String requestId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'providerId' is set
        if (providerId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'providerId' when calling deleteApplyParametersRequest");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling deleteApplyParametersRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("providerId", providerId);
        uriVariables.put("requestId", requestId);

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

        ParameterizedTypeReference<ParameterProviderApplyParametersRequestEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderApplyParametersRequestEntity>() {};
        return apiClient.invokeAPI("/parameter-providers/{providerId}/apply-parameters-requests/{requestId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * @param id The ID of the Parameter Provider (required)
     * @param requestId The ID of the Verification Request (required)
     * @return VerifyConfigRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConfigRequestEntity deleteVerificationRequest1(String id, String requestId) throws RestClientException {
        return deleteVerificationRequest1WithHttpInfo(id, requestId).getBody();
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
     * @param id The ID of the Parameter Provider (required)
     * @param requestId The ID of the Verification Request (required)
     * @return ResponseEntity&lt;VerifyConfigRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConfigRequestEntity> deleteVerificationRequest1WithHttpInfo(String id, String requestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteVerificationRequest1");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling deleteVerificationRequest1");
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
        return apiClient.invokeAPI("/parameter-providers/{id}/config/verification-requests/{requestId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Fetches and temporarily caches the parameters for a provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param parameterProviderParameterFetchEntity The parameter fetch request. (required)
     * @return ParameterProviderEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderEntity fetchParameters(String id, ParameterProviderParameterFetchEntity parameterProviderParameterFetchEntity) throws RestClientException {
        return fetchParametersWithHttpInfo(id, parameterProviderParameterFetchEntity).getBody();
    }

    /**
     * Fetches and temporarily caches the parameters for a provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param parameterProviderParameterFetchEntity The parameter fetch request. (required)
     * @return ResponseEntity&lt;ParameterProviderEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderEntity> fetchParametersWithHttpInfo(String id, ParameterProviderParameterFetchEntity parameterProviderParameterFetchEntity) throws RestClientException {
        Object localVarPostBody = parameterProviderParameterFetchEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling fetchParameters");
        }
        
        // verify the required parameter 'parameterProviderParameterFetchEntity' is set
        if (parameterProviderParameterFetchEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'parameterProviderParameterFetchEntity' when calling fetchParameters");
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

        ParameterizedTypeReference<ParameterProviderEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderEntity>() {};
        return apiClient.invokeAPI("/parameter-providers/{id}/parameters/fetch-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @return ParameterProviderEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderEntity getParameterProvider(String id) throws RestClientException {
        return getParameterProviderWithHttpInfo(id).getBody();
    }

    /**
     * Gets a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @return ResponseEntity&lt;ParameterProviderEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderEntity> getParameterProviderWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getParameterProvider");
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

        ParameterizedTypeReference<ParameterProviderEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderEntity>() {};
        return apiClient.invokeAPI("/parameter-providers/{id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns the Apply Parameters Request with the given ID
     * Returns the Apply Parameters Request with the given ID. Once an Apply Parameters Request has been created by performing a POST to /nifi-api/parameter-providers, that request can subsequently be retrieved via this endpoint, and the request that is fetched will contain the state, such as percent complete, the current state of the request, and any failures. 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param providerId The ID of the Parameter Provider (required)
     * @param requestId The ID of the Apply Parameters Request (required)
     * @return ParameterProviderApplyParametersRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderApplyParametersRequestEntity getParameterProviderApplyParametersRequest(String providerId, String requestId) throws RestClientException {
        return getParameterProviderApplyParametersRequestWithHttpInfo(providerId, requestId).getBody();
    }

    /**
     * Returns the Apply Parameters Request with the given ID
     * Returns the Apply Parameters Request with the given ID. Once an Apply Parameters Request has been created by performing a POST to /nifi-api/parameter-providers, that request can subsequently be retrieved via this endpoint, and the request that is fetched will contain the state, such as percent complete, the current state of the request, and any failures. 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param providerId The ID of the Parameter Provider (required)
     * @param requestId The ID of the Apply Parameters Request (required)
     * @return ResponseEntity&lt;ParameterProviderApplyParametersRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderApplyParametersRequestEntity> getParameterProviderApplyParametersRequestWithHttpInfo(String providerId, String requestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'providerId' is set
        if (providerId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'providerId' when calling getParameterProviderApplyParametersRequest");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling getParameterProviderApplyParametersRequest");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("providerId", providerId);
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

        ParameterizedTypeReference<ParameterProviderApplyParametersRequestEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderApplyParametersRequestEntity>() {};
        return apiClient.invokeAPI("/parameter-providers/{providerId}/apply-parameters-requests/{requestId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets all references to a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @return ParameterProviderReferencingComponentsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderReferencingComponentsEntity getParameterProviderReferences(String id) throws RestClientException {
        return getParameterProviderReferencesWithHttpInfo(id).getBody();
    }

    /**
     * Gets all references to a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @return ResponseEntity&lt;ParameterProviderReferencingComponentsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderReferencingComponentsEntity> getParameterProviderReferencesWithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getParameterProviderReferences");
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

        ParameterizedTypeReference<ParameterProviderReferencingComponentsEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderReferencingComponentsEntity>() {};
        return apiClient.invokeAPI("/parameter-providers/{id}/references", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets a parameter provider property descriptor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param propertyName The property name. (required)
     * @return PropertyDescriptorEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PropertyDescriptorEntity getPropertyDescriptor2(String id, String propertyName) throws RestClientException {
        return getPropertyDescriptor2WithHttpInfo(id, propertyName).getBody();
    }

    /**
     * Gets a parameter provider property descriptor
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param propertyName The property name. (required)
     * @return ResponseEntity&lt;PropertyDescriptorEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PropertyDescriptorEntity> getPropertyDescriptor2WithHttpInfo(String id, String propertyName) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getPropertyDescriptor2");
        }
        
        // verify the required parameter 'propertyName' is set
        if (propertyName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'propertyName' when calling getPropertyDescriptor2");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "propertyName", propertyName));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<PropertyDescriptorEntity> localReturnType = new ParameterizedTypeReference<PropertyDescriptorEntity>() {};
        return apiClient.invokeAPI("/parameter-providers/{id}/descriptors", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the state for a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @return ComponentStateEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ComponentStateEntity getState1(String id) throws RestClientException {
        return getState1WithHttpInfo(id).getBody();
    }

    /**
     * Gets the state for a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @return ResponseEntity&lt;ComponentStateEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentStateEntity> getState1WithHttpInfo(String id) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getState1");
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
        return apiClient.invokeAPI("/parameter-providers/{id}/state", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
     * @param id The ID of the Parameter Provider (required)
     * @param requestId The ID of the Verification Request (required)
     * @return VerifyConfigRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConfigRequestEntity getVerificationRequest1(String id, String requestId) throws RestClientException {
        return getVerificationRequest1WithHttpInfo(id, requestId).getBody();
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
     * @param id The ID of the Parameter Provider (required)
     * @param requestId The ID of the Verification Request (required)
     * @return ResponseEntity&lt;VerifyConfigRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConfigRequestEntity> getVerificationRequest1WithHttpInfo(String id, String requestId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getVerificationRequest1");
        }
        
        // verify the required parameter 'requestId' is set
        if (requestId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestId' when calling getVerificationRequest1");
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
        return apiClient.invokeAPI("/parameter-providers/{id}/config/verification-requests/{requestId}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Deletes a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ParameterProviderEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderEntity removeParameterProvider(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        return removeParameterProviderWithHttpInfo(id, version, clientId, disconnectedNodeAcknowledged).getBody();
    }

    /**
     * Deletes a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param version The revision is used to verify the client is working with the latest version of the flow. (optional)
     * @param clientId If the client id is not specified, new one will be generated. This value (whether specified or generated) is included in the response. (optional)
     * @param disconnectedNodeAcknowledged Acknowledges that this node is disconnected to allow for mutable requests to proceed. (optional, default to false)
     * @return ResponseEntity&lt;ParameterProviderEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderEntity> removeParameterProviderWithHttpInfo(String id, LongParameter version, ClientIdParameter clientId, Boolean disconnectedNodeAcknowledged) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling removeParameterProvider");
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

        ParameterizedTypeReference<ParameterProviderEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderEntity>() {};
        return apiClient.invokeAPI("/parameter-providers/{id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Initiate a request to apply the fetched parameters of a Parameter Provider
     * This will initiate the process of applying fetched parameters to all referencing Parameter Contexts. Changing the value of a Parameter may require that one or more components be stopped and restarted, so this action may take significantly more time than many other REST API actions. As a result, this endpoint will immediately return a ParameterProviderApplyParametersRequestEntity, and the process of updating the necessary components will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /parameter-providers/apply-parameters-requests/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /parameter-providers/apply-parameters-requests/{requestId}.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param providerId The ID of the Parameter Provider (required)
     * @param parameterProviderParameterApplicationEntity The apply parameters request. (required)
     * @return ParameterProviderApplyParametersRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderApplyParametersRequestEntity submitApplyParameters(String providerId, ParameterProviderParameterApplicationEntity parameterProviderParameterApplicationEntity) throws RestClientException {
        return submitApplyParametersWithHttpInfo(providerId, parameterProviderParameterApplicationEntity).getBody();
    }

    /**
     * Initiate a request to apply the fetched parameters of a Parameter Provider
     * This will initiate the process of applying fetched parameters to all referencing Parameter Contexts. Changing the value of a Parameter may require that one or more components be stopped and restarted, so this action may take significantly more time than many other REST API actions. As a result, this endpoint will immediately return a ParameterProviderApplyParametersRequestEntity, and the process of updating the necessary components will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /parameter-providers/apply-parameters-requests/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /parameter-providers/apply-parameters-requests/{requestId}.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param providerId The ID of the Parameter Provider (required)
     * @param parameterProviderParameterApplicationEntity The apply parameters request. (required)
     * @return ResponseEntity&lt;ParameterProviderApplyParametersRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderApplyParametersRequestEntity> submitApplyParametersWithHttpInfo(String providerId, ParameterProviderParameterApplicationEntity parameterProviderParameterApplicationEntity) throws RestClientException {
        Object localVarPostBody = parameterProviderParameterApplicationEntity;
        
        // verify the required parameter 'providerId' is set
        if (providerId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'providerId' when calling submitApplyParameters");
        }
        
        // verify the required parameter 'parameterProviderParameterApplicationEntity' is set
        if (parameterProviderParameterApplicationEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'parameterProviderParameterApplicationEntity' when calling submitApplyParameters");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("providerId", providerId);

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

        ParameterizedTypeReference<ParameterProviderApplyParametersRequestEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderApplyParametersRequestEntity>() {};
        return apiClient.invokeAPI("/parameter-providers/{providerId}/apply-parameters-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Performs verification of the Parameter Provider&#39;s configuration
     * This will initiate the process of verifying a given Parameter Provider configuration. This may be a long-running task. As a result, this endpoint will immediately return a ParameterProviderConfigVerificationRequestEntity, and the process of performing the verification will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /parameter-providers/{serviceId}/verification-requests/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /parameter-providers/{providerId}/verification-requests/{requestId}.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param verifyConfigRequestEntity The parameter provider configuration verification request. (required)
     * @return VerifyConfigRequestEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyConfigRequestEntity submitConfigVerificationRequest1(String id, VerifyConfigRequestEntity verifyConfigRequestEntity) throws RestClientException {
        return submitConfigVerificationRequest1WithHttpInfo(id, verifyConfigRequestEntity).getBody();
    }

    /**
     * Performs verification of the Parameter Provider&#39;s configuration
     * This will initiate the process of verifying a given Parameter Provider configuration. This may be a long-running task. As a result, this endpoint will immediately return a ParameterProviderConfigVerificationRequestEntity, and the process of performing the verification will occur asynchronously in the background. The client may then periodically poll the status of the request by issuing a GET request to /parameter-providers/{serviceId}/verification-requests/{requestId}. Once the request is completed, the client is expected to issue a DELETE request to /parameter-providers/{providerId}/verification-requests/{requestId}.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param verifyConfigRequestEntity The parameter provider configuration verification request. (required)
     * @return ResponseEntity&lt;VerifyConfigRequestEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyConfigRequestEntity> submitConfigVerificationRequest1WithHttpInfo(String id, VerifyConfigRequestEntity verifyConfigRequestEntity) throws RestClientException {
        Object localVarPostBody = verifyConfigRequestEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling submitConfigVerificationRequest1");
        }
        
        // verify the required parameter 'verifyConfigRequestEntity' is set
        if (verifyConfigRequestEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'verifyConfigRequestEntity' when calling submitConfigVerificationRequest1");
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
        return apiClient.invokeAPI("/parameter-providers/{id}/config/verification-requests", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Updates a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param parameterProviderEntity The parameter provider configuration details. (required)
     * @return ParameterProviderEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ParameterProviderEntity updateParameterProvider(String id, ParameterProviderEntity parameterProviderEntity) throws RestClientException {
        return updateParameterProviderWithHttpInfo(id, parameterProviderEntity).getBody();
    }

    /**
     * Updates a parameter provider
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param id The parameter provider id. (required)
     * @param parameterProviderEntity The parameter provider configuration details. (required)
     * @return ResponseEntity&lt;ParameterProviderEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ParameterProviderEntity> updateParameterProviderWithHttpInfo(String id, ParameterProviderEntity parameterProviderEntity) throws RestClientException {
        Object localVarPostBody = parameterProviderEntity;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateParameterProvider");
        }
        
        // verify the required parameter 'parameterProviderEntity' is set
        if (parameterProviderEntity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'parameterProviderEntity' when calling updateParameterProvider");
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

        ParameterizedTypeReference<ParameterProviderEntity> localReturnType = new ParameterizedTypeReference<ParameterProviderEntity>() {};
        return apiClient.invokeAPI("/parameter-providers/{id}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
