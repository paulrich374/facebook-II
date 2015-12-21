package facebook;

/*
 * 
 *       Like Unique Path - Cache

 
 DP - constant variable
 * 
 * 
 *    if the current state only replies on a fixed set of previous states, we may not need to create a DP array, 
 *    just use several variables to save the states is fine
 *    
 *    Like decode ways, fibonacci, clime a flight, Interleaving String
 *    
 * Write a function to compute the number of ways to climb a flight of n steps.
 * Taking 1, 2, or 3 steps at a time. Do it in linear time and constant spaces. 
 *     For example, n = 3.
1   1   1
1   2
2   1
3
http://buttercola.blogspot.com/2014/11/facebook-climb-flight.html
https://leetcode.com/problems/climbing-stairs/
* 
 * */
/* Approach#1 Recursive                  T:O(n), Space:O(n)*/
/* Approach#2 Constant previous variable T:O(n), Space:O(1)*/
/* Approach#3 Cache                      T:O(n), Space:O(n)*/
/*climewaysRecursive
 * 		// Base Case
	// Recursive Case
 * */
/*climewaysConstant
 * 		// Zero: 0 case
	if (n < 0)
		return 0;	
	// Zero: base case
	if (n == 1){
		return 1;
	}
	if (n == 2){
		return 2;
	}
 * 		// variable
	// iterating

 * */
/*climewaysCache
// Zero: 0 case. Cover Zero since otherwise [0] is 1
if (n <= 0)
	return 0;
* 		// cache
// iterating
* */
public class ClimeaFlight {
	/*climewaysRecursive
	 * 		// Base Case
		// Recursive Case
	 * */
	public int climewaysRecursive(int n){
		// Base Case
		if (n < 0)
			return 0;
		if (n == 0)
			return 1;
		// Recursive Case
		return climewaysRecursive(n-1) + climewaysRecursive(n-2) +climewaysRecursive(n-3);
	}
	/*climewaysConstant
	 * 		// Zero: 0 case
		if (n < 0)
			return 0;	
		// Zero: base case
		if (n == 1){
			return 1;
		}
		if (n == 2){
			return 2;
		}
	 * 		// variable
		// iterating

	 * */
	public int climewaysConstant(int n){
		// Zero: 0 case
		if (n < 0)
			return 0;	
		// Zero: base case
		if (n == 1){
			return 1;
		}
		if (n == 2){
			return 2;
		}		
		// variable
		int f_0 = 1;
		int f_1 = 1;
		int f_2 = 2;
		// iterating
		int f_3 = 0;
		for (int i = 3; i <= n; i++){
			f_3 = f_2 + f_1 + f_0;
			f_2 = f_3;
			f_1 = f_2;
			f_0 = f_1;
		}
		return f_3;		
	}
	/*climewaysCache
		// Zero: 0 case. Cover Zero since otherwise [0] is 1
		if (n <= 0)
			return 0;
	 * 		// cache
		// iterating
	 * */
	public int climewaysCache(int n){
		// Zero: 0 case. Cover Zero since otherwise [0] is 1
		if (n <= 0)
			return 0;			
		// cache
		int[] cache = new int[n+1];
		cache[0] = 1;
		cache[1] = 1;
		cache[2] = 2;
		// iterating
		for (int i = 3; i <= n; i++){
			cache[i] = cache[i-1] + cache[i-2] + cache[i-3];
		}
		return cache[n];
	}
	
	public static void main(String[] args){
		int n = 3;
		ClimeaFlight sol = new ClimeaFlight();
		Long s = System.nanoTime();
		System.out.println(sol.climewaysRecursive(n)+" Ways");
		Long e = System.nanoTime() - s;
		System.out.println("Time: " + e);
		
		
		Long s2 = System.nanoTime();
		System.out.println(sol.climewaysConstant(n)+" Ways");
		Long e2 = System.nanoTime() - s2;
		System.out.println("Time: " + e2);
		
		
		Long s3 = System.nanoTime();
		System.out.println(sol.climewaysCache(n)+" Ways");
		Long e3 = System.nanoTime() - s3;
		System.out.println("Time: " + e3);		
	}
}
