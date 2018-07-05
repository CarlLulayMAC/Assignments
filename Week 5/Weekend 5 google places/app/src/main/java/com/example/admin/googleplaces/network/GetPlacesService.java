package com.example.admin.googleplaces.network;

import com.example.admin.googleplaces.model.PlacesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetPlacesService {
    @GET("/maps/api/place/nearbysearch/json")
    Call<PlacesResponse> getNearbyePlaces(@Query("location") String location,
                                          @Query("radius") int radius,
                                          @Query("key") String apiKey,
                                          @Query("keyword") String keyword);
}
