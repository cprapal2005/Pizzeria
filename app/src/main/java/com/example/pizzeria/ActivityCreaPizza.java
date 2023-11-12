package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pizzeria.pojos.Ingrediente;
import com.example.pizzeria.pojos.Pizza;

public class ActivityCreaPizza extends AppCompatActivity {

    Pizza pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_pizza);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCreaPizza);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);

        Intent intent = getIntent();

        if (intent.hasExtra("pizzaElegida")) {

            pizza = (Pizza) intent.getSerializableExtra("pizzaElegida");

            mostrarInformacionEnUI(pizza);

        }


    }

    private void mostrarInformacionEnUI(Pizza pizza) {

        TextView textViewNombrePizza = findViewById(R.id.textViewNombrePizza);

        textViewNombrePizza.setText(pizza.getNombre());

        ImageView imagenCreaPizza = findViewById(R.id.imagenCreaPizza);

        imagenCreaPizza.setImageResource(pizza.getImagenResourceId());

        TextView textViewDescripcionPizza = findViewById(R.id.textViewDescripcionPizza);

        textViewDescripcionPizza.setText(pizza.getDescripcion());

        Button btnTamañoPizza = findViewById(R.id.btnTamañoPizza);

        btnTamañoPizza.setText("Tamaño: " + pizza.getTamanoPizza().getNombre());

        Button btnMasaPizza = findViewById(R.id.btnMasaPizza);

        btnMasaPizza.setText("Masa: " + pizza.getMasaPizza().getNombre());

        Button btnSalsaPizza = findViewById(R.id.btnSalsaPizza);

        btnSalsaPizza.setText("Salsa: " + pizza.getSalsaPizza().getNombre());

        Button btnQuesoPizza = findViewById(R.id.btnQuesoPizza);

        btnQuesoPizza.setText("Queso: " + pizza.getQuesoPizza().getNombre());

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showBottomSheetTamano(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheettamanos, null);
        dialog.setContentView(bottomSheetView);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1500);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);


        RadioGroup radioGroupTamano = bottomSheetView.findViewById(R.id.radioGroupTamano);
        RadioButton radioFamiliar = bottomSheetView.findViewById(R.id.radio_Familiar);
        RadioButton radioMediana = bottomSheetView.findViewById(R.id.radio_Mediana);
        RadioButton radioIndividual = bottomSheetView.findViewById(R.id.radio_Individual);

        switch (pizza.getTamanoPizza()) {

            case Familiar:
                radioGroupTamano.check(radioFamiliar.getId());
                break;

            case Mediana:
                radioGroupTamano.check(radioMediana.getId());
                break;

            case Individual:
                radioGroupTamano.check(radioIndividual.getId());
                break;

        }

    }

    public void showBottomSheetMasa(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheetmasa, null);
        dialog.setContentView(bottomSheetView);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1500);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        RadioGroup radioGroupMasa = bottomSheetView.findViewById(R.id.radioGroupMasa);
        RadioButton radioOriginal = bottomSheetView.findViewById(R.id.radio_Original);
        RadioButton radioRollRoulette = bottomSheetView.findViewById(R.id.radio_RollRoulette);
        RadioButton radioMozzarellaRoll = bottomSheetView.findViewById(R.id.radio_MozzarellaRoll);
        RadioButton radioCheddapeñoRoll = bottomSheetView.findViewById(R.id.radio_CheddapeñoRoll);
        RadioButton radioCabraRoll = bottomSheetView.findViewById(R.id.radio_CabraRoll);
        RadioButton radioFinizzima = bottomSheetView.findViewById(R.id.radio_Finizzima);
        RadioButton radioPan = bottomSheetView.findViewById(R.id.radio_Pan);
        RadioButton radioVeggiThinCrust = bottomSheetView.findViewById(R.id.radio_VeggiThinCrust);

        switch (pizza.getMasaPizza()) {

            case Original:
                radioGroupMasa.check(radioOriginal.getId());
                break;

            case Roll_Roulette:
                radioGroupMasa.check(radioRollRoulette.getId());
                break;

            case Mozzarella_Roll:
                radioGroupMasa.check(radioMozzarellaRoll.getId());
                break;

            case Cheddapeño_Roll:
                radioGroupMasa.check(radioCheddapeñoRoll.getId());
                break;

            case Cabra_Roll:
                radioGroupMasa.check(radioCabraRoll.getId());
                break;

            case Finizzima:
                radioGroupMasa.check(radioFinizzima.getId());
                break;

            case Pan:
                radioGroupMasa.check(radioPan.getId());
                break;

            case Veggi_Thin_Crust:
                radioGroupMasa.check(radioVeggiThinCrust.getId());
                break;

        }

    }

    public void showBottomSheetSalsa(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheetsalsa, null);
        dialog.setContentView(bottomSheetView);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1500);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        RadioGroup radioGroupSalsa = bottomSheetView.findViewById(R.id.radioGroupSalsa);
        RadioButton radioSinSalsa = bottomSheetView.findViewById(R.id.radio_SinSalsa);
        RadioButton radioSalsaBBQOriginal = bottomSheetView.findViewById(R.id.radio_SalsaBBQOriginal);
        RadioButton radioSalsaRancheraBBQ = bottomSheetView.findViewById(R.id.radio_SalsaRancheraBBQ);
        RadioButton radioSalsaTomate = bottomSheetView.findViewById(R.id.radio_SalsaTomate);
        RadioButton radioCremaFresca = bottomSheetView.findViewById(R.id.radio_CremaFresca);
        RadioButton radioCremaFrescaBarbacoa = bottomSheetView.findViewById(R.id.radio_CremaFrescaBarbacoa);
        RadioButton radioSalsaBourbon = bottomSheetView.findViewById(R.id.radio_SalsaBourbon);
        RadioButton radioSalsaBarbacoaTexas = bottomSheetView.findViewById(R.id.radio_SalsaBarbacoaTexas);
        RadioButton radioSalsaCarbonaraMornay = bottomSheetView.findViewById(R.id.radio_SalsaCarbonaraMornay);

        switch (pizza.getSalsaPizza()) {

            case Sin_Salsa:
                radioGroupSalsa.check(radioSinSalsa.getId());
                break;

            case Salsa_BBQ_Original:
                radioGroupSalsa.check(radioSalsaBBQOriginal.getId());
                break;

            case Salsa_Ranchera_BBQ:
                radioGroupSalsa.check(radioSalsaRancheraBBQ.getId());
                break;

            case Salsa_Tomate:
                radioGroupSalsa.check(radioSalsaTomate.getId());
                break;

            case Crema_Fresca:
                radioGroupSalsa.check(radioCremaFresca.getId());
                break;

            case Crema_Fresca_Barbacoa:
                radioGroupSalsa.check(radioCremaFrescaBarbacoa.getId());
                break;

            case Salsa_Bourbon:
                radioGroupSalsa.check(radioSalsaBourbon.getId());
                break;

            case Salsa_Barbacoa_Texas:
                radioGroupSalsa.check(radioSalsaBarbacoaTexas.getId());
                break;

            case Salsa_Carbonara_Mornay:
                radioGroupSalsa.check(radioSalsaCarbonaraMornay.getId());
                break;

        }

    }

    public void showBottomSheetQueso(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheetqueso, null);
        dialog.setContentView(bottomSheetView);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1500);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        RadioGroup radioGroupQueso = bottomSheetView.findViewById(R.id.radioGroupQueso);
        RadioButton radio_SinQueso = bottomSheetView.findViewById(R.id.radio_SinQueso);
        RadioButton radio_QuesoMozzarella = bottomSheetView.findViewById(R.id.radio_QuesoMozzarella);
        RadioButton radio_VeggiCheeseViolife = bottomSheetView.findViewById(R.id.radio_VeggiCheeseViolife);

        switch (pizza.getQuesoPizza()) {

            case Sin_Queso:
                radioGroupQueso.check(radio_SinQueso.getId());
                break;

            case Queso_Mozzarella:
                radioGroupQueso.check(radio_QuesoMozzarella.getId());
                break;

            case VeggiCheese_Violife:
                radioGroupQueso.check(radio_VeggiCheeseViolife.getId());
                break;

        }

        TextView textViewCantidadQuesoPizza = bottomSheetView.findViewById(R.id.textViewCantidadQuesoPizza);

        switch (pizza.getQuesoPizza().getCantidadQueso()) {

            case 0:
                textViewCantidadQuesoPizza.setText("Estandar");
                break;

            case 1:
                textViewCantidadQuesoPizza.setText("Mucho queso");
                break;

            case 2:
                textViewCantidadQuesoPizza.setText("Mucheeesemo queso");
                break;

        }

    }

    public void showBottomSheetIngredientes(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheetingredientes, null);
        dialog.setContentView(bottomSheetView);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1500);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        TextView textViewCantidadAceitunas = bottomSheetView.findViewById(R.id.textViewCantidadAceitunas);
        TextView textViewCantidadAnchoas = bottomSheetView.findViewById(R.id.textViewCantidadAnchoas);
        TextView textViewCantidadAtun = bottomSheetView.findViewById(R.id.textViewCantidadAtun);
        TextView textViewCantidadBacon = bottomSheetView.findViewById(R.id.textViewCantidadBacon);
        TextView textViewCantidadBaconCrispy = bottomSheetView.findViewById(R.id.textViewCantidadBaconCrispy);
        TextView textViewCantidadCebolla = bottomSheetView.findViewById(R.id.textViewCantidadCebolla);
        TextView textViewCantidadCebollaCaramelizada = bottomSheetView.findViewById(R.id.textViewCantidadCebollaCaramelizada);
        TextView textViewCantidadChampinon = bottomSheetView.findViewById(R.id.textViewCantidadChampinon);
        TextView textViewCantidadMaiz = bottomSheetView.findViewById(R.id.textViewCantidadMaiz);
        TextView textViewCantidadPeperoni = bottomSheetView.findViewById(R.id.textViewCantidadPeperoni);
        TextView textViewCantidadPimiento = bottomSheetView.findViewById(R.id.textViewCantidadPimiento);
        TextView textViewCantidadPina = bottomSheetView.findViewById(R.id.textViewCantidadPina);
        TextView textViewCantidadPolloBuffalo = bottomSheetView.findViewById(R.id.textViewCantidadPolloBuffalo);
        TextView textViewCantidadPolloParrilla = bottomSheetView.findViewById(R.id.textViewCantidadPolloParrilla);
        TextView textViewCantidadPulledPork = bottomSheetView.findViewById(R.id.textViewCantidadPulledPork);
        TextView textViewCantidadQuesoCheddarRojo = bottomSheetView.findViewById(R.id.textViewCantidadQuesoCheddarRojo);
        TextView textViewCantidadQuesoParmesano = bottomSheetView.findViewById(R.id.textViewCantidadQuesoParmesano);
        TextView textViewCantidadTernera = bottomSheetView.findViewById(R.id.textViewCantidadTernera);
        TextView textViewCantidadTomate = bottomSheetView.findViewById(R.id.textViewCantidadTomate);
        TextView textViewCantidadVegeroni = bottomSheetView.findViewById(R.id.textViewCantidadVegeroni);
        TextView textViewCantidadVeggichicken = bottomSheetView.findViewById(R.id.textViewCantidadVeggichicken);
        TextView textViewCantidadYork = bottomSheetView.findViewById(R.id.textViewCantidadYork);


        pizza.getListaIngredientes().forEach(ingrediente -> {

            switch (ingrediente.getNombre()) {

                case "Aceitunas":
                    textViewCantidadAceitunas.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case"Anchoas":
                    textViewCantidadAnchoas.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Atun":
                    textViewCantidadAtun.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Bacon":
                    textViewCantidadBacon.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Bacon Crispy":
                    textViewCantidadBaconCrispy.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Cebolla":
                    textViewCantidadCebolla.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Cebolla Caramelizada":
                    textViewCantidadCebollaCaramelizada.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Champiñon":
                    textViewCantidadChampinon.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Maiz":
                    textViewCantidadMaiz.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Peperoni":
                    textViewCantidadPeperoni.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Pimiento":
                    textViewCantidadPimiento.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Piña":
                    textViewCantidadPina.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Pollo Buffalo":
                    textViewCantidadPolloBuffalo.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Pollo Parrilla":
                    textViewCantidadPolloParrilla.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Pulled Pork":
                    textViewCantidadPulledPork.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Queso Cheddar Rojo":
                    textViewCantidadQuesoCheddarRojo.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Queso Parmesano":
                    textViewCantidadQuesoParmesano.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Ternera":
                    textViewCantidadTernera.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Tomate":
                    textViewCantidadTomate.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Vegeroni":
                    textViewCantidadVegeroni.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "Veggi Chicken":
                    textViewCantidadVeggichicken.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;
                case "York":
                    textViewCantidadYork.setText(String.valueOf(ingrediente.getCantidadIngrediente()));
                    break;

            }
        });

    }

}