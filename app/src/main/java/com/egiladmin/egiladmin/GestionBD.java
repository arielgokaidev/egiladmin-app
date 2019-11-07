package com.egiladmin.egiladmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GestionBD {

    public static final String CREAR_TABLA_RESIDENTES = "CREATE TABLE 'residentes' (" +
            "'idresidente' INTEGER PRIMARY KEY AUTOINCREMENT," +
            "'rut' VARCHAR(45)," +
            "'nombre' VARCHAR(45)," +
            "'apellido' VARCHAR(45)," +
            "'usuario' VARCHAR(45)," +
            "'password' VARCHAR(45)," +
            "'tipo' VARCHAR(45)" +
            ");";

    public static final String CREAR_TABLA_DEPARTAMENTOS = "CREATE TABLE 'departamentos' (" +
            "'iddepartamento' INTEGER PRIMARY KEY AUTOINCREMENT,  " +
            "'numero' VARCHAR(45),  " +
            "'torre' VARCHAR(45),  " +
            "'estado' VARCHAR(45),  " +
            "'residentes_idresidente' INTEGER,  " +
            "FOREIGN KEY('residentes_idresidente') REFERENCES 'residentes'('idresidente')  " +
            ");";

    public static final String CREAR_TABLA_RESERVAS = "CREATE TABLE 'reservas' (  " +
            " 'idreserva' INTEGER PRIMARY KEY AUTOINCREMENT,  " +
            " 'fecha' DATE,  " +
            " 'hora' TIME,  " +
            " 'valor' INT,  " +
            " 'departamentos_iddepartamento' INTEGER,  " +
            " FOREIGN KEY('departamentos_iddepartamento') REFERENCES 'departamentos'('iddepartamento')  " +
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
    public void eliminarResidente(String rut) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("rut", rut);
        basedatos.delete("residentes", "rut=" + rut, null);

    }
}
