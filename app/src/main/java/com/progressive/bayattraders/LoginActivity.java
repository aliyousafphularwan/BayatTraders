package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.progressive.bayattraders.helpers.CheckNetwork;
import com.progressive.bayattraders.helpers.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
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

        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    String status = jo.getString("status");
                    if(status.equals("1")){
                        String message = jo.getString("message");
                        String id = String.valueOf(message.equals("id"));
                        // login session save
                        prefs = getSharedPreferences(getString(R.string.login_user), Context.MODE_PRIVATE);
                        SharedPreferences.Editor perfEditor = prefs.edit();
                        perfEditor.putString(getString(R.string.login_user), id);
                        perfEditor.apply();

                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, "Error:" + error.toString(), Toast.LENGTH_SHORT).show();

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