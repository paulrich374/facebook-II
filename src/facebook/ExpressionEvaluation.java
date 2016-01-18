package facebook;

import java.util.Arrays;
import java.util.LinkedList;

/*						**** constant space solution => without using a stack
 *                       背 Like Tree Traversal -  Stack Solution and Without Stack Solution
 * 						stackOp.peek() check if empty before call it
 * 
 * 						Infix notation
 * 						背  two stacks and check PRECEDENCE to 背  calculate and push back
 *                      no stacks and keep 3 variables like stack
 * 
 * Write a function that calculates input strings with operators +,-,*,/ 
 * eg. "5+5*6" should output 35
http://buttercola.blogspot.com/2014/11/facebook-expression-evaluation.html   
   
   With Parentheses     T:O(n) S:O(n)
   Without Parentheses  T:O(n) S:O(n) hasHigherPrecedence()
   Without Parentheses  T:O(n) S:O(1) hasHigherPrecedence() by using divide and m reset and sum pre-calcualted
 * */
/*eval S:O(n)
 * 		// Zero: null check
	// First: POSTFIX Mocking- two stacks, one for number and the other for operators
	//        for loop to iterating through expression string 
	//        case1: number    => stack
	//        case2: operator
	//               case2-1   empty
	//               case2-2   hasHigherPrecedence
	//               case2-3   others
	//        T:O(n), Sapce:O(n)
	// Second: Final Calculation
	// third: return res 
 * */
/*evalConstantSpace S:O(1)
 * 		// zero: null check
 * 			// First: for loop iterate and check odd or even index
	//        if even =>digit=> m * or / current digit
	//        if odd  =>operator=> * or /, keep record(will do *, / at next digit). '+' or '-' calculate previous sum and reset m(+1 or -1)
	//        sum    : keep calculated result, only when '+' or '-' happen, either add or minus next OR the next *,/ calculated result
	//        m      : keep previous number, //reset to record next number 
	//        divide : keep previous operator
	//        // last must be a digit and record in m, sum+=m;
 * */
/*evalWithParentheses
//First: for loop iteration to put tokens on the stack
//     case1 : number
//     case2 : operator : as OLD
//     多case3: left parentheses: push
//     多case4: right parentheses: as Calculation till find "("
//              // pop left parentheses
* 
* */
public class ExpressionEvaluation {
	
	/*eval S:O(n)
	 * 		// Zero: null check
		// First: POSTFIX Mocking- two stacks, one for number and the other for operators
		//        for loop to iterating through expression string 
		//        case1: number    => stack
		//        case2: operator
		//               case2-1   empty
		//               case2-2   hasHigherPrecedence
		//               case2-3   others
		//        T:O(n), Sapce:O(n)
		// Second: Final Calculation
		// third: return res 
	 * */
	public int eval(String s){
		// Zero: null check
		if (s == null || s.length() == 0)
			return 0;
		// First: POSTFIX Mocking- two stacks, one for number and the other for operators
		//        for loop to iterating through expression string 
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
			// case1: number  
			if (isValidNum(c)){
				stackNum.push((int)(c-'0'));
			// case2: operator				
			} else if (c == '+' || c == '-' || c == '*' || c == '/'){
				//stackOp.push(c);
				// case2-1   empty
				// NOTE: Exception in thread "main" java.lang.NullPointerException
				if (stackOp.isEmpty()){
					stackOp.push(c);
				}
				// case2-2   hasHigherPrecedence
				else if (hasHigherPrecedence(stackOp.peek(), c)){// c1 precede
					calculate(stackOp, stackNum);
					stackOp.push(c);// put c2 in later since c1 has precedence
				}	
				// case2-3   others
				else { // c2 precede
					stackOp.push(c);// put c2 in first since it has precedence
					//calculate(stackOp, stackNum);
				}
		    // case3: Others, return 0	
			} else {
				return 0;
			}
		}
		// Second: Final Calculation
		while (!stackOp.isEmpty()){
			calculate(stackOp, stackNum);
		}
		// third: return res
		return stackNum.pop();
	}
	/*hasHigherPrecedence - no parentheses, so *,/ precede
	 * "5+5*6" should output 35
	 * */
	private boolean hasHigherPrecedence(char c1, char c2){
		if (c1 == '*' || c1 == '/')
			return true;
		if ((c1 == '+' || c1 == '-') && (c2 == '+' || c2 == '-'))
			return true;
		return false;
	}
	/*calculate -  Just update stackNum	
	 * 		// First: pop up one operator and two number to compute
			// Second: put the calculated result back to stackNum
	 * */
	//private int calculate(LinkedList<Character> stackOp, LinkedList<Integer> stackNum){
	private void calculate(LinkedList<Character> stackOp, LinkedList<Integer> stackNum){// Just update stackNum	
		// First: pop up one operator and two number to compute
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
		// Second: put the calculated result back to stackNum
		stackNum.push(res);
	}
	/*isValidNum
	 * 	// Zero: null check
	 * http://stackoverflow.com/questions/4510136/how-to-check-if-a-char-is-equal-to-an-empty-space
		if (c == ' '){
			return false;
		}
	 * */
	private boolean isValidNum (char c){
		// Zero: null check
		if (c == ' '){
			return false;
		}
		// Frist: check digit
		if (c >='0' && c <='9'){
			return true;
		}
		return false;
	}
	/*evalConstantSpace S:O(1)
	 * 		// zero: null check
	 * 			// First: for loop iterate and check odd or even index
		//        if even =>digit=> m * or / current digit
		//        if odd  =>operator=> * or /, keep record(will do *, / at next digit). '+' or '-' calculate previous sum and reset m(+1 or -1)
		//        sum    : keep calculated result, only when '+' or '-' happen, either add or minus next OR the next *,/ calculated result
		//        m      : keep previous number, //reset to record next number 
		//        divide : keep previous operator
		//        // last must be a digit and record in m, sum+=m;
	 * */
	public int evalConstantSpace(String s){
		// zero: null check
		if (s == null || s.length() == 0)
			return 0;
		// First: for loop iterate and check odd or even index
		//        if even =>digit=> m * or / current digit
		//        if odd  =>operator=> * or /, keep record(will do *, / at next digit). '+' or '-' calculate previous sum and reset m(+1 or -1)
		//        sum    : keep calculated result, only when '+' or '-' happen, either add or minus next OR the next *,/ calculated result
		//        m      : keep previous number, //reset to record next number 
		//        divide : keep previous operator
		//        // last must be a digit and record in m, sum+=m;
		int sum = 0;
		int m = 1;
		boolean divide = false;
		for (int i = 0 ; i < s.length(); i++){
			if (i % 2 == 0){
				int digit = (int)(s.charAt(i) - '0');
				if (!divide)
					m *= digit;
				else
					m /= digit;
			} else {
				char c = s.charAt(i);
				divide = false;
				if (c == '+'){
					sum += m;
					m = 1;//reset to record next number
				} else if (c == '-') {
					sum += m;
					m = -1;//reset to record next number
				} else if (c == '/'){
					divide = true;
				}
			}
		}
		sum += m;// last must be a digit and record in m
		return sum;
	}
	/*evalWithParentheses
		// First: for loop iteration to put tokens on the stack
		//        case1 : number
		//        case2 : operator : as OLD
		//        多case3: left parentheses: push
		//        多case4: right parentheses: as Calculation till find "("
		//                 // pop left parentheses
	 * 
	 * */
	public int evalWithParentheses (String[] token){
		// First: for loop iteration to put tokens on the stack
		//        case1 : number
		//        case2 : operator : as OLD
		//        多case3: left parentheses: push
		//        多case4: right parentheses: as Calculation till find "("
		//                 // pop left parentheses
		LinkedList<Integer> stackNum = new LinkedList<Integer>();
		LinkedList<String> stackOp = new LinkedList<String>();
		for (int i = 0 ; i < token.length; i++){
			String s = token[i];
			if (isValidNumString(s)){
				stackNum.push(Integer.parseInt(s));
			} else if (s == "+" || s == "-" || s == "*" || s == "/"){
				if (stackOp.isEmpty()){
					stackOp.push(s);
				} else if (hasHighPrecedenceString(stackOp.peek(), s)) {
					calculateString(stackNum, stackOp);
					stackOp.push(s);
				} else {
					stackOp.push(s);
				}
			} else if (s == "("){
				stackOp.push(s);
			} else if (s == ")"){
				while(!stackOp.peek().equals("(")){
					calculateString(stackNum, stackOp);
				}
				stackOp.pop();// pop left parentheses
			}
		}
		// Second: calcualte the rest
		while (!stackOp.isEmpty()){
			calculateString(stackNum, stackOp);
		}
		if (!stackNum.isEmpty() && stackNum.size() == 1)
			return stackNum.pop();
		return 0;
		//return stackNum.pop();
	}
	private void calculateString(LinkedList<Integer> stackNum, LinkedList<String> stackOp){
		String op = stackOp.pop();
		int operandNext = stackNum.pop();
		int operandPrev = stackNum.pop();
		int res = 0;
		switch(op){
			case "+":
				res =  operandPrev + operandNext;
				break;
			case "-":
				res =  operandPrev - operandNext;
				break;
			case "*":
				res =  operandPrev * operandNext;
				break;
			case "/":
				res =  operandPrev / operandNext;								
				break;			
		}
		stackNum.push(res);
	}	
	private boolean hasHighPrecedenceString(String s1, String s2){
		if (s1.equals("("))
			return false;
		if (s1.equals("*") || s1.equals("/"))
			return true;
		if ((s1.equals("+") || s1.equals("-")) && (s2.equals("+") || s2.equals("-")))
			return true;
		return false;
	}
	private boolean isValidNumString (String s){
		// Zero: null check
		if (s == null || s.length() == 0){
			return false;
		}
		// Frist: check digit
		if (s.charAt(0) >='0' && s.charAt(0) <='9'){
			return true;
		}
		return false;
	}
	public static void main(String[] args){
		ExpressionEvaluation sol = new ExpressionEvaluation();
		String s = "5+5*6";
		System.out.println("Expression: "+s+" = "+sol.eval(s));
		System.out.println("Expression: "+s+" = "+sol.evalConstantSpace(s));				
		String s2 = "3*5*3";
		System.out.println("Expression: "+s2+" = "+sol.eval(s2));	
		String s3 = "3*5-3";
		System.out.println("Expression: "+s3+" = "+sol.eval(s3));
		String s4 = "3-5-3";
		System.out.println("Expression: "+s4+" = "+sol.eval(s4));	
		String s5 = "6/2-1";
		System.out.println("Expression: "+s5+" = "+sol.eval(s5));
		System.out.println("Expression: "+s5+" = "+sol.evalConstantSpace(s5));	
		//String[] tokens = new String[]{"100", "*", "(", "2", "+", "12", ")", "/", "14"};
		String[] tokens = new String[]{"(", "1", "(","+", "4", "+", "5", "+", "2", ")","-","3",")","+","(","6","+","8",")"};
		System.out.println("Expression: "+Arrays.toString(tokens)+" = "+sol.evalWithParentheses(tokens));

	}
}
/*
 * 
 * 				//stackOp.push(c);
				// case2-1   empty
				if (stackOp.isEmpty()){
					stackOp.push(c);
				}
				// case2-2   hasHigherPrecedence
				else if (hasHigherPrecedence(stackOp.peek(), c)){// c1 precede
					calculate(stackOp, stackNum);
					stackOp.push(c);// put c2 in later since c1 has precedence
				}	
				// case2-3   others
				else { // c2 precede
					stackOp.push(c);// put c2 in first since it has precedence
					//calculate(stackOp, stackNum);
				}
 * */
