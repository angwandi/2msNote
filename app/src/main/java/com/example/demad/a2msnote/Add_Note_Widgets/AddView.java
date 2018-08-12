package com.example.demad.a2msnote.Add_Note_Widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.demad.a2msnote.R;

public class AddView extends FrameLayout {
    public AddView(@NonNull Context context) {
        super(context);
    }

    public AddView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AddView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AddView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.adnt_add_view, this, true);
    }
}
