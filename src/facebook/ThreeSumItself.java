package facebook;
import java.util.*;
/*
 *   SORTED 
     时间复杂度O(n^2) no need 时间复杂度O(n!) since we sorted and don't need to try all combinations
     only three elements combinations

Facebook: Two elements sum up to a third


HashMap like Polynomial Equations

like Polynomial Equations
 * Given a sorted array, write a program to decide if two elements sum up a third.
 * http://buttercola.blogspot.com/2014/11/facebook-two-elements-sum-up-to-third.html
 * */
/*Approach#1 Time:O(n^2), Space:O(1),Two pointers, can NOT solve array with negative number */
/*Approach#2 Time:O(n^2), Space:O(n),Two pointers, hash map, can solve array with negative number
 * like Polynomial Equations */

/*Approach#1 Time:O(n^2), Space:O(1), can NOT solve array with negative number 
 * 		// Zero: null check and length check
	// First: Fix one last element, avoid duplicate the fixed element and two sum the rest

 * */
/*Approach#2 Time:O(n^2), Space:O(n) hash map, can solve array with negative number
 * 		// Zero: null check and length check
			// First: HashMap record all integers value and its index for sum to match
		// Second: Pair(i) by pair(j=i+1) to check if their sum has a match in the map
 *  */
public class ThreeSumItself {
	/*Approach#1 Time:O(n^2), Space:O(1), can NOT solve array with negative number 
	 * 		// Zero: null check and length check
		// First: Fix one last element, avoid duplicate the fixed element and two sum the rest

	 * */
	public List<List<Integer>> ThreeSumItselfCompute(int[] nums){
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		// Zero: null check and length check
		if (nums == null || nums.length <= 2)
			return res;
		
		// First: Fix one last element, avoid duplicate the fixed element and two sum the rest
		for (int i = nums.length-1; i >= 2; i--){
			// avoid duplicate solution
			if (i != nums.length-1 && nums[i] == nums[i+1])
				continue;
			
			// TwoSum Function, find the other two integer sum to the fixed element
			System.out.println(6);
			int l = 0 ;
			int r = i-1;
			// NOTe: l < r not i < r
			while (l < r){
				if (nums[l] + nums[r] == nums[i]){
					List<Integer> item = new ArrayList<Integer>();
					item.add(nums[l]);
					item.add(nums[r]);
					item.add(nums[i]);
					res.add(item);
					l++;
					r--;
					// avoid duplicate solution
					while(l < r && nums[l] == nums[l-1])
						l++;
					// avoid duplicate solution
					while(l < r && nums[r] == nums[r+1])
						r--;
				} else if (nums[l] + nums[r] > nums[i]){
					r--;
				} else {
					l++;
				}
			}
		}
		return res;
	}
	/*Approach#2 Time:O(n^2), Space:O(n) hash map, can solve array with negative number
	 * 		// Zero: null check and length check
  			// First: HashMap record all integers value and its index for sum to match
		// Second: Pair(i) by pair(j=i+1) to check if their sum has a match in the map
	 *  */
	public List<List<Integer>> ThreeSumItselfCompute2(int[] nums){
		// Zero: null check and length check
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length <= 2)
			return res;
		
		// First: HashMap record all integers value and its index for sum to match
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0 ; i < nums.length;i++){
			if (!map.containsKey(nums[i]))
				map.put(nums[i], i);
		}
		
		// Second: Pair(i) by pair(j=i+1) to check if their sum has a match in the map
		for (int j = 0 ; j < nums.length;j++){
			for (int k = j+1; k < nums.length;k++){
				int sum = nums[j] + nums[k];
				if (map.containsKey(sum) && map.get(sum) != j && map.get(sum) != k){
					List<Integer> item = new ArrayList<Integer>();
					item.add(nums[j]);
					item.add(nums[k]);
					item.add(nums[map.get(sum)]);
					res.add(item);
				}
			}
		}
		return res;
	}
	public static void main(String[] args){
		System.out.println(1);
		ThreeSumItself solution = new ThreeSumItself();
		System.out.println(2);
		//int[] nums = new int[]{0,1,1}; 
		int[] nums = new int[]{-1,0,1};
		System.out.println(3);
		//List<List<Integer>> res = solution.ThreeSumItselfCompute(nums);
		List<List<Integer>> res = solution.ThreeSumItselfCompute2(nums);
		// Print out the result 
		if (res == null || res.size() ==0){
			System.out.println("No Such Solution Exist");
		}
		for (List<Integer> item: res){
			for (Integer num:item){
				System.out.print(num+", ");
			}
			System.out.println("");
		}
	}
}
