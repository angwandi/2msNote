package com.example.demad.a2msnote.ActivityFragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demad.a2msnote.NoteCardRecyclerViewAdapter;
import com.example.demad.a2msnote.NoteGridItemDecoration;
import com.example.demad.a2msnote.R;
import com.example.demad.a2msnote.data.AppDatabase;
import com.example.demad.a2msnote.data.NoteEntry;
import com.example.demad.a2msnote.ui.AllNoteNavDrawerFragment;

import java.util.Objects;

import static android.support.design.bottomappbar.BottomAppBar.FAB_ALIGNMENT_MODE_CENTER;
/**
 * All Notes {@link Fragment} subclass.
 */
public class AllNoteFragment extends Fragment implements NoteCardRecyclerViewAdapter.ItemClickListener {
    Menu menu1;
    FloatingActionButton floatingActionButton;
    BottomAppBar bottomAppBar;
    private NoteCardRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    // COMPLETED (1) Create AppDatabase member variable for the Database
    private AppDatabase mDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CommitTransaction")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.all_note_fragment, container, false);
        mDb = AppDatabase.getsInstance(getContext());
        //Set up bottom app bar
        setUpBar(view);
        //set up fab
        floatingActionButton = view.findViewById(R.id.all_note_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                swapFragment();
            }
        });
/*
        Initialize member variable for the data base
*/
        mDb = AppDatabase.getsInstance(getContext());
        // Set up the RecyclerView
        recyclerView = view.findViewById(R.id.nt_add_note_recycler_view);
        recyclerView.setHasFixedSize(true);
        //Portrait and Landscape : this might need to be moved later after revisiting fragment life cycle, works fine for now
        StaggeredGridLayoutManager gridLayoutPortrait = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager gridLayoutLandscape = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutPortrait.setReverseLayout(false);
        if (Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(gridLayoutPortrait);
        } else {
            recyclerView.setLayoutManager(gridLayoutLandscape);
        }
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, true));
        adapter = new NoteCardRecyclerViewAdapter(getContext(), this, NoteEntry.initNoteEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        //recyclerView Decoration should go here
        int largePadding = getResources().getDimensionPixelOffset(R.dimen.nt_note_grid_spacing);
        int smallPadding = getResources().getDimensionPixelOffset(R.dimen.nt_note_grid_spacing_small);
        recyclerView.addItemDecoration(new NoteGridItemDecoration(largePadding, smallPadding));
          /*
         Add a touch helper to the RecyclerView to recognize when a user swipes to delete an item.
         An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
         and uses callbacks to signal when a user is performing these actions.
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
            }
        }).attachToRecyclerView(recyclerView);
        return view;
    }

    private void setUpBar(View view) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            bottomAppBar = view.findViewById(R.id.all_note_bottom_app_bar);
            activity.setSupportActionBar(bottomAppBar);
            bottomAppBar.setFabAlignmentMode(FAB_ALIGNMENT_MODE_CENTER);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.bar_all_note_menu, menu);
        this.menu1 = menu;
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    /*Bottom app bar item menu behaviors*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AllNoteNavDrawerFragment allNoteNavDrawerFragment = new AllNoteNavDrawerFragment();
                assert getFragmentManager() != null;
                allNoteNavDrawerFragment.show(getFragmentManager(), allNoteNavDrawerFragment.getTag());
                return true;
            case R.id.bar_all_note_search:
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*Change Staggered view to list View
     * Work on screen orientation changes todo
     * */
    @Override
    public void onPrepareOptionsMenu(final Menu menu) {
        final MenuItem list = menu.findItem(R.id.bar_all_note_list_view);
        final MenuItem stag = menu.findItem(R.id.bar_all_note_staggered_view);
        menu.findItem(R.id.bar_all_note_list_view).setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                list.setVisible(false);
                stag.setVisible(isVisible());
                return true;
            }
        });
        menu.findItem(R.id.bar_all_note_staggered_view).setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                StaggeredGridLayoutManager gridLayoutPortrait = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                StaggeredGridLayoutManager gridLayoutLandscape = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                if (Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    recyclerView.setLayoutManager(gridLayoutPortrait);
                } else {
                    recyclerView.setLayoutManager(gridLayoutLandscape);
                }
                list.setVisible(isVisible());
                stag.setVisible(false);
                return true;
            }
        });
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Call the adapter's setNotes method using the result
        // of the loadAllTasks method from the taskDao
        adapter.setNotes(mDb.noteDao().loadAllNotes());
    }

    /*Swap to Add Note Fragment*/
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void swapFragment() {
        Fragment fragment;
        fragment = AddNoteFragment.newInstance();
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onItemClickListener(int itemId) {
    }
}




