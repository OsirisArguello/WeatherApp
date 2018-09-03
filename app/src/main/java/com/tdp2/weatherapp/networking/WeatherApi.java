package com.tdp2.weatherapp.networking;


import com.tdp2.weatherapp.model.City;
import com.tdp2.weatherapp.model.Weather;
import com.tdp2.weatherapp.model.WeatherResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/weather/{id}")
    Call<Map<String, Weather>> getWeatherForCity(@Path("id") Integer id);

    @GET("/cities")
    Call<ArrayList<City>> getCities(@Query("query") String name);
}
