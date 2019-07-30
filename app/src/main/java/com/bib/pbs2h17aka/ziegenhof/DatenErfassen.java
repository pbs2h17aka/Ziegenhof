package com.bib.pbs2h17aka.ziegenhof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

// Activity um einen neuen Ertrag zu erfassen
public class DatenErfassen extends AppCompatActivity {

    // Elemente deklarieren
    Spinner spin_namen;
    EditText et_datum;
    EditText et_ertrag;
    Button bt_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daten_erfassen);

        // Elemente initialisieren
        spin_namen = findViewById(R.id.spinnerName);
        et_datum = findViewById(R.id.editTextDatum);
        et_ertrag = findViewById(R.id.editTextErtrag);
        bt_ok = findViewById(R.id.buttonOK);
    }

    // Button Click Methoden

    // Methode um neuen Ertrag zu erfassen
    public void doOk(View view) {

    }
}
