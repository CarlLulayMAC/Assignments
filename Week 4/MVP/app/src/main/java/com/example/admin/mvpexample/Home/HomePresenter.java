package com.example.admin.mvpexample.Home;

import android.util.Log;

import com.example.admin.mvpexample.data.RandomAPI;
import com.example.admin.mvpexample.entities.Result;
import com.example.admin.mvpexample.entities.UserResponse;
import com.example.admin.mvpexample.helper.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {

    public static final String TAG = HomePresenter.class.getSimpleName() + "_TAG";
    public static final int USER_COUNT = 3;
    private HomeContract.View homeView;
    private RandomAPI randomAPI;

    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
        this.randomAPI = RetrofitHelper.getInstance().getRandomAPI();
    }

    @Override
    public void getResult(int numResults) {
        // Here we call the model/service/managers/interactors/use cases, repository, etc
        // Can be synchronous or async
        getRandomUsers(numResults);
    }

    @Override
    public void onNavigateToDetail(Result result) {
        // Chance to prepare data to be shared
        homeView.navigateToDetail(result);
    }

    @Override
    public void onViewDestroyed() {
        this.homeView = null;
    }


    private void getRandomUsers(int count) {
        randomAPI.getUsers(count).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                List<Result> results = userResponse.getResults();
                homeView.showResult(results);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                homeView.showError();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
