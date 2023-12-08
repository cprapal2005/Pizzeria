package com.example.pizzeria;

public class Servicio {

    private static Servicio servicio;
    private String usuarioLogueado = "";
    private BaseDatosHelper dbHelper = null;

    private Servicio() {
    }

    public static Servicio getStatic() {

        if(servicio == null) servicio = new Servicio();

        return servicio;

    }

    public void setDbHelper(BaseDatosHelper db) {
        this.dbHelper = db;
    }
    public void setUsuarioLogueado(String usuario) {
        this.usuarioLogueado = usuario;
    }
    public BaseDatosHelper getDbHelper() {
        return this.dbHelper;
    }
    public String getUsuarioLogueado() {
        return this.usuarioLogueado;
    }

}
