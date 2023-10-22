package com.example.pizzeria.pojos;

public class QuesoPizza {
    //Sin_Queso, Queso_Mozzarella, VeggiCheese_Violife
    // cantidadQueso    0 - 1 - 2
    private int cantidadQueso;
    private double sumaPrecio;
    private final double precio = 1.8;

    private QuesoPizza() {
    }


    public int getCantidadQueso() {
        return cantidadQueso;
    }

    public void setCantidadQueso(int cantidad) {
        this.cantidadQueso = cantidad;
        this.sumaPrecio = precio * cantidad;
    }

    public double getSumaPrecio() {
        return sumaPrecio;
    }

}
