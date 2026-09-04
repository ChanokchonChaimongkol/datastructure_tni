import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TraverseInorder {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.createTree1();    // Try to test with createTree2() and createTree3()
		tree.printTree(tree.getRoot(), 0);
		System.out.println();
		System.out.println("Inorder = " + traversal(tree.getRoot()));
	}
	
	public static ArrayList<Integer> traversal(Node node) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Deque<Node> stack = new ArrayDeque<Node>();

		Node current_node = node;
		
        while(!stack.isEmpty()||current_node!=null) {
        	
        	if(current_node != null) {
        		stack.push(current_node);
        		current_node = current_node.left;
        		
        	}
        	
        	else 
        		
        	{
        		
        		current_node = stack.pop();
        		list.add(current_node.data);
        		current_node = current_node.right;
        		
        	}
        	
        	
        	
        }
		return list;
	  }
	
	}