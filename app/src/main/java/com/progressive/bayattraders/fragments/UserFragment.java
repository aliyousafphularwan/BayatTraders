package com.progressive.bayattraders.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.progressive.bayattraders.AddBeneficiaryActivity;
import com.progressive.bayattraders.AddDocsActivity;
import com.progressive.bayattraders.R;
import com.progressive.bayattraders.TransactionListActivity;
import com.progressive.bayattraders.adapters.DashboardTransAdapter;
import com.progressive.bayattraders.models.DashboardTransactionDetails;

import java.util.ArrayList;

public class UserFragment extends Fragment {

    RecyclerView rv_trans;
    DashboardTransAdapter dashboardTransAdapter;
    LinearLayout trans_list, add_beneif, add_docs, resue_trans;
    private ArrayList<DashboardTransactionDetails> list;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        rv_trans = v.findViewById(R.id.trans_view);
        list = new ArrayList<>();
        rv_trans.setLayoutManager(new LinearLayoutManager(getContext()));
        dashboardTransAdapter = new DashboardTransAdapter(list);
        rv_trans.setAdapter(dashboardTransAdapter);

        add_beneif = v.findViewById(R.id.add_beneif);
        add_beneif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), AddBeneficiaryActivity.class));

            }
        });

        trans_list = v.findViewById(R.id.trans_list);
        trans_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TransactionListActivity.class));
            }
        });

        add_docs = v.findViewById(R.id.add_docs);
        add_docs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddDocsActivity.class));
            }
        });

        transInfo();

        return v;
    }

    private void transInfo() {

        list.add(new DashboardTransactionDetails("Ali Yousaf", "175.05 USD", "FX1928375", "04/02/2021"));
        list.add(new DashboardTransactionDetails("Khalid Noor", "120.0 USD", "FX8524561", "23/04/2021"));
        list.add(new DashboardTransactionDetails("Muhammad Numan", "75.9 USD", "FX1425967", "13/05/2021"));
        list.add(new DashboardTransactionDetails("Hassan Junaid", "100.0 USD", "FX7854126", "19/05/2021"));

    }
}