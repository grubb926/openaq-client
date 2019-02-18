package com.example.openaqclient.service.helper.rest.response;

public class RestResponseEntity {

    private int httpStatusCodeValue;
    private String body;

    public RestResponseEntity(int httpStatusCodeValue, String body) {
        this.httpStatusCodeValue = httpStatusCodeValue;
        this.body = body;
    }

    public int getHttpStatusCodeValue() {
        return httpStatusCodeValue;
    }

    public String getBody() {
        return body;
    }
}
