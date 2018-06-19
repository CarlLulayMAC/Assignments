package com.example.admin.mvpexample.helper;

import com.example.admin.mvpexample.data.RandomAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static RetrofitHelper INSTANCE;
    private RandomAPI randomAPI;

    private RetrofitHelper() {
        randomAPI = createRandomApi(prepareRetrofit());
    }

    public static RetrofitHelper getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RetrofitHelper();
        return INSTANCE;
    }

    public Retrofit prepareRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(RandomAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RandomAPI createRandomApi(Retrofit client) {
        return client.create(RandomAPI.class);
    }

    public RandomAPI getRandomAPI() {
        return randomAPI;
    }
}
