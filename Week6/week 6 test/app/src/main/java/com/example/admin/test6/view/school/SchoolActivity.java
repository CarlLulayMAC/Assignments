package com.example.admin.test6.view.school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.test6.R;
import com.example.admin.test6.adapter.SchoolRecyclerViewAdapter;
import com.example.admin.test6.di.component.DaggerSchoolComponent;
import com.example.admin.test6.model.response.SchoolResponse;
import com.example.admin.test6.view.DetailActivity;

import java.util.List;

import javax.inject.Inject;

public class SchoolActivity extends AppCompatActivity implements SchoolContract.SchoolView {
    RecyclerView recyclerView;

    @Inject
    SchoolPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        DaggerSchoolComponent.create().inject(this);


        presenter.attachView(this);
        presenter.getSchools();
    }

    @Override
    public void onSchoolResultReceived(List<SchoolResponse> schools) {
        recyclerView = findViewById(R.id.rvSchool);
        SchoolRecyclerViewAdapter schoolAdapter = new SchoolRecyclerViewAdapter(schools, presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(schoolAdapter);
    }

    @Override
    public void navigateToDetail(SchoolResponse school) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("School", school);
        startActivity(intent);
    }

    // This is an unnecessary method used for debugging
    public void getSchools(View view) {
        presenter.getSchools();
    }
}
