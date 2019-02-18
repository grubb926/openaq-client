package com.example.openaqclient.controller.form;


import org.thymeleaf.util.StringUtils;

public class CityAirQualityRequest {

    private String cityName;
    private String sort;
    private boolean orderByLocation;
    private int limit;

    public CityAirQualityRequest() {
    }

    public CityAirQualityRequest(String cityName, String sort, boolean orderByLocation, int limit) {
        this.cityName = cityName;
        this.sort = sort;
        this.orderByLocation = orderByLocation;
        this.limit = limit;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = StringUtils.capitalize(cityName.toLowerCase());
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean isOrderByLocation() {
        return orderByLocation;
    }

    public void setOrderByLocation(boolean orderByLocation) {
        this.orderByLocation = orderByLocation;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
