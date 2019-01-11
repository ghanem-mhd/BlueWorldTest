package com.example.mhd_ghanem.blueworldtest.network_adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.mhd_ghanem.blueworldtest.R;
import com.example.mhd_ghanem.blueworldtest.models.CityForecast;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class WeatherRequests {

    private static final String TAG = WeatherRequests.class.getSimpleName();

    private static final String CITY_FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast";

    @SuppressLint("LogNotTimber")
    public static Observable<JSONObject> getForeCastCiyID(Context context, String cityID) {
        Log.i(TAG,"Getting forecast for city" + cityID);
        return Rx2AndroidNetworking.get(CITY_FORECAST_URL)
                .addQueryParameter("id", cityID)
                .addQueryParameter("units", "metric")
                .addQueryParameter("appid", context.getString(R.string.open_weather_api_key))
                .build().getJSONObjectObservable();
    }

    public static Disposable getForeCastMultipleCities(Context context, Collection<String> citiesIDs, WeatherRequestsInterface callback) {
        List<Observable<?>> requests = new ArrayList<>();

        for (String cityID : citiesIDs) {
            requests.add(WeatherRequests.getForeCastCiyID(context, cityID));
        }

        Disposable disposable = Observable.zip(requests,
                new Function<Object[], ArrayList<CityForecast>>() {
                    @SuppressLint("LogNotTimber")
                    @Override
                    public ArrayList<CityForecast> apply(Object[] objects) {
                        Log.i(TAG,"All cities requests succeed " + Arrays.toString(objects));
                        ArrayList<CityForecast> cityForecast = new ArrayList<>();
                        for (Object object : objects) {
                            cityForecast.add(WeatherRequestsParser.parseGetForecastCiyIDResponse((JSONObject) object));
                        }
                        return cityForecast;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<CityForecast>>() {
                               @Override
                               public void accept(ArrayList<CityForecast> cityForecast) {
                                    callback.onSuccess(cityForecast);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable e) {
                                Log.i(TAG,"Cities requests failed",e);
                                callback.onError(e);
                            }
                        });
        return disposable;
    }

    public interface WeatherRequestsInterface {
        void onSuccess(List<CityForecast> citiesForecast);

        void onError(Throwable e);
    }
}
