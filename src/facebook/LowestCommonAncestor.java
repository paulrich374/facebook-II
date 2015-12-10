package facebook;
/*
 * LCA problem with no parent pointers. 
 * Given the root of a tree and pointers to two nodes contained in that tree, 
 * return the lowest common ancestor of the two nodes. 
 * IE, the common ancestor furthest from the root. 
 * http://buttercola.blogspot.com/2014/11/facebook-lca-without-parent-pointer.html
 * */

import java.util.ArrayList;
import java.util.List;

/*

* BT - O(n)
*      _______3______
      /              \
   ___5__          ___1__
  /      \        /      \
  6      _2           
        /  \     / \
        7   4   
For example, 
the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
Another example is LCA of nodes 5 and 4 is 5, 
since a node can be a descendant of itself according to the LCA definition.


Without Parent -
BT-       

*/


/*Approach#1 Traditional CORRECT for node is not in the tree*/
/*lca
 * 
		// Zero:corner case, null are not anyone common ancestor
 * 	// First: get path for each node. NOTE: cannot find a path, return NULL
	// Second: compare list from root and find the diverged node's parent
	// Third: in the end, return last equal node
 */
/*findPath
// Zero: base case, root == null, to end and found nothing. FLASE
* 		// First:  TRY LEFT AND RIGHT O(n) save not null itself to not null root
* 		// Second: if never hit any true, drop this node since it is not on our path(equal or could traverse) FALSE

*/
/*Approach#2 Divide and Conquer. WRONG for node is not in the tree*/
/*lcaIIHelper
 * 		// First: Base case, equal to root or root is null
	// Second: get ancestor for both node
	// Third: four cases:
		// fourth: later for judge for hit case, one side is not null, TRUE

 * */
public class LowestCommonAncestor {
	/*Approach#1 Traditional CORRECT for node is not in the tree*/
	/*lca
	 * 
		// Zero:corner case, null are not anyone common ancestor
	 * 	// First: get path for each node
		// Second: compare list from root and find the diverged node's parent
		// Third: in the end, return last equal node
	 */
	public TreeNode lca(TreeNode root, TreeNode p, TreeNode q){
		// Zero:corner case, null are not anyone common ancestor
		if (root == null || p == null || q == null) {
			return null;
		}		
		// First: get path for each node. NOTE: cannot find a path, return NULL
		List<TreeNode> list1 = new ArrayList<TreeNode>();
		List<TreeNode> list2 = new ArrayList<TreeNode>();
		if (!findPath(root, p, list1) || !findPath(root, q , list2)){
			return null;
		}
		// Second: compare list from root and find the diverged node's parent		
		int i, j ;
		for (i = 0, j = 0; i < list1.size() && j < list2.size(); i++, j++) {
			if(list1.get(i).val != list2.get(j).val){
				return list1.get(i-1);
			}
		}
		// Third: in the end, return last equal node		
		return list1.get(i-1);
	}
	/*findPath
			// Zero: base case, root == null, to end and found nothing
	 * 		// First: TRY LEFT AND RIGHT O(n)  save not null itself to not null root
	 * 		// Second: if never hit any true, drop this node since it is not on our path(equal or could traverse)

	 */
	private boolean findPath(TreeNode root, TreeNode node, List<TreeNode> list){
		// Zero: base case, root == null, to end and found nothing. FLASE
		if (root == null)
			return false;
		// First: TRY LEFT AND RIGHT O(n) add node in the list until we encounter our node
		list.add(root);
		if (root.val == node.val){
			return true;// already add current root in
		}
		if ((root.left != null && findPath(root.left, node, list)) || 
				(root.right != null && findPath(root.right, node, list))){
			return true;
		}
		// Second: if never hit any true, drop this node since it is not on our path(equal or could traverse). FLASE
		list.remove(list.size()-1);
		return false;
	}
	/*Approach#2 Divide and Conquer. WRONG for node is not in the tree*/
	/*lcaIIHelper
	 * 		// First: Base case,  root is null
		// when hit , not stop, keep going down
		 *		//Extra: if two node are the same
 		// Second: get ancestor for both node
		// third: Four cases. check returned left and right ancestor, return ancestor is not null
		// fourth: later for judge for hit case	

	 * */
    //public TreeNode lcaIIHelper(TreeNode root, TreeNode p, TreeNode q){
	public Result lcaIIHelper(TreeNode root, TreeNode p, TreeNode q){
		// First: Base case,  root is null
		// when hit , not stop, keep going down
		if (root == null)
			return new Result(root, false);
		// Extra: if two node are the same
		if (root == p && root ==q)
			return new Result(root, true);
		// Second: get ancestor for both node
    	Result left = lcaIIHelper(root.left, p, q);
    	if (left.isAncestor)
    		return left;
    	Result right = lcaIIHelper(root.right, p, q);
    	if (right.isAncestor)
    		return right;
		// third: Four cases. check returned left and right ancestor, return ancestor is not null
		if (left.node != null && right.node != null){
			return new Result(root,true);
		// fourth: later for judge for hit case, one side is not null, TRUE
		} else if (root == p || root == q){
			boolean isAncestor = (left.node != null || right.node != null) ? true: false;
			return new Result(root, isAncestor);
		} else {
			return new Result(left.node != null ? left.node:right.node, false);
		}	
    }
    public TreeNode lcaII(TreeNode root, TreeNode p, TreeNode q){
    	Result res = lcaIIHelper(root, p ,q);
    	if (res.isAncestor)
    		return res.node;
    	return null;
    }
    public static class Result{
    	TreeNode node;
    	boolean isAncestor;
    	public Result(TreeNode node, boolean isAnc){
    		this.node = node;
    		this.isAncestor = isAnc;
    	}
    }
	/*lcaIIWrong
	 * 		// First: Base case, equal to root or root is null
		// Second: get ancestor for both node
    	// Third: four cases:

	 * */    
	public TreeNode lcaIIWrong(TreeNode root, TreeNode p, TreeNode q){
		// First: Base case, equal to root or root is null
    	if (root == null || root == p || root == q)
    	      return root;
		// Second: get ancestor for both node
    	TreeNode left = lcaIIWrong(root.left, p, q);
    	TreeNode right = lcaIIWrong(root.right, p, q);
		// third: Four cases. check returned left and right ancestor, return ancestor is not null
		if (left != null && right != null)
			return root;
		if (left != null)
			return left;
		if (right != null)
			return right;
		// left == null && right == null
		return null;  	
    }    
	public static void main (String[] args){
		TreeNode node1 = new TreeNode(3);
		TreeNode node3 = new TreeNode(5);
		TreeNode node4 = new TreeNode(1);
		TreeNode node7 = new TreeNode(6);
		TreeNode node8 = new TreeNode(2);
		TreeNode node17 = new TreeNode(7);
		TreeNode node18 = new TreeNode(4);
		TreeNode nodeOut = new TreeNode(9);
		node1.left = node3;
		node1.right = node4;
		node3.left = node7;
		node3.right = node8;
		node8.left = node17;
		node8.right = node18;
		LowestCommonAncestor sol = new LowestCommonAncestor();
		System.out.println("root:"+node1+", node1:"+node3+", node4:"+node4);
		System.out.println(sol.lca(node1, node3, node4));
		System.out.println("root:"+node1+", node1:"+node3+", node18:"+node18);
		System.out.println(sol.lca(node1, node3, node18));
		System.out.println("root:"+node1+", node1:"+node3+", nodeOut:"+nodeOut);
		System.out.println(sol.lca(node1, node3, nodeOut));
		System.out.println("root:"+node1+", node1:"+node3+", node1:"+node3);
		System.out.println(sol.lca(node1, node3, node3));
		System.out.println("root:"+node1+", node1:"+node3+", node4:"+node4);
		System.out.println(sol.lcaII(node1, node3, node4));
		System.out.println("root:"+node1+", node1:"+node3+", node18:"+node18);
		System.out.println(sol.lcaII(node1, node3, node18));
		System.out.println("root:"+node1+", node1:"+node3+", nodeOut:"+nodeOut);
		System.out.println(sol.lcaII(node1, node3, nodeOut));
		System.out.println("root:"+node1+", node1:"+node3+", node1:"+node3);
		System.out.println(sol.lcaII(node1, node3, node3));	
		System.out.println("root:"+node1+", node1:"+node3+", nodeOut:"+nodeOut);
		System.out.println("lcaIIWrong"+sol.lcaIIWrong(node1, node3, nodeOut));		
		
		
	}
}
class TreeNode{
	int val;
	TreeNode left, right;
	TreeNode (int val){
		this.val = val;
		left = null;
		right = null;
	}
	public String toString(){
		//return "("+val+","+left.val+","+right.val+")";
		return "("+val+")";
	}
}
/*
 *    3
 *   / \
 *  5   1
 *   \ 
 *    2
 *     \
 *      4
 *   3,5,4
 *      
 * */
 