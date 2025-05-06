package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editCapital, editJuros, editTaxa, editPrazo;
    Spinner spinner;
    Button btnCalcular;
    String criterioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        editCapital = findViewById(R.id.editCapital);
        editJuros = findViewById(R.id.editJuros);
        editTaxa = findViewById(R.id.editTaxa);
        editPrazo = findViewById(R.id.editPrazo);
        btnCalcular = findViewById(R.id.btnCalcular);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcoes_calculo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                criterioSelecionado = parent.getItemAtPosition(position).toString();
                habilitarCampos(criterioSelecionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });
    }




