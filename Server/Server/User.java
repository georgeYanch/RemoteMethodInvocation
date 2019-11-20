package Server;

public class User {
    private String name;
    private String surname;
    private int ID;
    private static int IDinc = 1;

    public User(String name,String surname) {
        this.name = name;
        this.surname = surname;
        this.ID = this.ID + IDinc;
        IDinc++;
    }

    public User() {};

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "id: "+getID()+"\nName: "+getName()+" "+getSurname();
    }
}
