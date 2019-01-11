package com.example.mhd_ghanem.blueworldtest.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastItem {
    private Date dt;
    private double temp;
    private List<WeatherCondition> weatherConditions;

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setWeatherCondition(List<WeatherCondition> weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public void addWeatherCondition(WeatherCondition weatherCondition){
        if (weatherConditions == null){
            weatherConditions = new ArrayList<>();
        }
        weatherConditions.add(weatherCondition);
    }

    @Override
    public String toString() {
        return "ForecastItem{" +
                "dt=" + dt +
                ", temp=" + temp +
                ", weatherConditions=" + weatherConditions +
                '}';
    }
}
