import java.util.*;

/**
 * Restaurant: ข้อมูลร้านอาหาร + รายการเมนู
 *
 * [DS] ArrayList<MenuItem> (เหลือลิสต์เดียวแล้ว ไม่ต้องใช้ Parallel Lists)
 *      คำสั่งที่บ่งบอก: new ArrayList<>()  .add()  .get(i)  .size()
 *      [Big O] get(i) = O(1), add() = O(1) แบบเฉลี่ย (amortized)
 *      [Why] จำนวนเมนูไม่คงที่ ArrayList ขยายเองได้ และเข้าถึงด้วย index เร็วที่สุด
 * [DS] Composition (has-a): Restaurant "มี" MenuItem หลายตัว
 */
public class Restaurant {
    int id;
    String name;
    String category;
    List<MenuItem> menu = new ArrayList<>();   // [DS] ArrayList ของ MenuItem

    Restaurant(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    // [Pattern] Method Chaining: return this ทำให้เขียน .add().add().add() ต่อกันได้
    // [Big O] O(1) amortized | [Why] ต่อท้ายลิสต์ ไม่ต้องเลื่อนสมาชิกตัวอื่น
    Restaurant add(String itemName, double price) {
        menu.add(new MenuItem(itemName, price));
        return this;
    }

    // [Algo] Traversal (วนอ่านทุกตัว) | คำสั่งที่บ่งบอก: for (int i = 0; i < size; i++)
    // [Big O] O(m) เมื่อ m = จำนวนเมนู
    // [Why] ต้องแสดงทุกเมนูอยู่แล้ว จึงไม่มีวิธีที่เร็วกว่า O(m)
    void showMenu() {
        System.out.println("\n---------- Menu: " + name + " ----------");
        for (int i = 0; i < menu.size(); i++) {
            MenuItem m = menu.get(i);
            System.out.printf("  %d. %-28s %7.2f THB%n", i + 1, m.name, m.price);
        }
    }
}