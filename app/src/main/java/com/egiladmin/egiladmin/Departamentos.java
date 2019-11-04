package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Departamentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamentos);

        getSupportActionBar().setTitle("Departamentos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
