package com.egiladmin.egiladmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GestionBD {

    public static final String CREAR_TABLA_RESIDENTES = "CREATE TABLE 'residentes' (" +
            "'rut' VARCHAR(45) PRIMARY KEY," +
            "'nombre' VARCHAR(45)," +
            "'apellido' VARCHAR(45)," +
            "'usuario' VARCHAR(45)," +
            "'password' VARCHAR(45)," +
            "'tipo' VARCHAR(45)" +
            ");";

    public static final String CREAR_TABLA_DEPARTAMENTOS = "CREATE TABLE 'departamentos' (" +
            "'numero' INTEGER PRIMARY KEY,  " +
            "'torre' VARCHAR(45),  " +
            "'estado' VARCHAR(45),  " +
            "'residentes_rut' INTEGER,  " +
            "FOREIGN KEY('residentes_rut') REFERENCES 'residentes'('rut')  " +
            ");";

    public static final String CREAR_TABLA_RESERVAS = "CREATE TABLE 'reservas' (  " +
            " 'idreserva' INTEGER PRIMARY KEY AUTOINCREMENT,  " +
            " 'fecha' DATE,  " +
            " 'hora' TIME,  " +
            " 'valor' INT,  " +
            " 'departamentos_numero' INTEGER,  " +
            " FOREIGN KEY('departamentos_numero') REFERENCES 'departamentos'('numero')  " +
            ");";

    public AsistenteBD asistenteBD;
    public SQLiteDatabase basedatos;

    public GestionBD(Context context) {
        asistenteBD = new AsistenteBD(context);
        basedatos = asistenteBD.getWritableDatabase();
    }

    public void insertarResidente(String rut, String nombre, String apellido, String usuario, String password, String tipo) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("rut", rut);
        contentValues.put("nombre", nombre);
        contentValues.put("apellido", apellido);
        contentValues.put("usuario", usuario);
        contentValues.put("password", password);
        contentValues.put("tipo", tipo);
        basedatos.insert("residentes", null, contentValues);

    }

    public List<Residente> leerResidentes() {
        int id;
        String rut, nombre, apellido, usuario, password, tipo;
        Cursor cursor = basedatos.rawQuery("select * from residentes", null);
        List<Residente> residentes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                rut = cursor.getString(0);
                nombre = cursor.getString(1);
                apellido = cursor.getString(2);
                usuario = cursor.getString(3);
                password = cursor.getString(4);
                tipo = cursor.getString(5);
                residentes.add(new Residente(rut, nombre, apellido, usuario, password, tipo));
            } while (cursor.moveToNext());
        }else {
            //Toast.makeText(getApplicationContext(), "No hay datos", Toast.LENGTH_SHORT).show();
        }
        return residentes;
    }

    public int eliminarResidente(String rut) {

        int cantidad =  basedatos.delete("residentes", "rut =" + rut, null);
        //Retorno de valor para validación de parametros en el activity
    return cantidad;
    }
    public int actualizarResidente(String rut, String nombre, String apellido, String usuario, String password, String tipo) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("apellido", apellido);
        contentValues.put("usuario", usuario);
        contentValues.put("password", password);
        contentValues.put("tipo", tipo);
        //
        //el metodo retorna un entero, la cantidad de elementos eliminados (cantidad)
        int cantidad = basedatos.update("residentes", contentValues, "rut=" + rut, null);
        //Retorno de valor para validación de parametros en el activity
        return cantidad;
    }

    public Cursor leerUnResidente(String rut) {
        Cursor cursor = basedatos.rawQuery("select * from residentes where rut =" + rut, null);
        return cursor;
    }
}
