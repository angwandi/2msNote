package com.example.demad.a2msnote;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailScreenNoteActivity extends AppCompatActivity {
    @BindView(R.id.floatingActionButton3)
    FloatingActionButton floatingActionButton33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_screen_note_activity);
        ButterKnife.bind(this);
        floatingActionButton33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailScreenNoteActivity.this, EditNoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
