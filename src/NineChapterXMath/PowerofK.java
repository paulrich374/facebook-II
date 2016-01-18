package NineChapterXMath;
/*
 * http://stackoverflow.com/questions/4429044/check-if-one-integer-is-an-integer-power-of-another
 * http://www.exploringbinary.com/ten-ways-to-check-if-an-integer-is-a-power-of-two-in-c/
 * power of k
 * 
 * 32 = 2*2*2*2*2
 * 2,4,16,256X
 * 32%16== 0 36/16 =2
 * 
 * 243 = 3*3*3*3*3
 * 3,9,81
 * 
 * while (k <= n)
 * 	k*=k;
 * if (n%k == 0){
 * }
 * http://www.cnblogs.com/EdwardLiu/p/5115390.html
 * */
/*
 * x is power of y
 * 
 * while (x%y == 0)  x = x / y
return x == 1
 * 
 * */
/*  O(1)
 *  log y X
 *  
 *     double d = Math.log(Math.abs(x)) / Math.log(Math.abs(y));

        if ((x > 0 && y > 0) || (x < 0 && y < 0)) {
            if (d == (int) d) {
                return true;
            } else {
                return false;
            }
        } else if (x > 0 && y < 0) {
            if ((int) d % 2 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
 * 
 * 
 */
public class PowerofK {
	public boolean powerOfTwo (int n, int k){
		
		/*O(logN)
		// Zero: less than check
		if (n<2){
			return false;
		}
		// First: approaching
		long k = 2;
		while(k*k <= n){
			k*=k;
		}
		//System.out.println(k);
		if (n/k == 2){
			return true;
		}
		return false;
		*/

		/*O(logN)
		return n > 0 && ((n & (n - 1)) == 0 );  
		*/

		/*O(N)
		while (((n % 2) == 0) && n > 1) // While x is even and > 1 
			   n /= 2;
			 return (n == 1);
			 
		*/
		/*MATH :LOG O(1)*/
		int x= n;
		int y= 2;
		double d = Math.log(Math.abs(x)) / Math.log(Math.abs(y));

		if ((x > 0 && y > 0) || (x < 0 && y < 0)) {
				if (d == (int) d) {
					return true;
				} else {
					return false;
				}
		} else if (x > 0 && y < 0) {
				if ((int) d % 2 == 0) {
					return true;
				} else {
					return false;
				}
		} 
		return false;
		/* log(N)
int i;
  unsigned long powers[100];// 100 freestyle value
  unsigned long last;
  last = powers[0] = y;//base
 // First: Approaching and record
  for (i = 1; last < x; i++) {
    last *= last; // note that last * last can overflow here!
    powers[i] = last;
  }
  // Second: divide by each approaching(x >= y) and check final ==1 or not
  while (x >= y) {
    unsigned long top = powers[--i];
    if (x >= top) {
      unsigned long x1 = x / top;
      if (x1 * top != x) return 0;
      x = x1;//update x as value after divide by some y^k
    }
  }
  return (x == 1);
		 * */
}
	
	public static void main(String[] args){
		PowerofK sol = new PowerofK();
		int n = 32;
		System.out.println("n="+n+" is power of 2:"+sol.powerOfTwo(n,2));
		n = 81;
		System.out.println("n="+n+" is power of 2:"+sol.powerOfTwo(n,2));
		n = -32;
		int k = -2;
		System.out.println("n="+n+" is power of "+k+":"+sol.powerOfTwo(n,k));		
		n = 16;
		System.out.println("n="+n+" is power of "+k+":"+sol.powerOfTwo(n,k));		

	}
}
