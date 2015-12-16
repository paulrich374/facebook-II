package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *       Divide and merge
 *       &&
 *       use while to take care of unequal length
 *       Recursive
 *       Iterative
 *       2n*k/2 + 4n*k/4 +.. kn*k/logK = nk operation/level * logk level = klogk*n
 *       2n + 3n + ... kn = k^2n if two two merge 
 * Merge k sorted arrays, each array may have max "n" elements.
 * http://buttercola.blogspot.com/2014/11/facebook-merge-k-sorted-arrays.html
 * */
/*mergeKArraysRecursive
 * 		//Zero: null check
 * 		// First: divide and merge 
 * */
/*mergeRecursive
 * divide
 * 		Approach #1  Recursive klogk*n	
	// Zero: make sure l < r, Base case l == r, return single list
// First: divide half with mid
 */
/*mergeIterative
 * 		// Approach#2 Iterative 2n*k/2 + 4n*k/4 +.. kn*k/logK = nk operation/level * logk level = klogk*n
	// Zero: For many rounds of within l and r, replace r with end since keep changing
	 * 			// First: within l and r, keep inward merge to the front one, ALWAYS start from 0
		//        beginAlwaysFromFirstForEveryRound
		//        end keep narrowing down
 * */
/**mergeTwoArrays
 * 		// Zero: null check, return the other 
	// First: create two pointers from 0 and while && both less than boundary, 
	//        take smaller number into res
	 * 		// Second: add the rest of longer length of list

 */
public class MergeKSortedArrays {
	/*mergeKArraysRecursive
	 * 		//Zero: null check
	 * 		// First: divide and merge
	 * */
	public List<Integer> mergeKArraysRecursive(List<List<Integer>> arrs){
		List<Integer> res = new ArrayList<Integer>();
		//Zero: null check
		if (arrs == null || arrs.size() == 0)
			return res;
		// First: divide and merge
		return mergeRecursive(0, arrs.size() - 1, arrs);
	}
	/*mergeKArraysRecursive
	 * 		//Zero: null check
	 * 		// First: divide and merge 
	 * */
	public List<Integer> mergeKArraysIterative(List<List<Integer>> arrs){
		List<Integer> res = new ArrayList<Integer>();
		//Zero: null check
		if (arrs == null || arrs.size() == 0)
			return res;
		// First: divide and merge
		return mergeIterative(0, arrs.size() - 1, arrs);
	}	
	/*mergeRecursive
	 * divide
	 * 		Approach #1  Recursive klogk*n	
		// Zero: make sure l < r, Base case l == r, return single list
	// First: divide half with mid
	 */
	private List<Integer> mergeRecursive(int l, int r, List<List<Integer>> arrs){
		/* Approach #1  Recursive klogk*n	*/ 
		// Zero: make sure l < r, Base case l == r, return single list
		if (l == r) 
			return arrs.get(l);
		// First: divide half with mid
		int mid = (l + r) / 2;
		List<Integer> left = mergeRecursive(l, mid, arrs);
		List<Integer> right = mergeRecursive(mid + 1, r, arrs);
		return mergeTwoArrays(left, right);
	
	}
	/*mergeIterative
	 * 		// Approach#2 Iterative 2n*k/2 + 4n*k/4 +.. kn*k/logK = nk operation/level * logk level = klogk*n
		// Zero: For many rounds of within l and r, replace r with end since keep changing
		 * 			// First: within l and r, keep inward merge to the front one, ALWAYS start from 0
			//        beginAlwaysFromFirstForEveryRound
			//        end keep narrowing down
	 * */
	private List<Integer> mergeIterative(int l, int r, List<List<Integer>> arrs){
		// Approach#2 Iterative 2n*k/2 + 4n*k/4 +.. kn*k/logK = nk operation/level * logk level = klogk*n
		// Zero: For many rounds of within l and r, replace r with end since keep changing
		int end = r;
		while(end > l){
			// First: within l and r, keep inward merge to the front one, ALWAYS start from 0
			//        beginAlwaysFromFirstForEveryRound
			//        end keep narrowing down
			int begin = 0;
			while (begin < end) {
				arrs.set(begin, mergeTwoArrays(arrs.get(begin), arrs.get(end)));
				begin++;
				end--;
			}
		}	
		return arrs.get(0);
	}	
	/**mergeTwoArrays
	 * 		// Zero: null check, return the other 
		// First: create two pointers from 0 and while && both less than boundary, 
		//        take smaller number into res
		 * 		// Second: add the rest of longer length of list

	 */
	private List<Integer> mergeTwoArrays(List<Integer> l1, List<Integer> l2){
		List<Integer> res = new ArrayList<Integer>();
		// Zero: null check, return the other 
		if ( l1 == null )
			return l2;
		if ( l2 == null )
			return l1;
		// First: create two pointers from 0 and while && both less than boundary, 
		//        take smaller number into res
		int i = 0, j = 0;
		while ( i < l1.size() && j < l2.size()){
			if ( l1.get(i) < l2.get(j)){
				res.add(l1.get(i));
				i++;
			} else {
				res.add(l2.get(j));
				j++;
			}
		}
		// Second: add the rest of longer length of list
		while ( i < l1.size()){
			res.add(l1.get(i));
			i++;
		}
		while ( j < l2.size()){
			res.add(l2.get(j));
			j++;
		}
		return res;
	}
	public static void main(String[] args){
		MergeKSortedArrays sol = new MergeKSortedArrays();
		Integer[][] A = new Integer[5][];
		A[0] = new Integer[] { 1, 5, 8, 9 };
		A[1] = new Integer[] { 2, 3, 7, 10 };
		A[2] = new Integer[] { 4, 6, 11, 15 };
		A[3] = new Integer[] { 9, 14, 16, 19 };
		A[4] = new Integer[] { 2, 4, 6, 9 };
		List<List<Integer>> arr = new ArrayList<List<Integer>>();
		for (int i = 0; i < A.length; i++){
			arr.add(Arrays.asList(A[i]));
		}
		System.out.println("K array: "+arr);
		Long startTime = System.nanoTime();
		System.out.println("Merged array: "+sol.mergeKArraysRecursive(arr));
		Long estimatedTime = System.nanoTime() - startTime;
		System.out.println("Recusrive Approach Time: "+estimatedTime);
		Long startTime2 = System.nanoTime();		
		System.out.println("Merged array: "+sol.mergeKArraysIterative(arr));
		Long estimatedTime2= System.nanoTime() - startTime2;
		System.out.println("Iterative Approach Time: "+estimatedTime2);		
		//Output:
		//[1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 8, 9, 9, 9, 10, 11, 14, 15, 16, 19]
	}
}
