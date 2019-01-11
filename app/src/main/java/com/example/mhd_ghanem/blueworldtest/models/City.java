package com.example.mhd_ghanem.blueworldtest.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class City {

    private String id;
    private String name;
    private double lat;
    private double lon;
    private List<ForecastItem> forecastItems;

    public City(String id,String name, double lat, double lon) {
        this.id = id;
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
        cities.add(new City("2950159","Berlin",52.5200,13.4050));
        cities.add(new City("2886242","Cologne",50.9375,6.9603));
        cities.add(new City("2911298","Hamburg",53.5511,9.9937));
        cities.add(new City("2867714","Munich",48.1351,11.5820));
        cities.add(new City("3220802","Frankfurt",50.1109,8.6821));
        return cities;
    }

    public static HashMap<String,City> getCitiesAsMap(){
        HashMap<String,City> cities = new HashMap<>();
        for (City city:getCities()){
            cities.put(city.getId(),city);
        }
        return cities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<ForecastItem> getForecastItems() {
        return forecastItems;
    }

    public void setForecastItems(List<ForecastItem> forecastItems) {
        this.forecastItems = forecastItems;
    }
}
