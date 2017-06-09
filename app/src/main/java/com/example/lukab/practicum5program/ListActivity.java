package com.example.lukab.practicum5program;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements ListView.OnItemClickListener, CityRequest.CityListener {
	
	private ListView listView;
	private ListViewAdapter adapter;
	
	private static final String TAG = "ListActivity";
	private static final int REQUEST = 111;
	private static final int REQUEST_PUT = 112;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (tokenAvailable()) {
			setContentView(R.layout.activity_list);
			
//			CityManager.addCity(new City(1, "Test 1", "TE", "NB", 10));
//			CityManager.addCity(new City(2, "Test 2", "TE", "NB", 11));
//			CityManager.addCity(new City(3, "Test 3", "TE", "NB", 12));
//			CityManager.addCity(new City(4, "Test 4", "TE", "NB", 13));
//			CityManager.addCity(new City(5, "Test 5", "TE", "NB", 14));
			
			listView = (ListView) findViewById(R.id.cityList_LV);
			adapter = new ListViewAdapter(getApplicationContext(), CityManager.getCities());
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(this);
			
			getCities();
		} else {
			Intent login = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(login);
			finish();
		}
	}
		
		
	@Override
	public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
		Log.i(TAG, "City " + position + " is geselecteerd");
		
		Intent i = new Intent(getApplicationContext(), DetailedCityActivity.class);
		i.putExtra("CITY", CityManager.getCities().get(position));
		startActivityForResult(i, REQUEST);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST) {
			if (resultCode == RESULT_OK) {
				final City city = (City) data.getSerializableExtra("CITY");
				int type = data.getIntExtra("Type", 0);
				
				Log.i("RESULT", type + "");
				
				if (type == 1) {
					postCity(city);
				} else if (type == 2) {
					putCity(city);
				} else if (type == 3) {
					deleteCity(city);
				}
			}
		}
	}
	
	private boolean tokenAvailable() {
		boolean result = false;
		
		Context context = getApplicationContext();
		SharedPreferences sharedPref = context.getSharedPreferences(
				getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		String token = sharedPref.getString(getString(R.string.saved_token), "dummy default token");
		if (token != null && !token.equals("dummy default token")) {
			result = true;
		}
		return result;
	}
	
	@Override
	public void onCitiesAvailable(ArrayList<City> cities) {
		Log.i("ListActivity", "Cities available.");
		CityManager.clear();
		
		for (City c : cities) {
			CityManager.addCity(c);
		}
		
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onCityAvailable(City city) {
		CityManager.addCity(city);
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onCitiesError(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}
	
	private void getCities() {
		CityRequest request = new CityRequest(getApplicationContext(), this);
		request.handleGetAllCities();
	}
	
	private void postCity(City city) {
		CityRequest request = new CityRequest(getApplicationContext(), this);
		request.handlePostCity(city);
	}
	
	private void putCity(City city) {
		CityRequest request = new CityRequest(getApplicationContext(), this);
		request.handlePutCity(city);
	}
	
	private void deleteCity(City city) {
		CityRequest request = new CityRequest(getApplicationContext(), this);
		request.handleDeleteCity(city);
	}
}

