package com.example.pizzeria;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzeria.pojos.Ingrediente;
import com.example.pizzeria.pojos.Pizza;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private List<Pizza> pizzaList;
    private Context context;

    public PizzaAdapter(Context context, List<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_favorita, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.textViewFavoritoNombrePizza.setText(pizza.getNombre());
        holder.textViewFavoritoDineroPizza.setText(pizza.getPrecio() + "€");
        holder.btnInfoPizzaFavorito.setOnClickListener(v -> {

            String informacionPizzaString = "Tamaño: " + pizza.getTamanoPizza().getNombre() + " " + pizza.getTamanoPizza().getSumaPrecio() + "€\n\n" +
                    "Masa: " + pizza.getMasaPizza().getNombre() + " " + pizza.getMasaPizza().getSumaPrecio() + "€\n\n" +
                    "Queso: " + pizza.getQuesoPizza().getNombre() + " | Cantidad " + pizza.getQuesoPizza().getCantidadQueso() + " | " + pizza.getQuesoPizza().getSumaPrecio() + "€\n\n" +
                    "Salsa: " + pizza.getSalsaPizza().getNombre() + "\n\n";

            informacionPizzaString += "Ingredientes: \n";

            for (Ingrediente ingrediente : pizza.getListaIngredientes()) {
                if(ingrediente.getCantidadIngrediente()>0) informacionPizzaString += ingrediente.getNombre() + ": " + ingrediente.getCantidadIngrediente() + " - " + ingrediente.getSumaPrecio() + "€\n";
            }

            holder.textViewInformacionPizzaFavorito.setText(informacionPizzaString);

            holder.rowFavoritoPizza3.setBackgroundColor(Color.TRANSPARENT);

            holder.rowFavoritoPizza3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

        });

        holder.btnPedirPizzaFavorito.setOnClickListener(v -> {

            Intent intent = new Intent(context, ActivityCesta.class);
            intent.putExtra("pizzaElegida", pizza);
            intent.putExtra("pizzaModificada", pizza);
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    static class PizzaViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewFavoritoNombrePizza;
        public TextView textViewFavoritoDineroPizza;
        public TextView textViewInformacionPizzaFavorito;
        public Button btnInfoPizzaFavorito;
        public Button btnPedirPizzaFavorito;
        public TableRow rowFavoritoPizza3;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFavoritoNombrePizza = itemView.findViewById(R.id.textViewFavoritoNombrePizza);
            textViewFavoritoDineroPizza = itemView.findViewById(R.id.textViewFavoritoDineroPizza);
            textViewInformacionPizzaFavorito = itemView.findViewById(R.id.textViewInformacionPizzaFavorito);
            btnInfoPizzaFavorito = itemView.findViewById(R.id.btnInfoPizzaFavorito);
            btnPedirPizzaFavorito = itemView.findViewById(R.id.btnPedirPizzaFavorito);
            rowFavoritoPizza3 = itemView.findViewById(R.id.rowFavoritoPizza3);
        }
    }
}
