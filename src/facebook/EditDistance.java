package facebook;

import java.util.Arrays;

/*
 *   		 *       Like Unique Path 2D->1D save space
 * mart, karma
editdis Edit Distance: 3
Time: 134000
editdisSaveSapce Edit Distance: 3
Time: 48000
Zeil, trials
editdis Edit Distance: 4
Time: 108000
editdis Edit Distance: 4
Time: 75000

 * EditDistance
 * 
 * Given two words word1 and word2, 
 * find the minimum number of steps required to convert word1 to word2.
 * (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character

 时间复杂度是O(m*n)
 空间复杂度是O(m*n)
 OR
 空间上每一行只需要上一行信息，所以可以只用一维数组即可，
 我们取m, n中小的放入内层循环，
 则复杂度是O(min(m,n))
 
 
处理这道题也是用动态规划。
动态数组dp[word1.length+1][word2.length+1]
dp[i][j]表示从word1前i个字符转换到word2前j个字符最少的步骤数。
假设
 word1现在遍历到字符x，word2遍历到字符y
（word1当前遍历到的长度为i，word2为j）
以下两种可能性：
1. x==y，那么不用做任何编辑操作，所以dp[i][j] = dp[i-1][j-1]
2. x != y
   (1) 在word1插入y， 那么dp[i][j] = dp[i][j-1] + 1
   (2) 在word1删除x， 那么dp[i][j] = dp[i-1][j] + 1
   (3) 把word1中的x用y来替换，那么dp[i][j] = dp[i-1][j-1] + 1

 最少的步骤就是取这三个中的最小值。
最后返回 dp[word1.length+1][word2.length+1] 即可。

	 	t	r	i	a	l	s
"" 	0	1	2	3	4	5	6
Z	1	1	?				
What's the minimum cost to convert “Z” to “tr”? 
It's the smallest of the three values computed as
Case1 - Add
1 plus the cost of converting “Z” to “t” 
(we get this cost by looking to the left one position).
Case2 - Remove
1 plus the cost of converting “” to “tr”, giving “trZ” (we get this cost by looking up one position).
Case3 - Change
1 (because “Z” and “t” are different characters) 
plus the cost of converting “” to “t” (we get this cost by looking diagonally up and to the left one position).
The last of these yields the minimal distance: 2.
Got the idea? Try filling in the rest of the row before reading further.
  
str1 = "mart", str2 = "karma" 
假如我们想知道mart和karma的edit distance y 
(当前所在的位置的字符是c1 = 't', c2 = 'a'), 
那么我们必须先求出substring 
"mar"和"karm"的edit distance x1， 
"mart"和"karm"的距离x2,  
"mar"和"karma"的距离x3为什么呢？
观察一下，不难发现，x1, x2, x3和y之间存在这样的关系
if (c1 == c2), then y = min(x1, x2+1, x3+1)
if (c1 != c2), then y = min(x1+1, x2+1, x3+1)  
      
        k   a   r   m   a
    0   1   2   3   4   5 <- add
m   1   1   2   3   3   4    
a   2   2   1   2   3   3
r   3   3   2   1   2   3
t   4   4   3   2   2   3
    |-> add
    
	 	t	r	i	a	l	s
 	0	1	2	3	4	5	6
Z	1	1	2	3	4	5	6
e	2	2	2	3	4	5	6
i	3	3	3	2	3	4	5
l	4	4	4	3	3	3	4   
   
 https://secweb.cs.odu.edu/~zeil/cs361/web/website/Lectures/styles/pages/editdistance.html  
 https://daisyleetcode2014.wordpress.com/tag/edit-distance/
 http://blog.csdn.net/linhuanmars/article/details/24213795
 http://www.cnblogs.com/springfor/p/3896167.html
 * */
/*editdis  时间复杂度是O(m*n) 空间复杂度是O(m*n)
 * 		// Zero: null check 
	// First: start from "" 
	//        fill first row with converting "" to s2, all ADD operations
	//        fill first column with converting "" to s1, all ADD operations
	//        fill the rest from 1,1 to len1,len2
	//        1,1 to charAt(0),charAt(0)   len1,len2 to charAt(len1-1),charAt(len2-1)
 * 
 * */
/*editdisSaveSapce   时间复杂度是O(m*n) 空间复杂度是O(n)
 * 		// Zero: null check 
 * 		// First: 1-D array		
	//        fill first row with converting "" to s2, all ADD operations
    //        start from m=1 and n= 1, and if n == 1, dp[0] = row
	//        record current dp[n] for next n+1 use
	//        add = dp[n] + 1;// up
	// 		  remove = dp[n-1] + 1;// left
	// 		  change = diagonal + 1;// diagonal
 * */
public class EditDistance {
	/*editdis
	 * 		// Zero: null check 
		// First: start from "" 
		//        fill first row with converting "" to s2, all ADD operations
		//        fill first column with converting "" to s1, all ADD operations
		//        fill the rest from 1,1 to len1,len2
		//        1,1 to charAt(0),charAt(0)   len1,len2 to charAt(len1-1),charAt(len2-1)
	 * 
	 * */
	public int editdis(String s1, String s2){
		// Zero: null check 
		if (s1 == null){
			return s2.length();
		}
		if (s2 == null){
			return s1.length();
		}		
		// First: start from "" 
		//        fill first row with converting "" to s2, all ADD operations
		//        fill first column with converting "" to s1, all ADD operations
		//        fill the rest from 1,1 to len1,len2
		//        1,1 to charAt(0),charAt(0)   len1,len2 to charAt(len1-1),charAt(len2-1)
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		for (int j = 0; j <= s2.length(); j++)
			dp[0][j] = j;
		for (int i = 0; i <= s1.length(); i++)
			dp[i][0] = i;
		for (int m = 1; m <= s1.length(); m++){
			for (int n = 1; n <= s2.length(); n++){
				if (s1.charAt(m-1) == s2.charAt(n-1)){
					dp[m][n] = dp[m-1][n-1];// diagonal 
				} else {
					int add = dp[m][n-1] + 1;//left (z=>t) + r => tr
					int remove = dp[m-1][n] + 1; // up (""=>tr) +Z => trZ remvoe Z => tr
					int change = dp[m-1][n-1] + 1;//diagonal (""=>t) + r = tr
					dp[m][n] = Math.min(Math.min(add, remove), change);
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
	/*editdisSaveSapce
	 * 		// Zero: null check 
	 * 		// First: 1-D array		
		//        fill first row with converting "" to s2, all ADD operations
        //        start from m=1 and n= 1, and if n == 1, dp[0] = row
		//        record current dp[n] for next n+1 use
		//        add = dp[n] + 1;// up
		// 		  remove = dp[n-1] + 1;// left
		// 		  change = diagonal + 1;// diagonal
	 * */
	public int editdisSaveSapce(String s1, String s2){
		// Zero: null check 
		if (s1 == null){
			return s2.length();
		}
		if (s2 == null){
			return s1.length();
		}
		// First: 1-D array		
		//        fill first row with converting "" to s2, all ADD operations
        //        start from m=1 and n= 1, and if n == 1, dp[0] = row
		//        record current dp[n] for next n+1 use
		//        add = dp[n] + 1;// up
		// 		  remove = dp[n-1] + 1;// left
		// 		  change = diagonal + 1;// diagonal
		int[] dp = new int[s2.length()+1];
		for (int j = 0; j <= s2.length(); j++)
			dp[j] = j;		
		for (int m = 1; m <= s1.length(); m++){
			int diagonal = m-1;
			for (int n = 1; n <= s2.length(); n++){
				int cur = dp[n]; // LIKE reverse linked list node
				if (n == 1){
						dp[0] = m;
				}	
				if (s1.charAt(m-1) == s2.charAt(n-1)){
					dp[n] = diagonal;// diagonal 
				} else {				
					int add = dp[n] + 1;
					int remove = dp[n-1] + 1;
					int change = diagonal + 1;
					dp[n] = Math.min(Math.min(add, remove), change);
				}
				diagonal = cur;
			}
		}
		return dp[s2.length()];
	}
	public static void main(String[] args){
		EditDistance sol = new EditDistance();
		String s1 ="mart";
		String s2 = "karma";
		System.out.println(s1+", "+s2);
		long s = System.nanoTime();
		System.out.println("editdis Edit Distance: "+ sol.editdis(s1, s2));
		long e = System.nanoTime() - s;
		System.out.println("Time: "+e);
		long start2 = System.nanoTime();		
		System.out.println("editdisSaveSapce Edit Distance: "+ sol.editdisSaveSapce(s1, s2));	
		long e2 = System.nanoTime() - start2;
		System.out.println("Time: "+e2);		
		String s3 ="Zeil";
		String s4 = "trials";
		System.out.println(s3+", "+s4);
		long start3 = System.nanoTime();		
		System.out.println("editdis Edit Distance: "+ sol.editdis(s3, s4));	
		long e3 = System.nanoTime() - start3;
		System.out.println("Time: "+e3);	
		long start4 = System.nanoTime();		
		System.out.println("editdis Edit Distance: "+ sol.editdisSaveSapce(s3, s4));	
		long e4 = System.nanoTime() - start4;
		System.out.println("Time: "+e4);		
	}
	
}
