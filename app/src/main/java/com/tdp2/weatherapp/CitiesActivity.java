package com.tdp2.weatherapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tdp2.weatherapp.model.City;
import com.tdp2.weatherapp.networking.WeatherClient;
import com.tdp2.weatherapp.networking.WeatherService;
import com.tdp2.weatherapp.view.CityAdapter;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity implements WeatherClient {

    ArrayList<City> cities;
    WeatherService weatherService;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);


        attachEvents();

        setupInitials();

        handleIntent(getIntent());
    }

    private void attachEvents() {

        SearchManager searchManager =(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =findViewById(R.id.search_view);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setFocusable(false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            searchCities(query);
        }

    }

    @Override
    public void onResponseSuccess(Object responseBody) {
        cities=(ArrayList<City>) responseBody;
        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading_cities);
        loadingView.setVisibility(View.INVISIBLE);

        displayCities();

    }

    @Override
    public void onResponseError() {
        Toast.makeText(this, "No fue posible conectarse al servidor, por favor reintente más tarde",
                Toast.LENGTH_LONG).show();
    }

    private void setupInitials() {
        cities=new ArrayList<City>();
        weatherService = new WeatherService();
    }

    private void searchCities(String query) {
        if(query.length()>=3){
            ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading_cities);
            loadingView.setVisibility(View.VISIBLE);
            weatherService.getCities(this,query);
        }

    }

    private void displayCities(){
        final ListView citiesListView = (ListView) findViewById(R.id.list_of_cities);
        CityAdapter cityAdapter = new CityAdapter(this, cities);
        citiesListView.setAdapter(cityAdapter);
        citiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City city = cities.get(position);

                Intent intent = new Intent(CitiesActivity.this, MainActivity.class);
                intent.putExtra("city", city);

                startActivity(intent);
            }
        });
    }
}
