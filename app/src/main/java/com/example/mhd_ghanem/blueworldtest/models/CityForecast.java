package com.example.mhd_ghanem.blueworldtest.models;

import java.util.List;

public class CityForecast {

    private String cityID;
    private List<ForecastItem> forecastItems;

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public List<ForecastItem> getForecastItems() {
        return forecastItems;
    }

    public void setForecastItems(List<ForecastItem> forecastItems) {
        this.forecastItems = forecastItems;
    }

    @Override
    public String toString() {
        return "CityForecast{" +
                "cityID='" + cityID + '\'' +
                ", forecastItems=" + forecastItems +
                '}';
    }
}
