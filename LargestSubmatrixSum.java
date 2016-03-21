import java.lang.*;
public class LargestSubmatrixSum {
//    Given an NxN matrix of positive and negative integers, write code to find the
//    submatrix with the largest possible sum.
    
    //brute force: O(n^6) time.
    //returns [r1, c1, r2, c2, sum] where (r1,c1),(r2,c2) represents 
    //the diagonal of submatrix
    static int[] findLargestSubmatrix(int[][] matrix) {
        int maxSum = Integer.MIN_VALUE;
        int[] ret = {-1, -1, -1, -1, 0};
        for (int r1 = 0; r1 < matrix.length; ++r1) {
            for (int r2 = r1; r2 < matrix.length; ++r2) {
                for (int c1 = 0; c1 < matrix[0].length; ++c1) {
                    for (int c2 = c1; c2 < matrix[0].length; ++c2) {
                        int sum = getSum(matrix, r1, c1, r2, c2);
                        if (sum > maxSum) {
                            maxSum = sum;
                            ret = new int[] {r1, c1, r2, c2, sum};
                        }
                    }
                }
            }
        }
        return ret;
    }
    
    static int getSum(int[][] matrix, int r1, int c1, int r2, int c2) {
        int sum = 0;
        for (int r = r1; r <= r2; ++r) {
            for (int c = c1; c <= c2; ++c) {
                sum += matrix[r][c];
            }
        }
        return sum;
    }
    
    //preprocess matrix: O(n^4) time, reducing getSum to constant time.
    static int[][] processMatrix(int[][] m) {
        if (m == null) return null;
        int[][] sumMatrix = new int[m.length][m[0].length];
        sumMatrix[0][0] = m[0][0];
        for (int j = 1; j < m[0].length;j++){
            sumMatrix[0][j]=sumMatrix[0][j-1]+m[0][j];
            //System.out.println(0+","+j+" - "+sumMatrix[0][j]+",");
        }
        for (int i = 1; i < m.length;i++){
            sumMatrix[i][0]=sumMatrix[i-1][0]+m[i][0]; 
            //System.out.println(i+","+0+" - "+sumMatrix[i][0]+",");
        } 
        for (int i =1; i< m.length;i++) {
            for (int j=1; j < m[0].length;j++){
                sumMatrix[i][j] = sumMatrix[i-1][j]+sumMatrix[i][j-1]-sumMatrix[i-1][j-1]+m[i][j];
                //System.out.println(i+","+j+" - "+sumMatrix[i][j]+",");
            }
            
        }
        return sumMatrix;
        /*
        for (int i = 0; i < m.length; ++ i) {
            int sumRowOne = 0; int sumColOne = 0;
            for (int j = 0; j < m[0].length; ++j) {
                if (i == 0) {
                    sumRowOne += m[i][j];
                    sumMatrix[i][j] = sumRowOne;
                }
                if (j == 0) {
                    sumColOne += m[i][j];
                    sumMatrix[i][j] = sumColOne;
                }
                if (i != 0 && j != 0) {
                    sumMatrix[i][j] = m[i][j] + 
                            sumMatrix[i][j-1] + 
                            sumMatrix[i-1][j] - 
                            sumMatrix[i-1][j-1];
                }
                System.out.println(i+","+j+" - "+sumMatrix[i][j]+",");
            }
        }
        return sumMatrix;
        */
    }
    
    static int getSum2(int[][] sumMatrix, int r1, int c1, int r2, int c2) {
  
       if (r1 == 0 && c1 == 0){
           return sumMatrix[r2][c2];
       }
       else if (r1 == 0){
           return sumMatrix[r2][c2]-sumMatrix[r2][c2-1];
       } 
       else if (c1 == 0 ){
           return sumMatrix[r2][c2]-sumMatrix[r1-1][c2];
       } else {
           return sumMatrix[r2][c2]-sumMatrix[r2][c1-1]-sumMatrix[r1-1][c2]+sumMatrix[r1-1][c1-1];
       }
      
       // return sumMatrix[r2][c2] - 
    //            sumMatrix[r1][c2] - 
    //            sumMatrix[r2][c1] + 
    //            sumMatrix[r1][c1];
    }

static int[] findLargestSubmatrix2(int[][] matrix) {
        int maxSum = Integer.MIN_VALUE;
        int[] ret = {-1, -1, -1, -1, 0};
        int[][] subMatrix = processMatrix(matrix);
        for (int r1 = 0; r1 < matrix.length; ++r1) {
            for (int r2 = r1; r2 < matrix.length; ++r2) {
                for (int c1 = 0; c1 < matrix[0].length; ++c1) {
                    for (int c2 = c1; c2 < matrix[0].length; ++c2) {
                        int sum = getSum2(subMatrix, r1, c1, r2, c2);
                        //System.out.println(r1+","+c1+";"+r2+","+c2+" - "+sum+",");
                        if (sum > maxSum) {
                            maxSum = sum;
                            ret = new int[] {r1, c1, r2, c2, sum};
                        }
                    }
                }
            }
        }
        return ret;
    }    
    
/**
   * 
   * @param matrix
   * @return return the max.
   */
  public static int maxSubMatrix(int[][] matrix) {
    // write implementation here
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    for (int start = 0; start < matrix.length; ++start) {
      int[] arr = new int[matrix[0].length];
      for (int end = start; end < matrix.length; ++end) {
        for (int i = 0; i < matrix[0].length; ++i) {
          arr[i] += matrix[end][i];
        }
        max = Math.max(max, maxArea(arr));
      }
    }
    return max;
  }

  // use max subarray to find max
  private static int maxArea(int[] arr) {
    // compress
    int max = Integer.MIN_VALUE;
    int maxSoFar = 0;
    for (int i = 0; i < arr.length; ++i) {
      maxSoFar = Math.max(0, maxSoFar + arr[i]);
      max = Math.max(max, maxSoFar);
    }
    return max;
  }    
    
    
    //--------------------------------------
    public static void main(String[]args) {
        int[][]m = {
                {1,-2,3,1},
                {1,5,-4,1},
                {1,1,0,2},
                {-1,1,1,-8}};
        long start =  System.nanoTime();     
        int[] r = findLargestSubmatrix(m);//O(n^6) time
        long end = System.nanoTime();  
         System.out.println("Time:"+(long)(end-start)+"("+r[0]+", "+r[1]+") ("+r[2]+", "+r[3]+") sum: "+r[4]);
        long start2 = System.nanoTime();
        int[] r2 = findLargestSubmatrix2(m);//O(n^4) time
        long end2 = System.nanoTime();         
         System.out.println("Time:"+(long)(end2-start2)+"("+r2[0]+", "+r2[1]+") ("+r2[2]+", "+r2[3]+") sum: "+r2[4]);
        long start3 = System.nanoTime();
        int r3 = maxSubMatrix(m);//O(n^4) time
        long end3 = System.nanoTime(); 
        System.out.println("Time:"+(long)(end3-start3)+", sum:"+r3);
    }

}