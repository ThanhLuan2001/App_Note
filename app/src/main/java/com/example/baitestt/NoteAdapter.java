package com.example.baitestt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    List<Note> noteList;
    public void setData(List<Note> list){
        this.noteList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note note = noteList.get(position);

        if(note==null){
            return;
        }else {
            holder.tv_id.setText("id: "+note.getId()+"");
            holder.tv_name.setText("tên: "+note.getName());
            holder.tv_date.setText("thời gian: "+note.getDate());
            holder.tv_note.setText("ghi chú: "+note.getNote());
            holder.itemView.setBackgroundColor(note.getColor());

        }


    }

    @Override
    public int getItemCount() {
        if (noteList!=null){
            return noteList.size();
        }

        return 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id,tv_name,tv_note,tv_date;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_note = itemView.findViewById(R.id.tv_note);
            tv_date = itemView.findViewById(R.id.tv_date);

        }
    }

}
