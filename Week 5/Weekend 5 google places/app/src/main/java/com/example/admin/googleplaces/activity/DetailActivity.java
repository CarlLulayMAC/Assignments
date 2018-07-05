package com.example.admin.googleplaces.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.admin.googleplaces.R;
import com.example.admin.googleplaces.model.Place;

public class DetailActivity extends AppCompatActivity {

    private static final String NAME = "Name";
    private static final String ADDRESS = "Address";
    private static final String RATING = "Rating";
    private static final String OPEN_NOW = "OpenNow";
    private TextView tvName;
//    private TextView tvRating;
    private TextView tvAddress;
    private TextView tvHours;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvName = findViewById(R.id.tvName);
//        tvRating = findViewById(R.id.tvRating);
        tvAddress = findViewById(R.id.tvAddress);
        tvHours = findViewById(R.id.tvOpenHours);
        ratingBar = findViewById(R.id.ratings);
        Intent intent = getIntent();
        String name = intent.getStringExtra(NAME);
        String address = intent.getStringExtra(ADDRESS);
        boolean openNow = intent.getBooleanExtra(OPEN_NOW, true);
        double rating = intent.getDoubleExtra(RATING, 0);
        tvName.setText(name);
        tvAddress.setText(address);
//        tvRating.setText();
        ratingBar.setRating((float) rating);
        if (openNow)
            tvHours.setText("Open Now");
        else
            tvHours.setText("Closed");
    }
}
