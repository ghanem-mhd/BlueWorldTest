package com.example.mhd_ghanem.blueworldtest.mapTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mhd_ghanem.blueworldtest.R;
import com.example.mhd_ghanem.blueworldtest.models.City;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapboxMap.OnStyleLoadedListener {

    private static final LatLng GERMANY_LATLNG = new LatLng(51.1657, 10.4515);
    private static final double ZOOM_LEVEL = 4.9;
    private MapView mapView;
    private MapboxMap mapboxMap;

    public static void open(Context context) {
        Intent myIntent = new Intent(context, MapActivity.class);
        context.startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_token));

        setContentView(R.layout.activity_map);

        setupToolbar();

        findViews();

        setupMapView(savedInstanceState);
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void findViews() {
        mapView = findViewById(R.id.mapView);
    }

    private void setupMapView(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }


    private void showCitiesMarkers(MapboxMap mapboxMap) {
        ArrayList<City> cities = City.getCities();
        for (City city : cities) {
            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(city.getLat(), city.getLon()))
                    .title(city.getName()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        mapboxMap.setStyle(Style.TRAFFIC_DAY,this);
        this.mapboxMap = mapboxMap;
    }

    @Override
    public void onStyleLoaded(@NonNull String style) {
        mapboxMap.setCameraPosition(new CameraPosition.Builder()
                .target(GERMANY_LATLNG)
                .zoom(ZOOM_LEVEL)
                .build());
        showCitiesMarkers(mapboxMap);
    }
}
