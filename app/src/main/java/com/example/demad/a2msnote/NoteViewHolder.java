package com.example.demad.a2msnote;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    public TextView noteTitle;
    public TextView noteDescription;
    public TextView noteDateEdited;
    public TextView notePriority;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        //TOD: Find and store views from itemView
        noteTitle = itemView.findViewById(R.id.note_title_tv);
        noteDescription = itemView.findViewById(R.id.note_description_tv);
        notePriority = itemView.findViewById(R.id.note_priority_tv);
        noteDateEdited = itemView.findViewById(R.id.note_date_tv);
    }
}
