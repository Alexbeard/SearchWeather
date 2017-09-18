package com.searchweather.ui.cityList;

import com.searchweather.data.network.DataProvider;
import com.searchweather.data.network.DataProviderImpl;
import com.searchweather.model.realmPojo.City;

/**
 * Created by Aleksandr Litvinchuck on 18.09.2017.
 */

public class CityListPresenterImpl implements CityListPresenter {

    CityListView view;
    DataProvider dataProvider;


    public CityListPresenterImpl(CityListView view) {
        this.view = view;
        dataProvider = DataProviderImpl.getInstance();
    }


    @Override
    public void deleteCity(City city) {
        dataProvider.deleteCity(city);
    }

}
