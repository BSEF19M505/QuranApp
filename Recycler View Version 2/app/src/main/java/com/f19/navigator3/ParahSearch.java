package com.f19.navigator3;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ParahSearch extends AppCompatActivity {


    public EditText ayatno;
    public EditText paraid;
    public Button search;
    public TextView tv;
    DBHelper controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parah_search);
        paraid = findViewById(R.id.parano);

        search = (Button)findViewById(R.id.search);


        controller  = new DBHelper(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pno = paraid.getText().toString();
                Intent intent = new Intent(getApplicationContext(), openparah.class);
                intent.putExtra("id",Integer.valueOf(pno) );

                startActivity(intent);


            }
        });

    }
}








