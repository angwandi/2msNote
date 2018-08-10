package com.example.demad.a2msnote;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demad.a2msnote.data.NoteEntry;

/**
 * Adapter used to show a simple grid of notes.
 */
public class NoteCardRecyclerViewAdapter extends RecyclerView.Adapter<NoteCardViewHolder> {
    private int numberNotes;

    public NoteCardRecyclerViewAdapter(int numberOfNotes) {
        this.numberNotes = numberOfNotes;
    }

    @NonNull
    @Override
    public NoteCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View layoutView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.nt_note_card, parent, false);
        return new NoteCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteCardViewHolder holder, int position) {
        if (numberNotes != 0 && position < numberNotes) {
            NoteEntry note = new NoteEntry("Title", "Description", 0);
            holder.noteTitle.setText(note.title);
            holder.noteDescription.setText(note.description);
        }
    }

    @Override
    public int getItemCount() {
        return numberNotes;
    }
}
