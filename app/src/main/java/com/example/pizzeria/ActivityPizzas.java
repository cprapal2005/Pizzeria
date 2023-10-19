package com.example.pizzeria;

import static com.example.pizzeria.R.id.action_button;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityPizzas extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);
    }

    // Inflar el menú en la Toolbar
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.id.toolbar, menu);
        return true;
    }

    // Manejar acciones del botón de la Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_button) {
            MenuItem botonVolverToolbar = findViewById(action_button);
            //botonVolverToolbar.setOnMenuItemClickListener(cambiarActivity());
        }

        return super.onOptionsItemSelected(item);
    }

    public MenuItem.OnMenuItemClickListener cambiarActivity() {

        startActivity(new Intent(ActivityPizzas.this, MainActivity.class));
        finish();

    }






}