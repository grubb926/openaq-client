package com.example.openaqclient.service.result;

import java.util.Date;

public class Measurement {

    private String parameter;
    private int value;
    private Date lastUpdated;
    private String unit;
    private String sourceName;
    private AveragingPeriod averagingPeriods;

    public Measurement(String parameter, int value, Date lastUpdated, String unit, String sourceName, AveragingPeriod averagingPeriods) {
        this.parameter = parameter;
        this.value = value;
        this.lastUpdated = lastUpdated;
        this.unit = unit;
        this.sourceName = sourceName;
        this.averagingPeriods = averagingPeriods;
    }

    public String getParameter() {
        return parameter;
    }

    public int getValue() {
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

    public AveragingPeriod getAveragingPeriods() {
        return averagingPeriods;
    }
}
