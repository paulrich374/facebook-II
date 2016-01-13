package NineChapter3BinaryTree;

import java.util.ArrayList;

public class ValidateBST {
	/*validate
	 *  
	 *  INORDER CHECK so next value is always larger than previous value
	 *  
	 *  like divide and conquer just put recursive call into if condition
	 *  recursive call only current compare previous since not sure you have children or not but sure
	 *  I have parent
	 *  
	 *  
	 * */
	private int last = Integer.MIN_VALUE;
	public boolean validate(TreeNode root){
		
		// Zero:null check, end of leaf or itself null=>return true
		if (root == null){
			return true;
		}
		// First: INORDER LEFT check left !validate(root.left), else go to next
		if (!validate(root.left)){
			return false;
		}
		// Second: INORDER UPDATE make sure leftmost node is larger than MIN_VALUE
		if (root.val > last){
			last = root.val;
		}else{
			return false;
		}
		// Third: INORDER RIGHT make sure 
		if (!validate(root.right)){
			return false;
		}
		
		return true;
	}
	/*validate2
	 * 
	 * se pre as null first and use if as last
	 * */
	public boolean validate2(TreeNode root){
		
			ArrayList<Integer> pre = new ArrayList<Integer>(); // Like last
			pre.add(null);// like last = MIN
			return helper(root,pre);
	}	
	/*helper
		    // ZERO:Inorder base case
			//FIRST:  INORDER LEFT boolean left = helper(root.left, pre);// like inorder left
			// SECOND:  INORDER sTOP AND UPADTE LAST/PRE
			// THIRD: INORDER RIGHT

	 * */
	private boolean helper(TreeNode root, ArrayList<Integer> pre  ){
		    // ZERO:Inorder base case
			if (root == null){
				return true;
			}
			// 
			//FIRST:  INORDER LEFT boolean left = helper(root.left, pre);// like inorder left
			if (!helper(root.left, pre)){
				return false;
			}
			// SECOND:  INORDER sTOP AND UPADTE LAST/PRE
			if (pre.get(0) == null || root.val > pre.get(0)){// like current > previous
				pre.set(0, root.val);
			} else {
				return false;
			}
			// THIRD: INORDER RIGHT
			//return left && helper(root.right, pre);//like indeorder right
			if (!helper(root.right, pre)){
				return false;
			}
			return true;
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
		
		ValidateBST sol = new ValidateBST();
		System.out.println(sol.validate(root));
		System.out.println(sol.validate2(root));
		TreeNode root2 = new TreeNode(20);
		root2.left = new TreeNode(12);
		root2.right = new TreeNode(30);
		
		root2.left.left = new TreeNode(10);
		
		root2.right.left = new TreeNode(23);
		root2.right.right = new TreeNode(36);
		
		root2.right.left.left = new TreeNode(21);
		root2.right.left.right = new TreeNode(25);
		root2.right.left.right.left = new TreeNode(28);//WRONG
		root2.right.left.right.right = new TreeNode(26);
		System.out.println(root2.toStringExpression());
		System.out.println(sol.validate(root2));		
		System.out.println(sol.validate2(root2));	
		
		
	}
}
