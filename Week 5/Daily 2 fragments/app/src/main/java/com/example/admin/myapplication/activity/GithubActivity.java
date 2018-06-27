package com.example.admin.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.adapter.RepositoryAdapter;
import com.example.admin.myapplication.model.Repository;
import com.example.admin.myapplication.network.GetRepositoryDataService;
import com.example.admin.myapplication.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class GithubActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Button btSearch;
    private RecyclerView repositoryRecyclerView;
    private RepositoryAdapter repositoryAdapter;
    private GetRepositoryDataService service;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        service = RetrofitInstance.getRetrofitInstance().create(GetRepositoryDataService.class);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        service.getRepositoryData(query).enqueue(new Callback<Repository[]>() {
            @Override
            public void onResponse(retrofit2.Call<Repository[]> call, Response<Repository[]> response) {
                if (response.isSuccessful()) {
                    Repository[] repositories = response.body();
                    repositoryRecyclerView = (RecyclerView) findViewById(R.id.recyclerRepositoryList);
                    List<Repository> repositoryList = new ArrayList<>();
                    for (Repository r : repositories) {
                        repositoryList.add(r);
                    }
                    repositoryAdapter = new RepositoryAdapter(repositoryList);
                    repositoryRecyclerView.setLayoutManager(
                            new LinearLayoutManager(GithubActivity.this));
                    repositoryRecyclerView.setAdapter(repositoryAdapter);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Repository[]> call, Throwable t) {

            }
        });
        return false;
    }

    public void showError() {
        Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
