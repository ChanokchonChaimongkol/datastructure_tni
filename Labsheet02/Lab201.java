import java.util.ArrayList;

public class Lab201 {
    public static void main(String[] args) {
        // สร้าง ArrayList ชื่อ names เก็บชื่อเล่นของนักเรียนในห้อง
        ArrayList<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Emma");
        names.add("Leon");
        names.add("John");

        // แสดงผลขนาดข้อมูล และ ข้อมูลทั้งหมดใน ArrayList
        System.out.println("Length = " + names.size());
        System.out.println("All names in ArrayList = " + names);
    }
}