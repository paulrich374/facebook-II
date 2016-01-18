package NineChapterXMath;
/*
 *
http://www.jiuzhang.com/solutions/divide-two-integers/


Ex. 11 /2 =5 ..1
11-2-2-2-2-2 = 1
11- 2^k*X2 >=0
largest k
11 > 2^k*X2
if (X1/2 >= X2) means Xw still has a chance to << 1



 X1 / X2  abs(X1)/abs(X2), since abs we need to consider MIN_VALUE
 * 
 * 核心 OVERFLOW + Binary Search(X2 keep  << 1) ( X/2 >= Y ) // 11- 8*1-4*0 -2*1 = 1
 * 			// 11 -2-2-2-2-2 =1      O(5)=(n)
			// 11- 8*1-4*0 -2*1 = 1  O(2)=(logn)
 * 
 * 
 * OVERFLOW case1 X2  = 0 	  		=> MAX_VALUE        
 *                   
 * OVERFLOW case2 X1  = MIN_VALUE 
 *                X2  = -1    		=> MAX_VALUE     
 *                X2  = MIN_VALUE	=> 1
 *                X2 others         => X1+abs(X2), quotient++ 
 *                
 * OVERFLOW case3 X2  = MIN_VALUE(X1  != MIN_VALUE )
 *                					=> ALL 0
 * 
 * NORMAL   case4 Binary Search X2<<=1, digit++ and then quo+2, X1- X2 and then digit--,X2>>=1
 * 				  1. neg = (X1^X2)>>31 & 0x1 == 1 ? true: false;
 *                1.5 ABS(X1) ,ABS(X2)
 * 				  2. Binary search  (X2 keep  << 1) like 
 * 						while ( (X1 >>1) >= X2){
 * 							X2 <<=1;
 * 							digit++;
 * 						}
 *                3. CALCULATE  digit >= 0 , X1 >= X2
 *                		while ( digit >= 0 ){
 *                			if (X1 >= X2){
 *                				quotient += 1 << digit;
 *                				X1 -= X2;
 *                			}
 *                			digit--;
 *      					X2 >>=1;
 *                		}
 *                
 *                

 * */
/*
 * Case1 (X2 == 0
 * Case2 X1 == Integer.MIN_VALUE // X2 == -1
 *  							// X2 == MIN
 *  							// otherwise
 * Case3 X2 == Integer.MIN_VALUE  
 * Case4  
 * // 1. neg
 * // 2. Binary search  (X2 keep  << 1) like
 * 			int digit = 0;
			while ( (X1>>1) >= X2){
				digit++;
				X2 <<=1;
			} 
 * // 3. CALCULATE
 * 			while(digit >=0 ){
				if (X1 >= X2){
					quotient+= (1<<digit);
					X1 -= X2;
				}
				digit--;
				X2 >>=1;
			}
 * */



/*divide ITERATIVE
 * 					Case1 X2 == 0
 *					Case2 X1 == MIN //&& X2 == -1
 *  								//&& X2 == MIN
 *  								//&& otherwise
 * 					Case3 X2 == MIN  
 * 					Case4  
		 				// 1. neg and abs
		 				// 2. Binary search  (X2 keep  << 1) like 
						while ( (X1>>1) >= X2){
							digit++;
							X2 <<=1;
						}		 
		 				// 3. CALCULATE
						while(digit >=0 ){
							if (X1 >= X2){
								quotient+= (1<<digit);
								X1 -= X2;
							}
						digit--;
						X2 >>=1;
						}
 * */
/*http://www.jiuzhang.com/solutions/divide-two-integers/
		/*divideLong
		 * 		// case1 X2 ==0
		 * 		// case new X1 == 0
		 * 		// case2 X1 =MIN && X2 ==-1
		 *		// Case4 
		 *      		1. neg and long abs
		 *      		2. binary search and calculate for each X1-X2*2^k
		 *      while (a >= b){
				int shift = 0;
				// approaching
				while(a >= (b<<shift)){ // X1 >= (X2 * 2^shift)
					shift++;
				}
				a   -= b<<(shift-1);
				res += 1<<(shift-1);
			}
		 * */
public class DivideTwoIntegers {
		/*divide ITERATIVE
		 * Case1 X2 == 0
		 * Case2 X1 == Integer.MIN_VALUE //&& X2 == -1
		 *  							//&& X2 == MIN
		 *  							//&& otherwise
		 * Case3 X2 == Integer.MIN_VALUE  
		 * Case4  
		 * // 1. neg and abs
		 * // 2. Binary search  (X2 keep  << 1) like 
				while ( (X1>>1) >= X2){
					digit++;
					X2 <<=1;
				}		 
		 * // 3. CALCULATE
				while(digit >=0 ){
					if (X1 >= X2){
						quotient+= (1<<digit);
						X1 -= X2;
					}
					digit--;
					X2 >>=1;
				}
		 * */
		public int divide(int X1, int X2){
			int quotient = 0;
			// Case1
			if (X2 == 0)
				//return Integer.MAX_VALUE;
				return X1 >=0 ? Integer.MAX_VALUE:Integer.MIN_VALUE;
			// Case2
			if (X1 == Integer.MIN_VALUE){
				// X2 == -1
				if (X2 == -1){
					return Integer.MAX_VALUE;
				}
				// X2 == MIN
				if (X2 == Integer.MIN_VALUE){
					return 1;
				}
				// otherwise
				X1+=Math.abs(X2);
				quotient++;
			}
			// Case3
			if (X2 == Integer.MIN_VALUE)
				return 0;
			// Case 4
			// 1. neg and abs
			boolean neg = ( (X1^X2) >> 31 & 0x1) == 1;
			X1 = Math.abs(X1);
			X2 = Math.abs(X2);
			// 2. Binary search
			int digit = 0;
			while ( (X1>>1) >= X2){
				digit++;
				X2 <<=1;
			}
			// 3. CALCULATE
			while(digit >=0 ){
				if (X1 >= X2){
					quotient+= (1<<digit);
					X1 -= X2;
				}
				digit--;
				X2 >>=1;
			}
			return neg?-quotient:quotient;
			
		}
		/*divideLong
		 * 		// case1 X2 ==0
		 * 		// case new X1 == 0
		 * 		// case2 X1 =MIN && X2 ==-1
		 *		// Case4 
		 *      		1. neg and long abs
		 *      		2. binary search and calculate for each X1-X2*2^k
		 *      while (a >= b){
				int shift = 0;
				// approaching
				while(a >= (b<<shift)){ // X1 >= (X2 * 2^shift)
					shift++;
				}
				a   -= b<<(shift-1);
				res += 1<<(shift-1);
			}
		 * */
		public int divideLong (int X1, int X2){
			// case1 X2 ==0
			if (X2 ==0){
				return X1 >=0 ? Integer.MAX_VALUE:Integer.MIN_VALUE;
			}
			// case new X1 == 0
			if (X1 == 0){
				return 0;
			}
			// case2 X1 =MIN && X2 ==-1
			if (X1 == Integer.MIN_VALUE && X2 == -1){
				return Integer.MAX_VALUE;
			}
			//1.neg
			boolean neg = (X1>0 && X2 <0) || (X1<0 && X2 >0 );
			long a= Math.abs((long)X1);
			long b= Math.abs((long)X2);
			//2.binary search
			int res = 0;
			while (a >= b){
				int shift = 0;
				// approaching
				while(a >= (b<<shift)){ // X1 >= (X2 * 2^shift)
					shift++;
				}
				a   -= b<<(shift-1);
				res += 1<<(shift-1);
			}
			return neg?-res:res;
		}
		public static void main(String[] arg){
			DivideTwoIntegers sol = new DivideTwoIntegers();
			System.out.println(1<<0);//1   * 2^0
			System.out.println(1<<1);//1   * 2^1
			System.out.println(1<<2);//1   * 2^2
			System.out.println(1<<5);//1   * 2^5
			System.out.println(5<<1);//5   * 2^0
			System.out.println(10>>1);//10 / 2^1
			System.out.println(2>>1);//2   / 2^1	
			
			System.out.println("divide(11,2): "+sol.divide(11,2));
			System.out.println("divide(-2147483648,-2147483648): "+sol.divide(-2147483648,-2147483648));
			System.out.println("divide(-2147483648,0): "+sol.divide(-2147483648,0));
			System.out.println("divide(-2147483648,-1): "+sol.divide(-2147483648,-1));
			
			System.out.println("divideLong(11,2) : "+sol.divideLong(11,2));
			System.out.println("divideLong(-2147483648,-2147483648): "+sol.divideLong(-2147483648,-2147483648));
			System.out.println("divideLong(-2147483648,0): "+sol.divideLong(-2147483648,0));
			System.out.println("divideLong(-2147483648,-1): "+sol.divideLong(-2147483648,-1));			
		}
}
/*
 * * case2 X2 != 0 => case2-1 X2 = MIN_VALUE 
 * 							    case2-1-1 X1 = MIN_VALUE	=> 1
 * 						    	case2-1-2 X1 = NOT MIN_VALUE=> -0.99 === 0
 *                  case2-2 X2 = MAX_VALUE
 * 							    case2-2-1 X1 = MIN_VALUE 	=> -1.xx
 * 						    	case2-2-2 X1 = MAX_VALUE 	=> 1
 *                  case2-3  10/2 = 5     => 10-2-2-2-2-2 = 0 < 2
 *                           11/2 = 5 ..1 => 10-2-2-2-2-2 = 1 < 2
 *                           QUotient = 0;
 *                           while (X1 >= X2){
 *                           	X1 -= X2;
 *                           	Quotient++; 
 *                           }
 *                           remaining = X1;
 *                           return Quotient;  
 *  *                E.g., 11/2 =5	==> 
 *                11/2(11/2 >= 2) X2 =4 digit =1
 *                11/4(11/2 >= 4) X2=8 digit =2
 *                X 11/8(11/2 <  8) 
 *                
 *                11 >= 8, quo+=1<<2=4, 11-8 =3
 *                8->4
 *                digit-- ->1
 *                
 *                3 >= 4, 
 *                2->1
 *                digit-->0
 * 				  
 * 				  3 >= 0, quo+=1<<0=4+1=5
 *                1->0
 *                digit-->-1
 */
