package NineChapterXMath;

import java.util.LinkedList;

/*
 *              NOTE:
 * 				NEVER: Integer.valueOf(c)
 *  			USE (int) c-'0'
 * 		
 *              NOTE: 
 *              encoutner * or / 
 *              next integer not coming in, FORCE to come in
 *              
 *              NOTE: 
 *              opStack not pop in calculate 
 *              pass operator as parameter in calculate
 *              
 *              NOTE: 
 *              later pop
 *              prev pop
 *              
 *              NOTE: 
 *              when force come in=> check is not space
 *              
 * Evaluate Reverse Polish Notation
 * 
 *  * pop up two and calculate and put it back
 * check precendece and do calculate first
 * 
 * 
 * "3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
 * You may assume that the given expression is always valid.


 * http://buttercola.blogspot.com/2015/09/leetcode-basic-calculator-ii.html
 * */
public class BasicCalculatorII {
	
	
	
	public int evalII(String s){
		// zero: null check
		if (s == null || s.length() ==0)
			return 0;
		// First: two stacks, (space, number, others)insert, precde=>force come in=>calcualte
		LinkedList<Integer> numStack = new LinkedList<Integer>();
		LinkedList<Character> opStack = new LinkedList<Character>();
		int i = 0;
		//for (int i = 0 ; i < s.length(); i++){
		while (i < s.length()){
			char c = s.charAt(i);
			if (c == ' '){
				i++;
				continue;
			}	
			if (c >= '0' && c <= '9'){
				//numStack.push(Integer.valueOf(c));
				numStack.push((int) c-'0');
				 
			} else {
				if (c == '*' || c == '/'){
					//if ((i+1) < s.length()){
					while ((i+1) < s.length() ){
						if (s.charAt(i+1) != ' '){
							numStack.push((int)(s.charAt(i+1) - '0'));
							i++;
							break;
						}
						i++;
					}	
					calculate(numStack, c);
				// NOTE: only else to push	
				} else {	
					opStack.push(c);
				}
			}
			i++;

			
		}		
		
		// Second: final calculate, pop up	
		while (!opStack.isEmpty()){
			calculate(numStack, opStack.pop());			
		}
		return (numStack.size()==1) ?numStack.pop():0;
	}
	
	
	
	private void calculate (LinkedList<Integer> numStack, Character c ){
		//char c = opStack.pop();
		int b = numStack.pop();
		int a = numStack.pop();
		switch(c){
			case '+':
				numStack.push(a+b);
				return;
			case '-':
				numStack.push(a-b);				
				return;	
			case '*':
				numStack.push(a*b);
				return;
			case '/':
				numStack.push(a/b);				
				return;	
			default:
				return;
		}
	}	
	
	
	public static void main(String[] args){
		BasicCalculatorII sol = new BasicCalculatorII();
		String s = "3+2*2";
		System.out.println(s+" = "+sol.evalII(s));
		 s = " 3/2 ";
		System.out.println(s+" = "+sol.evalII(s));
		 s = " 3+5 / 2 ";
		System.out.println(s+" = "+sol.evalII(s));		
	}
}
