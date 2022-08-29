package com.f19.navigator3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Surah extends AppCompatActivity {

    ListView list;
    QDH h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);
        list=findViewById(R.id.list2);
        h=new QDH();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,h.urduSurahNames);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),opensurah.class);
                intent.putExtra("id",i);
                intent.putExtra("start",h.getSurahStart(i));
                intent.putExtra("end",h.getSurahStart(i+1));
                startActivity(intent);
            }
        });
    }
}