package com.egiladmin.egiladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class IngresarReserva extends AppCompatActivity {

    private Spinner spinnerDepartamento;
    private DatePickerDialog datePickerDialog;
    private EditText etFecha, etHora;
    private Button btnFecha, btnHora, btnIngresar;
    private TimePickerDialog timePickerDialog;
    private RadioGroup rgTurno;
    private RadioButton rbTurno;
    private GestionBD gestionBD;
    private String departamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_reserva);

        getSupportActionBar().setTitle("Ingresar reserva");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerDepartamento = findViewById(R.id.spinnerDepartamento);
        etFecha = findViewById(R.id.etFecha);
        etHora = findViewById(R.id.etHora);
        btnFecha = findViewById(R.id.btnFecha);
        btnHora = findViewById(R.id.btnHora);
        rgTurno = findViewById(R.id.rgTurno);
        btnIngresar = findViewById(R.id.btnIngresar);

        etFecha.setInputType(InputType.TYPE_NULL);
        etHora.setInputType(InputType.TYPE_NULL);

        gestionBD = new GestionBD(this);

        final ArrayList<Departamento> departamentos = gestionBD.leerDepartamentos();
        ArrayList<String> departamentoList = new ArrayList<>();
        for (int i = 0; i < departamentos.size(); i++) {
            departamentoList.add(String.valueOf(departamentos.get(i).getNumero()));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departamentoList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartamento.setAdapter(arrayAdapter);

        spinnerDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departamento = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(IngresarReserva.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etFecha.setText(dayOfMonth + "-" + (month) + "-" + year);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                // time picker dialog
                timePickerDialog = new TimePickerDialog(IngresarReserva.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                if (sMinute < 10) {
                                    etHora.setText(sHour + ":0" + sMinute);
                                } else {
                                    etHora.setText(sHour + ":" + sMinute);
                                }
                            }
                        }, hour, minutes, true);
                timePickerDialog.show();
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });
    }

    public void ingresar() {
        long codigo = 0;
        String fecha = etFecha.getText().toString();
        String hora = etHora.getText().toString();
        String turno = "";
        int numero = Integer.parseInt(departamento);
        int radioId = rgTurno.getCheckedRadioButtonId();
        if (radioId != -1) {
            rbTurno = findViewById(radioId);
            turno = rbTurno.getText().toString();
        }
        if (departamento.isEmpty() || fecha.isEmpty() || hora.isEmpty() || turno.isEmpty()) {
            Toast.makeText(getApplicationContext(),"¡Debe completar todos los campos!", Toast.LENGTH_SHORT).show();
        } else {
            codigo = gestionBD.insertarReserva(fecha, hora, turno, numero);
            if (codigo > -1) {
                etFecha.setText("");
                etHora.setText("");
                Toast.makeText(getApplicationContext(), "¡Datos ingresados con éxito!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "¡Error al ingresar datos!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
