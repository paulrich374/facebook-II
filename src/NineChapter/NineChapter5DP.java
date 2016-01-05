package NineChapter;

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
 *			1. Unique Path II (/facebook/src/facebook/UniquePath.java)
 *             long[] dp = new long[n];
 *             for (int i = 1; i < m; i++){
					for (int k = 0; k < n; k++){
			
			
 * 2.Sequence DP -
 * 			• dp[i] stands for first i character, numbers, etc...
 *			• Transit Function: dp[i] = dp[j], i >= j
 *		 	• initial state: dp[0]
 *		 	• Final state: dp[n-1]
 *			1. Climb chairs (/facebook/src/facebook/ClimeaFlight.java)
 *			2. Jump Game (/facebook/src/facebook/JumpGame.java)
 *          3. Jump GameII (/facebook/src/facebook/JumpGame.java)
 *          4. Longest Increasing Subsequence(LIS) 
 *          5. MaximumSubsequence (/facebook/src/facebook/MaximumSubsequence.java)
 *          
 *          
 * 3.Two sequences DP -
 * 			• dp[i][j] stands for first i character in the first sequence matches the first j characters in the second sequence
 *			• Transit Function: dp[i][j] figure out the relationship of ith character in sequence one and jth character in sequence two
 *		 	• initial state: dp[i][0] and dp[0][j]
 *		 	• Final state: dp[s1.length()][s2.length()]
 *			1. Longest Common Subsequence(LCS)
 *			2. Edit Distance	
 *          3. One Edit Distance
 *          
 *          
 * 4.Backpack -
 * 		1. 0 / 1 Back Pack problem.
 * 		2. Lintcode: 0/1 Back Pack II
		3. A general 0/1 Back Pack problem:
		4. A multiple DP problem        -- Each item can be picked up by k times.
		5. A multiple Back Pack problem -- Each item can be picked up by unlimited number of times
		6. Other Back Pack DP problems:
			1. k sum
 * 
 * 		
 * 
 * 		
 * 
 * */
}
