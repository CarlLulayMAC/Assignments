package com.example.admin.myapplication.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.myapplication.FormFragment;
import com.example.admin.myapplication.ListFragment;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.model.Person;

public class MainActivity extends AppCompatActivity implements FormFragment.OnFragmentInteractionListener, ListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSubmit(String name, int age, String city) {
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        listFragment.addPerson(new Person(name, age, city));
    }

    @Override
    public void onListFragmentInteraction(Person person) {
        // do nothing for now
    }
}
