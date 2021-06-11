package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddBeneficiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beneficiary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Beneficiary");

    }
}