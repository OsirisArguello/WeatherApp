package com.tdp2.weatherapp.networking;

import android.util.Log;

import com.tdp2.weatherapp.model.City;
import com.tdp2.weatherapp.model.Weather;
import com.tdp2.weatherapp.model.WeatherResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherService {
    private WeatherApi weatherApi;

    public WeatherService() {
        weatherApi=ApiClient.getInstance().getWeatherClient();
    }

    public void getWeatherForCity(final WeatherClient weatherClient, final City city){
        weatherApi.getWeatherForCity(city.id).enqueue(new Callback<Map<String, Weather>>() {
            @Override
            public void onResponse(Call<Map<String, Weather>> call, Response<Map<String, Weather>> response) {
                if (response.code() > 199 && response.code() < 300) {
                    if(response.body() != null) {
                        Log.i("WEATHERCITYSERVICE", response.body().toString());
                        weatherClient.onResponseSuccess(response.body());
                    }else {
                        Log.i("WEATHERCITYSERVICE", "NO RESPONSE");
                        weatherClient.onResponseError();
                    }
                } else {
                    if(response.body() != null) {
                        Log.e("WEATHERCITYSERVICE", response.body().toString());
                    }else {
                        Log.e("WEATHERCITYSERVICE", "NO RESPONSE");
                    }
                    weatherClient.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Weather>> call, Throwable t) {
                weatherClient.onResponseError();
                Log.e("WEATHERCITYSERVICE", t.getMessage());
            }
        });
    }

    public void getCities(final WeatherClient weatherClient, final String name){
        weatherApi.getCities(name).enqueue(new Callback<ArrayList<City>>() {
            @Override
            public void onResponse(Call<ArrayList<City>> call, Response<ArrayList<City>> response) {
                if (response.code() > 199 && response.code() < 300) {
                    if(response.body() != null) {
                        Log.i("WEATHERSERVICE", response.body().toString());
                        weatherClient.onResponseSuccess(response.body());
                    }else {
                        Log.i("WEATHERSERVICE", "NO RESPONSE");
                        weatherClient.onResponseError();
                    }
                } else {
                    if(response.body() != null) {
                        Log.e("WEATHERSERVICE", response.body().toString());
                    }else {
                        Log.e("WEATHERSERVICE", "NO RESPONSE");
                    }
                    weatherClient.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<City>> call, Throwable t) {
                weatherClient.onResponseError();
                Log.e("WEATHERSERVICE", t.getMessage());
            }
        });
    }
}
