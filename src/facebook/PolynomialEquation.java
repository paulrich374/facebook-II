package facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Facebook: Write all solutions for a^3 + b^3 = c^3 + d^3, 
 * where a, b, c, d lie between [0, 10^5]
 * http://buttercola.blogspot.com/2014/11/facebook-write-all-solutions-for-a3-b3.html
 * */

/*Approach#1: T:O(n^4), S:O(1)*/
/*a= 0~n, b=a+1~n, c= b+1~n, d= c+1~n*/
/*Approach#2: T:O(n^2), S:O(n)*/
/*If the second time that the a^3 + b^3 exists in the hash table*/

public class PolynomialEquation {
	/*Approach#1: T:O(n^4), S:O(1)*/
	/*a= 0~n, b=a+1~n, c= b+1~n, d= c+1~n*/
	/*Approach#2: T:O(n^2), S:O(n)*/
	/*If the second time that the a^3 + b^3 exists in the hash table*/
	public List<List<Integer>> findSolution(int n){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (n <= 0){
			return res;
		}
		/*NOTE: whenever it comes to pow(x,n), use long to avoid overflow */
		HashMap<Long, List<Integer>> map = new HashMap<Long, List<Integer>>();
		//for (int a = 1; a < n; a++){
		//	for (int b = a + 1; b < n; b++){
		for (int a = 0; a < n; a++){
			for (int b = a + 1; b < n; b++){				
				long sum = a * a * a + b * b * b;
				if (map.containsKey(sum)){
					List<Integer> temp = map.get(sum);
					temp.add(a);
					temp.add(b);
					res.add(temp);
				}else{
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(a);
					temp.add(b);
					map.put(sum, temp);
				}
			}
		}
		return res;
	}
	public static void main(String[] args){
		PolynomialEquation sol = new PolynomialEquation();
		List<List<Integer>> res = sol.findSolution(100);
		System.out.println(res);
	}
}
