package facebook;
/*
 * Facebook: Check if two binary trees are Isomorphic
 * 
 * Two trees are called isomorphic if one of them can be obtained from other by a series of flips, 
 * i.e. by swapping left and right children of a number of nodes. 
 * Any number of nodes at any level can have their children swapped. 
 * Two empty trees are isomorphic.
 * 
 * 
 * For example, following two trees are isomorphic 
 * with following sub-trees flipped: 
 * 2 and 3, 
 * NULL and 6, 
 * 7 and 8.
*      _______1______                    _______1______
      /              \                  /              \
   ___2__          ___3__            ___3__          ___2_
  /      \        /      \          /      \        /      \
  4      _5_     6                          6       4      _5_                        
        /   \                                             /   \
 *     7     8                                           8     7
 * 
http://buttercola.blogspot.com/2014/11/facebook-check-if-two-binary-trees-are.html
http://www.geeksforgeeks.org/tree-isomorphism-problem/
 */
/*
 * We simultaneously traverse both trees. 
 * Let the current internal nodes of two trees being traversed be n1 and n2 respectively. 
 * There are following two conditions for subtrees rooted with n1 and n2 to be isomorphic.
1) Data of n1 and n2 is same.
2) One of the following two is true for children of n1 and n2
……a) (Not FLipped) Left child of n1 is isomorphic to left child of n2 and right child of n1 is isomorphic to right child of n2.
isIsoMorphic(root1.left, root2.left) && (isIsoMorphic(root1.right, root2.right)
……b) (Flipped) Left child of n1 is isomorphic to right child of n2 and right child of n1 is isomorphic to left child of n2.
isIsoMorphic(root1.left, root2.right) && (isIsoMorphic(root1.right, root2.left))
 * Time Complexity: The above solution does a traversal of both trees. 
 * So time complexity is O(m + n) 
 * where m and n are number of nodes in given trees.
 * */


/* Scramble String
 *  great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t          
 * https://leetcode.com/problems/scramble-string/
 * Symmetric Tree 
 * helper( root1.left, root2.right ) && helper(root1.right, root2.left)
 *      if ( root1 == null && root2 == null )
            return true;
        if ( root1 == null || root2 == null )
            return false;
        if ( root1.val != root2.val )
            return false;
 * https://leetcode.com/problems/symmetric-tree/
 * Recover Binary Search Tree
 * 简单而言，第一个逆序点要记录，最后一个逆序点要记录，最后swap一下
 * https://leetcode.com/problems/recover-binary-search-tree/
 * 
 * Same Tree
 * Subtree
 * 根字符串有关的题十有八九可以用DP来做，那么难点就在于如何找出递推公式。
 * */
public class IsomorphicBinaryTree {
	public boolean Isisomorphic(TreeNode root1, TreeNode root2){
		// Zero: base case. all null, either null(not equivalent), not equal(not equivalent)
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		if (root1.val != root2.val)
			return false;
		// First: exactly same or symmetric at some level
		return (Isisomorphic(root1.left, root2.left) && Isisomorphic(root1.right, root2.right)) ||
				(Isisomorphic(root1.left, root2.right)&&Isisomorphic(root1.right, root2.left));
	}
	public static void main(String[] args){
		IsomorphicBinaryTree sol = new IsomorphicBinaryTree();
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		root1.left.left = new TreeNode(4);
		root1.left.right = new TreeNode(5);
		root1.right.left = new TreeNode(6);
		root1.left.right.left = new TreeNode(7);
		root1.left.right.right = new TreeNode(8);
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(3);
		root2.right = new TreeNode(2);		
		root2.left.right = new TreeNode(6);
		root2.right.left = new TreeNode(4);
		root2.right.right = new TreeNode(5);
		root2.right.right.left = new TreeNode(7);
		root2.right.right.right = new TreeNode(8);
		System.out.println(sol.Isisomorphic(root1, root2));
	}
}
