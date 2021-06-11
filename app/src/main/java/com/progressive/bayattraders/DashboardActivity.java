package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.progressive.bayattraders.adapters.DashboardTransAdapter;
import com.progressive.bayattraders.fragments.ProfileFragment;
import com.progressive.bayattraders.fragments.SendFragment;
import com.progressive.bayattraders.fragments.TransactionFragment;
import com.progressive.bayattraders.fragments.UserFragment;
import com.progressive.bayattraders.models.DashboardTransactionDetails;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    ImageView home, trans, send, profile, dots;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().hide();

        trans = findViewById(R.id.img_trans);
        trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new TransactionFragment(), null).commit();
            }
        });

        home = findViewById(R.id.img_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserFragment(), null).commit();
            }
        });

        send = findViewById(R.id.img_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new SendFragment(), null).commit();
            }
        });

        profile = findViewById(R.id.img_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new ProfileFragment(), null).commit();
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