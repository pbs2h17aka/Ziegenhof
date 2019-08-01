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
public class ActivityDatenAnzeigen extends AppCompatActivity {

    // Elemente deklarieren
    Spinner spin_ziege;
    ListView lv_ertraege;

    // Datenstrukturen deklarieren
    ArrayList<String> ziegenNamen;
    ArrayAdapter<String> adapterZiegenNamen;

    ArrayList<Ertrag> ertraege;
    ArrayAdapter<Ertrag> adapterErtraege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daten_anzeigen);

        // Elemente initialisieren
        spin_ziege = findViewById(R.id.spinnerZiege);
        lv_ertraege = findViewById(R.id.listViewErtraege);

        // Spinner mit verfügbaren Ziegen Namen befüllen
        ziegenNamen = ErtragManager.getZiegen(getApplicationContext());
        ziegenNamen.add(0,"Alle");
        adapterZiegenNamen = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, ziegenNamen);
        spin_ziege.setAdapter(adapterZiegenNamen);
        //spin_ziege.setSelection(adapterZiegenNamen.getCount()-1);

        // List View mit verfügbaren Erträgen befüllen
        ertraege = ErtragManager.getErtragLise(getApplicationContext(), "Alle");
        adapterErtraege = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ertraege);
        lv_ertraege.setAdapter(adapterErtraege);

        // Wenn ein Name auf dem Spinner ausgewählt wurde
        spin_ziege.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Abfrage der ausgewählten Ziege
                String gewaehlt = parent.getItemAtPosition(position).toString();
                Toast.makeText(ActivityDatenAnzeigen.this, "Spinner selected: " + gewaehlt, Toast.LENGTH_SHORT).show();
                ertraege = ErtragManager.getErtragLise(getApplicationContext(), gewaehlt);
                adapterErtraege = new ArrayAdapter<>(ActivityDatenAnzeigen.this, android.R.layout.simple_list_item_1, ertraege);
                lv_ertraege.setAdapter(adapterErtraege);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Methode hier nicht benötigt
            }
        });
    }
}
