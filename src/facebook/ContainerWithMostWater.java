package facebook;

import java.util.Arrays;

/*
 *  *      Two Pointers - 遍歷所有元素[order] 兩倆比較  (左右圍起來高=>两边)两边往中间夹逼
 * 
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 *  n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 *  Find two lines, which together with x-axis forms a container, 
 *  such that the container contains the most water.
 *  
 * |~~~~~~~~~~|    
   |          |    |
   |__________|____|
   0          3    4
   for 4 4*2 = 8
   for 3 3*3 = 9
 * 
 *  	Approach #2  两边往中间夹逼 Ex. PalindromeChecker, Two Sum，Container With Most Water 
			Time:O(n) , Sapce:O(1)
这个算法每个元素只被访问一次，所以时间复杂度是O(n)，并且常数是1，
比前面的方法更优一些，不过理解起来需要想得比较清楚。
这种两边往中间夹逼的方法也挺常用的，它的核心思路就是向中间夹逼时
能确定接下来移动一侧窗口不可能使结果变得更好，所以每次能确定移动一侧指针，
直到相遇为止。
这种方法带有一些贪心，用到的有Two Sum，Container With Most Water，
都是不错的题目，有兴趣的朋友可以看看哈。
 * 
 * */
public class ContainerWithMostWater {
	/*mostWater
	 * 		// Zero: null check
	 * 		// First: calculate area and smaller one shift one to find largest height
	 * */
	public int mostWater(int[] h){
		// Zero: null check
		if (h == null || h.length == 0)
			return 0;
		// First: calculate area and smaller one shift one to find largest height
		int l = 0 ;
		int r = h.length - 1;
		int maxArea = 0;
		while (l < r){
			//h[l]
			//h[r]
			maxArea = Math.max(Math.min(h[l],h[r])*(r - l), maxArea);
			if (h[l] < h[r]){
				l++;
			}else {
				r--;
			}
		}
		return maxArea;
	}
	public static void main(String[] args){
		ContainerWithMostWater sol = new ContainerWithMostWater();
		int[] h = new int[]{3,2,2,3,2};
		System.out.println(Arrays.toString(h));
		Long s = System.nanoTime();
		System.out.println("Most Water: "+sol.mostWater(h));
		Long e = System.nanoTime() - s;
		System.out.println("Time : " + e);	
	}
}
