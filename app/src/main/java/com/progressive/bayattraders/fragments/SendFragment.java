package com.progressive.bayattraders.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.progressive.bayattraders.R;

public class SendFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner country;
    ImageView flag;
    TextView ttlsndngamnt;
    EditText send_amount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_send, container, false);

        ttlsndngamnt = v.findViewById(R.id.ttlsndngamnt);

        country = v.findViewById(R.id.rcv_country);
        country.setOnItemSelectedListener(this);

        flag = v.findViewById(R.id.country_flag);
        flag.setVisibility(View.INVISIBLE);

        send_amount = v.findViewById(R.id.send_amount);
        send_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ttlsndngamnt.setText(send_amount.getText().toString() + " GBP");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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