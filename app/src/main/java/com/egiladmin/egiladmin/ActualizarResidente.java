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

public class ActualizarResidente extends AppCompatActivity {

    private TextView tvRut;
    private EditText etNombre, etApellido, etUsuario, etPassword, etTipo;
    private Button btnGuardar;
    private GestionBD gestionBD;
    private String rut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_residente);

        // Toolbar y volver atrás
        getSupportActionBar().setTitle("Editar Residente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvRut = findViewById(R.id.tvRut);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        etTipo = findViewById(R.id.etTipo);
        btnGuardar = findViewById(R.id.btnGuardar);

        rut = getIntent().getStringExtra("rut");
        tvRut.setText(rut);

        gestionBD = new GestionBD(this);
        ArrayList<Residente> residente =  gestionBD.leerResidente(rut);
        etNombre.setText(residente.get(0).getNombre());
        etApellido.setText(residente.get(0).getApellido());
        etUsuario.setText(residente.get(0).getUsuario());
        etPassword.setText(residente.get(0).getPassword());
        etTipo.setText(residente.get(0).getTipo());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
    }

    public void guardar() {
        String rut = this.rut;
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();
        String tipo = etTipo.getText().toString();
        // validar campos e insertar
        if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "¡Debe completar todos los campos!", Toast.LENGTH_SHORT).show();
        } else{
            int codigo =  gestionBD.actualizarResidente(rut, nombre, apellido, usuario, password, tipo);
            // Codigo de retorno
            if (codigo == 1) {
                Toast.makeText(getApplicationContext(), "¡Datos Actualizados con éxito! Rut: "+ rut, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Residentes.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
