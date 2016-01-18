package NineChapterXMath;

import java.util.ArrayList;
import java.util.HashSet;
/*fraction
 * 
 *     hashSet maintain insertion order
		// Zero: X2 ==0, X1 ==0, X1 == MIN, X2 =MIN
		// First: first part (X1/X2), sign,abs long 
 		// Second: second part(X1%X2 recurring), set, Flag
		// third: recalculate, Flag&parenthenses

 * */
public class FractionRecurring {
	public String fraction(int X1, int X2){
		// Zero: X2 ==0, X1 ==0, X1 == MIN, X2 =MIN
		if (X2 == 0){
			return (X1 >0)? String.valueOf(Integer.MAX_VALUE): String.valueOf(Integer.MIN_VALUE);
		}
		if (X1 == 0)
			return String.valueOf(0);
		if (X1 == Integer.MIN_VALUE && X2 == -1){
			return String.valueOf(Integer.MAX_VALUE);
		}
		if (X1 == Integer.MIN_VALUE && X2 == Integer.MIN_VALUE){
			return String.valueOf(1);
		}
		// First: first part (X1/X2), sign,abs long
		String firstPart = "";
		String sign = ((X1>0 && X2 <0) || (X1<0 && X2 >0) )? "-":"";
		long X1_L = Math.abs((long)X1);
		long X2_L = Math.abs((long)X2);
		long first = X1_L/X2_L;
		firstPart = sign + String.valueOf(first);
		if (X1_L%X2_L == 0)
			return firstPart;
		// Second: second part(X1%X2 recurring), set, Flag
		String secondPart = "";	
		//ArrayList<Integer> digit = new ArrayList<Integer>();
		//HashSet<Integer> set = new HashSet<Integer>();
		HashSet<Long> set = new HashSet<Long>();
		Long second = (X1_L%X2_L);
		set.add(second);
		boolean recurringFlag = false;
		while(second != 0){
			second = second*10 % X2_L;
			if (set.contains(second)){
				recurringFlag = true;
				break;
			}else {
				set.add(second);
			}
		}
		// third: recalculate, Flag&parenthenses
		if (recurringFlag)
			secondPart = secondPart + "(";
		for (Long i:set){
			Long digit = i*10 / X2_L;
			secondPart +=String.valueOf(digit);
		}
		if (recurringFlag)
			secondPart = secondPart + ")";
		return firstPart+"."+secondPart;
		
	}
	public static void main(String[] args){
		FractionRecurring sol = new FractionRecurring();
		int X1 = 1;
		int X2 = 2;
		// return "0.5"
		System.out.println(X1+" / "+X2 +"= "+sol.fraction(X1,X2));
		X1 = 2;
		X2 = 1;
		// return "2"
		System.out.println(X1+" / "+X2 +"= "+sol.fraction(X1,X2));	
		X1 = 2;
		X2 = 3;
		// return "0.(6)"
		System.out.println(X1+" / "+X2 +"= "+sol.fraction(X1,X2));	
		X1 = 4;
		X2 = 7;
		// return ""
		System.out.println(X1+" / "+X2 +"= "+sol.fraction(X1,X2));			
	}
}
