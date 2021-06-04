package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    EditText et_fname, et_lname, et_email, et_pass, et_country;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_fname = findViewById(R.id.reg_fname);
        et_lname = findViewById(R.id.reg_lname);
        et_email = findViewById(R.id.reg_email);
        et_pass = findViewById(R.id.reg_password);
        et_country = findViewById(R.id.reg_country);

        btnRegister = findViewById(R.id.btnregister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getRegister();

            }
        });

    }

    private void getRegister() {

        String fname = et_fname.getText().toString().trim();
        String lname = et_lname.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        String country = et_country.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.signup, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    String status = jo.getString("status");
                    if(status.equals("1")){
                        Toast.makeText(RegisterActivity.this, "your account created, check your email for login details.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Error: " + jo.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Error: check internet connection, & try again", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("fname", fname);
                params.put("lname", lname);
                params.put("email", email);
                params.put("password", pass);
                params.put("country", country);

                return params;

            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}