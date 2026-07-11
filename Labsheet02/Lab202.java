import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab202 {

    public static void main(String[] args)
    {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        int[] initial_numbers = {10, 12, 13, 14, 15, 16, 17, 18, 19};

        for(int Numbers : initial_numbers)
            numbers.add(Numbers);

        System.out.print(numbers);

        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter number to update: ");
        int number_update = scan.nextInt();
        numbers.add(number_update);
        System.out.print(numbers);

        System.out.print("\nEnter number to update: ");
        int num1_update = scan.nextInt();
        int index_update = 0;
        numbers.add(index_update, num1_update);
        System.out.print(numbers);

        System.out.print("\n");
        int ind1_update = 9;
        numbers.remove(ind1_update);
        System.out.println(numbers);

        System.out.print("\nEnter the date : ");
        int num2_update = scan.nextInt();
        int ind2_update = 9;
        numbers.set(ind2_update, num2_update);
        System.out.print(numbers);


    }

}