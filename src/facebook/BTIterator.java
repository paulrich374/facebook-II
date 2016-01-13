package facebook;

import java.util.Stack;



/*
 * 							 Inorder - next smallest number in the BST
 *                           http://codingweihung.blogspot.com/2015/10/tree-traversal.html
 * public interface InOrderBinaryTreeIterator extends Iterator<Integer> { 
   Returns the next integer a in the in-order traversal of the given binary tree.
   * For example, given a binary tree below,
   *       4
   *      / \
   *     2   6
   *    / \ / \
   *   1  3 5  7
   * InOrderBinaryTreeIterator   the outputs will be 1, 2, 3, 4, 5, 6, 7. 
   * PreOrderBinaryTreeIterator  the outputs will be 4, 2, 1, 3, 6, 5, 7. 
     PostOrderBinaryTreeIterator the outputs will be 1, 3, 2, 5, 7, 6, 4.
     http://www.yaldex.com/game-programming/FILES/03fig12.gif 
  public Integer next(); 

   Return true if traversal has not finished; otherwise, return false.
   
  public boolean hasNext();
}
 * */
/*
 * 	// First(BTIterator): pushLeftChildren
 * 	// Second(hasNext): check stack, already something on stack since pushLeftChildren at teh very first
	// Third(next): pop up from the stack since previously pushLeftChildren to null and change right to pushLeftChildren 

 * */
public class BTIterator {
	private Stack<TreeNode> stack;
	
	
	
	/*===================InOrder==================*/
	// First: pushLeftChildren
	public BTIterator(TreeNode root){
		// First: like helper(root.left)
		//while (root != null){
		//	stack.push(root);
		//	root = root.left;
		//}
		stack = new Stack<TreeNode>();
		pushLeftChildren(root);
	}
	// Second: check stack, already something on stack since pushLeftChildren at teh very first
	public boolean hasNext(){
		return !stack.isEmpty();
	}
	// Third: pop up from the stack since previously pushLeftChildren to null and change right to pushLeftChildren 
	public int next(){
		TreeNode res = stack.pop();
		pushLeftChildren(res.right);
		return res.val;
	}
	private void pushLeftChildren(TreeNode root){
		// First: like helper(root.left)
		while (root != null){
			stack.push(root);
			root = root.left;
		}		
	}	
	
	
	
	/*===================PreOrder==================*/	
	// First: need put a node for next	
	// second: check stack, already has something inside
	// Third:  right first and left(Last In) later
	//        since pop up left(Last In) first 
	//         we check when we put it on to stack
	
	// First: need put a node for next
	public BTIterator(TreeNode root, boolean pre){
		stack = new Stack<TreeNode>();		
		if (root != null){
			stack.push(root);
		}
	}
	// second: check stack, already has something inside
	public boolean hasNextPre(){
		return !stack.isEmpty();
	}
	// Third: every node counts so next node would be right first and left(Last In) later
	//        since pop up left(Last In) first 
	//        since we don't check null in the beginning pop up we check when we put it on to stack
	public int nextPre(){
		TreeNode res = stack.pop();
		if (res.right != null) stack.push(res.right);
		if (res.left != null) stack.push(res.left);
		return res.val;
	}	
	
	
	
	/*===================PostOrder==================*/	
	//http://www.yaldex.com/game-programming/FILES/03fig12.gif
	// First: need put a node for next
	// second: check stack, already has something inside

	// First: need put a node for next. pushLeftChildrenPost first
	private TreeNode pre = null;
	public BTIterator(TreeNode root, boolean pre, boolean post){
		stack = new Stack<TreeNode>();		
		pushLeftChildrenPost(root);
	}
	// second: check stack, already has something inside
	public boolean hasNextPost(){
		return !stack.isEmpty();
	}
	// Third: root last 
	//        case1: (avoid root) res.left == pre && pre != null
	//               explore right and pop up when stop means left most children
	//        case2: (not root), we pop up directly       
	public int nextPost(){
		TreeNode res = stack.peek();
		if (pre!= null && res.left == pre){
			pushLeftChildrenPost(res.right);
			res = stack.pop();
			pre = res;
		}else {
			res = stack.pop();
			pre = res;
		}
		return res.val;
	}
	private void pushLeftChildrenPost(TreeNode root){
		// First: like helper(root.left)
		while (root != null){
			stack.push(root);
			if (root.left != null)
				root = root.left;
			else 
				root = root.right;
		}	
		//while (root.right != null){//  java.lang.NullPointerException ROOT ALREADY NULL
		//	stack.push(root.right);
		//	root = root.right.left;			
		//}
	}	
	
	
	
	public static void main(String[] args){
		final TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		System.out.print(root.toStringExpression());
		System.out.println();
		BTIterator sol = new BTIterator(root);
		System.out.print("nextIn ");
		while(sol.hasNext()){
			System.out.print(sol.next()+", ");
		}
		System.out.println();
		BTIterator sol2 = new BTIterator(root,true);
		System.out.print("nextPre ");
		while(sol2.hasNextPre()){
			System.out.print(sol2.nextPre()+", ");
		}
		System.out.println();
		BTIterator sol3 = new BTIterator(root,false,true);
		System.out.print("nextPost ");
		while(sol3.hasNextPost()){
			System.out.print(sol3.nextPost()+", ");
		}	
		
		System.out.println();
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
		System.out.print(root2.toStringExpression());
		System.out.println();
		BTIterator sol4 = new BTIterator(root2);
		System.out.print("nextIn ");
		while(sol4.hasNext()){
			System.out.print(sol4.next()+", ");
		}		
		System.out.println();
		BTIterator sol6 = new BTIterator(root2,false,true);
		System.out.print("nextPost ");
		while(sol6.hasNextPost()){
			System.out.print(sol6.nextPost()+", ");
		}	
		
		TreeNode rootpre = new TreeNode(1);
		rootpre.left = new TreeNode(2);
		rootpre.right = new TreeNode(3);
		
		rootpre.left.left = new TreeNode(4);
		rootpre.left.right = new TreeNode(5);
		
		rootpre.right.left = new TreeNode(23);
		rootpre.right.right = new TreeNode(36);
		
		rootpre.left.left.left = new TreeNode(21);
		rootpre.left.left.right = new TreeNode(25);
		
		rootpre.left.left.right.left = new TreeNode(24);
		rootpre.left.left.right.right = new TreeNode(26);
		
		System.out.println();
		System.out.print("nextPre ");
		BTIterator solpre = new BTIterator(rootpre,true);		
		while(solpre.hasNextPre()){
			System.out.print(solpre.nextPre()+", ");
		}		
		//Preorder: [1, 2, 4, 21, 25, 24, 26, 5, 3, 23, 36]

	}
}
