package com.example.demad.a2msnote.ActivityFragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demad.a2msnote.NoteCardRecyclerViewAdapter;
import com.example.demad.a2msnote.NoteGridItemDecoration;
import com.example.demad.a2msnote.R;
import com.example.demad.a2msnote.data.NoteEntry;
import com.example.demad.a2msnote.ui.AllNoteNavDrawerFragment;

/**
 * All Notes {@link Fragment} subclass.
 */
public class AllNoteFragment extends Fragment {
    Menu menu1;
    FloatingActionButton floatingActionButton;
    BottomAppBar bottomAppBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("CommitTransaction")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nt_all_note_fragment, container, false);
        //Set up bottom app bar
        bottomAppBar = view.findViewById(R.id._nt_add_bottom_app_bar);
        setUpBar(view);
        //set up fab
        floatingActionButton = view.findViewById(R.id.nt_add_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                swapFragment();
            }
        });
        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.nt_add_note_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        NoteCardRecyclerViewAdapter adapter;
        adapter = new NoteCardRecyclerViewAdapter(NoteEntry.initNoteEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        //recyclerView Decoration should go here
        //Shrine code lab 102
        int largePadding = getResources().getDimensionPixelOffset(R.dimen.nt_note_grid_spacing);
        int smallPadding = getResources().getDimensionPixelOffset(R.dimen.nt_note_grid_spacing_small);
        recyclerView.addItemDecoration(new NoteGridItemDecoration(largePadding, smallPadding));
        return view;
    }

    private void setUpBar(View view) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(bottomAppBar);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.nt_bar_all_note_menu, menu);
        this.menu1 = menu;
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    /*Bottom app bar item menu behaviors*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bar_all_note_menu:
                AllNoteNavDrawerFragment allNoteNavDrawerFragment = new AllNoteNavDrawerFragment();
                assert getFragmentManager() != null;
                allNoteNavDrawerFragment.show(getFragmentManager(), allNoteNavDrawerFragment.getTag());
                return true;
            case R.id.bar_all_note_search:
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            default:
        }
        return true;
    }

    /*Change Staggered view to list View
     * Work on screen orientation changes todo
     * */
    @Override
    public void onPrepareOptionsMenu(final Menu menu) {
        final MenuItem list = menu.findItem(R.id.bar_all_note_list_view);
        final MenuItem stag = menu.findItem(R.id.bar_all_note_staggered_view);
        /*menu.findItem(R.id.bar_all_note_list_view).setVisible(false);
        menu.findItem(R.id.bar_all_note_staggered_view).setVisible(true);*/
        menu.findItem(R.id.bar_all_note_list_view).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.setVisible(false);
                stag.setVisible(isVisible());
                Toast.makeText(getContext(), "Staggered", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        menu.findItem(R.id.bar_all_note_staggered_view).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.setVisible(isVisible());
                stag.setVisible(false);
                Toast.makeText(getContext(), "List", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        super.onPrepareOptionsMenu(menu);
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
}




