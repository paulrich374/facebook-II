package facebook;

import java.util.ArrayList;
import java.util.List;

/*
 * With Parent -
(p, q, pp, qq)
// Base case 
pp.parent == qq.parent || pp.parent == q || qq.parent == p
pp == null || qq == null
h(p,q, pp.parent,qq.parent)
6->5->3
4->2->5
13->9->1->3
 6->5->3
 FROM BOTTOM UP and recursive, how to compare???????
 recursive + top down !!!
 FROM TOP ROOT is easy, just seek divided node or last equal node
need a hash table to store parent's info
HashMap<TreeNode, List<TreeNode>> = <p or q, its parent nodes list>
*      _______3______
      /              \
   ___5__          ___1__
  /      \        /      \
  6      _2            
        /  \     
        7   4   
 * 
 * http://buttercola.blogspot.com/2014/11/facebook-lca-without-parent-pointer.html
 */

/*Approach#1: Traditional BETTER for those node not in the tree*/
/*lcaParent
 * 		// First: get path for each node
 * 		// Second: compare list from root and find the diverged node's parent. 
		// Third: in the end, return last equal node
 */
/*getPathToRoot
 * 		// First: save not null itself to not null root
 */

/*Approach#2: Divide and Conquer WRONG for those node not in the tree*/
/*lcaParentII
 * 		// Zero: corner case
		// First: get root
		// Second: get ancestor from root, p and q
 * 
 */
/*getAncestor
 * 		// First: Base case, equal to root or root is null
	// Second: get ancestor for both node
	// third: check returned left and right ancestor, return ancestor is not null
 5
1 3
diff side, hit both, both not null, return current root
 5
2 4 
diff side, not one hit, both null
 6
5 
 4
 same side, hit 5 and return 
   6
5 
 9
 same side, hit 5 and return WRONG. 9 is not in the tree
 */
/*getRoot
// First: not null parent to null parent is root	 	
*/
public class LowestCommonAncestorWithParent {
	/*Approach#1: Traditional */
	/*lcaParent
	 * 		// First: get path for each node
	 * 		// Second: compare list from root and find the diverged node's parent
		    // Third: in the end, return last equal node
     */
	//public TreeNodeParent lcaParent(TreeNodeParent root, TreeNodeParent p, TreeNodeParent q){
	public TreeNodeParent lcaParent( TreeNodeParent p, TreeNodeParent q){
		// Zero:corner case, null are not anyone common ancestor
		if (p == null || q == null)
			return null;
		// First: get path for each node
		List<TreeNodeParent> list1 = getPathToRoot(p);
		List<TreeNodeParent> list2 = getPathToRoot(q);
		// Second: compare list from root and find the diverged node's parent
		int i,j;
		for (i = list1.size() - 1, j = list2.size() - 1; i >= 0 && j >=0; i--, j--){
			if (list1.get(i) != list2.get(j) ){
				return list1.get(i).parent;
			}
		}
		// Third: in the end, return last equal node
		return list1.get(i+1); // define i as global
	}
	/*getPathToRoot
	 * 		// First: save not null itself to not null root
	 * 
	 */
	private List<TreeNodeParent> getPathToRoot(TreeNodeParent node){
		List<TreeNodeParent> res= new ArrayList<TreeNodeParent>();
		// First: save not null itself to not null root
		while(node!= null){
			res.add(node);
			node = node.parent;
		}
		return res;
	}
	/*Approach#2: Divide and Conquer */
	/*lcaParentII
	 * 		// Zero: corner case
			// First: get root
			// Second: get ancestor from root, p and q
	 * 
	 */
	public TreeNodeParent lcaParentII(TreeNodeParent p, TreeNodeParent q){
		// Zero: corner case
		if (p == null || q == null) 
			return null;
		// First: get root
		TreeNodeParent root = getRoot(p);
		// Second: get ancestor from root, p and q
		return getAncestor(root, p, q);
	}
	/*getAncestor
	 * 		// First: Base case, equal to root or root is null
		// Second: get ancestor for both node
		// third: check returned left and right ancestor, return ancestor is not null
 5
1 3
diff side, hit both, both not null, return current root
 5
2 4 
diff side, not one hit, both null
 6
5 
 4
 same side, hit 5 and return 
  6
5 
 9
 same side, hit 5 and return WRONG. 9 is not in the tree
	 */
	private TreeNodeParent getAncestor(TreeNodeParent root, TreeNodeParent p, TreeNodeParent q){
		// First: Base case, equal to root or root is null
		if (root == null || root == p || root == q)
			return root;
		// Second: get ancestor for both node
		TreeNodeParent left= getAncestor(root.left, p, q);
		TreeNodeParent right = getAncestor(root.right, p ,q);
		// third: Four cases. check returned left and right ancestor, return ancestor is not null
		if (left != null && right != null){
			return root;
		}
		if (left != null){
			return left;
		}
		if (right != null){
			return right;
		}
		// left == null && right == null
		return null;
	}
	/*getRoot
		// First: not null parent to null parent is root	 	
	 */
	private TreeNodeParent getRoot(TreeNodeParent node){
		// First: not null parent to null parent is root
		while(node.parent!=null){
			node = node.parent;
		}
		return node;
	}
	
	
	
	
	public static void main (String[] args){
		TreeNodeParent node1 = new TreeNodeParent(3);
		TreeNodeParent node3 = new TreeNodeParent(5);
		TreeNodeParent node4 = new TreeNodeParent(1);
		TreeNodeParent node7 = new TreeNodeParent(6);
		TreeNodeParent node8 = new TreeNodeParent(2);
		TreeNodeParent node17 = new TreeNodeParent(7);
		TreeNodeParent node18 = new TreeNodeParent(4);
		TreeNodeParent nodeOut = new TreeNodeParent(9);
		node1.left = node3;
		node1.right = node4;
		node3.parent = node1;
		node4.parent = node1;
		node3.left = node7;
		node3.right = node8;
		node7.parent = node3;
		node8.parent = node3;
		node8.left = node17;
		node8.right = node18;
		node17.parent = node8;
		node18.parent = node8;
		LowestCommonAncestorWithParent sol = new LowestCommonAncestorWithParent();
		System.out.println("root:"+node1+", node1:"+node3+", node4:"+node4);
		System.out.println("lca: "+sol.lcaParent(node3, node4));
		System.out.println("root:"+node1+", node3:"+node3+", node18:"+node18);
		System.out.println("lca: "+sol.lcaParent(node3, node18));
		System.out.println("root:"+node1+", node3:"+node3+", nodeOut:"+nodeOut);
		System.out.println("lca: "+sol.lcaParent(node3, nodeOut));			
		LowestCommonAncestorWithParent sol2= new LowestCommonAncestorWithParent();
		System.out.println("root:"+node1+", node1:"+node3+", node4:"+node4);
		System.out.println("lca: "+sol2.lcaParentII(node3, node4));
		System.out.println("root:"+node1+", node3:"+node3+", node18:"+node18);
		System.out.println("lca: "+sol2.lcaParentII(node3, node18));	
		System.out.println("root:"+node1+", node3:"+node3+", nodeOut:"+nodeOut);
		System.out.println("lcaWRONG: "+sol2.lcaParentII(node3, nodeOut));		
	}
}
class TreeNodeParent{
	int val;
	TreeNodeParent left, right, parent;
	TreeNodeParent (int val){
		this.val = val;
		left = null;
		right = null;
		parent = null;
	}
	public String toString(){
		//return "("+val+","+left.val+","+right.val+")";
		return "("+val+")";
	}
}
/*
 * 5-> 5,3
 * 1-> 1,3
 * from 3==3, to 5!=1 return 5's parent
 * 5-> 5,3
 * 4-> 4,2,5,3
 * from 3(i=1)==3, to 5(i=0)==5, to OUT of loop(i=-1)
 * return last list1.get(i+1)[i=0] is 5
 */
 