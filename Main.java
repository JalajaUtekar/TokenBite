import java.util.*;

public class Main{

    static Scanner sc = new Scanner(System.in);

    static ArrayList<MenuItem> menu = new ArrayList<>();
    static Queue<Order> queue = new LinkedList<>();

    static int tokenCounter = 101;

    public static void main(String[] args){

        loadDefaultMenu();

        Admin admin = new Admin("Anish", 1, "AnishDharmadhikari", "B24CE1113");
        Staff staff1 = new Staff("Sahil", 2, "SahilBadve", "B24CE1114");
        Staff staff2 = new Staff("Kaivalya", 3, "KaivalyaGirme", "B24CE1120");
        Staff staff3 = new Staff("Jalaja", 4, "JalajaUtekar", "B24CE1119");

        while(true){

            System.out.println("\n===== SMART CAFETERIA MANAGEMENT SYSTEM =====");
            System.out.println("1. Customer Panel");
            System.out.println("2. Staff Panel");
            System.out.println("3. Admin Panel");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    customerPanel();
                    break;

                case 2:
                    staffPanel(staff1, staff2, staff3);
                    break;

                case 3:
                    adminPanel(admin);
                    break;

                case 4:
                    System.out.println("Thank you.");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void loadDefaultMenu(){

        menu.add(new MenuItem(1,"Tea",10,"Drink",true,2));
        menu.add(new MenuItem(2,"Coffee",20,"Drink",true,3));
        menu.add(new MenuItem(3,"Cold Coffee",40,"Drink",true,4));
        menu.add(new MenuItem(4,"Lemon Juice",25,"Drink",true,3));

        menu.add(new MenuItem(5,"Samosa",25,"Snack",true,4));
        menu.add(new MenuItem(6,"Vada Pav",20,"Snack",true,5));
        menu.add(new MenuItem(7,"Sandwich",60,"Snack",true,5));
        menu.add(new MenuItem(8,"French Fries",70,"Snack",true,6));

        menu.add(new MenuItem(9,"Burger",80,"Food",true,7));
        menu.add(new MenuItem(10,"Pizza",120,"Food",true,10));
        menu.add(new MenuItem(11,"Pasta",100,"Food",true,8));
        menu.add(new MenuItem(12,"Noodles",90,"Food",true,8));

        menu.add(new MenuItem(13,"Veg Thali",140,"Meal",true,12));
        menu.add(new MenuItem(14,"Paneer Rice",110,"Meal",true,10));
        menu.add(new MenuItem(15,"Biryani",130,"Meal",true,12));

        menu.add(new MenuItem(16,"Ice Cream",50,"Dessert",true,2));
    }

    static void customerPanel(){

        System.out.print("Enter Customer Name: ");
        String cname = sc.nextLine();

        Customer customer = new Customer(cname, tokenCounter);

        customer.customerWork();

    Order order = new Order(tokenCounter++, cname);
        while(true){

            System.out.println("\n----- MENU -----");

            for(MenuItem m : menu)
                System.out.println(m);

            System.out.print("Enter Item ID: ");
            int id = sc.nextInt();

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            boolean found = false;

            for(MenuItem m : menu){

                if(m.getID() == id){

                    found = true;

                    if(m.isAvailable()){
                        order.addItem(m, qty);
                    }
                    else{
                        System.out.println("Item currently unavailable.");
                    }
                }
            }

            if(!found){
                System.out.println("Invalid Item ID.");
            }

            System.out.print("Add more items? (y/n): ");
            char ch = sc.next().charAt(0);

            if(ch=='n' || ch=='N')
                break;
        }

        if(order.getPrepTime() == 0){
            System.out.println("No valid items selected.");
            sc.nextLine();
            return;
        }

        sc.nextLine();

        System.out.print("Any Note: ");
        String note = sc.nextLine();

        order.setNote(note);
        order.calculateTotal();
        order.displayBill();

        queue.add(order);

        FileManager.saveReceipt(order);

        System.out.println("Order added to queue.");
    }

    static void staffPanel(Staff s1, Staff s2, Staff s3) {
        
    System.out.print("\nEnter Staff Username: ");
    String uname = sc.nextLine();

    System.out.print("Enter Staff Password: ");
    String pass = sc.nextLine();

    Staff active;

    if (uname.equals(s1.getUsername()) && pass.equals(s1.getPassword())) {
        active = s1;
    } else if (uname.equals(s2.getUsername()) && pass.equals(s2.getPassword())) {
        active = s2;
    } else if (uname.equals(s3.getUsername()) && pass.equals(s3.getPassword())) {
        active = s3;
    } else {
        System.out.println("Wrong username or password. Redirecting to Main Menu...");
        return;
    }

    active.staffWork();

    if (queue.isEmpty()) {
        System.out.println("No Pending Orders.");
        return;
    }

    System.out.println("\nPending Orders:");
    for (Object obj : queue) {
        Order o = (Order) obj;
        System.out.println(o.toShort());
    }

    System.out.println("\n1. Prepare Next Order");
    System.out.println("2. Back");

    System.out.print("Enter choice: ");
    int ch = sc.nextInt();

    if (ch == 1) {
        Order next = (Order) queue.poll();
        CookThread cook = new CookThread(next);
        cook.start();
    }
}

static void adminPanel(Admin admin) {

    sc.nextLine(); // clear buffer if needed

    System.out.print("\nEnter Admin Username: ");
    String uname = sc.nextLine();

    System.out.print("Enter Admin Password: ");
    String pass = sc.nextLine();

    if (!uname.equals(admin.getUsername()) || !pass.equals(admin.getPassword())) {
        System.out.println("Wrong username or password. Redirecting to Main Menu...");
        return;
    }

    admin.adminWork();

    while (true) {

        System.out.println("\n--- ADMIN PANEL ---");
        System.out.println("1. View Menu");
        System.out.println("2. Toggle Availability");
        System.out.println("3. Back");

        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {

            case 1 -> {
                System.out.println("\nCurrent Menu:");
                for (Object obj : menu) {
                    MenuItem m = (MenuItem) obj;
                    System.out.println(m);
                }
            }

            case 2 -> {
                System.out.println("\nSelect Item ID:");
                for (Object obj : menu) {
                    MenuItem m = (MenuItem) obj;
                    System.out.println(m);
                }

                int id = sc.nextInt();

                for (Object obj : menu) {
                    MenuItem m = (MenuItem) obj;
                    if (m.getID() == id) {
                        m.setAvailability(!m.isAvailable());
                        System.out.println("Availability Updated.");
                        break;
                    }
                }
            }

            case 3 -> {
                return;
            }

            default -> System.out.println("Invalid Choice");
        }
    }
}
}