package com.searchweather.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.searchweather.R;
import com.searchweather.databinding.ActivityMainBinding;
import com.searchweather.ui.cityList.CityListFragment;
import com.searchweather.ui.search.SearchCityFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private TabsAdapter adapter;
    private CityListFragment cityListFragment;
    private SearchCityFragment searchCityFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initToolbar();
        initViewPager();


    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));
        }

    }

    private void initViewPager() {

        adapter = new TabsAdapter(getSupportFragmentManager());

        searchCityFragment = new SearchCityFragment();
        adapter.addFrag(searchCityFragment, getString(R.string.fragment_search_cities_tab_title).toUpperCase());

        cityListFragment = new CityListFragment();
        adapter.addFrag(cityListFragment, getString(R.string.fragment_cities_tab_title).toUpperCase());

        binding.viewpager.setAdapter(adapter);
        binding.tabs.setupWithViewPager(binding.viewpager);


    }


}
