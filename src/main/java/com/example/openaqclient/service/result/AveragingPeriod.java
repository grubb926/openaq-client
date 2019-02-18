package com.example.openaqclient.service.result;

public class AveragingPeriod {

    private int value;
    private String unit;

    public AveragingPeriod(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
