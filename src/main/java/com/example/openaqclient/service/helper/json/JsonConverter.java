package com.example.openaqclient.service.helper.json;

import com.example.openaqclient.service.result.ResultWrapper;

import java.lang.reflect.Type;

public interface JsonConverter {

    <T> ResultWrapper<T> convertJsonStringToResultWrapperOfType(String json, Type type);
}
