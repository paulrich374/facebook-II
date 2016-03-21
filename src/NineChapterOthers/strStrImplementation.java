package NineChapterOthers;



import java.util.Arrays;
/*
 *      value means the length of the string match from end and from start
 *      shift value = (current index - current value)+1 = index - length +1 (index incude)
 *      e.g., i s s i p
 *            0 0 0 1 0
 *        idx 0 1 2 3 4
 *        gap = 3 - 1 +1 =3
 *        i= 0 + 3 = 3 
 * 
 * 
 * */


/* strStr
 * 		// Zero: Edge case
 * 		// First: get prefix usffix length table
 * 		// Second: matching
	//        for each index reset success 
	//        not matching =>increment
	//        not matching => shift 	
	//        check success for each index
	//        NOTE: gap =(j -1)- table[j-1] +1
 * */
/* getTable
// Zero: Init
// First: Fill value
// find comparison element 
// prev matched len next character	
// search more prev to find  matched len next character
// judge
// match, depends on previous match +1	
// not match, len 0	
*/

public class strStrImplementation {
	/*strStr
	 * 		// Zero: Edge case
	 * 		// First: get prefix usffix length table
	 * 		// Second: matching
		//        for each index reset success 
		//        not matching =>increment
		//        not matching => shift 	
		//        check success for each index
		//        NOTE: gap =(j -1)- table[j-1] +1
	 * */
	public int strStr(String s1, String s2){
		// Zero: Edge case
		if (s2.length() > s1.length())
			return -1;
		if (s2.length()==0)// empty, all matching
			return 0;
		// First: get prefix usffix length table
		int[] table = getTable(s2);
		//boolean success = true;
		// Second: matching
		//        for each index reset success 
		//        not matching =>increment
		//        not matching => shift 	
		//        check success for each index
		int i = 0;
		//while (i < s1.length()){
		while (i <= (s1.length()-s2.length()) ){
			// for each index reset success 
			boolean success = true;
			for (int j = 0 ; j < s2.length(); j++){
				//not matching =>   increment
				if (s1.charAt(i) != s2.charAt(0)){
					success = false;
					i++;
					break;
				// not matching => shift 	
				}else if (s1.charAt(i+j) != s2.charAt(j)){ 
					success = false;
					i += (j -1)- table[j-1] +1;// 1 means include itself
					break;
				}
			}
			// check success for each index
			if (success == true){
				return i;
			}
		}
		return -1;
	}
	// Longest prefix and suffix substring length
	// i=3, consider 0~3 subtring's prefix and suffix
	// prefix table meaning array value are the longest length of prefix and suffix
	// value means the length of the string match from end and from start
	// e.g., i s s i p 
	//       0 0 0 1 0
	
	/* getTable
	// Zero: Init
	// First: Fill value
	// find comparison element 
	// prev matched len next character	
	// search more prev to find  matched len next character
	// judge
	// match, depends on previous match +1	
	// not match, len 0	
	*/	
	public int[] getTable(String s){
		// Zero: Init
		int[] table = new int[s.length()];
		table[0] = 0;
		// First: Fill value
		for (int i =1 ; i < s.length();i++){
			// find comparison element 
			// prev matched len next character
			int index = 0 + table[i-1];
			// search more prev to find  matched len next character
			while(index > 0 && s.charAt(index) != s.charAt(i)){
				index = table[index-1];
			}
			// judge
			// match, depends on previous match +1
			if (s.charAt(i) == s.charAt(index)){
				table[i] = table[i-1]+1;// 
			// not match, len 0
			}else {
				table[i] = 0;
			}
		}
		return table;
	}
	public int strStrRollisghash(String haystack, String needle){
	    // Corner case
		if(haystack==null || needle == null || needle.length()==0)  
	        return 0;  
	    if(needle.length()>haystack.length())  
	        return -1;  
	    // Rolling hash
	    int base = 29;  
	    long patternHash = 0;  
	    for (int i = 0 ;i < needle.length();i++){ //[0]*base^n-1+[n]*base^0
	        patternHash = patternHash*29 +(int)needle.charAt(i);
	    }
	    //long maxBase = 1; 
	    //for(int i=needle.length()-1; i>=0; i--){  
	   // 	patternHash += (int)needle.charAt(i)*maxBase;  
	   // 	System.out.println((int)needle.charAt(i)*maxBase+",,");
	    // 	maxBase *= base;  
	    //} 	
	    
	    long hayHash = 0;
	    
	    for (int i = 0; i < needle.length();i++){
	        hayHash = hayHash*base +(int)haystack.charAt(i);
	    }  
	  //maxBase = 1;
	    //for(int i=needle.length()-1; i>=0; i--){  
	    //	hayHash += (int)haystack.charAt(i)*maxBase;  
	    //	maxBase *= base;  
	   // }	    
	    //maxBase /= base;
	    
	    if(hayHash == patternHash){  
	     return 0;
	    }  	 
	     for (int i = needle.length(); i < haystack.length(); i++){
	         // Line 141: error: incompatible types: possible lossy conversion from double to long
	         //hayHash = (hayHash - (int)haystack.charAt(i-needle.length())*maxBase  )*base  + (int)haystack.charAt(i); 
	         hayHash = (long) ((hayHash - (int)haystack.charAt(i-needle.length())*Math.pow(base,needle.length()-1)  )*base  + (int)haystack.charAt(i)); 

	    	 if (hayHash == patternHash){
	            return i - needle.length()+1;
	         }  
	         System.out.println("(needlehash,hayhash)= "+"("+patternHash+","+hayHash+")");
	     }	    
	    return -1;
	}
	public static void main(String[] args){
		String s2 ="issip";

		strStrImplementation sol = new strStrImplementation();
		System.out.println(s2);
		System.out.println(Arrays.toString( sol.getTable(s2) ));
		String s1 = "mississippi";
		System.out.println(s1+" and "+s2+". the matching index is :");
		System.out.println(( sol.strStr(s1,s2) ));
		System.out.println(( sol.strStrRollisghash(s1,s2) ));		
	}
}
