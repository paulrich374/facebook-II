package NineChapter5DP;

import java.util.Arrays;

public class KnapsackPickUnlimitedTimes {
	/*knapsackpickK T:O(n^3), Space:O(n)
	 * 	// Zero: null and negative check
	 * 	// First: DP dp[j] = Math.max(dp[j], dp[j- k * wt[i-1]] + k * val[i-1]), where k * wt[i-1] <= j
		// NOTE: j--
		// NOTE: k has  times array
		// NOTE: nopick INSDIE THE LOOP => keepp updating dp[j]
	 * */
	public int knapsackpickUnlimited(int[] wt, int[] val, int[] times, int capacity){
		// Zero: null and negative check
		if (wt == null || wt.length == 0 || val == null || val.length == 0 || times == null || times.length == 0 || capacity < 0)
			return 0;
		// First: DP dp[j] = Math.max(dp[j], dp[j- k * wt[i-1]] + k * val[i-1]), where k * wt[i-1] <= j
		// NOTE: j--
		// NOTE: k has  times array
		// NOTE: nopick INSDIE THE LOOP => keepp updating dp[j]
		int[] dp = new int[capacity+1];
		for (int i =1; i <= wt.length;i++){
			//for (int j = 0 ; j <= capacity; j++){
			// NOTE: j--
			for (int j = capacity ; j >= 0; j--){
				
				//for (int k = 1; k <= times.length;k++){
				// NOTE: k has  times array
				//for (int k = 0; k <= times[i-1];k++){
				for (int k = 0; k * wt[i-1] <= j;k++){
					// NOTE: nopick INSDIE THE LOOP => keepp updating dp[j]
					int nopick = dp[j];
					int pick = 0;
					if (j - k*wt[i-1] >=0){
						pick = dp[j - k*wt[i-1]] + k*val[i-1];
					}
					dp[j] = Math.max(pick, nopick);
				}
			}
		}
		return dp[capacity];
		
        /*
        int[][] dp = new int[val.length + 1][capacity + 1];
         
        for (int i = 1; i <= val.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                for (int k = 0; k <= times[i - 1]; k++) {
                    if (k * wt[i - 1] <= j) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * wt[i - 1]] + k * val[i - 1]);
                    }
                }
            }
        }
         
        return dp[val.length][capacity]; 
		*/
	}
	/*knapsackRecursive 
	 *   
	 * wt[], val[],start, listWeight, listVal, res
	 * wt[], val[],i, listWeight, listVal, res
	 * listWeight count times < times[i]
	 * */	
	// NOTE main Not mian
	public static void main(String[] args){
		int[] wt = new int[]{10,20,30};
		int[] val = new int[]{60,100,120};
		int[] times = new int[]{100,100,100};
		int W = 50;
		KnapsackPickUnlimitedTimes sol = new KnapsackPickUnlimitedTimes();
		System.out.println("wt: "+Arrays.toString(wt)+", val: "+Arrays.toString(val)+", times: "+Arrays.toString(times)+", W="+W+"\n knapsack: "+ sol.knapsackpickUnlimited(wt, val, times, W));
		// wt: [10, 20, 30], val: [60, 100, 120], times: [100, 100, 100], W=50
		// knapsack: 300
	}
}
