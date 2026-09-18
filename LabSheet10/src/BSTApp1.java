
public class BSTApp1 {

	public static void main(String[] args) {
		
		BinarySearchTree tree = new BinarySearchTree();
		
		tree.sampleTree1();
		tree.printTree(tree.getRoot(), 0);
		System.out.println();
		System.out.println("Minimum Node is " + tree.findMinimum());
		System.out.println("Minimum Node is " + tree.findMaximum());
		
		System.out.println("Is 40 in BST => " + tree.findSpecificData(15));
		
		int delNode = 60;
		tree.searchDeleteNode(delNode);
		System.out.println("Parent is " + tree.getParent().data);
		System.out.println("Delete Node is " + tree.getDeleteNode().data);
		
		System.out.println();
		tree.delete(delNode);
		tree.printTree(tree.getRoot(), 0);
		
	}
	
	
	
	
}