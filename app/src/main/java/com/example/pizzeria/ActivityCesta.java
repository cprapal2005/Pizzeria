package com.example.pizzeria;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pizzeria.pojos.Ingrediente;
import com.example.pizzeria.pojos.Pizza;

import java.io.IOException;
import java.io.InputStream;

public class ActivityCesta extends AppCompatActivity {

    Pizza pizza;
    Pizza pizzaDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCestaPizzas);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);

        Intent intent = getIntent();

        if (intent.hasExtra("pizzaElegida")) {

            pizza = (Pizza) intent.getSerializableExtra("pizzaElegida");
            pizzaDefault = (Pizza) intent.getSerializableExtra("pizzaDefault");

            mostrarInformacionEnUI(pizza);

        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void modificarPizza(View view) {

        Intent intent = new Intent(ActivityCesta.this, ActivityCreaPizza.class);

        intent.putExtra("pizzaElegida", pizza);

        startActivity(intent);

        finish();

    }

    public void borrarPizza(View view) {
        finish();
    }

    public void informacionPizza(View view) {

        TextView textViewInformacionPizza = findViewById(R.id.textViewInformacionPizza);

        String informacionPizzaString = "Tamaño: " + pizza.getTamanoPizza().getNombre() + " " + pizza.getTamanoPizza().getSumaPrecio() + "€\n\n" +
                "Masa: " + pizza.getMasaPizza().getNombre() + " " + pizza.getMasaPizza().getSumaPrecio() + "€\n\n" +
                "Queso: " + pizza.getQuesoPizza().getNombre() + " | Cantidad " + pizza.getQuesoPizza().getCantidadQueso() + " | " + pizza.getQuesoPizza().getSumaPrecio() + "€\n\n" +
                "Salsa: " + pizza.getSalsaPizza().getNombre() + "\n\n";

        informacionPizzaString += "Ingredientes: \n";

        for (Ingrediente ingrediente : pizza.getListaIngredientes()) {
            if(ingrediente.getCantidadIngrediente()>0) informacionPizzaString += ingrediente.getNombre() + ": " + ingrediente.getCantidadIngrediente() + " - " + ingrediente.getSumaPrecio() + "€\n";
        }

        textViewInformacionPizza.setText(informacionPizzaString);

        TableRow rowInformacionCesta1 = findViewById(R.id.rowInformacionCesta1);

        rowInformacionCesta1.setBackgroundColor(Color.TRANSPARENT);

        rowInformacionCesta1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

    }

    private void mostrarInformacionEnUI(Pizza pizza) {

        TextView textViewCestaNombrePizza = findViewById(R.id.textViewCestaNombrePizza);

        textViewCestaNombrePizza.setText(pizza.getNombre());

        TextView textViewCestaDineroPizza = findViewById(R.id.textViewCestaDineroPizza);

        textViewCestaDineroPizza.setText(pizza.getPrecio() + "€");

    }

    public void activarFavorito(View view) {

        Switch switchFavorito = findViewById(R.id.switchFavorito);

        EditText textFavorito = findViewById(R.id.textFavorito);

        if(switchFavorito.isChecked()) textFavorito.setEnabled(true);

        else textFavorito.setEnabled(false);

    }

}