package com.example.admin.moviedb.presenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.admin.moviedb.MovieContract;
import com.example.admin.moviedb.model.Movie;
import com.example.admin.moviedb.service.MovieService;

import java.util.List;

public class MoviePresenter implements ServiceConnection, MovieContract.Presenter {
    private MovieContract.View view;
    private MovieService movieService;
    private boolean isBound;

    public MoviePresenter(MovieContract.View view) {
        this.view = view;
        Intent intent = new Intent(view.getContext(), MovieService.class);
        view.getContext().bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void makeQuery(String query) {
        List<Movie> movies = movieService.getMovies(query);
        view.createMovieList(movies);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder iBinder) {
        MovieService.MovieBinder binder = (MovieService.MovieBinder) iBinder;
        movieService = binder.getMovieService();
        isBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
