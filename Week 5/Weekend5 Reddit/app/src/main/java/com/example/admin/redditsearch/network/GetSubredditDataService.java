package com.example.admin.redditsearch.network;

import com.example.admin.redditsearch.model.RedditResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetSubredditDataService {
    @GET("/r/{subreddit}/.json")
    Call<RedditResult> getSubredditData(@Path("subreddit") String subreddit,
                                        @Query("limit") int limit);

}