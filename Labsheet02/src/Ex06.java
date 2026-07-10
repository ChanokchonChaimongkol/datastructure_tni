
public class Ex06 {
	public static void main(String[] args) {
		
		ArrayController nums = new ArrayController();
		nums.display("All elements: ");
		
		
		
		int delete_index = 2;
		
		int[] new_nums = new int[nums.getSize()-1];  // length ➔ 4
		for (int i=0; i<delete_index; i++) {   // looping between 0 and delete_index-1 (➔ 1)
		   new_nums[i] = nums.getNums()[i];
		}
		// current new_nums ➔ {10, 20, 0, 0}
		for (int i=delete_index+1; i<nums.getSize(); i++) {
		   // looping between delete_index+1 (➔ 3) and nums.length-1 (➔ 4)
		   new_nums[i - 1] = nums.getNums()[i];
		}
		
		nums.setNums(new_nums);
		nums.display("\n\nAfter delete index 2: ");
		
		
		
	
	}
}
