package com.bib.pbs2h17aka.ziegenhof;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

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
    public void doDatenEmpfangen(View view) {

        final String ip = "10.0.2.2";
        final int port = 61234;

        class doOnNetwork extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    // Ziegen Namen in lokale SQLite Datenbank übertragen
                    ErtragManager.uebertrageZiegenNamen(getApplicationContext(), ip, port);
                } catch(IOException e) {
                    return e.getMessage();
                }

                return null;
            }

            // Wenn der Async Task beendet ist

            @Override
            protected void onPostExecute(String errorText) {
                if(errorText != null) {
                    tv_netzwerk_status.setText(errorText);
                }
            }
        }

        // Erstellen & Ausführen des Async Task
        doOnNetwork don = new doOnNetwork();
        don.execute();
        
        // Activity beenden
        finish();
        Toast.makeText(this, "Daten erfolgreich übertragen.", Toast.LENGTH_SHORT).show();
    }
}
