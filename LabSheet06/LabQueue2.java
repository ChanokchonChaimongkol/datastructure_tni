import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

public class LabQueue2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter some message: ");
        String message = input.next();
        System.out.println();
        System.out.println("Check Palindrome by Deque = "
                + (checkByDeque(message.toLowerCase()) ? "Palindrome" : "Not Palindrome"));
        System.out.println("Check Palindrome by Stack = "
                + (checkByStack(message.toLowerCase()) ? "Palindrome" : "Not Palindrome"));
    }

    public static boolean checkByDeque(String message) {
        Deque<Character> letters = new ArrayDeque<Character>();
        for (char c : message.toCharArray()) {
            letters.addLast(c);
        }
        while (letters.size() > 1) {
            char first = letters.pollFirst();
            char last = letters.pollLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkByStack(String message) {
        Queue<Character> queue = new ArrayDeque<Character>();
        Deque<Character> stack = new ArrayDeque<Character>();

        for (char c : message.toCharArray()) {
            queue.offer(c);
            stack.push(c);
        }

        while (!queue.isEmpty()) {
            char q = queue.poll();
            char s = stack.pop();
            if (q != s) {
                return false;
            }
        }
        return true;
    }
}