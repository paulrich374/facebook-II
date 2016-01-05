package facebook;

import java.util.Arrays;

/* *        in-order, befroe/ after
 *       	max sum 
 *          local and global
 * T:O(n!),  try all possible combinations and add them up, 
 * so we have totally Cn1 + Cn2 + ... + Cnn = n! combinations.
 * http://buttercola.blogspot.com/2014/08/leetcode-maximum-subarray.html
 * */
public class MaximumSubarray {
	/* maxSum
	 * Time: O(n), Space:O(1)
	 * local[i] = max(array[i] + local[i - 1], array[i])
	 * max = Math.max(local[i], max)
	 */
	public int maxSum(int[] array){
		// Zero: null check
		if (array == null || array.length == 0)
			return 0;
		// First:  local = max
		int local = array[0];
		int global = array[0];
		for (int i = 1; i < array.length; i++){
			local = Math.max(local+array[i], array[i]);// accumulate, alone
			global = Math.max(local, global);
		}
		return global;
	}
	/*maxSumRecursive
	 * Time:O(nlogn) merge sort, Space:O(logn)
	 * 		// Zero: null check
	 * 		// First: recursive with a global maximum
	/*helper
	 * it could be actually four cases: left + mid, mid, right + mid,  left + mid + right. 
	 * However, if either left or right sum is less than zero, 
	 * we can simply discard that part. 
	 * That is why the mlMax and mrMax start from zero, 
	 * and we simply calculate the left + mid + right which guarantee covering all cases above
	 * */
	public int maxSumRecursive(int[] array){
		// Zero: null check
		if (array == null || array.length == 0)
			return 0;
		// First: recursive with a global maximum
		int[] max = new int[2];
		max[0] = Integer.MIN_VALUE;
		return helper(array, 0 , array.length-1,max);
		
	}
	/*helper
	 * it could be actually four cases: left + mid, mid, right + mid,  left + mid + right. 
	 * However, if either left or right sum is less than zero, 
	 * we can simply discard that part. 
	 * That is why the mlMax and mrMax start from zero, 
	 * and we simply calculate the left + mid + right which guarantee covering all cases above
	 * */
	private int helper(int[] array, int l, int r, int[] max){
		//Base case:
		if (l > r){
			return Integer.MIN_VALUE;
		}
		// Recursive case:
		int mid = (l+r) /2;
		int lMax = helper(array, l, mid-1, max);
		int rMax = helper(array, mid+1, r, max);
		max[0] = Math.max(max[0], lMax);
		max[0] = Math.max(max[0], rMax);
		// find max from middle to left
		int mlMax = 0;
		int sum = 0;
		for (int i = mid -1; i >= l;i--){
			sum += array[i];
			mlMax = Math.max(mlMax, sum);
		}
		// find max from middle to right
		int mrMax = 0;
		sum = 0;
		for (int j = mid+1; j<=r;j++){
			sum += array[j];
			mrMax = Math.max(mrMax, sum);
		}
		return Math.max(max[0], mrMax+mlMax+array[mid]);
	}
	public static void main(String[] args){
		int[] array = new int[]{-2,1,-3,4,-1,2,1,-5,4};
		MaximumSubarray sol = new MaximumSubarray();
		System.out.println(Arrays.toString(array)+"\nmax subarray sum: "+sol.maxSum(array));
		System.out.println(Arrays.toString(array)+"\nmax subarray sum(recursive): "+sol.maxSumRecursive(array));
	
	}
}
/*  0 1 2  3  4 5 6  7 8
 * -2,1,-3,4,-1,2,1,-5,4
 * 
 *  [4,−1,2,1] => 6
 * 
 * h(A,0,8)
 *     m = 4
 *     int lMax = h(A, 0,3)
 *                     m = 1
 *                     int lMax = h(A,0,0)  
 *                                    m = 0
 *                                    int lMax = h(A, 0,-1) = MIN
 *                                    int rMax = h(A,1,0) = MIN
 *                                    max= max(MIN, MIN) = MIN
 *                                    max= max(MIN, MIN) = MIN
 *                                    mlmax = 0
 *                                    mrmax = 0
 *                                    return max(MIN, mlmax+mrmax+A[mid])=-2
 *                     int rMax = h(A,2,3)
 *                                    m =2
 *                                    int lMax = h(A,2,1) = MIN 
 *                                    int rMax = h(A,3,3) 
 *                                                    m = 3
 *                                                    int lMax = h(A,3,2) = MIN
 *                                                    int rMax = h(A,4,3) = MIN 
 *                                                    max = max(MIN, MIN) = MIN
 *                                                    max = MIN
 *                                                    mlmax = 0
 *                                                    mrmax = 0
 *                                                    return max(MIN, mlamx+mrmax+A[mid]) = 4
 *                                    max = max(MIN, MIN) = MIN
 *                                    max = max(MIN,4) = 4
 *                                    mlmax = 0 =>0
 *                                    mrmax = 0 =>4
 *                                    return max()
 *                     max = max(-2,MIN) = -2
 *                     max = max(4, MIN) = 4
 *                     mlmax =   0
 *                     mrMax= 4
 *                     return max(4, 0+0+1) = 4            
 *     int rMax = h(A, 5,8)=>1,2
 *                     mid = 6
 *                     int lmax = h(A,5,5)
 *                                   ...
 *                                   return 2
 *                     int rmax = h(A,7,8)
 *                                    mid= 7
 *                                    int lMax = MIN
 *                                    iny rMAX = h(A,8,8)
 *                                                 ..
 *                                                 return 4
 *                                    max = 4
 *                                    mlMax = 0
 *                                    mrMax = 4
 *                                    return 4
 *                    max = 2,
 *                    max =4,
 *                    nlMax = 2
 *                    nrMax = 4
 *                    return 
 * 
 * */