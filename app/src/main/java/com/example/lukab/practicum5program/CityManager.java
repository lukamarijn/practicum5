package com.example.lukab.practicum5program;

import java.util.ArrayList;

public class CityManager {
	
	private static ArrayList<City> cities = new ArrayList<>();
	
	public static void addCity(City city) {
		cities.add(city);
	}
	
	public static ArrayList<City> getCities() {
		return cities;
	}
	
	public static void clear() {
		cities.clear();
	}
}
