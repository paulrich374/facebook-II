package NineChapterXMath;
import java.util.PriorityQueue;
/*
 * NOTE: whenever keep*i, use min <= Integer.MAX_VALUE/2 boundary check or long 

  Design an algorithm to find the kth number such that the only prime factors are 2,
3, and 5.

Approach#1 [Best for limited prime number ]
Time:O(K), Space:O(n) and merge n sorted list
           [0]  [1]
factor2(1) 1×2, 2×2, 3×2, 4×2, 5×2, …
factor3(2) 1×3, 2×3, 3×3, 4×3, 5×3, …
factor5(3) 1×5, 2×5, 3×5, 4×5, 5×5, …
1,2,3,4,5,6,..
 int min = Math.min(factor2, Math.min(factor3, factor5));
             if(min == factor2){ // update factor2
                factor2 = 2*ugly[++index2]; 
            } 
            Ex: min = 1*2, next factor2= 2*2 = 4
            
            
Approach#2 [Best for unlimited prime number ]
Time :O(K log K), Space:O()
minHeap:[1]
poll:1
minHeap:[2, 3, 5]
poll:2
minHeap:[3, 5, 4, 6, 10]
poll:3
minHeap:[4, 5, 10, 6, 9, 15]
poll:4
minHeap:[5, 6, 8, 15, 9, 10, 12, 20]
poll:5
minHeap:[6, 9, 8, 15, 20, 10, 12, 25]
poll:6
minHeap:[8, 9, 10, 15, 20, 25, 12, 18, 30]
poll:8
minHeap:[9, 15, 10, 16, 20, 25, 12, 30, 18, 24, 40]
poll:9
minHeap:[10, 15, 12, 16, 20, 25, 40, 30, 18, 24, 27, 45]
poll:10
minHeap:[12, 15, 25, 16, 20, 45, 40, 30, 18, 24, 27, 50]
 * */
public class UglyNumberII {
	/* Approach#1: [Best for limited prime number ]
	 * merge 3 sorted array(int) Time :O(K)
	 *  
	 *            1    2    3
		factor2  1×2, 2×2, 3×2, 4×2, 5×2, …
		factor3  1×3, 2×3, 3×3, 4×3, 5×3, …
		factor5  1×5, 2×5, 3×5, 4×5, 5×5, …
		if min == factor(i) used, evolve factor(i) to next
	 * */
	public int nthUglyNumberI(int n) { 
        int[] ugly = new int[n]; 
        //ugly[0] =1;
        int res =1;
        int index2 = 1, index3 = 1, index5 = 1; 
        int factor2 = 2, factor3 = 3, factor5 = 5; 
        for(int i = 1; i< n; i++){ 
            int min = Math.min(factor2, Math.min(factor3, factor5)); 
            //ugly[i] = min; 
            res = min;
            if(min == factor2){ // if current min == factor2, factor2 evolve to next
                factor2 = 2*(++index2); 
            } 
            if(min == factor3){ //factor3 evolve to next
                factor3 = 3*(++index3); 
            } 
            if(min == factor5){ //factor5 evolve to next
                factor5 = 5*(++index5); 
            } 
        } 
        //return ugly[n-1]; // since [0] is 1th, [n-1] is nth
        return res;
    } 	
	/* Approach#2:[Best for unlimited prime number ]
	 * heap Time :O(K log K)
	 * take min out, if min < BOUNDARY and min*primefacotr not in the heap, add it into heap
	 * FINAL nth: heap.poll(), since while n-1 add the last value into heap*/
	public int nthUglyNumberII(int n){ 
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); 
        minHeap.offer(1);
        //System.out.println("minHeap:"+minHeap);
        for(int i = 1; i < n; i++){ 
            int min = minHeap.poll(); // take min out 
            //System.out.println("poll:"+ min );
            if(min <= Integer.MAX_VALUE/2 && !minHeap.contains(min*2)){ 
                minHeap.offer(min*2); 
            } 
              
            if(min <= Integer.MAX_VALUE/3 && !minHeap.contains(min*3)){ 
                minHeap.offer(min*3); 
            } 
              
            if(min <= Integer.MAX_VALUE/5 && !minHeap.contains(min*5)){ 
                minHeap.offer(min*5); 
            }  
            //System.out.println("minHeap:"+minHeap);
        } 
        return minHeap.poll(); 
    }
	/* Approach#3:merge 3 sorted array(int) Time :O(K)
	 * everytime, take min among three factors
	 * use min and 3 factor to mock sorted array to iterate three factor next number
	 * if(min == factor2){ 
                factor2 = 2*ugly[++index2]; 
       }
       current factor2 used, so iterate to next factor2 value based on previous factors 
	 * FINAL nth: ugly[n-1] since [0] is 1th so [n-1] is nth*/
	public int nthUglyNumberIII(int n) { 
        int[] ugly = new int[n]; 
        ugly[0] =1; 
        int index2 = 0, index3=0, index5= 0; 
        int factor2 = 2, factor3 = 3, factor5 = 5; 
        for(int i = 1; i< n; i++){ 
            int min = Math.min(factor2, Math.min(factor3, factor5)); 
            ugly[i] = min; 
            if(min == factor2){ // if current min == factor2, factor2 evolve to next
                factor2 = 2*ugly[++index2]; 
            } 
            if(min == factor3){ //factor3 evolve to next
                factor3 = 3*ugly[++index3]; 
            } 
            if(min == factor5){ //factor5 evolve to next
                factor5 = 5*ugly[++index5]; 
            } 
        } 
        return ugly[n-1]; // since [0] is 1th, [n-1] is nth
    } 	
	public static void main (String[] args){
		UglyNumberII sol = new UglyNumberII();
		int n1= sol.nthUglyNumberI(10);
		System.out.println("nthUglyNumberI - "+n1);
		int n2 = sol.nthUglyNumberII(10);
		System.out.println("nthUglyNumberII - "+n2);
		int n3 = sol.nthUglyNumberIII(10);
		System.out.println("nthUglyNumberIII - "+n3);
	}
	
}
