package com.example.mhd_ghanem.blueworldtest.weatherTask.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mhd_ghanem.blueworldtest.R;
import com.example.mhd_ghanem.blueworldtest.models.ForecastItem;
import com.example.mhd_ghanem.blueworldtest.utilities.DateManager;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<ForecastItem> forecastItems = new ArrayList<>();



    public ForecastAdapter(Context context) {

    }

    @NonNull
    @Override
    public ForecastAdapter.ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.forecast_item, viewGroup, false);
        return new ForecastAdapter.ForecastViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ForecastAdapter.ForecastViewHolder forecastViewHolder, int i) {
        ForecastItem forecastItem = forecastItems.get(i);

        forecastViewHolder.forecastTempTextView.setText((int)forecastItem.getTemp()+"°");
        forecastViewHolder.forecastTimeTextView.setText(DateManager.getDateAsTme(forecastItem.getDt()));



        Picasso
                .get()
                .load(String.format("http://openweathermap.org/img/w/%s.png",forecastItem.getWeatherConditionIcon()))
                .error(R.drawable.no_image_placeholder)
                .into(forecastViewHolder.forecastImageView);
    }

    @Override
    public int getItemCount() {
        return forecastItems.size();
    }

    static class ForecastViewHolder extends RecyclerView.ViewHolder {

        private TextView forecastTimeTextView;
        private TextView forecastTempTextView;
        private ImageView forecastImageView;

        ForecastViewHolder(View v) {
            super(v);
            forecastTimeTextView = v.findViewById(R.id.forecast_item_time_tv);
            forecastImageView = v.findViewById(R.id.forecast_item_image_iv);
            forecastTempTextView = v.findViewById(R.id.forecast_item_temp_tv);
        }
    }

    public void setItemsData(List<ForecastItem> newData) {
        if (forecastItems != newData) {
            forecastItems = newData;
            notifyDataSetChanged();
        }
    }
}
