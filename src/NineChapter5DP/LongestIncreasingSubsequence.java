package NineChapter5DP;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {
	/*LIS T:O(n^2), Space:O(n)
	 * */
	public ArrayList<Integer> LIS(int[] nums){
		ArrayList<Integer> res=  new ArrayList<Integer>();
		// Zero: null check
		if (nums == null || nums.length == 0)
			return res;
		// First: 
		//        for each index check 0 to so far and find the longest increasing subsequence
		int maxLen = 1;
		res.add(nums[0]);
		
		for (int i = 1 ; i < nums.length; i++){
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

			for (int j = 0 ; j < res.size(); j++ ){
				if (nums[i] >= res.get(j)){
					temp.add(res.get(j));
					break;
				}
			}
			temp.add(nums[i]);
			if (temp.size() > maxLen){
				res = temp;
				maxLen = temp.size();
			}			
		}
		return res;
	}
	/*LISOptimize  T:O(n log n), Space:O(n)
	 * maintain a increasing sorted list and search for insert position for each index O(logn)
	 * */
	public ArrayList<Integer> LISOptimize(int[] nums){
		ArrayList<Integer> res=  new ArrayList<Integer>();
		// Zero: null check
		if (nums == null || nums.length == 0)
			return res;
		// First: 
		//        for each index check 0 to so far and find the longest increasing subsequence
		
		
		return res;
	}	
	/*searchForInsertPosition T:O(logn) , S:O(1)
	 * 		// Zero: null check
			// First: Binary search for insert position
				// "="  since target could be equal to 
	 * 			// since we put new element in right position  
	 * */
	private int searchForInsertPosition(int[] nums, int target){
		// Zero: null check
		if (nums == null || nums.length == 0)
			return -1;
		// First: Binary search for insert position
		int l = 0;
		int r = nums.length -1;
		while (l <= r){// "="  since target could be equal to 
			int m = (l+r) /2;
			if (nums[m] == target){
				return m;
			}
			if (nums[m] > target){
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
		System.out.println(Arrays.toString(nums2)+"\n Longest Increasing Subsequence: "+sol.LIS(nums2));;
	}
}
