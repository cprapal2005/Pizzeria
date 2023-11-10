package com.example.pizzeria.pojos;

public enum QuesoPizza {
    Sin_Queso(0), Queso_Mozzarella(0), VeggiCheese_Violife(0);
    private int cantidadQueso;
    private double sumaPrecio;
    private final double precio = 1.8;

    private QuesoPizza(int cantidad) {

        this.cantidadQueso = cantidad;
        setCantidadQueso(cantidad);

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
