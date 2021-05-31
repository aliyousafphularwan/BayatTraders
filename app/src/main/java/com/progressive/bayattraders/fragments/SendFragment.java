package com.progressive.bayattraders.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.progressive.bayattraders.R;

public class SendFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner country;
    ImageView flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_send, container, false);

        country = v.findViewById(R.id.rcv_country);
        country.setOnItemSelectedListener(this);

        flag = v.findViewById(R.id.country_flag);
        flag.setVisibility(View.INVISIBLE);
        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position){

            case 0:
                flag.setImageResource(R.drawable.afghan);
                flag.setVisibility(View.VISIBLE);
                break;

            case 1:
                flag.setImageResource(R.drawable.pakistan);
                flag.setVisibility(View.VISIBLE);
                break;

            case 2:
                flag.setImageResource(R.drawable.uae);
                flag.setVisibility(View.VISIBLE);
                break;

            case 3:
                flag.setImageResource(R.drawable.eu);
                flag.setVisibility(View.VISIBLE);
                break;

            case 4:
                flag.setImageResource(R.drawable.india);
                flag.setVisibility(View.VISIBLE);
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        flag.setImageResource(R.drawable.afghan);
        flag.setVisibility(View.VISIBLE);
    }
}