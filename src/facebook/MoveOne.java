package facebook;
/*
 * Remove Element 将一个01随机数列中，
 * 把1全部移到左边，
 * 要求移动次数最少
 * Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
/*Approach#1, minimize operations
 * MoveOneToLeft
 * 		// Zero:corner case, null check
	// First: create a NonTarget value index and swap nonTarget value to nonTarget Index. Minimize operation by iterating same direction

 */
/*Approach#2, Not minimize operations
 * MoveOneToLef2
 * 		// Zero:corner case, null check
	// First: create a keep trying iterating index and end swap index, COND: iterIdx < swapIdx

 */	
import java.util.List;

public class MoveOne {
	/*Approach#1
	 * MoveOneToLeft
	 * 		// Zero:corner case, null check
		// First: create a NonTarget value index and swap nonTarget value to nonTarget Index. Minimize operation by iterating same direction

	 */
	public void MoveOneToLeft(int[] nums){
		// Zero:corner case, null check
		if (nums == null || nums.length == 0)
			return;
		// First: create a NonTarget value index and swap nonTarget value to nonTarget Index. Minimize operation by iterating same direction
		int nonOne = nums.length-1;
		//for (int i = 0 ; i < nums.length; i++){
		//Afer Move: 
		// 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1
		// NOTE: need same direction, some no need to swap, just stay still
		for (int i = nums.length-1 ; i >= 0; i--){
			if (nums[i] != 1){
				int temp = nums[i];
				nums[i] = nums[nonOne];
				nums[nonOne] = temp;
				nonOne--;
			}
		}
	}
	/*Approach#2
	 * MoveOneToLef2
	 * 		// Zero:corner case, null check
		// First: create a keep trying iterating index and end swap index, COND: iterIdx < swapIdx

	 */	
	public void MoveOneToLeft2(int[] nums){
		// Zero:corner case, null check
		if (nums == null || nums.length == 0)
			return;
		// First: create a keep trying iterating index and end swap index, COND: iterIdx < swapIdx
		int i = 0;
		int swapIndex = nums.length-1;
		while( i < swapIndex){
			if (nums[i] != 1){
				int temp = nums[swapIndex];
				nums[swapIndex] = nums[i];
				nums[i] = temp;
				swapIndex--;
			}else{
				i++;
			}
		}
	}	
	public static void main(String[] args){
	
	int[] nums = new int[]{0,1,0,0,1,0,1,1,0,0,0,0};
	MoveOne sol = new MoveOne();
	System.out.println("Before Move: ");
	for (int a: nums){
		System.out.print(a+", ");
	}
	
	System.out.println();	
	long startTime = System.nanoTime();    
	sol.MoveOneToLeft(nums);
	// ... the code being measured ...    
	long estimatedTime = System.nanoTime() - startTime;		
	System.out.println("Approach#1 Afer Move: "+estimatedTime+"ns");
	for (int a: nums){
		System.out.print(a+", ");
	}	
	
	int[] nums2 = new int[]{0,1,0,0,1,0,1,1,0,0,0,0};	
	System.out.println("-----------------");	
	long startTime2 = System.nanoTime();    
	sol.MoveOneToLeft2(nums2);
	// ... the code being measured ...    
	long estimatedTime2 = System.nanoTime() - startTime2;		
	System.out.println("Approach#2 Afer Move: "+estimatedTime2+"ns");
	for (int a: nums2){
		System.out.print(a+", ");
	}		
	}
}
