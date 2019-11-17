package com.egiladmin.egiladmin;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Departamentos extends AppCompatActivity {

    // Variables globales
    private GestionBD gestionBD;
    private Button btnIngresarDepartamentos, btnVerDepartamentos, btnActualizarDepartamentos, btnEliminarDepartamentos;
    private EditText etNumero, etTorre, etEstado;
    private Spinner spinnerRut;
    private String residente, numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamentos);

        // Toolbar
        getSupportActionBar().setTitle("Departamentos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Botones
        btnIngresarDepartamentos = findViewById(R.id.btnIngresarDepartamentos);
        btnVerDepartamentos = findViewById(R.id.btnVerDepartamentos);
        btnActualizarDepartamentos = findViewById(R.id.btnActualizarDepartamentos);
        btnEliminarDepartamentos = findViewById(R.id.btnEliminarDepartamentos);

        // Listeners
        btnIngresarDepartamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoIngresar();
            }
        });

        btnVerDepartamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityLeer();
            }
        });

        btnActualizarDepartamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoActualizar();
            }
        });

        btnEliminarDepartamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoEliminar();
            }
        });

        // INSTANCIA DE LA BASE DE DATOS
        gestionBD = new GestionBD(this);

    }

    //INGRESO DEPARTAMENTO
    public void dialogoIngresar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Departamentos.this);
        LayoutInflater inflater = Departamentos.this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_insertar_departamento, null);

        builder.setTitle("Ingresar Departamento");
        builder.setView(view);

        builder.setPositiveButton("Ingresar", null);
        builder.setNegativeButton("Cancelar", null);

        etNumero = view.findViewById(R.id.etNumero);
        etTorre = view.findViewById(R.id.etTorre);
        etEstado = view.findViewById(R.id.etEstado);
        spinnerRut = view.findViewById(R.id.spinnerRut);

        final ArrayList<Residente> residentes = gestionBD.leerRuts();
        ArrayList<String> rutList = new ArrayList<>();
        for (int i = 0; i < residentes.size(); i++) {
            rutList.add(residentes.get(i).getRut());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rutList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRut.setAdapter(arrayAdapter);

        spinnerRut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                residente = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button ingresar = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                ingresar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long codigo = 0;
                        int numero = 0;
                        if (!etNumero.getText().toString().isEmpty()) {
                            numero = Integer.parseInt(etNumero.getText().toString());
                        }
                        String torre = etTorre.getText().toString();
                        String estado = etEstado.getText().toString();
                        if (numero == 0 || torre.isEmpty() || estado.isEmpty() || residente.isEmpty()) {
                            Toast.makeText(getApplicationContext(),"¡Debe completar todos los campos!", Toast.LENGTH_SHORT).show();
                        } else {
                            codigo = gestionBD.insertarDepartamento(numero, torre, estado, residente);
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

        alertDialog.show();

    }

    // LEER DEPARTAMENTOS
    public void activityLeer() {
        Intent intent = new Intent(this, VerDepartamentos.class);
        startActivity(intent);
    }

    //ACTUALIZAR RESIDENTES
    public void dialogoActualizar() {
        numero = "";
        // Select ruts
        ArrayList<Departamento> departamentos = gestionBD.leerNumeros();
        // Elementos para radiobutton
        final CharSequence numeros[] = new CharSequence[departamentos.size()];
        // Setear CharSequence
        for (int i = 0; i < departamentos.size(); i++) {
            numeros[i] = String.valueOf(departamentos.get(i).getNumero());
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(Departamentos.this);

        builder.setTitle("Seleccione un departamento para actualizar");

        // Radiobutton
        builder.setSingleChoiceItems(numeros, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                numero = numeros[which].toString();
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
                        if (numero.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "¡Seleccione un departamento!" + numero, Toast.LENGTH_SHORT).show();
                        } else {
                            // Cerrar dialog
                            builder.create().cancel();
                            // Enviar rut a activity
                            Intent intent = new Intent(getApplicationContext(), ActualizarDepartamento.class);
                            intent.putExtra("numero", numero);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        // Validar si existen ruts
        if (numeros.length > 0) {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "¡No hay departamentos registrados!", Toast.LENGTH_LONG).show();
        }
    }

    //ELIMINAR depto
    public void dialogoEliminar() {
        numero = "";

        ArrayList<Departamento> departamentos = gestionBD.leerNumeros();

        final CharSequence numeros[] = new CharSequence[departamentos.size()];

        for (int i = 0; i < departamentos.size(); i++) {
            numeros[i] = String.valueOf(departamentos.get(i).getNumero());
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(Departamentos.this);

        builder.setTitle("Eliminar Departamento");

        builder.setSingleChoiceItems(numeros, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                numero = numeros[which].toString();
            }
        });

        builder.setPositiveButton("Eliminar", null);
        builder.setNegativeButton("Cancelar", null);

        final AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button eliminar = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (numero.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "¡Seleccione un departamento!", Toast.LENGTH_SHORT).show();
                        } else {
                            int codigo = gestionBD.eliminarDepartamento(numero);
                            if (codigo > 0) {
                                alertDialog.cancel();
                                Toast.makeText(getApplicationContext(), "¡El departamento se ha eliminado con exito!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "El departamento no existe", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

        if (numeros.length > 0) {
            alertDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "¡No hay departamentos registrados!", Toast.LENGTH_LONG).show();
        }

    }

}

