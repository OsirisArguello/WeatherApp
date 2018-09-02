package com.tdp2.weatherapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tdp2.weatherapp.R;
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
        final City city = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_view, parent, false);
        }
        // Lookup view for data population
        TextView cityNameTextView = (TextView) convertView.findViewById(R.id.city_name);

        cityNameTextView.setText(city.name);

        return convertView;
    }
}
