package com.searchweather.ui.detail;

import com.searchweather.model.realmPojo.City;

/**
 * Created by Aleksandr Litvinchuck on 17.09.2017.
 */

public interface DetailPresenter {

    void loadWeather(String cityName);

    void saveCity(City city);


}
