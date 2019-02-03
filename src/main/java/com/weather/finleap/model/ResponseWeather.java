package com.weather.finleap.model;

import java.io.Serializable;

public class ResponseWeather implements Serializable {

    private Double pressureAverage;
    private Double dayTempAverage;
    private Double nightTempAverage;

    public ResponseWeather(Double pressureAverage, Double dayTempAverage, Double nightTempAverage) {
        this.pressureAverage = pressureAverage;
        this.dayTempAverage = dayTempAverage;
        this.nightTempAverage = nightTempAverage;
    }

    public Double getPressureAverage() {
        return pressureAverage;
    }

    public void setPressureAverage(Double pressureAverage) {
        this.pressureAverage = pressureAverage;
    }

    public Double getDayTempAverage() {
        return dayTempAverage;
    }

    public void setDayTempAverage(Double dayTempAverage) {
        this.dayTempAverage = dayTempAverage;
    }

    public Double getNightTempAverage() {
        return nightTempAverage;
    }

    public void setNightTempAverage(Double nightTempAverage) {
        this.nightTempAverage = nightTempAverage;
    }
}
