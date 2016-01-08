package NineChapter5DP;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
/*LIS T:O(n^2), Space:O(n)
// Zero: null check 
// First: 
// Find a position to insert in previous sorted list
//         create a temp list to add all nums[i] >= res.get(j)
//         once smaller than => break => add current element into temp list
// 
// compare current temp list with previous sorted list with length and last element if length equal
*/
/*LISOptimize  T:O(n log n), Space:O(n)
// Zero: null check 
// First: 
// 0.Create a maxLen 
// 1.Find a position to insert in previous sorted list
			int index = searchForInsertPosition(res, nums[i]);
			// NOTE: j <= index-1 since index is the next insert position
			for (int j = 0 ; j <= index-1;j++){
				temp.add(res.get(j));
			}
			temp.add(nums[i]);
// 2.Compare current temp list with previous sorted list with length and last element if length equal
? when move existing element to a new list =>O(n)
? 
*/
public class LongestIncreasingSubsequence {
	/*LIS T:O(n^2), Space:O(n)
		// Zero: null check 
	  	// First: 
        // Find a position to insert in previous sorted list
		//         create a temp list to add all nums[i] >= res.get(j)
		//         once smaller than => break => add current element into temp list
		// 
		// compare current temp list with previous sorted list with length and last element if length equal
	 */
	public ArrayList<Integer> LIS(int[] nums){
		ArrayList<Integer> res=  new ArrayList<Integer>();
		// Zero: null check
		if (nums == null || nums.length == 0)
			return res;
		// First: 
		// 0.Create a maxLen
        // 1.Find a position to insert in previous sorted list
		//         create a temp list to add all nums[i] >= res.get(j)
		//         once smaller than => break => add current element into temp list
		// 2.Compare current temp list with previous sorted list with length and last element if length equal
		
		//int maxLen = 1;
		//res.add(nums[0]);
		int maxLen =0;
		for (int i = 0 ; i < nums.length; i++){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			
			// NOTE: list will have all elements  instead of increasing elements since we just take those elements less than current element value
			//for (int j = 0 ; j < i; j++){
					//if (nums[j] <= nums[i]){
					//	temp.add(nums[j]);
					//}
			//}
			//temp.add(nums[i]);
			// NOTE: list will reset and only include current element instead of combing it with the part of previous sorted list which are less than current element value
			//if (nums[i] >= res.get(res.size()-1)){
			//	temp.addAll(res);
			//	temp.add(nums[i]);
			//}else{
			//	temp.add(nums[i]);
			//}
			
			//1. Find a position to insert in previous sorted list
			for (int j = 0 ; j < res.size(); j++ ){
				if (nums[i] >= res.get(j)){
					temp.add(res.get(j));
				} else {
					break;
				}
			}
			temp.add(nums[i]);
			//2. Compare current temp list with previous sorted list with length and last element if length equal
			// NOTE: > wouldn't update {10} to {1} and sorted list minimum value is 10 not 1 but 1 is better for appending s
			//       >= would update {10} to {1} and sorted list minimum value is 1 not 10
			if (temp.size() > maxLen || (temp.size() == maxLen && temp.get(temp.size() -1) <= res.get(res.size() -1) )){
				res = temp;
				maxLen = temp.size();
			}			
		}
		return res;
	}
	/*LISOptimize  T:O(n log n), Space:O(n)
		// Zero: null check 
		// First: 
		// 0.Create a maxLen 
        // 1.Find a position to insert in previous sorted list
				int index = searchForInsertPosition(res, nums[i]);
				// NOTE: j <= index-1 since index is the next insert position
				for (int j = 0 ; j <= index-1;j++){
					temp.add(res.get(j));
				}
				temp.add(nums[i]);
		// 2.Compare current temp list with previous sorted list with length and last element if length equal
	    ? when move existing element to a new list =>O(n)
	    ? 
	*/
	public ArrayList<Integer> LISOptimize(int[] nums){
		ArrayList<Integer> res=  new ArrayList<Integer>();
		// Zero: null check
		if (nums == null || nums.length == 0)
			return res;
		// First: 
		// 0.Create a maxLen		
        // 1.Find a position to insert in previous sorted list
		// 2.Compare current temp list with previous sorted list with length and last element if length equal
		// // NOTE: j <= index-1 since index is the next insert position
		int maxLen = 0;
		for (int i = 0 ; i < nums.length;i++){
			// 1.Find a position to insert in previous sorted list
			ArrayList<Integer> temp=  new ArrayList<Integer>();
			/*
			if (res.size() == 0 || res.get(res.size()-1) <= nums[i]) {
				temp.addAll(res);
				temp.add(nums[i]);	

			} else {
				int index = searchForInsertPosition(res, nums[i]);
				// NOTE: j <= index-1 since index is the next insert position
				for (int j = 0 ; j <= index-1;j++){
					temp.add(res.get(j));
				}
				temp.add(nums[i]);
			}
			*/
			int index = searchForInsertPosition(res, nums[i]);
			// NOTE: j <= index-1 since index is the next insert position
			for (int j = 0 ; j <= index-1;j++){
				temp.add(res.get(j));
			}
			temp.add(nums[i]);
			// 2.Compare current temp list with previous sorted list with length and last element if length equal
			if (temp.size() > maxLen || (temp.size() == maxLen && temp.get(temp.size() -1) <= res.get(res.size() -1) )){
				res = temp;
				maxLen = temp.size();
			}	
		}
		return res;
	}	
	/*searchForInsertPosition T:O(logn) , S:O(1)
	 * 		// Zero: null check
			// First: Binary search for insert position
				// "="  since target could be equal to 
	  			// since we put new element in right position  
	 * */
	private int searchForInsertPosition(ArrayList<Integer> list, int target){
		// Zero: null check
		if (list == null || list.size() == 0)
			return -1;
		// First: Binary search for insert position
		int l = 0;
		int r = list.size() -1;
		while (l <= r){// "="  since target could be equal to 
			int m = (l+r) /2;
			if (list.get(m) == target){
				return m;
			}
			if (list.get(m) > target){
				r = m-1;
			} else {
				l = m+1;
			}
		}
		return l; // since we put new element in right position  
	}	
	public static void main(String[] args){
		int[] nums = new int[]{4, 2, 4, 5, 3, 7};//LIS is [4, 4, 5, 7], return 4
		LongestIncreasingSubsequence sol = new LongestIncreasingSubsequence();
		System.out.println(Arrays.toString(nums)+"\n Longest Increasing Subsequence: "+sol.LIS(nums));
		int[] nums2= new int[]{10,1,11,2,12,3,11};//LIS is (1, 2, 3, 11), return 4
		System.out.println(Arrays.toString(nums2)+"\n Longest Increasing Subsequence: "+sol.LIS(nums2));
		System.out.println(Arrays.toString(nums)+"\n Longest Increasing Subsequence Fast : "+sol.LISOptimize(nums));
		System.out.println(Arrays.toString(nums2)+"\n Longest Increasing Subsequence Fast: "+sol.LISOptimize(nums2));
	}
}
