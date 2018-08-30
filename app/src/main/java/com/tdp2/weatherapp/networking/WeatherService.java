package com.tdp2.weatherapp.networking;

public class WeatherService {
    private WeatherApi weatherApi;

    public WeatherService() {
        weatherApi=ApiClient.getInstance().getWeatherClient();
    }

    public void getWeatherForCity(){
        //TODO
    }

    public void getCities(){
        //TODO
    }
}
