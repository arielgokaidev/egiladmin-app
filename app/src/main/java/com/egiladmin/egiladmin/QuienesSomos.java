package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuienesSomos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quienes_somos);

        getSupportActionBar().setTitle("Quienes Somos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
