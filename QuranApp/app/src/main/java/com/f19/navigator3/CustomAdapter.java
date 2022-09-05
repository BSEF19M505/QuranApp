package com.f19.navigator3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.lang.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    Context context;
    ArrayList<Model> sm;

    public CustomAdapter(Context context, ArrayList<Model> list){
        this.context=context;
        sm=list;

    }




    @Override
    public int getCount() {
        return this.sm.size();
    }
    public Object getItem(int position){
        return sm.get(position);
    }



    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int i, @Nullable View v, @NonNull ViewGroup parent) {



        if (v == null) {

            // LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            //v=inflater.inflate(R.layout.list_items,null);
            v=LayoutInflater.from(context).inflate(R.layout.list_items,parent,false);

        }

        TextView aya =(TextView) v.findViewById(R.id.txtara);
        TextView  urdu =(TextView) v.findViewById(R.id.txtur);
        TextView  eng =(TextView) v.findViewById(R.id.txteng);

        Model ayat=sm.get(i);

        aya.setText(ayat.getArabic());
        urdu.setText(ayat.getUrdu());
eng.setText(ayat.getEnglish());

        return v;
    }
}

