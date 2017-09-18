package com.searchweather.ui.detail;

import com.searchweather.model.weatherPojo.Result;

/**
 * Created by Aleksandr Litvinchuck on 17.09.2017.
 */

public interface DetailView {

    void showLoading(boolean isShown);

    void onWeatherLoadSuccess(Result result);

    void onWeatherLoadFail();


    void onCitySaveSuccess();
}
