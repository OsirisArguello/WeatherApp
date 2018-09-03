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

    /*@Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_cities, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =(SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);

       return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.cities_button) {
            Intent citiesActivity = new Intent(getApplicationContext(), CitiesActivity.class);
            startActivity(citiesActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        //citiesListView.setSelection();
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
