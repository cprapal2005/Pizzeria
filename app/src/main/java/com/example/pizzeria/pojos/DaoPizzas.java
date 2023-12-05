package com.example.pizzeria.pojos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pizzeria.BaseDatosHelper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DaoPizzas {

    private static DaoPizzas dao;

    private DaoPizzas() {

    }

    public static DaoPizzas getStatic() {

        if(dao == null) dao = new DaoPizzas();

        return dao;

    }

    public ArrayList<Pizza> getPizzas(BaseDatosHelper dbHelper) {

        ArrayList<Pizza> arrayListPizzas = new ArrayList<Pizza>();

        Cursor cursor = dbHelper.getAllDataFromTable("Pizza");

        while (cursor.moveToNext()) {
            @SuppressLint("Range") int idPizza = (cursor.getInt(cursor.getColumnIndex("idPizza")));
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));
            @SuppressLint("Range") String tamanoPizza = cursor.getString(cursor.getColumnIndex("tamanoPizza"));
            @SuppressLint("Range") String masaPizza = cursor.getString(cursor.getColumnIndex("masaPizza"));
            @SuppressLint("Range") String salsaPizza = cursor.getString(cursor.getColumnIndex("salsaPizza"));
            @SuppressLint("Range") String quesoPizza = cursor.getString(cursor.getColumnIndex("quesoPizza"));
            @SuppressLint("Range") String imagenId = cursor.getString(cursor.getColumnIndex("imagenId"));

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String[] projection = {"Ingrediente.nombre"};
            String[] selectionArgs = { String.valueOf(idPizza) };

            Cursor cursor2 = db.query("IngredientePizza INNER JOIN Ingrediente ON IngredientePizza.idIngrediente = Ingrediente.idIngrediente", projection, "IngredientePizza.idPizza = ? AND IngredientePizza.idIngrediente = Ingrediente.idIngrediente", selectionArgs, null, null, null);

            ArrayList<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();

            while (cursor2.moveToNext()) {
                @SuppressLint("Range") String nombreIngrediente = cursor2.getString(cursor2.getColumnIndex("nombre"));
                listaIngredientes.add(new Ingrediente(nombreIngrediente, 1));
            }

            arrayListPizzas.add(new Pizza(nombre, descripcion, TamanoPizza.valueOf(tamanoPizza), MasaPizza.valueOf(masaPizza), QuesoPizza.valueOf(quesoPizza), SalsaPizza.valueOf(salsaPizza), DaoIngredientes.getStatic().getIngredientes(listaIngredientes), imagenId));
            cursor2.close();
            db.close();

        }

        cursor.close();
        dbHelper.close();

        return arrayListPizzas;

    }

    public boolean insertarPizzaDefault(BaseDatosHelper dbHelper, Pizza pizza, boolean favorito) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        ContentValues values = new ContentValues();
        values.put("usuario", "christian");
        values.put("idPizza", pizza.getId());
        values.put("fecha", formatter.format(new java.util.Date()));

        long newRowId = db.insert("HistorialPizza", null, values);

        if (newRowId != -1) {

            if(favorito) {

                values = new ContentValues();
                values.put("usuario", "christian");
                values.put("idPizza", pizza.getId());
                values.put("nombre", pizza.getNombre());

                newRowId = db.insert("FavoritosUsuario", null, values);

                if (newRowId != -1) {

                    db.close();
                    return true;

                }

                else {
                    db.close();
                    return  false;
                }

            }

            else {
                db.close();
                return  true;
            }

        } else {
            db.close();
            return  false;
        }

    }

    public boolean insertarPizzaModificada(BaseDatosHelper dbHelper, Pizza pizza, boolean favorito) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        ContentValues values = new ContentValues();
        values.put("nombre", pizza.getNombre());
        values.put("descripcion", pizza.getDescripcion());
        values.put("tamanoPizza", pizza.getTamanoPizza().getNombre());
        values.put("masaPizza", pizza.getMasaPizza().getNombre());
        values.put("quesoPizza", pizza.getQuesoPizza().getNombre());
        values.put("salsaPizza", pizza.getSalsaPizza().getNombre());
        values.put("imagenId", pizza.getImagenResourceId());

        long newRowId = db.insert("Pizza", null, values);

        if (newRowId != -1) {

            for (int i = 0; i < pizza.getListaIngredientes().size(); i++) {

                if (pizza.getListaIngredientes().get(i).getCantidadIngrediente() > 0) {

                    values = new ContentValues();
                    values.put("idPizza", pizza.getId());
                    values.put("idIngrediente", (i+1));
                    values.put("cantidadIngrediente", pizza.getListaIngredientes().get(i).getCantidadIngrediente());

                    newRowId = db.insert("IngredientePizza", null, values);

                    if (newRowId == -1) {
                        db.close();
                        return false;
                    }
                }
            }

            if (newRowId != -1) {

                values = new ContentValues();
                values.put("usuario", "christian");
                values.put("idPizza", pizza.getId());
                values.put("fecha", formatter.format(new java.util.Date()));

                newRowId = db.insert("HistorialPizza", null, values);

                if (newRowId != -1) {

                    if (favorito) {

                        values = new ContentValues();
                        values.put("usuario", "christian");
                        values.put("idPizza", pizza.getId());
                        values.put("nombre", pizza.getNombre());

                        newRowId = db.insert("FavoritosUsuario", null, values);

                        if (newRowId != -1) {

                            db.close();
                            return true;

                        } else {
                            db.close();
                            return false;
                        }

                    } else {
                        db.close();
                        return true;
                    }

                } else {
                    db.close();
                    return false;
                }

            }

        } else {
            db.close();
            return false;
        }

        return true;

    }

}
