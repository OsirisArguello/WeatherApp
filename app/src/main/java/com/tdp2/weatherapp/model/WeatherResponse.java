package com.tdp2.weatherapp.model;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Map;
import java.util.ArrayList;

public class WeatherResponse {
    @Expose
    public Map<String, Weather> dateWeatherMap;

    public ArrayList<String> getDates() {
        return (ArrayList<String>) dateWeatherMap.keySet().toArray()[0];
    }

    public Weather getWeatherFor(String date) {
        return new Weather();
    }
}

