package com.egiladmin.egiladmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
    private List<Residente> residentes;
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
                Toast.makeText(getApplicationContext(), "¡Datos ingresados con éxito!"+rut, Toast.LENGTH_SHORT).show();
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

        //residentes = gestionBD.leerResidentes();
        //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, residentes);
        //listViewResidentes.setAdapter(arrayAdapter);

        AlertDialog ad = builder.create();
        ad.show();


    }
    //ACTUALIZAR RESIDENTES
    public void dialogoActualizar() {

        final CharSequence tipos[] = {"Dueño", "Arrendatario"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        LayoutInflater inflater = Residentes.this.getLayoutInflater();

        builder.setTitle("Actualizar Residente");
        builder.setView(inflater.inflate(R.layout.dialog_actualizar_residente, null));
        builder.setSingleChoiceItems(tipos, -1, null);

        builder.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                // declarar variables = texto desde input
                /*
                String rut = etRut.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String usuario = etUsuario.getText().toString();
                String password = etPassword.getText().toString();
                /*String tipo = tipos[i].toString();*/
                Toast.makeText(getApplicationContext(), "" +tipos[which], Toast.LENGTH_SHORT).show();
                // validar campos
                // llamar metodo insertar residentes y pasar las variables
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
    //ELIMINAR RESIDENTES
    public void dialogoEliminar() {

        final CharSequence tipos[] = {"Dueño", "Arrendatario"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        LayoutInflater inflater = Residentes.this.getLayoutInflater();

        builder.setTitle("Eliminar Residente");
        builder.setView(inflater.inflate(R.layout.dialog_eliminar_residente, null));
        builder.setSingleChoiceItems(tipos, -1, null);

        builder.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                // declarar variables = texto desde input
                String rut = etRut.getText().toString();


               // gestionBD.insertarResidente(rut, nombre, apellido, usuario, password, tipo);
                Toast.makeText(getApplicationContext(), "¡Usuario eliminado con éxito!"+rut, Toast.LENGTH_SHORT).show();
                /*
                String rut = etRut.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String usuario = etUsuario.getText().toString();
                String password = etPassword.getText().toString();
                /*String tipo = tipos[i].toString();*/
                //Toast.makeText(getApplicationContext(), "" +tipos[which], Toast.LENGTH_SHORT).show();
                // validar campos
                // llamar metodo insertar residentes y pasar las variables
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

}
