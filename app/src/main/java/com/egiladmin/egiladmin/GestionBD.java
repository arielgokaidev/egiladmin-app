package com.egiladmin.egiladmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GestionBD {

    public static final String CREAR_TABLA_RESIDENTES = "CREATE TABLE `residentes` (" +
            "  `idresidente` INT NOT NULL," +
            "  `rut` VARCHAR(45) NULL," +
            "  `nombre` VARCHAR(45) NULL," +
            "  `apellido` VARCHAR(45) NULL," +
            "  `usuario` VARCHAR(45) NULL," +
            "  `password` VARCHAR(45) NULL," +
            "  `tipo` VARCHAR(45) NULL," +
            "  PRIMARY KEY (`idresidente`));";

    public static final String CREAR_TABLA_DEPARTAMENTOS = "CREATE TABLE 'departamentos' (" +
            "'iddepartamento' INT NOT NULL," +
            "'numero' VARCHAR(45)," +
            "'torre' VARCHAR(45)," +
            "'estado' VARCHAR(45)," +
            "'residentes_idresidente' INTEGER," +
            "PRIMARY KEY('iddepartamento')," +
            "FOREIGN KEY('residentes_idresidente') REFERENCES 'residentes' ('idresidente')" +
            ");";

    public static final String CREAR_TABLA_RESERVAS = "CREATE TABLE 'reservas' (" +
            "'idreserva' INT NOT NULL," +
            "'fecha' DATE," +
            "'hora' TIME," +
            "'valor' INT," +
            "'departamentos_iddepartamento' INTEGER," +
            "PRIMARY KEY('idreserva')," +
            "FOREIGN KEY('departamentos_iddepartamento') REFERENCES 'departamentos' ('iddepartamento')" +
            ");";

    public AsistenteBD asistenteBD;
    public SQLiteDatabase basedatos;

    public GestionBD(Context context) {
        asistenteBD = new AsistenteBD(context);
        basedatos = asistenteBD.getWritableDatabase();

    }

}
