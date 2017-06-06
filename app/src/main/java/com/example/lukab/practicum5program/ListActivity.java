package com.example.lukab.practicum5program;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView list =  (ListView) findViewById(R.id.cityList_LV);
//        ListAdapter adapter = new ListAdapter()
//        list.setAdapter(adapter);


    }


}

