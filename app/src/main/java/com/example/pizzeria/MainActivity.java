package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cambiarActivity(View v) {

        startActivity(new Intent(MainActivity.this, ActivityPizzas.class));
        finish();

    }

    public void cerrarAlpicacion(View v) {

        finish();

    }

}