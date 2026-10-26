import java.util.*;

public class LineManApp {
    static List<Restaurant> restaurants = new ArrayList<>();

    static List<Order> orders = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    static void seedData() {
        restaurants.add(new Restaurant(1, "Uncle Ung's Chicken Rice", "Rice Dishes")
                .add("Steamed Chicken Rice", 50).add("Fried Chicken Rice", 55)
                .add("Mixed Chicken Rice", 60).add("Soup", 10));
        restaurants.add(new Restaurant(2, "Auntie Daeng's Boat Noodles", "Noodles")
                .add("Pork Boat Noodles", 45).add("Beef Boat Noodles", 55)
                .add("Fried Wontons", 30));
        restaurants.add(new Restaurant(3, "Pizza Corner", "Western")
                .add("Hawaiian Pizza", 199).add("Seafood Pizza", 229)
                .add("Spaghetti Carbonara", 129).add("Coke", 25));
        restaurants.add(new Restaurant(4, "Jasmine Bubble Tea", "Beverages")
                .add("Pearl Milk Tea", 45).add("Matcha Green Tea", 55).add("Iced Cocoa", 50));
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  ! Please enter a valid number.");
            }
        }
    }

    static String readLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("  ! This field cannot be empty.");
        }
    }

    static Restaurant findRestaurant(int id) {
        for (Restaurant r : restaurants) if (r.id == id) return r;
        return null;
    }

    static Order findOrder(int id) {
        for (Order o : orders) if (o.id == id) return o;
        return null;
    }

    static void showRestaurants() {
        System.out.println("\n========== Restaurants ==========");
        for (Restaurant r : restaurants) {
            System.out.printf("[%d] %s (%s)%n", r.id, r.name, r.category);
        }
    }

    static void browseRestaurants() {
        showRestaurants();
        int id = readInt("Select a restaurant to view its menu (0 = back): ");
        if (id == 0) return;
        Restaurant r = findRestaurant(id);
        if (r == null) {
            System.out.println("  ! Restaurant not found.");
            return;
        }
        r.showMenu();
    }

    static void placeOrder() {
        showRestaurants();
        int rid = readInt("Select a restaurant to order from (0 = cancel): ");
        if (rid == 0) return;
        Restaurant r = findRestaurant(rid);
        if (r == null) {
            System.out.println("  ! Restaurant not found.");
            return;
        }

        Order order = new Order(r);
        boolean shopping = true;
        while (shopping) {
            r.showMenu();
            order.printCart();
            System.out.println("\n  [item no.] Add to cart | [-1] Remove item | [99] Checkout | [0] Cancel");
            int choice = readInt("Choose: ");

            if (choice == 0) {
                System.out.println("  Order cancelled.");
                return;
            } else if (choice == 99) {
                if (order.isEmpty()) System.out.println("  ! Your cart is empty. Please add an item first.");
                else shopping = false;
            } else if (choice == -1) {
                if (order.isEmpty()) {
                    System.out.println("  ! Your cart is already empty.");
                } else {
                    int pos = readInt("Remove which cart item (cart number): ");
                    System.out.println(order.removeItem(pos - 1) ? "  - Item removed." : "  ! Item not found.");
                }
            } else if (choice >= 1 && choice <= r.menu.size()) {
                int qty = readInt("Quantity: ");
                if (qty <= 0) {
                    System.out.println("  ! Quantity must be greater than 0.");
                } else {
                    order.addItem(choice - 1, qty);
                    System.out.println("  + Added to cart.");
                }
            } else {
                System.out.println("  ! Invalid choice.");
            }
        }

        order.address = readLine("Delivery address: ");
        order.printSummary();
        if (readLine("Confirm order? (y/n): ").equalsIgnoreCase("y")) {
            order.confirm(order.address);
            orders.add(order);
            OrderStorage.save(orders);
            System.out.println("\n  Order placed successfully! Your order number is #" + order.id);
        } else {
            System.out.println("  Order cancelled.");
        }
    }

    static void trackOrder() {
        if (orders.isEmpty()) {
            System.out.println("\n  You have no orders yet. Please place an order first.");
            return;
        }
        System.out.println("\n========== Your Orders ==========");
        for (Order o : orders) {
            System.out.printf("#%d | %-28s | %s%n", o.id, o.restaurant.name, o.statusLabel());
        }

        int id = readInt("Enter an order number to view details (0 = back): ");
        if (id == 0) return;
        Order o = findOrder(id);
        if (o == null) {
            System.out.println("  ! Order not found.");
            return;
        }

        while (true) {
            o.printSummary();
            o.printProgress();
            if (o.isDelivered()) {
                System.out.println("\n  This order has been delivered. Thank you for using our service!");
                return;
            }
            String cmd = readLine("\n[u] Simulate status update | [b] Back: ");
            if (cmd.equalsIgnoreCase("u")) {
                o.nextStatus();
                OrderStorage.save(orders);
                System.out.println("  -> New status: " + o.statusLabel());
            } else if (cmd.equalsIgnoreCase("b")) {
                return;
            } else {
                System.out.println("  ! Invalid command.");
            }
        }
    }

    static void orderHistory() {
        if (orders.isEmpty()) {
            System.out.println("\n  No order history yet. Please place an order first.");
            return;
        }
        while (true) {
            System.out.println("\n========================= Order History =========================");
            System.out.printf("%-6s | %-16s | %-28s | %11s | %s%n", "Order", "Date", "Restaurant", "Total (THB)", "Status");
            double totalSpent = 0;
            for (Order o : orders) {
                System.out.printf("#%-5d | %-16s | %-28s | %11.2f | %s%n",
                        o.id, o.placedAt, o.restaurant.name, o.grandTotal(), o.statusLabel());
                totalSpent += o.grandTotal();
            }
            System.out.printf("%nTotal orders: %d | Total spent: %.2f THB%n", orders.size(), totalSpent);

            int id = readInt("Enter an order number to view its receipt (0 = back): ");
            if (id == 0) return;
            Order o = findOrder(id);
            if (o == null) System.out.println("  ! Order not found.");
            else o.printReceipt();
        }
    }

    public static void main(String[] args) {
        seedData();
        orders.addAll(OrderStorage.load(restaurants));
        System.out.println("=====================================");
        System.out.println("          Welcome to LINEMAN         ");
        System.out.println("=====================================");
        if (!orders.isEmpty()) {
            System.out.println("Loaded " + orders.size() + " previous order(s) from " + OrderStorage.FILE_NAME);
        }

        boolean running = true;
        while (running) {
            System.out.println("\n========== Main Menu ==========");
            System.out.println("1. Browse restaurants and menus");
            System.out.println("2. Place an order");
            System.out.println("3. Track order status");
            System.out.println("4. Order history and receipts");
            System.out.println("0. Exit");

            switch (readInt("Choose an option: ")) {
                case 1: browseRestaurants(); break;
                case 2: placeOrder(); break;
                case 3: trackOrder(); break;
                case 4: orderHistory(); break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using our service. See you again!");
                    break;
                default: System.out.println("  ! Invalid option.");
            }
        }
    }
}