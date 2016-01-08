package NineChapter5DP;

public class NineChapter5DP {
/*http://buttercola.blogspot.com/2014/10/nine-chapter-lesson-5-dynamic.html
 * 
 * Outline
 * 
 * A. Four Steps to solve DP problems
 * 		1. Define DP array,1D, 2D or even 3D. figure out the meaning of each element in the DP array
 * 		2. Transit function
 * 			1. Figure out the connections between each state, how to decide current status from past status
 * 		3. Initialization
 * 			1. Figure out what is the starting status
 * 		4. Return answer
 * 			1. Figure out the final status
 * 
 * 
 * B. How to determine if a question is a DP problem
 * 		There are some common rules to follow:
 * 		• Maximum/Minimum e.g.,  maximum subarray, maximum subsequence, Best time to buy and sell stock
 * 		• Yes/No e.g., edit distance
 * 		• Count all possible solutions e.g., unique path, climb a flight
 * 		• Cannot sort/swap
 * 
 * 
 * C. Four most common types of DP questions
 * 		1. Matrix DP 
 * 			e.g.,   unique path II,  
 * 		2. Sequence DP 
 * 			e.g.,   Climb chairs, 
 * 					Jump Game
 * 					Jump Game II
 * 					Longest Increasing Subsequence (LIS)
 * 					maximum subsequence
 * 					maximum subarray, 
 * 		3. Two sequences DP 
 * 			e.g.,   Longest Common Subsequence (LCS)
 * 					edit distance
 * 					One Edit Distance
 * 		4. Backpack
 *   		1. 0 / 1 back pack problem.
 * 			2. Lintcode: 0/1 Back pack II
			3. A general 0/1 back pack problem:
			4. A multiple DP problem -- Each item can be picked up by k times.
			5. A multiple back pack problem -- each item can be picked up by unlimited number of times
			6. Other back pack DP problems:
					1. k sum
 * 
 * 
 * 1.Matrix DP -  
 * 			• dp[x][y] means from starting point to index x,y 
 *			• Transit Fucntion:Analyze how to deduce dp[i][j] from previous states
 *		 	• initial state: Starting point
 *		 	• Final state: Final point	 
 *			1. Unique Path II                      (/facebook/src/facebook/UniquePath.java)
 *             long[] dp = new long[n];
 *             for (int i = 1; i < m; i++){
					for (int k = 0; k < n; k++){
			
			
 * 2.Sequence DP - Usually given a array
 * 			• dp[i] stands for first i character, numbers, etc...
 *			• Transit Function: dp[i] = dp[j], i >= j
 *		 	• initial state: dp[0]
 *		 	• Final state: dp[n-1]
 *			1. Climb chairs                         (/facebook/src/facebook/ClimeaFlight.java)
 *			2. Jump Game                            (/facebook/src/NineChapter5DP/JumpGame.java)
 *          3. Jump GameII                          (/facebook/src/NineChapter5DP/JumpGame.java)
 *          4. Longest Increasing Subsequence(LIS)  (/facebook/src/NineChapter5DP/LongestIncreasingSubsequence.java)
 *          5. MaximumSubsequence                   (/facebook/src/facebook/MaximumSubsequence.java)
 *          
 *          
 * 3.Two sequences DP -- Usually given two strings
 * 			• dp[i][j] stands for first i character in the first sequence matches the first j characters in the second sequence
 *			• Transit Function: dp[i][j] figure out the relationship of ith character in sequence one and jth character in sequence two
 *		 	• initial state: dp[i][0] and dp[0][j]
 *		 	• Final state: dp[s1.length()][s2.length()]
 *			1. Longest Common Subsequence(LCS)
 *			2. Edit Distance	
 *          3. One Edit Distance
 *          4. Longest Common Prefix
 *          
 *          
 * 4.Backpack -  pick up any from N
 *            -  Usually given at least one array
 * 
 * 		1. 0 / 1 Back Pack problem.[TRUE OR FALSE] SUMMARY A[0..n-1], == Target [equal] TRUE OR FALSE  ----  nopick || pick
 * 				• dp[n+1][target+1]
 * 				• dp[i][S] Given first i numbers, can we pick up some numbers and their sum equal to S
 * 				• Transit function: dp[i][S] = dp[i-1][S-a[i]] (picked a[i]) OR dp[i-1][S] (not picked a[i])
 * 				• Initial state: dp[0][0] = true; dp[0][1...SUM] = false;
 * 				• Final state: dp[n][target]
 * 				
 * 
 * 				Given N non-negative integers and a target, 
 * 				Can we pick up any numbers from the N integers and its sum equals to the target. 
 * 				Return true of false
				4 items with size [2, 3, 5, 7], the target size is 17, 
				we can select 2, 3, 5, and 7  
				
 * 		2. Lintcode: 0/1 Back Pack II[INT VALUE] SUMMARY A[0..n-1], <= Size [closest] SUM  ---- Math.max(pick, nopick)
 * 				• dp[n+1][m+1]
 * 				• dp[i][j] Given first i numbers, can we pick up some numbers and their sum close to j
 * 				• Transit function: dp[i][j] = Math.max( dp[i-1][j] (not picked a[i]) OR dp[i-1][j-a[i]] + a[i] ( picked a[i])
 * 				• Initial state: all zero value no need to initialize
 * 				• Final state: dp[n][m]
 *              
 *              
				Given n items with size A[i], an integer m denotes the size of a backpack. 
				How full you can fill this backpack?
				Example:
				4 items with size [2, 3, 5, 7], the backpack size is 11, 
				we can select 2, 3 and 5,  
				
		3. A general 0/1 Back Pack problem[INT VALUE] : SUMMARY val[0..n-1], wt[0..n-1], <= W(Capacity)[closest] with max val[],  ---- Math.max(dp[i-1][j], dp[i-1][j - w[i-1]] + val[i-1])
 * 				• dp[n+1][W+1]
 * 				• dp[i][j] Given first i numbers, can we pick up some numbers and their sum close to j as well as the value sum is maximal 
 * 				• Transit function: dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - w[i-1]] + val[i-1])
 * 				• Initial state: 
 * 				• Final state: dp[n][W]
 * 				
				
				Given weights and values of n items, 
				put these items in a knapsack of capacity W to get the maximum total value in the knapsack. 
				In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively.
				Also given an integer W which represents knapsack capacity, 
				find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. 
				
		4. A multiple DP problem[INT VALUE]         -- SUMMARY Each item can be picked up by k times.  ----   dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - k * w[i-1]] + k * val[i-1]), where 0 <= k <= n[i] (k boundary)
 * 				• 
 * 				• 
 * 				• Transit function: dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - k * w[i-1]] + k * val[i-1]), where 0 <= k <= n[i] (k boundary)
 * 				•
 * 				•  				
				SUMMARY Each item can be picked up by k times.				
				each element could be selected at most n[i] times. Find the maximum value with the sum of the weight less or equal to W. 
		5. A multiple Back Pack problem[INT VALUE]  --SUMMARY Each item can be picked up by unlimited number of times ----  where k * wt[i - 1] <= j (k boundary) [ change k <= n[i - 1] ]
 * 				• 
 * 				• 
 * 				• Transit function: dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - k * w[i-1]] + k * val[i-1]), where k * wt[i - 1] <= j (k boundary) [ change k <= n[i - 1] ]
 * 				• 
 * 				• 
		        SUMMARY Each item can be picked up by Unlimited times.
		        
		        In this case, we only need to change little of the code above. 
		        We change the inner most loop from k <= n[i - 1] to k * wt[i - 1] <= j.
		6. Other Back Pack DP problems[INT WAYS] : -- pick up K from N SUMMARY A[0..n-1], SUM(B[0..k-1]) == Target
 * 				• dp[n+1][k+1][target+1] 
 * 				• dp[i][j][m], Given first i numbers, can we pick up j numbers out of i and their sum equal to m ---- dp[i][j][m] = dp[i-1][j][m](nopickup) or if (m - A[i-1] >= 0) dp[i][j][m] += dp[i-1][j-1][m - A[i-1]] (pickup) 
 * 				• Transit function: dp[i][j][m] = dp[i-1][j][m](nopickup) or if (m - A[i-1] >= 0) dp[i][j][m] += dp[i-1][j-1][m - A[i-1]] (pickup) 
 * 				• Initial state: dp[0][0][0] = 1, dp[i][0][0] = 1, 1 <= i <= n
 * 				• Final state: dp[n][k][target] 				
				
				
				1. k sum				-- pick up K +  Each item can be picked up by 1 time
					Given n distinct positive integers, integer k (k <= n) and a number target.
					Find k numbers where sum is target. Calculate how many solutions there are?
					Example
					Given [1,2,3,4], k = 2, target=5. 
					There are 2 solutions:[1,4] and [2,3], return 2.
				2. Combination sum     -- pick up ANY +   Each item can be picked up by unlimited number of times
				3. Combination sumII   -- pick up ANY +   Each item can be picked up by 1 time
 * 
 * 		
 * 
 * 		
 * 
 * */
}
