package biz.touchtechnologies.backendchallanege.util;

import biz.touchtechnologies.backendchallanege.application.exception.ExternalApiException;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetProduct;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Map;

@Component
public class RestTemplateClientAdapter implements HttpClientAdapter {

    private final RestTemplate restTemplate;

    public RestTemplateClientAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <R> R doGet(String endpoint, Map<String, String> queryParams, Class<R> bodyResponseType) {
        String url = endpoint;

        if(queryParams != null && !queryParams.isEmpty()){
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint);

            queryParams.entrySet()
            .stream().forEach(entry -> builder.queryParam(entry.getKey(),entry.getValue()));

            url = builder.toUriString();
        }

        System.out.println(String.format("GET request to %s", endpoint));
        HttpEntity requestEntity = new HttpEntity(getHttpHeader());
        ResponseEntity<R> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, bodyResponseType );

        if(response.getStatusCode().value() != HttpStatus.OK.value() ){
            throw new ExternalApiException(String.format("Error consumiendo endpoint [{}] - {}, codigo de respuesta = {}",
                    HttpMethod.POST.name() , endpoint, response.getStatusCode().value()));
        }

        return response.getBody();
    }

    @Override
    public <T, R> T doPost(String endpoint, R bodyRequest, Class<T> bodyResponseType) {
        System.out.println(String.format("POST request to %s", endpoint));
        HttpEntity<R> requestEntity = new HttpEntity<R>(bodyRequest, getHttpHeader());
        ResponseEntity<T> response = (ResponseEntity<T>) restTemplate.exchange(endpoint, HttpMethod.POST, requestEntity, bodyResponseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value()
                && response.getStatusCode().value() != HttpStatus.CREATED.value()){
            throw new ExternalApiException(String.format("Error consumiendo endpoint [{}] - {}, codigo de respuesta = {}",
                    HttpMethod.POST.name(), endpoint, response.getStatusCode().value()));
        }

        return response.getBody();
    }

    @Override
    public <T, R> T doPut(String endpoint, R bodyRequest, Class<T> bodyResponseType) {

        System.out.println(String.format("PUT request to %s", endpoint));
        HttpEntity<R> requestEntity = new HttpEntity<R>(bodyRequest, getHttpHeader());
        ResponseEntity<T> response = (ResponseEntity<T>) restTemplate.exchange(endpoint, HttpMethod.PUT, requestEntity, bodyResponseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value()
                && response.getStatusCode().value() != HttpStatus.NO_CONTENT.value()){
            throw new ExternalApiException(String.format("Error consumiendo endpoint [{}] - {}, codigo de respuesta = {}",
                    HttpMethod.PUT.name(), endpoint, response.getStatusCode().value()));
        }

        return response.getBody();
    }

    @Override
    public <T> T doDelete(String endpoint, Class<T> bodyResponseType) {
        System.out.println(String.format("DELETE request to %s", endpoint));
        HttpEntity requestEntity = new HttpEntity<>(null, getHttpHeader());
        ResponseEntity<T> response = (ResponseEntity<T>) restTemplate.exchange(endpoint, HttpMethod.DELETE, requestEntity, bodyResponseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value()
            && response.getStatusCode().value() != HttpStatus.NO_CONTENT.value()){
            throw new ExternalApiException(String.format("Error consumiendo endpoint [{}] - {}, codigo de respuesta = {}",
                    HttpMethod.DELETE.name() , endpoint, response.getStatusCode().value()));
        }

        return response.getBody();
    }

    /**
     * Returns generics http headers
     * @return {@link HttpHeaders}
     */
    private HttpHeaders getHttpHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
