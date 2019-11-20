package Server;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class DataHandler extends DefaultHandler {
    private boolean isName;
    private boolean isSurname;
    private ArrayList<User> users;

    private ArrayList<String> names;
    private ArrayList<String> surnames;
    private ArrayList<Integer> ids;

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public void startDocument() throws SAXException {
        names = new ArrayList<>();
        surnames = new ArrayList<>();
        ids = new ArrayList<>();
        users = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        for (int i = 0; i < ids.size(); i++) {
            User user = new User();
            user.setID(ids.get(i));
            user.setName(names.get(i));
            user.setSurname(surnames.get(i));
            users.add(user);
        }

    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes attrs) throws SAXException {
        if (qName.equals("name")) {
            isName = true;
        } else if (qName.equals("surname")) {
            isSurname = true;
        }
        if (attrs.getLength() > 0) {
            ids.add(Integer.parseInt(attrs.getValue(0)));
            //user.setID(Integer.parseInt(attrs.getValue(0)));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("name")) {
            isName = false;
        } else if (qName.equals("surname")) {
            isSurname = false;
            //users.add(user);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (isName) {
            //user.setName(str);
            names.add(str);
        } else if (isSurname) {
            //user.setSurname(str);
            surnames.add(str);
        }
    }

}
