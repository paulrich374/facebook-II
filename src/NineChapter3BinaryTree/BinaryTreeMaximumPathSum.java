package NineChapter3BinaryTree;
/*
 * The path may start and end at any node in the tree.


         	  10"
            /   \
           2"    11"
         /  \      \
        20"  1    -25
      			 /  \        
     			3    4 

maximum path sum = 20+2+10+11 = 43

 * http://d2dskowxfbo68o.cloudfront.net/wp-content/uploads/tree4.png
 * */


/*maxPathSum
 * call recursive function
 * record max use array
 * */
/*helper
// Base case: null return 0 
// Recursive case:
//               divide helper(root.left, max) helper(root.right, max)
//               conquer   path  Math.max(Math.max(left, right) + root.val, root.val)
//                         sum   Math.max(Math.max(root.val+left+right, maxPath), max[0])
* */	

public class BinaryTreeMaximumPathSum {
	/*maxPathSum
	 * call recursive function
	 * record max use array
	 * */
	/*helper
	// Base case: null return 0 
	// Recursive case:
	//               divide helper(root.left, max) helper(root.right, max)
	//               conquer   path  Math.max(Math.max(left, right) + root.val, root.val)
	//                         sum   Math.max(Math.max(root.val+left+right, maxPath), max[0])
 * */	
	public int maxPathSum(TreeNode root){
		int[] max = new int[2];
		helper(root,max);
		return max[0];
	}
	/*helper
		// Base case: null return 0 
		// Recursive case:
		//               divide helper(root.left, max) helper(root.right, max)
		//               conquer   path  Math.max(Math.max(left, right) + root.val, root.val)
		//                         sum   Math.max(Math.max(root.val+left+right, maxPath), max[0])
	 * */
	private int helper(TreeNode root, int[] max){
		// Base case: null return 0
		if (root == null){
			return 0;
		}
		// Recursive case:
		//               divide helper(root.left, max) helper(root.right, max)
		//               conquer   path  Math.max(Math.max(left, right) + root.val, root.val)
		//                         sum   Math.max(Math.max(root.val+left+right, maxPath), max[0])
		int left = helper(root.left, max);
		int right = helper(root.right, max);
		// path: root, root+ left ,root+right
		int maxPath = Math.max(Math.max(left, right) + root.val, root.val);
		// sum 
		max[0] = Math.max(Math.max(root.val+left+right, maxPath), max[0]);
		return maxPath;
	}
	public static void main(String[] args){
		BinaryTreeMaximumPathSum sol =new BinaryTreeMaximumPathSum();
		TreeNode root =  new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		System.out.println("max path sum: "+sol.maxPathSum(root));
		// http://d2dskowxfbo68o.cloudfront.net/wp-content/uploads/tree4.png
		TreeNode root2 =  new TreeNode(10);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(11);
		
		root2.left.left = new TreeNode(20);
		root2.left.right = new TreeNode(1);
		
		root2.right.right = new TreeNode(-25);
		root2.right.right.left = new TreeNode(3);
		root2.right.right.right = new TreeNode(4);
		
		System.out.println("max path sum: "+sol.maxPathSum(root2));		
	}
}
