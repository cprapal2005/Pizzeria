package com.example.pizzeria.pojos;

import java.util.ArrayList;

public class Pizza {

    private static int contador = 0;
    private int id;
    private String nombre;
    private TamanoPizza tamanoPizza;
    private MasaPizza masaPizza;
    private QuesoPizza quesoPizza;
    private SalsaPizza salsaPizza;
    private ArrayList<Ingrediente> listaIngredientes;

    public Pizza(String nombre, TamanoPizza tamanoPizza, MasaPizza masaPizza, QuesoPizza quesoPizza, SalsaPizza salsaPizza, ArrayList<Ingrediente> listaIngredientes) {
        this.id = contador++;
        this.nombre = nombre;
        this.tamanoPizza = tamanoPizza;
        this.masaPizza = masaPizza;
        this.quesoPizza = quesoPizza;
        this.salsaPizza = salsaPizza;
        this.listaIngredientes = listaIngredientes;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TamanoPizza getTamanoPizza() {
        return tamanoPizza;
    }

    public MasaPizza getMasaPizza() {
        return masaPizza;
    }

    public QuesoPizza getQuesoPizza() {
        return quesoPizza;
    }

    public SalsaPizza getSalsaPizza() {
        return salsaPizza;
    }

    public ArrayList<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    @Override
    public String toString() {
        return "Pizza:" +
                "\nNombre = " + nombre +
                "\nTamano Pizza = " + tamanoPizza +
                "\nMasa Pizza = " + masaPizza +
                "\nQueso Pizza = " + quesoPizza +
                "\nSalsa Pizza = " + salsaPizza +
                "\nLista Ingredientes = " + listaIngredientes;
    }
}
