package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.accounts.AccountManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzeria.pojos.DaoUsuarios;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameRegister;
    private EditText passwordRegister;
    private Button registerButton;
    private BaseDatosHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRegister);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);
        usernameRegister = findViewById(R.id.usernameRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        registerButton = findViewById(R.id.btnCrearCuenta);
        dbHelper = new BaseDatosHelper(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void registrar(View view) {

        if(usernameRegister.getText().length()>1 && passwordRegister.getText().length()>1) {
            if(DaoUsuarios.getStatic().insertarUsuario(dbHelper, usernameRegister.getText().toString(), passwordRegister.getText().toString())) finish();
            else Toast.makeText(getApplicationContext(), "Error en la inserccion", Toast.LENGTH_SHORT).show();
        }

        else Toast.makeText(getApplicationContext(), "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();

    }



}