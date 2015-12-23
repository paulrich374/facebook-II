package facebook;

import java.util.LinkedList;
import java.util.Stack;

/*
 * 						 Inorder - next smallest number in the BST
 *         
 * Implement an iterator over a binary search tree (BST). 
 * Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.
next()    T:O(1)
hasNext() T:O(1)
Space:O(h)
If using recursive O(h)
https://leetcode.com/problems/binary-search-tree-iterator/
https://www.quora.com/What-would-be-the-space-complexity-of-a-recursive-inorder-traversal-of-a-binary-tree
But if a list is uesed to store all elements then it is O(n)
Note: next() and hasNext() should run in average O(1) time and uses O(h) memory,
 where h is the height of the tree.
 http://www.jiuzhang.com/solutions/implement-iterator-of-binary-search-tree/
 * 
 * 

 * */
/*
 * 
   //First(BSTIterator):  a current node assigned by a root and a stack S:O(h) to hold all previous parents
   //Second(hasNext): hasNext  T:O(1)
  		//First: when first time no stack and root != null || to the leaf and stack is not empty
   //Third(next):
  		// First: like helper(root.left)
		// Second:Like .add(root). Pop up the Last in on the stack when cur == null, 
		// Third: like helper(root.right)
 * */
public class BSTIterator {
	// First:  a current node assigned by a root and a stack S:O(h) to hold all previous parents
	private TreeNode cur;
	private Stack<TreeNode> stack = new Stack<TreeNode>();
	public BSTIterator(TreeNode root){
		this.cur = root;
	}
	// T:O(1) or < O(h)
	/*next
	 * 	// First: like helper(root.left)
		// Second:Like .add(root). Pop up the Last in on the stack when cur == null, 
		// Third: like helper(root.right)
	 * */
	public int next(){
		// First: like helper(root.left)
		while (cur != null){
			stack.push(cur);
			cur = cur.left;
		}
		// Second: Like .add(root). when cur == null, pop up the latest
		cur = stack.pop();
		TreeNode res = cur;
		// Third: like helper(root.right)
		cur = cur.right;
		return res.val;
	}
	// T:O(1)
	/*hasNext
	 * 		//First: when first time no stack and root != null || to the leaf and stack is not empty
	 * */
	public boolean hasNext(){
		//First: when first time no stack and root != null || to the leaf and stack is not empty
		return (cur != null || !stack.isEmpty());
	}
	public static void main(String[] args){
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		System.out.print(root.toStringExpression());
		System.out.println();
		BSTIterator sol = new BSTIterator(root);
		while(sol.hasNext()){
			System.out.println(sol.next()+", ");
		}
	}
}
