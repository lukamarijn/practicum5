package com.example.lukab.practicum5program;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by lukab on 6-6-2017.
 */

public class City implements Serializable {
    private int id;
    private String cityName;
	private String countryCode;
	private String district;
	private long population;

    public City(int id, String cityName, String countryCode, String district, long population) {
	    this.id = id;
        this.cityName = cityName;
	    this.countryCode = countryCode;
	    this.district = district;
	    this.population = population;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public void setPopulation(long population) {
		this.population = population;
	}
	
	public String getCityName() {
        return cityName;
    }
	
	public int getID() {
		return id;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public long getPopulation() {
		return population;
	}
}
