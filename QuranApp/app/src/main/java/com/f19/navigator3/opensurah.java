package com.f19.navigator3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class opensurah extends AppCompatActivity {
    Context context;

            int id,start,end;

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        DBHelper controller = new DBHelper(getApplicationContext(), id);
        controller.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opensurah);

        Intent Intent = getIntent();
        id = Intent.getIntExtra("id", 0);
       // start = Intent.getIntExtra("start", 0);
       // end = Intent.getIntExtra("end", 0);
       //Toast t=Toast.makeText(this,String.valueOf(id),Toast.LENGTH_SHORT);
       // t.show();
       // try {
            /*if (controller == null) {
                DBHelper controller = new DBHelper(this, id);
            }*/
            ArrayList<Model> myList = controller.getSurah(id);
            if (myList.size() != 0) {
                lv = findViewById(R.id.lstView);
                CustomAdapter adapter = new CustomAdapter(this, myList);

                lv.setAdapter(adapter);
            }
      //  } catch (Exception ex) {
          //  Toast("FillList error: " + ex.getMessage(), ex);
      //  }
    }

            void Toast(String message, Exception ex) {
                if (ex != null)

                Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            }

        }