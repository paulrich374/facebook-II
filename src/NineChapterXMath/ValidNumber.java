package NineChapterXMath;
/*
 *  E.g., +2.5e-10
 *        -2.5e-10
 *           重複 前            重複 位置 前 後 
 * Case1: .  前.前Ｅ        ｜  前.前Ｅ 頭尾 前後不數字
 * Case2: e  前Ｅ前不數字    ｜  前Ｅ    頭尾
 * Case3: +- 前不Ｅ        ｜  尾 前不Ｅ 後不數字不‘.’   
 * 
 * 
 * 
 * 		// Zero: null check
		if (s == null || s.length()==0)
			return false;
		// 1. trim
		int l = 0;
		int r = s.length()-1;
		while (l <s.length() && s.charAt(l)==' ')
			l++;
		while(r >=0 && s.charAt(r)==' ')
			r--;
		// 2. skip first
		if (s.charAt(l) == '+' || s.charAt(l) == '-'){
			l++;
		}
 * 
 * 
 * 
 * */
/*Nine
 * 1. trim
 	while (i <= e && Character.isWhitespace(s.charAt(i))) i++;
    if (i > len - 1) return false;
    while (e >= i && Character.isWhitespace(s.charAt(e))) e--;
 * 2. skip first+-
 *   if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
   3. 3 flags
         boolean num = false; // is a digit
        boolean dot = false; // is a '.'
        boolean exp = false; // is a 'e'
   4. while loop
       1. num
             if (Character.isDigit(c)) {
                num = true;
            }
       2. '.'
            else if (c == '.') {
                if(exp || dot) return false;
                dot = true;
            }
       3. 'e' 'E'                  
 *            else if (c == 'e') {
                if(exp || num == false) return false;
                exp = true;
                num = false;
            }
       4. '+' '-'     
            else if (c == '+' || c == '-') {
                if (s.charAt(i - 1) != 'e') return false;
            }
       5.  ELSE
                 else {
                return false;
            }
       6. return  
               return num;   
 * */
/* LeetCode
 *  1. trim and zero
 *      s = s.trim();  
 *      		// NOTE:******* immutable need to reassign

    if(s.length()==0)  
        return false;
 *  2.  2 flags
    boolean dotFlag = false;  
    boolean eFlag = false;  
 *  3. for loop
 *     1.'.'
                 if(
                dotFlag || 
                eFlag || 
                  ( (i==0||!(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9')) &&   
                  (i==s.length()-1||!(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'))  )
                )    =>F
                
                                 dotFlag = true;  

 *     2.'+' '-'
             if( (i>0 && (s.charAt(i-1)!='e' && s.charAt(i-1)!='E'))  || 
                   i==s.length()-1 || 
                   !(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9' || s.charAt(i+1)=='.')    )
 *     3.'e' 'E'
                 if(eFlag || 
                  i==s.length()-1 || 
                  i==0)  
                    return false;  
                eFlag = true;  
 *     4. digit
             case '0':  
            case '1':  
            case '2':  
            case '3':  
            case '4':  
            case '5':  
            case '6':  
            case '7':  
            case '8':  
            case '9':  
                break;
 *     5. else 
 *          return flase
 *     6. return 
 *         return true;     
 * */
/*Regex
 *   +1.5e-10
 *   []?  //d*   []?
 *   [+-]?  (\\d+\\.? | \\.\\d+)   //d*  (e[-+]?\\d+)?
 * */
public class ValidNumber {
	public boolean validNine(String s){
		// Zero: null check
		if (s == null || s.length()==0)
			return false;
		// 1. trim
		int l = 0;
		int r = s.length()-1;
		while (l < s.length() && s.charAt(l)==' ')
			l++;
		while(r >= 0 && s.charAt(r)==' ')
			r--;
		// 2. skip first
		if (s.charAt(l) == '+' || s.charAt(l) == '-'){
			l++;
		}
		// 3. flag
		boolean dotFlag = false;
		boolean numFlag = false;
		boolean expFlag = false;
		// 4. loop
		while(l <= r){
			char c =s.charAt(l);
			//System.out.print("char:"+c);
			// 1. num
			if (c >='0' && c <='9'){
				numFlag = true;
			}
			// 2. '.'
			else if (c == '.') {
				if (expFlag || dotFlag)
					return false;
				dotFlag= true;
			}
			// 3. 'e'
			else if (c == 'e' || c == 'E') {
				if (expFlag || numFlag == false)
					return false;
				numFlag = false;
				expFlag = true;
			}			
			// 4. '+-'
			else if (c == '+' || c == '-') {
				if (s.charAt(l-1) != 'e')
					return false;
			}			
			// 5. else return
			else  {
				return false;
			}			
			l++;
		}
		return numFlag;
	}
	public boolean validReg(String s){
		// Zero: null check
		if (s == null || s.length()==0)
			return false;
		// regex
		//String regex = "[+-]?(\\d+\\.?|\\.\\d+)//d*(e[+-]?\\d+)?";
		String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?"; 
		if (s.trim().matches(regex)){
			return true;
		}
		return false;
	}
	public boolean valid(String s){
		// Zero:
		if (s == null || s.length()==0)
			return false;		
		// 1.trim and zero
		// NOTE:******* immutable need to reassign
		s = s.trim();
		
		if (s.length()==0)
			return false;
		// 2.flag first
		boolean dotFlag =  false;
		boolean expFlag = false;
		// 3. loop
		for (int i = 0;i < s.length(); i++){
			char c = s.charAt(i);
			//System.out.println("c: "+c);
/*
 * 
 * *           重複 前            重複 位置 前 後 
 * Case1: .  前.前Ｅ        ｜  前.前Ｅ 頭尾 前後不數字
 * Case2: e  前Ｅ前不數字    ｜  前Ｅ    頭尾
 * Case3: +- 前不Ｅ        ｜  尾 前不Ｅ 後不數字或不‘.’ 
 * 
 * */			
			
			switch (s.charAt(i)) 
			{
				// 1. '.'
			case '.':
					if (expFlag || dotFlag || (i == 0 || !(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9')) && (i == s.length()-1 ||  !(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9')) )
						return false;
	                dotFlag = true;  
	                break;  
				// 2. '+-'

	            case '+':  
	            case '-':  
					//if ( i ==s.length()-1 || (i>0&& s.charAt(i-1)!='e') || !(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'&&s.charAt(i+1)=='.') )
					//	return false;
					//break;
	            	// NOTE: 後不數字“或”不‘.’ 
	                if( (i>0 && (s.charAt(i-1)!='e' && s.charAt(i-1)!='E'))  
	  	                  ||  i==s.length()-1 || !(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9' || s.charAt(i+1)=='.')    ) // "+.8" 
	  	                    return false;  
	  	                break;  
				// 3. 'eE'
	            case 'e':  
	            case 'E':
					if (expFlag || i == 0 || i == s.length()-1)
						return false;
					expFlag = true;
					break;
				// 4. num
	            case '0':  
	            case '1':  
	            case '2':  
	            case '3':  
	            case '4':  
	            case '5':  
	            case '6':  
	            case '7':  
	            case '8':  
	            case '9':  
	                break;  
	            default:  
	                return false;  
			}
		
		}
		return true;
	}		
	public static void main(String[] args){
		ValidNumber sol = new ValidNumber();
		String s = "-2.5e-10";
		System.out.println("String: "+ s + ". Valid number validNine: "+sol.validNine(s));
		System.out.println("String: "+ s + ". Valid number: "+sol.valid(s));		
		System.out.println("String: "+ s + ". Valid number validReg: "+sol.validReg(s));
		
		s = "+.8";
		System.out.println("String: "+ s + ". Valid number validNine: "+sol.validNine(s));
		System.out.println("String: "+ s + ". Valid number: "+sol.valid(s));		
		System.out.println("String: "+ s + ". Valid number validReg: "+sol.validReg(s));
		s = " 0.1 ";
		System.out.println("String: "+ s + ". Valid number validNine: "+sol.validNine(s));
		System.out.println("String: "+ s + ". Valid number: "+sol.valid(s));		
		System.out.println("String: "+ s + ". Valid number validReg: "+sol.validReg(s));
		s = "1 a";
		System.out.println("String: "+ s + ". Valid number validNine: "+sol.validNine(s));
		System.out.println("String: "+ s + ". Valid number: "+sol.valid(s));		
		System.out.println("String: "+ s + ". Valid number validReg: "+sol.validReg(s));		
	}
}
