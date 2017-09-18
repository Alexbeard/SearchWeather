package com.searchweather.model.realmPojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Aleksandr Litvinchuck on 17.09.2017.
 */

public class City extends RealmObject{

    @PrimaryKey
     String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
