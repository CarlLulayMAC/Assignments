package com.example.admin.googlebookshw.network;


import com.example.admin.googlebookshw.model.Book;
import com.example.admin.googlebookshw.model.BookList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetBookDataService {
    @GET("volumes")
    Call<BookList> getBookData(@Query("q") String searchTerm, @Query("key") String apiKey, @Query("maxResults") int maxResults);
}
