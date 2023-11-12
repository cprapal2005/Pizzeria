package com.example.pizzeria.pojos;

import java.io.Serializable;
import java.util.ArrayList;

public class Pizza implements Serializable {

    private static int contador = 0;
    private int id;
    private String nombre;
    private String descripcion;
    private TamanoPizza tamanoPizza;
    private MasaPizza masaPizza;
    private QuesoPizza quesoPizza;
    private SalsaPizza salsaPizza;
    private ArrayList<Ingrediente> listaIngredientes;
    private int imagenId;

    public Pizza(String nombre, String descripcion, TamanoPizza tamanoPizza, MasaPizza masaPizza, QuesoPizza quesoPizza, SalsaPizza salsaPizza, ArrayList<Ingrediente> listaIngredientes, int imagenId) {
        this.id = contador++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tamanoPizza = tamanoPizza;
        this.masaPizza = masaPizza;
        this.quesoPizza = quesoPizza;
        this.salsaPizza = salsaPizza;
        this.listaIngredientes = listaIngredientes;
        this.imagenId = imagenId;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() { return descripcion; };

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

    public int getImagenResourceId() { return imagenId;}

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
