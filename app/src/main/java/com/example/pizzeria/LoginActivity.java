package com.example.pizzeria;

import android.accounts.AccountManager;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btnLogin);
        registerButton = findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inicioSesion = SharedPreferencesUtils.areCredentialsValid(getApplicationContext(), usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if(inicioSesion) {
                    updateUiWithUser();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else showLoginFailed();
            }
        });

    }

    private void updateUiWithUser() {
        String welcome = "Bienvenido " + usernameEditText.getText().toString();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed() {
        Toast.makeText(getApplicationContext(), "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
    }



}