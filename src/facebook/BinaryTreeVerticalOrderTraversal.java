package facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import java.util.TreeMap;

/*
 * 
 * 				(ArrayList<Integer>) e.getValue();
 *             // E need to cast
 * 
 * Print the sum of all the numbers at every vertical level in a binary tree
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
Idea is similar to the question above.
 * 
 * */
/*

T:O(w*n), where w is width and n is the number of nodes
w is O(n) when complete tree and thus Time complexity become O(n^2)


 *Approach#1 Recursive DFS

	 *printvertical T:O(), S:O(n) Treemap
	 * // Zero: null check
		// First: recursive left and right with Treemap to identify which vertical level
		//        TreeMap to sort the verticalIndex 
					//helper
	 				// Base case: null stop
					// Recursive case: left and right when return add current value and its index into map
		// Second: treemap get list and sort 
		// NOTE e.getValue() case
	 *


 *Approach#2 Iterative DFS+Stack
 

 
 
 
 * */
public class BinaryTreeVerticalOrderTraversal {
	/*printvertical T:O(), S:O(n) Treemap
	 * // Zero: null check
		// First: recursive left and right with Treemap to identify which vertical level
		//        TreeMap to sort the verticalIndex 
					//helper
	 				// Base case: null stop
					// Recursive case: left and right when return add current value and its index into map
		// Second: treemap get list and sort 
		// NOTE e.getValue() case
	 * */
	public ArrayList<ArrayList<Integer>> printvertical (TreeNode root){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		// Zero: null check
		if (root == null)
			return res;
		// First: recursive left and right with Treemap to identify which vertical level
		//        TreeMap to sort the verticalIndex
		
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		helper(root,0, map);
		// Second: treemap get list and sort
		// NOTE e.getValue() case
		for (Entry e: map.entrySet()){
			// NOTE: sort the list Vertical Order Traversal: [[4], [2], [5, 6, 1], [8, 3], [7], [9]]
			ArrayList<Integer> list = (ArrayList<Integer>) e.getValue();
			Collections.sort(list);
			res.add(list);
		}
		return res;
	}
	/*helper
	 * 	// Base case: null stop
		// Recursive case: left and right when return add current value and its index into map
	 * */
	private void helper(TreeNode root, int verticalIndex, TreeMap<Integer, ArrayList<Integer>> map){
		// Base case: null stop
		if (root == null){
			return;
		}
		// Recursive case: left and right when return add current value and its index into map
		helper(root.left, verticalIndex-1, map);
		helper(root.right, verticalIndex+1, map);
		ArrayList<Integer> list = null;
		if (map.get(verticalIndex) == null) {
			list = new ArrayList<Integer>();
		}else {
			list = map.get(verticalIndex);
		}
		list.add(root.val);
		map.put(verticalIndex, list);
	}
	public ArrayList<ArrayList<Integer>> printverticalIterative(TreeNode root){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		// Zero: null check
		if (root == null){
			return res;
		}
		// DFS+Stack
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		stack.push(root);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(root.val);
		map.put(0, list);
		HashMap<TreeNode, Integer> mapLevel = new HashMap<TreeNode, Integer>();
		mapLevel.put(root, 0);
		while (!stack.isEmpty()){
			TreeNode current = stack.pop();
			if (current.left != null){
				stack.push(current.left);
				ArrayList<Integer> listleft = null;
				if (map.get(mapLevel.get(current)-1) == null) {
					listleft = new ArrayList<Integer>();
				}else {
					listleft = map.get(mapLevel.get(current)-1);
				}
				listleft.add(current.left.val);				
				map.put(mapLevel.get(current)-1,listleft);
				mapLevel.put(current.left, mapLevel.get(current)-1);
			}
			if (current.right != null){
				stack.push(current.right);
				ArrayList<Integer> listright = null;
				if (map.get(mapLevel.get(current)+1) == null) {
					listright = new ArrayList<Integer>();
				}else {
					listright = map.get(mapLevel.get(current)+1);
				}		
				listright.add(current.right.val);			
				map.put(mapLevel.get(current)+1,listright);
				mapLevel.put(current.right, mapLevel.get(current)+1);
			}	
			
		}
		// Second: treemap get list and sort
		// NOTE e.getValue() case
		for (Entry e: map.entrySet()){
			// NOTE: sort the list Vertical Order Traversal: [[4], [2], [5, 6, 1], [8, 3], [7], [9]]
			ArrayList<Integer> list2 = (ArrayList<Integer>) e.getValue();
			Collections.sort(list2);
			res.add(list2);
		}
		return res;
	}
	public static void main(String[] args){
		BinaryTreeVerticalOrderTraversal sol = new BinaryTreeVerticalOrderTraversal();
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
		System.out.println("Vertical Order Traversal: "+sol.printvertical(root));
		
		System.out.println("Vertical Order Traversal Iterative: "+sol.printverticalIterative(root));

	}
}
