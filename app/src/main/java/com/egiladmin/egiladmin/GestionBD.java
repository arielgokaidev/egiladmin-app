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
            " 'fecha' VARCHAR(45),  " +
            " 'hora' VARCHAR(45),  " +
            " 'turno' VARCHAR(45),  " +
            " 'departamentos_numero' INTEGER,  " +
            " FOREIGN KEY('departamentos_numero') REFERENCES 'departamentos'('numero')  " +
            ");";

    public static final String INSERTAR_DATOS_RESIDENTE_1 = "INSERT INTO RESIDENTES VALUES " +
            "(" +
            "'12345678-9'," +
            "'Root'," +
            "'Root'," +
            "'root'," +
            "'123'," +
            "'Admin'" +
            ");";

    public static final String INSERTAR_DATOS_RESIDENTE_2 = "INSERT INTO RESIDENTES VALUES " +
            "("+
            "'16000000-0'," +
            "'Yirman'," +
            "'Sereño'," +
            "'yirman'," +
            "'123'," +
            "'Arrendatario'" +
            ");";

    public static final String INSERTAR_DATOS_DEPARTAMENTO_1 = "INSERT INTO DEPARTAMENTOS VALUES " +
            "("+
            "101," +
            "'A'," +
            "'Ocupado'," +
            "'16000000-0'" +
            ");";

    public static final String INSERTAR_DATOS_DEPARTAMENTO_2 = "INSERT INTO DEPARTAMENTOS VALUES " +
            "("+
            "151," +
            "'B'," +
            "'Vacaciones'," +
            "'17103342-K'" +
            ");";

    public AsistenteBD asistenteBD;
    public SQLiteDatabase basedatos;

    public GestionBD(Context context) {
        asistenteBD = new AsistenteBD(context);
        basedatos = asistenteBD.getWritableDatabase();
    }

    // RESIDENTES

    //METODO INSERT RESIDENTE
    public long insertarResidente(String rut, String nombre, String apellido, String usuario, String password, String tipo) {
        long codigo = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("rut", rut);
        contentValues.put("nombre", nombre);
        contentValues.put("apellido", apellido);
        contentValues.put("usuario", usuario);
        contentValues.put("password", password);
        contentValues.put("tipo", tipo);
        codigo = basedatos.insert("residentes", null, contentValues);
        return codigo;
    }

    // METODO SELECT * FROM RESIDENTES
    public ArrayList<Residente> leerResidentes() {
        String rut, nombre, apellido, usuario, password, tipo;
        Cursor cursor = basedatos.rawQuery("select * from residentes where usuario <> 'root'", null);
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
        Cursor cursor = basedatos.rawQuery("select rut from residentes where usuario <> 'root'", null);
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
    public long insertarDepartamento(int numero, String torre, String estado, String residentes_rut) {
        long codigo = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("numero", numero);
        contentValues.put("torre", torre);
        contentValues.put("estado", estado);
        contentValues.put("residentes_rut", residentes_rut);
        codigo = basedatos.insert("departamentos", null, contentValues);
        return codigo;
    }

    // METODO SELECT * FROM departamento WHERE numero
    public ArrayList<Departamento> leerDepartamento(int numero) {
        String torre, estado, residentes_rut;

        Cursor cursor = basedatos.rawQuery("select * from departamentos where numero =" + numero + "", null);
        ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
        if (cursor.moveToFirst()) {
            do {
                numero = cursor.getInt(0);
                torre = cursor.getString(1);
                estado = cursor.getString(2);
                residentes_rut = cursor.getString(3);
                Departamento departamento = new Departamento(numero, torre, estado, residentes_rut);
                departamentos.add(departamento);
            } while (cursor.moveToNext());
        }
        return departamentos;
    }

    // METODO SELECT * FROM DEPARTAMENTOS INNER JOIN RESIDENTES
    public ArrayList<Departamento> leerDepartamentos() {
        String torre, estado, residentes_rut, nombre, apellido, tipo;
        int numero;
        String query = "select numero, torre, estado, residentes_rut, nombre, apellido, tipo from departamentos inner join residentes on residentes.rut = departamentos.residentes_rut";
        Cursor cursor = basedatos.rawQuery(query, null);
        ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
        if (cursor.moveToFirst()) {
            do {
                numero = cursor.getInt(0);
                torre = cursor.getString(1);
                estado = cursor.getString(2);
                residentes_rut = cursor.getString(3);
                nombre = cursor.getString(4);
                apellido = cursor.getString(5);
                tipo = cursor.getString(6);
                Departamento departamento = new Departamento(numero, torre, estado, residentes_rut, nombre, apellido, tipo);
                departamentos.add(departamento);
            } while (cursor.moveToNext());
        }
        return departamentos;
    }
    // METODO UPDATE DEPARTAMENTOS
    public int actualizarDepartamentos(int numero, String torre, String estado) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("torre", torre);
        contentValues.put("estado", estado);
        //el metodo retorna un entero, la cantidad de elementos eliminados (cantidad)
        int cantidad = basedatos.update("departamentos", contentValues, "numero =" + numero, null);
        //Retorno de valor para validación de parametros en el activity
        return cantidad;
    }
    // METODO DELETE DEPARTAMETNO
    public int eliminarDepartamento(String numero) {
        int cantidad =  basedatos.delete("departamentos", "numero ='" + numero + "'", null);
        //Retorno de valor para validación de parametros en el activity
        return cantidad;
    }
    // METODO SELECT numero FROM deptos
    public ArrayList<Departamento> leerNumeros() {
        int numero;
        Cursor cursor = basedatos.rawQuery("select numero from departamentos", null);
        ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
        if (cursor.moveToFirst()) {
            do {
                numero = cursor.getInt(0);
                Departamento departamento = new Departamento(numero);
                departamentos.add(departamento);
            } while (cursor.moveToNext());
        }
        return departamentos;
    }

    //METODO INSERT RESERVA
    public long insertarReserva(String fecha, String hora, String turno, int departamentos_numero) {
        long codigo = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("fecha", fecha);
        contentValues.put("hora", hora);
        contentValues.put("turno", turno);
        contentValues.put("departamentos_numero", departamentos_numero);
        codigo = basedatos.insert("reservas", null, contentValues);
        return codigo;
    }

    // METODO SELECT * FROM RESERVAS
    public ArrayList<Reserva> leerReservas() {
        int id, departamento;
        String fecha, hora, turno, torre, nombre, apellido;
        String query = "select idreserva, fecha, hora, turno, departamentos_numero, torre, nombre, apellido from reservas " +
                "inner join departamentos on reservas.departamentos_numero = departamentos.numero " +
                "inner join residentes on departamentos.residentes_rut = residentes.rut";
        Cursor cursor = basedatos.rawQuery(query, null);
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(0);
                fecha = cursor.getString(1);
                hora = cursor.getString(2);
                turno = cursor.getString(3);
                departamento = cursor.getInt(4);
                torre = cursor.getString(5);
                nombre = cursor.getString(6);
                apellido = cursor.getString(7);
                Reserva reserva = new Reserva(id, fecha, hora, turno, departamento, torre, nombre, apellido);
                reservas.add(reserva);
            } while (cursor.moveToNext());
        }
        return reservas;
    }

    // METODO LOGIN
    public ArrayList<Residente> loginResidente(String usuario) {
        String query = "select usuario, password, nombre from residentes where usuario = '" + usuario + "'";
        Cursor cursor = basedatos.rawQuery(query, null);
        ArrayList<Residente> residentes = new ArrayList<Residente>();
        if (cursor.moveToFirst()) {
            do {
                usuario = cursor.getString(0);
                String password = cursor.getString(1);
                String nombre = cursor.getString(2);
                Residente residente = new Residente(usuario, password, nombre);
                residentes.add(residente);
            } while (cursor.moveToNext());
        }
        return residentes;
    }


}
