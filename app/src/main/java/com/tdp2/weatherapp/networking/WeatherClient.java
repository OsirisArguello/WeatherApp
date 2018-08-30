package com.tdp2.weatherapp.networking;

public interface WeatherClient<T> {

    public abstract void onResponseSuccess(T responseBody);

    public abstract void onResponseError();
}
