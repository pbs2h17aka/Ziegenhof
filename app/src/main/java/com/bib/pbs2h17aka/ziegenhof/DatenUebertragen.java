package com.bib.pbs2h17aka.ziegenhof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// Activity zur Netzwerkkommunikation mit dem Server
public class DatenUebertragen extends AppCompatActivity {

    // Elemente deklarieren
    EditText et_ip;
    EditText et_port;
    Button bt_daten_senden;
    Button bt_daten_empfangen;
    TextView tv_netzwerk_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daten_uebertragen);

        // Elemente initialisieren
        et_ip = findViewById(R.id.editTextIP);
        et_port = findViewById(R.id.editTextPort);
        bt_daten_senden = findViewById(R.id.buttonDatenSenden);
        bt_daten_empfangen = findViewById(R.id.buttonDatenEmpfangen);
        tv_netzwerk_status = findViewById(R.id.textViewNetzwerkStatus);
    }

    // Button Click Methoden

    // Methode um erfasste Erträge an den Server zu senden
    public void doDatenSenden(View view) {

    }

    // Methode um Ziegennamen vom Server abzufragen
    // Request: "GET ZIEGEN\n"
    public void doDatenEmpfangen(View view) {

    }
}
