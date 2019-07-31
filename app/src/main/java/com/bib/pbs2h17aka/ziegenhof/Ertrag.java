package com.bib.pbs2h17aka.ziegenhof;

// Klasse zur Organisation von Ertragsdaten
public class Ertrag {

    // Ertragsdaten
    private String name;
    private String datum;
    private double liter;

    // Konstruktor
    public Ertrag(String name, String datum, double liter) {
        this.name = name;
        this.datum = datum;
        this.liter = liter;
    }

    // Get Methoden
    public String getName() {
        return this.name;
    }
    public String getDatum() {
        return this.datum;
    }
    public double getLiter() {
        return this.liter;
    }

    // Methode zur formartierten Ausgabe
    @Override
    public String toString() {
        return String.format("%s    %.2f    %s", this.datum, this.liter, this.name);
    }
}
