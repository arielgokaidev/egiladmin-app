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

    public List<Residente> leerResidentes() {
        int id;
        String rut, nombre, apellido, usuario, password, tipo;
        Cursor cursor = basedatos.rawQuery("select * from residentes", null);
        List<Residente> residentes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(1);
                rut = cursor.getString(2);
                nombre = cursor.getString(3);
                apellido = cursor.getString(4);
                usuario = cursor.getString(5);
                password = cursor.getString(6);
                tipo = cursor.getString(7);
                residentes.add(new Residente(id, rut, nombre, apellido, usuario, password, tipo));
            } while (cursor.moveToNext());
        }else {
            //Toast.makeText(getApplicationContext(), "No hay datos", Toast.LENGTH_SHORT).show();
        }
        return residentes;
    }

    // Ocupa esta función para no modificar la original, me falta debuggearla
    public List<Residente> leer2Residentes() {
        int id;
        String rut, nombre, apellido, usuario, password, tipo;
        Cursor cursor = basedatos.rawQuery("select * from residentes", null);
        List<Residente> residentes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(1);
                rut = cursor.getString(2);
                nombre = cursor.getString(3);
                apellido = cursor.getString(4);
                usuario = cursor.getString(5);
                password = cursor.getString(6);
                tipo = cursor.getString(7);
                residentes.add(new Residente(id, rut, nombre, apellido, usuario, password, tipo));
            } while (cursor.moveToNext());
        }else {
            //Toast.makeText(getApplicationContext(), "No hay datos", Toast.LENGTH_SHORT).show();
        }
        return residentes;
    }

}
