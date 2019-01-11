package com.example.mhd_ghanem.blueworldtest.base;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import net.danlew.android.joda.JodaTimeAndroid;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        JodaTimeAndroid.init(this);
    }
}
