package facebook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Facebook: Printing a binary tree L-R
Print a Binary Tree L-R
Binary Tree Level Order Traversal II
 *  3
   / \
  9  20
    /  \
   15   7
 eturn its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]  
https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
http://buttercola.blogspot.com/2014/11/facebook-printing-binary-tree-l-r.html * 
*/
/*levelordertraversal
 * 		// Zero:corner case, root is null return null
	// First: BFS

 * */
public class BinaryTreeLevelOrderTraversalBottom {
	/*levelordertraversal
	 * 		// Zero:corner case, root is null return null
		// First: BFS

	 * */
	public List<List<Integer>> levelordertraversal(TreeNode root){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		// Zero:corner case, root is null return null
		if (root == null)
			return res;
		// First: BFS
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		//List<Integer> item = new ArrayList<Integer>();
		//item.add(root.val);
		//res.add(0, item);
		// [[], [15, 7], [9, 20], [3]]
		while (!queue.isEmpty()){
			int size = queue.size();
			System.out.println(size);
			List<Integer> itemtemp = new ArrayList<Integer>();
			for (int i = 0; i < size; i++){
				TreeNode cur = queue.poll();
				itemtemp.add(cur.val);
				if (cur.left != null){
					queue.offer(cur.left);
					//itemtemp.add(cur.left.val);
				}
				if (cur.right != null){
					queue.offer(cur.right);
					//itemtemp.add(cur.right.val);
				}
			}
			res.add(0,itemtemp);
		}
		return res;
	}
	public static void main(String[] args){
		TreeNode node1 = new TreeNode(3);
		TreeNode node3 = new TreeNode(9);
		TreeNode node4 = new TreeNode(20);
		TreeNode node9 = new TreeNode(15);
		TreeNode node10 = new TreeNode(7);
		node1.left = node3;
		node1.right = node4;
		node4.left = node9;
		node4.right = node10;		
		BinaryTreeLevelOrderTraversalBottom sol = new BinaryTreeLevelOrderTraversalBottom();
		System.out.println("root:"+node1+", node1:"+node3+", node4:"+node4);
		System.out.println(sol.levelordertraversal(node1));
	}
}
