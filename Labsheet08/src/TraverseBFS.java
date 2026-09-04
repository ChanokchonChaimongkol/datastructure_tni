import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class TraverseBFS {

	public static void main(String[] args) {
		
		BinaryTree tree = new BinaryTree();
		tree.createTree3();    // Try to test with createTree2() and createTree3()
		tree.printTree(tree.getRoot(), 0);
		System.out.println();
		System.out.println("Preorder = " + traversal(tree.getRoot()));

	}
	public static ArrayList<Integer> traversal(Node node) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		
		
		if (node != null) {
			if (node != null) {
				Queue<Node> queue = new ArrayDeque<Node>();
				queue.add(node);
				while (!queue.isEmpty()) {
					int levelSize = queue.size();
					for(int i=0; i<levelSize; i++) {
						Node current_node = queue.poll();
						if (current_node.left != null) {
							queue.add(current_node.left);
						}
						if (current_node.right != null) {
							queue.add(current_node.right);
						}
						list.add(current_node.data);
					}
					
				}
			}
            
		}
		return list;	
	}
	
}