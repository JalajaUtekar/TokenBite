import java.io.*;

public class FileManager {

    public static void saveReceipt(Order order) {
        try {
            try (FileWriter fw = new FileWriter("Receipt_" + order.getTokenNum() + ".txt")) {
                fw.write("===== CAFETERIA RECEIPT =====\n");
                fw.write("Customer Name: " + order.getCustomerName() + "\n");
                fw.write("Token No: " + order.getTokenNum() + "\n\n");
                fw.write(order.getReceiptText());
            }

            System.out.println("Receipt Saved.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}