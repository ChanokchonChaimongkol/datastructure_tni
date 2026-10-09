import java.util.ArrayList;
import java.util.Scanner;

public class binarySearch03 {

    public static int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        BinarySearchTree tree = new BinarySearchTree();
        tree.sampleTree();


        tree.printTree(tree.getRoot(), 0);


        ArrayList<Integer> list = BinarySearchTree.inorderTraversal(tree.getRoot());

        System.out.println("Traversal order: " + list);


        int[] nums = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }


        System.out.print("Enter target: ");
        int target = sc.nextInt();


        int index = binarySearch(nums, target);

        if (index != -1) {
            System.out.println("The target (" + target + ") at index " + index);
        } else {
            System.out.println("Cannot found " + target + " in this tree");
        }

        sc.close();
    }
}