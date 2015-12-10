package facebook;

import java.util.ArrayList;
import java.util.List;
/*
 * Given a sorted array arr[] and a value X,
 * find the k closest elements to X in arr[]. 
 * http://www.geeksforgeeks.org/find-k-closest-elements-given-value/
 */

/*
 *  
Output:
39 30 42 45
The time complexity of this method is O(Logn + k).
 * 	Approach#1: T:O(logn + k)
	 * kclosest
	 * First O(logn): use BST to locate the vlaue closest to target or even equal to target
	 * Second O(k): use left right small difference check to find total k elements
	 * S:O(k) to keep an array to store k elements
 */
/* searchForPosition
 *  BST to locate target position T:O(logn), S:O(1)
			// First: left boundary less or equal to right boundary
			// Second: return left boundary 	
 */
/* searchForPositionRecursive
 * BST to locate target postion T:O(logn), Sapce:O(logn)
 * 		// Zero: Base cases
		// First: Recursive cases: compare nums[mid] with target to shift boundary
 * */
/* findkelements
 * use left right check to find k elemnts T:O(k)
  		// Zero: use index as left boundary and right are left+1
  		// First: if target is present in nums[], then reduce left index
		// Second: Compare elements on left and right of index, small difference
		// Third: LEFTOVER: if there are no elements on the right side, then print left elemenrs
 */
public class KClosestValues {
	/*Approach#1: T:O(logn + k)
	 * kclosest
	 * First O(logn): use BST to locate the vlaue closest to target or even equal to target
	 * Second O(k): use left right small difference check to find total k elements
	 * S:O(k) to keep an array to store k elements*/
	public int[] kclosest(int[] nums, final int target, final int k){
		int[] res = new int[k];
		if(nums== null || nums.length == 0)
			return res;
		int index = searchForPosition(nums, target);
		int index2 = searchForPositionRecursive(nums, 0, nums.length-1, target);
		System.out.println("index: "+index);
		System.out.println("index2: "+index2);
		res = findkelements(nums, index,  k, target);
		return res;
	}
	/* searchForPosition
	 *  BST to locate target position T:O(logn), S:O(1)
				// First: left boundary less or equal to right boundary
				// Second: return left boundary 	
	 */
	private int searchForPosition(int[] nums, int target){
		int l = 0; 
		int r = nums.length-1;
		// First: left boundary less or equal to right boundary
		while(l<=r){
			int mid = (l+r)>>1;
			if (nums[mid] == target){
				return mid;
			} else if (nums[mid] > target){
				r--;
			} else{
				l++;
			}	
		}
		// Second: return left boundary 
		return l;
	}
	/* searchForPositionRecursive
	 * BST to locate target postion T:O(logn), Sapce:O(logn)
	 * 		// Zero: Base cases
			// First: Recursive cases: compare nums[mid] with target to shift boundary
	 * */
	private int searchForPositionRecursive(int[] nums, int l, int r, int target){
		// Zero: Base cases
		if (nums[r] <= target){// target is greater than all
			return r;
		}
		if (nums[l] > target){// target is smaller than all
			return l;
		}
		// First: Recursive cases: compare nums[mid] with target to shift boundary
		//int l = 0; 
		//int r = nums.length-1;
		//while(l<=r){
			int mid = (l+r)>>1;
			if (nums[mid] <= target && nums[mid+1] > target)
				return mid;
			//} else if (nums[mid] > target){
			//	r--;
			//} else{
			//	l++;
			//}	
			if (nums[mid] < target)
				return searchForPositionRecursive(nums, mid+1, r, target);
			return searchForPositionRecursive(nums, l, mid-1, target);
		//}
		//return l;
	}	
	/* findkelements
	 * use left right check to find k elemnts T:O(k)
	  		// Zero: use index as left boundary and right are left+1
	  		// First: if target is present in nums[], then reduce left index
			// Second: Compare elements on left and right of index, small difference
			// Third: LEFTOVER: if there are no elements on the right side, then print left elemenrs
	 */
	private int[] findkelements(int[] nums, int index, int k, int target){
		int[] res = new int[k];
		List<Integer> restemp = new ArrayList<Integer>(); 
		// Zero: use index as left boundary and right are left+1
		int l = index;
		int r = l+1;
		int count = 0;
		// First: if target is present in nums[], then reduce left index
		if (nums[l] == target) {
			l--;
		}
		// Second: Compare elements on left and right of index, small difference
		while (l >= 0 && r < nums.length && count < k){
			// which side has small difference
			if (target - nums[l] < nums[r] - target)
				restemp.add(nums[l--]);
			else
				restemp.add(nums[r++]);
			count++;
		}
		// Third: LEFTOVER: if there are no elements on the right side, then print left elemenrs
		while (count < k && l >= 0){
			restemp.add(nums[l--]);
			count++;
		}	
		//  LEFTOVER:
		while (count < k && r < nums.length){
			restemp.add(nums[r++]);
			count++;
		}
		for (int i = 0; i < restemp.size(); i++){
			res[i] = restemp.get(i);
		}
		return res;
	}
	public static void main(String[] args){
		int[] nums = new int[]{12, 16, 22, 30, 35, 39, 42,
	               45, 48, 50, 53, 55, 56};
		final int target = 35;
		final int k = 4;
		KClosestValues sol = new KClosestValues();
		int[] res = sol.kclosest(nums, target, k);
		for (Integer a: res)
		System.out.print(a+" ");
		
	}
}
