package com.example.mhd_ghanem.blueworldtest.network_adapter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.mhd_ghanem.blueworldtest.models.CityForecast;
import com.example.mhd_ghanem.blueworldtest.models.ForecastItem;
import com.example.mhd_ghanem.blueworldtest.models.WeatherCondition;
import com.example.mhd_ghanem.blueworldtest.weatherTask.WeatherActivity;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class WeatherRequestsParser {

    private static final String TAG = WeatherRequestsParser.class.getSimpleName();

    @SuppressLint("LogNotTimber")
    public static CityForecast parseGetForecastCiyIDResponse(JSONObject jsonObject){
        Log.i(TAG,"Parsing " + jsonObject.toString());
        CityForecast cityForecast = new CityForecast();
        List<ForecastItem> forecastsList = new ArrayList<>();
        try {
            JSONObject cityElement = jsonObject.getJSONObject("city");
            cityForecast.setCityID(cityElement.getString("id"));
            JSONArray forecastArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < forecastArray.length(); i++) {
                JSONObject forecastElement = forecastArray.getJSONObject(i);
                JSONObject mainElement = forecastElement.getJSONObject("main");
                JSONArray weatherArray = forecastElement.getJSONArray("weather");

                ForecastItem forecastItem = new ForecastItem();
                forecastItem.setDt(new Date(forecastElement.getLong("dt")* 1000));
                forecastItem.setTemp(mainElement.getDouble("temp"));


                for (int j = 0; j < weatherArray.length(); j++) {
                    JSONObject weatherElement = weatherArray.getJSONObject(j);

                    WeatherCondition weatherCondition = new WeatherCondition();
                    weatherCondition.setDescription(weatherElement.getString("main"));
                    weatherCondition.setIcon(weatherElement.getString("icon"));

                    forecastItem.addWeatherCondition(weatherCondition);
                }

                forecastsList.add(forecastItem);
            }
            cityForecast.setForecastItems(forecastsList);
            Log.i(TAG,"Parsing succeed" + jsonObject.toString());
        } catch (JSONException e) {
            Log.e(TAG,"Parsing failed" + jsonObject.toString(),e);
            e.printStackTrace();
        }
        return cityForecast;
    }
}
