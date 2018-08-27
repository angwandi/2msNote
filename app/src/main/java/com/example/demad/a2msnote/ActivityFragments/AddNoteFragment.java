package com.example.demad.a2msnote.ActivityFragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private EditText title;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_note_fragment, container, false);
        EditText description = view.findViewById(R.id.list_item_edit_text);
        description.requestFocus();
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
                Back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    /*Implement Back Navigation*/
    public void Back() {
        assert getFragmentManager() != null;
        getFragmentManager().getBackStackEntryCount();
        getFragmentManager().popBackStack();
    }
}
