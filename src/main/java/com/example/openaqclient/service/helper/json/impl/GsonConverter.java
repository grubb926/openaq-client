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

    private Gson gson;

    public GsonConverter() {
        gson = new GsonBuilder().create();
    }

    @Override
    public <T> T convertJsonStringToType(String json, Type type) {
        return gson.fromJson(json, type);
    }

    @Override
    public <T> ResultWrapper<T> convertJsonStringToResultWrapperOfType(String json, Type type) {
        return gson.fromJson(json, TypeToken.getParameterized(ResultWrapper.class, type).getType());
    }
}
