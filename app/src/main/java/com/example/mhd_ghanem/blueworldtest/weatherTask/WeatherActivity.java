package com.example.mhd_ghanem.blueworldtest.weatherTask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mhd_ghanem.blueworldtest.R;
import com.example.mhd_ghanem.blueworldtest.models.City;
import com.example.mhd_ghanem.blueworldtest.models.CityForecast;
import com.example.mhd_ghanem.blueworldtest.network_adapter.WeatherRequests;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class WeatherActivity extends AppCompatActivity implements WeatherRequests.WeatherRequestsInterface {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    private List<City> cities = City.getCities();
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        compositeDisposable = new CompositeDisposable();

        setupToolbar();

        getCitiesForecast();


    }

    private void getCitiesForecast() {
        List<String> citiesIDs = new ArrayList<>();
        for (City city:cities){
            citiesIDs.add(city.getId());
        }
        compositeDisposable.add(WeatherRequests.getForeCastMultipleCities(this,citiesIDs,this));
    }

    public static void open(Context context) {
        Intent myIntent = new Intent(context, WeatherActivity.class);
        context.startActivity(myIntent);
    }


    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void onSuccess(List<CityForecast> citiesForecasts) {
        System.out.println("WeatherActivity.onSuccess " + citiesForecasts);
    }

    @Override
    public void onError(Throwable e) {

    }
}
