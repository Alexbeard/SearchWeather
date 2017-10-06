package com.searchweather.ui.cityList;

import com.searchweather.model.realmPojo.City;

import java.util.List;

/**
 * Created by Aleksandr Litvinchuck on 18.09.2017.
 */

public interface CityListView {


    void updateCityList(List<City> cities);
}
