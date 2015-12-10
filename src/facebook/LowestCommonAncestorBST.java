package facebook;

import java.util.ArrayList;
import java.util.List;

/*
 * BST - O(h)
* If this were a binary search tree, we could modify the find operation for the two nodes
and see where the paths diverge. Unfortunately, this is not a binary search tree, so we
must try other approaches.
* 
*      _______6______
      /              \
   ___2__          ___8__
  /      \        /      \
  0      _4              
        /  \
          
* For example, 
* the lowest common ancestor (LCA) of nodes 2 and 8 is 6. 
* Another example is LCA of nodes 2 and 4 is 2, 
* since a node can be a descendant of itself according to the LCA definition.
* 
Without Parent -
BST-
case: not descendant of itself
root.val > p.val && root.val > q.val
h(root.left,p,q)
root.val == p.val && root.val > q.val
h(root.left,p,q)
root.val > p.val && root.val == q.val
h(root.left,p,q)

root.val < p.val && root.val < q.val
h(root.right,p,q)
root.val == p.val && root.val < q.val
h(root.right,p,q)
root.val < p.val && root.val == q.val
h(root.right,p,q)
case: descendant of itself
*/
/*Approach#1: Traditional*/
/*lcaBST
 * 		// Zero:corner case, null are not anyone common ancestor
	// First: findPath for both node NOTE: cannot find a path, return NULL
	// Second: compare list from root and find the diverged node's parent		

 * */
/*findPath
 * 		// Zero: base case, root == null, to end and found nothing. FLASE
	// First: TRY LEFT OR RIGHT O(H) add node in the list until we encounter our node

 * */
/*Approach#2: Divide and Conquer*/

/*lcaBSTII
 * 	// Zero: check if both nodes contained in the tree or not
	// First: rearrange smaller first
	// Second: same side, keep going down. Otherwise, return current root
 * */
public class LowestCommonAncestorBST {
	/*Approach#1: Traditional*/
	/*lcaBST
	 * 		// Zero:corner case, null are not anyone common ancestor
		// First: findPath for both node NOTE: cannot find a path, return NULL
		// Second: compare list from root and find the diverged node's parent		

	 * */
	public TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q){
		// Zero:corner case, null are not anyone common ancestor
		if (root == null || p == null || q == null)
			return null;
		// First: findPath for both node NOTE: cannot find a path, return NULL
		List<TreeNode> list1= new ArrayList<TreeNode>();
		List<TreeNode> list2 = new ArrayList<TreeNode>();
		if(!findPath(root, p, list1) || !findPath(root, q, list2))
			return null;
		// Second: compare list from root and find the diverged node's parent		
		int i, j;
		for (i = 0, j = 0; i < list1.size() && j < list2.size(); i++, j++){
			if (list1.get(i) != list2.get(j)){
				return list1.get(i-1);
			}
		}
		// Third: in the end, return last equal node				
		return list1.get(i-1);
	}
	/*findPath
	 * 		// Zero: base case, root == null, to end and found nothing. FLASE
		// First: TRY LEFT OR RIGHT O(H) add node in the list until we encounter our node

	 * */
	private boolean findPath(TreeNode root, TreeNode node, List<TreeNode> list){
		// Zero: base case, root == null, to end and found nothing. FLASE
		if (root == null)
			return false;		
		// First: TRY LEFT OR RIGHT O(H) add node in the list until we encounter our node
		list.add(root);
		if (root.val == node.val){
			return true;// already add current root in
		}
		if (root.left != null && root.val > node.val && findPath(root.left, node, list)){
			return true;
		} else if (root.right != null && root.val < node.val && findPath(root.right, node, list)) {
			return true;
		}
		list.remove(list.size()-1);
		return false;
	}
	/*Approach#2: Divide and Conquer*/
	/*lcaBSTII
	 * 	// Zero: check if both nodes contained in the tree or not
		// First: rearrange smaller first
		// Second: same side, keep going down. Otherwise, return current root
	 * */
	public TreeNode lcaBSTII(TreeNode root, TreeNode p, TreeNode q){
		// Zero: check if both nodes contained in the tree or not
		if (!covers(root,p) || !covers(root,q)){
			return null;
		}
		// First: rearrange smaller first
		if (p.val > q.val) {
            return lcaBSTII(root, q, p);
        }
		// Second: same side, keep going down. Otherwise, return current root
        if (root.val <= q.val && root.val >= p.val) {
            return root;
        } else if (root.val > q.val && root.val > p.val) {
            return lcaBSTII(root.left, p, q);
        } else if (root.val < q.val && root.val < p.val) {
            return lcaBSTII(root.right, p, q);
        } else {
            return null;
        }	
    }
	/* Returns true if p is a descendent of root */
	private boolean covers(TreeNode root, TreeNode p) {
	 if (root == null) return false;
	 if (root == p) return true;
	 return covers(root.left, p) || covers(root.right, p);
	}	
	public static void main (String[] args){
		TreeNode node1 = new TreeNode(6);
		TreeNode node3 = new TreeNode(2);
		TreeNode node4 = new TreeNode(8);
		TreeNode node7 = new TreeNode(0);
		TreeNode node8 = new TreeNode(4);
		TreeNode nodeOut = new TreeNode(9);
		node1.left = node3;
		node1.right = node4;
		node3.left = node7;
		node3.right = node8;
		LowestCommonAncestorBST sol = new LowestCommonAncestorBST();
		System.out.println("root:"+node1+", node1:"+node3+", node4:"+node4);
		System.out.println(sol.lcaBST(node1, node3, node4));
		System.out.println("root:"+node1+", node1:"+node3+", node8:"+node8);
		System.out.println(sol.lcaBST(node1, node3, node8));
		System.out.println("root:"+node1+", node1:"+node3+", nodeOut:"+nodeOut);
		System.out.println(sol.lcaBST(node1, node3, nodeOut));
		System.out.println("root:"+node1+", node1:"+node3+", node1:"+node3);
		System.out.println(sol.lcaBST(node1, node3, node3));
		System.out.println("root:"+node1+", node1:"+node3+", node4:"+node4);
		System.out.println(sol.lcaBSTII(node1, node3, node4));
		System.out.println("root:"+node1+", node1:"+node3+", node8:"+node8);
		System.out.println(sol.lcaBSTII(node1, node3, node8));
		System.out.println("root:"+node1+", node1:"+node3+", nodeOut:"+nodeOut);
		System.out.println("COULD WRONG:"+sol.lcaBSTII(node1, node3, nodeOut));
		System.out.println("root:"+node1+", node1:"+node3+", node1:"+node3);
		System.out.println(sol.lcaBSTII(node1, node3, node3));		
	}
}
