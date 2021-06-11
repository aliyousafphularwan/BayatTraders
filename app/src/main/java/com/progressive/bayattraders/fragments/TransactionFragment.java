package com.progressive.bayattraders.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.progressive.bayattraders.R;
import com.progressive.bayattraders.adapters.DashboardTransAdapter;
import com.progressive.bayattraders.models.DashboardTransactionDetails;

import java.util.ArrayList;

public class TransactionFragment extends Fragment {

    ArrayList<DashboardTransactionDetails> list;
    DashboardTransAdapter dashboardTransAdapter;
    RecyclerView rv_trans;

    public TransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_transaction, container, false);

        rv_trans = v.findViewById(R.id.frag_trans);
        list = new ArrayList<>();
        dashboardTransAdapter = new DashboardTransAdapter(list);
        rv_trans.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_trans.setAdapter(dashboardTransAdapter);


        return v;
    }


}