import java.util.ArrayList;

public class Ex07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> numlist = new ArrayList<Integer>();
		
		System.out.println("Length = " + numlist.size());
		
		numlist.add(11);
		numlist.add(12);
		numlist.add(13);
		numlist.add(14);
		numlist.add(15);
		
		System.out.println("Length = " + numlist.size());
		System.out.println("All elements = " + numlist);

		
		int update_index = 2;
		int update_element = 20;
		
		numlist.set(update_index, update_element);
		System.out.println("All elements = " + numlist);
		
		numlist.remove(2);
		System.out.println("All elements = " + numlist);
		
		numlist.remove(Integer.valueOf(2));
		System.out.println("All elements = " + numlist);
		
		numlist.clear();
		System.out.println("All elements = " + numlist);
		
		
		
	}

}
