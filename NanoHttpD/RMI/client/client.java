package client;

import interfaces.Handler;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class client {

    private static void PrintMenu() {
        System.out.println("1.Add");
        System.out.println("2.GetInfo");
        System.out.println("3.FormXML");
        System.out.println("4.GetFromXML");
        System.out.println("5.Exit");
    }
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Handler handler = (Handler) registry.lookup("Handler");

            boolean sign = true;
            Scanner scanner = new Scanner(System.in);
            PrintMenu();
            while (sign) {
                System.out.println("Choose");
                int case_ask = scanner.nextInt();
                switch (case_ask) {
                    case 1: {
                        scanner.nextLine();
                        System.out.println("Enter name");
                        String name = scanner.nextLine();
                        System.out.println("Enter surname"); //fix line missing
                        String surname = scanner.nextLine();
                        handler.add(name,surname);
                        break;
                    }
                    case 2: {
                        System.out.println(handler.getAll());
                        break;
                    }
                    case 3: {
                        handler.formXML();
                        System.out.println("xml created");
                        break;
                    }
                    case 4: {
                        handler.readXML();
                        System.out.println("added from xml");
                        break;
                    }
                    case 5: {
                        sign = false;
                        break;
                    }
                }
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
