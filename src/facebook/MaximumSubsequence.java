package facebook;

import java.util.Arrays;

/* *        in-order, befroe/ after
 *       	max sum 
 * 
 *           1D -> constant variable
 *          Cannot next to each other: maximum-subsequence,  maximum path sum with a gap, house robber
 *          Contiguous or consecutive: maximum-subarray, maximum path sum, best time to buy and sell stock
 *          
         
 * Find the maximum sub-sequence sum (similar to Kadane's Algorithm) with the constraint that 
 * two numbers in the array that form the max sum cannot be next to each other.
 * 
 * 

 * http://buttercola.blogspot.com/2014/11/facebook-finding-maximum-subsequence-sum.html
 * */
public class MaximumSubsequence {
	/*maxSumPos T:O(n), S:O(n)
	 * dp[i] = max(dp[i-2]+current,dp[i-1])
		// Zero: null check 
		// First: dp[i] = max(dp[i-2]+current,dp[i-1])
		//        NOTE: array size length+1 since we need to check i-2,i start from 2
	 * */
	public int maxSumPos(int[] array){
		// Zero: null check
		if (array == null || array.length == 0){
			return 0;
		}
		// First: dp[i] = max(dp[i-2]+current,dp[i-1])
		//        NOTE: array size length+1 since we need to check i-2,i start from 2
		int[] dp = new int[array.length+1];
		dp[0] = 0;
		dp[1] = array[0];
		for (int i = 2; i <= array.length;i++){
			dp[i] = Math.max(dp[i-2] + array[i-1], dp[i-1]);
		}
		return dp[array.length];
	}
	/*maxSumPosSaveSpace T:O(n), S:O(1)
	 * dp = max(dp_2+array[i-1],dp_1)
		// Zero: null check
		// First: dp[i] = max(dp[i-2]+current,dp[i-1])
		//        NOTE: array size length+1 since we need to check i-2,i start from 2
		//        NOTE: return dp_1  
	 * */
	public int maxSumPosSaveSpace(int[] array){
		// Zero: null check
		if (array == null || array.length == 0){
			return 0;
		}
		// First: dp[i] = max(dp[i-2]+current,dp[i-1])
		//        NOTE: array size length+1 since we need to check i-2,i start from 2
		//        NOTE: return dp_1 
		int dp_2 = 0;
		int dp_1 = array[0];
		for (int i = 2; i <= array.length;i++){
			int dp = Math.max(dp_2 + array[i-1], dp_1); 
			dp_2 = dp_1;
			dp_1 = dp;
		}
		return dp_1; 
	}	
	/*maxSumNeg T:O(n), S:O(1)
	 *          dp[i] = max(dp[i-2]+current,dp[i-1])
	 *          int dp_1 = Math.max(0, array[0]);
	 *          the following no need to max(0, array[i-1])
	 *          // Zero: null check
	 *			// First: NOTE: dp_1 = Math.max(0, array[0]);
	 * */
	public int maxSumNeg(int[] array){
		// Zero: null check
		if (array == null || array.length == 0)
			return 0;
		// First: NOTE: dp_1 = Math.max(0, array[0]);
		int dp_2 = 0;
		int dp_1 = Math.max(0, array[0]);
		for (int i =2 ;i <= array.length; i++){
			int dp = Math.max(dp_2 + array[i-1], dp_1);
			dp_2 = dp_1;
			dp_1 = dp;
		}
		return dp_1;
	}
	public static void main(String[] args){
		int[] arrayPos = new int[]{1,3,5,7,9};
		int[] arrayPos2 = new int[]{9};
		int[] arrayNeg = new int[]{-1,-3,5,7};
		MaximumSubsequence sol = new MaximumSubsequence();
		System.out.println(Arrays.toString(arrayPos)+"\nPos Array max seq sum: "+ sol.maxSumPos(arrayPos));
		System.out.println(Arrays.toString(arrayPos2)+"\nPos Array  max seq sum: "+ sol.maxSumPos(arrayPos2));
		System.out.println(Arrays.toString(arrayPos)+"\nPos Array max seq sum(save space): "+ sol.maxSumPosSaveSpace(arrayPos));
		System.out.println(Arrays.toString(arrayPos2)+"\nPos Array  max seq sum(save space): "+ sol.maxSumPosSaveSpace(arrayPos2));		
		System.out.println(Arrays.toString(arrayNeg)+"\nNeg Array  max seq sum (WRONG): "+ sol.maxSumPos(arrayNeg));		
		System.out.println(Arrays.toString(arrayNeg)+"\nNeg Array max seq sum: "+ sol.maxSumNeg(arrayNeg));
		
	}
}
