package com.bib.pbs2h17aka.ziegenhof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

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

        // Spinner mit verfügbaren Ziegen Namen befüllen
        ArrayList<String> ziegenNamen = ErtragManager.getZiegen(getApplicationContext());
        ArrayAdapter<String> adapterZiegenNamen = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, ziegenNamen);
        spin_ziege.setAdapter(adapterZiegenNamen);

        // List View mit verfügbaren Erträgen befüllen
        ArrayList<Ertrag> ertraege = ErtragManager.getErtragLise(getApplicationContext(), "Alle");
        ArrayAdapter<Ertrag> adapterErtraege = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ertraege);
        lv_ertraege.setAdapter(adapterErtraege);

        // Wenn ein Name auf dem Spinner ausgewählt wurde
        spin_ziege.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Abfrage der ausgewählten Ziege
                String gewaehlt = parent.getItemAtPosition(position).toString();
                Toast.makeText(DatenAnzeigen.this, "Spinner selected: " + gewaehlt, Toast.LENGTH_SHORT).show();
                ArrayList<Ertrag> ertraege = ErtragManager.getErtragLise(getApplicationContext(), gewaehlt);
                ArrayAdapter<Ertrag> adapterErtraege = new ArrayAdapter<>(DatenAnzeigen.this, android.R.layout.simple_list_item_1, ertraege);
                lv_ertraege.setAdapter(adapterErtraege);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Methode hier nicht benötigt
            }
        });
    }
}
