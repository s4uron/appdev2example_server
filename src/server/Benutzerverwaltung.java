/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Diese Klasse ist die Implementierung des Interfaces BenutzerverwaltungInterface.
 * Sie muss das Interface impelemntieren und mit UnicastRemoteObject erweitert werden.
 * Hier wird die ganze Logik des Programms abgebildet. Es können zum Beispiel Benutzer
 * erstellt werden, alle ausgelesen oder alle gelöscht werden.
 * @author Jan
 */
public class Benutzerverwaltung extends UnicastRemoteObject implements BenutzerverwaltungInterface {

    ArrayList<Benutzer> benutzer;
    Datenbank db;
   
    @Override
    public void benutzerErstellen(String name) throws RemoteException {
        Benutzer b = new Benutzer(name);
        benutzer.add(b); // Benutzer wird lokal im Arbeitsspeicher (also der ArrayList benutzer) hinzugefügt
        db.speicherBenutzer(b); // Benutzer wird zur Datenbank hinzugefügt
        System.out.println("Benutzer "+name+" wurde erstellt");
        
    }
    
    @Override
    public void benutzerLoeschen(String name) throws RemoteException {
        for(Benutzer b : benutzer) {
            if(b.name.equals(name)) {
                benutzer.remove(b);
                db.loescheBenutzer(b);
                System.out.println("Benutzer "+b.name+" gelöscht");
            }
        }
    }

    public Benutzerverwaltung() throws RemoteException, SQLException {
        this.benutzer = new ArrayList<>();
        this.db = new Datenbank(); // die Datenbank wird initiiert
        benutzer.addAll(db.ladeBenutzer()); // alle Benutzer der Datenbank werden geladen und der lokalen
        // Liste im Arbeitsspeicher (also der ArrayList benutzer) hinzugefügt
    }

    @Override
    public String alleBenutzer() throws RemoteException {
        String allebenutzer = "";
        // alle Benutzer werden aus der lokalen liste ausgelesen und in einen String geschrieben und zurück gegeben
        for(Benutzer b : benutzer) {
            allebenutzer += b.name + "\n";
        }
        return allebenutzer;
    }

    @Override
    public void alleBenutzerLoeschen() throws RemoteException {
        db.alleBenutzerLoeschen(); // alle benutzer werden in der Datenbank
        benutzer.clear(); // und lokal gelöscht
        System.out.println("Alle Benutzer wurden gelöscht");
    }
    
}
