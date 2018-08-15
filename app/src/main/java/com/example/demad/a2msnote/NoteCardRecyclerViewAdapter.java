package com.example.demad.a2msnote;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demad.a2msnote.data.NoteEntry;

import java.util.List;

/**
 * Adapter used to show a simple grid of notes.
 */
public class NoteCardRecyclerViewAdapter extends RecyclerView.Adapter<NoteCardViewHolder> {
    private List<NoteEntry> noteEntries;

    public NoteCardRecyclerViewAdapter(List<NoteEntry> noteEntries) {
        this.noteEntries = noteEntries;
    }

    @NonNull
    @Override
    public NoteCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View layoutView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.nt_main, parent, false);
        return new NoteCardViewHolder(layoutView);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     */
    @Override
    public void onBindViewHolder(@NonNull NoteCardViewHolder holder, int position) {
        if (noteEntries != null && position < noteEntries.size()) {
            // Determine the values of the wanted data
            NoteEntry note = noteEntries.get(position);
            holder.noteTitle.setText(note.getTitle());
            holder.noteDescription.setText(note.getDescription());
            holder.notePriority.setText(note.getPriority());
            holder.noteDateEdited.setText((CharSequence) note.getUpdatedAt());
        }
    }

    @Override
    public int getItemCount() {
        return 50;
    }
}
