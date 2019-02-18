package com.example.openaqclient.service.helper.rest;

import com.example.openaqclient.service.helper.rest.response.RestResponseEntity;

public interface RestClient {

    RestResponseEntity performGetRequestForPath(String path);
}
