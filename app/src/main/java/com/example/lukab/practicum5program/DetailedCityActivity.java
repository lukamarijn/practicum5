package com.example.lukab.practicum5program;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by lukab on 6-6-2017.
 */

public class DetailedCityActivity extends AppCompatActivity {
	
	private EditText et_ID;
	private EditText et_Name;
	private EditText et_Country;
	private EditText et_District;
	private EditText et_Population;
	
	private Button btn_Add;
	private Button btn_Edit;
	private Button btn_Delete;
	
	private City city;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed);
		
		et_ID = (EditText) findViewById(R.id.detail_ET_ID);
		et_Name = (EditText) findViewById(R.id.detail_ET_Name);
		et_Country = (EditText) findViewById(R.id.detail_ET_Country);
		et_District = (EditText) findViewById(R.id.detail_ET_District);
		et_Population = (EditText) findViewById(R.id.detail_ET_Population);
		
		btn_Add = (Button) findViewById(R.id.detail_BTN_Add);
		btn_Edit = (Button) findViewById(R.id.detail_BTN_Edit);
		btn_Delete = (Button) findViewById(R.id.detail_BTN_Delete);
		
		//et_ID.setEnabled(false);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			city = (City) extras.getSerializable("CITY");
			
			int id;
			if (Integer.valueOf(city.getID()) == null) {
				id = 0;
			} else {
				id = city.getID();
			}
			
			et_ID.setText(Integer.toString(id));
			et_Name.setText(city.getCityName());
			et_Country.setText(city.getCountryCode());
			et_District.setText(city.getDistrict());
			et_Population.setText(Long.toString(city.getPopulation()));
		}
		
		btn_Add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				City city = new City(Integer.parseInt(et_ID.getText().toString()), et_Name.getText().toString(), et_Country.getText().toString(), et_District.getText().toString(), Long.valueOf(et_Population.getText().toString()));
				
				Intent i = new Intent();
				i.putExtra("CITY", city);
				i.putExtra("Type", 1);
				setResult(RESULT_OK, i);
				
				finish();
			}
		});
		
		btn_Edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				City city = new City(Integer.parseInt(et_ID.getText().toString()), et_Name.getText().toString(), et_Country.getText().toString(), et_District.getText().toString(), Long.valueOf(et_Population.getText().toString()));
				
				Intent i = new Intent();
				i.putExtra("CITY", city);
				i.putExtra("Type", 2);
				setResult(RESULT_OK, i);
				
				finish();
			}
		});
		
		btn_Delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				City city = new City(Integer.parseInt(et_ID.getText().toString()), et_Name.getText().toString(), et_Country.getText().toString(), et_District.getText().toString(), Long.valueOf(et_Population.getText().toString()));
				
				Intent i = new Intent();
				i.putExtra("CITY", city);
				i.putExtra("Type", 3);
				setResult(RESULT_OK, i);
				
				finish();
			}
		});
	}
}
