package com.tdp2.weatherapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tdp2.weatherapp.model.City;
import com.tdp2.weatherapp.model.WeatherForecast;
import com.tdp2.weatherapp.networking.WeatherClient;
import com.tdp2.weatherapp.networking.WeatherService;
import com.tdp2.weatherapp.view.CityAdapter;
import com.tdp2.weatherapp.view.WeatherForecastAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements WeatherClient {

    private WeatherService weatherService;
    private City city;
    private ArrayList<WeatherForecast> nextFiveDaysForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachEvents();

        setupInitials();


    }

    private void setupInitials() {
        weatherService = new WeatherService();
        lookForWeather(city.id);
    }

    private void attachEvents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //Este city viene desde la segunda pantalla
        city = (City) getIntent().getSerializableExtra("city");

        if (city==null){

            city = new City();
            StorageUtil storageUtil = new StorageUtil(MainActivity.this);
            city.name=storageUtil.getCityName();
            city.id=storageUtil.getCityCode();

        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(city.name);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.lightGray));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void lookForWeather(Integer id) {
        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.VISIBLE);
        weatherService.getWeatherForCity(this,id);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cities_button) {
            Intent citiesActivity = new Intent(getApplicationContext(), CitiesActivity.class);
            startActivity(citiesActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponseError() {

    }

    @Override
    public void onResponseSuccess(Object responseBody) {

        nextFiveDaysForecast=(ArrayList<WeatherForecast>) responseBody;
        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.INVISIBLE);

        displayForecast();

    }

    private void displayForecast() {
        final ListView forecastListView = (ListView) findViewById(R.id.list_of_days);
        WeatherForecastAdapter weatherForecastAdapter = new WeatherForecastAdapter(this, nextFiveDaysForecast);
        forecastListView.setAdapter(weatherForecastAdapter);


    }
}
