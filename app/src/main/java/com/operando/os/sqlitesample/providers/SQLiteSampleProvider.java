package com.operando.os.sqlitesample.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.operando.os.sqlitesample.databases.SQLiteSampleHelper;

public class SQLiteSampleProvider extends ContentProvider {

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final String AUTHORITY = "users";

    private static final int SQLITESAMPLE_ALL = 0;
    private static final int SQLITESAMPLE_ID = 1;
    private static final int SQLITESAMPLE_FILTER = 2;

    static {
        sURIMatcher.addURI(AUTHORITY, "user_all", SQLITESAMPLE_ALL);
        sURIMatcher.addURI(AUTHORITY, "user/#", SQLITESAMPLE_ID);
        sURIMatcher.addURI(AUTHORITY, "user/address/*", SQLITESAMPLE_FILTER);
    }

    private SQLiteSampleHelper mHelper;

    @Override
    public boolean onCreate() {
        final Context context = getContext();
        mHelper = new SQLiteSampleHelper(context);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final int match = sURIMatcher.match(uri);
        switch (match) {
            case SQLITESAMPLE_ALL:
                break;
            case SQLITESAMPLE_ID:
                break;
            case SQLITESAMPLE_FILTER:
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
