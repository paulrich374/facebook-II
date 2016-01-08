package NineChapter5DP;

import java.util.Arrays;

/*
 * 
	Given n distinct positive integers, integer k (k <= n) and a number target.
	Find k numbers where sum is target. Calculate how many solutions there are?
	Example
	Given [1,2,3,4], k=2, target=5. There are 2 solutions:
	[1,4] and [2,3], return 2.
http://buttercola.blogspot.com/2014/11/lintcode-k-sum.html
http://www.lintcode.com/en/problem/k-sum/#
   
   
           0    1     2     3     4    5
    ""     1    
     
     1     1
     
     2     1
     
     3     1
     
     4     1
 * 
 * */
public class KSum {
	public int ksum (int[] nums, int k ,int target){
		// Zero: null check, negative check
		if (nums == null || nums.length == 0 || k < 0 || target < 0){
			return 0;
		}
		// First: way is accumulated like decode ways
		//        dp[i][j][m] = dp[i-1][j][m](no pick) or if (m - A[i-1] >= 0) dp += dp[i-1][j-1][m - A[i-1]]  
		//        init dp[0][0][0] = 1, dp[i][0][0] =1 (pick 0 times sum 0 is always 1 way - pick nothing)
		// init
		// NOTE: i < nums.length
		// transit
		// NOTE: i <= nums.length since use [i-1], i start from 1, j start from 1, and m start from 1
		// init
		int[][][] dp = new int[nums.length+1][k+1][target+1];
		dp[0][0][0] = 1;
		// NOTE: i < nums.length
		for (int i = 1; i < nums.length; i++){
			dp[i][0][0] = 1;
		}
		// transit
		// NOTE: i <= nums.length since use [i-1]
		for (int i = 1; i <= nums.length; i++){
			for (int j =1; j <= k; j++){// since dp[][0][0] stay 1 and dp[][0][m!=0] stay 0 
				for(int m = 1; m <= target; m++){ // since dp[][][0] stay 1 and dp[0][][m!=0] stay 0
					dp[i][j][m] = dp[i-1][j][m];
					if (m - nums[i-1] >= 0){
						dp[i][j][m] += dp[i-1][j-1][m - nums[i-1]];
					}
				}
			}
		}
		return dp[nums.length][k][target];
			
	}
	public static void main(String[] args){
		int[] nums = new int[]{1,2,3,4};
		int k = 2;
		int target = 5;
		KSum sol = new KSum();
		System.out.println("nums= "+Arrays.toString(nums)+", k="+k+", target= "+target+"\n # of ways sum k elements to target: "+sol.ksum(nums, k, target));
	}

}
