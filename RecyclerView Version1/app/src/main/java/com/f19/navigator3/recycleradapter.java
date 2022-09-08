package com.f19.navigator3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recycleradapter extends RecyclerView.Adapter<recycleradapter.viewHolder> {
    private Context context;
    private String mylist[];
   private OnNoteListener monotelistener;
    public recycleradapter(Context context, String mylist[],OnNoteListener onNoteListener) {
        this.context = context;
        this.mylist = mylist;
        this.monotelistener=onNoteListener;
    }
//where to get single card  as viewholder object
    @NonNull
    @Override
    public recycleradapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlist,parent,false);
        return new viewHolder(view,monotelistener);
    }
//what will happen after creating the view holder object
    @Override
    public void onBindViewHolder(@NonNull recycleradapter.viewHolder holder, int position) {
     holder.setData(mylist[position]);
    }
//how many items
    @Override
    public int getItemCount() {
        return mylist.length;
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
public TextView paraname;
OnNoteListener onNoteListener;
        public viewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            paraname=itemView.findViewById(R.id.txt);
            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }
        public void setData(String text){
            paraname.setText(text);
        }


        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

}
