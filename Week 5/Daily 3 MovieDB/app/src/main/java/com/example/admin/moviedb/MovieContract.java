package com.example.admin.moviedb;

import android.content.Context;

import com.example.admin.moviedb.model.Movie;

import java.util.List;

public interface MovieContract {
    interface View {
        void showResult(List<Movie> movies);
        void navigateToDetail(Movie movie);
        Context getContext();
        void createMovieList(List<Movie> movies);
    }

    interface Presenter {
        void makeQuery(String query);
    }
}
