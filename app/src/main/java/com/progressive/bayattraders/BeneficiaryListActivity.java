package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.progressive.bayattraders.adapters.DashboardTransAdapter;
import com.progressive.bayattraders.models.DashboardTransactionDetails;

import java.util.ArrayList;

public class BeneficiaryListActivity extends AppCompatActivity {

    RecyclerView rcv_beneif_list;
    ArrayList<DashboardTransactionDetails> list;
    DashboardTransAdapter dashboardTransAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Beneficiary List");

        rcv_beneif_list = findViewById(R.id.rcv_beneif_list);
        list = new ArrayList<>();
        dashboardTransAdapter = new DashboardTransAdapter(list);
        rcv_beneif_list.setLayoutManager(new LinearLayoutManager(this));
        rcv_beneif_list.setAdapter(dashboardTransAdapter);

        get_List();


    }

    private void get_List() {

        list.add(new DashboardTransactionDetails("Ali Yousaf", "350", "FX74145247856524", "02/03/2021"));

    }
}