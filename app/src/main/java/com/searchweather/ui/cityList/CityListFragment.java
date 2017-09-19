package com.searchweather.ui.cityList;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.searchweather.R;
import com.searchweather.databinding.FragmentCitiesBinding;
import com.searchweather.model.realmPojo.City;
import com.searchweather.ui.detail.DetailActivity;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by Aleksandr Litvinchuck on 15.09.2017.
 */

public class CityListFragment extends Fragment implements CityListView{

    FragmentCitiesBinding binding;
    private CityAdapter adapter;
    private Realm realm;
    CityListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities, container, false);

        presenter = new CityListPresenterImpl(this);

        initList();
        initRealm();

        return binding.getRoot();
    }

    private void initList() {

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        binding.cityList.setLayoutManager(manager);
        adapter = new CityAdapter();
        binding.cityList.setAdapter(adapter);
        binding.cityList.setItemAnimator(new DefaultItemAnimator());


        adapter.clickCity.subscribe(city -> {
            DetailActivity.start(getActivity(), city.getName());
        });

        adapter.clickDelete.subscribe(city -> {
            deleteCity(city);
        });

    }

    private void deleteCity(City city) {
            presenter.deleteCity(city);
    }



    private void initRealm() {

        realm = Realm.getDefaultInstance();

        updateAdapter(realm);

        realm.addChangeListener(realm1 -> {
            updateAdapter(realm1);
        });

    }

    private void updateAdapter(Realm realm) {
        List<City> cities = realm.where(City.class).findAllSorted("name", Sort.ASCENDING);
        adapter.populateCities(cities);
    }

}
