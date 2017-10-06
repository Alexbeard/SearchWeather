package com.searchweather.data.network;

import com.searchweather.model.realmPojo.City;
import com.searchweather.model.weatherPojo.Result;

import io.realm.RealmResults;
import rx.Observable;
import rx.Single;

/**
 * Created by Aleksandr Litvinchuck on 15.09.2017.
 */

public interface DataProvider {

    Single<Result> getWeather(String cityName);

    void saveCity(City city);

    void deleteCity(City city);

    Observable<RealmResults<City>> getCities ();

}
