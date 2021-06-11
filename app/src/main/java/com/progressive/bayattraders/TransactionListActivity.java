package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.progressive.bayattraders.adapters.DashboardTransAdapter;
import com.progressive.bayattraders.models.DashboardTransactionDetails;

import java.util.ArrayList;

public class TransactionListActivity extends AppCompatActivity {

    ArrayList<DashboardTransactionDetails> list;
    DashboardTransAdapter dashboardTransAdapter;
    RecyclerView rv_trans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Transactions List");

        rv_trans = findViewById(R.id.frag_trans);
        list = new ArrayList<>();
        dashboardTransAdapter = new DashboardTransAdapter(list);
        rv_trans.setLayoutManager(new LinearLayoutManager(this));
        rv_trans.setAdapter(dashboardTransAdapter);

        getList();

    }

    private void getList() {

        

    }
}