package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Handler extends Remote {
    void add(String name,String surname) throws RemoteException;

    String getAll() throws RemoteException;

    void formXML() throws RemoteException;

    void readXML() throws RemoteException;
}
