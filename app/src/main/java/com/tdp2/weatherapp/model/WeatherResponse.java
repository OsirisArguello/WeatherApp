package com.tdp2.weatherapp.model;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Map;
import java.util.ArrayList;

public class WeatherResponse {
    @Expose
    public Map<String, Weather> dateWeatherMap;

    public void setDateWeatherMap(Map<String, Weather> dateWeatherMap) {
        this.dateWeatherMap = dateWeatherMap;
    }

    public ArrayList<String> getDates() {
        return new ArrayList<String>(dateWeatherMap.keySet());
    }

    public Weather getWeatherFor(String date) {
        return dateWeatherMap.get(date);
    }
}

