import java.util.*;

public class Order {
    private final int tokenNum;
    private final String customerName;
    private final ArrayList<MenuItem> items = new ArrayList<>();
    private final ArrayList<Integer> quantity = new ArrayList<>();
    private String note = "";
    private int total = 0;

    public Order(int tnum, String customerName) {
        this.tokenNum = tnum;
        this.customerName = customerName;
    }

    void addItem(MenuItem m, int q) {
        items.add(m);
        quantity.add(q);
    }

    public void setNote(String n) {
        this.note = n;
    }

    public int getTokenNum() {
        return tokenNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getPrepTime() {
        int max = 0;
        for (MenuItem m : items) {
            if (m.getPrepTime() > max) {
                max = m.getPrepTime();
            }
        }
        return max;
    }

    public void calculateTotal() {
        total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice() * quantity.get(i);
        }
    }

    public void displayBill() {
        System.out.println("\n===== BILL =====");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Token No: " + tokenNum);
        for (int i = 0; i < items.size(); i++) {
            MenuItem m = items.get(i);
            int q = quantity.get(i);
            System.out.println(m.getName() + " x " + q + " = ₹" + (m.getPrice() * q));
        }
        if (!note.isEmpty()) {
            System.out.println("Note: " + note);
        }
        System.out.println("Total = ₹" + total);
    }

    public String getReceiptText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Name: ").append(customerName).append("\n");
        sb.append("Token No: ").append(tokenNum).append("\n\n");

        for (int i = 0; i < items.size(); i++) {
            MenuItem m = items.get(i);
            int q = quantity.get(i);
            sb.append(m.getName())
              .append(" x ")
              .append(q)
              .append(" = ₹")
              .append(m.getPrice() * q)
              .append("\n");
        }

        if (!note.isEmpty()) {
            sb.append("Note: ").append(note).append("\n");
        }

        sb.append("Total = ₹").append(total).append("\n");
        return sb.toString();
    }

    public String toShort() {
        return "Token " + tokenNum + " | Customer: " + customerName + " | Total: ₹" + total;
    }
}