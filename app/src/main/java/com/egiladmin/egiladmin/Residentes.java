package com.egiladmin.egiladmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Residentes extends AppCompatActivity {

    private GestionBD gestionBD;
    private Button btnIngresarResidentes, btnVerResidentes, btnActualizarResidentes, btnEliminarResidentes;
    private EditText etRut, etNombre, etApellido, etUsuario, etPassword, etTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residentes);

        getSupportActionBar().setTitle("Residentes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Botones
        btnIngresarResidentes = findViewById(R.id.btnIngresarResidentes);
        btnVerResidentes = findViewById(R.id.btnVerResidentes);
        btnActualizarResidentes = findViewById(R.id.btnActualizarResidentes);
        btnEliminarResidentes = findViewById(R.id.btnEliminarResidentes);

        // Dialog insertar
        etRut = findViewById(R.id.etRut);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        etTipo = findViewById(R.id.etTipo);

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

        AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        LayoutInflater inflater = Residentes.this.getLayoutInflater();

        builder.setTitle("Ingresar Residente");
        builder.setView(inflater.inflate(R.layout.dialog_insertar_residente, null));
        builder.setSingleChoiceItems(tipos, -1, null);

        builder.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                // declarar variables = texto desde input

                String rut = etRut.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String usuario = etUsuario.getText().toString();
                String password = etPassword.getText().toString();
                String tipo = etTipo.toString();
               // Toast.makeText(getApplicationContext(), "" +tipos[which], Toast.LENGTH_SHORT).show();
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
    //LEER RESIDENTES
    public void dialogoLeer() {

        final CharSequence tipos[] = {"Dueño", "Arrendatario"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        LayoutInflater inflater = Residentes.this.getLayoutInflater();

        builder.setTitle("Ver Residente");
        builder.setView(inflater.inflate(R.layout.dialog_leer_residente, null));
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

}
