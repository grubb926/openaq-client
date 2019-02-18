package com.example.openaqclient.service.impl;

import com.example.openaqclient.controller.PathParameter;
import com.example.openaqclient.exception.APIResponseException;
import com.example.openaqclient.service.LatestAirQualityService;
import com.example.openaqclient.service.helper.json.JsonConverter;
import com.example.openaqclient.service.helper.rest.RestClient;
import com.example.openaqclient.service.helper.rest.response.RestResponseEntity;
import com.example.openaqclient.service.result.Location;
import com.example.openaqclient.service.result.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResultWrapper<Location> getLatestAirQualityForCityWithParameters(PathParameter cityParameter, List<PathParameter> pathParameters) throws APIResponseException {
        final RestResponseEntity restResponseEntity = restClient.performGetRequestForPath(buildPath(cityParameter, pathParameters));
        if (restResponseEntity.getHttpStatusCodeValue() != 200) {
            throw new APIResponseException();
        }
        return jsonConverter.convertJsonStringToResultWrapperOfType(restResponseEntity.getBody(), Location.class);
    }

    private String buildPath(PathParameter cityParameter, List<PathParameter> pathParameters) {
        StringBuilder sb = new StringBuilder(latestPath + "?" + cityParameter.toString());

        for(PathParameter pathParameter : pathParameters) {
            sb.append("&").append(pathParameter.toString());
        }

        return sb.toString();
    }
}
