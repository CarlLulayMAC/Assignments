package com.example.admin.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.myapplication.customviews.MyRectView;

public class MainActivity extends AppCompatActivity {

    private MyRectView rectView;
    int rectState;
    public static final int BLUE_RECTANGLE = 0;
    public static final int RED_SQUARE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rectState = BLUE_RECTANGLE;
        rectView = findViewById(R.id.myRectView);
    }

    @SuppressLint("NewApi")
    public void changeRectView(View view) {
        if (rectState == BLUE_RECTANGLE) {
            rectView.setBoxHeight(200);
            rectView.setBoxWidth(200);
            rectView.setBoxStartX(0);
            rectView.setBoxStartY(0);
            rectView.setFillColor(getColor(android.R.color.holo_red_light));
            rectState = RED_SQUARE;
        }
        else {
            rectView.setBoxHeight(100);
            rectView.setBoxWidth(400);
            rectView.setBoxStartX(50);
            rectView.setBoxStartY(50);
            rectView.setFillColor(getColor(android.R.color.holo_blue_light));
            rectState = BLUE_RECTANGLE;
        }
    }
}
