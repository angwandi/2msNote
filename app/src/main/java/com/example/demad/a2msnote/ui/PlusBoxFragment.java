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
public class PlusBoxFragment extends BottomSheetDialogFragment {
    NavigationView plus_box_navigationView;

    public PlusBoxFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_plus_box_fragment, container, false);
        plus_box_navigationView = view.findViewById(R.id.nt_add_plus_box_navigation_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        plus_box_navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_plus_box_TakePhoto:
                        Toast.makeText(plus_box_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        PlusBoxFragment.this.dismiss();
                        break;
                    case R.id.nav_plus_box_ChooseImage:
                        Toast.makeText(plus_box_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        PlusBoxFragment.this.dismiss();
                        break;
                    case R.id.nav_plus_box_Drawing:
                        Toast.makeText(plus_box_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        PlusBoxFragment.this.dismiss();
                        break;
                    case R.id.nav_plus_box_Recording:
                        Toast.makeText(plus_box_navigationView.getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        PlusBoxFragment.this.dismiss();
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
                        disableNavigationViewScrollbars(plus_box_navigationView);
                    }

                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        switch (newState) {
                            case 5:
                                PlusBoxFragment.this.dismiss();
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
