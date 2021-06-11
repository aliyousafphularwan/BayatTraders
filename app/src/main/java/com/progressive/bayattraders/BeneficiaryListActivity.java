package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.progressive.bayattraders.adapters.DashboardTransAdapter;
import com.progressive.bayattraders.helpers.Constants;
import com.progressive.bayattraders.models.DashboardTransactionDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeneficiaryListActivity extends AppCompatActivity {

    RecyclerView rcv_beneif_list;
    ArrayList<DashboardTransactionDetails> list;
    DashboardTransAdapter dashboardTransAdapter;

    TextView txt_beneif;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Beneficiary List");

        rcv_beneif_list = findViewById(R.id.rcv_beneif_list);
        list = new ArrayList<>();


        pref = getSharedPreferences("mypref", MODE_PRIVATE);

        get_List();


    }

    private void get_List() {

        String cid = pref.getString("uid", null);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.beneficiary + cid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    String status = jo.getString("status");

                    if(status.equals("1")){
                        JSONArray message = jo.getJSONArray("message");
                        for (int i = 0; i <= message.length(); i++){

                            JSONObject data = message.getJSONObject(i);

                           // Toast.makeText(BeneficiaryListActivity.this, "Message: " + data, Toast.LENGTH_SHORT).show();

                            String name = data.getString("pname");

                            // Toast.makeText(BeneficiaryListActivity.this, "name: " + name, Toast.LENGTH_SHORT).show();

                            list.add(new DashboardTransactionDetails(name, "", "", ""));

                            dashboardTransAdapter = new DashboardTransAdapter(list);
                            rcv_beneif_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rcv_beneif_list.setAdapter(dashboardTransAdapter);

                        }

                    }else{

                        Toast.makeText(BeneficiaryListActivity.this, "error", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BeneficiaryListActivity.this, "Error: check your internet", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}