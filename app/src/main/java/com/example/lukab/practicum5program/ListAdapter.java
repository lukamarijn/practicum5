package com.example.lukab.practicum5program;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lukab on 6-6-2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CityViewHolder> {

    private List<City> cities;
    private Context context;
    private City city;


    // Constructor
    public ListAdapter(List<City> cities) {
        this.cities = cities;
    }

    public int getItemCount() {
        return cities.size();
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.citylist_row, parent, false);

        return new CityViewHolder(itemView, cities);
    }

    @Override
    public void onBindViewHolder(final CityViewHolder contactViewHolder, final int i) {
        city = cities.get(i);

        contactViewHolder.cityName.setText(city.getCityName());



        contactViewHolder.cityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), DetailedCityActivity.class);

                intent.putExtra("City", cities.get(i));

                v.getContext().startActivity(intent);
            }
        });


    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView cityName;


        public CityViewHolder(View v, final List<City> cities) {
            super(v);

            cityName = (TextView) v.findViewById(R.id.cityName);



        }

    }

}