package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.pizzeria.pojos.DaoPizzas;
import com.example.pizzeria.pojos.Pizza;

import java.util.ArrayList;
import java.util.List;

public class ActivityFavoritos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PizzaAdapter pizzaAdapter;
    private List<Pizza> listaDePizzas;
    private BaseDatosHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFavoritosPizzas);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);
        dbHelper = Servicio.getStatic().getDbHelper();
        recyclerView = findViewById(R.id.recyclerView);

        // Crea e inicializa tu lista de pizzas.
        listaDePizzas = obtenerListaDePizzas(); // Aquí debes implementar tu lógica para obtener las pizzas.

        // Crea e inicializa tu adaptador de pizza.
        pizzaAdapter = new PizzaAdapter(this, listaDePizzas);

        // Configura el administrador de diseño para el RecyclerView.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Asigna el adaptador al RecyclerView.
        recyclerView.setAdapter(pizzaAdapter);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Pizza> obtenerListaDePizzas() {
        // Simplemente devuelve una lista de ejemplo por ahora.
        ArrayList<Pizza> pizzas = DaoPizzas.getStatic().getPizzasFavoritas(dbHelper, Servicio.getStatic().getUsuarioLogueado());

        return pizzas;
    }

}