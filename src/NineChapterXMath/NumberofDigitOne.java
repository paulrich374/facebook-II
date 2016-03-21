package NineChapterXMath;
/*
 *           int m=1; m<=n;m*=10
 * 		
 * 	
 * http://www.meetqun.com/thread-10288-1-1.html
 * count digit k
 * a = n/m; b = n%m
 * digit = a/10;
 * digit ~~ power=m
 * digit >= k+1 && digit <=9 => (a/10+1)*m;// 1 means include digit k since digit >= k+1
 *                           => (a/10+0)*m + (digit == k)*(b+1);// 0 means doesn't include digit k
 *                                                              // if digit ==k, we count remainder accompany with k
 *                                                              // otherwise, it is 0
 * ex. 54215
 * m =100
 * a = 54215/100 =542, b = 54215%100 =15
 * digit = 542%10 = 2
 * 2 >=2 && 2 <=9=> (542/10+1)*100;// 00100~54199(<54215)
 * 
 * ex. 54125
 * m =100
 * a = 54125/100 =541, b = 54125%100 =25
 * digit = 541%10 = 1
 * !(1 >=2 && 1 <=9)=> (542/10)*100+ (digit==1)*(25+1);// 00100~53199(<54125) + 54100-54125 (26) 
 * 
 * ex. 54025
 * 
 * (542/10)*100 //// 00100~53199(<54025) 
 * 
 * O(n^2)
 * 	1. 每次循环计算这個數有多少1
 * 	2. 对这個數的每一位
 * for (i=1-n)
 *  while(i != 0)
 *     int digit = i%10;
 *     i=/10;
 *     if (digit == 1)
 *     		countDigitOne++;
 * n =13 
 * 1,2,3,4,5,6,7,8,9,10,11,12, 13    
 * *                  *  ** *   * = > 6*=>6
 * O(n)
 	1. 每次循环只计算这一位有多少1
	2. 对于每一位，分大于等于2，等于1，小于1考虑
	
 * while(n != 0){
 *     int digit = n%10;
 *     n=/10;
 *     if (digit == 1)
 *     		
 *     else 
 *     		
 * n =13
 * digit =3
 * 1=>1
 * digit =1    
 * 0,1,2,3 =>4    
 *   1      =>1      
 * }    
 * */





/*	countOne O(n)
 * 		// Zero: neg check
		// First: check every digit how many one they have
		// Second: get every digit
		for (int m=1; m<=n;m*=10){
			int a = n/m;
			int b = n%m;
			int digit = a%10; 
		// Third: count every digit
			if (digit >=2 && digit <=9){
				count+= (a/10+1)*m;
			}else{
				int flag= 0;
				if (digit==1){
					flag = 1;
				}
				count+=(a/10)*m+flag*(b+1);
			}
 * */

/*countOneRecursiveReal
 * 		// Base case:n<10, n<=0
		// Recursive case:
		// current + next + remainder
		// digit*countOneRecursiveReal(power-1)+countOneRecursiveReal(remiander);
 * */

public class NumberofDigitOne {
	/*	countOne
	 * 		// Zero: neg check
			// First: check every digit how many one they have
			// Second: get every digit
			for (int m=1; m<=n;m*=10){
				int a = n/m;
				int b = n%m;
				int digit = a%10; 
			// Third: count every digit
				if (digit >=2 && digit <=9){
					count+= (a/10+1)*m;
				}else{
					int flag= 0;
					if (digit==1){
						flag = 1;
					}
					count+=(a/10)*m+flag*(b+1);
				}
	 * */
	public int countOne(int n){
		// Zero: neg check
		if (n <= 0){
			return 0;
		}
		// First: check every digit how many one they have
		int count = 0;
		// Second: get every digit
//		for (int m=1; m<=n;m*=10){
//			int a = n/m;
//			int b = n%m;
//          int digit = a%10;
		for (long m=1; m<=n;m*=10){
			long a = n/m;
			long b = n%m;		
			long digit = a%10;
			// Third: count every digit
			if (digit >=2 && digit <=9){
				count+= (a/10+1)*m;
			}else{
				int flag= 0;
				if (digit==1){
					flag = 1;
				}
				count+=(a/10)*m+flag*(b+1);
			}
		}
		return count;
	}
	/*countOneRecursive
	 * 		// Base case:n<10, n<=0
			// Recursive case:
			// current + next + remainder
			// digit*countOneRecursiveReal(power-1)+countOneRecursiveReal(remiander);
	 * */
	public int countOneRecursive(int n){
		// Base case:n<10, n<=0
		if (n <= 0 )
			return 0;
		if (n < 10)
			return 1;
		int power =(int) Math.pow(10, (n+"").length()-1);
		// current
		int digit = n/power;
		int remiander = n%power;
		// Recursive case:
		// current + next + remainder
		return (digit==1 ?(remiander+1):power ) + digit*countOneRecursive(power-1)+countOneRecursive(remiander);
	}	

	public static void main(String[] args){
		NumberofDigitOne sol = new NumberofDigitOne();
		int n = 13;
		System.out.println(n+" number of digit one: "+sol.countOne(n));
		System.out.println(n+" number of digit one: "+sol.countOneRecursive(n));
	    n = 99;
		System.out.println(n+" number of digit one: "+sol.countOne(n));
		System.out.println(n+" number of digit one: "+sol.countOneRecursive(n));
	    n = 215;
		System.out.println(n+" number of digit one: "+sol.countOne(n));
		System.out.println(n+" number of digit one: "+sol.countOneRecursive(n));			
	}
}
/*
 * 
 * 	// O(m), Space:O(m)
	public int countOneRecursive(int n){
		long m =1;
		int[] count = new int[2];
		helper(m, n, count);
		return count[0];
	}
	private void helper(long m, int n, int[] count){
		// Base case:
		if (m > n){
			return;
		}
		long a = n/m;
		long b = n%m;
		long digit = a%10;
		// Third: count every digit
		if (digit >=2 && digit <=9){
			count[0]+= (a/10+1)*m;
		}else{
			int flag= 0;
			if (digit==1){
				flag = 1;
			}
			count[0]+=(a/10)*m+flag*(b+1);
		}		
		// Recurse case:
		helper(m*10, n, count);
		
	    // return (digit == 1 ? (remainder+1) : power) + digit*countDigitOne(power - 1) + countDigitOne(remainder);  		
	}
 * */
