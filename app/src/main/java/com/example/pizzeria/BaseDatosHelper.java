package com.example.pizzeria;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BaseDatosHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Pizzeria.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public BaseDatosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        copyDatabaseFromAssets();
    }
    public Cursor getAllDataFromTable(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + tableName + ";";
        return db.rawQuery(selectQuery, null);
    }

    private void copyDatabaseFromAssets() {
        // Ruta a la ubicación donde se verificará si la base de datos ya existe
        String outFileName = context.getDatabasePath(DATABASE_NAME).getPath();

        // Verifica si la base de datos ya existe
        if (new File(outFileName).exists()) {
            return; // La base de datos ya está presente, no es necesario copiarla
        }

        InputStream myInput = null;
        try {
            myInput = context.getAssets().open("Pizzeria.db");

            // Abre la base de datos vacía como salida
            OutputStream myOutput = new FileOutputStream(outFileName);

            // Transfiere los datos desde el archivo de entrada al archivo de salida
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Cierra los streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
