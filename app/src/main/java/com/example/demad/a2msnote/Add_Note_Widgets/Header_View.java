package com.example.demad.a2msnote.Add_Note_Widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.demad.a2msnote.R;

public class Header_View extends FrameLayout {
    public Header_View(@NonNull Context context) {
        super(context);
    }

    public Header_View(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Header_View(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Header_View(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.adnt_header_view, this, true);
    }
}
