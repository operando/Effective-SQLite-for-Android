package com.operando.os.sqlitesample.databases;

import android.net.Uri;
import android.provider.BaseColumns;

public class User {

    public static final String TABLE_NAME = "users";

    public static final Uri CONTENT_URI = Uri.parse("content://users");

    public static final class UserColumns implements BaseColumns {
        public static final String ADDRESS = "address";
    }
}
