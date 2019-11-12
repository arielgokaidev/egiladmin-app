package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class VerResidentes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterResidente;
    private RecyclerView.LayoutManager layoutManager;
    private GestionBD gestionBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_residentes);
        getSupportActionBar().setTitle("Listado de Residentes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewResidentes);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        gestionBD = new GestionBD(this);

        ArrayList<Residente> residentes;
        residentes = gestionBD.leerResidentes();
        adapterResidente = new AdapterResidentes(residentes);

        recyclerView.setAdapter(adapterResidente);
    }
}
