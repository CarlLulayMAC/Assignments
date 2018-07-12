package com.example.admin.myapplication.data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;

public class PersonContract {

    public static final String CONTENT_AUTHORITY = "com.example.admin.myapplication";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public PersonContract() {
    }

    public static final class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "people";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_CITY = "city";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI
                .buildUpon()
                .appendPath(TABLE_NAME).build();

        public static Uri buildPersonWithId(long id) {
            return  CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(id))
                    .build();
        }



    }
}
