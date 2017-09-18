package com.searchweather.data.network;

import android.support.annotation.IntRange;

import com.readystatesoftware.chuck.ChuckInterceptor;
import com.searchweather.App;
import com.searchweather.model.realmPojo.City;
import com.searchweather.model.weatherPojo.Result;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Aleksandr Litvinchuck on 15.09.2017.
 */

public class DataProviderImpl implements DataProvider {

    public static final String OPENWEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String API_KEY = "d2a6b21c943e38d9e44edcc03c9912ad";
    private static final String UNITS = "metric";
    private Realm realm;


    private static volatile DataProviderImpl instance;

    private DataProviderImpl() {
        //Prevent form the reflection api.
        if (instance != null) {
            throw new AssertionError("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static DataProviderImpl getInstance() {
        if (instance == null) {
            synchronized (DataProviderImpl.class) {
                if (instance == null) {
                    instance = new DataProviderImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public Single<Result> getWeather(String cityName) {
        return provideWeatherClient().getWeather(cityName, API_KEY, UNITS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    private OpenWeatherApi provideWeatherClient() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(OPENWEATHER_BASE_URL)
                .client(provideHttpClient(30))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherApi.class);
    }


    private OkHttpClient provideHttpClient(@IntRange(from = 0, to = 1000) int waitingTime) {
        return new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(App.getContext()))
                .connectTimeout(waitingTime, TimeUnit.SECONDS)
                .readTimeout(waitingTime, TimeUnit.SECONDS)
                .build();
    }


    @Override
    public void saveCity(City city) {

        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(city);
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public void deleteCity(City city) {

        try {
            realm = Realm.getDefaultInstance();
            RealmResults<City> cities = realm.where(City.class)
                    .equalTo("name", city.getName())
                    .findAllSorted("name", Sort.ASCENDING);

            realm.executeTransaction(realm1 -> cities.deleteAllFromRealm());
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

    }
}
