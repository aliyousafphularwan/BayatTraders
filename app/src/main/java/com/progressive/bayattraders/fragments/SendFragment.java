package com.progressive.bayattraders.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.progressive.bayattraders.R;
import com.progressive.bayattraders.helpers.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SendFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner country;
    TextView ttlsndngamnt, ex_rate;
    EditText send_amount, rcv_amount;
    double amnt;
    double res;
    double ext_rate;
    String company, customer;
    RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_send, container, false);

        ttlsndngamnt = v.findViewById(R.id.ttlsndngamnt);
        ex_rate = v.findViewById(R.id.ex_rate);
        rcv_amount = v.findViewById(R.id.rcv_amount);

        country = v.findViewById(R.id.rcv_country);
        country.setOnItemSelectedListener(this);

        send_amount = v.findViewById(R.id.send_amount);
        send_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(send_amount.getText().toString().equals("")){
                    send_amount.setText("0");
                }

                amnt = Double.parseDouble(send_amount.getText().toString());
                res = amnt * Double.parseDouble(customer);

                rcv_amount.setText(String.valueOf(res));

                ttlsndngamnt.setText(send_amount.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        get_ex_rate();

        return v;
    }

    private void get_ex_rate() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.ex_rate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    String status = jo.getString("status");
                    if (status.equals("1")){

                        JSONArray msg = jo.getJSONArray("message");
                        for(int i = 0; i < msg.length(); i++){
                            JSONObject data = msg.getJSONObject(i);
                            String comp = data.optString("company");
                            String cust = data.optString("cust_rate");
                            company = comp;
                            customer = cust;

                            List<String> list = new ArrayList<>();
                            list.add(comp);

                            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            country.setAdapter(adapter);
                        }

                    }else{
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case 0:
                ex_rate.setText(String.valueOf(customer));
                break;
            case 1:
                ex_rate.setText(String.valueOf(customer));
                break;
            case 2:
                ex_rate.setText(String.valueOf(customer));
                break;
            case 3:
                ex_rate.setText(String.valueOf(customer));
                break;
            case 4:
                ex_rate.setText(String.valueOf(customer));
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        ex_rate.setText(String.valueOf(customer));
    }
}