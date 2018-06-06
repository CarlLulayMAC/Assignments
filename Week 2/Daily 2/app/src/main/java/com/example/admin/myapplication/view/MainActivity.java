package com.example.admin.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPhotoActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
        startActivity(intent);
    }

    public void openEMICalculatorActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), EMIActivity.class);
        startActivity(intent);
    }

    public void openAddressBookActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddressBookActivity.class);
        startActivity(intent);
    }
}
