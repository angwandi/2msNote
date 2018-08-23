package com.example.demad.a2msnote.ui;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.demad.a2msnote.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllNoteNavDrawerFragment extends BottomSheetDialogFragment {
    NavigationView navigationView;

    public AllNoteNavDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.all_note_nav_drawer_fragment, container, false);
        navigationView = view.findViewById(R.id.all_note_main_navigation_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*Handle Priority menu item click
         * to be moved later todo
         * */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_all_note_Reminders:
                        Toast.makeText(navigationView.getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                        AllNoteNavDrawerFragment.this.dismiss();
                        return true;
                    case R.id.group_nav_all_note_labels:
                        Toast.makeText(navigationView.getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                        AllNoteNavDrawerFragment.this.dismiss();
                        return true;
                    case R.id.group_nav_all_note_deletes:
                        Toast.makeText(navigationView.getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                        AllNoteNavDrawerFragment.this.dismiss();
                        return true;
                    case R.id.nav_all_note_Settings:
                        Toast.makeText(navigationView.getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                        AllNoteNavDrawerFragment.this.dismiss();
                        return true;
                    case R.id.nav_all_note_Help:
                        Toast.makeText(navigationView.getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                        AllNoteNavDrawerFragment.this.dismiss();
                    default:
                }
                return true;
            }
        });

        /*Remove the horrible scroll bar*/
        PriorityNavDrawerFragment priorityNavDrawerFragment = new PriorityNavDrawerFragment();
        priorityNavDrawerFragment.disableNavigationViewScrollbars(navigationView);
    }

    /*Control the behavior of the Bottom sheet*/
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                FrameLayout frameLayout = dialog.findViewById(R.id.design_bottom_sheet);
                assert frameLayout != null;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
                bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    }

                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        switch (newState) {
                            case 5:
                                AllNoteNavDrawerFragment.this.dismiss();
                            default:
                        }
                    }
                });
            }
        });
        return dialog;
    }
}
