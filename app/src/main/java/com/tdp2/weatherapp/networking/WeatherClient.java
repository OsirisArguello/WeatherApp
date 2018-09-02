package com.tdp2.weatherapp.networking;

public interface WeatherClient<T> {

    void onResponseSuccess(T responseBody);

    void onResponseError();
}
