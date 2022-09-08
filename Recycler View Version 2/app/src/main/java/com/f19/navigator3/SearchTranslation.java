package com.f19.navigator3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchTranslation extends AppCompatActivity {

    public EditText ayatno;
    public EditText surahid;
    public Button search;
    public TextView tv;
    DBHelper controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_translation);
            ayatno = findViewById(R.id.ayahno);
            surahid = findViewById(R.id.surahno);
            search = (Button)findViewById(R.id.search);
            tv = findViewById(R.id.result);

            controller  = new DBHelper(this);
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String ano = ayatno.getText().toString();
                    String id = surahid.getText().toString();
                    String result = controller.gettranslation(Integer.valueOf(ano), Integer.valueOf(id));

                    tv.setText(result);
                }
            });
        }
    }


