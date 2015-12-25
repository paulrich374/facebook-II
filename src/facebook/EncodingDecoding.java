package facebook;

import java.util.ArrayList;
import java.util.List;

/*
 * 					Rule
 *                  // ENCODE
 * 					//              e.g., sunny => 5/sunny
 *                  // DECODE
 *                  // s.indexOf('/',i), i=0 = > slashIdx => 1
 *                  // s.substring(i,slashIdx) => "5"
 *                  // s.substring(slashIdx+1, slashIdx+1+5) => sunny
 * Design an algorithm to encode a list of strings to a string. 
 * The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters.
 Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. 
Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. 
You should implement your own encode/decode algorithm.
 
 * 
 http://buttercola.blogspot.com/2015/09/leetcode-encode-and-decode-strings.html
 * */
public class EncodingDecoding {
	/*encode
	 * 		//Zero: null check
		// First :endcode 256 character ASCII
		//        Rule: (string length)+("/")+(string itself)
		//              e.g., sunny => 5/sunny
	 * */
	public String encode(List<String> strs){
		//Zero: null check
		if (strs == null || strs.size() == 0)
			return "";
		// First :endcode 256 character ASCII
		//        Rule: (string length)+("/")+(string itself)
		//              e.g., sunny => 5/sunny
		StringBuilder sb = new StringBuilder();
		for (String s: strs){
			sb.append(s.length()).append("/").append(s);
		}
		return sb.toString();
	}
	/*decode
	 * 		// Zero: null check
	 * 		// First: decode
		//        Rule: find the slash index and get the length from before and get substring from after
		//        indexOf(int ch, int fromIndex): starting the search at the specified index - fromIndex
		//        get the substring length - Integer.parseInt(s.substring(i, slashIdx))
		//        add the substring - s.substring(slashIdx + 1, slashIdx + size + 1)
		//        update the next starting point - slashIdx + size + 1
	 * */
	public List<String> decode(String s){
		List<String> res = new ArrayList<String>();
		// Zero: null check
		if (s == null || s.length() == 0)
			return res;
		// First: decode
		//        Rule: find the slash index and get the length from before and get substring from after
		//        indexOf(int ch, int fromIndex): starting the search at the specified index - fromIndex
		//        get the substring length - Integer.parseInt(s.substring(i, slashIdx))
		//        add the substring - s.substring(slashIdx + 1, slashIdx + size + 1)
		//        update the next starting point - slashIdx + size + 1
		int i = 0;
		while (i < s.length()){
			int slashIdx = s.indexOf('/', i);// index of / most close to front
			int size = Integer.parseInt(s.substring(i, slashIdx));
			res.add(s.substring(slashIdx + 1, slashIdx + size + 1));
			i = slashIdx + size + 1;
		}
		return res;
	}
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		list.add("weihung");
		list.add("liu");
		list.add("is");
		list.add("No.");
		list.add("1");
		list.add("awesome");
		list.add("man");
		list.add("/:");		
		EncodingDecoding sol = new EncodingDecoding();
		String res = sol.encode(list);
		System.out.println("Encode: "+res);
		System.out.println("Decode: "+sol.decode(res));
	}
}
