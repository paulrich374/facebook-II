package facebook;

import java.util.Arrays;
import java.util.LinkedList;

/* 	
 * 						Postfix notation ("Reverse Polish")(RPN)
 *  					背 one stack and check operator to 背 calculate and push back
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.
Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
  
Prefix notation ("Polish")
Infix notation
Postfix notation ("Reverse Polish")
  
 * */
public class EvaluateReversePolishNotation {
	/*evalRPN
	 * 		// Zero:null check
		// First: for loop to iterate token and calculate
		//        case1: digit => push to stack 
		//        case2: operator => stack pop two and calculated to push it back to stack 
		// Second: check if only one num left in the stack and it's the final result
 
	 * */
	public int evalRPN(String[] token){
		// Zero:null check
		if (token == null || token.length == 0)
			return 0;
		// First: for loop to iterate token and calculate
		//        case1: digit => push to stack 
		//        case2: operator => stack pop two and calculated to push it back to stack 
		LinkedList<Integer> stackNum = new LinkedList<Integer>();
		for (int i = 0; i < token.length; i++){
			String s = token[i];
			if (isValidNum(s)){
				stackNum.push(Integer.parseInt(s));
			} else {
				int operandNext = stackNum.pop();
				int operandPrev = stackNum.pop();
				int res = 0;
				if (s.equals("+")){
					res = operandPrev + operandNext;
				} else if (s.equals("-")){
					res = operandPrev - operandNext;
				} else if (s.equals("*")){
					res = operandPrev * operandNext;
				} else if (s.equals("/")){
					res = operandPrev / operandNext;
				}
				stackNum.push(res);
			}
		}
		// Second: check if only one num left in the stack and it's the final result
		if (!stackNum.isEmpty() && stackNum.size() == 1){
			return stackNum.pop();
		}
		return 0;
	}
	/*isValidNum
	 * 	// Zero: null check
	 * http://stackoverflow.com/questions/4510136/how-to-check-if-a-char-is-equal-to-an-empty-space
		if (c == ' '){
			return false;
		}
	 * */
	private boolean isValidNum (String s){
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
		EvaluateReversePolishNotation sol = new EvaluateReversePolishNotation();
		String[] s = new String[]{"2", "1", "+", "3", "*"};
		System.out.println("Expression: "+Arrays.toString(s)+" = "+sol.evalRPN(s));
		String[] s5 = new String[]{"4", "13", "5", "/", "+"};
		System.out.println("Expression: "+Arrays.toString(s5)+" = "+sol.evalRPN(s5));
	}
}
