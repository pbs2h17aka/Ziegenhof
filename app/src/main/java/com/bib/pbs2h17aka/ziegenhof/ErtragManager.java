package com.bib.pbs2h17aka.ziegenhof;

import android.content.Context;

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

        // Ofline Testdaten
        liste.add("Alle");
        liste.add("Lotte");
        liste.add("Frieda");
        liste.add("Esmeralda");
        liste.add("Gudrun");
        liste.add("Elfriede");

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

}
