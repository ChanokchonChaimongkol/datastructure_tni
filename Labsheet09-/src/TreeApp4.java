import java.util.Scanner;


public class TreeApp4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("------------------------------");
        System.out.println("Binary Tree from createTree6()");
        System.out.println("------------------------------");

        BinaryTree tree6 = new BinaryTree();
        tree6.createTree6();
        tree6.printTree(tree6.getRoot(), 0);

        System.out.print("\nEnter a number to found in the tree: ");
        int N = scanner.nextInt();

        // find Node
        Node targetNode = tree6.findNode(N);

        // if not found
        if (targetNode == null) {
            System.out.println("Cannot found Node(data=" + N + ")");
            return;
        }

        // Left Child
        if (targetNode.left != null) {
            System.out.println("Left Child Node is " + targetNode.left.data);
        } else {
            System.out.println("No Left Child");
        }

        // Right Child
        if (targetNode.right != null) {
            System.out.println("Right Child Node is " + targetNode.right.data);
        } else {
            System.out.println("No Right Child");
        }

        // Leaf Node
        if (targetNode.left == null && targetNode.right == null) {
            System.out.println(N + " is Leaf Node");
        } else {
            System.out.println(N + " is not Leaf Node");
        }

    }
}

