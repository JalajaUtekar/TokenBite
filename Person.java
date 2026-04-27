
public class Person {

    protected String name;
    protected int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
    }
}

class Admin extends Person {

    private final String username;
    private final String password;

    public Admin(String name, int id, String username, String password) {
        super(name, id);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void adminWork() {
        System.out.println(name + " is managing menu items.");
    }
}

class Staff extends Person {

    private final String username;
    private final String password;

    public Staff(String name, int id, String username, String password) {
        super(name, id);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void staffWork() {
        System.out.println(name + " is preparing orders.");
    }
}

class Customer extends Person {

    public Customer(String name, int id) {
        super(name, id);
    }

    public void customerWork() {
        System.out.println(name + " is placing an order.");
    }
}