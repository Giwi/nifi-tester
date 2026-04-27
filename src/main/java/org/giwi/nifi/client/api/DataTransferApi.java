package org.giwi.nifi.client.api;

import org.giwi.nifi.client.invoker.ApiClient;
import org.giwi.nifi.client.invoker.BaseApi;

import org.giwi.nifi.client.model.TransactionResultEntity;

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
public class DataTransferApi extends BaseApi {

    public DataTransferApi() {
        super(new ApiClient());
    }

    public DataTransferApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Commit or cancel the specified transaction
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param responseCode The response code. Available values are BAD_CHECKSUM(19), CONFIRM_TRANSACTION(12) or CANCEL_TRANSACTION(15). (required)
     * @param portId The input port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return TransactionResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransactionResultEntity commitInputPortTransaction(Integer responseCode, String portId, String transactionId, Object body) throws RestClientException {
        return commitInputPortTransactionWithHttpInfo(responseCode, portId, transactionId, body).getBody();
    }

    /**
     * Commit or cancel the specified transaction
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param responseCode The response code. Available values are BAD_CHECKSUM(19), CONFIRM_TRANSACTION(12) or CANCEL_TRANSACTION(15). (required)
     * @param portId The input port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return ResponseEntity&lt;TransactionResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TransactionResultEntity> commitInputPortTransactionWithHttpInfo(Integer responseCode, String portId, String transactionId, Object body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'responseCode' is set
        if (responseCode == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'responseCode' when calling commitInputPortTransaction");
        }
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling commitInputPortTransaction");
        }
        
        // verify the required parameter 'transactionId' is set
        if (transactionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'transactionId' when calling commitInputPortTransaction");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("portId", portId);
        uriVariables.put("transactionId", transactionId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "responseCode", responseCode));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/octet-stream"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<TransactionResultEntity> localReturnType = new ParameterizedTypeReference<TransactionResultEntity>() {};
        return apiClient.invokeAPI("/data-transfer/input-ports/{portId}/transactions/{transactionId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Commit or cancel the specified transaction
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param responseCode The response code. Available values are CONFIRM_TRANSACTION(12) or CANCEL_TRANSACTION(15). (required)
     * @param checksum A checksum calculated at client side using CRC32 to check flow file content integrity. It must match with the value calculated at server side. (required)
     * @param portId The output port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return TransactionResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransactionResultEntity commitOutputPortTransaction(Integer responseCode, String checksum, String portId, String transactionId, Object body) throws RestClientException {
        return commitOutputPortTransactionWithHttpInfo(responseCode, checksum, portId, transactionId, body).getBody();
    }

    /**
     * Commit or cancel the specified transaction
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param responseCode The response code. Available values are CONFIRM_TRANSACTION(12) or CANCEL_TRANSACTION(15). (required)
     * @param checksum A checksum calculated at client side using CRC32 to check flow file content integrity. It must match with the value calculated at server side. (required)
     * @param portId The output port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return ResponseEntity&lt;TransactionResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TransactionResultEntity> commitOutputPortTransactionWithHttpInfo(Integer responseCode, String checksum, String portId, String transactionId, Object body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'responseCode' is set
        if (responseCode == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'responseCode' when calling commitOutputPortTransaction");
        }
        
        // verify the required parameter 'checksum' is set
        if (checksum == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'checksum' when calling commitOutputPortTransaction");
        }
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling commitOutputPortTransaction");
        }
        
        // verify the required parameter 'transactionId' is set
        if (transactionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'transactionId' when calling commitOutputPortTransaction");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("portId", portId);
        uriVariables.put("transactionId", transactionId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "responseCode", responseCode));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "checksum", checksum));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/octet-stream"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<TransactionResultEntity> localReturnType = new ParameterizedTypeReference<TransactionResultEntity>() {};
        return apiClient.invokeAPI("/data-transfer/output-ports/{portId}/transactions/{transactionId}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a transaction to the specified output port or input port
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param portType The port type. (required)
     * @param portId The input or output port id. (required)
     * @param body  (optional)
     * @return TransactionResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransactionResultEntity createPortTransaction(String portType, String portId, Object body) throws RestClientException {
        return createPortTransactionWithHttpInfo(portType, portId, body).getBody();
    }

    /**
     * Create a transaction to the specified output port or input port
     * 
     * <p><b>201</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param portType The port type. (required)
     * @param portId The input or output port id. (required)
     * @param body  (optional)
     * @return ResponseEntity&lt;TransactionResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TransactionResultEntity> createPortTransactionWithHttpInfo(String portType, String portId, Object body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'portType' is set
        if (portType == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portType' when calling createPortTransaction");
        }
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling createPortTransaction");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("portType", portType);
        uriVariables.put("portId", portId);

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

        ParameterizedTypeReference<TransactionResultEntity> localReturnType = new ParameterizedTypeReference<TransactionResultEntity>() {};
        return apiClient.invokeAPI("/data-transfer/{portType}/{portId}/transactions", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Extend transaction TTL
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param portId The input port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return TransactionResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransactionResultEntity extendInputPortTransactionTTL(String portId, String transactionId, Object body) throws RestClientException {
        return extendInputPortTransactionTTLWithHttpInfo(portId, transactionId, body).getBody();
    }

    /**
     * Extend transaction TTL
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * @param portId The input port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return ResponseEntity&lt;TransactionResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TransactionResultEntity> extendInputPortTransactionTTLWithHttpInfo(String portId, String transactionId, Object body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling extendInputPortTransactionTTL");
        }
        
        // verify the required parameter 'transactionId' is set
        if (transactionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'transactionId' when calling extendInputPortTransactionTTL");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("portId", portId);
        uriVariables.put("transactionId", transactionId);

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

        ParameterizedTypeReference<TransactionResultEntity> localReturnType = new ParameterizedTypeReference<TransactionResultEntity>() {};
        return apiClient.invokeAPI("/data-transfer/input-ports/{portId}/transactions/{transactionId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Extend transaction TTL
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param portId The output port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return TransactionResultEntity
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransactionResultEntity extendOutputPortTransactionTTL(String portId, String transactionId, Object body) throws RestClientException {
        return extendOutputPortTransactionTTLWithHttpInfo(portId, transactionId, body).getBody();
    }

    /**
     * Extend transaction TTL
     * 
     * <p><b>200</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param portId The output port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return ResponseEntity&lt;TransactionResultEntity&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TransactionResultEntity> extendOutputPortTransactionTTLWithHttpInfo(String portId, String transactionId, Object body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling extendOutputPortTransactionTTL");
        }
        
        // verify the required parameter 'transactionId' is set
        if (transactionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'transactionId' when calling extendOutputPortTransactionTTL");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("portId", portId);
        uriVariables.put("transactionId", transactionId);

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

        ParameterizedTypeReference<TransactionResultEntity> localReturnType = new ParameterizedTypeReference<TransactionResultEntity>() {};
        return apiClient.invokeAPI("/data-transfer/output-ports/{portId}/transactions/{transactionId}", HttpMethod.PUT, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Transfer FlowFiles to the input port
     * 
     * <p><b>202</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param portId The input port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String receiveFlowFiles(String portId, String transactionId, Object body) throws RestClientException {
        return receiveFlowFilesWithHttpInfo(portId, transactionId, body).getBody();
    }

    /**
     * Transfer FlowFiles to the input port
     * 
     * <p><b>202</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param portId The input port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> receiveFlowFilesWithHttpInfo(String portId, String transactionId, Object body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling receiveFlowFiles");
        }
        
        // verify the required parameter 'transactionId' is set
        if (transactionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'transactionId' when calling receiveFlowFiles");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("portId", portId);
        uriVariables.put("transactionId", transactionId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "text/plain"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/octet-stream"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        ParameterizedTypeReference<String> localReturnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI("/data-transfer/input-ports/{portId}/transactions/{transactionId}/flow-files", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Transfer FlowFiles from the output port
     * 
     * <p><b>200</b> - There is no flow file to return.
     * <p><b>202</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param portId The output port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void transferFlowFiles(String portId, String transactionId, Object body) throws RestClientException {
        transferFlowFilesWithHttpInfo(portId, transactionId, body);
    }

    /**
     * Transfer FlowFiles from the output port
     * 
     * <p><b>200</b> - There is no flow file to return.
     * <p><b>202</b>
     * <p><b>400</b> - NiFi was unable to complete the request because it was invalid. The request should not be retried without modification.
     * <p><b>401</b> - Client could not be authenticated.
     * <p><b>403</b> - Client is not authorized to make this request.
     * <p><b>404</b> - The specified resource could not be found.
     * <p><b>409</b> - The request was valid but NiFi was not in the appropriate state to process it.
     * <p><b>503</b> - NiFi instance is not ready for serving request, or temporarily overloaded. Retrying the same request later may be successful
     * @param portId The output port id. (required)
     * @param transactionId The transaction id. (required)
     * @param body  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> transferFlowFilesWithHttpInfo(String portId, String transactionId, Object body) throws RestClientException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'portId' is set
        if (portId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'portId' when calling transferFlowFiles");
        }
        
        // verify the required parameter 'transactionId' is set
        if (transactionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'transactionId' when calling transferFlowFiles");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("portId", portId);
        uriVariables.put("transactionId", transactionId);

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

        ParameterizedTypeReference<Void> localReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/data-transfer/output-ports/{portId}/transactions/{transactionId}/flow-files", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
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
            "application/octet-stream"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "CookieSecureAuthorizationBearer", "HTTPBearerJWT" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
