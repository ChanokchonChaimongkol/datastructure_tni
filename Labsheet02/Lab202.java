import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Lab202 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int lastDigit       = 7;
        int secondLastDigit = 4;

        int[] initial_numbers = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int value : initial_numbers) {
            numbers.add(value);
        }
        System.out.println("Initial numbers: " + numbers);

        System.out.print("Input last numbers: ");
        int addEndValue = sc.nextInt();
        numbers.add(addEndValue);
        System.out.println("After add to end (" + addEndValue + "): " + numbers);

        System.out.print("Input insert number " + lastDigit + ": ");
        int insertValue = sc.nextInt();
        numbers.add(lastDigit, insertValue);
        System.out.println("After insert at index " + lastDigit + " (" + insertValue + "): " + numbers);

        numbers.remove(lastDigit);
        System.out.println("After remove at index " + lastDigit + ": " + numbers);

        int today = LocalDate.now().getDayOfMonth();
        numbers.set(secondLastDigit, today);
        System.out.println("After update at index " + secondLastDigit + " with today's date (" + today + "): " + numbers);

        System.out.println("Final numbers in ArrayList = " + numbers);

        sc.close();
    }
}