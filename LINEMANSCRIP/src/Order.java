import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Order: ตะกร้าสินค้า ยอดเงิน สถานะการจัดส่ง และใบเสร็จ
 *
 * ===== DATA STRUCTURE / ALGORITHM ที่ใช้ในคลาสนี้ =====
 * [DS]   ArrayList<OrderItem>   -> items (ตะกร้าสินค้า)
 * [DS]   Array (String[])       -> STATUS (ตารางข้อความสถานะ)
 * [DS]   Composition (has-a)    -> Order มี Restaurant และมี OrderItem หลายตัว
 * [Algo] Linear Search          -> addItem (หาว่าเมนูนี้อยู่ในตะกร้าแล้วหรือยัง)
 * [Algo] Summation              -> foodTotal()
 * [Algo] Traversal              -> printCart / printSummary / printReceipt
 * [Algo] Finite State Machine   -> status 0 -> 1 -> 2 -> 3
 */
public class Order {
    // [Algo] Auto-increment ID: ++counter ทำให้เลขออเดอร์ไม่ซ้ำ | [Big O] O(1)
    static int counter = 1000;
    static final double DELIVERY_FEE = 20.0;

    // [DS] Array ขนาดคงที่ใช้เป็น Lookup Table: เลข status -> ข้อความ
    // คำสั่งที่บ่งบอก: String[]  { ... }  STATUS[i]  STATUS.length
    // [Big O] STATUS[status] = O(1) | [Why] มีสถานะแค่ 4 ตัวคงที่ ใช้ array ง่ายและเร็วที่สุด
    static final String[] STATUS = {
            "Waiting for restaurant to confirm",
            "Restaurant is preparing your food",
            "Rider is on the way",
            "Delivered"
    };

    int id;
    Restaurant restaurant;   // [DS] Composition (has-a)
    String address;
    String placedAt;
    int status = 0;          // [Algo] State ปัจจุบันของ Finite State Machine

    // [DS] ArrayList<OrderItem>: 1 สมาชิก = 1 บรรทัดในตะกร้า (เมนู + จำนวน)
    List<OrderItem> items = new ArrayList<>();

    Order(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // [Big O] O(1) | [Why] แค่กำหนดค่า ไม่มีลูป
    void confirm(String address) {
        this.id = ++counter;
        this.address = address;
        this.placedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    // [Algo] Search and Update: ค้นหาก่อน ถ้าเจอบวกจำนวน ถ้าไม่เจอเพิ่มใหม่
    // คำสั่งที่บ่งบอก Linear Search: for-each + if (oi.item == menuItem)
    // [Big O] O(k) เมื่อ k = จำนวนรายการในตะกร้า
    // [Why] ตะกร้ามีไม่กี่รายการ Linear Search จึงเร็วพอ ไม่ต้องใช้ HashMap ที่ซับซ้อนกว่า
    void addItem(int menuIndex, int qty) {
        MenuItem menuItem = restaurant.menu.get(menuIndex);   // O(1)
        for (OrderItem oi : items) {
            if (oi.item == menuItem) {   // เจอเมนูเดิมในตะกร้า -> บวกจำนวน
                oi.qty += qty;
                return;
            }
        }
        items.add(new OrderItem(menuItem, qty));   // ไม่เจอ -> เพิ่มบรรทัดใหม่ O(1)
    }

    // [Big O] O(k) | [Why] ArrayList.remove(pos) ต้องเลื่อนสมาชิกที่อยู่หลังตำแหน่งนั้นมาข้างหน้า
    // การตรวจ bounds (pos < 0 || pos >= size) เป็น O(1) ช่วยกัน IndexOutOfBoundsException
    boolean removeItem(int pos) {
        if (pos < 0 || pos >= items.size()) return false;
        items.remove(pos);
        return true;
    }

    boolean isEmpty() {   // [Big O] O(1)
        return items.isEmpty();
    }

    // [Algo] Summation (สะสมค่า) | คำสั่งที่บ่งบอก: ตัวแปร sum เริ่มที่ 0 แล้ว sum += ... ในลูป
    // [Big O] O(k) | [Why] ต้องบวกทุกรายการ ไม่มีทางข้ามได้
    double foodTotal() {
        double sum = 0;
        for (OrderItem oi : items) sum += oi.subtotal();
        return sum;
    }

    double grandTotal() {   // [Big O] O(k) เพราะเรียก foodTotal()
        return foodTotal() + DELIVERY_FEE;
    }

    boolean isDelivered() {   // [Big O] O(1)
        return status == STATUS.length - 1;
    }

    // [Algo] Finite State Machine (เครื่องสถานะอย่างง่าย): เดินหน้าทางเดียว 0 -> 1 -> 2 -> 3 แล้วหยุด
    // คำสั่งที่บ่งบอก: status++ พร้อมเงื่อนไขกันเกินสถานะสุดท้าย
    // [Big O] O(1) | [Why] ป้องกันสถานะย้อนกลับหรือเกินขอบเขต ทำให้ข้อมูลถูกต้องเสมอ
    void nextStatus() {
        if (!isDelivered()) status++;
    }

    String statusLabel() {   // [Big O] O(1) เพราะเข้าถึง array ด้วย index
        return STATUS[status];
    }

    // [Algo] Traversal | [Big O] O(k) | [Why] ต้องแสดงทุกรายการในตะกร้า
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

    // [Algo] Traversal | [Big O] O(k)
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

    // [Algo] Traversal บน Array STATUS | [Big O] O(s) เมื่อ s = จำนวนสถานะ (คงที่ 4 จึงถือเป็น O(1))
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

    // [Algo] Traversal | [Big O] O(k) | ใบเสร็จ = สรุปรายการทั้งหมดของออเดอร์
    void printReceipt() {
        String line = "----------------------------------------------";
        System.out.println("\n==============================================");
        System.out.println("                   RECEIPT");
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