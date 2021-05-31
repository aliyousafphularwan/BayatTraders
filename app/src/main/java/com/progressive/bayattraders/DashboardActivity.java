package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.progressive.bayattraders.adapters.DashboardTransAdapter;
import com.progressive.bayattraders.fragments.TransactionFragment;
import com.progressive.bayattraders.fragments.UserFragment;
import com.progressive.bayattraders.models.DashboardTransactionDetails;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    ImageView rates, transfer, user, settings, translist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().hide();

        translist = findViewById(R.id.img_translist);
        translist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new TransactionFragment(), null).commit();
            }
        });

        user = findViewById(R.id.img_user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserFragment(), null).commit();
            }
        });

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null){

            if (savedInstanceState != null){

                return;

            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UserFragment homeFragment = new UserFragment();
            fragmentTransaction.add(R.id.fragment_container, homeFragment, null);
            fragmentTransaction.commit();

        }

    }
}