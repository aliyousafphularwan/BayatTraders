package com.progressive.bayattraders.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.progressive.bayattraders.helpers.ExchangeRates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SendFragment extends Fragment{

    Spinner country, rcvr;
    EditText send_amount;
    TextView rcv_amount, ex_rate, ttlsndngamnt;
    public String cid;
    RequestQueue queue;
    List<String> listBeneif;
    ArrayList<ExchangeRates> listComp;
    ArrayAdapter adapter;
    SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_send, container, false);

        send_amount = v.findViewById(R.id.send_amount);
        rcv_amount = v.findViewById(R.id.rcv_amount);
        ex_rate = v.findViewById(R.id.ex_rate);
        ttlsndngamnt = v.findViewById(R.id.ttlsndngamnt);

        send_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // rcv_amount.setText(send_amount.getText());
                ttlsndngamnt.setText(send_amount.getText());

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        // get value from login session
        pref = getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);
        cid = pref.getString("uid", null);
        listComp = new ArrayList<ExchangeRates>();
        listBeneif = new ArrayList<>();
        rcvr = v.findViewById(R.id.select_Beneficiary);
        country = v.findViewById(R.id.rcv_country);
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ExchangeRates exchangeRates = (ExchangeRates) parent.getItemAtPosition(position);
                ex_rate.setText(exchangeRates.rate);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Position" + parent, Toast.LENGTH_SHORT).show();
            }
        });
        get_ex_rate();
        get_benif_data();
        return v;
    }
    private void get_benif_data() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.beneficiary + cid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    String status = jo.getString("status");
                    if(status.equals("1")){

                        JSONArray ja = jo.getJSONArray("message");
                        for (int i = 0; i < ja.length(); i++){

                            JSONObject data = ja.getJSONObject(i);
                            String name = data.getString("pname");

                            if(name != null){
                                listBeneif.add(name);

                                ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listBeneif);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                rcvr.setAdapter(adapter);
                            }else{
                                List<String> list = new ArrayList<>();
                                list.add("no beneficiary found.");

                                ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                rcvr.setAdapter(adapter);
                            }

                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

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
                            String comp = data.getString("company");
                            String cust = data.getString("cust_rate");

                            listComp.add(new ExchangeRates(comp, cust));

                            adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listComp);
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
}