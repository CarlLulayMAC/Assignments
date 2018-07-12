package com.example.admin.myapplication.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class PersonProvider extends ContentProvider {
    public static final int PERSON_CODE = 100;
    public static final int PERSON_CODE_WITH_ID = 101;
    public static final String TAG = PersonProvider.class.getSimpleName() + "_TAG";
    public static final UriMatcher uriMatcher = buildUriMatcher();

    private PersonHelper personHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PersonContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, PersonContract.PersonEntry.TABLE_NAME, PERSON_CODE);
        matcher.addURI(authority, PersonContract.PersonEntry.TABLE_NAME + "/#",
                PERSON_CODE_WITH_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        personHelper = new PersonHelper(getContext());
        Log.d(TAG, "onCreate: ");
        return personHelper != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case PERSON_CODE:
                cursor = personHelper.getReadableDatabase().query(
                        PersonContract.PersonEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase database = personHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case PERSON_CODE:
                long _id = database.insert(PersonContract.PersonEntry.TABLE_NAME,
                        null, values);
                if (_id != -1) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return PersonContract.PersonEntry.buildPersonWithId(_id);
            default:
                return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase database = personHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case PERSON_CODE:
                 return database.delete(PersonContract.PersonEntry.TABLE_NAME, null, null);
            default:
                return 0;
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
