package com.searchweather.model.weatherPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aleksandr Litvinchuck on 17.09.2017.
 */
public class Result {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("list")
    @Expose
    private List<ListWeather> listWeather = null;
    @SerializedName("city")
    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }


    public List<ListWeather> getListWeather() {
        return listWeather;
    }

    public void setListWeather(List<ListWeather> listWeather) {
        this.listWeather = listWeather;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
