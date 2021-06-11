package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.progressive.bayattraders.helpers.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddBeneficiaryActivity extends AppCompatActivity {

    Button beneif_list, add_beneif;
    EditText et_name, et_cnic, et_adrs, et_phone;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beneficiary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Beneficiary");

        pref = getSharedPreferences("mypref", MODE_PRIVATE);

        et_name = findViewById(R.id.brnif_name);
        et_cnic = findViewById(R.id.brnif_cnic);
        et_adrs = findViewById(R.id.brnif_address);
        et_phone = findViewById(R.id.brnif_phone);


        beneif_list = findViewById(R.id.beneif_list);
        beneif_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Beneficiary List not available", Toast.LENGTH_SHORT).show();

            }
        });

        add_beneif = findViewById(R.id.submit_beneif);
        add_beneif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_new_beneif();
            }
        });

    }

    private void add_new_beneif() {
        String name = et_name.getText().toString().trim();;
        String cnic = et_cnic.getText().toString().trim();;
        String adrs = et_adrs.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        String cid = pref.getString("cid", null);

        if (TextUtils.isEmpty(name)){
            et_name.setError("Name is Mandatory");
            et_name.requestFocus();
        }

        else if (TextUtils.isEmpty(cnic)){
            et_cnic.setError("cnic is Mandatory");
            et_cnic.requestFocus();
        }

        else if (TextUtils.isEmpty(adrs)){
            et_adrs.setError("Address is Mandatory");
            et_adrs.requestFocus();
        }

        else if (TextUtils.isEmpty(phone)){
            et_phone.setError("Phone is Mandatory");
            et_phone.requestFocus();
        }
        else{

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.add_beneficiary, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jo = new JSONObject(response);
                        String status = jo.getString("status");
                        String msg = jo.getString("message");
                        if(status.equals("1")){
                            Toast.makeText(AddBeneficiaryActivity.this, "Message: " + msg, Toast.LENGTH_SHORT).show();
                            et_name.setText("");
                            et_cnic.setText("");
                            et_adrs.setText("");
                            et_phone.setText("");
                        }else{
                            Toast.makeText(AddBeneficiaryActivity.this, "Message: " + msg, Toast.LENGTH_SHORT).show();
                            et_name.setText("");
                            et_cnic.setText("");
                            et_adrs.setText("");
                            et_phone.setText("");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(AddBeneficiaryActivity.this, "Message: " + e.toString(), Toast.LENGTH_SHORT).show();
                        et_name.setText("");
                        et_cnic.setText("");
                        et_adrs.setText("");
                        et_phone.setText("");
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(AddBeneficiaryActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    et_name.setText("");
                    et_cnic.setText("");
                    et_adrs.setText("");
                    et_phone.setText("");

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("name", name);
                    params.put("cnic", cnic);
                    params.put("address", adrs);
                    params.put("phone", phone);
                    params.put("cid", cid);

                    return params;

                }
            };

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);

        }

    }
}