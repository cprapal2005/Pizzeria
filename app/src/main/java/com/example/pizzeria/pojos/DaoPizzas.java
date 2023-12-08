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
    private ArrayList<Pizza> arrayPizzasDefault = null;

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

            arrayListPizzas.add(new Pizza(idPizza, nombre, descripcion, TamanoPizza.valueOf(tamanoPizza), MasaPizza.valueOf(masaPizza), QuesoPizza.valueOf(quesoPizza), SalsaPizza.valueOf(salsaPizza), DaoIngredientes.getStatic().getIngredientes(listaIngredientes), imagenId));
            cursor2.close();
            db.close();

        }

        cursor.close();

        if(this.arrayPizzasDefault==null) this.arrayPizzasDefault = arrayListPizzas;

        if(this.arrayPizzasDefault.size()!=arrayListPizzas.size()) this.arrayPizzasDefault = arrayListPizzas;

        return this.arrayPizzasDefault;

    }

    public ArrayList<Pizza> getPizzasFavoritas(BaseDatosHelper dbHelper, String usuario) {

        ArrayList<Pizza> pizzasFavoritas = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT Pizza.* FROM Pizza " +
                "INNER JOIN FavoritosUsuario ON Pizza.idPizza = FavoritosUsuario.idPizza " +
                "WHERE FavoritosUsuario.usuario = ?";

        Cursor cursor = db.rawQuery(query, new String[]{usuario});

        while (cursor.moveToNext()) {
            @SuppressLint("Range") int idPizza = (cursor.getInt(cursor.getColumnIndex("idPizza")));
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));
            @SuppressLint("Range") String tamanoPizza = cursor.getString(cursor.getColumnIndex("tamanoPizza"));
            @SuppressLint("Range") String masaPizza = cursor.getString(cursor.getColumnIndex("masaPizza"));
            @SuppressLint("Range") String salsaPizza = cursor.getString(cursor.getColumnIndex("salsaPizza"));
            @SuppressLint("Range") String quesoPizza = cursor.getString(cursor.getColumnIndex("quesoPizza"));
            @SuppressLint("Range") String imagenId = cursor.getString(cursor.getColumnIndex("imagenId"));

            SQLiteDatabase db2 = dbHelper.getWritableDatabase();

            String[] projection = {"Ingrediente.nombre"};
            String[] selectionArgs = { String.valueOf(idPizza) };

            Cursor cursor2 = db2.query("IngredientePizza INNER JOIN Ingrediente ON IngredientePizza.idIngrediente = Ingrediente.idIngrediente", projection, "IngredientePizza.idPizza = ? AND IngredientePizza.idIngrediente = Ingrediente.idIngrediente", selectionArgs, null, null, null);

            ArrayList<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();

            while (cursor2.moveToNext()) {
                @SuppressLint("Range") String nombreIngrediente = cursor2.getString(cursor2.getColumnIndex("nombre"));
                listaIngredientes.add(new Ingrediente(nombreIngrediente, 1));
            }

            pizzasFavoritas.add(new Pizza(idPizza, nombre, descripcion, TamanoPizza.valueOf(tamanoPizza), MasaPizza.valueOf(masaPizza), QuesoPizza.valueOf(quesoPizza), SalsaPizza.valueOf(salsaPizza), DaoIngredientes.getStatic().getIngredientes(listaIngredientes), imagenId));
            cursor2.close();
            db2.close();

        }

        cursor.close();
        db.close();
        return pizzasFavoritas;

    }

    public int getNuevoId(BaseDatosHelper dbHelper) {

        int idUltimaPizza = 0;

        SQLiteDatabase dbPizza = dbHelper.getReadableDatabase();

        // Consulta SQL para obtener la última pizza
        String query = "SELECT * FROM Pizza ORDER BY idPizza DESC LIMIT 1";

        Cursor cursor = dbPizza.rawQuery(query, null);

        // Verifica si hay al menos una fila
        if (cursor.moveToFirst()) {
            // Obtén los datos de la pizza desde el cursor
            @SuppressLint("Range") int idPizza = (cursor.getInt(cursor.getColumnIndex("idPizza")));
            idUltimaPizza = idPizza;
        }

        return idUltimaPizza;

    }

    public boolean insertarPizzaDefault(BaseDatosHelper dbHelper, Pizza pizza, boolean favorito, String usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("idPizza", pizza.getId());
        values.put("fecha", formatter.format(new java.util.Date()));

        long newRowId = db.insert("HistorialPizza", null, values);

        if (newRowId != -1) {

            if(favorito) {

                values = new ContentValues();
                values.put("usuario", usuario);
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

    public boolean insertarPizzaModificada(BaseDatosHelper dbHelper, Pizza pizza, boolean favorito, String usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int idUltimaPizza = 0;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        ContentValues values = new ContentValues();
        values.put("nombre", pizza.getNombre());
        values.put("descripcion", pizza.getDescripcion());
        values.put("tamanoPizza", pizza.getTamanoPizza().toString());
        values.put("masaPizza", pizza.getMasaPizza().toString());
        values.put("quesoPizza", pizza.getQuesoPizza().toString());
        values.put("salsaPizza", pizza.getSalsaPizza().toString());
        values.put("imagenId", pizza.getImagenResourceId());

        long newRowId = db.insert("Pizza", null, values);

        if (newRowId != -1) {

            idUltimaPizza = getNuevoId(dbHelper);

            for (int i = 0; i < pizza.getListaIngredientes().size(); i++) {

                if (pizza.getListaIngredientes().get(i).getCantidadIngrediente() > 0) {

                    values = new ContentValues();
                    values.put("idPizza", idUltimaPizza);
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
                values.put("usuario", usuario);
                values.put("idPizza", idUltimaPizza);
                values.put("fecha", formatter.format(new java.util.Date()));

                newRowId = db.insert("HistorialPizza", null, values);

                if (newRowId != -1) {

                    if (favorito) {

                        values = new ContentValues();
                        values.put("usuario", usuario);
                        values.put("idPizza", idUltimaPizza);
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
