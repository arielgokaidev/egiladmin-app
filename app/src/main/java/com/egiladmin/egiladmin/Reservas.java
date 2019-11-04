package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Reservas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        getSupportActionBar().setTitle("Reservas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
