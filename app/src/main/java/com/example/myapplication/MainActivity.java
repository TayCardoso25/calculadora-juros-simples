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

    private void habilitarCampos(String criterio) {
        editCapital.setEnabled(true);
        editJuros.setEnabled(true);
        editTaxa.setEnabled(true);
        editPrazo.setEnabled(true);

        switch (criterio) {
            case "Capital":
                editCapital.setEnabled(false);
                editCapital.setText("");
                break;
            case "Juros":
                editJuros.setEnabled(false);
                editJuros.setText("");
                break;
            case "Taxa":
                editTaxa.setEnabled(false);
                editTaxa.setText("");
                break;
            case "Prazo":
                editPrazo.setEnabled(false);
                editPrazo.setText("");
                break;
        }
    }
    private void calcular() {
        try {
            String capitalStr = editCapital.getText().toString();
            String jurosStr = editJuros.getText().toString();
            String taxaStr = editTaxa.getText().toString();
            String prazoStr = editPrazo.getText().toString();

            switch (criterioSelecionado) {
                case "Capital":
                    double j1 = Double.parseDouble(jurosStr);
                    double i1 = Double.parseDouble(taxaStr);
                    int t1 = Integer.parseInt(prazoStr);
                    double c1 = (j1 * 100) / (i1 * t1);
                    editCapital.setText(String.format("%.2f", c1));
                    break;

                case "Juros":
                    double c2 = Double.parseDouble(capitalStr);
                    double i2 = Double.parseDouble(taxaStr);
                    int t2 = Integer.parseInt(prazoStr);
                    double j2 = (c2 * i2 * t2) / 100;
                    editJuros.setText(String.format("%.2f", j2));
                    break;
                case "Taxa":
                    double j3 = Double.parseDouble(jurosStr);
                    double c3 = Double.parseDouble(capitalStr);
                    int t3 = Integer.parseInt(prazoStr);
                    double i3 = (j3 * 100) / (c3 * t3);
                    editTaxa.setText(String.format("%.2f", i3));
                    break;

                case "Prazo":
                    double j4 = Double.parseDouble(jurosStr);
                    double c4 = Double.parseDouble(capitalStr);
                    double i4 = Double.parseDouble(taxaStr);
                    int t4 = (int) ((j4 * 100) / (c4 * i4));
                    editPrazo.setText(String.valueOf(t4));
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro no cálculo. Verifique os valores.", Toast.LENGTH_SHORT).show();
        }
    }
}

