package com.egiladmin.egiladmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Residentes extends AppCompatActivity {

    // Variables globales
    private GestionBD gestionBD;
    private Button btnIngresarResidentes, btnVerResidentes, btnActualizarResidentes, btnEliminarResidentes;
    private EditText etRut, etNombre, etApellido, etUsuario, etPassword;
    private ListView listViewResidentes;
    private String tipo, rut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residentes);

        // Toolbar y volver atrás
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
                activityLeer();
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

                //Validacion de parametros ingresados
                dialogoEliminar();
            }
        });

        // INSTANCIA DE LA BASE DE DATOS
        gestionBD = new GestionBD(this);

    }

    //INGRESO RESIDENTES
    public void dialogoIngresar() {

        // Variables para el radiobutton
        final CharSequence tipos[] = {"Dueño", "Arrendatario"};
        // Reiniciar variable
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
                // Guardar tipo seleccionado
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
        builder.setNegativeButton("Cancelar", null);

        // Declarar componentes
        etRut = view.findViewById(R.id.etRut);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etUsuario = view.findViewById(R.id.etUsuario);
        etPassword = view.findViewById(R.id.etPassword);

        // Crear AlertDialog
        AlertDialog alertDialog = builder.create();

        // Mostrar AlertDialog
        alertDialog.show();

    }

    // ACTIVITY LEER RESIDENTES
    public void activityLeer() {
        Intent intent = new Intent(this, VerResidentes.class);
        startActivity(intent);
    }

    //ACTUALIZAR RESIDENTES
    public void dialogoActualizar() {
        // Vaciar rut
        rut = "";
        // Select ruts
        ArrayList<Residente> residentes = gestionBD.leerRuts();
        // Elementos para radiobutton
        final CharSequence ruts[] = new CharSequence[residentes.size()];
        // Setear CharSequence
        for (int i = 0; i < residentes.size(); i++) {
            ruts[i] = residentes.get(i).getRut();
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        builder.setTitle("Seleccione un rut para actualizar");

        // Radiobutton
        builder.setSingleChoiceItems(ruts, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rut = ruts[which].toString();
            }
        });

        // Boton Actualizar sin parametros
        builder.setPositiveButton("Actualizar", null);

        // Botón cancelar sin parametros
        builder.setNegativeButton("Cancelar", null);

        AlertDialog alertDialog = builder.create();

        // Listener botones
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                // Botón actualizar
                Button actualizar = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                actualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Validar selección de rut
                        if (rut.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "¡Seleccione un rut!" + rut, Toast.LENGTH_SHORT).show();
                        } else {
                            // Cerrar dialog
                            builder.create().cancel();
                            // Enviar rut a activity
                            Intent intent = new Intent(getApplicationContext(), ActualizarResidente.class);
                            intent.putExtra("rut", rut);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        // Validar si existen ruts
        if (ruts.length > 0) {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "¡No hay residentes registrados!", Toast.LENGTH_LONG).show();
        }
    }


    //ELIMINAR RESIDENTES
    public void dialogoEliminar() {
        rut = "";
        ArrayList<Residente> residentes = gestionBD.leerRuts();
        final CharSequence ruts[] = new CharSequence[residentes.size()];

        for (int i = 0; i < residentes.size(); i++) {
            ruts[i] = residentes.get(i).getRut();
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        builder.setTitle("Seleccione un rut para eliminar");

        builder.setSingleChoiceItems(ruts, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rut = ruts[which].toString();
            }
        });

        // Boton Actualizar sin parametros
        builder.setPositiveButton("Actualizar", null);

        // Botón cancelar sin parametros
        builder.setNegativeButton("Cancelar", null);

        AlertDialog alertDialog = builder.create();

        // Listener botones
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                // Botón actualizar
                Button actualizar = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                actualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Validar selección de rut
                        if (rut.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "¡Seleccione un rut!" + rut, Toast.LENGTH_SHORT).show();
                        } else {
                            int codigo = gestionBD.eliminarResidente(rut);

                            if (codigo == 1) {
                                Toast.makeText(getApplicationContext(), "¡El usuario se ha eliminado con exito!", Toast.LENGTH_LONG).show();
                                builder.create().cancel();
                            } else {
                                Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

        // Validar si existen ruts
        if (ruts.length > 0) {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "¡No hay residentes registrados!", Toast.LENGTH_LONG).show();
        }
/*
        // BOTON ELIMINAR
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //String rut = etRut2.getText().toString();

                //el metodo retorna un entero, la cantidad de elementos eliminados (cantidad)

                if (rut.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "¡Debe ingresar un RUT!" + rut, Toast.LENGTH_SHORT).show();
                }else {
                    int codigo = gestionBD.eliminarResidente(rut);

                    if (codigo == 1) {
                        Toast.makeText(getApplicationContext(), "El usuario se ha eliminado con exito", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                    //Limpia campos del activity
*//*                    etRut2.setText("");
                    etNombre2.setText("");
                    etApellido2.setText("");
                    etUsuario2.setText("");
                    etPassword2.setText("");
                    etTipo2.setText("");*//*

                }
            }
        });*/


    }
}
