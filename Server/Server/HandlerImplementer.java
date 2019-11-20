package Server;

import interfaces.Handler;

import java.util.ArrayList;


public class HandlerImplementer implements Handler {
    private ArrayList<User> users = new ArrayList<>();
    public void add(String name,String surname) {
        User user = new User(name,surname);
        users.add(user);
    }

    public void formXML() {
        XML.CreateXML(users);
    }

    public String getAll() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            builder.append(users.get(i).toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    public void readXML() {
        users = XML.readXML();
    }
}

