package NineChapter5DP;

/*				
 * 				SUMMARY val[0..n-1], wt[0..n-1], <= W(Capacity)[closest] with max val[]				
 * 
 * 				Given weights and values of n items, 
				put these items in a knapsack of capacity W to get the maximum total value in the knapsack. 
				In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively.
				Also given an integer W which represents knapsack capacity, 
				find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. 
				
 * 
 * 
 * http://buttercola.blogspot.com/2014/10/nine-chapter-lesson-5-dynamic.html
 * */
import java.util.Arrays;

public class Knapsack {
	/*knapsack T:O(N^2), S:O(N^2)
	 *         0     20   30    50   
	 *    ""   0
	 *       
	 *    10   0
	 *    
	 *    20   0     100
	 *    
	 *    30         100        220
	 * 
	 * 		// Zero: null and negative capacity check
	 * 		// First: dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - w[i-1]] + val[i-1])
			//        i start from 1 and j start from 0
	 * */
	public int knapsack(int[] wt, int[] val, int capacity){
		// Zero: null and negative capacity check
		if (wt.length == 0 || val.length == 0 || capacity < 0 ){
			return 0;
		}
		// First: dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - w[i-1]] + val[i-1])
		//        i start from 1 and j start from 0
		//int [][] dp = new int[wt.length+1][capacity+1];
		int []dp = new int[capacity+1];
		for (int i = 1 ; i <= wt.length;i++){
			//for (int j = 0 ; j <=capacity ;j++){
			for (int j = capacity ; j >= 0 ;j--){
				//int nopick = dp[i-1][j];
				int nopick = dp[j];
				int pick = 0;
				if (j - wt[i-1] >= 0){
					//pick = dp[i-1][j - wt[i-1]] + val[i-1];
					pick = dp[j - wt[i-1]] + val[i-1];
				}
				//dp[i][j] = Math.max(pick, nopick);
				dp[j] = Math.max(pick, nopick);
			}
		}
		//return dp[wt.length][capacity];
		return dp[capacity];
	}
	/*knapsackRecursive 
	 *   
	 * wt[], val[],start, listWeight, listVal, res
	 * wt[], val[],i+1, listWeight, listVal, res
	 * 
	 * 
	 * */
	// NOTE main Not mian
	public static void main(String[] args){
		int[] wt = new int[]{10,20,30};
		int[] val = new int[]{60,100,120};
		int W = 50;
		Knapsack sol = new Knapsack();
		System.out.println("wt: "+Arrays.toString(wt)+", val: "+Arrays.toString(val)+", W="+W+"\n knapsack: "+ sol.knapsack(wt, val, W));
	}
}
