package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddDocsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_docs);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Documents");

    }
}