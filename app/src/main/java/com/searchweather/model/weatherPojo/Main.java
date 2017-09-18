package com.searchweather.model.weatherPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Aleksandr Litvinchuck on 17.09.2017.
 */
public class Main {

    @SerializedName("temp")
    @Expose
    private double temp;
    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;
    @SerializedName("humidity")
    @Expose
    private int humidity;


    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getTempMin() {
        return String.valueOf(tempMin);
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return String.valueOf(tempMax);
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }




    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }



}