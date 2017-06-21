/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 *
 * @author Jan
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Benutzerverwaltung benutzerverwaltung = new Benutzerverwaltung();
            //Der Server wird gestartet und unter benutzerverwaltung "veröffentlicht"
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://localhost:1099/benutzerverwaltung", benutzerverwaltung);
            // nun wartet der server auf anfragen vom client...
        } catch (RemoteException ex) {
            System.out.println("Ein remote fehler ist aufgetreten: "+ex.getMessage());
        } catch (AlreadyBoundException ex) {
            System.out.println("Fehler: "+ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der rmi Adresse: "+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Fehler: "+ex.getMessage());
        }
    }
    
}
