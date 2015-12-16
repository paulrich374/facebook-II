package facebook;
/*
 * Facebook: Mutate input array?
 * Given an input array and another array that describe a new index for each element, 
 * mutate the input array so that each element ends up in their new index.
 *  Discuss the runtime of the algorithm and how you can be sure there won't be any infinite loops.

 * Understand the problem:
Input   char[] {a, b, c, d}
        int [] {3, 1, 0, 2}
Suppose the input is valid, i.e, index array does not contain out of boundary elements, or "collision" elements. 
Output: char[] {c, b, d, a}
 * */
public class MutateInputArray {
	/*mutate
	  	// Zero:corner case, null check
		// First: create a keep trying iterating index and (NO NontargetINDEX or swap needed)  swap with specific position if not "in the target/target"
	 */		
	public void  mutate(char[] letter, int[] pos){
		// Zero:corner case,  null check
		if (letter == null || letter.length == 0){
			return;
		}
		// First: create a keep trying iterating index and (NO NontargetINDEX or swap needed) swap with specific position if not "in the target/target"
		int i = 0;

		while (i < pos.length){
			if (pos[i] != i){
				int temp = pos[i];
				pos[i] = pos[temp];
				pos[temp] = temp;//NOTE: INFINITE LOOP IF pos[pos[i]], pos[i] has been changed
				char tempc = letter[i];
				letter[i] = letter[temp];
				letter[temp] = tempc;
			} else {
				i++;
			}
			//System.out.println(i+", ");
			//for (char a:letter)
				//System.out.print(a+", ");			
		}		
	}
	public static void main(String[ ] args){
		MutateInputArray sol = new MutateInputArray();
		char[] letter = new char[]{'a','b','c','d'};
		int[] pos = new int[]{3,1,0,2};
		System.out.println("Before Mutate");
		for (char a:letter)
			System.out.print(a+", ");
		System.out.println();
		for (int a:pos)
			System.out.print(a+", ");			
		sol.mutate(letter, pos);
		System.out.println();
		System.out.println("After Mutate");
		for (char a:letter)
			System.out.print(a+", ");
		System.out.println();
		for (int a:pos)
			System.out.print(a+", ");		
	}
}
