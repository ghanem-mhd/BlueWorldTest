package com.example.mhd_ghanem.blueworldtest.weatherTask;

import com.example.mhd_ghanem.blueworldtest.models.ForecastItem;
import com.example.mhd_ghanem.blueworldtest.utilities.DateManager;

import java.util.ArrayList;
import java.util.List;

public class ForecastManager {

    public static List<ForecastItem> getNextDayForecast(List<ForecastItem> forecast){
        List<ForecastItem> nextDayForecast = new ArrayList<>();
        for (ForecastItem forecastItem :forecast){
            if (DateManager.isTomorrow(forecastItem.getDt())){
                nextDayForecast.add(forecastItem);
            }
        }
        return nextDayForecast;
    }
}
