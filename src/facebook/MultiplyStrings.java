package facebook;
/*
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * (Given 2 very large numbers, each of which is so large that it can only be represented as an array of integers. Write a function to multiply them.)
 * https://leetcode.com/problems/multiply-strings/
 * */
/* Time O( (m+n)^2 ), space O(1) for carry
 * https://en.wikipedia.org/wiki/Multiplication_algorithm
 * Time O(n log(n) log(log(n))) 
 *     idx   2  1  0
 *           1  2  3
 *              4  5
 *     idx      1  0
 *   -----------------
 *            5[2]  10[1]  15[0] 
 * 0[4] 4[3]  8[2]  12[1]                
 * -----------------------
 * 0[4] 4[3] 13[2]  22[1]  15[0]
 * 0[4] 5[3]  5[2]   3[1]   5[0] 
 * 
 * https://en.wikipedia.org/wiki/Multiplication_algorithm
 * a=\sum _{i=0}^{m-1}{a_{i}2^{wi}}
 * b=\sum _{j=0}^{m-1}{b_{j}2^{wj}}.
We look at these numbers as polynomials in x, where x = 2w, to get,
a=\sum _{i=0}^{m-1}{a_{i}x^{i}}
b=\sum _{j=0}^{m-1}{b_{j}x^{j}}.
Then we can then say that,
ab=\sum _{i=0}^{m-1}\sum _{j=0}^{m-1}a_{i}b_{j}x^{(i+j)}
  =\sum _{k=0}^{2m-2}c_{k}x^{k}
0~m-1 = m = pow(2,n) most close to digit 
Demonstration of multiplying 1234 × 5678  m =8
43210000
87650000
10,329,298,126,2,271,43,301 ???
26,24,298,322, 2,83,  43, 277  ???
26*10/337
260,145,173,132,4,251,164,138 
iverse FFT
32,52,61,60,34,16,5,0 ???
like iur method digit arithmetic
2 5 6 6 0 0 7 0
=> 7006652
 * 
 * */
/*multiply  O( (m+n)^2 ), space O(1) for carry
 * 	// Zero: null check
 * 	// First: create an array with size m + n
	// multiply string from lsb to msb and put equal index sum together along with their value  
	// Second: iterate through array to shift carry to the larger SB
	//         carry to i+1 in i iteration 		
 * 	// Third: remove zero head. e.g., 05535 => 5535

 * */
public class MultiplyStrings {
	/*multiply  O( (m+n)^2 ), space O(1) for carry
	 * 	// Zero: null check
	 * 	// First: create an array with size m + n
		// multiply string from lsb to msb and put equal index sum together along with their value  
		// Second: iterate through array to shift carry to the larger SB
		//         carry to i+1 in i iteration 		
	 * 	// Third: remove zero head. e.g., 05535 => 5535

	 * */
	public String multiply(String s1, String s2){
		String res = new String();
		// Zero: null check
		if (s1 == null || s1.length() == 0)
			return s1;
		if (s2 == null || s2.length() == 0)
			return s2;
		// First: create an array with size m + n
		// multiply string from lsb to msb and put equal index sum together along with their value  
		int[] resarray = new int[s1.length()+s2.length()];
		//for(int i = s1.length()-1; i >= 0; i--) {
		//	for (int j = s2.length()-1; j >= 0; j--){
		//		resarray[i+j] += (int)(s1.charAt(i) - '0')* (int)(s2.charAt(j) - '0');
		//	}
		//}
		// 4,13,22,15,0 WRONG should be 15,22,13,4,0
		String n1 = new StringBuilder(s1).reverse().toString();
		String n2 = new StringBuilder(s2).reverse().toString();
		for(int i = 0; i < n1.length(); i++) {
			for (int j = 0; j < n2.length(); j++){
				resarray[i+j] += (int)(n1.charAt(i) - '0')* (int)(n2.charAt(j) - '0');
			}
		}		
		// Second: iterate through array to shift carry to the larger SB
		//         carry to i+1 in i iteration
		StringBuilder sb = new StringBuilder();
		for (int k = 0 ; k < resarray.length; k++){
			int digit = resarray[k] % 10;
			int carry = resarray[k] / 10;
			if (k + 1 < resarray.length)
				resarray[k+1] += carry;
			//arr[k + 1] += arr[k] / 10;
			//arr[k] = arr[k] % 10;
			sb.append(digit);
		}
		sb.reverse();
		// Third: remove zero head. e.g., 05535 => 5535
		while(sb.charAt(0) == '0' && sb.length() > 1)
			sb.deleteCharAt(0);
		return sb.toString();
	}
	public static void main(String[] args){
		MultiplyStrings sol = new MultiplyStrings();
		String s1 = "123";
		String s2 = "45";
		String res = sol.multiply(s1, s2);//5535
		System.out.println(res);
	}
}
