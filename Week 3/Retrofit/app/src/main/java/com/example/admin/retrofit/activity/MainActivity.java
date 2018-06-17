package com.example.admin.retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.retrofit.adapter.EmployeeAdapter;
import com.example.admin.retrofit.model.Employee;
import com.example.admin.retrofit.model.EmployeeList;
import com.example.admin.retrofit.network.GetEmployeeDataService;
import com.example.admin.retrofit.network.RetrofitInstance;
import com.example.admin.retrofit.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Create handle for the RetrofitInstance interface*/
        GetEmployeeDataService service = RetrofitInstance.getRetrofitInstance()
                .create(GetEmployeeDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<EmployeeList> call = service.getEmployeeData(100);

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                generateEmployeeList(response.body().getEmployeeArrayList());
            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateEmployeeList(ArrayList<Employee> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_employee_list);

        adapter = new EmployeeAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}