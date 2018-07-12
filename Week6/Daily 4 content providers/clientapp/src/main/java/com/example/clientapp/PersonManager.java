package com.example.clientapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import static com.example.clientapp.PersonManager.URI.BASE_CONTENT_URI;

public class PersonManager {

    Context context;
    private List<Person> personList;

    public PersonManager(Context context) {
        this.context = context;
        personList = new ArrayList<>();
    }

    static class URI {
        public static final String CONTENT_AUTHORITY = "com.example.admin.myapplication";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    }

    static class COLUMNS {
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_CITY = "city";
    }

    public static final String TABLE_NAME = "people";
    public static final Uri CONTENT_URI = BASE_CONTENT_URI
            .buildUpon()
            .appendPath(TABLE_NAME).build();

    public List<Person> getPersonList() {
        Cursor cursor = context.getContentResolver().query(CONTENT_URI,
                null,
                null,
                null,
                null,
                null);
        personList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(COLUMNS.COLUMN_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(COLUMNS.COLUMN_AGE));
            String city = cursor.getString(cursor.getColumnIndex(COLUMNS.COLUMN_CITY));
            String email = cursor.getString(cursor.getColumnIndex(COLUMNS.COLUMN_EMAIL));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMNS.COLUMN_PHONE));
            personList.add(new Person(name, age, city, email, phone));
        }
        return personList;
    }
}
