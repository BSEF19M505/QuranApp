package com.f19.navigator3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class openparah extends AppCompatActivity {
    Context context=this;
    int id,start,end;
    DBHelper controller;
    ListView lv;
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openparah);

        controller  = new DBHelper(context);

        Intent Intent = getIntent();
        id =Intent.getIntExtra("id",0);
      //start = Intent.getIntExtra("start", 0);
     // end = Intent.getIntExtra("end", 0);
       // Toast t=Toast.makeText(this,String.valueOf(id),Toast.LENGTH_SHORT);
       // t.show();
        FillList();

    }
        public void FillList(){

                ArrayList<Model> myList = controller.getPara(id);
                if (myList.size() != 0) {
                    lv = findViewById(R.id.lstView);
                    CustomAdapter adapter=new CustomAdapter(this,myList);

                    lv.setAdapter(adapter);
                }

        }

        void Toast(String message, Exception ex) {
            if (ex != null)

            Toast.makeText(openparah.this, message, Toast.LENGTH_LONG).show();

        }













}