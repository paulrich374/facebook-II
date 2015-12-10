package facebook;
import java.util.*;
/*
 * Facebook: Two elements sum up to a third
 * Given a sorted array, write a program to decide if two elements sum up a third.
 * http://buttercola.blogspot.com/2014/11/facebook-two-elements-sum-up-to-third.html
 * */
/*Approach#1 Time:O(n^2), Space:O(1), can NOT solve array with negative number */
/*Approach#2 Time:O(n^2), Space:O(n) hash map, can solve array with negative number */

public class ThreeSumItself {
	/*Approach#1 Time:O(n^2), Space:O(1), can NOT solve array with negative number */
	public List<List<Integer>> ThreeSumItselfCompute(int[] nums){
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length <= 2)
			return res;
		
		// Fix one last element
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
	/*Approach#2 Time:O(n^2), Space:O(n) hash map, can solve array with negative number */
	public List<List<Integer>> ThreeSumItselfCompute2(int[] nums){
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0)
			return res;
		
		// HashMap record all integers vlaue and its index
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0 ; i < nums.length;i++){
			if (!map.containsKey(nums[i]))
				map.put(nums[i], i);
		}
		
		// Pair by pair to check if their sum is in the map
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
