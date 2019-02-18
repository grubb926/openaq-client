package com.example.openaqclient.service.impl;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

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
    public ResultWrapper<Location> getLatestAirQualityForCityWithParameters(Map<String, String> queryParameters) throws APIResponseException {
        final RestResponseEntity restResponseEntity = restClient.performGetRequestForPath(buildPath(queryParameters));
        if (restResponseEntity.getHttpStatusCodeValue() != 200) {
            throw new APIResponseException();
        }
        return jsonConverter.convertJsonStringToResultWrapperOfType(restResponseEntity.getBody(), Location.class);
    }

    private String buildPath(Map<String, String> queryParameters) {
        final MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
            multiValueMap.add(entry.getKey(), entry.getValue());
        }
        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(latestPath).queryParams(multiValueMap);
        return uriComponentsBuilder.build().encode().toUriString();
    }
}
