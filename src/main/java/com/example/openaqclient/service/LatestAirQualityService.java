package com.example.openaqclient.service;

import com.example.openaqclient.exception.APIResponseException;
import com.example.openaqclient.service.result.Location;
import com.example.openaqclient.service.result.ResultWrapper;

public interface LatestAirQualityService {

    ResultWrapper<Location> getLatestAirQualityForCity() throws APIResponseException;
}
