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

import com.example.pizzeria.pojos.DaoUsuarios;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private BaseDatosHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btnLogin);
        registerButton = findViewById(R.id.btnRegister);
        if(Servicio.getStatic().getDbHelper()==null){
            dbHelper = new BaseDatosHelper(this);
            Servicio.getStatic().setDbHelper(dbHelper);
        }
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inicioSesion = DaoUsuarios.getStatic().loginUsuario(dbHelper, usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if(inicioSesion) {
                    updateUiWithUser();
                    Servicio.getStatic().setUsuarioLogueado(usernameEditText.getText().toString());
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