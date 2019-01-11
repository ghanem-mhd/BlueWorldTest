package com.example.mhd_ghanem.blueworldtest.weatherTask.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mhd_ghanem.blueworldtest.R;
import com.example.mhd_ghanem.blueworldtest.models.City;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WeatherCitiesAdapter extends RecyclerView.Adapter<WeatherCitiesAdapter.WeatherCityViewHolder>{

    private List<City> cities = new ArrayList<>();

    public WeatherCitiesAdapter(Context context){

    }

    @NonNull
    @Override
    public WeatherCityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_weahter_item, viewGroup, false);
        return new WeatherCityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherCityViewHolder weatherCityViewHolder, int i) {
        City city = cities.get(i);

        weatherCityViewHolder.cityTitleTextView.setText(city.getName());
        weatherCityViewHolder.forecastAdapter.setItemsData(city.getForecastItems());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class WeatherCityViewHolder extends RecyclerView.ViewHolder {
        public TextView cityTitleTextView;
        public RecyclerView forecastRecyclerView;
        public ForecastAdapter forecastAdapter;

        public WeatherCityViewHolder(View v) {
            super(v);
            forecastAdapter = new ForecastAdapter(v.getContext());
            cityTitleTextView = v.findViewById(R.id.city_weather_item_title_tv);
            forecastRecyclerView = v.findViewById(R.id.city_weather_item_forecast_rv);

            forecastRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
            forecastRecyclerView.setAdapter(forecastAdapter);
        }
    }

    public void setItemsData(Collection<City> newData) {
        cities.clear();
        cities.addAll(newData);
        notifyDataSetChanged();
    }
}
