package com.example.mhd_ghanem.blueworldtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mhd_ghanem.blueworldtest.mapTask.MapActivity;
import com.example.mhd_ghanem.blueworldtest.weatherTask.WeatherActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMapActivity(View view) {
        MapActivity.open(this);
    }

    public void openWeatherActivity(View view) {
        WeatherActivity.open(this);
    }
}
