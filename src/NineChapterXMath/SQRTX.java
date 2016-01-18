package NineChapterXMath;
/* 
 * 
 * 核心  + Binary Search
 * Newton
http://www.jiuzhang.com/solutions/sqrtx/
 
 int res = 1;
 for (int i = 1; i < X/2; i++){
     if (X > i*i)
     	res = i;
 }
 */


/*sqrt
 *      1~x
 *      1~x/2
 *      1~(1+x/2)/2 
	// Zero: neg and Zero check
	// FirsT: Binary Search
	//              1.
	//              while (l <= r){
	//              2.
	//   			if ( mid*mid <= x && x <= (mid+1)*(mid+1))
	//					return mid;
	//				else if ( mid*mid > x ){
	//					r = mid-1;
	//				} else {
	//					l = mid+1;
	//				}
	//              3.
	//              return -1;
 * */


/*http://blog.icodejava.com/190/finding-square-root-of-a-double-number-in-java-using-binary-search/
 * sqrtReal
 * 	// Zero: neg and Zero and one check
	// FirsT: Binary Search
	//        1. epislon
	//        2. narrow down while( r - l > epislon)
	//        3. 			if (mid*mid == x)
	//							return mid;
	//						else if (mid*mid > x){
	//							r = mid;
	//						} else {
	//							l = mid;
	//						}
	//        4. return  (l + r)/2;  // most of the case won't be equal, so we narrow down the boundary
 * */


/*sqrtNewton
 * X = Y^2, DX/DY = 2Y, y is the bottom and x is the height 
 * slope = 2Ypre = (Ypre^2-x) / Ypre- Ylater
 * 		while(Math.abs(y*y - x) > 0.0001){
		y = y - (y*y - (double)x) / (2*y);			
	}
 * */

public class SQRTX {
	/*sqrt
	 *      1~x
	 *      1~x/2
	 *      1~(1+x/2)/2 
		// Zero: neg and Zero check
		// FirsT: Binary Search
		//              1.
		//              while (l <= r){
		//              2.
		//   			if ( mid*mid <= x && x <= (mid+1)*(mid+1))
		//					return mid;
		//				else if ( mid*mid > x ){
		//					r = mid-1;
		//				} else {
		//					l = mid+1;
		//				}
		//              3.
		//              return -1;
	 * */
	public int sqrt(int x){
		// Zero: neg and Zero check
		if (x < 0)
			return -1;
		if (x ==0)
			return 0;
		// FirsT: Binary Search
		//              1.
		//              while (l <= r){
		//              2.
		//   			if ( mid*mid <= x && x <= (mid+1)*(mid+1))
		//					return mid;
		//				else if ( mid*mid > x ){
		//					r = mid-1;
		//				} else {
		//					l = mid+1;
		//				}
		//              3.
		//              return -1;
		int l = 1;
		int r = x/2+1;
		while (l <= r){
			int mid  = (l + r)/2;
			if ( mid*mid <= x && x <= (mid+1)*(mid+1))
				return mid;
			else if ( mid*mid > x ){
				r = mid-1;
			} else {
				l = mid+1;
			}
		}
		return -1;
	}
	/*http://blog.icodejava.com/190/finding-square-root-of-a-double-number-in-java-using-binary-search/
	 * sqrtReal
	 * 	// Zero: neg and Zero and one check
		// FirsT: Binary Search
		//        1. epislon
		//        2. narrow down while( r - l > epislon)
		//        3. 			if (mid*mid == x)
		//							return mid;
		//						else if (mid*mid > x){
		//							r = mid;
		//						} else {
		//							l = mid;
		//						}
		//        4. return  (l + r)/2;  // most of the case won't be equal, so we narrow down the boundary
	 * */
	public double sqrtReal(double x){
		// Zero: neg and Zero and one check
		if (x < 0.0)
			return -1.0;
		if (x ==0.0 || x == 1.0)
			return x;
		// FirsT: Binary Search
		//        1. epislon
		//        2. narrow down while( r - l > epislon)
		//        3. 			if (mid*mid == x)
		//							return mid;
		//						else if (mid*mid > x){
		//							r = mid;
		//						} else {
		//							l = mid;
		//						}
		//        4. return  (l + r)/2;  // most of the case won't be equal, so we narrow down the boundary
		double l = 1.0;
		double r = x/2+1;
		double epislon = 0.00001;
		while( r - l > epislon) {
		//while (l <= r){// r -l >= 0
			double mid  = (l + r)/2;
			//System.out.println(mid);
			//if ( mid*mid <= x && x <= (mid+1)*(mid+1))
			//if ( Math.abs(mid*mid - x) >= 0.001)
			//	return mid;
			//else if ( mid*mid > x ){
			//	r = mid-epislon;
			//} else {
			//	l = mid+epislon;
			//}
			if (mid*mid == x)
				return mid;
			else if (mid*mid > x){
				r = mid;
			} else {
				l = mid;
			}
		}
		// return -1.0;
		return  (l + r)/2;// most of the case won't be equal, so we narrow down the boundary
	}	
	/*sqrtNewton
	 * X = Y^2, DX/DY = 2Y, y is the bottom and x is the height 
	 * slope = 2Ypre = (Ypre^2-x) / Ypre- Ylater
	 * 		while(Math.abs(y*y - x) > 0.0001){
			y = y - (y*y - (double)x) / (2*y);			
		}
	 * */
	public int sqrtNewton(int x){
		double y = ((double)x)/2;
		while(Math.abs(y*y - x) > 0.0001){
			y = y - (y*y - (double)x) / (2*y);
			// NOTE: / (2*y) need parentheses
			//System.out.println(y);
		}	
		return (int) y;
	}
	public static void main(String[] args){
		SQRTX sol = new SQRTX();
		int x = 2;
		System.out.println("sqrt("+x+") = "+sol.sqrt(x));
		System.out.println("sqrtReal("+x+") = "+sol.sqrtReal((double)x));		
		System.out.println("sqrtNewton("+x+") = "+sol.sqrtNewton(x));		
		
		
		x = 9;
		System.out.println("sqrt("+x+") = "+sol.sqrt(x));
		System.out.println("sqrtReal("+x+") = "+sol.sqrtReal((double)x));
		System.out.println("sqrtNewton("+x+") = "+sol.sqrtNewton(x));		
		
		x = 12;
		System.out.println("sqrt("+x+") = "+sol.sqrt(x));	
		System.out.println("sqrtReal("+x+") = "+sol.sqrtReal((double)x));		
		System.out.println("sqrtNewton("+x+") = "+sol.sqrtNewton(x));		
		
		x = 20;
		System.out.println("sqrt("+x+") = "+sol.sqrt(x));
		System.out.println("sqrtReal"+x+") = "+sol.sqrtReal((double)x));
		System.out.println("sqrtNewton("+x+") = "+sol.sqrtNewton(x));		
		
		
	}
}
