package com.example.mhd_ghanem.blueworldtest.models;

public class WeatherCondition {

    private String description;
    private String icon;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "WeatherCondition{" +
                "description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
