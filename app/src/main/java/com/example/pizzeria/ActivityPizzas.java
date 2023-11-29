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
import com.example.pizzeria.pojos.DaoPizzas;
import com.example.pizzeria.pojos.Ingrediente;
import com.example.pizzeria.pojos.MasaPizza;
import com.example.pizzeria.pojos.Pizza;
import com.example.pizzeria.pojos.QuesoPizza;
import com.example.pizzeria.pojos.SalsaPizza;
import com.example.pizzeria.pojos.TamanoPizza;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ActivityPizzas extends AppCompatActivity {

    private BaseDatosHelper dbHelper;

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

    public void elegirPizza(View v) {

        ArrayList<Pizza> arrayListPizzas = DaoPizzas.getStatic().getPizzas(dbHelper);

        Pizza pizza = null;

        //Deluxe

        if(v.getId()==findViewById(R.id.btnHawaianaCrispyPizza).getId()) pizza = arrayListPizzas.get(0); //new Pizza("Hawaiana Plus", "Base de salsa Mornay, queso 100% mozzarella, bacon, bacon crispy y piña.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Carbonara_Mornay, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Baco   n", 1), new Ingrediente("Bacon Crispy", 1), new Ingrediente("Piña", 1)}), R.drawable.hawaianapizza);

        else if(v.getId()==findViewById(R.id.btnParmesanaCarbonaraPizza).getId()) pizza = arrayListPizzas.get(1);//new Pizza("Parmesana Carbonara","Salsa carbonara Mornay, queso 100% mozzarella, cheddar, emmental, parmesano, cebolla, bacon y bacon crispy.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Sin_Queso, SalsaPizza.Salsa_Carbonara_Mornay, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Bacon", 1), new Ingrediente("Bacon Crispy", 1), new Ingrediente("Cebolla", 1), new Ingrediente("Queso parmesano", 1)}), R.drawable.carbonara);

        else if(v.getId()==findViewById(R.id.btnCremozzaBourbonPizza).getId()) pizza = arrayListPizzas.get(2);//new Pizza("Cremozza Estilo Bourbon","Base de crema fresca, queso 100% mozzarella, bacon, pollo a la parrilla, cebolla, salsa Bourbon (0% Alcohol).", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Bourbon, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Bacon", 1), new Ingrediente("Pollo Parrilla", 1), new Ingrediente("Cebolla", 1)}), R.drawable.cremozzabourbonpizza);

        else if(v.getId()==findViewById(R.id.btnCremozzaBBQPizza).getId()) pizza = arrayListPizzas.get(3);//new Pizza("Cremozza BBQ","Base de crema fresca, queso 100% mozzarella, bacon, pollo a la parrilla, cebolla, salsa barbacoa.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Crema_Fresca_Barbacoa, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Bacon", 1), new Ingrediente("Pollo Parrilla", 1), new Ingrediente("Cebolla", 1)}), R.drawable.cremozzabbqpizza);

        else if(v.getId()==findViewById(R.id.btnCheesixPizza).getId()) pizza = arrayListPizzas.get(4);//new Pizza("Cheesix","Crema fresca, queso 100% mozzarella, cheddar, emmental, parmesano, gorgonzola, queso de cabra.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Crema_Fresca, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Queso Parmesano", 1)}), R.drawable.cheesixpizza);

        else if(v.getId()==findViewById(R.id.btnExtravaganzzaPizza).getId()) pizza = arrayListPizzas.get(5);//new Pizza("Extravaganzza","Salsa de tomate, mozzarella, carne de vacuno, pepperoni, York, bacon, cebolla, pimiento verde, champiñón y aceitunas negras.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("York", 1), new Ingrediente("Bacon", 1), new Ingrediente("Peperoni", 1), new Ingrediente("Aceitunas", 1), new Ingrediente("Champiñon", 1), new Ingrediente("Cebolla", 1), new Ingrediente("Pimiento", 1)}), R.drawable.extravaganzzapizza);

        else if(v.getId()==findViewById(R.id.btnVarvacoaHeuraPizza).getId()) pizza = arrayListPizzas.get(6);//new Pizza("Varvacoa Heura","Salsa de bbq, VeggiCheese Violife, VeggiChicken de Heura, cebolla y maíz. Con masa veggi Thin Crust.", TamanoPizza.Familiar, MasaPizza.Veggi_Thin_Crust, QuesoPizza.VeggiCheese_Violife, SalsaPizza.Salsa_BBQ_Original, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Veggi Chicken", 1), new Ingrediente("Cebolla", 1), new Ingrediente("Maiz", 1)}), R.drawable.veganavarvacoaheurapizza);

        else if(v.getId()==findViewById(R.id.btnTonyVeggeroniPizza).getId()) pizza = arrayListPizzas.get(7);//new Pizza("Tony Veggeroni","Salsa de tomate, VeggiCheese Violife, Vegeroni. Con masa veggi Thin Crust.", TamanoPizza.Familiar, MasaPizza.Veggi_Thin_Crust, QuesoPizza.VeggiCheese_Violife, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Vegeroni", 1)}), R.drawable.veganatonyveggeronipizza);

        //Clazzicas

        else if(v.getId()==findViewById(R.id.btnPecadoCarnalPizza).getId()) pizza = arrayListPizzas.get(8);//new Pizza("Pecado Carnal","Salsa de tomate, extra de queso 100% mozzarella, carne de vacuno, bacon, pepperoni, york.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("York", 1), new Ingrediente("Bacon", 1), new Ingrediente("Peperoni", 1)}), R.drawable.pecadocarnal);

        else if(v.getId()==findViewById(R.id.btnCarbonaraPizza).getId()) pizza = arrayListPizzas.get(9);//new Pizza("Carbonara","Crema fresca, queso 100% mozzarella, bacon, champiñón y cebolla.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Crema_Fresca, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Champiñon", 1), new Ingrediente("Bacon", 1), new Ingrediente("Cebolla", 1)}), R.drawable.carbonara);

        else if(v.getId()==findViewById(R.id.btnBarbacoaPizza).getId()) pizza = arrayListPizzas.get(10);//new Pizza("Barbacoa","Salsa barbacoa, queso 100% mozzarella,carne de vacuno, cebolla, bacon, maíz.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_BBQ_Original, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Cebolla", 1), new Ingrediente("Bacon", 1), new Ingrediente("Maiz", 1)}), R.drawable.barbacoa);

        else if(v.getId()==findViewById(R.id.btnHawaianaPlusPizza).getId()) pizza = arrayListPizzas.get(11);//new Pizza("Hawaiana","Salsa de tomate, extra de queso 100% mozzarella, doble de York y piña.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("York", 1), new Ingrediente("Piña", 1)}), R.drawable.hawaiana);

        else if(v.getId()==findViewById(R.id.btnPolloParrillaPizza).getId()) pizza = arrayListPizzas.get(12);//new Pizza("Pollo a la parrilla","Salsa de tomate, queso 100% mozzarella, doble de pollo a la parrilla, cebolla, champiñón y maíz.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Pollo Parrilla", 1), new Ingrediente("Maiz", 1), new Ingrediente("Champiñon", 1), new Ingrediente("Cebolla", 1)}), R.drawable.polloparrillapizza);

        else if(v.getId()==findViewById(R.id.btnCuatroQuesosPizza).getId()) pizza = arrayListPizzas.get(13);//new Pizza("Cuatro Quesos","Salsa de tomate, queso 100% mozzarella, cheddar, emmental, gorgonzola (receta mejorada).", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Queso Parmesano", 1)}), R.drawable.cuatroquesos);

        else if(v.getId()==findViewById(R.id.btnTonyPepperoniPizza).getId()) pizza = arrayListPizzas.get(14);//new Pizza("Tony Pepperoni","Salsa de tomate, extra de queso 100% mozzarella y doble de pepperoni.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Peperoni", 1)}), R.drawable.tonypepperoni);

        else if(v.getId()==findViewById(R.id.btnCampiñaPizza).getId()) pizza = arrayListPizzas.get(15);//new Pizza("Campiña","Salsa de tomate, extra de queso 100% mozzarella, champiñón, pimiento verde, cebolla, aceitunas negras y tomate natural.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{new Ingrediente("Pimiento", 1), new Ingrediente("Tomate", 1), new Ingrediente("Aceitunas", 1), new Ingrediente("Champiñon", 1), new Ingrediente("Cebolla", 1)}), R.drawable.campi_a);

        //Elegir

        else if(v.getId()==findViewById(R.id.btnMargaritaPizza).getId()) pizza = arrayListPizzas.get(16);//new Pizza("Margarita","Sobre una base de salsa de tomate y queso 100% mozzarella, añade los ingredientes que tú quieras.", TamanoPizza.Familiar, MasaPizza.Original, QuesoPizza.Queso_Mozzarella, SalsaPizza.Salsa_Tomate, DaoIngredientes.getStatic().getIngredientes(new Ingrediente[]{}), R.drawable.margarita);

        Intent intent = new Intent(ActivityPizzas.this, ActivityCreaPizza.class);

        intent.putExtra("pizzaElegida", pizza);
        
        startActivity(intent);

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