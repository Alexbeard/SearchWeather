package com.searchweather.ui.detail;

import com.searchweather.data.network.DataProvider;
import com.searchweather.data.network.DataProviderImpl;
import com.searchweather.model.realmPojo.City;

/**
 * Created by Aleksandr Litvinchuck on 17.09.2017.
 */

public class DetailPresenterImpl implements DetailPresenter {

    DetailView view;
    DataProvider dataProvider;

    public DetailPresenterImpl(DetailView view) {
        this.view = view;
        dataProvider = DataProviderImpl.getInstance();
    }


    public void loadWeather(String cityName) {


        dataProvider.getWeather(cityName)
                .subscribe(
                        result -> view.onWeatherLoadSuccess(result),
                        throwable -> view.onWeatherLoadFail()
                );

    }

    @Override
    public void saveCity(City city) {
        dataProvider.saveCity(city);
        view.onCitySaveSuccess();
    }
}
