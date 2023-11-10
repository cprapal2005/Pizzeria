package com.example.pizzeria.pojos;

import java.util.ArrayList;
import java.util.List;

public class DaoIngredientes {

    private static DaoIngredientes dao;

    private DaoIngredientes() {

    }

    public static DaoIngredientes getStatic() {

        if(dao == null) dao = new DaoIngredientes();

        return dao;

    }

    public ArrayList<Ingrediente> getIngredientes(Ingrediente[] listaIngredientes) {

        ArrayList<Ingrediente> arrayIngredientes = new ArrayList<Ingrediente>();

        arrayIngredientes.add(new Ingrediente("Aceitunas", 0));
        arrayIngredientes.add(new Ingrediente("Anchoas", 0));
        arrayIngredientes.add(new Ingrediente("Atun", 0));
        arrayIngredientes.add(new Ingrediente("Bacon", 0));
        arrayIngredientes.add(new Ingrediente("Bacon Crispy", 0));
        arrayIngredientes.add(new Ingrediente("Cebolla", 0));
        arrayIngredientes.add(new Ingrediente("Cebolla Caramelizada", 0));
        arrayIngredientes.add(new Ingrediente("Champiñon", 0));
        arrayIngredientes.add(new Ingrediente("Maiz", 0));
        arrayIngredientes.add(new Ingrediente("Peperoni", 0));
        arrayIngredientes.add(new Ingrediente("Pimiento", 0));
        arrayIngredientes.add(new Ingrediente("Piña", 0));
        arrayIngredientes.add(new Ingrediente("Pollo Buffalo", 0));
        arrayIngredientes.add(new Ingrediente("Pollo Parrilla", 0));
        arrayIngredientes.add(new Ingrediente("Pulled Pork", 0));
        arrayIngredientes.add(new Ingrediente("Queso Cheddar Rojo", 0));
        arrayIngredientes.add(new Ingrediente("Queso Parmesano", 0));
        arrayIngredientes.add(new Ingrediente("Ternera", 0));
        arrayIngredientes.add(new Ingrediente("Tomate", 0));
        arrayIngredientes.add(new Ingrediente("Vegeroni", 0));
        arrayIngredientes.add(new Ingrediente("Veggi Chicken", 0));
        arrayIngredientes.add(new Ingrediente("York", 0));

        arrayIngredientes.forEach(ingrediente -> {

            for (Ingrediente ingredienteLista : listaIngredientes) {

                if(ingrediente.getNombre().equals(ingredienteLista.getNombre())) ingrediente.setCantidadIngrediente(ingredienteLista.getCantidadIngrediente());

            }

        });

        return  arrayIngredientes;

    }

}
