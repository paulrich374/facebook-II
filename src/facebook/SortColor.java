package facebook;

import java.util.Arrays;

/*
 * Given an array with n objects colored red, white or blue, 
 * sort them so that objects of the same color are adjacent, 
 * with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * 0 1 2 2 1 0 2
 * Sort Color ==>
 * 0 0 1 1 2 2 2 
 * 
 * 
 * https://leetcode.com/problems/sort-colors/
 * */
/*SortK
 * 		// Zero: arr null check
 * 		// First:create array index to record 0 to K-2 current index
	// for loop input arr
	//           for loop check arr element value from 0 to K - 1()(last value) 
	//               if equal, overwrite itself with K - 1(last value)
	//                  for loop to right shift all value till current EQUAL value
	//                          where all value from K - 2 to Equal value,  BACKWARD to avoid overwriting already changed
	//                          index[m]++ , where m from K - 2 to Equal value
 * */
/*
 *           i = 0 ;i < a.length;i++
 *           j start from 0 to assign value
 *          while (helper[j] == 0)
                j++;

            A[i] = j;
            helper[j]--;
            
            if (helper[j] == 0)
                j++;
 * 
 * */
public class SortColor {
	/*sort
	 * 		// Zero: arr null check
		// First:create two index to record 0 and 1 current index
		// for loop, if 0, overwrite itself to 2
		// 				   shift 1 index right by one
		//                 assign value to 0 index and 0 index++
		//           if 1
	 * */
	public void sort (int[] arr){
		// Zero: arr null check
		if ( arr == null || arr.length == 0)
			return;
		// First:create two index to record 0 and 1 current index
		// for loop, if 0, overwrite itself to 2
		// 				   shift 1 index right by one
		//                 assign value to 0 index and 0 index++
		//           if 1
		int indexZero = 0;
		int indexOne = 0;
		for (int i = 0 ; i < arr.length; i++){
			if (arr[i] == 0){
				arr[i] = 2;
				//arr[indexZero++] = 0;
				//arr[indexOne++] = 1;
				arr[indexOne++] = 1;	
				arr[indexZero++] = 0;   
			}else if (arr[i] == 1){
				arr[i] = 2;
				arr[indexOne++] = 1;
			}
		}
		return;
	}
	/*SortK
	 * 		// Zero: arr null check
	 * 		// First:create array index to record 0 to K-2 current index
		// for loop input arr
		//           for loop check arr element value from 0 to K - 1()(last value) 
		//               if equal, overwrite itself with K - 1(last value)
		//                  for loop to right shift all value till current EQUAL value
		//                          where all value from K - 2 to Equal value,
		//                          index[m]++ , where m from K - 2 to Equal value
	 * */
	public void sortK(int[] arr, int k){
		// Zero: arr null check
		if ( arr == null || arr.length == 0)
			return;
		// First:create array index to record 0 to K-2 current index
		// for loop input arr
		//           for loop check arr element value from 0 to K - 1()(last value) 
		//               if equal, overwrite itself with K - 1(last value)
		//                  for loop to right shift all value till current EQUAL value
		//                          where all value from K - 2 to Equal value, BACKWARD to avoid overwriting already changed
		//                          index[m]++ , where m from K - 2 to Equal value
		//int indexZero = 0;
		//int indexOne = 0;
		int[] index = new int[k-1];
		for (int i = 0 ; i < arr.length; i++){
			/*
			if (arr[i] == 0){
				arr[i] = 2;
				arr[indexOne++] = 1;	
				arr[indexZero++] = 0;   
			}else if (arr[i] == 1){
				arr[i] = 2;
				arr[indexOne++] = 1;
			}
			*/
			for (int j = 0; j < k - 1; j++){
				if (arr[i] == j){
					arr[i] = k - 1;
					for (int m = k - 2; m >= j ; m--) {
						arr[index[m]++] = m ;
					}
				}
			}
		}
		return;
	}	
	public static void main(String[] args){
		SortColor sol = new SortColor();
		int[] arr = new int[]{0, 1, 2, 2, 1, 0, 2};
		System.out.println("Before Sorting: "+Arrays.toString(arr));
		sol.sort(arr);
		System.out.println("Aftter Sorting: "+Arrays.toString(arr));
		int[] arr2 = new int[]{0, 1, 3, 2, 1, 0, 2, 3, 3};
		System.out.println("Before Sorting: "+Arrays.toString(arr2));
		int k = 4;
		sol.sortK(arr2,4);
		System.out.println("Aftter Sorting: "+Arrays.toString(arr2));		
	}
}
