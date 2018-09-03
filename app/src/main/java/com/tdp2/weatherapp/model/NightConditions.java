package com.tdp2.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NightConditions implements Serializable{

    @SerializedName("temperature")
    @Expose
    public String temperature;

    @SerializedName("icon")
    @Expose
    public String iconName;
}
