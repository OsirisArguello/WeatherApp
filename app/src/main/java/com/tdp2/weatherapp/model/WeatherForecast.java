package com.tdp2.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherForecast implements Serializable{
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("day")
    @Expose
    public DayConditions day;
    @SerializedName("night")
    @Expose
    public NightConditions night;
}
