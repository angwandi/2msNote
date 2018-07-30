package com.example.demad.a2msnote;

import android.content.Intent;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteActivity extends AppCompatActivity {
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.bottomBar)
    BottomAppBar bottomAppBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);
        ButterKnife.bind(this);
        bottomAppBar.replaceMenu(R.menu.main);
/*
        setSupportActionBar(bottomAppBar);
*/
        toolbar.setTitle("All Notes");
        floatingActionButton.setRippleColor(getResources().getColor(R.color.colorSecondaryLight));
        floatingActionButton.setCompatPressedTranslationZ(12);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
