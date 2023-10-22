package com.example.pizzeria.pojos;

public enum SalsaPizza {
    Sin_Salsa("Sin salsa"),
    Salsa_BBQ_Original("Salsa BBQ Original"),
    Salsa_Ranchera_BBQ("Salsa ranchera BBQ"),
    Salsa_Tomate("Salsa de tomate"),
    Crema_Fresca("Crema fresca"),
    Crema_Fresca_Barbacoa("Crema fresca y barbacoa"),
    Salsa_Bourbon("Salsa Bourbon (0% alcohol)"),
    Salsa_Barbacoa_Texas("Salsa barbacoa Texas"),
    Salsa_Carbonara_Mornay("Salsa carbonara Mornay");

    private final String nombre;

    private SalsaPizza(final String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

}
