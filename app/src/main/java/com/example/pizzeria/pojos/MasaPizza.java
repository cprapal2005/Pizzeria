package com.example.pizzeria.pojos;

public enum MasaPizza {
    Original("Original", "Para aquellos amantes del auténtico sabor de la pizza Almunia's", 0),
    Roll_Roulette("Roll Roulette", "Borde relleno de queso Mozarella, cabra y cheddapeño", 1.5),
    Mozzarella_Roll("Mozarella Roll", "Con delicioso borde relleno de queso", 1.5),
    Cheddapeño_Roll("Cheddapeño Roll", "Borde relleno de queso cheddar y jalapeño", 1.5),
    Cabra_Roll("Cabra & Roll", "Borde relleno de queso de cabra fundido", 1.5),
    Finizzima("Finizzima", "La masa más fina y crujiente de Domino's", 0),
    Pan("Pan", "Al más puro estilo Chicago", 0),
    Veggi_Thin_Crust("Veggi Thin Crust", "Masa vegana Fina y Crujiente", 0);

    private final String nombre;
    private final String info;
    private final double sumaPrecio;

    private MasaPizza(final String nombre, final String info, final double sumaPrecio) {
        this.nombre = nombre;
        this.info = info;
        this.sumaPrecio = sumaPrecio;
    }

    public String getNombre() { return nombre; }
    public String getInfo() { return info; }
    public double getSumaPrecio() { return sumaPrecio; }
}

