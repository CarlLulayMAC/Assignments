package com.example.admin.test6.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.test6.R;
import com.example.admin.test6.di.component.DaggerSchoolComponent;
import com.example.admin.test6.model.response.SATResponse;
import com.example.admin.test6.model.response.SchoolResponse;
import com.example.admin.test6.view.school.SchoolContract;
import com.example.admin.test6.view.school.SchoolPresenter;

import java.util.List;

import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity implements SchoolContract.DetailView {

    @Inject
    SchoolPresenter schoolPresenter;
    private TextView tvName;
    private TextView tvMath;
    private TextView tvReading;
    private TextView tvWriting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        DaggerSchoolComponent.create().inject(this);
        tvName = findViewById(R.id.tvName);
        tvMath = findViewById(R.id.tvSatMath);
        tvReading = findViewById(R.id.tvSatReading);
        tvWriting = findViewById(R.id.tvSatWriting);
        SchoolResponse school = (SchoolResponse) intent.getSerializableExtra("School");
        tvName.setText(school.getSchoolName());
    }


    @Override
    public void onSATResultReceived(List<SATResponse> satScores) {
        SATResponse satScore = satScores.get(0);

        tvMath.setText(satScore.getSatMathAvgScore());
        tvReading.setText(satScore.getSatCriticalReadingAvgScore());
        tvWriting.setText(satScore.getSatWritingAvgScore());
    }
}
