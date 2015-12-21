package facebook;
/*
 * 				 O(m*n)  ===>   O(n)
 * 

   OneEditDistance
 * Given two strings S and T, determine if they are both one edit distance apart.

OneEditDistance 时间复杂度是O(n)[Edit distance O(m*n)]
OneEditDistance 空间复杂度是O(1)[Edit distance O(min(m,n))]

  * http://buttercola.blogspot.com/2015/08/leetcode-one-edit-distance.html

 * 
 * */
public class OneEditDistance {
	public boolean oneEditDis(String s1, String s2){
		// Zero: two null, zero edit distance
		if ((s1 == null || s1.length() == 0) && (s2 == null || s2.length() == 0)){
			return true;
		}
		// First: use len to compute edit distance
		int len1 = s1.length();
		int len2 = s2.length();
		int i = 0;
		int j = 0;
		int count = 0;
		while (i < len1 && j < len2){
			if (s1.charAt(i) == s2.charAt(j)){
				i++;
				j++;
				// same edit distance, just iterating to next
			} else {
				count++;
				if (count > 1) 
					return false;
				if (len1 > len2){
					i++;
				}else if (len1 < len2){
					j++;
				} else {
					i++;
					j++;
				}
			}
		}
		return count < 2;
	}
	public static void main(String[] args){
		OneEditDistance sol = new OneEditDistance();
		String s1 = "abcd";
		String s2 = "acedo";
		System.out.println(s1+", "+s2);
		System.out.println(sol.oneEditDis(s1,s2));

		String s3 = "abcd";
		String s4 = "abce";
		System.out.println(s3+", "+s4);
		System.out.println(sol.oneEditDis(s3,s4));		
	}

}
