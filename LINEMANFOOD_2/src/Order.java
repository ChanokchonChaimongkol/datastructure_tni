import java.util.*;

/** Order: stores cart, totals and delivery status */
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
    int status = 0;
    List<Integer> itemIndexes = new ArrayList<>();
    List<Integer> qtys = new ArrayList<>();

    Order(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    void confirm(String address) {
        this.id = ++counter;
        this.address = address;
    }

    void addItem(int itemIndex, int qty) {
        int pos = itemIndexes.indexOf(itemIndex);
        if (pos >= 0) {
            qtys.set(pos, qtys.get(pos) + qty);
        } else {
            itemIndexes.add(itemIndex);
            qtys.add(qty);
        }
    }

    boolean removeItem(int pos) {
        if (pos < 0 || pos >= itemIndexes.size()) return false;
        itemIndexes.remove(pos);
        qtys.remove(pos);
        return true;
    }

    boolean isEmpty() {
        return itemIndexes.isEmpty();
    }

    double foodTotal() {
        double sum = 0;
        for (int i = 0; i < itemIndexes.size(); i++) {
            sum += restaurant.itemPrices.get(itemIndexes.get(i)) * qtys.get(i);
        }
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
        for (int i = 0; i < itemIndexes.size(); i++) {
            String name = restaurant.itemNames.get(itemIndexes.get(i));
            double sub = restaurant.itemPrices.get(itemIndexes.get(i)) * qtys.get(i);
            System.out.printf("    %d. %-26s x%d = %8.2f%n", i + 1, name, qtys.get(i), sub);
        }
        System.out.printf("    Food subtotal: %.2f THB%n", foodTotal());
    }

    void printSummary() {
        System.out.println("\n========== Order Summary ==========");
        System.out.println("Restaurant: " + restaurant.name);
        for (int i = 0; i < itemIndexes.size(); i++) {
            String name = restaurant.itemNames.get(itemIndexes.get(i));
            double sub = restaurant.itemPrices.get(itemIndexes.get(i)) * qtys.get(i);
            System.out.printf("  %-26s x%d = %8.2f%n", name, qtys.get(i), sub);
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
}