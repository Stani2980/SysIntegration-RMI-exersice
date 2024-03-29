/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studadminserver;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author stanislavnovitski
 */
public class StudAdminServer {

    public StudAdminServer() throws RemoteException {
    }

    public static Registry registry;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("RMI server localhost starts");

            // Create a server registry at default port 1099
            registry = LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry created ; ");

            // Create engine of remote services, running on the server
            StudentDataImplementation remoteEngine = new StudentDataImplementation();

            // Give a name to this engine
            String engineName = "StudentRegistry";

            // Register the engine by the name, which later will be given to the clients
            Naming.rebind("//localhost/" + engineName, remoteEngine);
            
            System.out.println("Engine " + engineName + " bound in registry");
        } catch (Exception e) {
            System.err.println("Exception:" + e);
        }
    }

}
