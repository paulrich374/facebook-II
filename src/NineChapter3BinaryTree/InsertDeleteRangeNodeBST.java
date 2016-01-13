package NineChapter3BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;


/*
 * 
 * * 
*      _______6______
      /              \
   ___2__          ___8__
  /      \        /      \
  0      _4              Insert 9
        /  \
   insert 3  

       _______20______
      /              \
   __12__          __30__
  /      \        /      \
 10      __      23      36
        /  \    /  \ 
               21   25
                   /  \
                  24   26  
 * 
 * */


/* insert 
 * 
 * 			// call recursive function helper()
 */
/* helper
 * 
 *	    	// Base case: hit null, not exist, create a new node
 * 	    	// Recursive case: traverse, go left or right or update
 * 				val < root.val  => root.left = helper(root.left, val);
 * 				val > root.val  => root.right = helper(root.right, val);
 * 				val == root.val => root.val = val;
				return root
 * */


/*searchForRange
 * 
 *XX Find the k1 and find the k2 and then using inorder to start from k1 and stop at k2
 *XX Find the k1 and keep recording visited node alone to k1
 *XX Find the k2 and keep recording visited node alone to k2 
 *
 * call the recursive function helper2()
  */
/*helper2
 * 
 * 	// Base case: leaf stop and return
	// Recursive case: Inorder from k1 to k2
	//          1. Inorder left =>check k1< root.val find the node just larger than k1
	//          2. Inorder hit null => check k1<= root.val <= k2 and add current leaf it into list
	//          3. Inorder right => root.val < k2 start find the node just less than k2
 * */

/*deleteNode
 *  
	     *  
	     *  0. deleteNode() === make node's parent's children null=>Need a parent so a FAKE parent
	     * 	1. findNode() call find the node
	     *  2. delete() call delete and rearrange within find the node
 * 
 *findNode
 * 	
 *  // Base Case: find the node(root.val == val) or cannot find the node(root == null)
 *  //           // 2. delete and rearrange   
	// Recursive Case: root.val > val go to left and root.val < val go to right
 *  
	    
 * delete
	     * 
	     * 	// case 1: node.left == null && node.right == null
	    	// case2: node.left == null || node.right == null
	    	//        move delete node's one not null children as parent's new children
	    	// 		  NOTE: can cover case 1
	    	//        @@NOTE: ONLY care left, right just append it to new replaced node
	    	// case3: node.left != null && node.right != null
	    	//        move delete node's two not null children as parent's new children
            //        find the largest in left subtree and replace delete node with this largest node
	    	// FIRST: LEFT IS NULL => APPEND RIGHT TO PARENT 
	    	//		    		if (parent.left == node)
			//  					parent.left = node.right;
	    	//					else 
			//						parent.right = node.right;     		
	    	// SESOND: LEFT IS NOT NULL =>First: FIND LEFT SUBTREE LARGEST
    		// 							=>Second: REPALCE AND APPEND LEFT SUBTREE LARGEST TO PARENT	    	
	     * 
	 
 * */


public class InsertDeleteRangeNodeBST {
		
	
		/*insert 
		 * 
		 * 			// call recursive function helper()
		 */
    	/*helper
    	 * 
    	 *	    	// Base case: hit null, not exist, create a new node
    	 * 	    	// Recursive case: traverse, go left or right or update
		 * 				val < root.val  => root.left = helper(root.left, val);
		 * 				val > root.val  => root.right = helper(root.right, val);
		 * 				val == root.val => root.val = val;
    					return root
    	 * */	
	    public TreeNode insert(TreeNode root, int val){
	    	// call recursive function helper()
	    	return helper(root, val);
	    }
	    /*helper
	     * 
	    			// Base case: go beyond leaf, not exist, create a new node
	    			// Recursive case: traverse, go left or right or update
					// 				val < root.val  => root.left = helper(root.left, val);
					// 				val > root.val  => root.right = helper(root.right, val);
					// 				val == root.val => root.val = val;
	    			//				return current node	 
	     * */
	    private TreeNode helper(TreeNode root, int val){
	    	// Base case: go beyond leaf, not exist, create a new node
	    	if (root == null)
	    		return new TreeNode(val);
	    	// Recursive case: traverse, go left or right or update
			// 				val < root.val  => root.left = helper(root.left, val);
			// 				val > root.val  => root.right = helper(root.right, val);
			// 				val == root.val => root.val = val;
	    	//				return current node	    	
	    	//int cmp = rootVlaue.compareTo(val);
	    	if (val < root.val)
	    		//return helper(root.left, val)
	    		root.left = helper(root.left, val);
	    	else if (val > root.val)
	    		//return helper(root.right, val);
	    		root.right = helper(root.right, val);
	    	else 
	    		root.val = val;
	    	// return current node
	    	return root;
	    }
	    
	    
	    
	    /*searchForRange
	     * 
	     *XX Find the k1 and find the k2 and then using inorder to start from k1 and stop at k2
	     *XX Find the k1 and keep recording visited node alone to k1
	     *XX Find the k2 and keep recording visited node alone to k2 
	     *
	     * call the recursive function helper2()
	      */
	    /*helper2
	     * 	// Base case: leaf stop and return
	    	// Recursive case: Inorder from k1 to k2
	    	//          1. Inorder left =>check k1< root.val find the node just larger than k1
	    	//          2. Inorder hit null => check k1<= root.val <= k2 and add current leaf it into list
	    	//          3. Inorder right => root.val < k2 start find the node just less than k2
	     * */	    
	    public ArrayList<Integer> searchForRange(TreeNode root, int k1, int k2){
	    	ArrayList<Integer> res = new ArrayList<Integer>();
	    	// Zero: neg check
	    	if (k1 <0 || k2 < 0)
	    		return res;
	    	// First:
	    	helper2(root, k1, k2, res);
	    	return res;
	    }
	    /*helper2
	     * 	// Base case: traverse to leaf stop and return
	    	// Recursive case: Inorder from k1 to k2
	    	//          1. Inorder left =>check k1< root.val find the node just larger than k1
	    	//          2. Inorder hit null => check k1<= root.val <= k2 and add current leaf it into list
	    	//          3. Inorder right => root.val < k2 start find the node just less than k2
	     * */
	    private void helper2(TreeNode root, int k1, int k2, ArrayList<Integer> res){
	    	// Base case:  traverse to leaf stop and return
	    	if (root == null)  
	    		return;
	    	// Recursive case: Inorder from k1 to k2
	    	//          1. Inorder left =>check k1< root.val find the node just larger than k1
	    	//          2. Inorder hit null => check k1<= root.val <= k2 and add current leaf it into list
	    	//          3. Inorder right => root.val < k2 start find the node just less than k2
	    	if (root.val > k1){
	    		helper2(root.left,k1,k2,res);
	    	}
	    	if (root.val >= k1 && root.val <= k2){
	    		res.add(root.val);
	    	}
	    	if (root.val < k2){
	    		helper2(root.right, k1,k2,res);
	    	}
	    }
	    
	    
	    
	    /*deleteNode
	     *  
	     *  0. deleteNode() === make node's parent's children null=>Need a parent so a FAKE parent
	     * 	1. findNode() call find the node
	     *  2. delete() call delete and rearrange within find the node
	     *  
	     * */
	    public void deleteNode(TreeNode root, int val){
	    	// 0. deleteNode === make node's parent's children null=>Need a parent so a FAKE parent
	    	TreeNode dummyNode = new TreeNode(Integer.MIN_VALUE);
	    	dummyNode.left = root;
	    	// 1. find the node
	    	findNode(dummyNode, root, val);
	    }
	    /*findNode
	     * 	
	     *  // Base Case: find the node(root.val == val) or cannot find the node(root == null)
	     *  //           // 2. delete and rearrange   
	    	// Recursive Case: root.val > val go to left and root.val < val go to right
	  
	     * */
	    private void findNode(TreeNode parent, TreeNode root, int val){
	    	// Base Case: find the node or cannot find the node
	    	if (root == null){
	    		return;
	    	}
	    	if (root.val == val){
	    		//System.out.println("find"+root.val);
	    		//delete(root);
	    		// 2. delete and rearrange
	    		delete(parent, root);
	    		return;
	    	}
	    	// Recursive Case: root.val > val go to left and root.val < val go to right
	    	if (root.val > val){
	    		findNode(root, root.left, val);
	    	}
	    	else if (root.val < val){
	    		findNode(root, root.right, val);
	    	}
	    }
	    /*
	     * delete
	     * 
	     * 	// case 1: node.left == null && node.right == null
	    	// case2: node.left == null || node.right == null
	    	//        move delete node's one not null children as parent's new children
	    	// 		  NOTE: can cover case 1
	    	//        @@NOTE: ONLY care left, right just append it to new replaced node
	    	// case3: node.left != null && node.right != null
	    	//        move delete node's two not null children as parent's new children
            //        find the largest in left subtree and replace delete node with this largest node
	    	// FIRST: LEFT IS NULL => APPEND RIGHT TO PARENT 
	    	//		    		if (parent.left == node)
			//  					parent.left = node.right;
	    	//					else 
			//						parent.right = node.right;     		
	    	// SESOND: LEFT IS NOT NULL =>First: FIND LEFT SUBTREE LARGEST
    		// 							=>Second: REPALCE AND APPEND LEFT SUBTREE LARGEST TO PARENT	    	
	     * 
	     * */
	    private void delete(TreeNode parent, TreeNode node){
	    	// case 1: node.left == null && node.right == null
	    	//if (node.left == null && node.right == null ){
	    	//	if (parent.left == node)
	    	//		parent.left = null;
	    	//	else 
	    	//		parent.right = null;
	    	//	return;
	    	//}
	    	// case2: node.left == null || node.right == null
	    	//        move delete node's one not null children as parent's new children
	    	// 		  NOTE: can cover case 1
	    	//        @@NOTE: ONLY care left, right just append it to new parent
	    	// FIRST: LEFT IS NULL => APPEND RIGHT TO PARENT
	    	//		    		if (parent.left == node)
			//  					parent.left = node.right;
	    	//					else 
			//						parent.right = node.right;
	    	
	    	if (node.left == null){
	    		// 
	    		if (parent.left == node)
	    			parent.left = node.right;
	    		else 
	    			parent.right = node.right;
	    	} 
	    	////@@@@else if (node.right == null){
	    	////@@@@	if (parent.left == node)
	    	////@@@@		parent.left = node.left;
	    	////@@@@	else 
	    	////@@@@		parent.right = node.left;	    		
	    	// case3: node.left != null && node.right != null
	    	//        move delete node's two not null children as parent's new children
            //        find the largest in left subtree and replace delete node with this largest node
    		// First: Find the largest node in the left subtree
    		// 		keep traversing right
    		// 		No right children, replace parent's left with maxNodeChilren's left subtree 
    		// 		with right children, replace parent's right with maxNodeChilren's left subtree     		
    		// Second: Replace the delete node with the found largest node
    		//         		maxNodeChildren.left = node.left;
    		//         		maxNodeChildren.right = node.right;
    		//         bind with original parent
    		// 	    		if (parent.left == node)
			//  				parent.left = maxNodeChildren;
    		//				else 
			// 					parent.right = maxNodeChildren;		    	
	    	//      /              \
	    	//   __12__          _"30"_
	    	//  /      \        /      \
	    	// 10      __      23      36
	    	//        /  \    /   \
	    	//               21   25=final max parent
	    	//                   /  \
	    	//         *        24   26=final max 
	    	
	    	//      /              \
	    	//   __12__          _"30"_
	    	//  /      \        /      \
	    	// 10      __      23      36
	    	//        /  \    /   \
	    	//               21   25=final max 
	    	//                   /  
	    	//         *        24     23 =final max parent	    	
	    	//@@@@if (node.left != null && node.right != null){
	    	// SESOND: LEFT IS NOT NULL =>First: FIND LEFT SUBTREE LARGEST
    		// 							=>Second: REPALCE AND APPEND LEFT SUBTREE LARGEST TO PARENT
	    	else {
	    		// First: FIND LEFT SUBTREE LARGEST
	    		// 		keep traversing right
	    		// 		No right children, replace parent's left with maxNodeChilren's left subtree 
	    		// 		with right children, replace parent's right with maxNodeChilren's left subtree 
	    		TreeNode maxNodeParent = node;
	    		TreeNode maxNodeChildren = node.left;
	    		// keep traversing right
	    		while (maxNodeChildren.right != null ){
	    			maxNodeParent = maxNodeChildren;
	    			maxNodeChildren = maxNodeChildren.right;
	    		}
	    		// No right children, replace parent's left with maxNodeChilren's left subtree 
	    		if (maxNodeChildren == maxNodeParent.left){
	    			maxNodeParent.left = maxNodeChildren.left; 
	    		// with right children, replace parent's right with maxNodeChilren's left subtree 
	    		} else{
	    			maxNodeParent.right = maxNodeChildren.left; 
	    		}
	    		// Second: REPALCE AND APPEND LEFT SUBTREE LARGEST TO PARENT
	    		//         		maxNodeChildren.left = node.left;
	    		//         		maxNodeChildren.right = node.right;
	    		//         bind with original parent
	    		// 	    		if (parent.left == node)
    			//  				parent.left = maxNodeChildren;
	    		//				else 
    			// 					parent.right = maxNodeChildren;	
	    		maxNodeChildren.left = node.left;
	    		maxNodeChildren.right = node.right;
	    		if (parent.left == node)
	    			parent.left = maxNodeChildren;
	    		else 
	    			parent.right = maxNodeChildren;		    		
	    	}
	    }
	    
		public static void main(String[] args){
			TreeNode root = new TreeNode(20);
			root.left = new TreeNode(12);
			root.right = new TreeNode(30);
			
			root.left.left = new TreeNode(10);
			
			root.right.left = new TreeNode(23);
			root.right.right = new TreeNode(36);
			
			root.right.left.left = new TreeNode(21);
			root.right.left.right = new TreeNode(25);
			root.right.left.right.left = new TreeNode(24);
			root.right.left.right.right = new TreeNode(26);
			
			InsertDeleteRangeNodeBST sol = new InsertDeleteRangeNodeBST();
			
			System.out.println("Tree:\n "+root.toStringExpression());
			sol.insert(root, 15);
			System.out.println("insert 15:\n "+root.toStringExpression());
			int k1 = 10;
			int k2 = 22;
			System.out.println("searchForRange:\n "+"("+k1+", "+k2+")\n "+sol.searchForRange(root, k1,k2));
			sol.deleteNode(root, 15);
			System.out.println("\ndelete 15:\n "+root.toStringExpression());
			sol.deleteNode(root, 12);
			System.out.println("delete 12:\n "+ root.toStringExpression());
			sol.deleteNode(root, 30);			
			System.out.println("delete 30:\n " + root.toStringExpression());

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
	public String toStringExpression(){
		StringBuilder sb = new StringBuilder();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(this);
		while (!queue.isEmpty()){
/*
			int size = queue.size();
			for (int i = 0 ; i < size; i++){
				TreeNode cur = queue.poll();
				//if (cur.val == -1)
				//	sb.append("#, ");
				//else 
					sb.append(cur.val+", ");
				if (cur.left != null)
					queue.add(cur.left);
				//else
					//queue.add(new TreeNode(-1));
				
				if (cur.right != null)
					queue.add(cur.right);
				//else
					//queue.add(new TreeNode(-1));
			}
			sb.append(", \n ,");
NO null.left or null.right so only if cur != null, we cur.left and cur.right			
*/
			int size = queue.size();
			for (int i = 0 ; i < size; i++){
				TreeNode cur = queue.poll();
				if (cur == null ){
					sb.append("null,");
				} else {
					sb.append(cur.val);
					sb.append(",");
					queue.add(cur.left);
					queue.add(cur.right);
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}