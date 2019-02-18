package com.example.openaqclient.service.impl;

import com.example.openaqclient.exception.APIResponseException;
import com.example.openaqclient.service.helper.json.JsonConverter;
import com.example.openaqclient.service.helper.rest.RestClient;
import com.example.openaqclient.service.helper.rest.response.RestResponseEntity;
import com.example.openaqclient.service.result.*;
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
    public void whenCallToGetLatestAirQualityForCitySuccessful_returnsResults() throws APIResponseException {
        final ResultWrapper<Location> expected = generateLocationResults();
        final String path = "/latest";
        final String body = "{}";
        ReflectionTestUtils.setField(instance, "latestPath", path);

        when(restClient.performGetRequestForPath(path)).thenReturn(restResponseEntity);
        when(restResponseEntity.getHttpStatusCodeValue()).thenReturn(200);
        when(restResponseEntity.getBody()).thenReturn(body);
        doReturn(expected).when(jsonConverter).convertJsonStringToResultWrapperOfType(body, Location.class);

        final ResultWrapper<Location> actual = instance.getLatestAirQualityForCity();
        assertThat(actual.getResults().get(0)).isEqualTo(expected.getResults().get(0));
        verify(restClient).performGetRequestForPath(path);
        verify(jsonConverter).convertJsonStringToResultWrapperOfType(body, Location.class);
    }

    @Test(expected = APIResponseException.class)
    public void whenCallToGetLatestAirQualityForCityUnsuccessful_throwsException() throws APIResponseException {
        final String path = "/latest";
        ReflectionTestUtils.setField(instance, "latestPath", path);

        when(restClient.performGetRequestForPath("/latest")).thenReturn(restResponseEntity);
        when(restResponseEntity.getHttpStatusCodeValue()).thenReturn(400);
        instance.getLatestAirQualityForCity();
    }

    private ResultWrapper<Location> generateLocationResults() {
        final List<Measurement> measurements = new ArrayList<>();
        final AveragingPeriod averagingPeriod = new AveragingPeriod(24, "hours");
        final Measurement measurement = new Measurement("pm10", 15, new Date(), "µg/m³", "DEFRA", averagingPeriod);
        measurements.add(measurement);

        final Coordinate coordinate = new Coordinate(53.34633, -2.8444333);
        final Location location = new Location("Liverpool Speke", "Liverpool", "GB", 307344.7902792764, measurements, coordinate);
        final List<Location> locations = new ArrayList<>();
        locations.add(location);

        return new ResultWrapper<>(null, locations);
    }
}
