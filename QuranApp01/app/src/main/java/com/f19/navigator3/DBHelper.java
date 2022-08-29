package com.f19.navigator3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;



public class DBHelper {

    String TAG = "DBAdapter";

    public static final String Tablename = "Ayat";
    public static final String ayahid="ayahid";
    public static final String arabic = "arabic";// 0 integer
    public static final String urdu = "urdu";// 1 text(String)


    private SQLiteDatabase db;
    private Database dbHelper;

    private static final int VERSION = 1;
    private static final String DB_NAME = "MyDB1.db";

    public DBHelper(Context context) {
        dbHelper = new Database(context);
    }

    public void open() {
        if (null == db || !db.isOpen()) {
            try {
                db = dbHelper.getWritableDatabase();
            } catch (SQLiteException sqLiteException) {
            }
        }
    }

    private class Database extends SQLiteOpenHelper {
        private static final int VERSION = 1;
        private static final String DB_NAME = "MyDB1.db";

        public Database(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String create_sql = "CREATE TABLE IF NOT EXISTS " + Tablename + "("
                    + ayahid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + arabic + " TEXT ," + urdu + " TEXT " + ")";
            db.execSQL(create_sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Tablename);
        }

    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

   public int insert(String table, ContentValues values) {
        try {
            db = dbHelper.getWritableDatabase();
            int y = (int) db.insert(table, null, values);
            db.close();
            Log.e("Data Inserted", "Data Inserted");
            Log.e("y", y + "");
            return y;
        } catch (Exception ex) {
            Log.e("Error Insert", ex.getMessage().toString());
            return 0;
        }
    }

   public void delete() {
        db.execSQL("delete from " + Tablename);
    }

    public Cursor getAllRow(String table) {
        return db.query(table, null, null, null, null, null, ayahid);
    }

    public ArrayList<Model> getProducts() {
        ArrayList<Model> ayahlist = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Tablename;
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                ayahlist.add(new Model(cursor.getString(1),
                        cursor.getString(2)));


            } while (cursor.moveToNext());
        }
        cursor.close();
        return ayahlist;


    }


}