package com.progressive.bayattraders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.progressive.bayattraders.helpers.CheckNetwork;

import java.util.concurrent.Executor;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    Button login, register;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull @org.jetbrains.annotations.NotNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(MainActivity.this, "Error: " + errString.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull @org.jetbrains.annotations.NotNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                if(new CheckNetwork().isConnected(getApplicationContext())){

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "No internet access, try again", Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(MainActivity.this, "Success: " + result.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(MainActivity.this, "Biometric Auth Failed...", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Authentication").setNegativeButtonText("Login via Email").build();

        login = findViewById(R.id.btnLoginActivity);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });

        register = findViewById(R.id.btnSignupActivity);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new CheckNetwork().isConnected(getApplicationContext())){
                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "No internet access, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}