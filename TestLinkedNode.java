
public class TestLinkedNode {
	public static void main(String[] args) {
		Node node1 = new Node("Alice");
		System.out.print("node1 created! ");
		System.out.println("Address = " + Node.getAddress(node1));
		System.out.println("Data = " + node1.data);
		System.out.println("Pointer address = " + Node.getAddress(node1.next));
		
		Node node2 = new Node(25);
		System.out.print("\nnode2 created! ");
		System.out.println("Address = " + Node.getAddress(node2));
		System.out.println("Data = " + node2.data);
		System.out.println("Pointer address = " + Node.getAddress(node2.next));
		
		Node node3 = new Node('x');
		System.out.print("\nnode3 created! ");
		System.out.println("Address = " + Node.getAddress(node3));
		System.out.println("Data = " + node3.data);
		System.out.println("Pointer address = " + Node.getAddress(node3.next));
		
		Node node4 = new Node(25.25);
		System.out.print("\nnode4 created! ");
		System.out.println("Address = " + Node.getAddress(node4));
		System.out.println("Data = " + node4.data);
		System.out.println("Pointer address = " + Node.getAddress(node4.next));
		
		
		node1.next = node2;
		System.out.println("\nnode1 point to address: " + Node.getAddress(node1.next));
		System.out.println("node2 address = " + Node.getAddress(node2));
		System.out.println("node2 point to address: " + Node.getAddress(node2.next));

		node2.next = node3;
		System.out.println("\nnode2 point to address: " + Node.getAddress(node2.next));
		System.out.println("node3 address = " + Node.getAddress(node3));
		System.out.println("node3 point to address: " + Node.getAddress(node3.next));

		node3.next = node4;
		System.out.println("\nnode3 point to address: " + Node.getAddress(node3.next));
		System.out.println("node4 address = " + Node.getAddress(node4));
		System.out.println("node4 point to address: " + Node.getAddress(node4.next));

		
	}

}
