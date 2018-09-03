package com.tdp2.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Weather {
    @SerializedName("day")
    @Expose
    public Map<String, String> day;

    @SerializedName("night")
    @Expose
    public Map<String, String> night;
}
