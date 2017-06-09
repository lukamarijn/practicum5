package com.example.lukab.practicum5program;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<City> {
	
	private City city;
	private Context context;
	
	public ListViewAdapter(Context context, ArrayList<City> cities) {
		super(context, R.layout.citylist_row, cities);
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		city = getItem(position);
		
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.citylist_row, parent, false);
		}
		
		TextView cityName = (TextView) convertView.findViewById(R.id.cityName);
		
		cityName.setText(city.getCityName());
		
		return convertView;
	}
}
