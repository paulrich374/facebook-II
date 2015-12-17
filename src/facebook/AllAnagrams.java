package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * 
 * Find all the anagrams in an array of strings
 * Given an array of strings, group anagrams together.
 * Note:
 * 1. For the return value, each ""inner"" list's elements must follow the lexicographic order.
   2. All inputs will be in lower-case.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:
[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
 * https://leetcode.com/problems/anagrams/
 * */
/*	   方案1：
 *     
 * 
 *     方案2：
    	MapReduce
 */
public class AllAnagrams {
	/*Approach#1*/
	/*groupAnagram
	 * 		// Zero: null check
	 * 		// Second: hashMap statistics
			// Third: Lexical order in inner statement of  List<List<String>>, meaning List<String> need to sort
			//        sort List<String>
	 * */
	public List<List<String>> groupAnagram(String[] arr){
		// Zero: null check
		List<List<String>> res = new ArrayList<List<String>>();
		if (arr == null || arr.length == 0)
			return res;
		// Second: hashMap statistics
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (int i = 0 ; i < arr.length; i++){
			char[] chararr = arr[i].toCharArray();
			Arrays.sort(chararr);
			String str = new String(chararr);
			if (map.containsKey(str)){
				map.get(str).add(arr[i]);
			} else {
				List<String> item = new ArrayList<String>();
				item.add(arr[i]);
				map.put(str, item);
			}
		}
		// Third: Lexical order in inner statement of  List<List<String>>, meaning List<String> need to sort
		//        sort List<String>
		//for (int j = 0 ; j < res.size(); j++){
		for (List<String> value: map.values()){
			List<String> list = new ArrayList<String>(value);
			Collections.sort(list);
			// NOTE: if list size > 0
			if (list.size() > 0)	
				res.add(list);
		}
		return res;
	}
	/*Approach#2*/
	// input split(pipeline) map(content parse)    shuffle(merge,categorize)   reduce(only take list of values) finalize(merge, no pipeline)
	/* <0, lint>       <ilnt, lint>                <cdeo,{code}>                                ---> <code,{}>
	 * <1, intl>       <ilnt, intl>                <ilnt,{lint,intl,inlt}>                      ---> <lint,{intl,inlt}> 
	 * <2, inlt>       <ilnt, inlt>
	 * <3, code>       <cdeo, code>
	 * 
	 *            Map                                                          Reduce
	 * input            output               shuffle                           input                         output
	 * k = lineID       k = sort(word)        ===>                             k = sort(word)                k = anagram0
	 * v = word         v = word                                               v = {anagram0, anagram1,..}   v = {anagram1, anagram2,..}
	 */
	public static void main(String[] args){
		AllAnagrams sol = new AllAnagrams();
		String[] arr = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
		List<List<String>> res = sol.groupAnagram(arr);
		System.out.println(Arrays.asList(arr));
		System.out.println("Anagrams: "+res);
		String[] arr2 = new String[]{"lint", "intl", "inlt", "code"};
		List<List<String>> res2 = sol.groupAnagram(arr2);
		System.out.println(Arrays.asList(arr2));
		System.out.println("Anagrams: "+res2);
	}
}
