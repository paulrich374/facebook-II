package NineChapter5DP;

import java.util.Arrays;
/*	 jump T:O(n*n), S:O(n)
 *        對於每個點 從頭到這 成不成立 成立條件 標記＋可以的步數 >＝ 我的標記
 *        一個成立就跳出
	      for (int i = 1; i < A.length; i++) {
            	for (int j = 0; j < i; j++) {
                	if (dp[j] == true && j + A[j] >= i) {
                    	dp[i] = true;
                    	break;
                	}
            	}
        	}
      jumpMinTimes T:O(n*n), S:O(n) 
      		對於每個點 從頭到這 更新 更新條件 標記＋可以的步數 >＝ 我的標記 ＆＆ 已更新果 dp[j] != Integer.MAX_VALUE
      		一個更新就跳出
        	for (int i = 1; i < A.length; i++) {
            	for (int j = 0; j < i; j++) {
                	if (dp[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    	dp[i] = dp[j] + 1;
                    	break;
                	}
            	}
        	}        	
http://buttercola.blogspot.com/2014/10/nine-chapter-lesson-5-dynamic.html
*/
public class JumpGame {

	/*jump T:O(n), S:O(1)
	 * 
	 * 	// Zero:null check
		// First: jump capability
		//        1. init: maxJump = nums[0];
		//        2. every iteration check maxJump => if (maxJump < 0)
		//        3. if (nums[i] > maxJump) => reset maxJump
	 * 
	 * */
	public boolean jump(int[] nums){
		// Zero:null check
		if (nums == null || nums.length == 0)
			return false;
		// First: jump capability
		//        1. init: maxJump = nums[0];
		//        2. every iteration check maxJump => if (maxJump < 0)
		//        3. if (nums[i] > maxJump) => reset maxJump
		int maxJump = nums[0];
		for (int i = 1 ; i < nums.length; i++){
			maxJump--;
			if (maxJump < 0)
				return false;
			if (nums[i] > maxJump)
				maxJump = nums[i];
		}
		return true;
	}
	/*jumpMinTimes  T:O(n), S:O(1)
	 * 		// Zero:null check
		// First: jump capability
		//        1. init: maxJump = nums[0];
		//        2. every iteration check maxJump => if (maxJump < 0) => return 0
		//        3. if (nums[i] > maxJump && i != nums.length-1) => reset maxJump, minJump++
		//        NOTE: add i != nums.length-1 condition to save computation on lastIndex since we already achieve there
	 * 
	 * */
	public int jumpMinTimes(int[] nums){
		// Zero:null check
		if (nums == null || nums.length == 0)
			return 0;
		// First: jump capability
		//        1. init: maxJump = nums[0];
		//        2. every iteration check maxJump => if (maxJump < 0) => return 0
		//        3. if (nums[i] > maxJump && i != nums.length-1) => reset maxJump, minJump++
		//        NOTE: add i != nums.length-1 condition to save computation on lastIndex since we already achieve there
		int maxJump = nums[0];
		int minJump = 1;
		for (int i = 1 ; i < nums.length; i++){
			maxJump--;
			if (maxJump < 0)
				return 0;
			if (nums[i] > maxJump && i != nums.length-1){
				maxJump = nums[i];
				minJump++;
			}	
		}
		return minJump;
	}	
	public static void main(String[] args){
		int[] nums = new int[]{2,3,1,1,4};
		JumpGame sol = new JumpGame();
		System.out.println(Arrays.toString(nums)+"\n. Can Jump To Last Index: "+ sol.jump(nums));
		int[] nums2 = new int[]{3,2,1,0,4};
		System.out.println(Arrays.toString(nums2)+"\n. Can Jump To Last Index: "+ sol.jump(nums2));
		
		System.out.println(Arrays.toString(nums)+"\n. Minimum Jump Times To Last Index: "+ sol.jumpMinTimes(nums));
		System.out.println(Arrays.toString(nums2)+"\n. Minimum Jump Times To Last Index: "+ sol.jumpMinTimes(nums2));
	}
}
