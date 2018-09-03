package com.example.demad.a2msnote.ActivityFragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.demad.a2msnote.R;
import com.example.demad.a2msnote.data.AppDatabase;
import com.example.demad.a2msnote.data.NoteEntry;
import com.example.demad.a2msnote.ui.DotCircleFragment;
import com.example.demad.a2msnote.ui.PlusBoxFragment;
import com.example.demad.a2msnote.ui.PriorityNavDrawerFragment;

import java.util.Date;
import java.util.Objects;
/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AddNoteFragment extends android.support.v4.app.Fragment {
    // Extra for the Note ID to be received in the intent
    public static final String EXTRA_NOTE_ID = "extraNoteId";
    // Extra for the Note ID to be received after rotation
    public static final String INSTANCE_NOTE_ID = "instanceNoteId";
    // Constants for priority
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;
    // Constant for default note id to be used when not in update mode
    private static final int DEFAULT_NOTE_ID = -1;
    // Constant for logging
    private static final String TAG = AddNoteFragment.class.getSimpleName();
    // Fields for views
    private int mNoteId = DEFAULT_NOTE_ID;
    /*Member variable for the Database*/
    private AppDatabase mDB;
    private EditText title;
    private EditText description;
    FloatingActionButton fab;

    public static android.support.v4.app.Fragment newInstance() {
        return new AddNoteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_note_fragment, container, false);
        description = view.findViewById(R.id.description_edit_text);
        title = view.findViewById(R.id.title_edit_text);
        //Initialize member variable for the data base
        mDB = AppDatabase.getsInstance(getContext());
        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_NOTE_ID)) {
            mNoteId = savedInstanceState.getInt(INSTANCE_NOTE_ID, DEFAULT_NOTE_ID);
        }
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(EXTRA_NOTE_ID)) {
            fab.setBackground(getActivity().getDrawable(R.drawable.ic_save));
            if (mNoteId == DEFAULT_NOTE_ID) {
                // populate the UI
            }
        }
        description.requestFocus();
        fab = view.findViewById(R.id.add_note_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClick();
                AddNoteFragment.this.Back();
            }
        });
        setUpBottomAppBar(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setUpToolbar(view);
        }
        return view;
    }

    /*Toolbar Method handler*/
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_toolbar);
        toolbar.setTitle("");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            Objects.requireNonNull(activity.getSupportActionBar()).setElevation(0);
            android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /*Bottom App bar Method Handler*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpBottomAppBar(View view) {
        BottomAppBar add_bottomAppBar = view.findViewById(R.id.add_note_bottom_app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            add_bottomAppBar.replaceMenu(R.menu.bar_add_note_menu);
            activity.setSupportActionBar(add_bottomAppBar);
            add_bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.bar_priority:
                            PriorityNavDrawerFragment priorityNavD = new PriorityNavDrawerFragment();
                            assert getFragmentManager() != null;
                            priorityNavD.show(getFragmentManager(), priorityNavD.getTag());
                            return true;
                        case R.id.bar_plus_box:
                            PlusBoxFragment plusBoxNavD = new PlusBoxFragment();
                            assert getFragmentManager() != null;
                            plusBoxNavD.show(getFragmentManager(), plusBoxNavD.getTag());
                            return true;
                        case R.id.bar_dots:
                            DotCircleFragment dotCircleNavD = new DotCircleFragment();
                            assert getFragmentManager() != null;
                            dotCircleNavD.show(getFragmentManager(), dotCircleNavD.getTag());
                            return true;
                        default:
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.add_note_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    /*Bottom app bar item menu behaviors*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AddNoteFragment.this.Back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param noteEntry the NoteEntry to populate the UI
     */
    private void populateUI(NoteEntry noteEntry) {
    }

    /*onSaveButton is called when the save Button is clicked
     * it retrieves user input and insert the new data into the underlying database.
     */
    public void onSaveButtonClick() {
        String ntTitle = title.getText().toString().trim();
        String ntDescription = description.getText().toString().trim();
        Date date = new Date();
        PriorityNavDrawerFragment prio = new PriorityNavDrawerFragment();
        int priority = PRIORITY_HIGH;
        NoteEntry noteEntry = new NoteEntry(ntTitle, ntDescription, priority, date);
        mDB.noteDao().insertNote(noteEntry);
    }

    /*Implement Back Navigation*/
    public void Back() {
        assert getFragmentManager() != null;
        getFragmentManager().getBackStackEntryCount();
        getFragmentManager().popBackStack();
    }
}
