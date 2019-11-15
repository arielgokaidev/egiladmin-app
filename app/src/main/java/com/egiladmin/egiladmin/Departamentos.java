package com.egiladmin.egiladmin;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Departamentos extends AppCompatActivity {

    // Variables globales
    private GestionBD gestionBD;
    private Button btnIngresarDepartamentos, btnVerDepartamentos, btnActualizarDepartamentos, btnEliminarDepartamentos;
    private EditText etNumero, etTorre, etEstado, etRut;
    private ListView listViewDepartamentos;
    private String numero;

    private List<Departamento> departamento;
    //Departamento String numero, torre, estado, rut;
    //private EditText etRut2, etNombre2, etApellido2, etUsuario2, etPassword2, etTipo2;
    //private ArrayList<Residente> residente;

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

                //Validacion de parametros ingresados
                dialogoEliminar();
            }
        });

        // INSTANCIACION DE LA BASE DE DATOS
        gestionBD = new GestionBD(this);

        // EditText de prueba
        //etRut2 = findViewById(R.id.etRut2);
       // etNombre2 = findViewById(R.id.etNombre2);
       // etApellido2 = findViewById(R.id.etApellido2);
        //etUsuario2 = findViewById(R.id.etUsuario2);
        //etPassword2 = findViewById(R.id.etPassword2);
        //etTipo2 = findViewById(R.id.etTipo2);

    }

    //INGRESO RESIDENTES
    public void dialogoIngresar() {

        //final CharSequence tipos[] = {"Dueño", "Arrendatario"};
       // tipo = "";

        // Constructor AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(Departamentos.this);

        // Asociación
        LayoutInflater inflater = Departamentos.this.getLayoutInflater();

        // Declarar vista desde layout
        View view = inflater.inflate(R.layout.dialog_insertar_departamento, null);

        // Titulo
        builder.setTitle("Ingresar Departamento");

        // Asignar vista
        builder.setView(view);

        // Radiobutton
        //builder.setSingleChoiceItems(tipos, -1, new DialogInterface.OnClickListener() {
            //@Override
           // public void onClick(DialogInterface dialog, int which) {
               // tipo = tipos[which].toString();
         //   }
       // });

        // Boton Ingresar
        builder.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                // declarar variables = texto desde input
                int numero = Integer.parseInt( etNumero.getText().toString());
                String torre = etTorre.getText().toString();
                String estado = etEstado.getText().toString();
                String rut = etRut.getText().toString();

//Departamento String numero, torre, estado, rut;
                // validar campos e insertar
                gestionBD.insertarDepartamento(numero, torre, estado, rut);
                Toast.makeText(getApplicationContext(), "¡Datos ingresados con éxito! Departametno: "+ numero, Toast.LENGTH_SHORT).show();
            }
        });

        // Botón cancelar
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        // Declarar componentes
        etNumero = view.findViewById(R.id.etNumero);
        etTorre = view.findViewById(R.id.etTorre);
        etEstado = view.findViewById(R.id.etEstado);
        etRut = view.findViewById(R.id.etRut);


        // Crear AlertDialog
        AlertDialog ad = builder.create();

        // Mostrar AlertDialog
        ad.show();

    }
/*
    //LEER RESIDENTES
    public void dialogoLeer() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Departamentos.this);

        LayoutInflater inflater = Departamentos.this.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_leer_residente, null);

        builder.setTitle("Ver Departamento");
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


    }*/




    //ACTUALIZAR RESIDENTES
    public void dialogoActualizar() {
        // Vaciar rut
        numero = "";
        // Select ruts
        ArrayList<Departamento> departamentos = gestionBD.leerNumero();
        // Elementos para radiobutton
        final CharSequence numeros[] = new CharSequence[departamentos.size()];
        // Setear CharSequence
        for (int i = 0; i < departamentos.size(); i++) {

            numeros[i] = String.valueOf(departamentos.get(i).getNumero());
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(Departamentos.this);

        builder.setTitle("Seleccione un numero dpto para actualizar");

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
                            Toast.makeText(getApplicationContext(), "¡Seleccione un Numero dpto!" + numero, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), "¡No hay Dptos registrados!", Toast.LENGTH_LONG).show();
        }
    }

    //ELIMINAR depto
    public void dialogoEliminar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Departamentos.this);

        LayoutInflater inflater = Departamentos.this.getLayoutInflater();

        builder.setTitle("Eliminar Departamento");
        builder.setView(inflater.inflate(R.layout.dialog_eliminar_departamento, null));

        // BOTON INGRESAR DATOS
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String numero = etNumero.getText().toString();

                //el metodo retorna un entero, la cantidad de elementos eliminados (cantidad)

                if (numero.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "¡Debe ingresar un Numero!" + numero, Toast.LENGTH_SHORT).show();
                }else {
                    int codigo = gestionBD.eliminarDepartamento(numero);

                    if (codigo == 1) {
                        Toast.makeText(getApplicationContext(), "El usuario se ha eliminado con exito", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                    //Limpia campos del activity
                    etNumero.setText("");
                    etTorre.setText("");
                    etEstado.setText("");
                    etRut.setText("");


                }
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
/*
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

    }*/
public void activityLeer() {
    Intent intent = new Intent(this, VerDepartamentos.class);
    startActivity(intent);
}
}

