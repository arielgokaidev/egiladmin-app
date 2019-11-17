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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Residentes extends AppCompatActivity {

    // Variables globales
    private GestionBD gestionBD;
    private Button btnIngresarResidentes, btnVerResidentes, btnActualizarResidentes, btnEliminarResidentes;
    private EditText etRut, etNombre, etApellido, etUsuario, etPassword;
    private RadioGroup rgTipo;
    private RadioButton rbTipo;
    private String rut;

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
                dialogoEliminar();
            }
        });

        // INSTANCIA DE LA BASE DE DATOS
        gestionBD = new GestionBD(this);

    }

    //INGRESO RESIDENTES
    public void dialogoIngresar() {

        // Constructor AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(Residentes.this);

        // Asociación
        LayoutInflater inflater = Residentes.this.getLayoutInflater();

        // Declarar vista desde layout
        final View view = inflater.inflate(R.layout.dialog_insertar_residente, null);

        // Titulo
        builder.setTitle("Ingresar Residente");

        // Asignar vista
        builder.setView(view);

        // Boton Ingresar
        builder.setPositiveButton("Ingresar", null);

        // Botón cancelar
        builder.setNegativeButton("Cancelar", null);

        // Declarar componentes
        etRut = view.findViewById(R.id.etRut);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etUsuario = view.findViewById(R.id.etUsuario);
        etPassword = view.findViewById(R.id.etPassword);
        rgTipo = view.findViewById(R.id.rgTipo);

        // Crear AlertDialog
        final AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button ingresar = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                ingresar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long codigo = 0;
                        String rut = etRut.getText().toString();
                        String nombre = etNombre.getText().toString();
                        String apellido = etApellido.getText().toString();
                        String usuario = etUsuario.getText().toString();
                        String password = etPassword.getText().toString();
                        String tipo = "";
                        int radioId = rgTipo.getCheckedRadioButtonId();
                        if (radioId != -1) {
                            rbTipo = view.findViewById(radioId);
                            tipo = rbTipo.getText().toString();
                        }
                        if (rut.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || password.isEmpty() || tipo.isEmpty()) {
                            Toast.makeText(getApplicationContext(),"¡Debe completar todos los campos!", Toast.LENGTH_SHORT).show();
                        } else {
                            codigo = gestionBD.insertarResidente(rut, nombre, apellido, usuario, password, tipo);
                            if (codigo > -1) {
                                alertDialog.cancel();
                                Toast.makeText(getApplicationContext(), "¡Datos ingresados con éxito!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "¡Error al ingresar datos!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

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

        final AlertDialog alertDialog = builder.create();

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
                            alertDialog.cancel();
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
        builder.setPositiveButton("Eliminar", null);

        // Botón cancelar sin parametros
        builder.setNegativeButton("Cancelar", null);

        final AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button eliminar = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Validar selección de rut
                        if (rut.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "¡Seleccione un rut!", Toast.LENGTH_SHORT).show();
                        } else {
                            int codigo = gestionBD.eliminarResidente(rut);
                            if (codigo > 0) {
                                alertDialog.cancel();
                                Toast.makeText(getApplicationContext(), "¡El usuario se ha eliminado con exito!", Toast.LENGTH_SHORT).show();
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

    }
}
