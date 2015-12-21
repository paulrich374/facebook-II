package facebook;
/*
 *      Two Pointers - 遍歷所有元素[order] 兩倆比較=> 两边 往两边扫描
 * 
 * 
 * http://stackoverflow.com/questions/4679746/time-complexity-of-javas-substring
 * ================   Two Pointers =================
 * 
這裡使用			Approach#0 DP 
				Time:O(n^2), Space:O(n^2)
				
	/*longestpalindrome
	 * 		// Zero: null check
			// First: DP[i,j] = ((j - i <= 1 || DP[i+1,j-1]) && S[i] == S[j])
	 * 		// Second: return the substring
	 * 
        		Approach#1  往两边扫描  Ex. Longest Palindromic
				Time:O(n^2),Sapce:O(1)
				
	 longestpalindromeSaveSpace
	  * 		// Zero: null check
		// First: starting point n letter + n-1 gap between letter
		//        even index at letter and odd index at Gap
		//        left = right = i / 2, if odd, right+=1;
		//        comapre with maxLen
	  
这道题比较直接的做法类似Longest Palindromic Substring中的第一种方法，
对于每一个bar往两边扫描，找到它能承受的最大水量，然后累加起来即可。
每次往两边扫的复杂度是O(n)，对于每个bar进行处理，所以复杂度是O(n^2)，空间复杂度是O(1)。
思路比较清晰，就不列代码了，有兴趣的朋友可以实现一下哈。
 * */
public class LongestPalindrome {
	
	/*Approach#0 DP Time:O(n^2), Space:O(n^2)*/
	/*longestpalindrome
	 * 		// Zero: null check
			// First: DP[i,j] = ((j - i <= 1 || DP[i+1,j-1]) && S[i] == S[j])
	 * 		// Second: return the substring
	 * */
	public String longestpalindrome(String s){
		String res = null;
		// Zero: null check
		if (s == null || s.length() == 0)
			return res;
		// First: T:O(n^2),S:O(n^2) DP[i,j] = ((j - i <= 1 || DP[i+1,j-1]) && S[i] == S[j])
		boolean[][] dp = new boolean[s.length()][s.length()];
		int maxLen = 0;
		int[] range = new int[2];
		for (int i = s.length(); i >= 0; i--){
			for (int j = i; j < s.length(); j++){
				if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i+1][j-1]) ){
					dp[i][j] = true;
					if ( j - i + 1 > maxLen){
						maxLen = j - i + 1;
						range[0] = i;
						range[1] = j;
					}
				}
			}
		}
		// Second: return the substring
		if (maxLen > 0){
			res = s.substring(range[0], range[1] + 1);
		}
		return res;
	}
	/*Approach#1  Time:O(n^2), Space:O(1)*/
	 /*longestpalindromeSaveSpace
	  * 		// Zero: null check
		// First: starting point n letter + n-1 gap between letter
		//        even index at letter and odd index at Gap
		//        left = right = i / 2, if odd, right+=1;
		//        comapre with maxLen
	  * */
	public String longestpalindromeSaveSpace(String s){
		// Zero: null check
		String res = null;
		if (s == null || s.length() == 0)
			return res;
		// First: starting point n letter + n-1 gap between letter
		//        even index at letter and odd index at Gap
		//        left = right = i / 2, if odd, right+=1;
		//        comapre with maxLen
		int maxLen = 0;
		for (int i = 0; i < 2*s.length()-1; i++){
			int left = i/2; 
			int right = i/2;
			if (i%2 == 1){
				//left = left - 1;// left=1/2=0; left= left -1=-1;WRONG 
				right+=1;
			}
			String str = palindromesubstring(s, left, right);
			if (str.length() > maxLen){
				maxLen = str.length();
				res = str;
			}
		}
		return res;
	}
	/*palindromesubstring
		// First:Two pointers, spread out two side	
		//       ITERATING ONLY left >= 0 && right < length && s[left]==s[right]
	 * */
	private String palindromesubstring(String s, int left, int right){
		/*
		while(left <= right && left >= 0 && right < s.length()){
			if (s.charAt(left) != s.charAt(right)){
				return s.substring(left+1,right);
			}
			left--;
			right++;
		}
		*/
		
		// First:Two pointers, spread out two side	
		//       ITERATING ONLY left >= 0 && right < length && s[left]==s[right]
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
			left--;
			right++;
		}
		
		return s.substring(left+1,right);
	}
	public static void main(String[] args){
		String s = "cbaccabd";
		LongestPalindrome sol = new LongestPalindrome();
		String res = sol.longestpalindrome(s);
		System.out.println(s);
		System.out.println("Longest Plaindrome: "+res);
		Long start = System.nanoTime();
		String s2 = "forgeeksskeegfor";		
		String res2 = sol.longestpalindrome(s2);
		Long estimate = System.nanoTime() - start;
		System.out.println(s2);
		System.out.println("Longest Plaindrome: "+res2+".\n#0 Time: "+estimate);	
		Long start2 = System.nanoTime();
		String res3 = sol.longestpalindromeSaveSpace(s2);
		Long estimate2 = System.nanoTime() - start;
		System.out.println(s2);
		System.out.println("Longest Plaindrome: "+res3+".\n#1 Time: "+estimate2);			
	}
}
