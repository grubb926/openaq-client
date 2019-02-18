package com.example.openaqclient.service.impl;

import com.example.openaqclient.controller.PathParameter;
import com.example.openaqclient.exception.APIResponseException;
import com.example.openaqclient.service.helper.json.JsonConverter;
import com.example.openaqclient.service.helper.rest.RestClient;
import com.example.openaqclient.service.helper.rest.response.RestResponseEntity;
import com.example.openaqclient.service.result.*;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LatestAirQualityServiceImplTest {

    @Mock
    private RestClient restClient;

    @Mock
    private JsonConverter jsonConverter;

    @Mock
    private RestResponseEntity restResponseEntity;

    @InjectMocks
    private LatestAirQualityServiceImpl instance;

    @Test
    public void whenCallToGetLatestAirQualityForCityWithParametersSuccessful_returnsResults() throws APIResponseException {
        final ResultWrapper<Location> expected = generateLocationResults();
        final String path = "/latest";
        final PathParameter cityParameter = new PathParameter("city", "London");
        final String fullPath = path + "?" + cityParameter.toString();
        final String body = "{}";
        ReflectionTestUtils.setField(instance, "latestPath", path);

        when(restClient.performGetRequestForPath(fullPath)).thenReturn(restResponseEntity);
        when(restResponseEntity.getHttpStatusCodeValue()).thenReturn(200);
        when(restResponseEntity.getBody()).thenReturn(body);
        doReturn(expected).when(jsonConverter).convertJsonStringToResultWrapperOfType(body, Location.class);

        final ResultWrapper<Location> actual = instance.getLatestAirQualityForCityWithParameters(cityParameter, Lists.emptyList());
        assertThat(actual.getResults().get(0)).isEqualTo(expected.getResults().get(0));
        verify(restClient).performGetRequestForPath(fullPath);
        verify(jsonConverter).convertJsonStringToResultWrapperOfType(body, Location.class);
    }

    @Test(expected = APIResponseException.class)
    public void whenCallToGetLatestAirQualityForCityUnsuccessful_throwsException() throws APIResponseException {
        final String path = "/latest";
        final PathParameter cityParameter = new PathParameter("city", "London");
        final String fullPath = path + "?" + cityParameter.toString();
        ReflectionTestUtils.setField(instance, "latestPath", path);

        when(restClient.performGetRequestForPath(fullPath)).thenReturn(restResponseEntity);
        when(restResponseEntity.getHttpStatusCodeValue()).thenReturn(400);
        instance.getLatestAirQualityForCityWithParameters(cityParameter, Lists.emptyList());
        verify(restClient).performGetRequestForPath(fullPath);
        verifyZeroInteractions(jsonConverter);
    }

    private ResultWrapper<Location> generateLocationResults() {
        final List<Measurement> measurements = new ArrayList<>();
        final AveragingPeriod averagingPeriod = new AveragingPeriod(24, "hours");
        final Measurement measurement = new Measurement("pm10", 15, new Date(), "µg/m³", "DEFRA", averagingPeriod);
        measurements.add(measurement);

        final Coordinates coordinates = new Coordinates(53.34633, -2.8444333);
        final Location location = new Location("Liverpool Speke", "Liverpool", "GB", 307344.7902792764, measurements, coordinates);
        final List<Location> locations = new ArrayList<>();
        locations.add(location);

        return new ResultWrapper<>(null, locations);
    }
}
