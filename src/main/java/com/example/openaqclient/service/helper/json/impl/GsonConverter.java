package com.example.openaqclient.service.helper.json.impl;

import com.example.openaqclient.service.helper.json.JsonConverter;
import com.example.openaqclient.service.result.ResultWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class GsonConverter implements JsonConverter {

    @Override
    public <T> ResultWrapper<T> convertJsonStringToResultWrapperOfType(String json, Type type) {
        final Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, TypeToken.getParameterized(ResultWrapper.class, type).getType());
    }
}
