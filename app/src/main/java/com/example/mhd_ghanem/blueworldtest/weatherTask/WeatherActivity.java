package com.example.mhd_ghanem.blueworldtest.weatherTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mhd_ghanem.blueworldtest.R;
import com.example.mhd_ghanem.blueworldtest.models.City;
import com.example.mhd_ghanem.blueworldtest.models.CityForecast;
import com.example.mhd_ghanem.blueworldtest.models.ForecastItem;
import com.example.mhd_ghanem.blueworldtest.network_adapter.WeatherRequests;
import com.example.mhd_ghanem.blueworldtest.utilities.DateManager;
import com.example.mhd_ghanem.blueworldtest.weatherTask.adapters.WeatherCitiesAdapter;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

public class WeatherActivity extends AppCompatActivity implements WeatherRequests.WeatherRequestsInterface, View.OnClickListener {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    private Map<String,City> cities = City.getCitiesAsMap();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView citiesRecyclerView;
    private ProgressBar progressBar;
    private TextView errorTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        findViews();

        setupToolbar();

        setUpRecyclerView();

        getCitiesForecast();
    }

    private void findViews() {
        citiesRecyclerView = findViewById(R.id.activity_weather_cities_rv);
        progressBar = findViewById(R.id.activity_weather_loading_pb);
        errorTextView = findViewById(R.id.activity_weather_error_tv);

        errorTextView.setOnClickListener(this);
    }

    private void getCitiesForecast() {
        progressBar.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.GONE);
        citiesRecyclerView.setVisibility(View.GONE);

        compositeDisposable.add(WeatherRequests.getForeCastMultipleCities(this,cities.keySet(),this));
    }

    @Override
    public void onSuccess(List<CityForecast> citiesForecast) {
        progressBar.setVisibility(View.GONE);
        citiesRecyclerView.setVisibility(View.VISIBLE);

        for (CityForecast cityForecast:citiesForecast){
            City city = cities.get(cityForecast.getCityID());
            if (city != null){
                List<ForecastItem> nextDateForecast = ForecastManager.getNextDayForecast(cityForecast.getForecastItems());
                city.setForecastItems(nextDateForecast);
            }
        }

        WeatherCitiesAdapter weatherCitiesAdapter = ((WeatherCitiesAdapter) citiesRecyclerView.getAdapter());
        if (weatherCitiesAdapter != null){
            weatherCitiesAdapter.setItemsData(cities.values());
        }
    }

    @Override
    public void onError(Throwable e) {
        progressBar.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
    }

    public static void open(Context context) {
        Intent myIntent = new Intent(context, WeatherActivity.class);
        context.startActivity(myIntent);
    }


    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Tomorrow, " + DateManager.getTomorrowAsDateMonth());
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        WeatherCitiesAdapter weatherCitiesAdapter = new WeatherCitiesAdapter(this);

        citiesRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        citiesRecyclerView.setLayoutManager(linearLayoutManager);
        citiesRecyclerView.setAdapter(weatherCitiesAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == errorTextView){
            getCitiesForecast();
        }
    }
}
