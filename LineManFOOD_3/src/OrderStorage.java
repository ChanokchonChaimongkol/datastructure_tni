import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class OrderStorage {
    static final String FILE_NAME = "orders.txt";

    static void save(List<Order> orders) {
        List<String> lines = new ArrayList<>();
        for (Order o : orders) lines.add(toLine(o));
        try {
            Files.write(Paths.get(FILE_NAME), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("  ! Could not save order history: " + e.getMessage());
        }
    }

    static List<Order> load(List<Restaurant> restaurants) {
        List<Order> result = new ArrayList<>();
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) return result;
        try {
            for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                if (line.trim().isEmpty()) continue;
                Order o = fromLine(line, restaurants);
                if (o != null) result.add(o);
            }
        } catch (IOException e) {
            System.out.println("  ! Could not load order history: " + e.getMessage());
        }
        return result;
    }

    private static String toLine(Order o) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < o.items.size(); i++) {
            OrderItem oi = o.items.get(i);
            if (i > 0) sb.append(",");
            sb.append(o.restaurant.menu.indexOf(oi.item)).append(":").append(oi.qty);
        }
        return o.id + "|" + o.placedAt + "|" + o.restaurant.id + "|" + o.status + "|"
                + o.address.replace("|", " ") + "|" + sb;
    }

    private static Order fromLine(String line, List<Restaurant> restaurants) {
        try {
            String[] p = line.split("\\|", -1);
            if (p.length < 6) return null;

            int restaurantId = Integer.parseInt(p[2]);
            Restaurant r = null;
            for (Restaurant x : restaurants) if (x.id == restaurantId) r = x;
            if (r == null) return null;

            Order o = new Order(r);
            o.id = Integer.parseInt(p[0]);
            o.placedAt = p[1];
            o.status = Integer.parseInt(p[3]);
            if (o.status < 0 || o.status >= Order.STATUS.length) return null;
            o.address = p[4];

            for (String part : p[5].split(",")) {
                String[] kv = part.split(":");
                int menuIndex = Integer.parseInt(kv[0]);
                int qty = Integer.parseInt(kv[1]);
                if (menuIndex < 0 || menuIndex >= r.menu.size() || qty <= 0) return null;
                o.addItem(menuIndex, qty);
            }
            if (o.isEmpty()) return null;

            if (o.id > Order.counter) Order.counter = o.id;
            return o;
        } catch (RuntimeException e) {
            return null;
        }
    }
}