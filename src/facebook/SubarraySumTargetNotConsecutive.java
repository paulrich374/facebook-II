package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 
 *  	unsorted + Random pick up=> SORTED
 *      时间复杂度O(n!) try all combinations
 * 
 * Given a set of candidate numbers (C) and a target number (T),
	
	
	
 *  find all unique combinations in C where the candidate numbers sums to T.
 * For example, given candidate set 2,3,6,7 and target 7, 
 * NOTE: non-descending order
 * NOTE: positive integers.
A solution set is: 
[7] 
[2, 2, 3] 
 * 
 * https://leetcode.com/problems/combination-sum/
 * */

/*sum // LeetCode, Combination Sum II 时间复杂度O(n!)，空间复杂度O(n)
	// Zero: null check and negative check
	// First: Sort the array and try every combinations from start in recursive way
 * */
/*helper
 * 	// Zero: Base case, sum == target or start >= arr length
	// First: Recursive
	// for loop iterating from start to length
	// no duplicate number in the solution set
	// backtracking arr[i], sum arr[i](integer is immutable, no need to BT)
	// recursive i+1 (since no duplicate number is allowed)
 * */
public class SubarraySumTargetNotConsecutive {
	/*sum:T:O(nlogn), Space:O(n) recursive call
	// Zero: null check and negative check
	// First: Sort the array and try every combinations from start in recursive way
	 * */
	public List<List<Integer>> sum(int[] arr, int target) {
		// Zero: null check and negative check
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (arr == null || arr.length == 0 || target < 0){
			return res;
		}
		// First: Sort the array and try every combinations from start in recursive way
		Arrays.sort(arr);
		helper(arr, 0, 0, target,new ArrayList<Integer>(),res);
		return res;
	}	
	/*helper
	 * 	// Zero: Base case, sum == target or start >= arr length
		// First: Recursive
		// for loop iterating from start to length
		// no duplicate number in the solution set
		// backtracking arr[i], sum arr[i](integer is immutable, no need to BT)
		// recursive i+1 (since no duplicate number is allowed)
	 * */
	private void helper(int[] arr, int start, int sum, int target, List<Integer> item, List<List<Integer>> res){
		// Zero: Base case, sum == target or start >= arr length
		if (start >= arr.length){
			return;
		}
		if (sum == target){
			List<Integer> newitem = new ArrayList<Integer>(item);
			res.add(newitem);
			return;
		}
		// First: Recursive
		// for loop iterating from start to length
		// no duplicate number in the solution set
		// backtracking arr[i], sum arr[i](integer is immutable, no need to BT)
		// recursive i+1 (since no duplicate number is allowed)
		for (int i = start; i < arr.length; i++){
			if ( i != start && arr[i] == arr[i-1])
				continue;
			item.add(arr[i]);
			helper(arr, i+1, sum + arr[i], target, item, res);
			item.remove(item.size()-1);
		}
	}
	public static void main(String[] args){
		int[] arr = new int[]{10,1,2,7,6,1,5};
		SubarraySumTargetNotConsecutive sol = new SubarraySumTargetNotConsecutive();
		List<List<Integer>> res = sol.sum(arr, 8);
		System.out.println(res);
	}
}
