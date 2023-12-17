package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cambiarActivityPizza(View v) {

        new MiAsyncTask(MainActivity.this).execute();

        startActivity(new Intent(MainActivity.this, ActivityPizzas.class));

    }

    public void cambiarActivityFavoritos(View v) {

        startActivity(new Intent(MainActivity.this, ActivityFavoritos.class));

    }

    public void iniciarSesion(View v) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    public void cerrarAlpicacion(View v) {

        finish();

    }

}