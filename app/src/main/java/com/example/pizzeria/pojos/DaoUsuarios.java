package com.example.pizzeria.pojos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pizzeria.BaseDatosHelper;

import java.util.ArrayList;

public class DaoUsuarios {

    private static DaoUsuarios dao;

    private DaoUsuarios() {

    }

    public static DaoUsuarios getStatic() {

        if(dao == null) dao = new DaoUsuarios();

        return dao;

    }

    public boolean loginUsuario(BaseDatosHelper dbHelper, String usuario, String contraseña) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {"Usuarios.usuario"};
        String[] selectionArgs = {usuario, contraseña};

        Cursor cursor = db.query("Usuarios", projection, "Usuarios.usuario = ? AND Usuarios.contraseña = ?", selectionArgs, null, null, null);

        if(cursor.moveToNext()) {
            db.close();
            cursor.close();
            return true;
        }

        else {
            db.close();
            cursor.close();
            return false;
        }

    }

    public boolean insertarUsuario(BaseDatosHelper dbHelper, String usuario, String contraseña) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("contraseña", contraseña);

        long newRowId = db.insert("Usuarios", null, values);

        if (newRowId != -1) {
            db.close();
            return true;
        } else {
            db.close();
            return  false;
        }
    }

}
