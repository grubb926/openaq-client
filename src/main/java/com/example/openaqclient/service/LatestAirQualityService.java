package com.example.openaqclient.service;

import com.example.openaqclient.exception.APIResponseException;
import com.example.openaqclient.service.result.Location;
import com.example.openaqclient.service.result.ResultWrapper;

import java.util.Map;

public interface LatestAirQualityService {

    ResultWrapper<Location> getLatestAirQualityForCityWithParameters(Map<String, String> parameters) throws APIResponseException;
}
