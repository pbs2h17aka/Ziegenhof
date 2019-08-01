package com.bib.pbs2h17aka.ziegenhof;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// Main Activity
public class ActivityZiegenhof extends AppCompatActivity {

    // Elemente deklarieren
    //Button bt_daten_erfassen;
    //Button bt_daten_anzeigen;
    //Button bt_daten_uebertragen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziegenhof);

        // Elemente initialisieren
        //bt_daten_erfassen = findViewById(R.id.buttonDatenErfassen);
        //bt_daten_anzeigen = findViewById(R.id.buttonDatenAnzeigen);
        //bt_daten_uebertragen = findViewById(R.id.buttonDatenUebertragen);
    }

    // Button Click Methoden
    // Wechsel in funktionale Aktivities

    // Activity um neuen Ertrag zu erfassen
    public void doDatenErfassen(View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityDatenErfassen.class);
        startActivity(intent);
    }

    // Activity um bisherige Erträge anzeigen zu lassen
    public void doDatenAnzeigen(View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityDatenAnzeigen.class);
        startActivity(intent);
    }

    // Activity um mit Server zu kommunizieren
    public void doDatenUebertragen(View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityDatenUebertragen.class);
        startActivity(intent);
    }
}
