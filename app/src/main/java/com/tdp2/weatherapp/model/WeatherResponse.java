package com.tdp2.weatherapp.model;

import java.util.Date;
import java.util.Map;
import java.util.ArrayList;

public class WeatherResponse {
    Map<Date, Weather> dateWeatherMap;

    public ArrayList<Date> getDates() {
        return (ArrayList<Date>) dateWeatherMap.keySet().toArray()[0];
    }

    public Weather getWeatherFor(Date date) {
        return dateWeatherMap.get(date);
    }
}

