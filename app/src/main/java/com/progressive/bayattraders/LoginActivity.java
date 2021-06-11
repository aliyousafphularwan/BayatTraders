package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.progressive.bayattraders.helpers.CheckNetwork;
import com.progressive.bayattraders.helpers.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;
    ProgressBar progressBar;
    SharedPreferences prefs;

    private static final String SHRD_PREF = "mypref";
    private static final String KEY_ID = "uid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        prefs = getSharedPreferences(SHRD_PREF, MODE_PRIVATE);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        progressBar = findViewById(R.id.prog_login);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(new CheckNetwork().isConnected(getApplicationContext())){
                    getLogin();
                }else{
                    Toast.makeText(LoginActivity.this, "No internet access, try again", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void getLogin() {

        btnLogin.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);


        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    String status = jo.getString("status");
                    JSONObject message = jo.getJSONObject("message");
                    String id = message.getString("id");
                    if(status.equals("1")){
                        // login session save
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString(KEY_ID, id);
                        editor.apply();

                        Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(i);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "wrong username or password", Toast.LENGTH_SHORT).show();
                    btnLogin.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, "Error:" + error.toString(), Toast.LENGTH_SHORT).show();
                btnLogin.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("email", user);
                params.put("password", pass);

                return params;

            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}