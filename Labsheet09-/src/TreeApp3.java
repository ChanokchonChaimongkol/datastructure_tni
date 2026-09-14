public class TreeApp3 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        System.out.println("----------------------------");
        System.out.println("Binary tree from createTree5()");
        System.out.println("----------------------------");
        tree.createTree5();
        tree.printTree(tree.getRoot(), 0);

        System.out.println();
        System.out.println("----------------------------");
        System.out.println("Binary tree from createTree5() + delete leaf node (25)");
        System.out.println("----------------------------");
        tree.delete(25);
        tree.printTree(tree.getRoot(), 0);

        System.out.println();
        System.out.println("----------------------------");
        System.out.println("Binary tree from createTree5() + delete 1 chile node (40)");
        System.out.println("----------------------------");
        tree.delete(40);
        tree.printTree(tree.getRoot(), 0);

        System.out.println();
        System.out.println("----------------------------");
        System.out.println("Binary tree from createTree5() + delete 2 chile node (60)");
        System.out.println("----------------------------");
        tree.delete(60);
        tree.printTree(tree.getRoot(), 0);
    }
}