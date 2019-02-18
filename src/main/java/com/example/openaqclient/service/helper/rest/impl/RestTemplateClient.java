package com.example.openaqclient.service.helper.rest.impl;

import com.example.openaqclient.service.helper.rest.RestClient;
import com.example.openaqclient.service.helper.rest.response.RestResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateClient implements RestClient {

    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public RestResponseEntity performGetRequestForPath(String path) {
        final ResponseEntity<String> responseEntity =  restTemplate.getForEntity(path, String.class);
        return new RestResponseEntity(responseEntity.getStatusCodeValue(), responseEntity.getBody());
    }
}
