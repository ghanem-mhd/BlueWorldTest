package com.example.mhd_ghanem.blueworldtest.models;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class City {

    private String name;
    private double lat;
    private double lon;

    public City(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


    public static ArrayList<City> getCities(){
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("Berlin",52.5200,13.4050));
        cities.add(new City("Cologne",50.9375,6.9603));
        cities.add(new City("Hamburg",53.5511,9.9937));
        cities.add(new City("Munich",48.1351,11.5820));
        cities.add(new City("Frankfurt",50.1109,8.6821));
        return cities;
    }
}
