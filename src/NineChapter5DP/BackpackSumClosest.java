package NineChapter5DP;

import java.util.Arrays;
/*
 * 				SUMMARY A[0..n-1], <= Size [closest]
 * 
 * 				Given n items with size A[i], an integer m denotes the size of a backpack. 
				How full you can fill this backpack?
				Example:
				4 items with size [2, 3, 5, 7], the backpack size is 11, 
				we can select 2, 3 and 5,  
 * 
 * 
 * 
 * http://buttercola.blogspot.com/2014/10/nine-chapter-lesson-5-dynamic.html
 * */
public class BackpackSumClosest {
	/*bpSumClosest T:O(n^2) , SpaCE:O(n^2)
	 * 		// Zero: null and negative check
			// First: 
			//       dp[n+1][m+1]
			//        dp[i][j] = Math.max( dp[i-1][j] (not picked a[i]) OR dp[i-1][j-a[i]] + a[i] ( picked a[i])
	 * */
	public int bpSumClosest(int[] nums, int size){
		// Zero: null and negative check
		if (nums == null || nums.length == 0 || size < 0){
			return 0;
		}
		// First: 
		//       dp[n+1][m+1]
		//        dp[i][j] = Math.max( dp[i-1][j] (not picked a[i]) OR dp[i-1][j-a[i]] + a[i] ( picked a[i])
		int[][] dp = new int[nums.length+1][size+1];
		for (int i =1; i <= nums.length;i++) {
			for (int j = 0; j <= size; j++){
				int nopick = dp[i-1][j];
				int pick = 0;
				if ( j - nums[i-1] >= 0){
					pick = dp[i-1][j-nums[i-1]] + nums[i-1];
				}
				dp[i][j] = Math.max(nopick, pick);
			}
		}
		return dp[nums.length][size];
		// Second:
		
	}
	/*bpSumClosestSaveSpace T:O(n^2) , SpaCE:O(n)
	 * 		// Zero: null and negative check
	 * 		// First: 
			//        dp[n+1][m+1]
			//        dp[i][j] = Math.max( dp[i-1][j] (not picked a[i]) OR dp[i-1][j-a[i]] + a[i] ( picked a[i])
			// 			// NOTE: backward to prevent overwritting
			//					 for (int j = size; j >= 0; j--){
	 * */
	public int bpSumClosestSaveSpace(int[] nums, int size){
		// Zero: null and negative check
		if (nums == null || nums.length == 0 || size < 0){
			return 0;
		}
		// First: 
		//        dp[n+1][m+1]
		//        dp[i][j] = Math.max( dp[i-1][j] (not picked a[i]) OR dp[i-1][j-a[i]] + a[i] ( picked a[i])
		// 			// NOTE: backward to prevent overwritting
		// 					for (int j = size; j >= 0; j--){		
		//int[][] dp = new int[nums.length+1][size+1];
		int[] dp = new int[size+1];
		for (int i =1; i <= nums.length;i++) {
			//for (int j = 0; j <= size; j++){
			// NOTE: backward to prevent overwritting
			for (int j = size; j >= 0; j--){
				//int nopick = dp[i-1][j];
				int nopick = dp[j];
				int pick = 0;
				if ( j - nums[i-1] >= 0){
					//pick = dp[i-1][j-nums[i-1]] + nums[i-1];
					pick = dp[j-nums[i-1]] + nums[i-1];
				}
				//dp[i][j] = Math.max(nopick, pick);
				dp[j] = Math.max(nopick, pick);
			}
		}
		//return dp[nums.length][size];
		return dp[size];
		// Second:
		
	}	
	public static void main(String[] args){
		int[] nums = new int[]{2,3,5,7};
		int size = 11;
		BackpackSumClosest sol = new BackpackSumClosest();
		System.out.println(Arrays.toString(nums)+"\n bpSumClosest: "+ sol.bpSumClosest(nums, size));
		System.out.println(Arrays.toString(nums)+"\n bpSumClosestSaveSpace: "+ sol.bpSumClosestSaveSpace(nums, size));
		
	}
}
