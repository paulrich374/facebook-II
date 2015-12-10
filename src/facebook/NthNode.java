package facebook;
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
	public int nthElement(TreeNode root, int n){
		// Zero: corner case
		if (root == null)
			return 0;
		
		return 0;
	}
	public static void main(String[] args) {
		NthNode sol = new NthNode();
	      TreeNode root = new TreeNode(1);
	      root.left = new TreeNode(2);
	      root.right = new TreeNode(3);
	      root.left.left = new TreeNode(4);
	      root.left.right = new TreeNode(5);
	      root.right.right = new TreeNode(6);
	      int result = sol.nthElement(root, 6);
	      System.out.println("Result is " + result);
	       
	    }
}
