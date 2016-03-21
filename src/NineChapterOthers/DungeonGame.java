package NineChapterOthers;

import java.util.Arrays;
/*
 *     \  d  b  b  c  a
 *   \  T  F  F  F  F  F
 *   a 'T  F  F  F  F  F
 *   a 'T -T -T -T -T  F
 *   b  F 'T -T  F 'T  F
 *   c  F  F 'T -T -T -T
 *   c  F  F  F 'T  F 'T
 * 
 *     aadbbcbcac
 * 
 * 
 * 
 * */
public class DungeonGame {
	public int health(int[][] dungeon){
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length==0){
            return 0;
        }
        int m = dungeon.length;
        int n = dungeon[0].length;	
        //int[][] dp = new int[m][n];
        int[]dp = new int[n];
        // case0: starting point satidfy the first minimum requirement while in pricess cell
        //dp[m-1][n-1] = Math.max(0, 0-dungeon[m-1][n-1]);
        dp[n-1] = Math.max(0, 0-dungeon[m-1][n-1]);
        // case1: last row
        for (int j = n-2; j >=0;j--  ){
            //dp[m-1][j] = Math.max(  dp[m-1][j+1]- dungeon[m-1][j]   ,0);
        	dp[j] = Math.max(  dp[j+1]- dungeon[m-1][j]   ,0);
        }
        // case2: last column
        for (int i = m-2; i>=0;i--){
            //dp[i][n-1] = Math.max( dp[i+1][n-1] - dungeon[i][n-1], 0 );
        	dp[n-1] = Math.max( dp[n-1] - dungeon[i][n-1], 0 );
        }
        // case3: minimum health and must be positive
        for (int i = m-2; i>=0 ; i--){
            for (int j = n-2; j >=0 ; j--){
                //dp[i][j] = Math.max(    Math.min(dp[i+1][j], dp[i][j+1])-dungeon[i][j] ,0);
            	dp[j] = Math.max(    Math.min(dp[j], dp[j+1])-dungeon[i][j] ,0);
            }
        }

        // return dp[0][0]+1;
        return dp[0]+1;// not just even, larger than 1			
	}
	/*when positive, we cannot decrease health value and instead we use it to decrease the value we need with the negative value after them*/
	public int health2(int[][] dungeon){
		int n = dungeon[0].length;	
        int[] dp = new int[n];
        //dp[0] = (dungeon[0][0] < 0)? -dungeon[0][0]:0;
        dp[0] = Math.max(  0-dungeon[0][0] ,0);
        for (int j  = 1; j < dungeon[0].length;j++){
            //dp[j] = dp[j-1] + ( (dungeon[0][j] < 0)? -dungeon[0][j]:0 );
            dp[j] = Math.max(  dp[j-1]-dungeon[0][j] ,0);
        }
        for (int i = 1; i < dungeon.length;i++){
            for (int j  =0; j < dungeon[0].length; j++){
                if (j == 0)
                    //dp[0] += dungeon[i][0]; 
                	//dp[0]+=(dungeon[i][0] < 0)? -dungeon[i][0]:0;
                	dp[0]=Math.max(  dp[0]-dungeon[i][j] ,0);
                else 
                    //dp[j] = Math.min(dp[j], dp[j-1]) + ((dungeon[i][j] < 0)? -dungeon[i][j]:0);
                	//dp[j] =  ;
                	dp[j] = Math.max(    Math.min(dp[j], dp[j-1])-dungeon[i][j] ,0);
            }
        }
        return dp[n-1]+1;
	}
	public static void main(String[] args){
		DungeonGame sol= new DungeonGame();
		int[][] d = new int[][]{{1,0,0}};
		sol.printMatrix(d);
		System.out.println("health:"+ sol.health(d));
		
		System.out.println("health2:"+ sol.health2(d));
		d = new int[][]{{-2,-3,3}, {-5,-10,1}, {10,30,-5}};
		sol.printMatrix(d);
		System.out.println("health:"+ sol.health(d));
		
		System.out.println("health2:"+ sol.health2(d));
		
	}
	public void printMatrix(int[][] d){
		System.out.print("Matrix:"+Arrays.toString(d)+"\n");
		for (int i = 0; i < d.length; i++) {
		    for (int j = 0; j < d[0].length; j++) {
		        System.out.print(d[i][j] + " ");
		    }
		    System.out.print("\n");
		}			
	}
	
}
