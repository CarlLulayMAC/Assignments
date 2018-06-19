package com.example.admin.mvpexample.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mvpexample.Detail.DetailActivity;
import com.example.admin.mvpexample.R;
import com.example.admin.mvpexample.adapters.HomeAdapter;
import com.example.admin.mvpexample.entities.Result;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View, View.OnClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName() + "_TAG";
    private Button resultBtn;
    private HomePresenter homePresenter;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        homePresenter = new HomePresenter(this);
    }

    @Override
    public void showResult(List<Result> results) {
        buildList(results);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDetail(Result result) { // pass in result
        // add result as extras to intent.
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("name", result.getName().getFirst() + " " + result.getName().getLast());
        detailIntent.putExtra("city", result.getLocation().getCity() + ", " + result.getLocation().getState());
        detailIntent.putExtra("cell", result.getCell());
        detailIntent.putExtra("email", result.getEmail());
        startActivity(detailIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btResult:
                int numResults;
                String input = etNumber.getText().toString();
                if (input == null || Integer.parseInt(input) < 1)
                    numResults = 1;
                else
                    numResults = Integer.parseInt(etNumber.getText().toString());
                homePresenter.getResult(numResults);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.onViewDestroyed();
    }

    private void initViews() {
        etNumber = findViewById(R.id.etNumber);
        resultBtn = findViewById(R.id.btResult);
        resultBtn.setOnClickListener(this);
    }

    private void buildList(List<Result> results) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerUserList);
        homeAdapter = new HomeAdapter(results, homePresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(homeAdapter);
    }
}
