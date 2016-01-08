package NineChaper6Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;


/*		  
 * 
 *      DFS/CLONE
 *      
 *      DFS
 *      !visited
 *         visited[neightbor] = true
 *         helper(neightbor)
 *      CLONE
 *      !map.constainsKey(neightbor) 
 *      	clone = new GraphNode(neightbor)
 *      	map.put(neightbor,clone)
 *      	helper(neightbor,)   
 * 
 *      LinkedList<Integer>[] adj;
 * 		adj = new LinkedList[v];
 *      [5] 0,2 5 point to 0 and 5 point to 2 (start point to end)
 *      [4] 0,1
 *      
 *      Course schedule
 *      4, [[1,0],[2,0],[3,1],[3,2]]
 *      [3,1],[3,2]=>[1] point to 3 (start point to end)
 *                 =>[2] point to 3
 *      course taking order 先修 -> 比修 -> 選修
 * 
 *      
 * http://www.geeksforgeeks.org/topological-sorting/
 * https://leetcode.com/problems/course-schedule/ 
 * https://leetcode.com/problems/course-schedule-ii/     
 * 
 * 
For LinkedList<E>
get(int index) is     			O(n)
add(E element) is 				O(1)
add(int index, E element) is 	O(n)
remove(int index) is 			O(n)
Iterator.remove() is 			O(1) <---$$$$ main benefit of LinkedList<E>
ListIterator.add(E element) is 	O(1) <---$$$ main benefit of LinkedList<E>
For ArrayList<E>

get(int index) is 				O(1) <---$$$ main benefit of ArrayList<E>
add(E element) is 				O(1) amortized, but O(n) worst-case since the array must be resized and copied
add(int index, E element) is 	O(n - index) amortized, but O(n) worst-case (as above)
remove(int index) is 			O(n - index) (i.e. removing last is O(1))
Iterator.remove() is 			O(n - index)
ListIterator.add(E element) is 	O(n - index)      
 *      
 *      
 *      TopologySorting
 * 	   SUMMARY:  linear ordering of vertices in Directed Acyclic Graph (DAG)
 *     WHEN:     ordering, dfs detect cycle
 *     HOW:      DFS with an extra stack
 *               NOTE: The first vertex in topological sorting is always a vertex with in-degree as 0
 *                (a vertex with no in-coming edges).
 *     VS DFS:  In DFS, we start from a vertex, we first print it and then recursively call DFS for its adjacent vertices. 
 *     			In topological sorting, we need to print a vertex before its adjacent vertices
 *              In topological sorting, we use a temporary stack.
 *               We don’t print the vertex immediately, we first recursively call topological sorting for all its adjacent vertices, 
 *               then push it to a stack. 
 *               Finally, print contents of stack. 
 *               Note that a vertex is pushed to stack only when all of its adjacent vertices (and their adjacent vertices and so on) are already in stack.
 *               5 -> 0  <- 4 -> 1  <- 3 
 *               |                     | 
 *               ---> 2---------------->
 *       		DFS of the above graph is “5 2 3 1 0 4″
 *      		TopologySorting           “5 4 2 3 1 0″ or  “4 5 2 3 1 0″
 *     COMPLEXITY: O(V+E)

 * */
public class TopologySorting {
	public ArrayList<Integer> topsort(Graph g){
		ArrayList<Integer> res = new ArrayList<Integer>();
		//Zero: null check
		if (g == null )
			return res;
		// First:
		// Approach# -  count the incoming edges for all vertices 
		int[] count = new int[g.v];
		for (int i = 0; i < g.v; i++){
			for (int j = 0 ; j < g.adj[i].size();j++)
				count[g.adj[i].get(j)]++;
		}
		// Approach# - DFS recursive
		// First: stack + DFS
		// In topological sorting, we use a temporary stack.
		Stack stack = new Stack();
		boolean[] visited = new boolean[g.v];//DFS
		// Call the recursive helper function to store Topological
        // Sort starting from all vertices one by one
		// We don’t print the vertex immediately, we first recursively call topological sorting for all its adjacent vertices, 
		for (int k = 0 ; k < g.v; k++){
			if (visited[k] == false){
				// DFS
				dfs(g, k, visited, stack);
			}
		}
		while (stack.empty()==false){
			res.add((Integer)stack.pop());
            //System.out.print( + " ");
		}
		return res;
	}
	/*dfs
	 * 		// First: mark current as visited
			// Second: for loop all neighbors if not visited recurse
			// third: Push current vertex to stack which stores result
	 * 
	 * */
	private void dfs(Graph g, int k, boolean[] visited, Stack stack){
		// We don’t print the vertex immediately, we first recursively call topological sorting for all its adjacent vertices, 
		// First: mark current as visited
		visited[k] = true;
		// Second: for loop all neighbors if not visited recurse
		Iterator<Integer> it =g.adj[k].iterator();
		while (it.hasNext()){
			int w = it.next();
			if (!visited[w]){
				// NOTE: if true here, 
				//visited[w] =true;
				dfs(g, w, visited, stack);
			}
		}
		// third: Push current vertex to stack which stores result
		// then push it to a stack.
		stack.push(new Integer(k));
	}
	public static void main(String[] args){
		Graph g = new Graph(6);
		g.addEdge(5,2);
		g.addEdge(5,0);
		
		g.addEdge(4,0);
		g.addEdge(4,1);
		
		g.addEdge(2,3);
		g.addEdge(3,1);
		
		TopologySorting sol = new TopologySorting();
		System.out.println(" a list of edges[end,start],"+g+"\n ordering list: "+sol.topsort(g));
		Graph g2 = new Graph(4);
		g2.addEdge(0,1);
		g2.addEdge(0,2);
		
		g2.addEdge(1,3);
		
		g2.addEdge(2,3);
		System.out.println(" a list of edges[end,start],"+g2+"\n ordering list: "+sol.topsort(g2));	
	}
}
class Graph{
	int v;
	LinkedList<Integer>[] adj;
	Graph(int v){
		this.v = v;
		// NOTE: java.lang.NullPointerException
		adj = new LinkedList[v];
		for (int i = 0 ; i < this.v; i++){
			this.adj[i] = new LinkedList<Integer>();
		}
	}
	public void addEdge(int v, int w){
		this.adj[v].add(w);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < this.v; i++){
			if (this.adj[i].size() == 0) {
				continue;
			}
			for (int j = 0; j < this.adj[i].size();j++){
				sb.append("["+this.adj[i].get(j));
				sb.append(","+i);
				sb.append("]");
			}
			sb.append(",");
		}
		return sb.toString();
	}
}