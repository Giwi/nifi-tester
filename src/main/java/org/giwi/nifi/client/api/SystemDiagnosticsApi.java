package org.giwi.nifi.client.api;

import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.invoker.BaseApi;

import org.giwi.nifi.client.model.JmxMetricsResultsEntity;
import org.giwi.nifi.client.model.SystemDiagnosticsEntity;

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
public class SystemDiagnosticsApi extends BaseApi {

    public SystemDiagnosticsApi() {
        super(new ApiClient());
    }

    public SystemDiagnosticsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Retrieve available JMX metrics
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param beanNameFilter Regular Expression Pattern to be applied against the ObjectName (optional)
     * @return JmxMetricsResultsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JmxMetricsResultsEntity getJmxMetrics(String beanNameFilter) throws RestClientException {
        return getJmxMetricsWithHttpInfo(beanNameFilter).getBody();
    }

    /**
     * Retrieve available JMX metrics
     * Note: This endpoint is subject to change as NiFi and it&#39;s REST API evolve.
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param beanNameFilter Regular Expression Pattern to be applied against the ObjectName (optional)
     * @return ResponseEntity&lt;JmxMetricsResultsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JmxMetricsResultsEntity> getJmxMetricsWithHttpInfo(String beanNameFilter) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "beanNameFilter", beanNameFilter));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<JmxMetricsResultsEntity> localReturnType = new ParameterizedTypeReference<JmxMetricsResultsEntity>() {};
        return apiClient.invokeAPI("/system-diagnostics/jmx-metrics", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets the diagnostics for the system NiFi is running on
     * 
     * <p><b>200</b>
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param diagnosticLevel BASIC or VERBOSE verbosity details. Optional, defaults to BASIC (optional, default to BASIC)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return SystemDiagnosticsEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SystemDiagnosticsEntity getSystemDiagnostics(Boolean nodewise, String diagnosticLevel, String clusterNodeId) throws RestClientException {
        return getSystemDiagnosticsWithHttpInfo(nodewise, diagnosticLevel, clusterNodeId).getBody();
    }

    /**
     * Gets the diagnostics for the system NiFi is running on
     * 
     * <p><b>200</b>
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * @param nodewise Whether or not to include the breakdown per node. Optional, defaults to false (optional, default to false)
     * @param diagnosticLevel BASIC or VERBOSE verbosity details. Optional, defaults to BASIC (optional, default to BASIC)
     * @param clusterNodeId The id of the node where to get the status. (optional)
     * @return ResponseEntity&lt;SystemDiagnosticsEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SystemDiagnosticsEntity> getSystemDiagnosticsWithHttpInfo(Boolean nodewise, String diagnosticLevel, String clusterNodeId) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "nodewise", nodewise));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "diagnosticLevel", diagnosticLevel));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "clusterNodeId", clusterNodeId));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<SystemDiagnosticsEntity> localReturnType = new ParameterizedTypeReference<SystemDiagnosticsEntity>() {};
        return apiClient.invokeAPI("/system-diagnostics", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
