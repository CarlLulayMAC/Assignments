package com.example.admin.myapplication.network;

import com.example.admin.myapplication.model.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetRepositoryDataService {
    @GET("{user}/repos")
    Call<Repository[]> getRepositoryData(@Path("user") String user);
}
