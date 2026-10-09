import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class linearSearch03 {



    public static int linearSearch(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        BinarySearchTree tree = new BinarySearchTree();
        tree.sampleTree();


        tree.printTree(tree.getRoot(), 0);


        ArrayList<Integer> list = tree.levelOrderTraversal(tree.getRoot());

        System.out.println("Traversal order: " + list);


        int[] nums = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }


        System.out.print("Enter target: ");
        int target = sc.nextInt();


        int index = linearSearch(nums, target);

        if (index != -1) {
            System.out.println(
                    "The target (" + target + ") at index " + index);
        } else {
            System.out.println(
                    "Cannot found " + target + " in this tree");
        }

        sc.close();
    }
}