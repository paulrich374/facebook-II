package NineChapterXMath;
/*
 * X^n double x int n
 * 
 * 核心 OVERFLOW(double) + Binary Search
 * 
 * 
 * CASE n = 0
 * 			=>1.0
 * CASE n%2 == 0
 * 			=>half*half
 * CASE n%2==1, n>0
 * 			=>half*half*x
 * CASE n%2==1, n<0
 * 			=>half*half*(1/x)
 * 
 * x=3, n=0,=>1
 * x=3, n=1,=>3
 * x=3, n=-1=>1/3
 * x=3, n=2=>3*3
 * x=3, n=-2=>(1/3)*(1/3)    
 * */

/* Recursive
 * CASE n = 0
 * 				=>1.0
 *              => half = pow(x,n/2)
 * CASE n%2 == 0
 * 				=>half*half
 * CASE n%2==1, n>0
 * 				=>half*half*x
 * CASE n%2==1, n<0
 * 				=>half*half*(1/x)
 * */

/*Iterative
 * http://blog.welkinlan.com/2015/11/13/powx-n-leetcode-java/
 * 
 * long m = n > 0?n:-(long)n // n = Math.abs(n);
 * // CASE n = 0
 * double ans = 1.0;
 * 
 * Binary search
 * while (m != 0){
 * 	 	// Case n%2 ==1
 *   	if ( (m & 1) == 1){
 *   		    // Case: overflow for double
 *   			if ( ans > Double.MAX_VALUE / x ) {
                    return Double.MAX_VALUE;
                }
 *   		ans*=x;
 *      }
 *      // Case n%2 == 0
 *   	x*=x
 *   	m >>= 1;
 * }
 * return (n >0)? ans: 1/ans;
 * 
 * */




/*powxnRecursive O(log n )
// Base case:  n = 0
// Recursive case 
*  half = pow(x,n/2)
// merge
*  n %2 ==0
*  n%2 ==1 && n >0
*  n%2 == 1 && n < 0
*  NOTE: (1/(double)x);
* */
/*powxnIterative O(log n )
 * // n =0
 * 	ans =1 
 *  // n < 0,abs
 *  long m = n > 0 ? n : -(long)n       
 *  // n%2 ==1
 *  (n & 1) == 1 => ans*=x
 *  // n%2 ==0
 *  x*=x 
 *  http://blog.welkinlan.com/2015/11/13/powx-n-leetcode-java/
 * */

public class PowXN {
	/*powxn O( n )
	 * 
	 * */	
	/*powxnRecursive O(log n )
		// Base case:n = 0
		// Recursvie case 
	 *  half = pow(x,n/2)
		// merge
	 *  n %2 ==0
	 *  n%2 ==1 && n >0
	 *  n%2 == 1 && n < 0
	 *  NOTE: (1/(double)x);
	 * */
	public double powxnRecursive(int x, int n){
		// Base case:n = 0
		if (n == 0)
			return 1.0;
		// Recursive case
		double half = powxnRecursive(x,n/2);
		// merge
		if (n%2 == 0)
			return half*half;
		else if ( n > 0)
			return half*half*(x);
		else 
			return (double)half*half*(1/(double)x);
	}
	/*powxnIterative O(log n )
	 * 	ans =1 // n =0
	 *  // n < 0,abs
	 *  long m = n > 0 ? n : -(long)n       
	 *  // n%2 ==1
	 *  (n & 1) == 1 => ans*=x
	 *  // n%2 ==0
	 *  x*=x 
	 * */
	public double powxnIterative(int x, int n){
		double ans = 1.0;
		// n < 0,abs
		long absn = n > 0?n: -(long)n;
		while (absn != 0){
			// n%2 ==1
			if ((absn & 1) == 1){
				ans*=x;
			}
			// n%2 ==0
			x*=x;
			absn >>=1;
		}
		return n>=0 ? ans: (1/ans);
	}	
	public static void main(String[] args){
		int x = 3;
		int n = 4;
		PowXN sol = new PowXN();
		System.out.println("x= "+x+", n ="+n+". result: "+ sol.powxnIterative(x,n));
		System.out.println("x= "+x+", n ="+n+ ". result: "+ sol.powxnRecursive(x,n));		
		n = -4;
		System.out.println("x= "+x+", n ="+n+ ". result: "+ sol.powxnIterative(x,n));
		System.out.println("x= "+x+", n ="+n+ ". result: "+ sol.powxnRecursive(x,n));		
	}
}
/*
 *  * case1 n = 0
 * => 1
 * ?? X = 0
 * ?? => 0
 * 
 * ?? neg = (X<0)?true;false;
 * 
 * ?? Long res = X;// OVERFLOW(long) 
 * 
 * while ((n>>1) > 1){ //Binary Search
 *    res = res*res;
 * }
 * if (n == 1){
 * 	res *= X;
 * ?? if (neg) res = -res; 
 * }
 * */
