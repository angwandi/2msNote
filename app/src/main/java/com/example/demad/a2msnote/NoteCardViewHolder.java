package com.example.demad.a2msnote;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NoteCardViewHolder extends RecyclerView.ViewHolder {
    public ImageView noteImage;
    public TextView noteTitle;
    public TextView noteDescription;

    public NoteCardViewHolder(@NonNull View itemView) {
        super(itemView);
        //TOD: Find and store views from itemView
        noteImage = itemView.findViewById(R.id.note_image);
        noteTitle = itemView.findViewById(R.id.note_title);
        noteDescription = itemView.findViewById(R.id.note_description);
    }
}
