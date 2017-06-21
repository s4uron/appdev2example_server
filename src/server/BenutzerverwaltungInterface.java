/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Das Interface der Benutzerverwaltung damit der Client später weiß, welche Methoden er auf dem Server
 * ausführen lassen kann. Die Methoden werfen (throws) RemoteExpcetion.
 * @author Jan
 */
public interface BenutzerverwaltungInterface extends Remote {
    
    public abstract void benutzerErstellen(String name) throws RemoteException;
    
    public abstract String alleBenutzer() throws RemoteException;
    
    public abstract void alleBenutzerLoeschen() throws RemoteException;
}
