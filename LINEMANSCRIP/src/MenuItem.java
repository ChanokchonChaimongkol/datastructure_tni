/**
 * MenuItem: เมนูอาหาร 1 อย่าง (ชื่อ + ราคา)
 *
 * [DS] Class / Object: รวมข้อมูลที่เกี่ยวข้องกันไว้ด้วยกัน
 * [Why] แทนที่จะมี itemNames กับ itemPrices แยกกัน (Parallel Lists ต้องคอยดูว่า index ตรงกัน)
 *       ตอนนี้ 1 object = 1 เมนู ข้อมูลชื่อกับราคาจึงไม่มีทางหลุดคู่กัน
 * [Big O] การเข้าถึง field (item.name, item.price) = O(1)
 */
public class MenuItem {
    final String name;
    final double price;

    MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}