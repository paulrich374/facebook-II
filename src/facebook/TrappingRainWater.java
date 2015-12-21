package facebook;

import java.util.Arrays;

/*
 *      Two Pointers - 遍歷所有元素[order] 兩倆比較  (比左右都低=>两边)两边往中间夹逼
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * compute how much water it is able to trap after raining.
 * 
 * RAIN WATER
        1   1 2 1     1     = 6[total rain water]
                  |
          |       | |   |
      |   | |   | | | | | |
   [0,1,0,2,1,0,1,3,2,1,2,1] [bar height]
   
			Approach#1  两边各扫描一次  Ex. Candy, Trapping Rain Water
			Time:O(2*n), Sapce:O(n) Time : 258000
这个方法算是一种常见的技巧，从两边各扫描一次得到我们需要维护的变量，
通常适用于当前元素需要两边元素来决定的问题，非常类似的题目是Candy，
有兴趣的朋友可以看看哈。   
   			Time:O(n^2), Sapce:O(1)
   每一點往左往右 找到最懸崖 比較左右選較小的做為上線
   相當于 從一邊記錄最大”左最大“ 再從另一邊記錄最大稱”右最大“ 餅與”左最大“比較拿最小做計算
   

這裡使用  	Approach #2  两边往中间夹逼 Ex. PalindromeChecker, Two Sum，Container With Most Water 
			Time:O(n) , Sapce:O(1) Time : 81000
           两边懸崖 比較左右選較小的做為上線
           上線 == 左边 ”左最大“往右 做計算
           上線 == 右边 ”右最大“往左 做計算
           
 * */
public class TrappingRainWater {
	/*trap Time:O(2*n), Sapce:O(n)
	 * 		// Zero:null check
	 * 		// First: Calculating trapping ability for each position, min(left highest, right highest)
		//        Scan left to right, record max height so far
		//        Scan right to left, record max height so far, and grab lower wall to get water
	 * */
	public int trap(int[] elevation){
		// Zero:null check
		if (elevation == null || elevation.length == 0)
			return 0;
		// First: Calculating trapping ability for each position, min(left highest, right highest)
		//        Scan left to right, record max height so far
		//        Scan right to left, record max height so far, and grab lower wall to get water
		int[] container = new int[elevation.length];
		int maxL = 0;
		for (int i = 0; i < elevation.length; i++){
			container[i] = maxL;
			maxL = Math.max(elevation[i], maxL);
		}
		int maxR = 0;
		int total = 0;
		for (int j = elevation.length -1; j >= 0;j--){
			//container[j] = maxR;
			container[j] = Math.min(maxR, container[j]);
			maxR = Math.max(elevation[j], maxR);
			total += container[j] - elevation[j] > 0?container[j] - elevation[j]:0;
		}
		return total;
	}
	/*trapSaveTimeSapce
	 * 		// Zero: null check
		// First: inward
		//        Find min(left highest, right highest)
		// Scan left to right, record max height so far and get water
		// Scan right to left, record max height so far and get water
		
	 * 
	 * */
	public int trapSaveTimeSapce(int[] elevation){
		// Zero: null check
		if (elevation == null || elevation.length == 0){
			return 0;
		}
		// First: inward
		//        Find min(left highest, right highest)
		// Scan left to right, record max height so far and get water
		// Scan right to left, record max height so far and get water
		int l = 0;
		int r = elevation.length - 1;
		int total = 0;
		while (l < r){
			int min = Math.min(elevation[l], elevation[r]);
			if (elevation[l] == min){// for next one is high so far
				l++;
				// Scan left to right, record max height so far and get water
				while(l < r && min > elevation[l]){
					total += min - elevation[l];
					l++;
				}
			} else {
				r--;
				// Scan right to left, record max height so far and get water
				while (l < r && min > elevation[r]){
					total += min - elevation[r];
					r--;
				}
			}
		}
		return total;
	}
	public static void main(String[] args){
		TrappingRainWater sol = new TrappingRainWater();
		int[] elevation = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(Arrays.toString(elevation));

		Long s = System.nanoTime();
		System.out.println("Total Water: " + sol.trap(elevation));
		Long e = System.nanoTime() - s;
		System.out.println("Time : " + e);
		
		Long s2 = System.nanoTime();
		System.out.println("Total Water: " + sol.trapSaveTimeSapce(elevation));
		Long e2 = System.nanoTime() - s2;
		System.out.println("Time : " + e2);
	}
}
