package com.tdp2.weatherapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tdp2.weatherapp.R;
import com.tdp2.weatherapp.model.City;
import com.tdp2.weatherapp.model.WeatherForecast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeatherForecastAdapter extends ArrayAdapter<WeatherForecast> {

    Calendar myCalendar = Calendar.getInstance();

    public WeatherForecastAdapter(@NonNull Context context, @NonNull ArrayList<WeatherForecast> nextFiveDaysForecast) {
        super(context, 0, nextFiveDaysForecast);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final WeatherForecast weatherForecast = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.weather_forecast_view, parent, false);
        }
        // Lookup view for data population
        TextView dateTextView = (TextView) convertView.findViewById(R.id.date);
        TextView dayTemperatureTextView = (TextView) convertView.findViewById(R.id.day_temperature);
        TextView nightTemperatureTextView = (TextView) convertView.findViewById(R.id.night_temperature);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date="";

        if(position==0){
            date="Hoy";
        } else {
            if(position==1){
                date="Mañana";
            } else {
                date=weatherForecast.date;
            }
        }

        dateTextView.setText(date);
        dayTemperatureTextView.setText(weatherForecast.day.temperature+" ºC");
        nightTemperatureTextView.setText(weatherForecast.night.temperature+" ºC");

        return convertView;
    }

}
