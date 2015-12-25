package facebook;
/*
 * Interweave a linked list. Do it in linear time and constant space.

Input: A->B->C->D->E
Output: A->E->B->D->C
http://buttercola.blogspot.com/2014/11/facebook-interweave-linked-list.html
 * 
 * */
public class InterweaveaLinkedList {
	/*interweaved T:O(n/2),S:O(1)
	 * 		// Zero:null check
	 * 		// First: find the middle and reverse the last half
			//        middle : runner.next != null && runner.next.next != null
			//                 last half = walker.next
			//                 cut half walker.next = null;
			//        reverse the last half: 
			//                 second = reverse(second);
	 * 		// Second: merge two list
			//         while (first != null && second != null)
			//         temp next first: firstnext = first.next, secondnext = second.next
			//         interweaved: first.next = second, second.next = firstnext
	    	//         iterate: first = firstnext, second = secondnext
	 * */
	public void interweaved(ListNodeChar root){
		// Zero:null check
		if (root == null)
			return;
		// First: find the middle and reverse the last half
		//        middle : runner.next != null && runner.next.next != null
		//                 last half = walker.next
		//                 cut half walker.next = null;
		//        reverse the last half: 
		//                 second = reverse(second);		
		ListNodeChar runner = root;
		ListNodeChar walker = root;
		while(runner.next != null && runner.next.next != null) {
			runner = runner.next.next;
			walker = walker.next;
		}
		ListNodeChar second = walker.next;
		walker.next = null;
		second = reverseRecursive(second);		
		// 1-2-3-4
		// r
		// w 
		//     r
		//   w
		//     se (4->3->null)
		// 1-2-3-4-5
		// r
		// w
		//     r    
		//   w
		//         r 
		//     w
		//       se (5->4->null)
		
		
		// Second: merge two list
		//         while (first != null && second != null)
		//         temp next first: firstnext = first.next, secondnext = second.next
		//         interweaved: first.next = second, second.next = firstnext
	    //         iterate: first = firstnext, second = secondnext
		//		     second           E->D->null   or (4->3->null)
		ListNodeChar first = root;// A->B->C->null or 1->2->null)
		while (first != null && second != null){
			ListNodeChar firstnext = first.next;
			ListNodeChar secondnext = second.next;
			first.next = second;
			second.next = firstnext;
			first = firstnext;
			second = secondnext;
		}
		return;
	}
	/*reverse T:O(n),n the length of list,S:O(1)
	 * 	return end;
	 * */
	private ListNodeChar reverse(ListNodeChar root){
		ListNodeChar p = root;
		ListNodeChar q;
		ListNodeChar end = null;
		while (p != null){
			q = p.next;
			p.next = end;
			end = p;
			p = q;
		}
		return end;
	}
	/*reverseRecursive T:O(n),n the length of list,S:O(n)
	 * 	return rest;//first rest point to end node
	 *  second.next = first;//original second (right now is reversed list's end point to previous node)
	 * */
	private ListNodeChar reverseRecursive(ListNodeChar root){
		// Base Case:
		if (root.next == null){
			return root;
		}
		ListNodeChar first = root;
		ListNodeChar second = root.next;
		first.next = null;
		ListNodeChar rest =	reverseRecursive(second);// 
		second.next = first;
		return rest;
	}	
	/*interweavedRecursive T:O(n)
	 * 		// Zero:null check
		// First: find the length of list 

	 * */
	public ListNodeChar interweavedRecursive(ListNodeChar root){
		// Zero:null check
		if (root == null)
			return null;
		// First: find the length of list 
		int length = 0;
		ListNodeChar p = root;
		while (p != null){
			length++;
			p = p.next;
		}
		// Second: recursive indexing into center of list
		ResultNode res = helper(root, length);
		return res.ret1;
	}	
	/**helper T:O(n/2), S:O(n/2)
  		//Base Case: length = 0(the combined node), return new ListNodeChar(root.c), root.next, true
		//           length = 1(the end node), new ListNodeChar(root.c), root.next, false
		// Recursive Case: 
		//             if ret2 appendable(ret1 is the end),(ListNodeChar next = res.ret2.next;) root.next = res.ret2, root.next.next = res.ret1;
		//                                  return  ResultNode(root, next, true)
		//             if ret2 not appendable(ret1 is appended node), root.next = res.ret1; PASS DOWN ret2 
		//                                  return  ResultNode(root,res.ret2,true);
	 */
	private ResultNode helper(ListNodeChar root, int length){
		//Base Case: length = 0(the combined node), return new ListNodeChar(root.c), root.next, true
		//           length = 1(the end node), new ListNodeChar(root.c), root.next, false
		if (length == 1){
			return new ResultNode(new ListNodeChar(root.c), root.next, true);
		}
		if(length == 0){
			return new ResultNode(new ListNodeChar(root.c), root.next, false);
		}
		// Recursive Case: 
		//             if ret2 appendable(ret1 is the end),(ListNodeChar next = res.ret2.next;) root.next = res.ret2, root.next.next = res.ret1;
		//                                  return  ResultNode(root, next, true)
		//             if ret2 not appendable(ret1 is appended node), root.next = res.ret1; PASS DOWN ret2 
		//                                  return  ResultNode(root,res.ret2,true);
		ResultNode res = helper(root.next, length-2);
		//System.out.println("Debug - ret1="+res.ret1+", ret2="+res.ret2);
		if (res.appendable){
			ListNodeChar next = res.ret2.next;
			root.next = res.ret2;
			root.next.next = res.ret1;
			return new ResultNode(root, next, true);
		} 
		//ListNodeChar next = res.ret2;
		root.next = res.ret1;
		return new ResultNode(root,res.ret2,true);
	}
   public static void main(String[] args){
	   
	   ListNodeChar root = new ListNodeChar('A');
	   root.next = new ListNodeChar('B');
	   root.next.next = new ListNodeChar('C');
	   root.next.next.next = new ListNodeChar('D');
	   root.next.next.next.next = new ListNodeChar('E');
	   //DeepCopy ListNodeChar root2 = new ListNodeChar
	   System.out.println("root before interweaved: "+root);
	   InterweaveaLinkedList sol = new InterweaveaLinkedList();
	   sol.interweaved(root);
	   System.out.println("root after interweaved: "+root);
	   System.out.println();
	   
	   ListNodeChar root2 = new ListNodeChar('A');
	   root2.next = new ListNodeChar('B');
	   root2.next.next = new ListNodeChar('C');
	   root2.next.next.next = new ListNodeChar('D');
	   root2.next.next.next.next = new ListNodeChar('E');	
	   System.out.println("root before interweavedRecursive: "+root2);
	   ListNodeChar res2 = sol.interweavedRecursive(root2);
	   System.out.println("root after interweavedRecursive: "+res2);	
	   System.out.println();
	   
	   ListNodeChar root3 = new ListNodeChar('A');
	   root3.next = new ListNodeChar('B');
	   root3.next.next = new ListNodeChar('C');
	   root3.next.next.next = new ListNodeChar('D');
	   System.out.println("root before interweavedRecursive: "+root3);
	   ListNodeChar res3 = sol.interweavedRecursive(root3);
	   System.out.println("root after interweavedRecursive: "+res3);	
	   System.out.println();
   }
}
class ResultNode{
	ListNodeChar ret1;
	ListNodeChar ret2;
	boolean appendable;
	ResultNode(ListNodeChar ret1, ListNodeChar ret2, boolean appendable){
		this.appendable = appendable;
		this.ret1 = ret1;
		this.ret2 = ret2;
	}
}
class ListNodeChar{
	char c;
	ListNodeChar next;
	ListNodeChar(char c){
		this.c = c;
		next = null;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		ListNodeChar node = this;
		while(node != null){
			sb.append(node.c);
			sb.append("->");
			node = node.next;
		}
		sb.append("null");
		return sb.toString();
	}
}
/*
 * 	 *  h(A->B->C->D->E->null,5)
	 *  	h(B->C->D->E->null,3)
	 *  		h(C->D->E->null,1)
	 *  		ret1, ret2 = return C,D( appendable)
	 *      B.next = D( appendable)->C, D.next = E
	 *      ret1, ret2 = B->D->C, E( appendable)
	 *  A.next = E( appendable)-> B->D->C    	
	 *  
	 *  h(A->B->C->D->null,4)
	 *  	h(B->C->D->null,2) 
	 *  		h(C->D->null,0)
	 *  		ret1,ret2 = return C,D = C.next(not appendable)
	 *      B.next = C, D(not appendable)
	 *      ret1, ret2 = B->C,D( appendable)
	 *  A.next = D( appendable)-> B->C  
 * 
 * */
