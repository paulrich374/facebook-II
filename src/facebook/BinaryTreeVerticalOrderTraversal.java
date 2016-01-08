package facebook;

import java.util.ArrayList;
import java.util.HashMap;

/*
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
/*T:O(w*n), where w is width and n is the number of nodes
w is O(n) when complete tree and thus Time complexity become O(n^2)
 *Approach#1 Recursive
 * 
 *Approach#2 Iterative
 * 
 * 
 * */
public class BinaryTreeVerticalOrderTraversal {
	public ArrayList<ArrayList<Integer>> printvertical (TreeNode root){
		// Zero: null check
		
		// First: recursive left and right with hashmap to identify which vertical level
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		
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
		System.out.println(sol.printvertical(root));
	}
}
