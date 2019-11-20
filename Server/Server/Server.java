package Server;

import interfaces.Handler;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public Server() {
        super();
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Handler obj = new HandlerImplementer();
        try {
            Handler stub = (Handler) UnicastRemoteObject.exportObject(obj,0);
            Registry registry = LocateRegistry.getRegistry(0);
            registry.rebind("Handler",stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
