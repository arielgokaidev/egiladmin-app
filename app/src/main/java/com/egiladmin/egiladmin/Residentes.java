package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Residentes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residentes);

        getSupportActionBar().setTitle("Residentes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
