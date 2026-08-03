import java.util.ArrayDeque;
import java.util.Queue;

public class LabQueue01 {
    public static void main(String[] args) {
        Queue<Integer> q_number = new ArrayDeque<Integer>();

        // เพิ่มหมายเลขคิว 101-105 เข้าคิวด้วยการวนลูป
        for (int i = 101; i <= 105; i++) {
            q_number.offer(i);
            System.out.println("Enqueue: " + i);
        }
        System.out.println("Queue => " + q_number);
        System.out.println();

        // ดึงหมายเลขคิวออกไปเรื่อยๆ จนกว่าคิวจะว่าง
        while (!q_number.isEmpty()) {
            int number = q_number.poll();
            System.out.println("Calling number: " + number);
            System.out.println("Providing service number: " + number);
            System.out.println();
        }

        System.out.println("Queue => " + q_number);
    }
}