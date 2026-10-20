import java.util.*;

/** Restaurant: stores name, category, menu items and prices */
public class Restaurant {
    int id;
    String name;
    String category;
    List<String> itemNames = new ArrayList<>();
    List<Double> itemPrices = new ArrayList<>();

    Restaurant(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    Restaurant add(String itemName, double price) {
        itemNames.add(itemName);
        itemPrices.add(price);
        return this;
    }

    void showMenu() {
        System.out.println("\n---------- Menu: " + name + " ----------");
        for (int i = 0; i < itemNames.size(); i++) {
            System.out.printf("  %d. %-28s %7.2f THB%n", i + 1, itemNames.get(i), itemPrices.get(i));
        }
    }
}