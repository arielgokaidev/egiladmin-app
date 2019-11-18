package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonResidentes, buttonDepartamentos, buttonReservas, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonResidentes = (Button) findViewById(R.id.btnResidentes);
        buttonDepartamentos = (Button) findViewById(R.id.btnDepartamentos);
        buttonReservas = (Button) findViewById(R.id.btnReservas);
        btnLogout = findViewById(R.id.btnLogout);

        buttonResidentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResidentes();
            }
        });

        buttonDepartamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDepartamentos();
            }
        });

        buttonReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReservas();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quienesSomos:
                Intent intent = new Intent(this, QuienesSomos.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openResidentes() {
        Intent intent = new Intent(this, Residentes.class);
        startActivity(intent);
    }

    public void openDepartamentos() {
        Intent intent = new Intent(this, Departamentos.class);
        startActivity(intent);
    }

    public void openReservas() {
        Intent intent = new Intent(this, Reservas.class);
        startActivity(intent);
    }

    public void logout() {
        Toast.makeText(getApplicationContext(),"¡Ha salido correctamente!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}
