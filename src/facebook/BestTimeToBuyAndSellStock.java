package facebook;

import java.util.Arrays;

/* *      in-order,  before/ after
 *        sum
 *        local and global
 *       1D- >constant variable
 *       PATH SUM , PATH(accumulate) OR SEPATATE(alone)
 *       maximum profit for k most transactions, 2D->1D
 *       Like Edit Distance, Unique Path 2D->1D save space

 *       local, global
 *       
 * Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction 
(ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.
int price[] = {100, 180, 260, 310, 40, 535, 695};
Buy on day : 0   Sell on day: 3
Buy on day : 4   Sell on day: 6
 [2, 3, 10, 6, 4, 8, 1] then returned value should be 8 (Diff between 10 and 2)
 [ 7, 9, 5, 6, 3, 2 ] then returned value should be 2 (Diff between 7 and 9)
http://buttercola.blogspot.com/2014/11/facebook-best-time-to-buy-and-sell-stock.html
http://www.geeksforgeeks.org/maximum-difference-between-two-elements/
http://www.geeksforgeeks.org/stock-buy-sell/
 * 
 * */
public class BestTimeToBuyAndSellStock {
	
	
	/* I.   If you were only permitted to complete at most one transaction 
(ie, buy one and sell one share of the stock), 
	 *maxProfit T:O(n), S:O(1) 
	 */
	public int maxProfit(int[] prices){
		// Zero: null check
		if (prices == null || prices.length == 0)
			return 0;
		// First: check local and update global
		//        local :current diff plus with previous local OR current diff
		//               since buy at 0 and sell at 3 can be calculated by adding 1-0 + 2-1 + 3-2  = 3 - 0
		//        global: local vs global
		int local = 0;
		int global = 0;
		for (int i =1; i < prices.length;i++){
			local = Math.max(local + prices[i] - prices[i-1], prices[i] - prices[i-1]);
			global = Math.max(local, global);
		}
		return global;
	}
	
	
	/* II.  You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
	 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	 * 
	 * Greedy, take all postive profit
	 * maxProfitII T:O(n), S:O(1) 
	 * */
	public int maxProfitII(int[] prices){
		// Zero: null check
		if (prices == null || prices.length == 0)
			return 0;
		// First: check local and update global
		//int local = 0;
		int global = 0;
		for (int i =1; i < prices.length;i++){
			//local = Math.max(local + prices[i] - prices[i-1], prices[i] - prices[i-1]);
			//global = Math.max(local, global);
			global += (prices[i] - prices[i-1]) >0?prices[i] - prices[i-1]:0;
		}
		return global;
	}	
	
	
	/* III.  You may complete at most two transactions.
	 * maxProfitIII T:O(n*k), S:O(k), k = 2 at most two transactions
	 *              T:O(n), S:O(1)
	 *    global[i][j] = max(local[i][j], global[i-1][j])
	 *    local[i][j] = max(local[i-1][j] + diff, global[i-1][j-1] + max(0, diff))=(accumulate, new transaction)
	 *    where i means ith day and j means jth transaction           
	 * */
	public int maxProfitIII(int[] prices){
		// Zero: null check and less than 2 check
		if (prices == null ||prices.length < 2)
			return 0;
		// First: take the top2 transaction
		//        new int[3]?
		//        for loop j start from 
		// 这是动态规划的实现细节哈～ 因为这里要省空间，所以使用了一维数组来存结果，
		// 也就是说数据会被一行行的覆盖，那么这里就有个问题，如果要用上一行前一个元素旧的数据，
		// 那么就得for循环倒着来走，如果正向走，那么走到j时，j+1已经被覆盖成新的数据，也就不对了～
		// [i] = [i-1] 用上一行 when backward j = 2~1 so if forward j = 1~2 , [i] = [i], it is wrong
		int[] local = new int[3];
		int[] global = new int[3];
		for (int i = 0; i < prices.length -1; i++) {
			int diff = prices[i+1] - prices[i];
			for (int j = 2; j >= 1; j--){
				local[j] = Math.max(local[j] + diff, global[j-1] + Math.max(0,diff));//alone, new
				//global[j] = Math.max(local[j], global[j-1]);
				global[j] = Math.max(local[j], global[j]);
			}
		}
		return global[2];
	}
	
	
	
	public static void main(String[] args){
		BestTimeToBuyAndSellStock sol = new BestTimeToBuyAndSellStock();
		int[] prices = new int[]{2, 3, 10, 6, 4, 8, 1};
		System.out.println(Arrays.toString(prices));
		System.out.println(sol.maxProfit(prices));
		int[] prices2= new int[]{100, 180, 260, 310, 40, 535, 695};
		System.out.println(Arrays.toString(prices2));
		System.out.println(sol.maxProfit(prices2));		
		
		System.out.println(Arrays.toString(prices));
		System.out.println(sol.maxProfitII(prices));		

		System.out.println(Arrays.toString(prices));
		System.out.println("at most two transactions: " + sol.maxProfitIII(prices));	
		System.out.println(Arrays.toString(prices2));
		System.out.println("at most two transactions: " + sol.maxProfitIII(prices2));			
	}
}
