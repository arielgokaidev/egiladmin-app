package com.egiladmin.egiladmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ResolutionDimension;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Residentes extends AppCompatActivity {

    // Variables globales
    private GestionBD gestionBD;
    private Button btnIngresarResidentes, btnVerResidentes, btnActualizarResidentes, btnEliminarResidentes;
    private EditText etRut, etNombre, etApellido, etUsuario, etPassword;
    private ListView listViewResidentes;
    private String tipo = "";
    //private List<Residente> residentes;

    private EditText etRut2, etNombre2, etApellido2, etUsuario2, etPassword2, etTipo2;
    private TextView textView3;
    //private ArrayList<Residente> residente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residentes);

        // Toolbar
        getSupportActionBar().setTitle("Residentes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Botones
        btnIngresarResidentes = findViewById(R.id.btnIngresarResidentes);
        btnVerResidentes = findViewById(R.id.btnVerResidentes);
        btnActualizarResidentes = findViewById(R.id.btnActualizarResidentes);
        btnEliminarResidentes = findViewById(R.id.btnEliminarResidentes);

        // Listeners
        btnIngresarResidentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoIngresar();
            }
        });

        btnVerResidentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoLeer();
            }
        });

        btnActualizarResidentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoActualizar();
            }
        });

        btnEliminarResidentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoEliminar();
            }
        });

        // INSTANCIACION DE LA BASE DE DATOS
        gestionBD = new GestionBD(this);

        // EditText de prueba
        etRut2 = findViewById(R.id.etRut2);
        etNombre2 = findViewById(R.id.etNombre2);
        etApellido2 = findViewById(R.id.etApellido2);
        etUsuario2 = findViewById(R.id.etUsuario2);
        etPassword2 = findViewById(R.id.etPassword2);
        etTipo2 = findViewById(R.id.etTipo2);

    }

    //INGRESO RESIDENTES
    public void dialogoIngresar() {

        final CharSequence tipos[] = {"Dueño", "Arrendatario"};
        tipo = "";

        // Constructor AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        // Asociación
        LayoutInflater inflater = Residentes.this.getLayoutInflater();

        // Declarar vista desde layout
        View view = inflater.inflate(R.layout.dialog_insertar_residente, null);

        // Titulo
        builder.setTitle("Ingresar Residente");

        // Asignar vista
        builder.setView(view);

        // Radiobutton
        builder.setSingleChoiceItems(tipos, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tipo = tipos[which].toString();
            }
        });

        // Boton Ingresar
        builder.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                // declarar variables = texto desde input
                String rut = etRut.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String usuario = etUsuario.getText().toString();
                String password = etPassword.getText().toString();

                // validar campos e insertar
                gestionBD.insertarResidente(rut, nombre, apellido, usuario, password, tipo);
                Toast.makeText(getApplicationContext(), "¡Datos ingresados con éxito! Rut: "+ rut, Toast.LENGTH_SHORT).show();
            }
        });

        // Botón cancelar
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        // Declarar componentes
        etRut = view.findViewById(R.id.etRut);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etUsuario = view.findViewById(R.id.etUsuario);
        etPassword = view.findViewById(R.id.etPassword);

        // Crear AlertDialog
        AlertDialog ad = builder.create();

        // Mostrar AlertDialog
        ad.show();

    }
    //LEER RESIDENTES
    public void dialogoLeer() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        LayoutInflater inflater = Residentes.this.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_leer_residente, null);

        builder.setTitle("Ver Residente");
        builder.setView(view);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });

        listViewResidentes = view.findViewById(R.id.listViewResidentes);
        textView3 = view.findViewById(R.id.textView3);

        ArrayList<Residente> residentes = gestionBD.leerResidentes();
        int cant = residentes.size();
        Toast.makeText(getApplicationContext(), "Cantidad: " + cant, Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, residentes);

        listViewResidentes.setAdapter(arrayAdapter);
        String texto = "";
        for (int i = 0; i < residentes.size(); i++) {
            texto += residentes.get(i).toString();
        }
        textView3.setText(texto);
        AlertDialog ad = builder.create();
        ad.show();

    }

    //ACTUALIZAR RESIDENTES
    public void dialogoActualizar() {

        final CharSequence tipos[] = {"Dueño", "Arrendatario"};
        tipo = "";
// Constructor AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);
 // Asociación
        LayoutInflater inflater = Residentes.this.getLayoutInflater();
// Declarar vista desde layout
        View view = inflater.inflate(R.layout.dialog_insertar_residente, null);
// Titulo
        builder.setTitle("Ingresar Residente");
 // Asignar vista
        builder.setView(view);
 // Radiobutton
        builder.setSingleChoiceItems(tipos, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tipo = tipos[which].toString();
            }
        });

        // Boton Actualizar
        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                // declarar variables = texto desde input
                String rut = etRut.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String usuario = etUsuario.getText().toString();
                String password = etPassword.getText().toString();

                // validar campos e insertar
                gestionBD.actualizarResidente(rut, nombre, apellido, usuario, password, tipo);
                Toast.makeText(getApplicationContext(), "¡Datos Actualizados con éxito! Rut: "+ rut, Toast.LENGTH_SHORT).show();
            }
        });

        // Botón cancelar
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        // Declarar componentes
        etRut = view.findViewById(R.id.etRut);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etUsuario = view.findViewById(R.id.etUsuario);
        etPassword = view.findViewById(R.id.etPassword);

        // Crear AlertDialog
        AlertDialog ad = builder.create();

        // Mostrar AlertDialog
        ad.show();

    }
    //ELIMINAR RESIDENTES
    public void dialogoEliminar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        LayoutInflater inflater = Residentes.this.getLayoutInflater();

        builder.setTitle("Eliminar Residente");
        builder.setView(inflater.inflate(R.layout.dialog_eliminar_residente, null));

        // BOTON INGRESAR DATOS
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

         String rut = etRut2.getText().toString();
         gestionBD.eliminarResidente(rut);
         Toast.makeText(getApplicationContext(), "¡Usuario eliminado con éxito!", Toast.LENGTH_SHORT).show();
                etRut2.setText("");
                etNombre2.setText("");
                etApellido2.setText("");
                etUsuario2.setText("");
                etPassword2.setText("");
                etTipo2.setText("");

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog ad = builder.create();
        ad.show();

    }

    public void buscar(View view){

        String rut = etRut2.getText().toString();

        try {
            if (!rut.isEmpty()) {
                Cursor cursor = gestionBD.leerUnResidente(rut);
                if (cursor.moveToFirst()) {
                    etRut2.setText(cursor.getString(0));
                    etNombre2.setText(cursor.getString(1));
                    etApellido2.setText(cursor.getString(2));
                    etUsuario2.setText(cursor.getString(3));
                    etPassword2.setText(cursor.getString(4));
                    etTipo2.setText(cursor.getString(5));
                }
            } else {
                Toast.makeText(this,"Introducir rut",Toast.LENGTH_SHORT).show();
                etRut2.setText("");
                etNombre2.setText("");
                etApellido2.setText("");
                etUsuario2.setText("");
                etPassword2.setText("");
                etTipo2.setText("");
            }
        } catch (SQLiteException e) {
            Toast.makeText(this,"El rut no existe",Toast.LENGTH_SHORT).show();
        }

    }

}
