package com.example.openaqclient.service.result;

import java.util.List;

public class Location {

    private String location;
    private String city;
    private String country;
    private double distance;
    private List<Measurement> measurements;
    private Coordinates coordinates;

    public Location(String location, String city, String country, double distance, List<Measurement> measurements, Coordinates coordinates) {
        this.location = location;
        this.city = city;
        this.country = country;
        this.distance = distance;
        this.measurements = measurements;
        this.coordinates = coordinates;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
