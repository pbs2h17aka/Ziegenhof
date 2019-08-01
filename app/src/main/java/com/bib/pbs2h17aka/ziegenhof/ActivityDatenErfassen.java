package com.bib.pbs2h17aka.ziegenhof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.InvalidParameterException;
import java.util.ArrayList;

// Activity um einen neuen Ertrag zu erfassen
public class ActivityDatenErfassen extends AppCompatActivity {

    // Elemente deklarieren
    Spinner spin_namen;
    EditText et_datum;
    EditText et_ertrag;
    //Button bt_ok;

    // Testdaten
    ArrayList<String> ziegenNamen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daten_erfassen);

        // Elemente initialisieren
        spin_namen = findViewById(R.id.spinnerName);
        et_datum = findViewById(R.id.editTextDatum);
        et_ertrag = findViewById(R.id.editTextErtrag);
        //bt_ok = findViewById(R.id.buttonOK);

        // Ziegen Namen in Spinner einbinden
        ArrayList<String> ziegenNamen = ErtragManager.getZiegen(getApplicationContext());
        ArrayAdapter adapterZiegenNamen = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, ziegenNamen);
        spin_namen.setAdapter(adapterZiegenNamen);
    }

    // Button Click Methoden

    // Methode um neuen Ertrag zu erfassen
    public void doOk(View view) {

        // Prüfung der Eingabe
        try {

            // Name
            String name = spin_namen.getSelectedItem().toString();

            // Datum
            String datum = et_datum.getText().toString();
            if(!datum.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")){
                throw new InvalidParameterException("Ungültiges Datumsformat.");
            }
            // Liter
            Double liter = Double.parseDouble(et_ertrag.getText().toString());

            // Ertsellen eines neuen Ertrags
            Ertrag ertrag = new Ertrag(name, datum, liter);

            // Neuen Ertrag in SQLite Datenbank schreiben
            ErtragManager.addErtrag(getApplicationContext(), ertrag);
            Toast.makeText(this, "Ertrag erfolgreich hinzugefügt.", Toast.LENGTH_SHORT).show();

            // Beenden der Activity
            finish();
        }
        // Fehler wenn Datum ein ungültiges Datumsformat
        catch (InvalidParameterException e) {
            Toast.makeText(this, "Ungültiges Datum.", Toast.LENGTH_SHORT).show();
        }
        // Fehler wenn Ertrag null oder ungültiges Format
        catch (NullPointerException | NumberFormatException e) {
            Toast.makeText(this, "Fehlender oder ungültiger Ertrag.", Toast.LENGTH_SHORT).show();
            et_ertrag.requestFocus();
        }
    }
}
