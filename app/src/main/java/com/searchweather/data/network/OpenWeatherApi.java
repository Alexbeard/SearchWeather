package com.searchweather.data.network;

import com.searchweather.model.weatherPojo.Result;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by Aleksandr Litvinchuck on 15.09.2017.
 */

public interface  OpenWeatherApi {


    @GET("forecast")
    Single<Result> getWeather(
            @Query("q") String cityName,
            @Query("appid") String appId,
            @Query("units") String units
     );


}
