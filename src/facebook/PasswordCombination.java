package facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * 
 * Given a dictionary based simple password, 
 * create all possible (special character) passwords based on a provide mapping. 
 * For example,
Input: face
Map: {a -> @, 4, A}
Output: f@ce, f4ce, fAce
http://buttercola.blogspot.com/2014/11/facebook-password-combination.html
 * 
 * */
public class PasswordCombination {
	/*password T:O(combinations), Sapce:O(combinations), n!
	 * 		// Zero: null check
	 * 		// First: recursive backtracking
	 * 		// Second :return the res
	 * */
	public List<String> password(String s, HashMap<Character, String> dict){
		List<String> res = new ArrayList<String>();
		// Zero: null check
		if (s == null || s.length() == 0)
			return res;
		// First: recursive backtracking
		helper(s, dict, 0, new String(), res);
		// Second :return the res
		return res;
	}
	/*helper
	 //Base case: iterate to string length, save the word into res
	 // Recursive case:
		//                check map contains character or not
		//                get start mapped string
		//                for loop to start form differnt combination
		//                    backtracking string item since string is immutable, no need BT
	 * */
	private void helper(String s, HashMap<Character,String> dict, int start, String item, List<String> res){
		//Base case: iterate to string length, save the word into res
		if (item.length() >= s.length()){
			res.add(new String(item));
			return;
		}
		// Recursive case:
		//                check map contains character or not
		//                get start mapped string
		//                for loop to start form differnt combination
		//                    backtracking string item since string is immutable, no need BT
		char c = s.charAt(start);
		if (dict.containsKey(c)) {
			String str = dict.get(s.charAt(start));
			for (int i = 0; i < str.length(); i++){
				helper(s, dict, start+1, item + str.charAt(i), res);
			}			
		}else{
			helper(s, dict, start+1, item + c, res);			
		}

	}
	public static void main(String[] args){
		PasswordCombination sol = new PasswordCombination();
		String str = "face";
		HashMap<Character, String> dict = new HashMap<Character, String>();
		dict.put('a', "@A4");
		dict.put('e', "eE");
		List<String> res = sol.password(str, dict);
		System.out.println("String: "+str+"\nPassword: "+res);
	}

}
