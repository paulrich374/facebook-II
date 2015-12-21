package facebook;

import java.util.Arrays;

/*
 *       Like clime a flight - cache
 *       Like Edit Distance 2D->1D save space
 *   
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 

The robot is trying to reach the bottom-right corner of the grid 

(marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.

uniquePathRecursive Time: 147000
-2129403600
uniquePathIterative Time: 739000
-2129403600
uniquePathIterativeSaveSpace Time: 511000

m: 3, n: 7
28
uniquePathRecursive Time: 131000
28
uniquePathIterative Time: 59000
28
uniquePathIterativeSaveSpace Time: 1058000 NOTE: CALCULATION takes time
 * 
 * 
 * 
 * */
/*
 * dp[i][j] = dp[i-1][j] + dp[i][j-1]
 * */
/* uniquePathRecursive
 * m: 100, n: 100
Exception in thread "main" java.lang.StackOverflowError
// T:O(m*n). S:O(m*n)
// Zero: null check	
// First: a cache array and recursive
 */
/*uniquePathIterative
// T:O(m*n).  S:O(m*n)
// Zero: null check	
// First: first row: all 1
//        first column: all 1
//        the others: dp[i][j] = dp[i-1][j] + dp[i][j-1]	
 * 
 */
/*  uniquePathIterativeSaveSpace: T:O(m*n).  S:O(n)
// Zero: null check	
// First: since we only concern [i-1][j] or [i][j-1]
//        only need 1 array
//        NOTE:  dp[k]  repalce dp[i-1][j]
//        Every time rewrite row value 
//        first row: all 1
//        the other row: dp[k] = dp[k] + dp[k-1]
//        // dp[0] always 1 no accmulation	
 * 
 */
public class UniquePath {
	/* uniquePathRecursive
	 * m: 100, n: 100
Exception in thread "main" java.lang.StackOverflowError
	// T:O(m*n). S:O(m*n)
	// Zero: null check	
	// First: a cache array and recursive
	 */
	public long uniquePathRecursive(int m , int n){
		// Zero: null check
		if (m <= 0 || n <= 0){
			return 0;
		}
		// First: a cache array and recursive
		long[][] cache = new long[m][n];
		return helper(m-1,n-1,m,n,cache);
	}	
	private long helper(int row, int col, int m, int n, long[][] cache){
		// Base case: row <0 || col <0
		//            row == 0 || col == 0
		//            NOTE: cache already computed, just return
		if (row < 0 || col < 0)
			return (long)0;
		if (row == 0 || col == 0){
			cache[row][col] = (long)1;
		}
		if (cache[row][col] > 0 ){
			return cache[row][col];
		}		
		// Recursive Case:
		return (long)helper(row-1, col, m, n, cache) + (long)helper(row, col-1, m, n, cache);
	}
	/*uniquePathIterative
	// T:O(m*n).  S:O(m*n)
	// Zero: null check	
	// First: first row: all 1
	//        first column: all 1
	//        the others: dp[i][j] = dp[i-1][j] + dp[i][j-1]	
	 * 
	 */
	public long uniquePathIterative(int m , int n){
		// Zero: null check
		if (m <= 0 || n <= 0){
			return (long) 0;
		}
		// First: first row: all 1
		//        first column: all 1
		//        the others: dp[i][j] = dp[i-1][j] + dp[i][j-1]
		long[][] dp = new long[m][n];
		for (int i = 0 ; i  < m; i++){
			for (int j = 0; j < n; j++){
				if (i == 0){
					dp[0][j] = (long) 1;
				} else if (j == 0){
					dp[i][0] = (long) 1;
				} else {
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}
			}
		}
		return dp[m-1][n-1];
	}
	/*  uniquePathIterativeSaveSpace: T:O(m*n).  S:O(n)
	// Zero: null check	
	// First: since we only concern [i-1][j] or [i][j-1]
	//        only need 1 array
	//        NOTE:  dp[k]  repalce dp[i-1][j]
	//        Every time rewrite row value 
	//        first row: all 1
	//        the other row: dp[k] = dp[k] + dp[k-1]
	//        // dp[0] always 1 no accmulation	
	 * 
	 */
	public Long uniquePathIterativeSaveSpace(int m , int n){
		// Zero: null check
		if (m <= 0 || n <= 0)
			return (long) 0;
		// First: since we only concern [i-1][j] or [i][j-1]
		//        only need 1 array
		//        NOTE:  dp[k]  repalce dp[i-1][j]
		//        Every time rewrite row value 
		//        first row: all 1
		//        the other row: dp[k] = dp[k] + dp[k-1]
		//        // dp[0] always 1 no accmulation 7209 000/60 000   760 000/59 000
		long[] dp = new long[n];
		for (int j  = 1; j < n; j++){
			dp[j] = (long) 1;
		}
		for (int i = 1; i < m; i++){
			for (int k = 0; k < n; k++){
					if (k == 0){
						//dp[0] = dp[0] + 1;
						dp[0] = (long) 1;// dp[0] always 1 no accmulation
					} else {
						dp[k] = dp[k] + dp[k-1]; 
					}
					
			}
		}
		return dp[n-1];
	}
	
	public static void main(String[] args){
		int m = 10;
		int n = 10;
		UniquePath sol = new UniquePath();
		System.out.println("m: "+m+", n: "+n);
		Long s = System.nanoTime();
		System.out.println(sol.uniquePathRecursive(m,n));
		Long e = System.nanoTime() - s;
		System.out.println("uniquePathRecursive Time: "+e);
		Long s2 = System.nanoTime();		
		System.out.println(sol.uniquePathIterative(m,n));
		Long e2 = System.nanoTime() - s2;
		System.out.println("uniquePathIterative Time: "+e2);	
		Long s3 = System.nanoTime();				
		System.out.println(sol.uniquePathIterativeSaveSpace(m,n));
		Long e3 = System.nanoTime() - s3;
		System.out.println("uniquePathIterativeSaveSpace Time: "+e3);		
	}

}
