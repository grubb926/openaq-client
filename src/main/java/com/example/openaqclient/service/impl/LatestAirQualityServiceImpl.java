package com.example.openaqclient.service.impl;

import com.example.openaqclient.exception.APIResponseException;
import com.example.openaqclient.service.LatestAirQualityService;
import com.example.openaqclient.service.helper.json.JsonConverter;
import com.example.openaqclient.service.helper.rest.RestClient;
import com.example.openaqclient.service.helper.rest.response.RestResponseEntity;
import com.example.openaqclient.service.result.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LatestAirQualityServiceImpl implements LatestAirQualityService {

    @Value("${openaq.path.latest}")
    private String latestPath;

    private RestClient restClient;
    private JsonConverter jsonConverter;

    @Autowired
    public LatestAirQualityServiceImpl(RestClient restClient, JsonConverter jsonConverter) {
        this.restClient = restClient;
        this.jsonConverter = jsonConverter;
    }

    @Override
    public ResultWrapper<Location> getLatestAirQualityForCity() throws APIResponseException {
        final RestResponseEntity restResponseEntity = restClient.performGetRequestForPath(latestPath);
        if (restResponseEntity.getHttpStatusCodeValue() != 200) {
            throw new APIResponseException();
        }
        return jsonConverter.convertJsonStringToResultWrapperOfType(restResponseEntity.getBody(), Location.class);
    }
}
