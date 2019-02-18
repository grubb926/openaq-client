package com.example.openaqclient.service;

import com.example.openaqclient.controller.PathParameter;
import com.example.openaqclient.exception.APIResponseException;
import com.example.openaqclient.service.result.Location;
import com.example.openaqclient.service.result.ResultWrapper;

import java.util.List;

public interface LatestAirQualityService {

    ResultWrapper<Location> getLatestAirQualityForCityWithParameters(PathParameter cityParameter, List<PathParameter> parameters) throws APIResponseException;
}
