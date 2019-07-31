package com.bib.pbs2h17aka.ziegenhof;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;

// Hilfsklassse zur Organisation von Erträgen und Kommunikation mit der SQLite Datenbank
public class ErtragManager {

    // ---------------------------------------------------------------------------------

    // Methode erfasst neuen Ertrag in der SQLite Datenbank
    public static void addErtrag(Context context, Ertrag ertrag) {

        // Öffnen der Datenbank
        ZiegenDB db = new ZiegenDB(context);
        // Ausführen der Insert Methode
        db.addErtrag(ertrag);
        // Schließen der Datenbank
        db.close();
    }

    // ---------------------------------------------------------------------------------

    // Lese alle Ziegen Namen
    public static ArrayList<String> getZiegen(Context context) {

        // Erstellen einer Liste
        ArrayList<String> liste = new ArrayList<>();
        // Öffnen der Datenbank
        ZiegenDB db = new ZiegenDB(context);
        // Abfrage der Ziegen Namen
        liste = db.getZiegenListe();
        // Datenbank schließen
        db.close();

        return liste;
    }

    // ---------------------------------------------------------------------------------

    // Lesen aller Erträge (alle|name)
    public static ArrayList<Ertrag> getErtragLise(Context context, String name) {

        // Erstellen einer Liste
        ArrayList<Ertrag> liste = new ArrayList<>();
        // Öffnen der Datenbank
        ZiegenDB db = new ZiegenDB(context);
        // Ausführen der Insert Methode
        liste = db.getErtragListe(name);
        // Schließen der Datenbank
        db.close();

        return liste;
    }

    // ---------------------------------------------------------------------------------

    // Methode um Ziegen Namen vom Server in SQLite Datenbank zu übertragen
    public static void uebertrageZiegenNamen(Context context, String ip, int port) throws IOException {

        // Daten vom Server abfragen
        ArrayList<String> liste = Netzwerk.getZiegen(ip, port);
        // Datenbank öffnen
        ZiegenDB db = new ZiegenDB(context);
        // Namen in Datenbank schreiben
        db.uebertrageZiegenNamen(liste);
        // Datenbank schließen
        db.close();
    }

    // ---------------------------------------------------------------------------------

}
