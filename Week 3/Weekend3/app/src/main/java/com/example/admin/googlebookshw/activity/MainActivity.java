package com.example.admin.googlebookshw.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.admin.googlebookshw.R;
import com.example.admin.googlebookshw.adapter.BookAdapter;
import com.example.admin.googlebookshw.model.Book;
import com.example.admin.googlebookshw.model.BookList;
import com.example.admin.googlebookshw.network.GetBookDataService;
import com.example.admin.googlebookshw.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//API KEY: AIzaSyBN6GpgnKJw39rsECTUHtCsvhcq59RFUSE
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private BookAdapter adapter;
    private RecyclerView recyclerView;
    public static final String API_KEY = "AIzaSyBN6GpgnKJw39rsECTUHtCsvhcq59RFUSE";
    GetBookDataService service;
    private int MAX_RESULTS = 40; //40 is the max
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = RetrofitInstance.getRetrofitInstance().create(GetBookDataService.class);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
    }

    private void makeCall(GetBookDataService service, String searchArgs) {
        Call<BookList> call = service.getBookData(searchArgs, API_KEY, MAX_RESULTS);
        Log.d(TAG, "Reuqest URL: " + call.request().url() + "");

        call.enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                createBookList(response.body().getBookArrayList());
            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something didn't work right. Probably your fault", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createBookList(ArrayList<Book> bookDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerBookList);
        adapter = new BookAdapter(bookDataList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        makeCall(service, query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
