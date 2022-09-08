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
   // int paraid;

    Context c;

    public DBHelper(Context context) {
        c = context;
        this.dbHelper = new DBAccess(context);
        //paraid = id;
    }

    public String gettranslation(int ayaid,int id)
    {
        db = dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select FatehMuhammadJalandhri from tayah where AyaNo = ? and SuraId =?", new String[]{String.valueOf(ayaid), String.valueOf(id)});
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            String address=cursor.getString(0);
            buffer.append(""+address);

        }
        return buffer.toString();
    }


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
    String getAyah(int ayatno, int id)
    {
        ArrayList<Model> list = new ArrayList<>();
        db = dbHelper.getWritableDatabase();


        Model aya;
        Cursor cursor = db.rawQuery("select ArabicText from tayah where AyaNo = ? and SuraId =?", new String[]{String.valueOf(ayatno), String.valueOf(id)});

        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext())
        {
            String str = cursor.getString(0);

            buffer.append(""+str);
        }
        return buffer.toString();
    }


}











