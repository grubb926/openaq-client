package com.example.openaqclient.service.helper.json;

import com.example.openaqclient.service.result.ResultWrapper;

import java.lang.reflect.Type;

public interface JsonConverter {

    <T> T convertJsonStringToType(String json, Type type);

    <T> ResultWrapper<T> convertJsonStringToResultWrapperOfType(String json, Type type);
}
