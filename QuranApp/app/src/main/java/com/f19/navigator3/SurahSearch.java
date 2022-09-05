package com.f19.navigator3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SurahSearch extends AppCompatActivity {
    Button s;
    EditText sura;
    DBHelper controller;
    ListView lv;
    int myVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_search);
        sura=findViewById(R.id.surahno);
        s=findViewById(R.id.search);

        String  str=sura.getText().toString();
        try{
            myVal=Integer.valueOf(str);
        }
        catch (NumberFormatException n){
            myVal=0;
        }


        controller  = new DBHelper(this,myVal);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), opensurah.class);
                intent.putExtra("id", myVal);

                startActivity(intent);

            }
        });
    }
}


