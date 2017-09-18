package com.searchweather;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * Created by Aleksandr Litvinchuck on 15.09.2017.
 */

public class App extends Application {


    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        Realm.init(context);


    }


    public static Context getContext() {
        return context;
    }

}
