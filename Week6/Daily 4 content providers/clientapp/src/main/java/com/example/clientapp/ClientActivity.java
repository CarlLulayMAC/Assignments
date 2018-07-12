package com.example.clientapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

public class ClientActivity extends AppCompatActivity {


    public static final String TAG = ClientActivity.class.getSimpleName() + "_TAG";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        PersonManager personManager = new PersonManager(this);
        List<Person> personList = personManager.getPersonList();

        recyclerView = findViewById(R.id.rvPerson);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PersonAdapter(personList));

        //printPeople(personList);
    }

    private void printPeople(List<Person> personList) {
        for (Person person : personList) {
            Log.d(TAG, person.name + "\n"
                    + person.age + "\n"
                    + person.city + "\n"
                    + person.email + "\n"
                    + person.phone + "\n");
        }
    }
}
