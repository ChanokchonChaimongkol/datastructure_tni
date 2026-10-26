import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    static int counter = 1000;
    static final double DELIVERY_FEE = 20.0;

    static final String[] STATUS = {
            "Waiting for restaurant to confirm",
            "Restaurant is preparing your food",
            "Rider is on the way",
            "Delivered"
    };

    int id;
    Restaurant restaurant;
    String address;
    String placedAt;
    int status = 0;

    List<OrderItem> items = new ArrayList<>();

    Order(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    void confirm(String address) {
        this.id = ++counter;
        this.address = address;
        this.placedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    void addItem(int menuIndex, int qty) {
        MenuItem menuItem = restaurant.menu.get(menuIndex);
        for (OrderItem oi : items) {
            if (oi.item == menuItem) {
                oi.qty += qty;
                return;
            }
        }
        items.add(new OrderItem(menuItem, qty));
    }

    boolean removeItem(int pos) {
        if (pos < 0 || pos >= items.size()) return false;
        items.remove(pos);
        return true;
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    double foodTotal() {
        double sum = 0;
        for (OrderItem oi : items) sum += oi.subtotal();
        return sum;
    }

    double grandTotal() {
        return foodTotal() + DELIVERY_FEE;
    }

    boolean isDelivered() {
        return status == STATUS.length - 1;
    }

    void nextStatus() {
        if (!isDelivered()) status++;
    }

    String statusLabel() {
        return STATUS[status];
    }

    void printCart() {
        System.out.println("\n  Your cart");
        if (isEmpty()) {
            System.out.println("    (empty)");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            OrderItem oi = items.get(i);
            System.out.printf("    %d. %-26s x%d = %8.2f%n", i + 1, oi.item.name, oi.qty, oi.subtotal());
        }
        System.out.printf("    Food subtotal: %.2f THB%n", foodTotal());
    }

    void printSummary() {
        System.out.println("\n========== Order Summary ==========");
        System.out.println("Restaurant: " + restaurant.name);
        for (OrderItem oi : items) {
            System.out.printf("  %-26s x%d = %8.2f%n", oi.item.name, oi.qty, oi.subtotal());
        }
        System.out.printf("Food total:   %8.2f THB%n", foodTotal());
        System.out.printf("Delivery fee: %8.2f THB%n", DELIVERY_FEE);
        System.out.printf("Grand total:  %8.2f THB%n", grandTotal());
        System.out.println("Delivery address: " + address);
    }

    void printProgress() {
        System.out.println("\nOrder status #" + id);
        for (int i = 0; i < STATUS.length; i++) {
            String mark;
            if (i < status || isDelivered()) mark = "[x]";
            else if (i == status) mark = "[>]";
            else mark = "[ ]";
            System.out.println("  " + mark + " " + STATUS[i]);
        }
    }

    void printReceipt() {
        String line = "----------------------------------------------";
        System.out.println("\n==============================================");
        System.out.println("                   RECEIPT                     ");
        System.out.println("==============================================");
        System.out.println("Receipt no.: #" + id);
        System.out.println("Date:        " + placedAt);
        System.out.println("Restaurant:  " + restaurant.name);
        System.out.println(line);
        System.out.printf("%-28s %3s %12s%n", "Item", "Qty", "Amount (THB)");
        for (OrderItem oi : items) {
            System.out.printf("%-28s %3d %12.2f%n", oi.item.name, oi.qty, oi.subtotal());
        }
        System.out.println(line);
        System.out.printf("%-32s %12.2f%n", "Food total", foodTotal());
        System.out.printf("%-32s %12.2f%n", "Delivery fee", DELIVERY_FEE);
        System.out.printf("%-32s %12.2f%n", "TOTAL PAID (THB)", grandTotal());
        System.out.println(line);
        System.out.println("Delivery address: " + address);
        System.out.println("Status: " + statusLabel());
        System.out.println("      Thank you for your order!");
        System.out.println("==============================================");
    }
}