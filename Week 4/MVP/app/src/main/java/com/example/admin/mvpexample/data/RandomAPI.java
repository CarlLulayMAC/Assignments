package com.example.admin.mvpexample.data;

import com.example.admin.mvpexample.entities.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomAPI {

    String BASE_URL = "https:/randomuser.me/";

    @GET("api")
    Call<UserResponse> getUsers(@Query("results") int results);

    // https://randomuser.me/{name}/api?results={}
//    @GET("api")
//    Call<UserResponse> getUsers(@Query("results") int results, @Path("name") String name);

}
