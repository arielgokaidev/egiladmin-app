package com.egiladmin.egiladmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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
            "'residentes_rut' VARCHAR(45),  " +
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

    public static final String INSERTAR_DATOS_RESIDENTE = "INSERT INTO RESIDENTES VALUES " +
            "(" +
            "'17103342-K'," +
            "'Ariel'," +
            "'Lizana'," +
            "'ariel'," +
            "'123'," +
            "'Dueño'" +
            "),"+
            "("+
            "'16xxxxxx-x'," +
            "'Yirman'," +
            "'Sereño'," +
            "'yirman'," +
            "'123'," +
            "'Arrendatario'" +
            ");";

    public AsistenteBD asistenteBD;
    public SQLiteDatabase basedatos;

    public GestionBD(Context context) {
        asistenteBD = new AsistenteBD(context);
        basedatos = asistenteBD.getWritableDatabase();
    }

    // RESIDENTES

    //METODO INSERT RESIDENTE
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

    // METODO SELECT * FROM RESIDENTES
    public ArrayList<Residente> leerResidentes() {
        String rut, nombre, apellido, usuario, password, tipo;
        Cursor cursor = basedatos.rawQuery("select * from residentes", null);
        ArrayList<Residente> residentes = new ArrayList<Residente>();
        if (cursor.moveToFirst()) {
            do {
                rut = cursor.getString(0);
                nombre = cursor.getString(1);
                apellido = cursor.getString(2);
                usuario = cursor.getString(3);
                password = cursor.getString(4);
                tipo = cursor.getString(5);
                Residente residente = new Residente(rut, nombre, apellido, usuario, password, tipo);
                residentes.add(residente);
            } while (cursor.moveToNext());
        }
        return residentes;
    }

    // METODO SELECT RUT FROM RESIDENTES
    public ArrayList<Residente> leerRuts() {
        String rut;
        Cursor cursor = basedatos.rawQuery("select rut from residentes", null);
        ArrayList<Residente> residentes = new ArrayList<Residente>();
        if (cursor.moveToFirst()) {
            do {
                rut = cursor.getString(0);
                Residente residente = new Residente(rut);
                residentes.add(residente);
            } while (cursor.moveToNext());
        }
        return residentes;
    }

    // METODO SELECT * FROM RESIDENTE WHERE RUT
    public ArrayList<Residente> leerResidente(String rut) {
        String nombre, apellido, usuario, password, tipo;
        Cursor cursor = basedatos.rawQuery("select * from residentes where rut ='" + rut + "'", null);
        ArrayList<Residente> residentes = new ArrayList<Residente>();
        if (cursor.moveToFirst()) {
            do {
                rut = cursor.getString(0);
                nombre = cursor.getString(1);
                apellido = cursor.getString(2);
                usuario = cursor.getString(3);
                password = cursor.getString(4);
                tipo = cursor.getString(5);
                Residente residente = new Residente(rut, nombre, apellido, usuario, password, tipo);
                residentes.add(residente);
            } while (cursor.moveToNext());
        }
        return residentes;
    }

    // METODO UPDATE RESIDENTE
    public int actualizarResidente(String rut, String nombre, String apellido, String usuario, String password, String tipo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("apellido", apellido);
        contentValues.put("usuario", usuario);
        contentValues.put("password", password);
        contentValues.put("tipo", tipo);
        //el metodo retorna un entero, la cantidad de elementos eliminados (cantidad)
        int cantidad = basedatos.update("residentes", contentValues, "rut='" + rut + "'", null);
        //Retorno de valor para validación de parametros en el activity
        return cantidad;
    }

    // METODO DELETE RESIDENTE
    public int eliminarResidente(String rut) {
        int cantidad =  basedatos.delete("residentes", "rut ='" + rut + "'", null);
        //Retorno de valor para validación de parametros en el activity
        return cantidad;
    }

    // DEPARTAMENTOS

    //METODO INSERT DEPARTAMENTO
    public void insertarDepartamento(String numero, String torre, String estado, String residentes_rut) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("numero", numero);
        contentValues.put("torre", torre);
        contentValues.put("estado", estado);
        contentValues.put("residentes_rut", residentes_rut);
        basedatos.insert("departamentos", null, contentValues);

    }


}
