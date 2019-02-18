package com.example.openaqclient.service.result;

import java.util.List;

public class Location {

    private String location;
    private String city;
    private String country;
    private double distance;
    private List<Measurement> measurements;
    private Coordinate coordinate;

    public Location(String location, String city, String country, double distance, List<Measurement> measurements, Coordinate coordinate) {
        this.location = location;
        this.city = city;
        this.country = country;
        this.distance = distance;
        this.measurements = measurements;
        this.coordinate = coordinate;
    }

    public String getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getDistance() {
        return distance;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
