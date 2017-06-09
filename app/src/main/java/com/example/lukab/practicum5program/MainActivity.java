package com.example.lukab.practicum5program;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
	
	private final String TAG = "mainactivity";
	private TextView loginTV;
	private TextView passwordTV;
	private Button loginBTN;
	
	private String username;
	private String password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loginTV = (TextView) findViewById(R.id.username_TV);
		passwordTV = (TextView) findViewById(R.id.password_TV);
		
		loginBTN = (Button) findViewById(R.id.logInButton);
		
		loginBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				username = loginTV.getText().toString();
				password = passwordTV.getText().toString();
				
				if ((username.isEmpty()) || (password.isEmpty())) {
					return;
				}
				
				handleLogin(username, password);
			}
		});
	}
	
	private void handleLogin(String username, String password) {
		
		String body = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
		Log.i(TAG, "handleLogin - body = " + body);
		
		try {
			JSONObject jsonBody = new JSONObject(body);
			JsonObjectRequest jsObjRequest = new JsonObjectRequest
					(Request.Method.POST, Config.URL_LOGIN, jsonBody, new Response.Listener<JSONObject>() {
						
						@Override
						public void onResponse(JSONObject response) {
							
							try {
								String token = response.getString("token");
								
								if (token == null) {
									throw new Exception("Token not found.");
								}
								
								displayMessage("Succesvol ingelogd!");
								
								Context context = getApplicationContext();
								SharedPreferences sharedPref = context.getSharedPreferences(
										getString(R.string.preference_file_key), Context.MODE_PRIVATE);
								SharedPreferences.Editor editor = sharedPref.edit();
								editor.putString(getString(R.string.saved_token), token);
								editor.commit();
								
								Intent main = new Intent(getApplicationContext(), ListActivity.class);
								startActivity(main);
								
								finish();
								
							} catch (JSONException e) {
								Log.e(TAG, e.getMessage());
							} catch (Exception e) {
								Log.e(TAG, e.getMessage());
							}
						}
					}, new Response.ErrorListener() {
						
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.e("MainActivity", "Error: " + error.getMessage());
							handleErrorResponse(error);
						}
					});
			
			jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
					1500, // SOCKET_TIMEOUT_MS,
					2, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
					DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
			
			// Access the RequestQueue through your singleton class.
			VolleyRequestQueue.getInstance(this).addToRequestQueue(jsObjRequest);
		} catch (JSONException e) {
			
			
			e.printStackTrace();
		}
		return;
	}
	
	public void handleErrorResponse(VolleyError error) {
		Log.e(TAG, "handleErrorResponse");
		
		if (error instanceof com.android.volley.AuthFailureError) {
			String json = null;
			NetworkResponse response = error.networkResponse;
			if (response != null && response.data != null) {
				json = new String(response.data);
				json = trimMessage(json, "error");
				if (json != null) {
					json = "Error " + response.statusCode + ": " + json;
					displayMessage(json);
				}
			} else {
				Log.e(TAG, "handleErrorResponse: kon geen networkResponse vinden.");
			}
		} else if (error instanceof com.android.volley.NoConnectionError) {
			Log.e(TAG, "handleErrorResponse: server was niet bereikbaar");
//            txtLoginErrorMsg.setText(getString(R.string.error_server_offline));
		} else {
			Log.e(TAG, "handleErrorResponse: error = " + error);
		}
	}
	
	public String trimMessage(String json, String key) {
		Log.i(TAG, "trimMessage: json = " + json);
		String trimmedString = null;
		
		try {
			JSONObject obj = new JSONObject(json);
			trimmedString = obj.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return trimmedString;
	}
	
	public void displayMessage(String toastString) {
		Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_LONG).show();
	}
}

