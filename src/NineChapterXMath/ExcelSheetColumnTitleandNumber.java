package NineChapterXMath;
/*
 * Integer to Roman  4,9 IV XC CM
 * column integer  to column title  >26 -> Z
 *  1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 

 * STRING TO INTEGER 10BASE
 * Roman to integer  I, X, C => V,X or L,C or D, M
 * I  1 V   5 X  10
X 10 L  50 C 100
C100 D 500 M1000
 * column title  to column integer 26BASE
 * 
 *  A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    
    AA -> 27
    AB -> 28 
 * 
 * */


/*toTitle
 * prev=prev*26+current digit
 * 		// Zero: neg check
		// First: mod and divide
 * 		n-=1;
 * 		int digit = (n)%26;
 * 		sb.append((char) (digit+'A'));
		n/=26;
 * */

/*toNumber
		// Zero: null check
		// First: loop all characters, 26Base, 
 * 			prev=prev*26+current digit
* */


public class ExcelSheetColumnTitleandNumber {
	/*toTitle
	 * prev=prev*26+current digit
	 * 		// Zero: neg check
		// First: mod and divide

	 * 		n-=1;
	 * 		int digit = (n)%26;
	 * 		sb.append((char) (digit+'A'));
			n/=26;
	 * */
	public String toTitle(int n){
		// Zero: neg check
		if (n <=0){
			return "";
		}
		// First: mod and divide
		//StringBuilder sb = new StringBuilder();
		String res = "";
		while( n != 0){
			n-=1;
			int digit = (n)%26;
			//sb.append((char) (digit+'A'));
			res = String.valueOf((char) (digit+'A'))+res;
			n/=26;
		}
		//return sb.reverse().toString();
		return res;
		/*
n=26=>Z
n=52=>AZ
n=28=>AB
n=703=>AAA
n=18278=>ZZZ
n=1000000001=>CFDGSXM
s=Z=>26
s=AZ=>52
s=AB=>28
s=BA=>53
s=AAA=>703
s=ZZZ=>18278
s=CFDGSXM=>1000000001
 
		StringBuilder sb = new StringBuilder();
		// Zero: neg check
		// n = 26+26+26+...= 26*n+k
		// n = <=26
		if (n < 0)
			return sb.toString();
		// First:
		while(n-26 > 0){
			sb.append('A');
			n -=26;
		}
	    sb.append( (char)((n-1) + 'A'));
	    return sb.toString();
	    */		
	}

	/*toNumber
		// Zero: null check
		// First: loop all characters, 26Base, prev=prev*26+current digit
	 * */
	public int toNumber(String s){
		// Zero: null check
		if (s == null || s.length() == 0)
			return 0;
		// First: loop all characters, 26Base, prev=prev*26+current digit
		int res = 0;
		for (int i = 0 ; i < s.length(); i++){
			char c = s.charAt(i);
			int digit = (int)(c-'A')+1;
			res = res*26+digit;
		}
		return res;
		/*
		int res = 0;
		// Zero: null check
		if (s ==null || s.length() == 0)
			return 0;
		// First:
		for (int i = 0 ; i < s.length();i++){
			int value = (int)(s.charAt(i)-'A')+1;
			//if (value == 1 && i != s.length()-1)// is msb A
			if ( i != s.length()-1)// is msb A
				//res+=26;
				res+=26*value;
				
			else
				res+=value;
		}
		return res;
		*/
	}
	public static void main(String[] args){
		ExcelSheetColumnTitleandNumber sol = new ExcelSheetColumnTitleandNumber();
		int n = 26; // Z
		System.out.println("n="+n+"=>"+sol.toTitle(n));		
		n = 52; //AZ
		System.out.println("n="+n+"=>"+sol.toTitle(n));		
		n = 28; //AB
		System.out.println("n="+n+"=>"+sol.toTitle(n));
		n = 703; 
		System.out.println("n="+n+"=>"+sol.toTitle(n));		
		n =18278;
		System.out.println("n="+n+"=>"+sol.toTitle(n));		
		n = 1000000001;
		System.out.println("n="+n+"=>"+sol.toTitle(n));
		
		String s = "Z";// 26
		System.out.println("s="+s+"=>"+sol.toNumber(s));		
		s= "AZ";// 52
		System.out.println("s="+s+"=>"+sol.toNumber(s));
		s= "AB";// 28
		System.out.println("s="+s+"=>"+sol.toNumber(s));	
		s= "BA";// 28
		System.out.println("s="+s+"=>"+sol.toNumber(s));
		s= "AAA";
		System.out.println("s="+s+"=>"+sol.toNumber(s));
		s= "ZZZ";
		System.out.println("s="+s+"=>"+sol.toNumber(s));
		s="CFDGSXM";
		System.out.println("s="+s+"=>"+sol.toNumber(s));
		
	}
}
/*	//  Memory Limit Exceeded input 1000000001

 * 	//Input:
	//"BA"
	//Output:
	//3
	//Expected:
	//53
	//Input:
	//	"AAA"
	//	Output:
	//	53
	//	Expected:
	//	703	
 * 
 * */
