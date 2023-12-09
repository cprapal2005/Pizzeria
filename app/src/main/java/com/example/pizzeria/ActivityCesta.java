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
import android.widget.Toast;

import com.example.pizzeria.pojos.DaoPizzas;
import com.example.pizzeria.pojos.DaoUsuarios;
import com.example.pizzeria.pojos.Ingrediente;
import com.example.pizzeria.pojos.Pizza;

import java.io.IOException;
import java.io.InputStream;

public class ActivityCesta extends AppCompatActivity {

    Pizza pizza;
    Pizza pizzaModificada;
    private BaseDatosHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCestaPizzas);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);
        dbHelper = Servicio.getStatic().getDbHelper();
        Intent intent = getIntent();

        if (intent.hasExtra("pizzaElegida")) {

            pizza = (Pizza) intent.getSerializableExtra("pizzaElegida");
            pizzaModificada = (Pizza) intent.getSerializableExtra("pizzaModificada");

            mostrarInformacionEnUI(pizzaModificada);

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

        intent.putExtra("pizzaElegida", pizzaModificada);

        startActivity(intent);

        finish();

    }

    public void borrarPizza(View view) {
        finish();
    }

    public void informacionPizza(View view) {

        TextView textViewInformacionPizza = findViewById(R.id.textViewInformacionPizza);

        TableRow rowInformacionCesta1 = findViewById(R.id.rowInformacionCesta1);

        if(textViewInformacionPizza.getText().length()==0) {

            String informacionPizzaString = "Tamaño: " + pizzaModificada.getTamanoPizza().getNombre() + " " + pizzaModificada.getTamanoPizza().getSumaPrecio() + "€\n\n" +
                    "Masa: " + pizzaModificada.getMasaPizza().getNombre() + " " + pizzaModificada.getMasaPizza().getSumaPrecio() + "€\n\n" +
                    "Queso: " + pizzaModificada.getQuesoPizza().getNombre() + " | Cantidad " + pizzaModificada.getQuesoPizza().getCantidadQueso() + " | " + pizzaModificada.getQuesoPizza().getSumaPrecio() + "€\n\n" +
                    "Salsa: " + pizzaModificada.getSalsaPizza().getNombre() + "\n\n";

            informacionPizzaString += "Ingredientes: \n";

            for (Ingrediente ingrediente : pizzaModificada.getListaIngredientes()) {
                if(ingrediente.getCantidadIngrediente()>0) informacionPizzaString += ingrediente.getNombre() + ": " + ingrediente.getCantidadIngrediente() + " - " + ingrediente.getSumaPrecio() + "€\n";
            }

            textViewInformacionPizza.setText(informacionPizzaString);

            rowInformacionCesta1.setBackgroundColor(Color.TRANSPARENT);

            rowInformacionCesta1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

        }

        else {

            textViewInformacionPizza.setText("");

            rowInformacionCesta1.setBackgroundColor(Color.parseColor("#D1D1D1"));

            rowInformacionCesta1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 3));

            rowInformacionCesta1.setPadding(0,0,0,10);

        }



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

    public void confirmarCompra(View view) {

        Switch switchFavorito = findViewById(R.id.switchFavorito);

        EditText textFavorito = findViewById(R.id.textFavorito);

        boolean diferente = false;
        if(!pizza.getTamanoPizza().getNombre().equals(pizzaModificada.getTamanoPizza().getNombre())) diferente=true;
        if(!pizza.getMasaPizza().getNombre().equals(pizzaModificada.getMasaPizza().getNombre())) diferente=true;
        if(!pizza.getQuesoPizza().getNombre().equals(pizzaModificada.getQuesoPizza().getNombre())) diferente=true;
        if(!pizza.getSalsaPizza().getNombre().equals(pizzaModificada.getSalsaPizza().getNombre())) diferente=true;
        for (int i = 0; i < pizza.getListaIngredientes().size(); i++) {

            if(pizza.getListaIngredientes().get(i).getCantidadIngrediente()!=pizzaModificada.getListaIngredientes().get(i).getCantidadIngrediente()) diferente=true;

        }

        if(diferente && switchFavorito.isChecked()) {

            pizzaModificada.setNombre(textFavorito.getText().toString());
            if(DaoPizzas.getStatic().insertarPizzaModificada(dbHelper, pizzaModificada, true, Servicio.getStatic().getUsuarioLogueado())) {
                Toast.makeText(getApplicationContext(), "Su pedido se encuentra en preparación", Toast.LENGTH_SHORT).show();
                finish();
            }
            else Toast.makeText(getApplicationContext(), "Error en la inserccion", Toast.LENGTH_SHORT).show();


        }

        else if(diferente) {

            if(DaoPizzas.getStatic().insertarPizzaModificada(dbHelper, pizzaModificada, false, Servicio.getStatic().getUsuarioLogueado())) {
                Toast.makeText(getApplicationContext(), "Su pedido se encuentra en preparación", Toast.LENGTH_SHORT).show();
                finish();
            }
            else Toast.makeText(getApplicationContext(), "Error en la inserccion", Toast.LENGTH_SHORT).show();


        }

        else if(switchFavorito.isChecked()) {

            if(DaoPizzas.getStatic().insertarPizzaDefault(dbHelper, pizza, true, Servicio.getStatic().getUsuarioLogueado())) {
                Toast.makeText(getApplicationContext(), "Su pedido se encuentra en preparación", Toast.LENGTH_SHORT).show();
                finish();
            }
            else Toast.makeText(getApplicationContext(), "Error en la inserccion", Toast.LENGTH_SHORT).show();

        }

        else {

            if(DaoPizzas.getStatic().insertarPizzaDefault(dbHelper, pizza, false, Servicio.getStatic().getUsuarioLogueado())) {
                Toast.makeText(getApplicationContext(), "Su pedido se encuentra en preparación", Toast.LENGTH_SHORT).show();
                finish();
            }
            else Toast.makeText(getApplicationContext(), "Error en la inserccion", Toast.LENGTH_SHORT).show();

        }

    }

}