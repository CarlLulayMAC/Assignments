package com.example.admin.moviedb.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.admin.moviedb.MovieContract;
import com.example.admin.moviedb.R;
import com.example.admin.moviedb.adapter.MovieAdapter;
import com.example.admin.moviedb.model.Movie;
import com.example.admin.moviedb.model.QueryResult;
import com.example.admin.moviedb.network.GetMovieDataService;
import com.example.admin.moviedb.network.RetrofitInstance;
import com.example.admin.moviedb.presenter.MoviePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MovieContract.View {
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private MoviePresenter moviePresenter;
    public static final String API_KEY = "686b8804a35c9a1ad34f8971e4759f87";
    GetMovieDataService service;
    SearchView searchView;

    //686b8804a35c9a1ad34f8971e4759f87
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: This will need moved to the presenter/service
        service = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        moviePresenter = new MoviePresenter(this);
    }

    //TODO put this in the right place
    private void makeCall(GetMovieDataService service, String searchArgs) {
        Call<QueryResult> call = service.getMovieData(API_KEY, searchArgs);
        Log.d(TAG, "Reuqest URL: " + call.request().url() + "");

        call.enqueue(new Callback<QueryResult>() {
            @Override
            public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
                List<Movie> movies = response.body().getMovies();
                createMovieList(movies);
            }

            @Override
            public void onFailure(Call<QueryResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something didn't work right.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void createMovieList(List<Movie> movieList) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerBookList);
        adapter = new MovieAdapter(movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        moviePresenter.makeQuery(query);
        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void showResult(List<Movie> movies) {
        createMovieList(movies);
    }

    @Override
    public void navigateToDetail(Movie movie) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
