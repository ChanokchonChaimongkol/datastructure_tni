
public class Ex03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayController nums = new ArrayController();
		nums.display("All elements: ");
		
		int[] new_nums = new int[nums.getSize()+1];
		
		for (int i=0; i<nums.getSize(); i++) {
			new_nums[i] = nums.getNums()[i];
			
			
		}
		nums.setNums(new_nums);
		nums.display("\n\nAfter add size: ");
		
		nums.getNums()[nums.getSize()-1] = 15;
		nums.display("\n\nAfter add the last element: ");
		
		
		
		
		
	}

}
