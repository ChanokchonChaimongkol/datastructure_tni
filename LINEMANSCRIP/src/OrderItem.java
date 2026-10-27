/**
 * OrderItem: รายการในตะกร้า 1 บรรทัด (เมนู 1 อย่าง + จำนวนที่สั่ง)
 *
 * [DS] Class / Object + Composition (has-a): OrderItem "มี" MenuItem
 * [Why] แทน Parallel Lists เดิม (itemIndexes + qtys) ด้วย object เดียว
 *       เมนูกับจำนวนผูกกันอยู่ใน object เดียว ลบหรือแก้ทีเดียวไม่มีทางเพี้ยน
 * [Big O] subtotal() = O(1)
 */
public class OrderItem {
    final MenuItem item;   // อ้างอิงเมนูของร้าน (ไม่คัดลอกชื่อ/ราคามาซ้ำ ราคาแก้ที่เดียวคือ MenuItem)
    int qty;

    OrderItem(MenuItem item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    // ราคารวมของรายการนี้ = ราคาต่อชิ้น x จำนวน
    double subtotal() {
        return item.price * qty;
    }
}