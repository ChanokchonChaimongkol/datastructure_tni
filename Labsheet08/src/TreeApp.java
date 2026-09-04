
public class TreeApp {

	public static void main(String[] args) {
		System.out.println("----------------------------------");
		System.out.println("Binary tree from createTree1()");
		System.out.println("----------------------------------");
		
		BinaryTree tree = new BinaryTree();
		tree.createTree1();
		tree.printTree(tree.getRoot(), 0);
		
		System.out.println("----------------------------------");
		System.out.println("Binary tree from createTree2()");
		System.out.println("----------------------------------");
		tree.createTree2();
		tree.printTree(tree.getRoot(), 0);
		
		System.out.println("----------------------------------");
		System.out.println("Binary tree from createTree3()");
		System.out.println("----------------------------------");
		tree.createTree3();
		tree.printTree(tree.getRoot(), 0);

	}

}
