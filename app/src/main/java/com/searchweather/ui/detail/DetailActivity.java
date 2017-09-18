package com.searchweather.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.searchweather.R;
import com.searchweather.databinding.ActivityDetailBinding;
import com.searchweather.model.realmPojo.City;
import com.searchweather.model.weatherPojo.Result;

/**
 * Created by Aleksandr Litvinchuck on 17.09.2017.
 */

public class DetailActivity extends AppCompatActivity implements DetailView {

    ActivityDetailBinding binding;

    private static final String CITY_NAME = "cityName";
    DetailPresenter presenter;
    WeatherAdapter adapter;
    private String cityName;


    public static void start(Context context, String cityName) {
        Intent starter = new Intent(context, DetailActivity.class);
        starter.putExtra(CITY_NAME, cityName);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        cityName = getIntent().getStringExtra(CITY_NAME);

        presenter = new DetailPresenterImpl(this);
        initToolbar();
        initList();
        initViews();


    }

    private void initToolbar() {

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(cityName);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }
    }

    private void initList() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.weatherList.setLayoutManager(manager);
        adapter = new WeatherAdapter();
        binding.weatherList.setAdapter(adapter);
        binding.weatherList.setItemAnimator(new DefaultItemAnimator());


        showLoading(true);
        presenter.loadWeather(cityName);

    }

    private void initViews() {

        binding.save.setOnClickListener(v -> {
            saveCity();
        });

    }

    private void saveCity() {

        City city = new City();
        city.setName(cityName);
        presenter.saveCity(city);
    }


    @Override
    public void showLoading(boolean isShown) {

        View progress = findViewById(R.id.progress_view);

        if (progress != null) {
            progress.setVisibility(isShown ? View.VISIBLE : View.GONE);
        } else {
            Toast.makeText(this, "NO PROGRESS", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onWeatherLoadSuccess(Result result) {

        showLoading(false);
        adapter.populateItems(result.getListWeather());

    }

    @Override
    public void onWeatherLoadFail() {
        showLoading(false);
        Toast.makeText(this, "Что то пошло не так", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCitySaveSuccess() {
        Toast.makeText(this, "Город " + cityName + " сохранен", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
