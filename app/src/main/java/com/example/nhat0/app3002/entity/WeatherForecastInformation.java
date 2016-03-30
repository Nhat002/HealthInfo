package com.example.nhat0.app3002.entity;

import java.util.Calendar;

/**
 * Created by nhat0 on 13/3/2016.
 */
public class WeatherForecastInformation extends HealthInformation {
    private String weatherType;
    private Calendar weatherTime;
    public WeatherForecastInformation(String title, String content, int urgentPriority, String weatherType, Calendar weatherTime) {
        super( title, content, urgentPriority);
        this.weatherTime = weatherTime;
        this.weatherType = weatherType;
    }

    public WeatherForecastInformation() {

    }

    public String getWeatherType() {
        return weatherType;
    }

    public Calendar getWeatherTime() {
        return weatherTime;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public void setWeatherTime(long weatherTimeLong) {
        this.weatherTime = Calendar.getInstance();
        this.weatherTime.setTimeInMillis(weatherTimeLong);
    }
}
