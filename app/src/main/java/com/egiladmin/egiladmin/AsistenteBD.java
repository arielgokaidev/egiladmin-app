package com.egiladmin.egiladmin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AsistenteBD extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "egiladmin.sqlite";
    public static final int VERSION_ESQUEMA = 1;

    public AsistenteBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_ESQUEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(GestionBD.CREAR_TABLA_RESIDENTES);
        sqLiteDatabase.execSQL(GestionBD.CREAR_TABLA_DEPARTAMENTOS);
        sqLiteDatabase.execSQL(GestionBD.CREAR_TABLA_RESERVAS);
        sqLiteDatabase.execSQL(GestionBD.INSERTAR_DATOS_RESIDENTE_1);
        sqLiteDatabase.execSQL(GestionBD.INSERTAR_DATOS_RESIDENTE_2);
        sqLiteDatabase.execSQL(GestionBD.INSERTAR_DATOS_DEPARTAMENTO_1);
        sqLiteDatabase.execSQL(GestionBD.INSERTAR_DATOS_DEPARTAMENTO_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
