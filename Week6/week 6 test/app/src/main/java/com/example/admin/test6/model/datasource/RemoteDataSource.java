package com.example.admin.test6.model.datasource;

import com.example.admin.test6.model.response.SATResponse;
import com.example.admin.test6.model.response.SchoolResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RemoteDataSource {

    public static final String BASEURL = "https://data.cityofnewyork.us";

    public static Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static Observable<List<SchoolResponse>> getSchoolResponse() {
        RemoteService remoteService = createRetrofit().create(RemoteService.class);
        return remoteService.getSchoolResponse();
    }

    public static Observable<SATResponse> getSATResponse(String dbn) {
        RemoteService remoteService = createRetrofit().create(RemoteService.class);
        return remoteService.getSATResponse(dbn);
    }

    interface RemoteService {
        @GET("/resource/97mf-9njv.json")
        Observable<List<SchoolResponse>> getSchoolResponse();

        @GET("/resource/734v-jeq5.json")
        Observable<SATResponse> getSATResponse(@Query("dbn") String dbn);

    }
}
