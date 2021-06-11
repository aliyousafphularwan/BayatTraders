package com.progressive.bayattraders.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.progressive.bayattraders.LoginActivity;
import com.progressive.bayattraders.R;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    Button btnLogout;
    SharedPreferences pref;

    private static final String PREF_NAME = "mypref";
    private static final String KEY_ID = "uid";

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        pref = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String uid = pref.getString(KEY_ID, null);
        Toast.makeText(getActivity(), "user id: " + uid, Toast.LENGTH_SHORT).show();

        btnLogout = v.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                getActivity().finish();

            }
        });

        return v;
    }
}