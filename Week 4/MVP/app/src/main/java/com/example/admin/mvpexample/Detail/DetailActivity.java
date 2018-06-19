package com.example.admin.mvpexample.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.mvpexample.R;
import com.example.admin.mvpexample.entities.Result;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvCity = findViewById(R.id.tvCity);
        TextView tvCell = findViewById(R.id.tvCell);
        TextView tvEmail = findViewById(R.id.tvEmail);
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvCity.setText(intent.getStringExtra("city"));
        tvCell.setText(intent.getStringExtra("cell"));
        tvEmail.setText(intent.getStringExtra("email"));
    }
}
