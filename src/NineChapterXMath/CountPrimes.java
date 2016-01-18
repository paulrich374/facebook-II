package NineChapterXMath;
/*
 *   isPrime()?
 *   prime[]
		for (int i = 2; i <= Math.sqrt(n); i++){
			if (primes[i]){
				for (int j = i+i; j< n; j=j+i){
					primes[j] = false;
				}
			}
		}
 * */
public class CountPrimes {
	// T:O(n log n), S:O(1)
	public int countPrimes(int n){
		int count = 0;
		for (int i = 1; i <= n;i++){
			if (isPrime(i)){
				count++;
				System.out.print(i+",");
			}
		}
		System.out.print("\n");
		return count;
	}
	// T:O(n), S:O(n)
	public int countPrimesLinear(int n){
		// Zero: less or equal to 2 check
		if (n <= 2)
			return 0;
		boolean[] primes = new boolean[n+1];
		
		//First: default all prime
		//for (int i = 0 ; i<=n; i++ ){
		for (int i = 2 ; i < n; i++ ){	
			primes[i] = true;
		}

		//Second: detect prime
		//for (int i = 1; i < n;i++){
		for (int i = 2; i <= Math.sqrt(n); i++){//O(log n)
			if (primes[i]){
				// NOTE: j = i+i
				for (int j = i+i; j< n; j=j+i){//O(logn)
					primes[j] = false;
				}
			}
		}
		
		//third: count
		int count = 0;
		//for (int i = 0 ; i<=n; i++ ){
		for (int i = 2 ; i < n; i++ ){
			if (primes[i])
				count++;
		}
		return count;
	}	
	public boolean isPrime(int n){
		if (n <=1)
			return false;
		for (int i = 2; i <= Math.sqrt(n); i++){
			if (n%i == 0)
				return false;
		}
		return true;
	}
	public static void main(String[] args){
		CountPrimes sol = new CountPrimes();
		int n =97;
		System.out.println("n = "+n+". isPrime: "+sol.isPrime(n));
		n =1;
		System.out.println("n = "+n+". isPrime: "+sol.isPrime(n));
		n =25;
		System.out.println("n = "+n+". isPrime: "+sol.isPrime(n));	
		n =25;
		System.out.println("\nn = "+n+". \ncountPrimes:");
		System.out.println(sol.countPrimes(n));
		System.out.println(sol.countPrimesLinear(n));
		n =100;
		System.out.println("\nn = "+n+". \ncountPrimes:");
		System.out.println(sol.countPrimes(n));	
		System.out.println(sol.countPrimesLinear(n));
	
	}
}
