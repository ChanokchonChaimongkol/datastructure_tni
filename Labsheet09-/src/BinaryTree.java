import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    private Node root;
    private Node parent;
    private Node deleteNode;

    public BinaryTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public Node getParent() {
        return parent;
    }

    public Node getDeleteNode() {
        return deleteNode;
    }

    public void printTree(Node node, int depth) {
        if (node != null) {
            printTree(node.right, depth + 1);
            System.out.println("    ".repeat(depth) + node.data);
            printTree(node.left, depth + 1);
        }
    }

    public void createTree1() {
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        root.right.left = new Node(6);
    }

    public void createTree2() {
        root = new Node(15);
        root.left = new Node(8);
        root.left.left = new Node(4);
        root.left.left.left = new Node(2);
        root.left.right = new Node(11);
        root.right = new Node(23);
        root.right.left = new Node(19);
        root.right.left.right = new Node(20);
    }

    public void createTree3() {
        root = new Node(50);
        root.left = new Node(10);
        root.left.left = new Node(11);
        root.left.right = new Node(12);
        root.right = new Node(22);
        root.right.right = new Node(24);
        root.right.right.left = new Node(23);
    }

    public void createTree4() {
        int[] nums = {10, 8, 15, 2, 9, 18, 14, 20, 11, 17};
        for (int i : nums) {
            insert(i);
        }
    }

    public void createTree5() {
        int[] nums = {50, 30, 10, 20, 25, 40, 45, 70, 60, 55, 65};
        for (int i : nums) {
            insert(i);
        }
    }

    public void createTree6() {
        int[] nums = {40, 20, 30, 25, 35, 32, 70, 55, 85, 80};
        for (int i : nums) {
            insert(i);
        }
    }

    public void insert(int new_data) {
        if (root == null) {
            root = new Node(new_data);
        } else {
            Node current_node = root;
            while (true) {
                if (new_data < current_node.data) {
                    if (current_node.left == null) {
                        current_node.left = new Node(new_data);
                        break;
                    }
                    current_node = current_node.left;
                } else {
                    if (current_node.right == null) {
                        current_node.right = new Node(new_data);
                        break;
                    }
                    current_node = current_node.right;
                }
            }
        }
    }

    public void serchDeleteNode(int traget) {
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node current_node = queue.poll();
            parent = current_node;

            if (parent.data == traget) {
                deleteNode = parent;
                break;
            }

            for (int i = 0; i < levelSize; i++) {
                if (current_node.left != null) {
                    if (current_node.left.data == traget) {
                        deleteNode = current_node.left;
                        queue.clear();
                        break;
                    }
                    queue.add(current_node.left);
                }
                if (current_node.right != null) {
                    if (current_node.right.data == traget) {
                        deleteNode = current_node.right;
                        queue.clear();
                        break;
                    }
                    queue.add(current_node.right);
                }
            }
            System.out.println("Parent = " + parent.data);
            System.out.println("DeleteNode = " + deleteNode);
        }
    }

    public void delete(int traget) {
        serchDeleteNode(traget);

        if (root == null) {
            System.out.println("Empty tree");
        } else if (deleteNode == null) {
            System.out.println("Cannot found data");
        } else if (deleteNode == root && deleteNode.left == null && deleteNode.right == null) {
            // ลบ root ที่เป็น node เดียวในต้นไม้
            root = null;
        } else {
            // case1: leaf node
            if (deleteNode.left == null && deleteNode.right == null) {
                if (parent.left == deleteNode) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // case2: 2 children
            else if (deleteNode.left != null && deleteNode.right != null) {
                Node succesorParent = deleteNode;
                Node successor = deleteNode.right;
                while (successor.left != null) {
                    succesorParent = successor;
                    successor = successor.left;
                }
                deleteNode.data = successor.data;
                if (succesorParent.left == successor) {
                    succesorParent.left = successor.right;
                } else {
                    succesorParent.right = successor.right;
                }
            }
            // case3: 1 child
            else {
                Node child = (deleteNode.left != null) ? deleteNode.left : deleteNode.right;
                if (deleteNode == root) {
                    root = child;
                } else if (parent.left == deleteNode) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        }
    }

    public Node findNode(int N) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.data == N) {
                return current;
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return null;
    }
}