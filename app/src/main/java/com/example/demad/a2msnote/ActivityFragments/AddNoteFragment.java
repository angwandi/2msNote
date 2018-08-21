package com.example.demad.a2msnote.ActivityFragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.demad.a2msnote.R;
import com.example.demad.a2msnote.ui.DotCircleFragment;
import com.example.demad.a2msnote.ui.PlusBoxFragment;
import com.example.demad.a2msnote.ui.PriorityNavDrawerFragment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AddNoteFragment extends android.support.v4.app.Fragment {
    EditText title;
    EditText description;
    BottomAppBar add_bottomAppBar;
    FloatingActionButton add_fab;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nt_add_note_fragment, container, false);
        add_bottomAppBar = view.findViewById(R.id._nt_add_bottom_app_bar);
        add_fab = view.findViewById(R.id.nt_add_fab);
        description = view.findViewById(R.id.list_item_edit_text);
        description.requestFocus();
        setUpBar(view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpBar(View view) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(add_bottomAppBar);
            add_bottomAppBar.replaceMenu(R.menu.nt_bar_add_note_menu);
            int fab_alignment = BottomAppBar.FAB_ALIGNMENT_MODE_END;
            add_bottomAppBar.setFabAlignmentMode(fab_alignment);
            add_fab.setImageDrawable(Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_save));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.nt_bar_add_note_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    /*Bottom app bar item menu behaviors*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bar_priority:
                PriorityNavDrawerFragment priorityNavD = new PriorityNavDrawerFragment();
                assert getFragmentManager() != null;
                priorityNavD.show(getFragmentManager(), priorityNavD.getTag());
                break;
            case R.id.bar_plus_box:
                PlusBoxFragment plusBoxNavD = new PlusBoxFragment();
                assert getFragmentManager() != null;
                plusBoxNavD.show(getFragmentManager(), plusBoxNavD.getTag());
                break;
            case R.id.bar_dots:
                DotCircleFragment dotCircleNavD = new DotCircleFragment();
                assert getFragmentManager() != null;
                dotCircleNavD.show(getFragmentManager(), dotCircleNavD.getTag());
                break;
            case R.id.bar_back:
                assert getFragmentManager() != null;
                getFragmentManager().getBackStackEntryCount();
                getFragmentManager().popBackStack();
                break;
        }
        return true;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }
}
