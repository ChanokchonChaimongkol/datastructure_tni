import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Querotate {
	public static Queue<Integer> queue = new ArrayDeque<Integer>();
	
	public static void main(String[] args) {
		originalQueue();
		Scanner input = new Scanner(System.in);
		
        while (true) {
            System.out.print("Press 1 to rotate queue: ");
            int choice = input.nextInt();
            
            if (choice == 1) {
                rotateQueue();
            } else {
                System.out.println("Exit");
                break;
            }
        }
        input.close();
	}
	
	public static void rotateQueue() {
        if (!queue.isEmpty()) {
            int front = queue.poll();
            System.out.println("Calling queue: " + front);
            queue.offer(front);
            System.out.println("Queue => " + queue);
        }
	}
	
	public static void originalQueue() {

		for (int i = 101; i <= 107; i++) {
            queue.offer(i);
        }
        System.out.println("Queue => " + queue);
	}

}