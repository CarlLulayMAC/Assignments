package com.example.admin.moviedb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.moviedb.R;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvPopularity = findViewById(R.id.tvPopularity);
        TextView tvVoteAverage = findViewById(R.id.tvVoteAverage);
        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("title"));
        tvDate.setText(intent.getStringExtra("release_date"));
        tvPopularity.setText(intent.getDoubleExtra("popularity", 0) + "");
        tvVoteAverage.setText(intent.getDoubleExtra("vote_average", 0) + "");
    }
}
