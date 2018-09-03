package com.tdp2.weatherapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.Date;

import com.tdp2.weatherapp.R;
import com.tdp2.weatherapp.model.Weather;
import com.tdp2.weatherapp.model.WeatherResponse;

import java.util.ArrayList;

public class WeatherDayAdapter extends ArrayAdapter<String> {

    WeatherResponse weatherResponse;

    public WeatherDayAdapter(@NonNull Context context, @NonNull WeatherResponse weather) {

        super(context, 0, weather.getDates());
        weatherResponse = weather;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final String date = getItem(position);
        final Weather weather = weatherResponse.getWeatherFor(date);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_view, parent, false);
        }
        // TODO: WIP, use weather textView and use icons
        TextView cityNameTextView = (TextView) convertView.findViewById(R.id.city_name);

        cityNameTextView.setText(date);

        return convertView;
    }
}
