package com.example.lukab.practicum5program;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CityMapper {
	
	public static final String CITY_ID = "ID";
	public static final String CITY_NAME ="Name";
	public static final String CITY_COUNTRY = "CountryCode";
	public static final String CITY_DISTRICT = "District";
	public static final String CITY_POPULATION = "Population";
	
//	public static ArrayList<City> mapCityList(JSONObject response) {
//		ArrayList<City> result = new ArrayList<>();
//		
//		try {
//			int id = response.getInt(CITY_ID);
//			String name = response.getString(CITY_NAME);
//			String country = response.getString(CITY_COUNTRY);
//			String district = response.getString(CITY_DISTRICT);
//			long population = response.getLong(CITY_POPULATION);
//			
//			City city = new City(id, name, country, district, population);
//			result.add(city);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
	public static ArrayList<City> mapCityList(JSONArray response) {
		ArrayList<City> result = new ArrayList<>();
		
		try {
			
			for (int i = 0; i < response.length(); i++) {
				JSONObject object = response.getJSONObject(i);
				Log.i("CityMapper", object.toString());
				
				int id = object.getInt(CITY_ID);
				String name = object.getString(CITY_NAME);
				String country = object.getString(CITY_COUNTRY);
				String district = object.getString(CITY_DISTRICT);
				long population = object.getLong(CITY_POPULATION);
				
				City city = new City(id, name, country, district, population);
				result.add(city);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
