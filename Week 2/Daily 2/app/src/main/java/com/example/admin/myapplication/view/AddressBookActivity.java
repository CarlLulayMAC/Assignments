package com.example.admin.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.Utils.Constants;
import com.example.admin.myapplication.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.Integer.parseInt;

public class AddressBookActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAge;
    private EditText etCity;
    private EditText etEmail;
    private Button submit;
    private Button viewEntries;
    private ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        people = new ArrayList<>();
        setContentView(R.layout.activity_address_book);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etCity = findViewById(R.id.etCity);
        etEmail = findViewById(R.id.etEmail);
        submit = findViewById(R.id.btnSubmit);
        viewEntries = findViewById(R.id.btnViewEntries);
    }

    public void onSubmit(View view) {
        Person person = new Person(etName.getText().toString(),etCity.getText().toString(),
                etEmail.getText().toString(),parseInt(etAge.getText().toString()));
        etName.setText("");
        etAge.setText("");
        etCity.setText("");
        etEmail.setText("");
        people.add(person);
    }

    public void onViewEntries(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewContactsActivity.class);
        intent.putParcelableArrayListExtra(Constants.Key.PEOPLE, people);
        startActivity(intent);
    }
}
