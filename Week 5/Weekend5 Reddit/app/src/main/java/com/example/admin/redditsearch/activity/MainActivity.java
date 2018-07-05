package com.example.admin.redditsearch.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.admin.redditsearch.R;
import com.example.admin.redditsearch.adapter.PostAdapter;
import com.example.admin.redditsearch.model.Child;
import com.example.admin.redditsearch.model.PostData;
import com.example.admin.redditsearch.model.RedditResult;
import com.example.admin.redditsearch.network.GetSubredditDataService;
import com.example.admin.redditsearch.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView postRecylcerView;
    private PostAdapter postAdapter;
    private SearchView searchView;
    private GetSubredditDataService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        service = RetrofitInstance.getRetrofitInstance().create(GetSubredditDataService.class);
        onQueryTextSubmit("funny");
    }

    private void makeCall(GetSubredditDataService service, String subreddit) {
        Call<RedditResult> call = service.getSubredditData(subreddit, 25);
        call.enqueue(new Callback<RedditResult>() {
            @Override
            public void onResponse(Call<RedditResult> call, Response<RedditResult> response) {
                List<PostData> postsList = new ArrayList<>();
                if (response.body() == null || response.body().getData().getChildren().size() == 0) {
                    Toast.makeText(MainActivity.this, "No Results", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (Child child: response.body().getData().getChildren()) {
                    postsList.add(child.getPostData());
                }
                createPostList(postsList);
            }

            @Override
            public void onFailure(Call<RedditResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createPostList(List<PostData> postsList) {
        postRecylcerView = findViewById(R.id.rvPosts);
        postAdapter = new PostAdapter(postsList);
        postRecylcerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        postRecylcerView.setAdapter(postAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        makeCall(service, query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // do nothing here
        return false;
    }
}
