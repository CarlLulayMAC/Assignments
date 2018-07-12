package com.example.admin.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.myapplication.data.PersonContract;
import com.example.admin.myapplication.data.PersonHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    PersonHelper helper;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new PersonHelper(this);
        database = helper.getWritableDatabase();
        deleteData();
        insertDummyData();
        getPeople();
    }

    private void insertDummyData() {
        insertPerson("Bob",
                80,
                "Washington D.C.",
                "bob@aol.com",
                "1-800-bob-dole");
        insertPerson("John",
                30,
                "New York",
                "john@aol.com",
                "555-555-5555");
        insertPerson("Billy",
                12,
                "Miami",
                "billy@aol.com",
                "555-555-5555");
        insertPerson("Susan",
                65,
                "Memphis",
                "susan@aol.com",
                "555-555-5555");
        insertPerson("Betty",
                52,
                "Fresno",
                "betty@aol.com",
                "555-555-5555");
        insertPerson("Clara",
                27,
                "Chicago",
                "clara@aol.com",
                "555-555-5555");
    }

    private void insertPerson(String name, int age, String city, String email, String phone) {
        ContentValues values = new ContentValues();
        values.put(PersonContract.PersonEntry.COLUMN_NAME, name);
        values.put(PersonContract.PersonEntry.COLUMN_AGE, age);
        values.put(PersonContract.PersonEntry.COLUMN_CITY, city);
        values.put(PersonContract.PersonEntry.COLUMN_EMAIL, email);
        values.put(PersonContract.PersonEntry.COLUMN_PHONE, phone);
        getContentResolver().insert(PersonContract.PersonEntry.CONTENT_URI, values);
    }

    private Cursor getPeople() {
        Cursor cursor = getContentResolver().query(PersonContract.PersonEntry.CONTENT_URI,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor
                        .getColumnIndex(PersonContract.PersonEntry.COLUMN_NAME));
                Log.d(TAG, "getPeople: " + name);
            } while (cursor.moveToNext());
        }

        return cursor;
    }

    private void deleteData() {
        getContentResolver().delete(PersonContract.PersonEntry.CONTENT_URI,null, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(database != null)
            database.close();
        helper = null;
    }
}
