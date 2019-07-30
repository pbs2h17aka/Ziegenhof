package com.bib.pbs2h17aka.ziegenhof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

// Activity um bisher erfasste Erträge anzeigen zu lassen
public class DatenAnzeigen extends AppCompatActivity {

    // Elemente deklarieren
    Spinner spin_ziege;
    ListView lv_ertraege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daten_anzeigen);

        // Elemente initialisieren
        spin_ziege = findViewById(R.id.spinnerZiege);
        lv_ertraege = findViewById(R.id.listViewErtraege);
    }
}
