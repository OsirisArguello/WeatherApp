package com.tdp2.weatherapp.networking;


import com.tdp2.weatherapp.model.City;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {

    //@GET("/weather/{id}")
    //Call<WeatherResponse> getWeatherForCity(@Path("id") String id);

    @GET("/cities/{name}")
    Call<ArrayList<City>> getCities(@Path("name") String name);
}
