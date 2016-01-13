package NineChapter3BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * 
         	  1
            /   \
           2     3
         /  \    / \
        4    5  23  36
      /  \
     21  25
        / \
       24  26
           1,2,4,21,25,24,26,5,3,23,36    
Preorder: [1, 2, 4, 21, 25, 24, 26, 5, 3, 23, 36]
        
 * */
/*preorder
 *    1. list
 *    2. stack
 *    3. cur
	// First: while( cur != null || !stack.isEmpty() ) since no leaf is null but stack has nodes
		// Second:  
		//         cur != null => add, push left
		//         cur == null => pop, right
 * */
public class BTPreorder {
	/*preorder
	 *    1. list
	 *    2. stack
	 *    3. cur
		// First: while( cur != null || !stack.isEmpty() ) since no leaf is null but stack has nodes
			// Second:  
			//         cur != null => add, push, left
			//         cur == null => pop, right
	 * */
	public ArrayList<Integer> preorder(TreeNode root){
		ArrayList<Integer> res = new ArrayList<Integer>();
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode cur = root;
		// First: cur != null || !stack.isEmpty()
		while (cur != null || !stack.isEmpty()){
			// Second:  
			//         cur != null => add, push left
			//         cur == null => pop, right
			if (cur != null){
				res.add(cur.val);
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				cur = cur.right;
			}
		}
		return res;
	}
	public static void main(String[] args){
		BTPreorder sol = new BTPreorder();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		
		root.right.left = new TreeNode(23);
		root.right.right = new TreeNode(36);
		
		root.left.left.left = new TreeNode(21);
		root.left.left.right = new TreeNode(25);
		
		root.left.left.right.left = new TreeNode(24);
		root.left.left.right.right = new TreeNode(26);
		System.out.println("Preorder: "+sol.preorder(root));
	}
}
