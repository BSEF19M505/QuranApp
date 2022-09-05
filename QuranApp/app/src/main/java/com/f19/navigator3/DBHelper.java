package com.f19.navigator3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import com.f19.navigator3.DBAccess;

public class DBHelper {

    String TAG = "DBAdapter";
    private SQLiteDatabase db;
    private final SQLiteOpenHelper dbHelper;
    int paraid;

    Context c;

    public DBHelper(Context context, int id) {
        c = context;
        this.dbHelper = new DBAccess(context);
        paraid = id;
    }

   /* public String getTranslation(int id)
    {
        cursor=db.rawQuery("Select MehmoodulHassan from tayah where SuraID = "+id+"",new String[]{});
        StringBuffer buffer =new StringBuffer();
        while(cursor.moveToNext()){
            String address=cursor.getString(0);
            buffer.append(""+address);

        }
        return buffer.toString();
    }*/


    public void open() {
        if (null == db || !db.isOpen()) {
            try {
                this.db = dbHelper.getWritableDatabase();
            } catch (SQLiteException sqLiteException) {

            }
        }
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public ArrayList getPara(int id) {

        ArrayList<Model> ayahlist = new ArrayList<>();
        db = dbHelper.getWritableDatabase();
        int a = id;

            Model aya;
            Cursor cursor = db.rawQuery("Select ArabicText,FatehMuhammadJalandhri,DrMohsinKhan from tayah where ParaID  = " + a + " ORDER BY ParaID,AyaID,SuraID  ", new String[]{});
            // Cursor cursor=db.rawQuery("Select MehmoodulHassan FROM tayah WHERE  SuraID =?", new String[]{Integer.toString(id)}); //",new String[]{Integer.toString(id)});

            if (cursor.moveToNext()) {
                do {
                    String ayah = cursor.getString(0);
                    String trans = cursor.getString(1);
                    String transeng = cursor.getString(2);
                    //ayahlist.add(new Model(ayah,trans));

                    aya = new Model(ayah, trans, transeng);
                    ayahlist.add(aya);


                } while (cursor.moveToNext());
            }
            //cursor.close();

        return ayahlist;


    }




    public ArrayList getSurah(int id) {

        ArrayList<Model> ayahlist = new ArrayList<>();
        db = dbHelper.getWritableDatabase();
        int a = id;

        Model aya;
        Cursor cursor = db.rawQuery("Select ArabicText,FatehMuhammadJalandhri,DrMohsinKhan from tayah where SuraID = " + a + "", new String[]{});
        // Cursor cursor=db.rawQuery("Select MehmoodulHassan FROM tayah WHERE  SuraID =?", new String[]{Integer.toString(id)}); //",new String[]{Integer.toString(id)});

        if (cursor.moveToNext()) {
            do {
                String ayah = cursor.getString(0);
                String trans = cursor.getString(1);
                String transeng = cursor.getString(2);

                aya = new Model(ayah, trans, transeng);
                ayahlist.add(aya);


            } while (cursor.moveToNext());
        }
        //cursor.close();
        return ayahlist;


    }


 /*   public Cursor search(String q, String table, boolean withSnippets, boolean isArabicDatabase) {


        String searchText = q;
        int pos = 0;
        int found = 0;
        boolean done = false;
        while (!done) {
            int quote = searchText.indexOf("\"", pos);
            if (quote > -1) {
                found++;
                pos = quote + 1;
            } else {
                done = true;
            }
        }

        if (found % 2 != 0) {
            searchText = searchText.replaceAll("\"", "");
        }



        final boolean useFullTextIndex = (schemaVersion > 1 && !isArabicDatabase);
        String qtext = searcher.getQuery(withSnippets, useFullTextIndex, table,
                "rowid as " + BaseColumns._ID + ", " + COL_SURA + ", " + COL_AYAH, COL_TEXT) +
                " " + searcher.getLimit(withSnippets);
        searchText = searcher.processSearchText(searchText, useFullTextIndex);
//    Crashlytics.log("search query: " + qtext + ", query: " + searchText); //1L

        final String[] columns = new String[] { BaseColumns._ID, COL_SURA, COL_AYAH, COL_TEXT };
        try {
            return searcher.runQuery(database, qtext, searchText, q, withSnippets, columns);
        } catch (Exception e) {
//      Crashlytics.logException(e); //1L
            return null;
        }
    }*/
}











