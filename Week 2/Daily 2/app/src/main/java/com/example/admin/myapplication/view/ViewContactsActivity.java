package com.example.admin.myapplication.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.Utils.Constants;
import com.example.admin.myapplication.models.Person;

import java.util.ArrayList;

public class ViewContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        TextView contactList = findViewById(R.id.tvContacts);
        Intent intent = getIntent();
        ArrayList<Person> people = intent.getParcelableArrayListExtra(Constants.Key.PEOPLE);
        String contactString = "Contacts: " + "\n";
        for (Person person : people) {
            contactString = contactString.concat(person.getName() + "\n"
                    + person.getAge() + "\n"
                    + person.getCity() + "\n"
                    + person.getEmail() + "\n"
                    + "===============================" + "\n");
        }
        contactList.setText(contactString);
    }
}
