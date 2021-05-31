package com.progressive.bayattraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.progressive.bayattraders.helpers.CheckNetwork;

public class MainActivity extends AppCompatActivity {

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        login = findViewById(R.id.btnLoginActivity);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new CheckNetwork().isConnected(getApplicationContext())){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "No internet access, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}