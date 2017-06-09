package com.example.lukab.practicum5program;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CityRequest {
	
	private Context context;
	private CityListener listener;
	
	public CityRequest(Context context, CityListener listener) {
		this.context = context;
		this.listener = listener;
	}
	
	public void handleGetAllCities() {
		SharedPreferences sharedPref = context.getSharedPreferences(
				context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		final String token = sharedPref.getString(context.getString(R.string.saved_token), "dummy default token");
		if (token != null && !token.equals("dummy default token")) {
			JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
					Request.Method.GET,
					Config.URL_CITIES,
					null,
					new Response.Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray response) {
							ArrayList<City> result = CityMapper.mapCityList(response);
							listener.onCitiesAvailable(result);
						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.e("CityRequest", "Error: " + error.toString());
						}
					}
			) {
				@Override
				public Map<String, String> getHeaders() throws AuthFailureError {
					Map<String, String> headers = new HashMap<>();
					headers.put("Content-Type", "application/json");
					headers.put("X-Access-Token", token);
					return headers;
				}
			};
			
			VolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
		}
	}
	
	public void handlePostCity(final City newCity) {
		SharedPreferences sharedPref = context.getSharedPreferences(
				context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		final String token = sharedPref.getString(context.getString(R.string.saved_token), "dummy default token");
		if (token != null && !token.equals("dummy default token")) {
			String body = "{\"Name\":\"" +  newCity.getCityName() + "\",\"CountryCode\":\"" + newCity.getCountryCode() + "\",\"District\":\"" + newCity.getDistrict() + "\",\"Population\":\"" + newCity.getPopulation() + "\"}";
			
			try {
				JSONObject jsonBody = new JSONObject(body);
				Log.i("CityRequest", "handlePostCity - body = " + jsonBody);
				
				JsonObjectRequest objectRequest = new JsonObjectRequest(
						Request.Method.POST,
						Config.URL_CITIES,
						jsonBody,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								Log.i("CityRequest", response.toString());
								listener.onCityAvailable(newCity);
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								listener.onCitiesError(error.toString());
							}
						}
				) {
					@Override
					public Map<String, String> getHeaders() throws AuthFailureError {
						Map<String, String> headers = new HashMap<>();
						headers.put("Content-Type", "application/json");
						headers.put("X-Access-Token", token);
						return headers;
					}
				};
				
				VolleyRequestQueue.getInstance(context).addToRequestQueue(objectRequest);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handlePutCity(final City newCity) {
		SharedPreferences sharedPref = context.getSharedPreferences(
				context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		final String token = sharedPref.getString(context.getString(R.string.saved_token), "dummy default token");
		if (token != null && !token.equals("dummy default token")) {
			String body = "{\"ID\":" + newCity.getID() + ", \"Name\":\"" +  newCity.getCityName() + "\",\"CountryCode\":\"" + newCity.getCountryCode() + "\",\"District\":\"" + newCity.getDistrict() + "\",\"Population\":\"" + newCity.getPopulation() + "\"}";
			
			try {
				JSONObject jsonBody = new JSONObject(body);
				Log.i("CityRequest", "handlePutCity - body = " + jsonBody);
				
				JsonObjectRequest objectRequest = new JsonObjectRequest(
						Request.Method.PUT,
						Config.URL_CITIES + "/" + newCity.getID(),
						jsonBody,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								Log.i("CityRequest", response.toString());
								listener.onCityAvailable(newCity);
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								listener.onCitiesError(error.toString());
							}
						}
				) {
					@Override
					public Map<String, String> getHeaders() throws AuthFailureError {
						Map<String, String> headers = new HashMap<>();
						headers.put("Content-Type", "application/json");
						headers.put("X-Access-Token", token);
						return headers;
					}
				};
				
				VolleyRequestQueue.getInstance(context).addToRequestQueue(objectRequest);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleDeleteCity(final City newCity) {
		SharedPreferences sharedPref = context.getSharedPreferences(
				context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		final String token = sharedPref.getString(context.getString(R.string.saved_token), "dummy default token");
		if (token != null && !token.equals("dummy default token")) {
			String body = "{\"ID\":" + newCity.getID() + "}";
			
			try {
				JSONObject jsonBody = new JSONObject(body);
				Log.i("CityRequest", "handlePutCity - body = " + jsonBody);
				
				JsonObjectRequest objectRequest = new JsonObjectRequest(
						Request.Method.DELETE,
						Config.URL_CITIES + "/" + newCity.getID(),
						jsonBody,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								Log.i("CityRequest", response.toString());
								listener.onCityAvailable(newCity);
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								listener.onCitiesError(error.toString());
							}
						}
				) {
					@Override
					public Map<String, String> getHeaders() throws AuthFailureError {
						Map<String, String> headers = new HashMap<>();
						headers.put("Content-Type", "application/json");
						headers.put("X-Access-Token", token);
						return headers;
					}
				};
				
				VolleyRequestQueue.getInstance(context).addToRequestQueue(objectRequest);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public interface CityListener {
		void onCitiesAvailable(ArrayList<City> cities);
		
		void onCityAvailable(City city);
		
		void onCitiesError(String message);
	}
}
