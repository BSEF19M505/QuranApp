package com.f19.navigator3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Parah extends AppCompatActivity implements recycleradapter.OnNoteListener{
    private RecyclerView recyclerview;
    private recycleradapter adapter;
    ListView list;
    QDH h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parah);

        h=new QDH();
        //recyclerview initialization
        recyclerview=findViewById(R.id.myrecyclerview);
        adapter=new recycleradapter(getApplicationContext(),h.ParahName,this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

recyclerview.setAdapter(adapter);
    /*    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),openparah.class);
                intent.putExtra("id",i+1);

                //
              //  intent.putExtra("start",h.getParahStart(i));
              // intent.putExtra("end",h.getParahStart(i+1));
                startActivity(intent);



            }
        });*/
    }

    @Override
    public void onNoteClick(int position) {
        //int i=h.ParahName.get(position);
        Intent intent=new Intent(getApplicationContext(),openparah.class);
        intent.putExtra("id",position+1);
        // intent.putExtra("start",h.getSurahStart(i));
        // intent.putExtra("end",h.getSurahStart(i+1));
        startActivity(intent);
    }
}

