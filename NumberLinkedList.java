
public class NumberLinkedList {

	public static void main(String[] args) {
		
		LinkedList numbers = new LinkedList();
		
		numbers.insert(0, 37);
		numbers.insert(0, 7);
		numbers.insert(0, 4);
		numbers.insert(0, 16);
		System.out.println("All element = "+ numbers.travesal());
		
		numbers.insert(2, 67);
		System.out.println("All element = "+ numbers.travesal());
		
		numbers.insert(25);
		System.out.println("All element = "+ numbers.travesal());
		
		numbers.remove(2);
		System.out.println("All element = "+ numbers.travesal());
		
	}

}
