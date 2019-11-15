package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class VerDepartamentos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterDepartamentos;
    private RecyclerView.LayoutManager layoutManager;
    private GestionBD gestionBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_departamentos);
        getSupportActionBar().setTitle("Listado de Departamentos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewDepartamentos);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        gestionBD = new GestionBD(this);

        ArrayList<Departamento> departamentos;
        departamentos = gestionBD.leerDepartamento();
        adapterDepartamentos = new AdapterDepartamentos(departamentos);

        recyclerView.setAdapter(adapterDepartamentos);
    }
}
