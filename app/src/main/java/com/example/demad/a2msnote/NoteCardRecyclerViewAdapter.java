package com.example.demad.a2msnote;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demad.a2msnote.data.NoteEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
/**
 * Adapter used to show a simple grid of notes.
 */
public class NoteCardRecyclerViewAdapter extends RecyclerView.Adapter<NoteCardRecyclerViewAdapter.NoteCardViewHolder> {
    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";
    // Member variable to handle item clicks
    final private ItemClickListener mItemClickListener;
    private List<NoteEntry> noteEntries;
    private Context mContext;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public NoteCardRecyclerViewAdapter(Context context, ItemClickListener mItemClickListener, List<NoteEntry> noteEntries) {
        this.mItemClickListener = mItemClickListener;
        this.noteEntries = noteEntries;
        mContext = context;
    }

    @NonNull
    @Override
    public NoteCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View layoutView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.main, parent, false);
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
            String titre = note.getTitle();
            String descr = note.getDescription();
            int prio = note.getPriority();
            String updateTime = dateFormat.format(note.getUpdatedAt());
            //Set values
            holder.noteTitle.setText(titre);
            holder.noteDescription.setText(descr);
            // Programmatically set the text and color for the priority TextView
            String priorityString = "" + prio;
            holder.notePriority.setText(priorityString);
            holder.noteDateEdited.setText(updateTime);
            GradientDrawable priorityCircle = (GradientDrawable) holder.notePriority.getBackground();
            int priorityColor = getPriorityColor(prio);
            // Get the appropriate background color based on the priority
            priorityCircle.setColor(priorityColor);
        }
    }

    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (noteEntries == null) {
            return 0;
        }
        return noteEntries.size();
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setNotes(List<NoteEntry> noteEntries1) {
        noteEntries = noteEntries1;
        notifyDataSetChanged();
    }

    /*
 Helper method for selecting the correct priority circle color.
 P1 = red, P2 = orange, P3 = yellow
 */
    private int getPriorityColor(int priority) {
        int priorityColor = 0;
        switch (priority) {
            case 1:
                priorityColor = ContextCompat.getColor(mContext, R.color.ColorSecondaryLight);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(mContext, R.color.ColorSecondary);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(mContext, R.color.ColorSecondaryDark);
                break;
            default:
                break;
        }
        return priorityColor;
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    // Inner class for creating ViewHolders
    public class NoteCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView noteTitle;
        TextView noteDescription;
        TextView noteDateEdited;
        TextView notePriority;

        public NoteCardViewHolder(@NonNull View itemView) {
            super(itemView);
            //TOD: Find and store views from itemView
            noteTitle = itemView.findViewById(R.id.note_title_tv);
            noteDescription = itemView.findViewById(R.id.note_description_tv);
            notePriority = itemView.findViewById(R.id.note_priority_tv);
            noteDateEdited = itemView.findViewById(R.id.note_date_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = noteEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}
