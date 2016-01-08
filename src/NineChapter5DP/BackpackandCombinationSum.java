package NineChapter5DP;

import java.util.ArrayList;
import java.util.Arrays;
/*
 *				SUMMARY A[0..n-1], == Target [equal]				
 * 
    			Given N non-negative integers and a target, 
  				Can we pick up any numbers from the N integers and its sum equals to the target. 
  				Return true of false
				4 items with size [2, 3, 5, 7], the target size is 17, 
				we can select 2, 3, 5, and 7  
 * 
 * http://buttercola.blogspot.com/2014/10/nine-chapter-lesson-5-dynamic.html
 * */
public class BackpackandCombinationSum {
	/*bpSum T:O(n^2), S:O(n^2)
	 *     0  1  2 3 4 5 ...10 ..........17
	 * ""  T,init
	 * 
	 * 2   T     T,pick 
	 * 
	 * 3         T     T,pick 
	 * 
	 * 5               T     T,pick       
	 *  
	 * 7                     T,propagate T,pick
	 * 
	 * NOTE: || target < 0
	 *		// Zero : null check 
	 *		// First: DP
				//       [nums.length+1][target+1] since initial condition dp[0][0] = true, no element and target is Zero value
				//        i start from 1 with value and j start from 0
				//       //  Not picked and propagate dp[i][j] = dp[i-1][j];
				//       //  Picked and update        dp[i][j] = dp[i][j] || dp[i-1][j - nums[i-1]];
				//                                   why or since we consider two possibilities so OR the result
	 * 		// Second: scan whole table to get first true cell associated value
	 * */
	public ArrayList<ArrayList<Integer>> bpSum(int[] nums, int target){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		// Zero : null check
		if (nums == null || nums.length == 0 || target < 0)
			return res;
		// First: DP
		//       [nums.length+1][target+1] since initial condition dp[0][0] = true, no element and target is Zero value
		//        i start from 1 with value and j start from 0
		//       // Not picked and propagate dp[i][j] = dp[i-1][j];
		//       //  Picked and update       dp[i][j] = dp[i][j] || dp[i-1][j - nums[i-1]];
		//                                   why or since we consider two possibilities so OR the result
		boolean[][] dp = new boolean[nums.length+1][target+1];
		dp[0][0] = true;
		for (int i = 1 ; i <= nums.length; i++){
			for (int j = 0 ; j <= target; j++){
				// Not picked and propagate 
				//dp[i][j] = dp[i-1][j];
				boolean nopick = dp[i-1][j];
				boolean pick = false;
				// Picked and update : current element < target => target -current element >= 0
				if(j - nums[i-1] >= 0){
					//dp[i][j] = dp[i][j] || dp[i-1][j - nums[i-1]];
					pick = dp[i-1][j - nums[i-1]];
				}
				// add this line
				dp[i][j] =  nopick || pick;
			}
		}
		//return dp[nums.length][target];
		// Second: scan whole table to get first true cell associated value
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= nums.length;i++){
			for (int j = 0 ; j <= target;j++) {
				if (dp[i][j]){
					list.add(nums[i-1]);
					break;
				}
			}
		}
		res.add(list);
		return res;
	}
	/*bpSumRecursive T:O(n logn)=> O(n!), S:O(n) recusrive
	 * 		// Zero : null check
			// First: sort the array
			// Second: recursive sum from index 0

	 * */	
	public ArrayList<ArrayList<Integer>>  bpSumRecursiveCombinationSum(int[] nums, int target){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		// Zero : null check
		if (nums == null || nums.length == 0 || target < 0)
			return res;
		// First: sort the array
		Arrays.sort(nums);
		// Second: recursive sum fro mindex 0
		helper(nums, 0, 0, target, new ArrayList<Integer>(), res);
		return res;
	}	
	/*helper
	 * 
	 * // Base Case: sum check and start index check
	 * // Recursive case: loop all elements different from previous and update sum and list 
	 * */
	private void helper(int[] nums, int start, int sum, int target, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> res){
		// Base Case: sum check and start index check
		if (sum > target || start > nums.length){
			return; 
		}
		if (sum == target){
			res.add(new ArrayList<Integer>(list));
			return;
		}
		// Recursive case: loop all elements different from previous and update sum and list 
		for (int i = start;i < nums.length;i++){
			list.add(nums[i]);
			// NOTE: is i+1 not  start+1
			helper(nums, i+1, sum+nums[i], target, list, res);
			// int immutable no need to backtracking
			list.remove(list.size()-1);
		}
	}
	public static void main(String[] args){
		int[] nums = new int[]{2,3,5,7};
		int target = 17;
		BackpackandCombinationSum sol = new BackpackandCombinationSum();
		System.out.println(Arrays.toString(nums)+"\n BackpackSum: "+ sol.bpSum(nums, target));
		System.out.println(Arrays.toString(nums)+"\n BackpackSumRecursiveCombinationSum: "+ sol.bpSumRecursiveCombinationSum(nums, target));
		
	}
}
