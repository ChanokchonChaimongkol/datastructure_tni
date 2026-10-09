import java.util.ArrayList;
import java.util.Scanner;

public class jumpSearch03 {

    public static int jumpSearch(int[] nums, int target) {
        int n = nums.length;

        if (n == 0) {
            return -1;
        }

        int step = (int) Math.sqrt(n);
        int previous = 0;
        int current = step;

        // Jump through blocks
        while (previous < n && nums[Math.min(current, n) - 1] < target) {
            previous = current;
            current += step;

            if (previous >= n) {
                return -1;
            }
        }

        // Linear search inside the block
        while (previous < Math.min(current, n)) {
            if (nums[previous] == target) {
                return previous;
            }

            if (nums[previous] > target) {
                return -1;
            }

            previous++;
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create Binary Search Tree
        BinarySearchTree tree = new BinarySearchTree();
        tree.sampleTree();

        // Print tree
        tree.printTree(tree.getRoot(), 0);

        // Inorder traversal: sorted data
        ArrayList<Integer> list = tree.inorderTraversal(tree.getRoot());

        System.out.println("Traversal order: " + list);

        // Convert ArrayList to int[]
        int[] nums = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }

        // Input target
        System.out.print("Enter target: ");
        int target = sc.nextInt();


        int index = jumpSearch(nums, target);

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