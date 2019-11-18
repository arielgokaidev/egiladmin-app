package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private Button btnIngresar;
    private GestionBD gestionBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login Reserva Admin");

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        gestionBD = new GestionBD(this);

    }

    public void login() {
        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();
        if (usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(),"¡Ingrese sus credenciales!", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<Residente> residentes = gestionBD.loginResidente(usuario);
            if (residentes.size() != 0) {
                if (residentes.get(0).getUsuario().equals(usuario) && residentes.get(0).getPassword().equals(password)) {
                    Toast.makeText(getApplicationContext(),"¡Ingreso exitoso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"¡Usuario y/o Contraseña incorrectos!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(),"¡Usuario y/o Contraseña incorrectos!", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
