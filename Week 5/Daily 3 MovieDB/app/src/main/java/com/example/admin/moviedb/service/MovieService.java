package com.example.admin.moviedb.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.moviedb.activity.MainActivity;
import com.example.admin.moviedb.model.Movie;
import com.example.admin.moviedb.model.QueryResult;
import com.example.admin.moviedb.network.GetMovieDataService;
import com.example.admin.moviedb.network.RetrofitInstance;
import com.example.admin.moviedb.presenter.MoviePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieService extends Service {
    private static final String TAG = Movie.class.getSimpleName() + "_TAG";
    IBinder movieBinder = new MovieBinder(this);
    private static final String API_KEY = "686b8804a35c9a1ad34f8971e4759f87";
    GetMovieDataService service;
    MoviePresenter moviePresenter;

    public MovieService() {
        Log.d(TAG, "MovieService: Constructor");
        service = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return movieBinder;
    }

    private void makeCall(GetMovieDataService service, String searchArgs) {
        Call<QueryResult> call = service.getMovieData(API_KEY, searchArgs);
        Log.d(TAG, "Reuqest URL: " + call.request().url() + "");
        List<Movie> movies;
        call.enqueue(new Callback<QueryResult>() {
            @Override
            public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
                List<Movie> movies = response.body().getMovies();

//                createMovieList(movies);
            }

            @Override
            public void onFailure(Call<QueryResult> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something didn't work right. Probably your fault", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<Movie> getMovies(String query) {
        // TODO --- Our problem lies here in that we can't wait for the data to come back
        // We can use broadcast receivers or handlers, or event bus, or we can use an interface (which we have)
        // to call out to the presenter
        makeCall(service, query);
        return null;
    }

    public class MovieBinder extends Binder {
        MovieService movieService;

        public MovieBinder(MovieService movieService) {
            this.movieService = movieService;
        }

        public MovieService getMovieService() {
            return movieService;
        }
    }
}
