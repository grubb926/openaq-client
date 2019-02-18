package com.example.openaqclient.service.result;

import java.util.List;

public class ResultWrapper<T> {

    private Meta meta;
    private List<T> results;

    public ResultWrapper(Meta meta, List<T> results) {
        this.meta = meta;
        this.results = results;
    }

    public Meta getMeta() {
        return meta;
    }

    public List<T> getResults() {
        return results;
    }
}
