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
import com.tdp2.weatherapp.model.WeatherResponse;

import com.tdp2.weatherapp.networking.WeatherClient;
import com.tdp2.weatherapp.networking.WeatherService;
import com.tdp2.weatherapp.view.CityAdapter;
import com.tdp2.weatherapp.view.WeatherDayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements WeatherClient {

    private WeatherResponse weatherDays;
    private WeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherService = new WeatherService();

        attachEvents();

        setupInitials();


    }

    private void setupInitials() {
        City city = new City();
        city.id = 3435910; // Ciudad de BsAs
        weatherService.getWeatherForCity(this,city);
    }

    private void attachEvents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //Este city viene desde la segunda pantalla
        City city = (City) getIntent().getSerializableExtra("city");

        if (city!=null){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(city.name);
        } else {
            StorageUtil storageUtil = new StorageUtil(MainActivity.this);
            if(!storageUtil.getCityName().isEmpty()){
                toolbar.setTitle(storageUtil.getCityName());
            }

        }

        //TODO CON CITY.ID INVOCAR AL SERVICIO DE WEATHER

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        Toast.makeText(this, "Response Error",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponseSuccess(Object responseBody) {
        weatherDays = (WeatherResponse) responseBody;

        final ListView listView = (ListView) findViewById(R.id.list_of_days);

        WeatherDayAdapter adapter = new WeatherDayAdapter(this, weatherDays);
        listView.setAdapter(adapter);

        Toast.makeText(this, "Response Success",
                Toast.LENGTH_LONG).show();
        //citiesListView.setSelection();
    }
}
