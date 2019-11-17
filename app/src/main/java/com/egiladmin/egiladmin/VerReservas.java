package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class VerReservas extends AppCompatActivity {

    private TextView tvMensaje;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterReservas;
    private RecyclerView.LayoutManager layoutManager;
    private GestionBD gestionBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reservas);
        getSupportActionBar().setTitle("Listado de Reservas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvMensaje = findViewById(R.id.tvMensaje);
        recyclerView = findViewById(R.id.recyclerViewReservas);

        layoutManager = new LinearLayoutManager(this);
        gestionBD = new GestionBD(this);

        ArrayList<Reserva> reservas = gestionBD.leerReservas();

        if (reservas.size() > 0) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            adapterReservas = new AdapterReservas(reservas);
            recyclerView.setAdapter(adapterReservas);
        } else {
            tvMensaje.setVisibility(View.VISIBLE);
        }

    }
}
