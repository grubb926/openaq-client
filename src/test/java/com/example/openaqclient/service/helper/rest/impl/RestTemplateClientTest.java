package com.example.openaqclient.service.helper.rest.impl;

import com.example.openaqclient.service.helper.rest.response.RestResponseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class RestTemplateClientTest {

    @Mock
    private RestTemplateBuilder  restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ResponseEntity<String> responseEntity;

    @InjectMocks
    private RestTemplateClient instance;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void whenGetRequestCallSuccessful_returnsValidResponse() {
        final String validPath = "/validPath";
        final int expectedStatus = 200;
        final String expectedBody = "{'body': 'valid'}";

        when(restTemplate.getForEntity(validPath, String.class)).thenReturn(responseEntity);
        when(responseEntity.getStatusCodeValue()).thenReturn(expectedStatus);
        when(responseEntity.getBody()).thenReturn(expectedBody);

        final RestResponseEntity actual = instance.performGetRequestForPath(validPath);
        assertThat(actual.getHttpStatusCodeValue()).isEqualTo(expectedStatus);
        assertThat(actual.getBody()).isEqualTo(expectedBody);
    }
}