import java.util.*;

public class Restaurant {
    int id;
    String name;
    String category;
    List<MenuItem> menu = new ArrayList<>();

    Restaurant(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    Restaurant add(String itemName, double price) {
        menu.add(new MenuItem(itemName, price));
        return this;
    }

    void showMenu() {
        System.out.println("\n---------- Menu: " + name + " ----------");
        for (int i = 0; i < menu.size(); i++) {
            MenuItem m = menu.get(i);
            System.out.printf("  %d. %-28s %7.2f THB%n", i + 1, m.name, m.price);
        }
    }
}