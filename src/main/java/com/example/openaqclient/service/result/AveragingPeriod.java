package com.example.openaqclient.service.result;

public class AveragingPeriod {

    private double value;
    private String unit;

    public AveragingPeriod(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
