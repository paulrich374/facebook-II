package NineChapterXMath;

import java.util.LinkedList;

/*
 * 
 * 
 *        NOTE: (1), 1, 1*
 *             if (numStack.size() < 2) {
            while (!opStack.isEmpty()) {
                opStack.pop();
            }
            return;
        }
 *        
 *      Character.isDigit saveisValidNumber  
 *        
 *        
 * pop up two and calculate and put it back
 * check precendece and do calculate first
 * 
 * "1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
 * You may assume that the given expression is always valid.
http://buttercola.blogspot.com/2015/09/leetcode-basic-calculator-ii.html
http://buttercola.blogspot.com/2015/08/leetcode-basic-calculator.html
 * */

/*
The problem looks complicated to implement, actually could be memorized in a template way. 
0. If the token is a number, then push it to the num stack. 
If the token is a operator, there are 5 cases to consider:
1. If the opStack is empty
  -- push into opStack
2. If the token is + or -
  -- Check the top of the opStack. 
     -- If the top is '(', then push the token into opStack.
     -- Else (which means has higher or equal precedence), consume the stack by doing one computation. 
3. If the token is * or /
     -- If the top is '(', push the token into opStack
     -- Else if top has lower precedence ( + or -), push the token into opStack.
     -- Else if the top has equal precedence (* or /), consume the stack by doing one computation. 
4. If the token is (
    -- Push into opStack
5. If the token is )
   -- Doing computations until we see the '(' in the opStack. 
 * 
 * */



/*eval S:O(n)
 * 		// Zero: null check
	// First: loop insert
	//       0: space		
	//       1: number    
	//       2: operator   	+ - * / (pre for *,/ and +- if now is +-)
	// 		 3. operator   	(
	//       4. operator   	) 		 
	// Second: final calculate 
 * */
/*hasHigherPrecedence - 
 * no parentheses, so *,/ precede
 * "5+5*6" should output 35
 * */
/*calculate  
 *		// Zero:edge case check
 * 		// First: first pop is next and second pop is prev
 * 		// Second: push result to stackNum
 * */
public class BasicCalculator {

	
	
	
	
	
	/*eval S:O(n)
	 * 		// Zero: null check
		// First: loop insert
		//       0: space		
		//       1: number    
		//       2: operator   	+ - * / (pre for *,/ and +- if now is +-)
		// 		 3. operator   	(
		//       4. operator   	) 		 
		// Second: final calculate 
	 * */
	public int evalTwo(String s){
		// Zero: null check
		if (s == null || s.length() == 0)
			return 0;
		// First: loop insert
		LinkedList<Integer> stackNum = new LinkedList<Integer>();
		LinkedList<Character> stackOp = new LinkedList<Character>();
		for (int i = 0 ; i < s.length(); i++){
			char c = s.charAt(i);
			// 0. space
			if (c == ' ')
				continue;
			// 1. number 123
			if (Character.isDigit(c)){
				//stackNum.push((int)(c-'0'));
				// "123+3" How to get 123 from 123
				int prev = (int)(c-'0');
				int j = i+1;
				while(j < s.length() && Character.isDigit(s.charAt(j))){
					prev = prev*10 + (int)(s.charAt(j) -'0');
					j++;
				}
				stackNum.push(prev);
				i = j-1; // the last is not digit		
			// 2. + - * /	
			} else if (c == '+' || c == '-' || c == '*' || c == '/'){
				if (stackOp.isEmpty()){
					stackOp.push(c);
				} else if (hasHigherPrecedence(stackOp.peek(), c)) {
					calculate( stackOp,stackNum);
					stackOp.push(c);
				} else {
					stackOp.push(c);
				}
			// 3. (	
			} else if (c =='('){
				stackOp.push(c);
			// 4. )	
			} else if (c == ')'){
				while(stackOp.peek()!='('){
					calculate( stackOp,stackNum);
				}
				stackOp.pop();// pop left parentheses
			}
		}
		// Second: final calculate 
		while (!stackOp.isEmpty()){
			calculate(stackOp,stackNum);
		}
		if (!stackNum.isEmpty() && stackNum.size() == 1)
			return stackNum.pop();
		return 0;

	}
	
	
	
	/*hasHigherPrecedence - 
	 * no parentheses, so *,/ precede
	 * "5+5*6" should output 35
	 * */
	private boolean hasHigherPrecedence(char c1, char c2){
		if (c1 == '(')
			return false;
		if (c1 == '*' || c1 == '/')
			return true;
		if ((c1 == '+' || c1 == '-') && (c2 == '+' || c2 == '-'))
			return true;
		return false;
	}	
	
	
	
	/*calculate  
	 *		// Zero:edge case check
	 * 		// First: first pop is next and second pop is prev
	 * 		// Second: push result to stackNum
	 * */
	private void calculate(LinkedList<Character> stackOp, LinkedList<Integer> stackNum){// Just update stackNum	
		// Zero:edge case check
		if (stackNum.size() < 2) {
            while (!stackOp.isEmpty()) {
            	stackOp.pop();
            }
            return;
        }
		// First: first pop is next and second pop is prev
		char operator = stackOp.pop();
		int operandNext = stackNum.pop();
		int operandPrev = stackNum.pop();
		int res = 0;
		switch(operator){
			case '+':
				res = operandPrev + operandNext;
				break;
			case '-':
				res = operandPrev - operandNext;
				break;
			case '*':
				res = operandPrev * operandNext;
				break;
			case '/':
				res = operandPrev / operandNext;
				break;				
		}
		// Second: push result to stackNum
		stackNum.push(res);
	}
	
	
	
	public static void main(String[] args){
		BasicCalculator sol = new BasicCalculator();
		String s = "1 + 1";
		//System.out.println(s+" = "+sol.evalI(s));
		System.out.println(s+" = "+sol.evalTwo(s));
		
		
		 s = " 2-1 + 2 ";
		//System.out.println(s+" = "+sol.evalI(s));
		System.out.println(s+" = "+sol.evalTwo(s));

		 s = "(1+(4+5+2)-3)+(6+8)";
		//System.out.println(s+" = "+sol.evalI(s));
		System.out.println(s+" = "+sol.evalTwo(s));
		
		s="1*";
		System.out.println(s+" = "+sol.evalTwo(s));

		s="123+4";
		System.out.println(s+" = "+sol.evalTwo(s));		
		
	}
}


/*
 * 	public int evalI(String s){
		// zero: null check
		if (s == null || s.length() ==0)
			return 0;
		// First: two stacks, (space, number, others)insert
		LinkedList<Integer> numStack = new LinkedList<Integer>();
		LinkedList<Character> opStack = new LinkedList<Character>();
		
		for (int i = 0 ; i < s.length(); i++){
			char c = s.charAt(i);
			if (c == ' ')
				continue;
			// 0. If the token is a number, then push it to the num stack. 
			if (c >= '0' && c <= '9'){
				//numStack.push(Integer.valueOf(c));
				// numStack.push((int)(c-'0'));
				// 123+3 = how to get "123"
				int prev = (int)(c-'0');
				int j = i+1;
				while(j < s.length() && Character.isDigit(s.charAt(j))){
					prev = prev*10 + (int)(s.charAt(j) -'0');
					j++;
				}
				numStack.push(prev);
				i = j-1; // the last is not digit
	
			// 1. If the opStack is empty
			// 2. If the token is + or -
			// 3. If the token is * or /
			// 4. If the token is (
			// 5. If the token is )
			} else {
				
				if (c == ')' ){
					while (opStack.peek() != '(')
						calculate(numStack, opStack);
					if (opStack.peek()  == '(')
						 opStack.pop();
				}	
			}
		}
		// Second: final calculate, pop up
		while (!opStack.isEmpty()){
			calculate(numStack, opStack);			
		}
		return (numStack.size()==1) ?numStack.pop():0;		
	}
	//private boolean precedence(char c){
	//	if (c == ')')
	//		return true;
	//}
	calculate
	 * // Zero:edge case chexk
		// First: first pop is b and second pop is a
	 * 
	private void calculate (LinkedList<Integer> numStack,LinkedList<Character> opStack ){
	    // Zero:edge case chexk
		if (numStack.size() < 2) {
	            while (!opStack.isEmpty()) {
	                opStack.pop();
	            }
	            return;
	    }
		// First: first pop up is b and then a
		char c = opStack.pop();
		int b = numStack.pop();// later
		int a = numStack.pop();// pre
		switch(c){
			case '+':
				numStack.push(a+b);
				return;
			case '-':
				numStack.push(a-b);				
				return;			
		}
	}
	
	*/
/*
// First: POSTFIX Mocking- two stacks, one for number and the other for operators
//        for loop to iterating through expression string 
//		 case0: space		
//        case1: number    => stack
//        case2: operator
//               case2-1   empty
//               case2-2   hasHigherPrecedence, // put c2 in later since c1 has precedence
//               case2-3   others, // put c2 in first since c2 has precedence
//        T:O(n), Sapce:O(n)
LinkedList<Integer> stackNum = new LinkedList<Integer>();
LinkedList<Character> stackOp = new LinkedList<Character>();
for (int i = 0 ; i < s.length(); i++){
	char c = s.charAt(i);
	// case0: space
	if (c == ' ')
		continue;
	// case1: number  
	if (Character.isDigit(c)){
		//stackNum.push((int)(c-'0'));
		int prev = (int)(c-'0');
		int j = i+1;
		while(j < s.length() && Character.isDigit(s.charAt(j))){
			prev = prev*10 + (int)(s.charAt(j) -'0');
			j++;
		}
		stackNum.push(prev);
		i = j-1; // the last is not digit				
	// case2: operator				
	} else if (c == '+' || c == '-' || c == '*' || c == '/'){
		//stackOp.push(c);
		// case2-1   empty
		// NOTE: Exception in thread "main" java.lang.NullPointerException
		if (stackOp.isEmpty()){
			stackOp.push(c);
		}
		// case2-2   hasHigherPrecedence
		else if (hasHigherPrecedenceTwo(stackOp.peek(), c)){// c1 precede
			calculateTwo(stackOp, stackNum);
			stackOp.push(c);// put c2 in later since c1 has precedence
		}	

    // case3: Others, return 0	
	} else if (c == '('){
		stackOp.push(c);
	} else if (c == ')'){
		while(stackOp.peek() != '('){
			calculateTwo(stackOp, stackNum);
		}
		stackOp.pop();// pop left parentheses			
	}
}
// Second: Final Calculation
while (!stackOp.isEmpty()){
	calculateTwo(stackOp, stackNum);
}
// third: return res
return stackNum.pop();	
*/
