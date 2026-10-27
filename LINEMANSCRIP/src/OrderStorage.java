import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * OrderStorage: อ่านและเขียนประวัติออเดอร์ลงไฟล์ (File I/O)
 *
 * [Algo] Serialization (object -> ข้อความ) และ Parsing / Deserialization (ข้อความ -> object)
 * รูปแบบ 1 ออเดอร์ = 1 บรรทัด:  id|เวลา|รหัสร้าน|สถานะ|ที่อยู่|เมนูลำดับที่:จำนวน,เมนูลำดับที่:จำนวน
 * [Why] แยกออกมาเป็นคลาสเฉพาะ เพื่อให้ Order และ LineManApp ไม่ต้องรู้เรื่องรูปแบบไฟล์
 *       ถ้าเปลี่ยนไปใช้ฐานข้อมูลในอนาคต แก้คลาสนี้ที่เดียว
 */
public class OrderStorage {
    static final String FILE_NAME = "orders.txt";   // ไฟล์เก็บประวัติ (ไม่หายเมื่อปิดโปรแกรม)

    // [Algo] Traversal + Serialization: วนทุกออเดอร์ แปลงเป็น 1 บรรทัด แล้วเขียนทับไฟล์
    // คำสั่งที่บ่งบอก: Files.write(...) | [Big O] O(N * k * m)
    //   N = ออเดอร์, k = รายการในตะกร้า, m = เมนูต่อร้าน (เพราะ indexOf ใน toLine)
    // [Why] เขียนทับทั้งไฟล์เพราะสถานะของออเดอร์เก่าเปลี่ยนได้ (append อย่างเดียวแก้บรรทัดเก่าไม่ได้)
    static void save(List<Order> orders) {
        List<String> lines = new ArrayList<>();
        for (Order o : orders) lines.add(toLine(o));
        try {
            Files.write(Paths.get(FILE_NAME), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("  ! Could not save order history: " + e.getMessage());
        }
    }

    // [Algo] Traversal + Parsing: อ่านทีละบรรทัด แปลงกลับเป็น Order แล้วเก็บลง ArrayList
    // [Big O] O(N * (R + k)) | ทำครั้งเดียวตอนเปิดโปรแกรม
    // [Why] บรรทัดที่เสียจะถูกข้าม (fromLine คืน null) โปรแกรมจึงไม่พัง
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

    // [Algo] Serialization | [DS] StringBuilder | [Big O] O(k * m)
    // [Why] ใช้ StringBuilder เพราะการต่อ String ด้วย + ในลูปสร้าง object ใหม่ทุกรอบ (ช้าและเปลืองหน่วยความจำ)
    // [Algo] Linear Search: menu.indexOf(...) หาว่าเมนูนี้เป็นลำดับที่เท่าไรของร้าน
    private static String toLine(Order o) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < o.items.size(); i++) {
            OrderItem oi = o.items.get(i);
            if (i > 0) sb.append(",");
            sb.append(o.restaurant.menu.indexOf(oi.item)).append(":").append(oi.qty);
        }
        return o.id + "|" + o.placedAt + "|" + o.restaurant.id + "|" + o.status + "|"
                + o.address.replace("|", " ") + "|" + sb;   // replace กันอักขระ | ในที่อยู่ทำให้รูปแบบไฟล์พัง
    }

    // [Algo] Parsing / Deserialization ด้วย split() + Integer.parseInt()
    // [Algo] Linear Search หาร้านจาก id | [Big O] O(R + k)
    // [Why] try-catch + ตรวจ bounds: ถ้าไฟล์เสียหรือถูกแก้มือ โปรแกรมจะข้ามบรรทัดนั้นแทนที่จะพัง (return null)
    private static Order fromLine(String line, List<Restaurant> restaurants) {
        try {
            String[] p = line.split("\\|", -1);   // แยกด้วยอักขระ | (ต้อง escape เป็น \\|)
            if (p.length < 6) return null;

            int restaurantId = Integer.parseInt(p[2]);
            Restaurant r = null;
            for (Restaurant x : restaurants) if (x.id == restaurantId) r = x;   // Linear Search
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

            if (o.id > Order.counter) Order.counter = o.id;   // ให้เลขออเดอร์ใหม่ไม่ซ้ำกับของเก่า
            return o;
        } catch (RuntimeException e) {
            return null;
        }
    }
}