package com.example.admin.googleplaces.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

        public static final String BASEURL = "https://maps.googleapis.com/";
        public static final String KEY = "AIzaSyCYoPa5nfFE647sNvSGYqNNhimE3W_F5h4";
        private static Retrofit retrofit;

        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }

}
