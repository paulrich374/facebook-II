package facebook;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * Facebook: Print n-th node in a binary tree
Write a function that takes 2 arguments: 
a binary tree and an integers, 
it should return the n-th element in the inorder traversal of the binary tree. 
 http://buttercola.blogspot.com/2014/10/facebook-print-n-th-node-in-binary-tree.html
 * 
 *      1
 *     /  \
 *    2    3
 *  /  \    \ 
 * 4    5    6
 * */

public class NthNode {
	/*
	 * NthNode
	 * 		// Zero: corner case, root is null 
			// First: set up a counter and a value return, if NOT FOUND, set Integer.MIN_VALUE

	 * */	
	public int nthElement(TreeNode root, int n){
		// Zero: corner case, root is null 
		if (root == null)
			return Integer.MIN_VALUE;
		// First: set up a counter and a value return, if NOT FOUND, set Integer.MIN_VALUE
		int[] count = new int[2];
		count[1] = Integer.MIN_VALUE;// not found
		helper(root, n, count);
		return count[1];
	}
	/*
	 * helper
		// Zero: inorder boilerplate, null return
		// First: inorder boilerplate, left, root.val, right. count++ if count == n, record vlaue and return

	 * */	
	private void helper(TreeNode root, int n, int[] count){
		// Zero: inorder boilerplate, null return
		if (root == null)
			return;
		// First: inorder boilerplate, left, root.val, right. count++ if count == n, record vlaue and return
		helper(root.left, n, count);
		//if (count[0] <= n)
		// 
			count[0]++;
		if (count[0] == n){
			count[1] = root.val;
			return;
		}	
		helper(root.right, n, count);
	}
	public List<Integer> inorder(TreeNode root){
		List<Integer>res = new ArrayList<Integer>();
		helperinorder(root,res);
		return res;
	}
	private void helperinorder(TreeNode root, List<Integer> res){
		if (root == null)
			return;
		helperinorder(root.left, res);
		res.add(root.val);
		helperinorder(root.right, res);
	}
	public static void main(String[] args) {
		NthNode sol = new NthNode();
	      TreeNode root = new TreeNode(1);
	      root.left = new TreeNode(2);
	      root.right = new TreeNode(3);
	      root.left.left = new TreeNode(4);
	      root.left.right = new TreeNode(5);
	      root.right.right = new TreeNode(6);
	      List<Integer> res = sol.inorder(root);
	      System.out.println(res);
	      System.out.println("6th Result is " + sol.nthElement(root, 6));
	      System.out.println("3th Result is " + sol.nthElement(root, 3));	      
	      System.out.println("1th Result is " + sol.nthElement(root, 1));	      
	      System.out.println("-4th Result is " + sol.nthElement(root, -4));	      
	      System.out.println("10th Result is " + sol.nthElement(root, 10));	      
	       
	    }
}
