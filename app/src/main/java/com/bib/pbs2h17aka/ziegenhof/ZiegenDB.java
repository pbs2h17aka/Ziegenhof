package com.bib.pbs2h17aka.ziegenhof;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ZiegenDB extends SQLiteOpenHelper {

    private final static String LOG_TAG = "ZiegenDB";

    Context context;
    SQLiteDatabase db;

    // ---------------------------------------------------------------------------------

    // Konstruktor
    public ZiegenDB(Context context) {
        super(context, "ZiegenDB", null, 1);
        this.context = context;
        this.db = getWritableDatabase();
    }

    // ---------------------------------------------------------------------------------

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Anlegen einer neuen Datenbank
        this.db = db;

        try{
            // Anlegen einer Tabelle für Ziegen
            String sql = "CREATE TABLE ziegen(name TEXT)";
            db.execSQL(sql);

            // Anlegen einer Tabelle für Erträge
            sql = "CREATE TABLE ertraege(name TEXT, datum TEXT, liter REAL)";
            db.execSQL(sql);

        } catch(Exception e) {
            Log.e(LOG_TAG, "onCreate: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------------

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Hier keine Reaktion auf Versionswechsel notwendig.
    }

    // ---------------------------------------------------------------------------------

    // Methode um Ziegen Namen abzufragen
    public ArrayList<String> getZiegenListe() {

        // Cursor deklarieren
        Cursor cursor = null;

        // SQL Query erstellen
        String sql = "SELECT * FROM ziegen ORDER BY name desc";

        try{
            // Cursor auf Datensatz setzen
            cursor = db.rawQuery(sql, null);
        } catch (Exception e) {
            Log.e(LOG_TAG,"Fehler beim Lesen der Datenbank: " + e.getMessage());
        }

        // Erstellen einer Namensliste
        ArrayList<String> liste = new ArrayList<>();
        liste.add("Alle");

        // Wennn der Cursor auf einem Dazensatz liegt
        if(cursor != null) {
            // Log der Anzahl von Datensätzen im Cursor
            Log.v(LOG_TAG,"Cursor.count = " + cursor.getCount());
            // Cursor auf den ersten Datensatz setzen
            cursor.moveToFirst();
            // Durchlauf aller Datensätze des Cursors
            while(! cursor.isAfterLast()) {
                // Hinzufügen des aktuellen Ertrags zur Liste
                liste.add(cursor.getString(0));
                // Cursor auf nächste Zeile setzen
                cursor.moveToNext();
            }
            // Cursor schließen
            cursor.close();
        }
        return liste;
    }

    // ---------------------------------------------------------------------------------

    // Methode um neuen Ertrag in SQLite Datenbank zu schreiben
    public void addErtrag(Ertrag ertrag) {

        // Erstellen des Insert Befehls
        String sql = "INSERT INTO ertraege(name, datum, liter) VALUES(" +
                "'" + ertrag.getName() + "'" +
                ",'"+ertrag.getDatum()+"'" +
                ","+ertrag.getLiter()+")";

        try {
            // Ausführen des Insert Befehls
            this.db.execSQL(sql);
        } catch (Exception e) {
            Log.e(LOG_TAG, "INSERT: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------------

    // Methode um Erträge abzufragen
    public ArrayList<Ertrag> getErtragListe(String name) {

        // Cursor deklarieren
        Cursor cursor = null;

        // SQL Query erstellen
        String query;
        if(name.toLowerCase().equals("alle")) {
            query = "SELECT * FROM ertraege";
        } else {
            query = "SELECT * FROM ertraege WHERE name = '" + name + "' ORDER BY datum desc, name asc";
        }

        // Setzen des Cursors auf den Datensatz
        try {
            cursor = this.db.rawQuery(query, null);
        } catch(Exception e) {
            Log.e(LOG_TAG, "Fehler beim Lesen der Datenbank: " + e.getMessage());
        }

        // Erstellen einer Ertrag Liste
        ArrayList<Ertrag> liste = new ArrayList<>();

        // Wenn der Cursor auf einem Datensatz liegt
        if(cursor != null) {
            // Log der Anzahl von Datensätzen im Cursor
            Log.v(LOG_TAG,"Cursor.count = " + cursor.getCount());
            // Curosr auf ersten Datensatz setzen
            cursor.moveToFirst();
            // Durchlauf aller Datensätze des Cursors
            while(!cursor.isAfterLast()) {
                // Anpassen der Liter
                String t = cursor.getString(2);
                t = t.replace(",",".");
                double liter = 0.0;
                try {
                    liter = Double.parseDouble(t);
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Fehler beim verarbeiten des Datensatzes: " + e.getMessage());
                }
                // Hinzufügen des aktuellen Ertrags zur Liste
                Ertrag e = new Ertrag(cursor.getString(0), cursor.getString(1), liter);
                liste.add(e);
                // Cursor auf nächste Zeile setzen
                cursor.moveToNext();
            }
            // Cursor schließen
            cursor.close();
        }
        return liste;
    }

    // ---------------------------------------------------------------------------------

    // Methode um Ziegen Namen in Datenbank zu schreiben
    public void uebertrageZiegenNamen(ArrayList<String> liste) {

        // Löschen der alten Daten
        try{
            db.delete("ziegen",null,null);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fehler beim Löschen der Tabelle 'ziegen': " + e.getMessage());
        }

        // Einfügen der neuen Daten
        for(String name : liste) {
            String sql = "INSERT INTO ziegen(name) VALUES('" + name + "')";
            try {
                db.execSQL(sql);
            } catch(Exception e) {
                Log.e(LOG_TAG,"INSERT: " + e.getMessage() );
            }
        }
    }

    // ---------------------------------------------------------------------------------
}
