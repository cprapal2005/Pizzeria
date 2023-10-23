package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pizzeria.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView msg = (android.widget.TextView) findViewById(R.id.txtNombre);
        //if(getIntent()!=null) msg.setText("Bienvenido " + getIntent().getExtras().getString("usuario"));
    }

    public void cambiarActivity(View v) {

        startActivity(new Intent(MainActivity.this, ActivityPizzas.class));

    }

    public void iniciarSesion(View v) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        Button btnElegir = findViewById(R.id.btnElegir);
        btnElegir.setEnabled(true);
        Button btnIniciar = findViewById(R.id.btnIniciar);
        btnIniciar.setText("Cambiar Usuario");
        //finish();
    }

    public void cerrarAlpicacion(View v) {

        finish();

    }

}