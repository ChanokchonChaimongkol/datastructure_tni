
public class TestLinkNode {

	public static void main(String[] args) {
		
		Node node1 = new Node("Alice");
	      System.out.print("node1 created! ");
	      System.out.println("Address = " + Node.getAddress(node1));
	      System.out.println("Data    = " + node1.data);
	      System.out.println("Pointer address = " + Node.getAddress(node1.next));
	      Node node2 = new Node(25);
	      System.out.print("\nnode2 created! ");
	      System.out.println("Address = " + Node.getAddress(node2));
	      System.out.println("Data    = " + node2.data);
	      System.out.println("Pointer address = " + Node.getAddress(node2.next));
	      node1.next = node2;
	      System.out.println("\nnode1 point to address: " + Node.getAddress(node1.next));
	      System.out.println("node2 address = " + Node.getAddress(node2));
	      System.out.println("node2 point to address: " + Node.getAddress(node2.next));
	      Node node3 = new Node(true);
	      Node node4 = new Node(true);
	      
	      node2.next = node3;
	      node3.next = node4;
	      
	      System.out.println("\n point to => " + Node.getAddress(node2.next));
	      System.out.println("node2 address = " + Node.getAddress(node3));
	      
	      
	      
	   }
	}