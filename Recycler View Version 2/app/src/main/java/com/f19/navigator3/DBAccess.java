package com.f19.navigator3;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DBAccess extends SQLiteAssetHelper {
    Context context;
        private static final String DATABASE_NAME= "qurandb.db";
        private static final int DATABASE_VERSION=1;

        public DBAccess(Context context) {
            super(context, DATABASE_NAME, context.getDatabasePath(DATABASE_NAME).getPath(), null, DATABASE_VERSION);

        }
    }

