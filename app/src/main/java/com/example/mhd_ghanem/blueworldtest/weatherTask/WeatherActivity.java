package com.example.mhd_ghanem.blueworldtest.weatherTask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mhd_ghanem.blueworldtest.R;
import com.example.mhd_ghanem.blueworldtest.mapTask.MapActivity;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    public static void open(Context context) {
        Intent myIntent = new Intent(context, WeatherActivity.class);
        context.startActivity(myIntent);
    }
}
