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

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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
                // Se ejecuta cuando se deselecciona un TabItem
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

    public void cambiarActivity() {

        //startActivity(new Intent(ActivityPizzas.this, MainActivity.class));
        //finish();

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