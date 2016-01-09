package facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Map.Entry;

/*
 * 
 *     Given a binary tree, print it vertically. The following example illustrates vertical order traversal.
           1
        /    \
       2      3
      / \    / \
     4   5  6   7
             \   \
              8   9 
               
     
The output of print this tree vertically will be:
4
2
1 5 6
3 8
7
9 

T:O(w*n), where w is width and n is the number of nodes
w is O(n) when complete tree and thus Time complexity become O(n^2)

http://b uttercola.blogspot.com/2014/12/facebook-print-binary-tree-in-vertical.html
 * 
 * 
 * 
 * 
 * */
/*Approach#1 Recursive
 * 
 *Approach#2 Iterative
 * 
 * 
 * */
public class BinaryTreeVerticalOrderSum {
	/*printverticalSum T:O(), S:O(n) Treemap
	 * // Zero: null check
		// First: recursive left and right with Treemap to identify which vertical level
		//        TreeMap to sort the verticalIndex 
					//helper
	 				// Base case: null stop
					// Recursive case: left and right when return add current value and its index into map
		// Second: treemap get list and sort 
		// NOTE e.getValue() case
	 * */
	public ArrayList<Integer> printverticalSum (TreeNode root){
		ArrayList<Integer> res = new ArrayList<Integer>();
		// Zero: null check
		if (root == null)
			return res;
		// First: recursive left and right with Treemap to identify which vertical level
		//        TreeMap to sort the verticalIndex
		
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		helper(root,0, map);
		// Second: treemap get list and sort
		// NOTE e.getValue() case
		for (Entry e: map.entrySet()){
			// NOTE: sort the list Vertical Order Traversal: [[4], [2], [5, 6, 1], [8, 3], [7], [9]]
			int sum = (int) e.getValue();
			res.add(sum);
		}
		return res;
	}
	/*helper
	 * 	// Base case: null stop
		// Recursive case: left and right when return add current value and its index into map
	 * */
	private void helper(TreeNode root, int verticalIndex, TreeMap<Integer, Integer> map){
		// Base case: null stop
		if (root == null){
			return;
		}
		// Recursive case: left and right when return add current value and its index into map
		helper(root.left, verticalIndex-1, map);
		helper(root.right, verticalIndex+1, map);
		//ArrayList<Integer> list = null;
		if (map.get(verticalIndex) == null) {
			map.put(verticalIndex, 0);
		//	list = new ArrayList<Integer>();
		//}else {
		//	list = map.get(verticalIndex);
		}
		//list.add(root.val);
		map.put(verticalIndex, map.get(verticalIndex)+root.val);
	}
	public static void main(String[] args){
		BinaryTreeVerticalOrderSum sol = new BinaryTreeVerticalOrderSum();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		root.right.left.right = new TreeNode(8);
		root.right.right.right = new TreeNode(9);
		System.out.println("Tree: "+root.toStringExpression());
		// Vertical Order Traversal: [[4], [2], [1, 5, 6], [3, 8], [7], [9]]
		System.out.println("Vertical Order Traversal Sum: "+sol.printverticalSum(root));
		

	}	
}
