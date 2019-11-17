package com.egiladmin.egiladmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Reservas extends AppCompatActivity {

    // Variables globales
    private GestionBD gestionBD;
    private Button btnIngresarReserva, btnVerReservas, btnEliminarReserva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        // Toolbar
        getSupportActionBar().setTitle("Reservas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Botones
        btnIngresarReserva = findViewById(R.id.btnIngresarReserva);
        btnVerReservas = findViewById(R.id.btnVerReservas);
        btnEliminarReserva = findViewById(R.id.btnEliminarReserva);

        // Listeners
        btnIngresarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });

        btnVerReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ver();
            }
        });

        btnEliminarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialogoEliminar();
            }
        });

        gestionBD = new GestionBD(this);

    }

    public void ingresar() {
        Intent intent = new Intent(this, IngresarReserva.class);
        startActivity(intent);
    }

    public void ver() {
        Intent intent = new Intent(this, VerReservas.class);
        startActivity(intent);
    }

}

