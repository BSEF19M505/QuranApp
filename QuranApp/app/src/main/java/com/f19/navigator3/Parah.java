package com.f19.navigator3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Parah extends AppCompatActivity {
    ListView list;
    QDH h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parah);
        list=findViewById(R.id.list1);
        h=new QDH();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,h.ParahName);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),openparah.class);
                intent.putExtra("id",i+1);

                //
              //  intent.putExtra("start",h.getParahStart(i));
              // intent.putExtra("end",h.getParahStart(i+1));
                startActivity(intent);



            }
        });
    }
}

