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
import com.example.pizzeria.pojos.MasaPizza;
import com.example.pizzeria.pojos.Pizza;
import com.example.pizzeria.pojos.QuesoPizza;
import com.example.pizzeria.pojos.SalsaPizza;
import com.example.pizzeria.pojos.TamanoPizza;

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

        RadioButton radioFamiliar = bottomSheetView.findViewById(R.id.radio_Familiar);
        RadioButton radioMediana = bottomSheetView.findViewById(R.id.radio_Mediana);
        RadioButton radioIndividual = bottomSheetView.findViewById(R.id.radio_Individual);

        radioFamiliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarTamano(bottomSheetView);
            }
        });

        radioMediana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarTamano(bottomSheetView);
            }
        });

        radioIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarTamano(bottomSheetView);
            }
        });

        actualizarTamano(bottomSheetView);


    }

    private void modificarTamano(View bottomSheetView) {

        RadioGroup radioGroupTamano = bottomSheetView.findViewById(R.id.radioGroupTamano);
        RadioButton radioFamiliar = bottomSheetView.findViewById(R.id.radio_Familiar);
        RadioButton radioMediana = bottomSheetView.findViewById(R.id.radio_Mediana);
        RadioButton radioIndividual = bottomSheetView.findViewById(R.id.radio_Individual);

        if(radioGroupTamano.getCheckedRadioButtonId()==radioFamiliar.getId()) pizza.setTamanoPizza(TamanoPizza.Familiar);

        else if(radioGroupTamano.getCheckedRadioButtonId()==radioMediana.getId()) pizza.setTamanoPizza(TamanoPizza.Mediana);

        else if(radioGroupTamano.getCheckedRadioButtonId()==radioIndividual.getId()) pizza.setTamanoPizza(TamanoPizza.Individual);

        actualizarTamano(bottomSheetView);

    }

    private void actualizarTamano(View bottomSheetView) {

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

        mostrarInformacionEnUI(pizza);

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

        RadioButton radioOriginal = bottomSheetView.findViewById(R.id.radio_Original);
        RadioButton radioRollRoulette = bottomSheetView.findViewById(R.id.radio_RollRoulette);
        RadioButton radioMozzarellaRoll = bottomSheetView.findViewById(R.id.radio_MozzarellaRoll);
        RadioButton radioCheddapeñoRoll = bottomSheetView.findViewById(R.id.radio_CheddapeñoRoll);
        RadioButton radioCabraRoll = bottomSheetView.findViewById(R.id.radio_CabraRoll);
        RadioButton radioFinizzima = bottomSheetView.findViewById(R.id.radio_Finizzima);
        RadioButton radioPan = bottomSheetView.findViewById(R.id.radio_Pan);
        RadioButton radioVeggiThinCrust = bottomSheetView.findViewById(R.id.radio_VeggiThinCrust);

        radioOriginal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarMasa(bottomSheetView);
            }
        });

        radioRollRoulette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarMasa(bottomSheetView);
            }
        });

        radioMozzarellaRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarMasa(bottomSheetView);
            }
        });

        radioCheddapeñoRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarMasa(bottomSheetView);
            }
        });

        radioCabraRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarMasa(bottomSheetView);
            }
        });

        radioFinizzima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarMasa(bottomSheetView);
            }
        });

        radioPan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarMasa(bottomSheetView);
            }
        });

        radioVeggiThinCrust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarMasa(bottomSheetView);
            }
        });

        actualizarMasa(bottomSheetView);

    }

    private void modificarMasa(View bottomSheetView) {

        RadioGroup radioGroupMasa = bottomSheetView.findViewById(R.id.radioGroupMasa);
        RadioButton radioOriginal = bottomSheetView.findViewById(R.id.radio_Original);
        RadioButton radioRollRoulette = bottomSheetView.findViewById(R.id.radio_RollRoulette);
        RadioButton radioMozzarellaRoll = bottomSheetView.findViewById(R.id.radio_MozzarellaRoll);
        RadioButton radioCheddapeñoRoll = bottomSheetView.findViewById(R.id.radio_CheddapeñoRoll);
        RadioButton radioCabraRoll = bottomSheetView.findViewById(R.id.radio_CabraRoll);
        RadioButton radioFinizzima = bottomSheetView.findViewById(R.id.radio_Finizzima);
        RadioButton radioPan = bottomSheetView.findViewById(R.id.radio_Pan);
        RadioButton radioVeggiThinCrust = bottomSheetView.findViewById(R.id.radio_VeggiThinCrust);

        if(radioGroupMasa.getCheckedRadioButtonId()==radioOriginal.getId()) pizza.setMasaPizza(MasaPizza.Original);

        else if(radioGroupMasa.getCheckedRadioButtonId()==radioRollRoulette.getId()) pizza.setMasaPizza(MasaPizza.Roll_Roulette);

        else if(radioGroupMasa.getCheckedRadioButtonId()==radioMozzarellaRoll.getId()) pizza.setMasaPizza(MasaPizza.Mozzarella_Roll);

        else if(radioGroupMasa.getCheckedRadioButtonId()==radioCheddapeñoRoll.getId()) pizza.setMasaPizza(MasaPizza.Cheddapeño_Roll);

        else if(radioGroupMasa.getCheckedRadioButtonId()==radioCabraRoll.getId()) pizza.setMasaPizza(MasaPizza.Cabra_Roll);

        else if(radioGroupMasa.getCheckedRadioButtonId()==radioFinizzima.getId()) pizza.setMasaPizza(MasaPizza.Finizzima);

        else if(radioGroupMasa.getCheckedRadioButtonId()==radioPan.getId()) pizza.setMasaPizza(MasaPizza.Pan);

        else if(radioGroupMasa.getCheckedRadioButtonId()==radioVeggiThinCrust.getId()) pizza.setMasaPizza(MasaPizza.Veggi_Thin_Crust);

        actualizarMasa(bottomSheetView);

    }

    private void actualizarMasa(View bottomSheetView) {

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

        mostrarInformacionEnUI(pizza);

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

        RadioButton radioSinSalsa = bottomSheetView.findViewById(R.id.radio_SinSalsa);
        RadioButton radioSalsaBBQOriginal = bottomSheetView.findViewById(R.id.radio_SalsaBBQOriginal);
        RadioButton radioSalsaRancheraBBQ = bottomSheetView.findViewById(R.id.radio_SalsaRancheraBBQ);
        RadioButton radioSalsaTomate = bottomSheetView.findViewById(R.id.radio_SalsaTomate);
        RadioButton radioCremaFresca = bottomSheetView.findViewById(R.id.radio_CremaFresca);
        RadioButton radioCremaFrescaBarbacoa = bottomSheetView.findViewById(R.id.radio_CremaFrescaBarbacoa);
        RadioButton radioSalsaBourbon = bottomSheetView.findViewById(R.id.radio_SalsaBourbon);
        RadioButton radioSalsaBarbacoaTexas = bottomSheetView.findViewById(R.id.radio_SalsaBarbacoaTexas);
        RadioButton radioSalsaCarbonaraMornay = bottomSheetView.findViewById(R.id.radio_SalsaCarbonaraMornay);

        radioSinSalsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarSalsa(bottomSheetView);
            }
        });

        radioSalsaBBQOriginal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarSalsa(bottomSheetView);
            }
        });

        radioSalsaRancheraBBQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarSalsa(bottomSheetView);
            }
        });

        radioSalsaTomate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarSalsa(bottomSheetView);
            }
        });

        radioCremaFresca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarSalsa(bottomSheetView);
            }
        });

        radioCremaFrescaBarbacoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarSalsa(bottomSheetView);
            }
        });

        radioSalsaBourbon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarSalsa(bottomSheetView);
            }
        });

        radioSalsaBarbacoaTexas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarSalsa(bottomSheetView);
            }
        });

        radioSalsaCarbonaraMornay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarSalsa(bottomSheetView);
            }
        });

        actualizarSalsa(bottomSheetView);

    }

    private void modificarSalsa(View bottomSheetView) {

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

        if(radioGroupSalsa.getCheckedRadioButtonId()==radioSinSalsa.getId()) pizza.setSalsaPizza(SalsaPizza.Sin_Salsa);

        else if(radioGroupSalsa.getCheckedRadioButtonId()==radioSalsaBBQOriginal.getId()) pizza.setSalsaPizza(SalsaPizza.Salsa_BBQ_Original);

        else if(radioGroupSalsa.getCheckedRadioButtonId()==radioSalsaRancheraBBQ.getId()) pizza.setSalsaPizza(SalsaPizza.Salsa_Ranchera_BBQ);

        else if(radioGroupSalsa.getCheckedRadioButtonId()==radioSalsaTomate.getId()) pizza.setSalsaPizza(SalsaPizza.Salsa_Tomate);

        else if(radioGroupSalsa.getCheckedRadioButtonId()==radioCremaFresca.getId()) pizza.setSalsaPizza(SalsaPizza.Crema_Fresca);

        else if(radioGroupSalsa.getCheckedRadioButtonId()==radioCremaFrescaBarbacoa.getId()) pizza.setSalsaPizza(SalsaPizza.Crema_Fresca_Barbacoa);

        else if(radioGroupSalsa.getCheckedRadioButtonId()==radioSalsaBourbon.getId()) pizza.setSalsaPizza(SalsaPizza.Salsa_Bourbon);

        else if(radioGroupSalsa.getCheckedRadioButtonId()==radioSalsaBarbacoaTexas.getId()) pizza.setSalsaPizza(SalsaPizza.Salsa_Barbacoa_Texas);

        else if(radioGroupSalsa.getCheckedRadioButtonId()==radioSalsaCarbonaraMornay.getId()) pizza.setSalsaPizza(SalsaPizza.Salsa_Carbonara_Mornay);

        actualizarSalsa(bottomSheetView);

    }

    private void actualizarSalsa(View bottomSheetView) {

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

        mostrarInformacionEnUI(pizza);

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

        RadioButton radio_SinQueso = bottomSheetView.findViewById(R.id.radio_SinQueso);
        RadioButton radio_QuesoMozzarella = bottomSheetView.findViewById(R.id.radio_QuesoMozzarella);
        RadioButton radio_VeggiCheeseViolife = bottomSheetView.findViewById(R.id.radio_VeggiCheeseViolife);

        Button btnMenosCantidadQuesoPizza = bottomSheetView.findViewById(R.id.btnMenosCantidadQuesoPizza);
        Button btnMasCantidadQuesoPizza = bottomSheetView.findViewById(R.id.btnMasCantidadQuesoPizza);

        radio_SinQueso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarQueso(bottomSheetView);
            }
        });

        radio_QuesoMozzarella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarQueso(bottomSheetView);
            }
        });

        radio_VeggiCheeseViolife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarQueso(bottomSheetView);
            }
        });

        btnMenosCantidadQuesoPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadQueso(bottomSheetView, false);
            }
        });

        btnMasCantidadQuesoPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadQueso(bottomSheetView, true);
            }
        });

        actualizarQueso(bottomSheetView);

    }

    private void modificarQueso(View bottomSheetView) {

        RadioGroup radioGroupQueso = bottomSheetView.findViewById(R.id.radioGroupQueso);
        RadioButton radio_SinQueso = bottomSheetView.findViewById(R.id.radio_SinQueso);
        RadioButton radio_QuesoMozzarella = bottomSheetView.findViewById(R.id.radio_QuesoMozzarella);
        RadioButton radio_VeggiCheeseViolife = bottomSheetView.findViewById(R.id.radio_VeggiCheeseViolife);

        Button btnMenosCantidadQuesoPizza = bottomSheetView.findViewById(R.id.btnMenosCantidadQuesoPizza);
        Button btnMasCantidadQuesoPizza = bottomSheetView.findViewById(R.id.btnMasCantidadQuesoPizza);

        if(radioGroupQueso.getCheckedRadioButtonId()==radio_SinQueso.getId()) {
            pizza.setQuesoPizza(QuesoPizza.Sin_Queso);
            btnMasCantidadQuesoPizza.setEnabled(false);
            btnMenosCantidadQuesoPizza.setEnabled(false);
        }

        else if(radioGroupQueso.getCheckedRadioButtonId()==radio_QuesoMozzarella.getId()) {
            pizza.setQuesoPizza(QuesoPizza.Queso_Mozzarella);
            btnMasCantidadQuesoPizza.setEnabled(true);
            btnMenosCantidadQuesoPizza.setEnabled(true);
        }

        else if(radioGroupQueso.getCheckedRadioButtonId()==radio_VeggiCheeseViolife.getId()) {
            pizza.setQuesoPizza(QuesoPizza.VeggiCheese_Violife);
            btnMasCantidadQuesoPizza.setEnabled(true);
            btnMenosCantidadQuesoPizza.setEnabled(true);
        }

        actualizarQueso(bottomSheetView);

    }

    private void modificarCantidadQueso(View bottomSheetView, boolean sumar) {

        if(sumar) {

            if(pizza.getQuesoPizza().getCantidadQueso()<2) {

                int cantidad = pizza.getQuesoPizza().getCantidadQueso();
                pizza.getQuesoPizza().setCantidadQueso(cantidad+1);

            }

        }

        else {

            if(pizza.getQuesoPizza().getCantidadQueso()>0) {

                int cantidad = pizza.getQuesoPizza().getCantidadQueso();
                pizza.getQuesoPizza().setCantidadQueso(cantidad-1);

            }

        }

        actualizarQueso(bottomSheetView);

    }

    private void actualizarQueso(View bottomSheetView) {

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

        mostrarInformacionEnUI(pizza);

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

        Button btnMenosAceitunas = bottomSheetView.findViewById(R.id.btnMenosAceitunas);
        Button btnMasAceitunas = bottomSheetView.findViewById(R.id.btnMasAceitunas);
        Button btnMenosAnchoas = bottomSheetView.findViewById(R.id.btnMenosAnchoas);
        Button btnMasAnchoas = bottomSheetView.findViewById(R.id.btnMasAnchoas);
        Button btnMenosAtun = bottomSheetView.findViewById(R.id.btnMenosAtun);
        Button btnMasAtun = bottomSheetView.findViewById(R.id.btnMasAtun);
        Button btnMenosBacon = bottomSheetView.findViewById(R.id.btnMenosBacon);
        Button btnMasBacon = bottomSheetView.findViewById(R.id.btnMasBacon);
        Button btnMenosBaconCrispy = bottomSheetView.findViewById(R.id.btnMenosBaconCrispy);
        Button btnMasBaconCrispy = bottomSheetView.findViewById(R.id.btnMasBaconCrispy);
        Button btnMenosCebolla = bottomSheetView.findViewById(R.id.btnMenosCebolla);
        Button btnMasCebolla = bottomSheetView.findViewById(R.id.btnMasCebolla);
        Button btnMenosCebollaCaramelizada = bottomSheetView.findViewById(R.id.btnMenosCebollaCaramelizada);
        Button btnMasCebollaCaramelizada = bottomSheetView.findViewById(R.id.btnMasCebollaCaramelizada);
        Button btnMenosChampinon = bottomSheetView.findViewById(R.id.btnMenosChampinon);
        Button btnMasChampinon = bottomSheetView.findViewById(R.id.btnMasChampinon);
        Button btnMenosMaiz = bottomSheetView.findViewById(R.id.btnMenosMaiz);
        Button btnMasMaiz = bottomSheetView.findViewById(R.id.btnMasMaiz);
        Button btnMenosPeperoni = bottomSheetView.findViewById(R.id.btnMenosPeperoni);
        Button btnMasPeperoni = bottomSheetView.findViewById(R.id.btnMasPeperoni);
        Button btnMenosPimiento = bottomSheetView.findViewById(R.id.btnMenosPimiento);
        Button btnMasPimiento = bottomSheetView.findViewById(R.id.btnMasPimiento);
        Button btnMenosPina = bottomSheetView.findViewById(R.id.btnMenosPina);
        Button btnMasPina = bottomSheetView.findViewById(R.id.btnMasPina);
        Button btnMenosPolloBuffalo = bottomSheetView.findViewById(R.id.btnMenosPolloBuffalo);
        Button btnMasPolloBuffalo = bottomSheetView.findViewById(R.id.btnMasPolloBuffalo);
        Button btnMenosPolloParrilla = bottomSheetView.findViewById(R.id.btnMenosPolloParrilla);
        Button btnMasPolloParrilla = bottomSheetView.findViewById(R.id.btnMasPolloParrilla);
        Button btnMenosPulledPork = bottomSheetView.findViewById(R.id.btnMenosPulledPork);
        Button btnMasPulledPork = bottomSheetView.findViewById(R.id.btnMasPulledPork);
        Button btnMenosQuesoCheddarRojo = bottomSheetView.findViewById(R.id.btnMenosQuesoCheddarRojo);
        Button btnMasQuesoCheddarRojo = bottomSheetView.findViewById(R.id.btnMasQuesoCheddarRojo);
        Button btnMenosQuesoParmesano = bottomSheetView.findViewById(R.id.btnMenosQuesoParmesano);
        Button btnMasQuesoParmesano = bottomSheetView.findViewById(R.id.btnMasQuesoParmesano);
        Button btnMenosTernera = bottomSheetView.findViewById(R.id.btnMenosTernera);
        Button btnMasTernera = bottomSheetView.findViewById(R.id.btnMasTernera);
        Button btnMenosTomate = bottomSheetView.findViewById(R.id.btnMenosTomate);
        Button btnMasTomate = bottomSheetView.findViewById(R.id.btnMasTomate);
        Button btnMenosVegeroni = bottomSheetView.findViewById(R.id.btnMenosVegeroni);
        Button btnMasVegeroni = bottomSheetView.findViewById(R.id.btnMasVegeroni);
        Button btnMenosVeggichicken = bottomSheetView.findViewById(R.id.btnMenosVeggichicken);
        Button btnMasVeggichicken = bottomSheetView.findViewById(R.id.btnMasVeggichicken);
        Button btnMenosYork = bottomSheetView.findViewById(R.id.btnMenosYork);
        Button btnMasYork = bottomSheetView.findViewById(R.id.btnMasYork);

        btnMenosAceitunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosAceitunas);
            }
        });

        btnMasAceitunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasAceitunas);
            }
        });

        btnMenosAnchoas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosAnchoas);
            }
        });

        btnMasAnchoas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasAnchoas);
            }
        });

        btnMenosAtun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosAtun);
            }
        });

        btnMasAtun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasAtun);
            }
        });

        btnMenosBacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosBacon);
            }
        });

        btnMasBacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasBacon);
            }
        });

        btnMenosBaconCrispy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosBaconCrispy);
            }
        });

        btnMasBaconCrispy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasBaconCrispy);
            }
        });

        btnMenosCebolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosCebolla);
            }
        });

        btnMasCebolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasCebolla);
            }
        });

        btnMenosCebollaCaramelizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosCebollaCaramelizada);
            }
        });

        btnMasCebollaCaramelizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasCebollaCaramelizada);
            }
        });

        btnMenosChampinon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosChampinon);
            }
        });

        btnMasChampinon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasChampinon);
            }
        });

        btnMenosMaiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosMaiz);
            }
        });

        btnMasMaiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasMaiz);
            }
        });

        btnMenosPeperoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosPeperoni);
            }
        });

        btnMasPeperoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasPeperoni);
            }
        });

        btnMenosPimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosPimiento);
            }
        });

        btnMasPimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasPimiento);
            }
        });

        btnMenosPina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosPina);
            }
        });

        btnMasPina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasPina);
            }
        });

        btnMenosPolloBuffalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosPolloBuffalo);
            }
        });

        btnMasPolloBuffalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasPolloBuffalo);
            }
        });

        btnMenosPolloParrilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosPolloParrilla);
            }
        });

        btnMasPolloParrilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasPolloParrilla);
            }
        });

        btnMenosPulledPork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosPulledPork);
            }
        });

        btnMasPulledPork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasPulledPork);
            }
        });

        btnMenosQuesoCheddarRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosQuesoCheddarRojo);
            }
        });

        btnMasQuesoCheddarRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasQuesoCheddarRojo);
            }
        });

        btnMenosQuesoParmesano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosQuesoParmesano);
            }
        });

        btnMasQuesoParmesano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasQuesoParmesano);
            }
        });

        btnMenosTernera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosTernera);
            }
        });

        btnMasTernera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasTernera);
            }
        });

        btnMenosTomate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosTomate);
            }
        });

        btnMasTomate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasTomate);
            }
        });

        btnMenosVegeroni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosVegeroni);
            }
        });

        btnMasVegeroni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasVegeroni);
            }
        });

        btnMenosVeggichicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosVeggichicken);
            }
        });

        btnMasVeggichicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasVeggichicken);
            }
        });

        btnMenosYork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMenosYork);
            }
        });

        btnMasYork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCantidadIngrediente(bottomSheetView, btnMasYork);
            }
        });

        actualizarIngrediente(bottomSheetView);

    }

    private void modificarCantidadIngrediente(View bottomSheetView, Button botonElegido) {

        Button btnMenosAceitunas = bottomSheetView.findViewById(R.id.btnMenosAceitunas);
        Button btnMasAceitunas = bottomSheetView.findViewById(R.id.btnMasAceitunas);
        Button btnMenosAnchoas = bottomSheetView.findViewById(R.id.btnMenosAnchoas);
        Button btnMasAnchoas = bottomSheetView.findViewById(R.id.btnMasAnchoas);
        Button btnMenosAtun = bottomSheetView.findViewById(R.id.btnMenosAtun);
        Button btnMasAtun = bottomSheetView.findViewById(R.id.btnMasAtun);
        Button btnMenosBacon = bottomSheetView.findViewById(R.id.btnMenosBacon);
        Button btnMasBacon = bottomSheetView.findViewById(R.id.btnMasBacon);
        Button btnMenosBaconCrispy = bottomSheetView.findViewById(R.id.btnMenosBaconCrispy);
        Button btnMasBaconCrispy = bottomSheetView.findViewById(R.id.btnMasBaconCrispy);
        Button btnMenosCebolla = bottomSheetView.findViewById(R.id.btnMenosCebolla);
        Button btnMasCebolla = bottomSheetView.findViewById(R.id.btnMasCebolla);
        Button btnMenosCebollaCaramelizada = bottomSheetView.findViewById(R.id.btnMenosCebollaCaramelizada);
        Button btnMasCebollaCaramelizada = bottomSheetView.findViewById(R.id.btnMasCebollaCaramelizada);
        Button btnMenosChampinon = bottomSheetView.findViewById(R.id.btnMenosChampinon);
        Button btnMasChampinon = bottomSheetView.findViewById(R.id.btnMasChampinon);
        Button btnMenosMaiz = bottomSheetView.findViewById(R.id.btnMenosMaiz);
        Button btnMasMaiz = bottomSheetView.findViewById(R.id.btnMasMaiz);
        Button btnMenosPeperoni = bottomSheetView.findViewById(R.id.btnMenosPeperoni);
        Button btnMasPeperoni = bottomSheetView.findViewById(R.id.btnMasPeperoni);
        Button btnMenosPimiento = bottomSheetView.findViewById(R.id.btnMenosPimiento);
        Button btnMasPimiento = bottomSheetView.findViewById(R.id.btnMasPimiento);
        Button btnMenosPina = bottomSheetView.findViewById(R.id.btnMenosPina);
        Button btnMasPina = bottomSheetView.findViewById(R.id.btnMasPina);
        Button btnMenosPolloBuffalo = bottomSheetView.findViewById(R.id.btnMenosPolloBuffalo);
        Button btnMasPolloBuffalo = bottomSheetView.findViewById(R.id.btnMasPolloBuffalo);
        Button btnMenosPolloParrilla = bottomSheetView.findViewById(R.id.btnMenosPolloParrilla);
        Button btnMasPolloParrilla = bottomSheetView.findViewById(R.id.btnMasPolloParrilla);
        Button btnMenosPulledPork = bottomSheetView.findViewById(R.id.btnMenosPulledPork);
        Button btnMasPulledPork = bottomSheetView.findViewById(R.id.btnMasPulledPork);
        Button btnMenosQuesoCheddarRojo = bottomSheetView.findViewById(R.id.btnMenosQuesoCheddarRojo);
        Button btnMasQuesoCheddarRojo = bottomSheetView.findViewById(R.id.btnMasQuesoCheddarRojo);
        Button btnMenosQuesoParmesano = bottomSheetView.findViewById(R.id.btnMenosQuesoParmesano);
        Button btnMasQuesoParmesano = bottomSheetView.findViewById(R.id.btnMasQuesoParmesano);
        Button btnMenosTernera = bottomSheetView.findViewById(R.id.btnMenosTernera);
        Button btnMasTernera = bottomSheetView.findViewById(R.id.btnMasTernera);
        Button btnMenosTomate = bottomSheetView.findViewById(R.id.btnMenosTomate);
        Button btnMasTomate = bottomSheetView.findViewById(R.id.btnMasTomate);
        Button btnMenosVegeroni = bottomSheetView.findViewById(R.id.btnMenosVegeroni);
        Button btnMasVegeroni = bottomSheetView.findViewById(R.id.btnMasVegeroni);
        Button btnMenosVeggichicken = bottomSheetView.findViewById(R.id.btnMenosVeggichicken);
        Button btnMasVeggichicken = bottomSheetView.findViewById(R.id.btnMasVeggichicken);
        Button btnMenosYork = bottomSheetView.findViewById(R.id.btnMenosYork);
        Button btnMasYork = bottomSheetView.findViewById(R.id.btnMasYork);

        if(botonElegido.getId()==btnMenosAceitunas.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Aceitunas") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasAceitunas.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Aceitunas") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosAnchoas.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Anchoas") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasAnchoas.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Anchoas") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosAtun.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Atun") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasAtun.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Atun") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosBacon.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Bacon") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasBacon.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Bacon") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosBaconCrispy.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Bacon Crispy") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasBaconCrispy.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Bacon Crispy") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosCebolla.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Cebolla") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasCebolla.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Cebolla") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosCebollaCaramelizada.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Cebolla Caramelizada") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasCebollaCaramelizada.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Cebolla Caramelizada") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosChampinon.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Champiñon") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasChampinon.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Champiñon") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosMaiz.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Maiz") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasMaiz.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Maiz") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosPeperoni.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Peperoni") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasPeperoni.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Peperoni") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosPimiento.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Pimiento") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasPimiento.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Pimiento") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosPina.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Piña") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasPina.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Piña") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosPolloBuffalo.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Pollo Buffalo") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasPolloBuffalo.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Pollo Buffalo") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosPolloParrilla.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Pollo Parrilla") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasPolloParrilla.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Pollo Parrilla") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosPulledPork.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Pulled Pork") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasPulledPork.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Pulled Pork") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosQuesoCheddarRojo.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Queso Cheddar Rojo") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasQuesoCheddarRojo.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Queso Cheddar Rojo") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosQuesoParmesano.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Queso Parmesano") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasQuesoParmesano.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Queso Parmesano") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosTernera.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Ternera") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasTernera.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Ternera") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosTomate.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Tomate") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasTomate.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Tomate") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosVegeroni.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Vegeroni") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasVegeroni.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Vegeroni") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosVeggichicken.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Veggi Chicken") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasVeggichicken.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("Veggi Chicken") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        else if(botonElegido.getId()==btnMenosYork.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("York") && ingrediente.getCantidadIngrediente()>0) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad-1);
                }
            });
        }

        else if(botonElegido.getId()==btnMasYork.getId()) {
            pizza.getListaIngredientes().forEach(ingrediente -> {
                if(ingrediente.getNombre().equals("York") && ingrediente.getCantidadIngrediente()<2) {
                    int cantidad = ingrediente.getCantidadIngrediente();
                    ingrediente.setCantidadIngrediente(cantidad+1);
                }
            });
        }

        actualizarIngrediente(bottomSheetView);

    }

    private void actualizarIngrediente(View bottomSheetView) {

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
                case "Anchoas":
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