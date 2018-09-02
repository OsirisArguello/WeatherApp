package com.tdp2.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class StorageUtil {

    private String CITY_CODE = "city_code";
    private String CITY_NAME = "city_name";

    private int DEFAULT_CITY_CODE = 3433955;
    private String DEFAULT_CITY_NAME = "Ciudad Autónoma de Buenos Aires";

    private Activity activity;

    public StorageUtil(Activity activity) {
        this.activity = activity;
    }

    public String getCityName() {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        if (!sharedPref.contains(CITY_NAME)) {
            initialiceValues(sharedPref);
        }
        return sharedPref.getString(CITY_NAME, DEFAULT_CITY_NAME);
    }

    public int getCityCode() {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        if (!sharedPref.contains(CITY_CODE)) {
            initialiceValues(sharedPref);
        }
        return sharedPref.getInt(CITY_CODE, DEFAULT_CITY_CODE);
    }

    public void saveCityName(String name) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(CITY_NAME, name);
        editor.commit();
    }

    public void saveCityCode(int code) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(CITY_CODE, code);
        editor.commit();
    }

    private void initialiceValues(SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(CITY_NAME, DEFAULT_CITY_NAME);
        editor.putInt(CITY_CODE, DEFAULT_CITY_CODE);
        editor.commit();
    }
}
