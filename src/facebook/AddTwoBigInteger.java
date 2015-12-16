package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *   || to take care of unequal length
 *   a new list to store integer and check overflow
 * 
 * Add up two big integers represented in arrays
 * Input: {3,4,2} + {4,6,5} (NOTE: MSB to LSB)
   Output: {8,0,7}
 * http://buttercola.blogspot.com/2014/10/facebook-add-up-two-big-integers.html
 * */
public class AddTwoBigInteger {
	public int[] addTwoSum(int[] a, int[] b){
		List<Integer> res = new ArrayList<Integer>();
		// Zero: null case check
		/*
		if ( a == null && b == null){
			return null;
		}
		if (a == null && b != null){
			return b;
		}
		if (a != null && b == null){
			return a;
		}
		*/
		if (a == null || a.length == 0)
			return b;
		if (b == null || b.length == 0)
			return a;
		// First: consider carry with digit of a and digit of b FROM last index
		// use OR to automatically take care unequal length problem
		// a list to keep prepending digit to 0, maintaining MSB to LSB
		// overflow check
		int carry = 0;
		int i = a.length - 1;
		int j = b.length - 1;
		while (i >= 0 || j >= 0){
			if (i >= 0){
				carry += a[i];
				i--;
			}
			if (j >= 0){
				carry += b[j];
				j--;
			}
			int digit = carry % 10;
			carry /= 10;
			res.add(0, digit);
		}
		// 
		if ( carry == 1){
			res.add(0, 1);
		}
		// Second: arrayList to array
		int[] resarray = new int[res.size()];
		for (int k = 0 ; k < res.size(); k++){
			resarray[k] = res.get(k);
		}
		return resarray;
	}
	public static void main(String[] args){
		AddTwoBigInteger sol = new AddTwoBigInteger();
		int[] a = new int[]{3,4,2};
		int[] b = new int[]{4,6,5};
		int[] res = sol.addTwoSum(a, b);
		System.out.println("array A:"+Arrays.toString(a)+" plus ");
		System.out.println("array B:"+Arrays.toString(b));
		System.out.println("array C:"+Arrays.toString(res));
	}
}
