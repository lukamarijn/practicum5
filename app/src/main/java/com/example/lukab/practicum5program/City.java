package com.example.lukab.practicum5program;

import java.io.Serializable;

/**
 * Created by lukab on 6-6-2017.
 */

public class City implements Serializable {

    private String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}
