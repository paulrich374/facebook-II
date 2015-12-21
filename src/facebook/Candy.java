package facebook;

import java.util.Arrays;

/*  
 *     Two Pointers - 遍歷所有元素[order] 兩倆比較 (比左右都高)
 * 
 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?


Input:
[4,2,3,4,1]
9
CANDY
    2 1 2 3 1 = 9[total candies]
    |     |
    |   | |
    | | | |
    | | | | |
    4 2 3 4 1[rating]
RAIN WATER
        1   1 2 1     1     = 6[total rain water]
                  |
          |       | |   |
      |   | |   | | | | | |
   [0,1,0,2,1,0,1,3,2,1,2,1] [bar height]


https://leetcode.com/problems/candy/
 * 
 * 			Approach#1  两边各扫描一次  Ex. Candy, Trapping Rain Water
			Time:O(2*n), Sapce:O(n)
			
这个方法算是一种常见的技巧，从两边各扫描一次得到我们需要维护的变量，
通常适用于当前元素需要两边元素来决定的问题，非常类似的题目是Candy，
有兴趣的朋友可以看看哈。


 * 
 * */
public class Candy {
	public int candy(int[] rating){
		//Zero: null check
		if (rating == null || rating.length == 0)
			return 0;
		// First: Scan left(=1) to right(<len), if right is greater than left +1, otherwise 1
		//        Scan right(=len-2) to left(>=0), if left is greater than right, max(right+1,left)
		//        A sum to sum current value
		int[] dp = new int[rating.length];
		dp[0] = 1;
		for (int i = 1; i < rating.length; i++){// Scan left to right
			if (rating[i] > rating[i-1]){
				dp[i] = dp[i-1] + 1;
			} else {
				dp[i] = 1;
			}
		}
		int total = dp[rating.length - 1];
		//for (int j = rating.length - 1; j >= 1; j--){// Scan right to left
			//if (rating[j] < rating[j-1] && dp[j-1] > dp[j]){
			//	continue;
			//} else if (rating[j] < rating[j-1] ){
			//	dp[j-1] = dp[j] + 1;
			//} 
		    // =========> use max to replace above code
			//total += dp[j-1];
		//}
		for (int j = rating.length - 2; j >= 0; j--){
			if (rating[j] > rating[j+1]){
				dp[j] = Math.max(dp[j+1] + 1, dp[j]);
			}
			total += dp[j];
		}
		// Second: sum all candies
		//int total = 0;
		//for (int num:dp){
		//	total+=num;
		//}
		return total;
	}
	/*
	public int candySaveTimeSpace(int[] rating){
		if (rating == null || rating.length == 0)
			return 0;
		int total = 0;
		int l = 0 ; 
		int r = rating.length - 1;
		while (l < r){
			int max = Math.max(rating[l], rating[r]);
			if (max == rating[l]){
				l++;
				while(l < r && min < rating[l]){
					
				}
			} else {
				
			}
		}
		return total;
	}
	*/
	public static void main(String[] args){
		Candy sol = new Candy();
		int[] rating = new int[]{4,2,3,4,1};
		System.out.println(Arrays.toString(rating));
		Long s = System.nanoTime();
		System.out.println("Minimum candies: "+sol.candy(rating));
		Long e = System.nanoTime() - s;
		System.out.println("Time : " + e);		
	}
	
}
