package com.example.pizzeria.pojos;

public class Ingrediente {

    private String nombre;
    private int cantidadIngrediente; // 0 - 1 - 2
    private double sumaPrecio;
    private final double precio = 2.29;

    public Ingrediente(String nombre, int cantidad) {

        this.nombre = nombre;
        setCantidadIngrediente(cantidad);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(int cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
        this.sumaPrecio = precio * cantidadIngrediente;
    }

    public double getSumaPrecio() {
        return sumaPrecio;
    }
}
