package facebook;

import java.util.HashMap;
import java.util.LinkedList;


/*
 * Understand the problem:
Serialization is to store tree in a file so that it can be later restored. The structure of tree must be maintained. 
Deserialization is reading tree back from file.
		              10,
	      3,		                     15,
    1,             7,            null,           18,
null,null,    5,          9,       ??       16,         21,
         null, null, null  ,null,       null, null ,null,  null,
 * http://buttercola.blogspot.com/2014/11/facebook-binary-tree-serialization-and.html
 * */

public class BTSerializeDeserialize {
	/*serializeIterative
	 *  The only trick is if the tree node dequeued is a null node, 
	 *  append a # character to the result list. 
	 * 		// Zero: null check
		// First: a queue and StringBuidelr 
		// Second: BFS
		//         case1: null, print null
		//         case2: not null, print val and comma and put left and right children in queue
	 * */
	public String serializeIterative(TreeNode root){
		// Zero: null check
		if (root == null)
			return "";
		// First: a queue and StringBuidelr 
		StringBuilder sb = new StringBuilder();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		// Second: BFS
		//         case1: null, print null
		//         case2: not null, print val and comma and put left and right children in queue
		queue.offer(root);
		while (!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0 ; i < size; i++){
				TreeNode cur = queue.poll();
				if (cur == null){
					sb.append("null,");
				} else {
					sb.append(cur.val);
					sb.append(",");
					queue.offer(cur.left);
					queue.offer(cur.right);
				}
			}
		}
		return sb.toString();
	}
	/*deserializeIterative - CONSTRUCT A TREE
	 * 		// Zero: null check
		// First: split the string
		// Second: construct a tree
		//         return root[0] 
		//         A prepared TreeNode array since root[0].left = root[1] and root[0].right = root[2]
		//         A countNull array NOTE: a previous null will have two gap for later node
		//         first loop: assign TreeNode array value
		//         	// update countnull
		//          // check nodes string null or not
		//         second loop: connect TreeNode array parent-child
        // 			// treenode == null , skip this iteration		
		//         	// treenode not null, assign (i-countnull[j])*2 +1 or 2
		// Third: return first node
	 * */
	public TreeNode deserializeIterative(String data){
		// Zero: null check
		if (data == null || data.length() == 0)
			return null;
		// First: split the string
		String[] nodes = data.split(",");
		// Second: construct a tree
		//         return root[0] 
		//         A prepared TreeNode array since root[0].left = root[1] and root[0].right = root[2]
		//         A countNull array NOTE: a previous null will have two gap for later node
		//         first loop: assign TreeNode array value
		//         	// update countnull
		//          // check nodes string null or not
		//         second loop: connect TreeNode array parent-child
        // 			// treenode == null , skip this iteration		
		//         	// treenode not null, assign (i-countnull[j])*2 +1 or 2
		TreeNode[] roots = new TreeNode[nodes.length];
		int[] countnull = new int[nodes.length];
		for (int i = 0 ; i < nodes.length;i++){
			// update countnull
			if (i > 0)
				countnull[i] = countnull[i-1];
			// check nodes string null or not
			if (nodes[i].equals("null")){
				roots[i] = null;
				countnull[i]++;
			} else {
				roots[i] = new TreeNode(Integer.parseInt(nodes[i]));
			}
		}
		for (int j = 0; j < roots.length; j++){
			// treenode == null , skip this iteration
			if (roots[j] == null) continue;
			// treenode not null, assign (j-countnull[j])*2 +1 or 2
			roots[j].left = roots[(j-countnull[j])*2 +1];
			roots[j].right = roots[(j-countnull[j])*2 +2];
		}
		// Third: return first node
		return roots[0];
	}
	/*serializeRecursive
	 * 		// Zero: null check
			// First:  StringBuilder and recursive helper
					// Base case:
					//           not null append
					//           null append and return
					// REcursive case :left first and right
	 * */
	public String serializeRecursive(TreeNode root){
		// Zero: null check
		if (root == null)
			return "";		
		// First:  StringBuilder and recursive
		StringBuilder sb = new StringBuilder();
		//LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		// Second: DFS
		helper(root, sb);
		return sb.toString();
	}
	private void helper(TreeNode root, StringBuilder sb){
		// Base case:
		//           not null append
		//           null append and return
		if (root != null){
			sb.append(root.val);
			sb.append(",");
		} else {
			sb.append("null,");
			return;
		}
		// Recursive case :left first and right
		helper(root.left, sb);
		helper(root.right, sb);
	}
	/*deserializeRecursive
	 * // Zero: null check
	 * // First: split the data and integer array
	 * // Second: buildtree 
	 */
	public TreeNode deserializeRecursive(String data){
		// Zero: null check
		if (data == null || data.length() == 0)
			return null;
		// First: split the data and integer array
		String[] nodes = data.split(",");
		int[] index = new int[2];
		// Second: buildtree 
		return buildTree(nodes, index);
	}	
	/*buildTree
	 * // Base case: index > length or nodes == null
	 * // Recursive case: create a new node with index and connect with left and right
	 * */
	private TreeNode buildTree(String[] nodes, int[] index){
		// Base case: index > length or nodes == null
		if (index[0] >= nodes.length)
			return null;
		if (nodes[index[0]].equals("null"))
			return null;
		// Recursive case: create a new node with index and connect with left and right
		TreeNode node = new TreeNode(Integer.parseInt(nodes[index[0]]));
		index[0]++;
		node.left = buildTree(nodes, index);	
		index[0]++;
		node.right = buildTree(nodes, index);
		return node;
	}
	public static void main(String[] args){
		BTSerializeDeserialize sol = new BTSerializeDeserialize();
		final TreeNode root2 = new TreeNode(10);
		root2.left = new TreeNode(3);
		root2.right = new TreeNode(15);
		root2.left.left = new TreeNode(1);
		root2.left.right = new TreeNode(7);
		root2.left.right.left = new TreeNode(5);
		root2.left.right.right = new TreeNode(9);
		
		root2.right.right = new TreeNode(18);
		root2.right.right.left = new TreeNode(16);
		root2.right.right.right = new TreeNode(21);
		System.out.println("\nroot2 - ");
		System.out.print(root2.toStringExpression());
		String res1 = sol.serializeIterative(root2);
		System.out.println("\nserializeIterative: \n"+res1);
		System.out.println("\ndeserializeIterative: \n"+sol.deserializeIterative(res1).toStringExpression());
		String res2 = sol.serializeRecursive(root2);
		System.out.println("\nserializeRecursive:  \n"+res2);
		System.out.println("\ndeserializeRecursive: \n"+sol.deserializeRecursive(res2).toStringExpression());	
	}
}
/*
 * 		int countNull = 0;
		HashMap<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
		for (int i = 0 ; i < nodes.length; i++){
			if (nodes[i] == null){
				countNull++;
			}else {
				TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
				root.left = new TreeNode(Integer.parseInt(nodes[2*i+1-countNull*2]));
				root.right = new TreeNode(Integer.parseInt(nodes[2*i+2-countNull*2]));
			}
		}
 * 
 * */
