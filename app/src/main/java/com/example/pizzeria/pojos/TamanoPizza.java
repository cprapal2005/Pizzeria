package com.example.pizzeria.pojos;

public enum TamanoPizza {
    Mediana("Mediana", 14.5),
    Familiar("Familiar", 20.5),
    Individual("Individual", 12.5);

    private final String nombre;
    private final double sumaPrecio;

    private TamanoPizza(final String nombre, final double sumaPrecio) {
        this.nombre = nombre;
        this.sumaPrecio = sumaPrecio;
    }

    public String getNombre() { return nombre; }
    public double getSumaPrecio() { return sumaPrecio; }
}
