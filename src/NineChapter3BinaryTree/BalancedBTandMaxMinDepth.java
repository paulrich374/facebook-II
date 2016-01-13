package NineChapter3BinaryTree;
/*
       _______5______
      /              \
   ___1__          ___8__
  /      \        /      \
         3           
  
       _______5______
      /              \
   ___1__          ___8__
  /      \        /      \
        _3
       /             
      2
MinDepth
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node
      
        _______5______
      /              
   ___1__          
  /             
 _3
/             
2 
The minimum depth of the tree is 4, not 1    
 * 
 * */


/*balance
 * 		// Zero:null check
		// First: call recursive function
 * */
/*helper
 * 		// Base case: hit null return 0
		// Recursive case: CALCULATE CURRENT +1
		// DIVIDE: every node has left and right
		 * 		helper(root.left)
		 * 		helper(root.right)
		// CONIDTION
	if (left == -1 || right == -1 ||  Math.abs(left-right) > 1){
		return -1;
	}
		// CONQUER
		//      Math.max(left, right) + 1
		//      for every node 
 * */	


/*maxDepth
 * 		// Base Case: root null return 0
	// Recursive Case: divide and conquer
	//    				divide   left = maxDepth(root.left)
	//                           right = maxDepth(root.right)
	//      			conquer  Math.max(left,right)+1
 * */

/*minDepth
 * 		// Base Case: root null not return 0 return MAX_VLAUE instead
		//            NOTE: to avoid unbalanced tree case
		//            ONLY  root.left == null && root.right == null return 1
		// Recursive Case: divide and conquer
		//    				divide   left = minDepth(root.left)
		//                           right = minDepth(root.right)
		//      			conquer  Math.min(left,right)+1	
 * */

public class BalancedBTandMaxMinDepth {
	/*balance
	 * 		// Zero:null check
			// First: call recursive function
	 * */
	/*helper
	 * 		// Base case: hit null return 0
			// Recursive case: CALCULATE CURRENT +1
			// DIVIDE: every node has left and right
			 * 		helper(root.left)
			 * 		helper(root.right)
			// CONIDTION
		if (left == -1 || right == -1 ||  Math.abs(left-right) > 1){
			return -1;
		}
			// CONQUER
			//      Math.max(left, right) + 1
			//      for every node 
	 * */	
	public boolean balance(TreeNode root){
		// Zero:null check
		if (root == null){
			return true;
		}
		// FirsT: call recursive function
		return helper(root) != -1;
		
	}
	/*helper
	 * 		// Base case: hit null return 0
			// Recursive case: CALCULATE CURRENT +1
			// DIVIDE: every node has left and right
			 * 		helper(root.left)
			 * 		helper(root.right)
			// CONIDTION
		if (left == -1 || right == -1 ||  Math.abs(left-right) > 1){
			return -1;
		}
			// CONQUER
			//      Math.max(left, right) + 1
			//      for every node 
	 * */
	private int helper(TreeNode root){
		// Base case: hit null return 0
		if (root == null){
			return 0;
		}
		// Recursive case:
		// Divide
		int left = helper(root.left) ;
		int right = helper(root.right) ;
		// left and right differ larger than 1 or already one subtree is not balanced
		if (left == -1 || right == -1 ||  Math.abs(left-right) > 1){
			return -1;
		}
		// Conquer
		return Math.max(left, right) + 1;// 連最大都有差距
	}
	/*maxDepth
	 * 		// Base Case: root null return 0
		// Recursive Case: divide and conquer
		//    				divide   left = maxDepth(root.left)
		//                           right = maxDepth(root.right)
		//      			conquer  Math.max(left,right)+1
	 * */
	public int maxDepth(TreeNode root){
		// Base Case: root null return 0
		if (root == null)
			return 0;
		// Recursive Case: divide and conquer
		//    				divide   left = maxDepth(root.left)
		//                           right = maxDepth(root.right)
		//      			conquer  Math.max(left,right)+1
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return Math.max(left,right)+1;
	}
	/*minDepth
	 * 		// Base Case: root null not return 0 return MAX_VLAUE instead
			//            NOTE: to avoid unbalanced tree case
			//            ONLY  root.left == null && root.right == null return 1
			// Recursive Case: divide and conquer
			//    				divide   left = minDepth(root.left)
			//                           right = minDepth(root.right)
			//      			conquer  Math.min(left,right)+1	
	 * */
	public int minDepth(TreeNode root){
		// Base Case: root null not return 0 return MAX_VLAUE instead
		//            NOTE: to avoid unbalanced tree case
		//            ONLY  root.left == null && root.right == null return 1
		
		//if (root == null)
		//	return 0;
		if (root == null)
			return Integer.MAX_VALUE;		
		if (root.left == null && root.right == null)
			return 1;
		// Recursive Case: divide and conquer
		//    				divide   left = minDepth(root.left)
		//                           right = minDepth(root.right)
		//      			conquer  Math.min(left,right)+1		
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		return Math.min(left,right)+1;		
	}
	public static void main(String[] args){
		
		BalancedBTandMaxMinDepth sol = new BalancedBTandMaxMinDepth();
		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(1);
		root.right = new TreeNode(8);
		
		root.left.right = new TreeNode(3);
		System.out.println("Tree:"+root.toStringExpression());
		System.out.println("Balanced: "+sol.balance(root));
		System.out.println("MaxDepth: "+sol.maxDepth(root));
		System.out.println("MinDepth: "+sol.minDepth(root));		
		System.out.println("----------");
		TreeNode root2 = new TreeNode(5);
		root2.left = new TreeNode(1);
		root2.right = new TreeNode(8);
		
		root2.left.right = new TreeNode(3);
		root2.left.right.left = new TreeNode(2);
		System.out.println("Tree:"+root2.toStringExpression());
		System.out.println("Balanced: "+sol.balance(root2));	
		System.out.println("MaxDepth: "+sol.maxDepth(root2));
		System.out.println("MinDepth: "+sol.minDepth(root2));		
		System.out.println("----------");

		TreeNode root3 = new TreeNode(5);
		root3.left = new TreeNode(4);
		root3.left.left = new TreeNode(3);
		root3.left.left.left = new TreeNode(2);		
		System.out.println("Tree:"+root3.toStringExpression());
		System.out.println("Balanced: "+sol.balance(root3));	
		System.out.println("MaxDepth: "+sol.maxDepth(root3));		
		System.out.println("MinDepth: "+sol.minDepth(root3));		
		System.out.println("----------");
		
	}
}
