package com.example.demad.a2msnote.ui;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demad.a2msnote.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class PriorityNavDrawerFragment extends BottomSheetDialogFragment {
    NavigationView navigationView;
    ImageView imageView;
    // Constants for priority
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_priority_nav_drawer_fragment, container, false);
        navigationView = view.findViewById(R.id.all_note_main_navigation_view);
        imageView = view.findViewById(R.id.close_imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*Handle Priority menu item click
         * to be moved later todo
         * */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            int priority = 1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_low:
                        priority = PRIORITY_LOW;
                        Toast.makeText(navigationView.getContext(), "Low priority selected", Toast.LENGTH_SHORT).show();
                        PriorityNavDrawerFragment.this.dismiss();
                        return true;
                    case R.id.nav_medium:
                        priority = PRIORITY_MEDIUM;
                        Toast.makeText(navigationView.getContext(), "Medium priority selected", Toast.LENGTH_SHORT).show();
                        PriorityNavDrawerFragment.this.dismiss();
                        return true;
                    case R.id.nav_high:
                        priority = PRIORITY_HIGH;
                        Toast.makeText(navigationView.getContext(), "High priority selected", Toast.LENGTH_SHORT).show();
                        PriorityNavDrawerFragment.this.dismiss();
                        return true;
                    default:
                }
                return true;
            }
        });
        /*Close Priority NavigationDrawer Fragment by clicking on the close close_image*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PriorityNavDrawerFragment.this.dismiss();
            }
        });
        /*Remove the horrible scroll bar*/
        this.disableNavigationViewScrollbars(navigationView);
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
                        if ((double) slideOffset > 0.5D) {
                            imageView.setVisibility(View.VISIBLE);
                        } else {
                            imageView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        switch (newState) {
                            case 5:
                                PriorityNavDrawerFragment.this.dismiss();
                            default:
                        }
                    }
                });
            }
        });
        return dialog;
    }


    /*Helper Method to hide the horrible scroll bar*/
    public final void disableNavigationViewScrollbars(NavigationView navigationView) {
        View view = navigationView != null ? navigationView.getChildAt(0) : null;
        NavigationMenuView navigationMenuView = (NavigationMenuView) view;
        assert navigationMenuView != null;
        navigationMenuView.setVerticalScrollBarEnabled(false);
    }
}
