package com.tdp2.weatherapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tdp2.weatherapp.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {

    public CityAdapter(@NonNull Context context, @NonNull ArrayList<City> cities) {
        super(context, 0, cities);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
