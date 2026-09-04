
public class BinaryTree {

	private Node root;

	public BinaryTree() {
		root = null;
	}
	
	public Node getRoot() {
		return root;
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
	
}