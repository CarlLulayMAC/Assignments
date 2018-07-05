package com.example.admin.moviedb.network;

import com.example.admin.moviedb.model.QueryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface GetMovieDataService {
    //https://api.themoviedb.org/3/search/movie?api_key=686b8804a35c9a1ad34f8971e4759f87&language=en-US&query=Deadpool
    @GET("search/movie")
    Call<QueryResult> getMovieData(@Query("api_key") String api_key, @Query("query") String query);


}
