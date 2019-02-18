package com.example.openaqclient.service.result;

import java.util.Date;

public class Measurement {

    private String parameter;
    private double value;
    private Date lastUpdated;
    private String unit;
    private String sourceName;
    private AveragingPeriod averagingPeriod;

    public Measurement(String parameter, double value, Date lastUpdated, String unit, String sourceName, AveragingPeriod averagingPeriod) {
        this.parameter = parameter;
        this.value = value;
        this.lastUpdated = lastUpdated;
        this.unit = unit;
        this.sourceName = sourceName;
        this.averagingPeriod = averagingPeriod;
    }

    public String getParameter() {
        return parameter;
    }

    public double getValue() {
        return value;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public String getUnit() {
        return unit;
    }

    public String getSourceName() {
        return sourceName;
    }

    public AveragingPeriod getAveragingPeriod() {
        return averagingPeriod;
    }
}
