import java.util.*;

/**
 * LineManApp: โปรแกรมหลัก (เมนู + รับ input + 4 ฟีเจอร์)
 *
 * ================= สรุปสำหรับ Present =================
 * | คลาส         | หน้าที่                          | DS / Algorithm หลัก            |
 * |--------------|----------------------------------|--------------------------------|
 * | MenuItem     | เมนู 1 อย่าง (ชื่อ+ราคา)          | Class / Object                 |
 * | Restaurant   | ร้าน + รายการเมนู                 | ArrayList<MenuItem>            |
 * | OrderItem    | 1 บรรทัดในตะกร้า (เมนู+จำนวน)     | Class / Composition            |
 * | Order        | ตะกร้า ยอดเงิน สถานะ ใบเสร็จ      | ArrayList<OrderItem>, Array,   |
 * |              |                                  | Linear Search, Summation, FSM  |
 * | OrderStorage | อ่าน/เขียนไฟล์ orders.txt         | File I/O, Serialization        |
 * | LineManApp   | เมนูหลัก + รับ input              | Linear Search, Validation Loop,|
 * |              |                                  | Menu-driven (Sentinel) Loop    |
 * ======================================================
 * ตัวแปรที่ใช้ในการวิเคราะห์ Big O:
 *   R = จำนวนร้าน, m = จำนวนเมนูต่อร้าน, k = จำนวนรายการในตะกร้า, N = จำนวนออเดอร์
 */
public class LineManApp {
    // [DS] ArrayList<Restaurant> | คำสั่งที่บ่งบอก: new ArrayList<>(), .add(), for-each
    // [Why] จำนวนร้านไม่คงที่ + ต้องวนดูทุกร้านบ่อย ArrayList เหมาะที่สุด
    static List<Restaurant> restaurants = new ArrayList<>();

    // [DS] ArrayList<Order> | เก็บประวัติออเดอร์เรียงตามเวลาที่สั่ง (เพิ่มต่อท้ายเสมอ = O(1))
    static List<Order> orders = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    // [Pattern] Method Chaining: .add().add().add() | [Big O] O(R * m) ทำครั้งเดียวตอนเริ่มโปรแกรม
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

    // ---------- Input helpers ----------

    // [Algo] Input Validation Loop: while(true) + try-catch วนจนกว่าจะได้ตัวเลขที่ถูกต้อง
    // คำสั่งที่บ่งบอก: Integer.parseInt + catch (NumberFormatException)
    // [Big O] O(1) ต่อรอบ | [Why] ผู้ใช้พิมพ์ผิดแล้วโปรแกรมไม่ crash
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

    // [Algo] Input Validation Loop: วนจนกว่าจะไม่ว่าง | [Big O] O(1) ต่อรอบ
    static String readLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("  ! This field cannot be empty.");
        }
    }

    // [Algo] Linear Search: วนทีละตัวเทียบ id ถ้าเจอ return ทันที ไม่เจอ return null
    // คำสั่งที่บ่งบอก: for-each + if (r.id == id) return r;
    // [Big O] O(R) | [Why] มีแค่ไม่กี่ร้าน จึงไม่คุ้มที่จะใช้ HashMap (ที่จะได้ O(1))
    static Restaurant findRestaurant(int id) {
        for (Restaurant r : restaurants) if (r.id == id) return r;
        return null;
    }

    // [Algo] Linear Search | [Big O] O(N)
    // [Why] orders เรียงตาม id อยู่แล้ว (เพิ่มต่อท้ายเสมอ) จึงใช้ Binary Search O(log N) ได้
    //       แต่ข้อมูลน้อย Linear Search เขียนง่ายและเร็วพอ
    static Order findOrder(int id) {
        for (Order o : orders) if (o.id == id) return o;
        return null;
    }

    // [Algo] Traversal | [Big O] O(R)
    static void showRestaurants() {
        System.out.println("\n========== Restaurants ==========");
        for (Restaurant r : restaurants) {
            System.out.printf("[%d] %s (%s)%n", r.id, r.name, r.category);
        }
    }

    // ---------- Feature 1: Browse restaurants and menus ----------
    // [Algo] Traversal + Linear Search | [Big O] O(R + m)
    static void browseRestaurants() {
        showRestaurants();
        int id = readInt("Select a restaurant to view its menu (0 = back): ");
        if (id == 0) return;   // 0 = ค่า sentinel สำหรับออกจากหน้านี้
        Restaurant r = findRestaurant(id);
        if (r == null) {
            System.out.println("  ! Restaurant not found.");
            return;
        }
        r.showMenu();
    }

    // ---------- Feature 2: Place an order ----------
    // [Algo] Menu-driven Loop (Sentinel Loop): ลูปทำงานจนกว่าผู้ใช้เลือก 99 (checkout) หรือ 0 (cancel)
    // [DS] Order object ทำหน้าที่เป็น "ตะกร้า" ระหว่างเลือกของ (ArrayList<OrderItem> ข้างใน)
    // [Big O] O(m + k) ต่อ 1 รอบ เพราะแสดงเมนู O(m) + แสดงตะกร้า O(k) ทุกรอบ
    // [Why] if-else if ตามค่า choice: -1, 0, 99 เป็นคำสั่งพิเศษ ส่วน 1..m คือเลขเมนู
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
                else shopping = false;   // ออกจากลูปไปขั้นตอนยืนยัน
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
                    order.addItem(choice - 1, qty);   // Search and Update O(k)
                    System.out.println("  + Added to cart.");
                }
            } else {
                System.out.println("  ! Invalid choice.");
            }
        }

        order.address = readLine("Delivery address: ");
        order.printSummary();
        if (readLine("Confirm order? (y/n): ").equalsIgnoreCase("y")) {
            order.confirm(order.address);   // ออกเลขออเดอร์ตอนยืนยันเท่านั้น (ถ้ายกเลิกเลขจะไม่เปลือง)
            orders.add(order);              // [DS] ArrayList.add ต่อท้าย O(1)
            OrderStorage.save(orders);      // บันทึกลงไฟล์ทันที
            System.out.println("\n  Order placed successfully! Your order number is #" + order.id);
        } else {
            System.out.println("  Order cancelled.");
        }
    }

    // ---------- Feature 3: Track order status ----------
    // [Algo] Traversal + Linear Search + Finite State Machine | [Big O] O(N) ต่อรอบการแสดงผล
    // [Why] สถานะเปลี่ยนผ่าน nextStatus() ซึ่งเดินหน้าได้ทางเดียว (O(1)) แล้ว save เก็บสถานะใหม่
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
                o.nextStatus();                 // FSM: เปลี่ยนสถานะ O(1)
                OrderStorage.save(orders);
                System.out.println("  -> New status: " + o.statusLabel());
            } else if (cmd.equalsIgnoreCase("b")) {
                return;
            } else {
                System.out.println("  ! Invalid command.");
            }
        }
    }

    // ---------- Feature 4: Order history and receipts ----------
    // [Algo] Traversal + Summation (totalSpent) | คำสั่งที่บ่งบอก: totalSpent += o.grandTotal() ในลูป
    // [Big O] O(N * k) เพราะ grandTotal() ต้องคำนวณใหม่ O(k) ให้ทุกออเดอร์
    // [Why] ไม่เก็บยอดรวมไว้ในตัวแปร (cache) เพื่อให้ข้อมูลตรงกับรายการเสมอ
    //       ถ้าข้อมูลมากขึ้น ค่อยเก็บยอดรวมไว้ตอน confirm เพื่อลดเหลือ O(N)
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
                totalSpent += o.grandTotal();   // Summation
            }
            System.out.printf("%nTotal orders: %d | Total spent: %.2f THB%n", orders.size(), totalSpent);

            int id = readInt("Enter an order number to view its receipt (0 = back): ");
            if (id == 0) return;
            Order o = findOrder(id);   // Linear Search O(N)
            if (o == null) System.out.println("  ! Order not found.");
            else o.printReceipt();
        }
    }

    // ---------- main ----------
    // [Algo] Menu-driven Loop (Sentinel Loop): while (running) วนจนกว่าผู้ใช้เลือก 0
    // [Big O] switch = O(1) ในการเลือกฟีเจอร์ แต่ละฟีเจอร์มี Big O ของตัวเอง
    // [Why] ใช้ switch แทน if-else หลายชั้น อ่านง่ายเมื่อมีหลายตัวเลือกที่เป็นค่าคงที่
    public static void main(String[] args) {
        seedData();
        orders.addAll(OrderStorage.load(restaurants));   // โหลดประวัติจากไฟล์ก่อนเริ่มทำงาน
        System.out.println("=====================================");
        System.out.println("               LINEMAN               ");
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