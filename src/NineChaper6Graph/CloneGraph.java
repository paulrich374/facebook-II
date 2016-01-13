package NineChaper6Graph;
/*

 
 * */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CloneGraph {
	/*cloneIterative
	 * 	// Zero: null check
		// First: 
		//       first node psh stack and map
		//       stack is not empty stack push neighbor and map neighbor
		//       if (!map.containsKey(neighbor)){ => new clone node and map put and stack push
		//       Whatever map constains or not => map.get(current).neighbors.add(map.get(neighbor));
	 * 
	 * // NOTE:  #0,1,2,2,2#1#2 id current not node
	 * */
	public UndigraphNode cloneIterative(UndigraphNode node){
		// Zero: null check
		if(node == null)
			return node;	
		// First: 
		//       first node push stack and map
		//       stack is not empty stack push neighbor and map neighbor
		//       if (!map.containsKey(neighbor)){ => new clone node and map put and stack push
		//       Whatever map contains or not => map.get(current).neighbors.add(map.get(neighbor)); 
		LinkedList<UndigraphNode> stack = new LinkedList<UndigraphNode>();
		stack.push(node);
		HashMap<UndigraphNode,UndigraphNode> map = new HashMap<UndigraphNode,UndigraphNode>();
		UndigraphNode clonenode = new UndigraphNode(node.label);
		map.put(node, clonenode);		
		
		while (!stack.isEmpty()){
			UndigraphNode current = stack.pop();
			for (UndigraphNode neighbor: current.neighbors){
				if (!map.containsKey(neighbor)){
					UndigraphNode cloneneighbor = new UndigraphNode(neighbor.label);
					stack.push(neighbor);
					map.put(neighbor, cloneneighbor);
				}
				// NOTE:  #0,1,2,2,2#1#2 id current not node
				map.get(current).neighbors.add(map.get(neighbor));
			}
		}
		return map.get(node);
	}
	public UndigraphNode clone(UndigraphNode node){
		// Zero: null check
		if(node == null)
			return null;
		// First: clone node's neighbors and append clone neighbors to clone parents
		//        clone first node and map first node
		HashMap<UndigraphNode, UndigraphNode> map = new HashMap<UndigraphNode, UndigraphNode>();
		// Second:clone first node's neighbor and map them
		helper(node,map);
		return map.get(node);
	}
	/*helper
	 *  // create a new clone node before for loop neighbor
	 *  //   for loop neighbor
	 *  //    !map.containsKey(neighbor)
	 *        helper()
		//       Whatever map contains or not => map.get(current).neighbors.add(map.get(neighbor)); 
     
	 * */
	private void helper(UndigraphNode node, HashMap<UndigraphNode,UndigraphNode> map){
		// Base Case
		// NOTE: violate ! first node will return 
		//if (map.containsKey(node)){
		//	return;
		//}
		// create a new clone node before for loop neighbor
		UndigraphNode clonenode = new UndigraphNode(node.label);
		map.put(node, clonenode);		
		// Recursive case
		//   for loop neighbor
		for(UndigraphNode neighbor: node.neighbors){
			//if (map.containsKey(neighbor)){
				// get node's clone's neighbors and add this neighbor's clone 
				//map.get(node).neighbors.add(map.get(neighbor));
			//} else {
				// create a clone for this meighbor
				// put into map as original, clone
				// add this clone neighbor into current node's clone's neighbors
				//UndigraphNode cloneneighbor = new UndigraphNode(neighbor.label);
				//map.put(neighbor, cloneneighbor);
				//map.get(node).neighbors.add(cloneneighbor);
			//}
			// STACKOVERFLOW
			// create a clone for this meighbor
			// put into map as original, clone
			// add this clone neighbor into current node's clone's neighbors			
			//if (!map.containsKey(neighbor)){
			//	UndigraphNode cloneneighbor = new UndigraphNode(neighbor.label);
			//	map.put(neighbor, cloneneighbor);				
			//}
			// get node's clone's neighbors and add this neighbor's clone 
			
			//if (map.containsKey(neighbor)){
			//	map.get(node).neighbors.add(map.get(neighbor));
			//}else{
			//	helper(neighbor, map);
			//	map.get(node).neighbors.add(map.get(neighbor));
			//}
				
			if (!map.containsKey(neighbor)){	
				helper(neighbor, map);
			}
			map.get(node).neighbors.add(map.get(neighbor));
		}
	}
	public static void main(String[] args){
		UndigraphNode zero = new UndigraphNode(0);		
		UndigraphNode one = new UndigraphNode(1);
		UndigraphNode two = new UndigraphNode(2);
		zero.neighbors.add(one);
		zero.neighbors.add(two);
		one.neighbors.add(two);
		two.neighbors.add(two);
		CloneGraph sol = new CloneGraph();
		System.out.println("Original: "+zero.printGraph());
		System.out.println("Clone: "+sol.clone(zero).printGraph());
		System.out.println("Clone Iterative: "+sol.cloneIterative(zero).printGraph());
	}
}

class UndigraphNode{
	int label;
	ArrayList<UndigraphNode> neighbors;// doesn't know exactly size so we use list
	UndigraphNode(int label){
		this.label = label;
		neighbors = new ArrayList<UndigraphNode>();
	}
	public String toString(){
		// Zero : null check
		if (this == null ){
			return "";
		}		
		StringBuilder sb = new StringBuilder(); 
		sb.append("#"+this.label);
		for (UndigraphNode neighbor: this.neighbors){
				sb.append(","+neighbor.label);
		}	
		return sb.toString();		
	}
	public String printGraph(){
		// Zero : null check
		if (this == null ){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		HashMap<UndigraphNode, Integer> map = new HashMap<UndigraphNode, Integer>();
		//map.put(this, 1);
		LinkedList<UndigraphNode> queue = new LinkedList<UndigraphNode>();
		queue.offer(this);
		while (!queue.isEmpty()){
			UndigraphNode node = queue.poll();
			if (map.containsKey(node)){
				continue;
			}
			// queue pool node want to print and map
			sb.append(node.toString());
			map.put(node, 1);			
			for (UndigraphNode neighbor: node.neighbors){
				queue.offer(neighbor);
			}	
		}
		return sb.toString();
	}
}
/*
class Digraph{
	String s;
	Digraph[] children;
	Digraph(String s){
		this.s = s;
	}
}
*/
/*
 * 
 * 		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(this.label, 1);		
		LinkedList<UndigraphNode> queue = new LinkedList<UndigraphNode>();
		queue.offer(this);
		while(!queue.isEmpty()){
			UndigraphNode current = queue.poll();
			for (UndigraphNode neighbor: current.neighbors){
				// A new node
				if (map.containsKey(neighbor.label)){
					sb.append("#"+this.label);
				// current node's neighbors	
				} else {
					sb.append(","+neighbor.label);
					queue.offer(neighbor);
					map.put(neighbor.label, 1);
				}
			}
			System.out.println(queue);
		}	
 * 
 * */
