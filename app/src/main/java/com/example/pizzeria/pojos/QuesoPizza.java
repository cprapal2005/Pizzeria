package com.example.pizzeria.pojos;

public enum QuesoPizza {
    Sin_Queso("Sin queso", 0), Queso_Mozzarella("Queso 100% mozzarella", 0), VeggiCheese_Violife("VeggiCheese Violife", 0);

    private final String nombre;
    private int cantidadQueso;
    private double sumaPrecio;
    private final double precio = 1.8;

    private QuesoPizza(final String nombre, int cantidad) {

        this.nombre=nombre;
        this.cantidadQueso = cantidad;
        setCantidadQueso(cantidad);

    }

    public String getNombre() { return nombre; }

    public int getCantidadQueso() {
        return cantidadQueso;
    }

    public void setCantidadQueso(int cantidad) {
        this.cantidadQueso = cantidad;
        this.sumaPrecio = precio * cantidad+1;
    }

    public double getSumaPrecio() {
        return sumaPrecio;
    }

}
