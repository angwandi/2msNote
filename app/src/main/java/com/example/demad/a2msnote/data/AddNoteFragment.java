package com.example.demad.a2msnote.data;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demad.a2msnote.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends android.support.v4.app.Fragment {
    public static android.support.v4.app.Fragment newInstance() {
        return new AddNoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_note_fragment1, container, false);
        return view;
    }
}
