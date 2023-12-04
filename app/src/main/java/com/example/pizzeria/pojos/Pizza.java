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
    private String imagenId;

    public Pizza(String nombre, String descripcion, TamanoPizza tamanoPizza, MasaPizza masaPizza, QuesoPizza quesoPizza, SalsaPizza salsaPizza, ArrayList<Ingrediente> listaIngredientes, String imagenId) {
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

    public String getImagenResourceId() { return imagenId;}

    public void setTamanoPizza(TamanoPizza tamanoPizza) {
        this.tamanoPizza = tamanoPizza;
    }

    public void setMasaPizza(MasaPizza masaPizza) {
        this.masaPizza = masaPizza;
    }

    public void setQuesoPizza(QuesoPizza quesoPizza) {
        this.quesoPizza = quesoPizza;
    }

    public void setSalsaPizza(SalsaPizza salsaPizza) {
        this.salsaPizza = salsaPizza;
    }

    public double getPrecio() {

        double precioFinal = 0;

        precioFinal += this.tamanoPizza.getSumaPrecio();
        precioFinal += this.masaPizza.getSumaPrecio();
        precioFinal += this.quesoPizza.getSumaPrecio();

        precioFinal += listaIngredientes.stream().mapToDouble(Ingrediente::getSumaPrecio).sum();

        return precioFinal;

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
