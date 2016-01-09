package NineChapter3BinaryTree;

public class NineChapter3BinaryTree {
/*
 	1. Binary Tree DFS Traversal
 	 	• DFS Traversal
 	 		• Recursion
 			• Stack
 			• Code Template:     
 			    public class Solution {
	 				public void traverse(TreeNode root){
	 					if (root == null){
	 						return;
	 					}
	 					traverse(root.left);
	 					traverse(root.right);
	 					//@@@ do something with root
	 				}
	 			}
 		• Divide and Conquer
 			• quick sort
 				•  partition (pivot)
 				•  left partition(left to pivot)
 				•  right partition (pivot to right)
 			• merge sort
 				•  left half
 				•  right half
 				•  merge left half and right half by comparison
 			• most of the binary tree problems can be solved by D&Q
 			• Code Template:
 			    public class Solution {
	 				public ResultType traverse(TreeNode root){
	 					//@@@  null or leaf
	 					if (root == null){
	 						//@@@  do something and return
	 					}
	 					/@@@  Divide
	 					ResultType left = traverse(root.left);
	 					ResultType right= traverse(root.right);
	 					
	 					//@@@  Conquer
	 					ResultType result = Merge from left and right
	 					return result
	 				}
	 			} 		
	 		• Example:==================================
	 		    	1. Binary Tree Pre-order Traversal:
	 		    	2. Maximum Depth of the binary tree:
	 		    	3. Balanced binary tree	
	 		    	4. Minimum depth of the binary tree:
					5. Binary Tree Maximum Path Sum
					==================================
 	2. Binary Tree BFS Traversal
 		• BFS Traversal
 			• Queue
 			• Code Template:
	 		• Example:==================================
	 				1. Binary tree level-order traversal:
	 				2. Binary Tree ZigZag traversal
	 				Surrounded Regions
	 				Number of Islands
	 				Word Ladder II
	 				==================================
 	3. Binary Search Tree		
	 		• Example:==================================
					1. Validate Binary Search Tree BST 				
					2. Insert a node in BST
					3. Search Range in a BST
					4. Implement an iterator of a BST (In-order traversal)
					5. Delete a node in BST
						• Case1: The node to delete has no left and right children (simple)
						• Case2: The node to delete has only one subtree (simple)
						• Case3: The node to delete has two children (most complicated)
					6. Least(Lowest) Common Ancestor (LCA) in BST
						• Recursive Solution:
						• Iterative Solution:
					7. Least(Lowest) Common Ancestor (LCA) in BT
						• Naive Solution:
						• Divide and Conquer solution.
	 				==================================
 */
}
