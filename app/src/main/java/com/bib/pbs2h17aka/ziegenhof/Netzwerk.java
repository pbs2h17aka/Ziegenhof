package com.bib.pbs2h17aka.ziegenhof;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

// Klasse zur Implementierung der Netzwerk Funktionen
public class Netzwerk {

    private static final String LOG_TAG = "Netzwerk";

    // ---------------------------------------------------------------------------------

    // Methode um Ziegen Namen vom Server abzufragen
    public static ArrayList<String> getZiegen(String ip, int port) throws IOException {

        // Socket deklarieren
        Socket so = null;

        // Verbindung zum Server herstellen
        try {
            // Socket initialisieren
            so = new Socket();
            // Socket mit Server verbinden
            so.connect(new InetSocketAddress(InetAddress.getByName(ip),port),1000);
        } catch(Exception e) {
            Log.e(LOG_TAG, "Gerät nicht erreichbar: " + e.getMessage());
            throw new IOException("Gerät nicht erreichbar:\n" + e.getMessage());
        }

        // Wenn Client erfolgreich mit Server verbunden
        Log.i(LOG_TAG, String.format("Client: verbunden mit %s:%d%n", so.getInetAddress(), so.getPort()));

        // Array Liste für Ziegen Namen erstellen
        ArrayList<String> liste = new ArrayList<>();

        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(so.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())));
            ) {

            // Request an den Server senden
            out.println("GET ZIEGEN\n");
            out.flush();
            Log.i(LOG_TAG, "Request 'GET ZIEGEN' erfolgreich an Server gesendet");

            // Result vom Server lesen
            // Ziegen Namen in Array Liste speichern
            String name = in.readLine();
            while(name != null) {
                liste.add(name);
                name = in.readLine();
                Log.i(LOG_TAG,"Request: " + name + " erfolgreich gelesen");
            }
        } catch(Exception e) {
            Log.e(LOG_TAG,"Netzwerkfehler: " + e.getMessage());
            throw new IOException("Netzwerkfehler: " + e.getMessage());
        }

        // Socket schließen
        try {so.close();} catch(Exception e) {}

        return liste;
    }

    // ---------------------------------------------------------------------------------

    // Methode um Erträge an Server zu senden
    public static void sendeErtragListe(ArrayList<Ertrag> liste, String ip, int port) throws IOException {

        // Socket deklarieren
        Socket so = new Socket();

        try {
            // Socket mit dem Server verbinden
            so.connect(new InetSocketAddress(InetAddress.getByName(ip),port),1000);
        } catch(Exception e){
            Log.e(LOG_TAG,"Gerät nicht erreichbar: " + e.getMessage());
            throw new IOException("Gerät nicht erreichbar:\n" + e.getMessage());
        }

        // Wenn Client erfolgreich mit Server verbunden ist
        Log.v(LOG_TAG, String.format("Client: erfolgreich verbunden mit Server auf %s:%d", so.getInetAddress(),so.getPort()));

        // Daten an den Server übertragen
        try(
            BufferedReader in = new BufferedReader(new InputStreamReader(so.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())));
        ) {
            // Auf dem Server Socket für Datenempfang informieren
            out.println("PUT DATA");
            out.flush();
            // Schreiben aller Datensätze in den Output Stream
            for(Ertrag e : liste) {
                out.println(String.format("%s,%s,$d", e.getName(), e.getDatum(),e.getLiter()));
            }
            // Senden des Streams
            out.flush();
        } catch(Exception e) {
            Log.e(LOG_TAG, "Netzwerkfehler: " + e.getMessage());
            throw new IOException("Netzwerkfehler:\n" + e.getMessage());
        }

        // Socket schließen
        try {so.close();} catch(Exception e) {}
    }
}
