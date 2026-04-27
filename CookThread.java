public class CookThread extends Thread {

    private final Order order;

    public CookThread(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        try {
            System.out.println("Preparing Token " + order.getTokenNum() + " ...");

            Thread.sleep(order.getPrepTime() * 1000);

            System.out.println("Token " + order.getTokenNum() + " is Ready!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}