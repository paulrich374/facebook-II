package facebook;
/*
 *     Two Pointers - 遍歷所有元素[order] 兩端比較＝> 两边( 两边往中间夹逼)
 * 
================   Two Pointers =================
			Approach#0  往两边扫描  Ex. Longest Palindromic
Time:O(n^2),Sapce:O(1)
这道题比较直接的做法类似Longest Palindromic Substring中的第一种方法，
对于每一个bar往两边扫描，找到它能承受的最大水量，然后累加起来即可。
每次往两边扫的复杂度是O(n)，对于每个bar进行处理，所以复杂度是O(n^2)，空间复杂度是O(1)。
思路比较清晰，就不列代码了，有兴趣的朋友可以实现一下哈。

			Approach#1  两边各扫描一次  Ex. Candy, Trapping Rain Water
Time:O(2*n), Sapce:O(n)
这个方法算是一种常见的技巧，从两边各扫描一次得到我们需要维护的变量，
通常适用于当前元素需要两边元素来决定的问题，非常类似的题目是Candy，
有兴趣的朋友可以看看哈。

這裡使用  	Approach #2  两边往中间夹逼 Ex. PalindromeChecker, Two Sum，Container With Most Water 
			Time:O(n) , Sapce:O(1)
这个算法每个元素只被访问一次，所以时间复杂度是O(n)，并且常数是1，
比前面的方法更优一些，不过理解起来需要想得比较清楚。
这种两边往中间夹逼的方法也挺常用的，它的核心思路就是向中间夹逼时
能确定接下来移动一侧窗口不可能使结果变得更好，所以每次能确定移动一侧指针，
直到相遇为止。
这种方法带有一些贪心，用到的有Two Sum，Container With Most Water，
都是不错的题目，有兴趣的朋友可以看看哈。
 * 
 * 
 * 
 * */
/*
 * 		// Zero: null check
 * 		// First: two pointers on both end and iterating to center
	//        isValid: make sure it is 0-9a-zA-Z
	//        isSame: convert to Lowercase
 * */
public class PalindromeChecker {
	/*
	 * 		// Zero: null check
	 * 		// First: two pointers on both end and iterating to center
		//        isValid: make sure it is 0-9a-zA-Z
		//        isSame: convert to Lowercase
	 * */
	public boolean checker(String s){
		// Zero: null check
		if (s == null || s.length() == 0)
			return true;	
		// First: two pointers on both end and iterating to center
		//        isValid: make sure it is 0-9a-zA-Z
		//        isSame: convert to Lowercase
		int l = 0;
		int r = s.length() - 1;
		while(l < r){
			
			if (!isValid(s.charAt(l))){
				l++;
				continue;
			}
			if (!isValid(s.charAt(r))){
				r--;
				continue;
			}
			if (!isSame(s.charAt(l), s.charAt(r))){
				return false;
			}
			l++;
			r--;
			/*
			if (s.charAt(l) != s.charAt(r)){
				return false;
			}
			l++;
			r--;
			*/
		}
		return true;
	}
	/*isValid
	 * // First: make sure it is 0-9a-zA-Z
	 * */
	private boolean isValid(char c){
		// First: make sure it is 0-9a-zA-Z
		if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
			return true;
		}
		return false;
	}
	/*isSame		
	 * // First: convert to Lowercase
		// Second: compare
	 * */ 
	private boolean isSame(char a, char b){
		// First: convert to Lowercase
		if ( a >='A' && a <= 'Z')
			a = (char) (a - 'A' + 'a');
		if ( b >='A' && b <= 'Z')
			b = (char) (b - 'A' + 'a');
		// Second: compare
		return a == b;
	}
	public static void main(String[] args){
		PalindromeChecker sol = new PalindromeChecker();
		String str1 = "A man, a plan, a canal: Panama";
		System.out.println("String: "+str1);
		System.out.println("palindrome: "+sol.checker(str1));
		String str2 = "race a car";
		System.out.println("String: "+str2);
		System.out.println("palindrome: "+sol.checker(str2));
		
		
	}
}
