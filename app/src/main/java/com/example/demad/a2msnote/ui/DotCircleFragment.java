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
import android.widget.Toast;

import com.example.demad.a2msnote.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotCircleFragment extends BottomSheetDialogFragment {
    NavigationView dot_circle_navigationView;

    public DotCircleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nt_add_dot_circle_fragment, container, false);
        dot_circle_navigationView = view.findViewById(R.id.nt_add_dots_circle_navigation_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dot_circle_navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_dots_circle_Delete:
                        Toast.makeText(dot_circle_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        DotCircleFragment.this.dismiss();
                        break;
                    case R.id.nav_dots_circle_MakeCopy:
                        Toast.makeText(dot_circle_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        DotCircleFragment.this.dismiss();
                        break;
                    case R.id.nav_dots_circle_Send:
                        Toast.makeText(dot_circle_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        DotCircleFragment.this.dismiss();
                        break;
                    case R.id.nav_dots_circle_Collaborate:
                        Toast.makeText(dot_circle_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        DotCircleFragment.this.dismiss();
                        break;
                    case R.id.nav_dots_circle_Labels:
                        Toast.makeText(dot_circle_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        DotCircleFragment.this.dismiss();
                        break;
                    case R.id.nav_dots_circle_colors:
                        Toast.makeText(dot_circle_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        DotCircleFragment.this.dismiss();
                        break;
                }
                return true;
            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                FrameLayout frameLayout = dialog.findViewById(R.id.design_bottom_sheet);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
                bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        disableNavigationViewScrollbars(dot_circle_navigationView);
                    }

                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        switch (newState) {
                            case 5:
                                DotCircleFragment.this.dismiss();
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
