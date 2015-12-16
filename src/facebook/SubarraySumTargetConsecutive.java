package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 *        後面剪掉前面一部份[WINDOW] subarraySumConsecutive
 *        後面剪掉前面一部份[WINDOW] word break
 *        負數 考慮 ＨＡＳＨＭＡＰ
 *        unsorted + window => CANNOT BE SORTED
 *        
 * Negative needs HashMap like thrresumitself

 * 
 * Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.
Examples:
Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
Ouptut: Sum found between indexes 2 and 4

Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
Ouptut: Sum found between indexes 1 and 4

Input: arr[] = {1, 4}, sum = 0
Output: No subarray found

 * 
 * http://buttercola.blogspot.com/2014/12/facebook-subarray-sums-to-target-value.html
 * */

//Follow Up :negative number ===> HashMap == > LIKE threesumitself

/*sum: Time:O(n^2), Space:O(1) two pointers
 * 		// Zero: null check and negative check
 * 		// First: create a Sliding window(consecutive), two pointers, 
 * 		// an iterating end index and a adjustable start index 
 * */
/*sumNeg: Time:O(n), Space:O(n) HashMap
 * 		// Zero: null check and negative check
		// First: a iterating end index to calculate sum do far and store (sum+target, current end index) into the map
		// // mean sum == target from 0 to curretn i
		// // means sum == target from index previous i to current i	
		//    When currentSum = PreicousSum + target happen, starting index needs map.get(curSum)+1
 * */	
public class SubarraySumTargetConsecutive {
/*sum: Time:O(n^2), Space:O(1)
 * 		// Zero: null check and negative check
 * 		// First: create a Sliding window(consecutive), two pointers, 
 * 		// an iterating end index and a adjustable start index
 *      // curSum+=arr[i] > target, take out arr[start]
 * */
	public List<Integer> sum(int[] arr, int target) {
		List<Integer> res = new ArrayList<Integer>();
		// Zero: null check and negative check
		if (arr == null || arr.length == 0 || target < 0)
			return res;
		// First: create a Sliding window(consecutive), two pointers, 
		// an iterating end index and a adjustable start index
		// curSum+=arr[i] > target, take out arr[start]
		// Time:O(n^2),Space:O(1)
		int curSum = 0;
		int start = 0;
		for (int i = 0 ; i < arr.length; i++){
			curSum += arr[i];
			while (curSum > target){
				curSum -= arr[start];
				start++;
			}
			if (curSum == target){
				for (int j = start; j <= i; j++)
					res.add(arr[j]);
				return res;
			}
		}
		return res;
	}	
	/*sumNeg: Time:O(n),Space:O(n) hashmap
	 * For Negative, - arr[start] will not make it smaller 
	 * 		// Zero: null check and negative check
	 * */	
	public List<Integer> sumNeg(int[] arr, int target) {
		// Zero: null check and negative check
		List<Integer> res = new ArrayList<Integer>();
		if (arr == null || arr.length == 0 || target < 0){
			return res;
		}
		// First: a iterating end index to calculate sum do far and store (sum+target, current end index) into the map
		// // mean sum == target from 0 to current i
		// // means sum == target from index previous i+1 to current i	
		//    When currentSum = PreicousSum + target happen, starting index needs map.get(curSum)+1
		int curSum = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0 ; i < arr.length; i++){
			curSum += arr[i];
			// mean sum == target from 0 to curretn i
			if (curSum == target){
				for (int j = 0; j <= i; j++)
					res.add(arr[j]);
				return res;
			// means sum == target from index previous i+1 to current i	
			} else if ( map.containsKey(curSum)){
				for (int j = map.get(curSum)+1; j <= i; j++)
					res.add(arr[j]);
				return res;				
			} else {
				map.put(curSum+target, i);
			}
		}
		
		return null;
	}	
	public static void main(String[] args){
		SubarraySumTargetConsecutive sol = new SubarraySumTargetConsecutive();		
		
		int[] arr = new int[]{1,4,20,3,10,15};
		int target = 33;
		long startTime = System.nanoTime() ;		
		List<Integer> res = sol.sum(arr, target);
		long estimatedTime = System.nanoTime() - startTime;		
		System.out.println(Arrays.toString(arr)+". Target = "+target);		
	    System.out.println(estimatedTime+"ns. "+"Array: "+res); 		
		int target2 = 0;
		List<Integer> res2 = sol.sum(arr, target2);
		System.out.println(Arrays.toString(arr)+". Target = "+target2);
	    System.out.println("Array: "+res2); 
	    
		int[] arrNeg = new int[]{1,2,3,6,-9,7};
	    int target3 = 4;
		List<Integer> res3 = sol.sumNeg(arrNeg, target3);	
		System.out.println(Arrays.toString(arrNeg)+". Target = "+target3);
	    System.out.println("Array: "+res3); 	
		long startTime2 = System.nanoTime() ;		
		List<Integer> res4 = sol.sumNeg(arr, target);			
		long estimatedTime2 = System.nanoTime() - startTime2;
		System.out.println(Arrays.toString(arr)+". Target = "+target);
	    System.out.println(estimatedTime2+"ns. "+"Array: "+res4); 	    
	}


}
