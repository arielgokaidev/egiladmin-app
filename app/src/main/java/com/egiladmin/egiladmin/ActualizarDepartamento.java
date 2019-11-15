package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActualizarDepartamento extends AppCompatActivity {

    private TextView tvNumero;
    private EditText etNumero, etTorre, etEstado, etRut;
    private Button btnGuardar;
    private GestionBD gestionBD;
    private int numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_departamento);

        // Toolbar y volver atrás
        getSupportActionBar().setTitle("Editar Departamento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNumero = findViewById(R.id.tvNumero);
        etNumero = findViewById(R.id.etNumero);
        etTorre = findViewById(R.id.etTorre);
        etEstado = findViewById(R.id.etUsuario);
        etRut = findViewById(R.id.etRut);
        btnGuardar = findViewById(R.id.btnGuardar);

        numero = Integer.parseInt(getIntent().getStringExtra("numero")) ;
        tvNumero.setText(numero);

        gestionBD = new GestionBD(this);
        ArrayList<Departamento> departamento =  gestionBD.leerDepartamento(numero);
        etNumero.setText(departamento.get(0).getNumero());
        etTorre.setText(departamento.get(0).getTorre());
        etEstado.setText(departamento.get(0).getEstado());
        etRut.setText(departamento.get(0).getRut());


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
    }

    public void guardar() {
        int numero = this.numero;
        String torre = etTorre.getText().toString();
        String estado = etEstado.getText().toString();
        String rut = etRut.getText().toString();

        // validar campos e insertar
        if (numero ==0 || torre.isEmpty() || estado.isEmpty() || rut.isEmpty()) {
            Toast.makeText(getApplicationContext(), "¡Debe completar todos los campos!", Toast.LENGTH_SHORT).show();
        } else{
            int codigo =  gestionBD.actualizarDepartamentos(numero, torre, estado, rut);
            // Codigo de retorno
            if (codigo == 1) {
                Toast.makeText(getApplicationContext(), "¡Datos Actualizados con éxito! Numero: "+ numero, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Departamentos.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
