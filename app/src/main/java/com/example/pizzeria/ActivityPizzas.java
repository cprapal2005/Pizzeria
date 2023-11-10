package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.example.pizzeria.pojos.DaoIngredientes;
import com.example.pizzeria.pojos.Ingrediente;
import com.example.pizzeria.pojos.MasaPizza;
import com.example.pizzeria.pojos.Pizza;
import com.example.pizzeria.pojos.QuesoPizza;
import com.example.pizzeria.pojos.SalsaPizza;
import com.example.pizzeria.pojos.TamanoPizza;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class ActivityPizzas extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                scrollAutomatico(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                scrollAutomatico(tab.getPosition());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void elegirPizza(View v) {

        Pizza pizza;

        //Deluxe

        if(v.getId()==findViewById(R.id.btnHawaianaCrispyPizza).getId()) pizza = new Pizza("Hawaiana Plus", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Carbonara_Mornay, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Bacon", 1), new Ingrediente("Bacon Crispy", 1), new Ingrediente("Piña", 1)}));

        else if(v.getId()==findViewById(R.id.btnParmesanaCarbonaraPizza).getId()) pizza = new Pizza("Parmesana Carbonara", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Sin_Queso, SalsaPizza.Salsa_Carbonara_Mornay, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Bacon", 1), new Ingrediente("Bacon Crispy", 1), new Ingrediente("Cebolla", 1), new Ingrediente("Queso parmesano", 1)}));

        else if(v.getId()==findViewById(R.id.btnCremozzaBourbonPizza).getId()) pizza = new Pizza("Cremozza Estilo Bourbon", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Bourbon, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Bacon", 1), new Ingrediente("Pollo Parrilla", 1), new Ingrediente("Cebolla", 1)}));

        else if(v.getId()==findViewById(R.id.btnCremozzaBBQPizza).getId()) pizza = new Pizza("Cremozza BBQ", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Crema_Fresca_Barbacoa, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Bacon", 1), new Ingrediente("Pollo Parrilla", 1), new Ingrediente("Cebolla", 1)}));

        else if(v.getId()==findViewById(R.id.btnCheesixPizza).getId()) pizza = new Pizza("Cheesix", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Crema_Fresca, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Queso Parmesano", 1)}));

        else if(v.getId()==findViewById(R.id.btnExtravaganzzaPizza).getId()) pizza = new Pizza("Extravaganzza", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("York", 1), new Ingrediente("Bacon", 1), new Ingrediente("Peperoni", 1), new Ingrediente("Aceitunas", 1), new Ingrediente("Champiñon", 1), new Ingrediente("Cebolla", 1), new Ingrediente("Pimiento", 1)}));

        else if(v.getId()==findViewById(R.id.btnVarvacoaHeuraPizza).getId()) pizza = new Pizza("Varvacoa Heura", TamanoPizza.Familiar, MasaPizza.Veggi_Thin_Crust, QuesoPizza.VeggiCheese_Violife, SalsaPizza.Salsa_BBQ_Original, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Veggi Chicken", 1), new Ingrediente("Cebolla", 1), new Ingrediente("Maiz", 1)}));

        else if(v.getId()==findViewById(R.id.btnTonyVeggeroniPizza).getId()) pizza = new Pizza("Tony Veggeroni", TamanoPizza.Familiar, MasaPizza.Veggi_Thin_Crust, QuesoPizza.VeggiCheese_Violife, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Vegeroni", 1)}));

        //Clazzicas



    }

    public void cambiarActivity(View v) {

        startActivity(new Intent(ActivityPizzas.this, ActivityCreaPizza.class));

    }

    public void scrollAutomatico(int position) {

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        NestedScrollView nestedScrollView = findViewById(R.id.nestedScroll);

        if (position == 0) {

            TableRow tab1 = findViewById(R.id.rowDeluxe);

            nestedScrollView.scrollTo(0, tab1.getTop());

        }

        if (position == 1) {

                TableRow tab1 = findViewById(R.id.rowClazzicas);

            nestedScrollView.scrollTo(0, tab1.getTop());

        }

        if (position == 2) {

                TableRow tab1 = findViewById(R.id.rowCreaPizza);

            nestedScrollView.scrollTo(0, tab1.getTop());

        }

    }

}