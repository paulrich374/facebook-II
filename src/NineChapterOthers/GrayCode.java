package NineChapterOthers;

import java.util.ArrayList;

public class GrayCode {
	public ArrayList<Integer> graycode(int n){
		// Zero: neg check and init first
		ArrayList<Integer> res= new ArrayList<Integer>();
		if (n < 0)
			return res;
		res.add(0);
		// First: check bit by bit
		// BT distinct element
		for (int i = 0 ; i < n; i++){
			int size = res.size();
			// BT previous set plus next distinct element
			for (int j = size-1; j >=0 ; j --){
				res.add(res.get(j)+(1<<i));
			}
			//for (int j = 0; j < size ; j++){
			//	res.add(res.get(j)+(1<<i));
			//}			
		}
		return res;
	}
	public static void main(String[] args){
		GrayCode sol = new GrayCode();
		int n = 2;
		System.out.println("n is : "+n+sol.graycode(n) );
		n = 3;
		System.out.println("n is : "+n+sol.graycode(n) );
	}
}
