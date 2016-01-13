package NineChaper6Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;


/*		
 * 
 *      TOPOLOGICAL SORT
 *      1. A DirectedGraph
 *      2. DFS+STACK
 *            call DFS for every vertices
 *      
 		//$$$$$$ USE ONE ARRAYLIST AND ONE ARRAY to achieve LIFO, one array store last index to decrement
 		// the other array store vertex value into corresponding index
      	index.add(g.v-1);
 		int cur = index.get(0);
        res[cur] = i;
        index.set(0, cur-1);
        
        
        ////$$$$$$ USE MAP TO ACHIEVE Digrectedgraph Ajacent list
        // MAP(start, List<end1, end2...>)
 *      
 *      GRAPH(DFS/CLONE)
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
 *      
 *      LinkedList<Integer>[] adj;
 * 		adj = new LinkedList[v];
 *      [5] 0,2 5 point to 0 and 5 point to 2 (start point to end)
 *      [4] 0,1
 *      
 *      Course schedule
 *      4, [[1,0],[2,0],[3,1],[3,2]]
 *      [3,1],[3,2]=>[1] point to 3 ([1]先修 point to [3]後修)
 *                 =>[2] point to 3
 *      course taking order 先修 -> 比修 -> 選修
 *      
 *      
 *      dfs a lot of redundant repeated check
 *          so we want to avoid visited and success and thus we need 3 states
 *          1.those not visited 
 *          2.those already visited and ongoing
 *          3.those already visited and succeed
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
	/*topsort   stack + DFS
	 * 		// First: call DFS for every vertices
			// Second: copy integer from stack to list
	 * */
	/*dfs
	 * 		// First: mark current as visited
			// Second: for loop all neighbors if not visited recurse
			// third: Push current vertex to stack which stores result
	 * */	
	public ArrayList<Integer> topsort(Graph g){
		ArrayList<Integer> res = new ArrayList<Integer>();
		//Zero: null check
		if (g == null )
			return res;
		// Approach# -  count the incoming edges for all vertices 
		//int[] count = new int[g.v];
		//for (int i = 0; i < g.v; i++){
		//	for (int j = 0 ; j < g.adj[i].size();j++)
		//		count[g.adj[i].get(j)]++;
		//}
		
		// Approach# - stack + DFS
		// First: call DFS for every vertices
		
		// In topological sorting, we use a temporary stack.
		Stack stack = new Stack();//#1
		ArrayList<Integer> index = new ArrayList<Integer>();//#2
		index.add(g.v-1);//#2
		int[] resarray = new int[g.v];
		//boolean[] visited = new boolean[g.v];//DFS
		int[] visited = new int[g.v];//DFS
		// Call the recursive helper function to store Topological
        // Sort starting from all vertices one by one
		// We don’t print the vertex immediately, we first recursively call topological sorting for all its adjacent vertices, 
		for (int k = 0 ; k < g.v; k++){
			//if (visited[k] == false){
			if (visited[k] == 0){
				// DFS
				//dfs(g, k, visited, stack);//#1
				dfs(g, k, visited, stack, index, resarray);//#2
			}
		}
		// Second: copy integer from stack to list
		//#1while (stack.empty()==false){
		//#1	res.add((Integer)stack.pop());
		//#1}
		for(int a:resarray){//#2
			res.add(a);
		}
		return res;
	}
	/*dfs
	 * 		// First: mark current as visited
			// Second: for loop all neighbors if not visited recurse
			// third: Push current vertex to stack which stores result
	 * */
	//#1private void dfs(Graph g, int k, boolean[] visited, Stack stack){
	private void dfs(Graph g, int k, boolean[] visited, Stack stack,  ArrayList<Integer> index, int[] resarray){
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
				//dfs(g, w, visited, stack);//#1
				dfs(g, w, visited, stack, index, resarray);//#2
			}
		}
		// third: LIFO (finish neighbors)Push current vertex to stack which stores result
		// http://image.slidesharecdn.com/skienaalgorithm2007lecture12topologicalsortconnectivity-111212074924-phpapp02/95/skiena-algorithm-2007-lecture12-topological-sort-connectivity-18-728.jpg?cb=1323676776#tfbml-data%7B%22iframe_height%22%3A162%2C%22location_url%22%3A%22http%3A%2F%2Fimage.slidesharecdn.com%2Fskienaalgorithm2007lecture12topologicalsortconnectivity-111212074924-phpapp02%2F95%2Fskiena-algorithm-2007-lecture12-topological-sort-connectivity-18-728.jpg%3Fcb%3D1323676776%22%7D
		// the top vertex on the stack always has no incoming edges
		stack.push(new Integer(k));//#1
		int cur = index.get(0);
		resarray[cur] = k;
		index.set(0, cur-1);
	}
	/*dfs
	 *  avoid time limit exceed by memoizing visited state 
	 *  3 states -1(visited),0(not visited),1(visited and success)
	 *  		// ZERO: avoid time limit exceed
				// We don’t print the vertex immediately, we first recursively call topological sorting for all its adjacent vertices, 
				// First: mark current as visited
				// Second: for loop all neighbors if not visited recurse
				// third: LIFO (finish neighbors)Push current vertex to stack which stores result
				// http://image.slidesharecdn.com/skienaalgorithm2007lecture12topologicalsortconnectivity-111212074924-phpapp02/95/skiena-algorithm-2007-lecture12-topological-sort-connectivity-18-728.jpg?cb=1323676776#tfbml-data%7B%22iframe_height%22%3A162%2C%22location_url%22%3A%22http%3A%2F%2Fimage.slidesharecdn.com%2Fskienaalgorithm2007lecture12topologicalsortconnectivity-111212074924-phpapp02%2F95%2Fskiena-algorithm-2007-lecture12-topological-sort-connectivity-18-728.jpg%3Fcb%3D1323676776%22%7D
				// the top vertex on the stack always has no incoming edges
	 * */
	private void dfs(Graph g, int k, int[] visited, Stack stack,  ArrayList<Integer> index, int[] resarray){
		// ZERO: avoid time limit exceed
		if (visited[k] == -1){
			return;
		}
		if (visited[k] == 1){
			return;
		}
		// We don’t print the vertex immediately, we first recursively call topological sorting for all its adjacent vertices, 
		// First: mark current as visited
		visited[k] = -1;
		// Second: for loop all neighbors if not visited recurse
		Iterator<Integer> it =g.adj[k].iterator();
		while (it.hasNext()){
			int w = it.next();
			if (visited[w] == 0){
				// NOTE: if true here, 
				//visited[w] =true;
				//dfs(g, w, visited, stack);//#1
				dfs(g, w, visited, stack, index, resarray);//#2
			}
		}
		// third: LIFO (finish neighbors)Push current vertex to stack which stores result
		// http://image.slidesharecdn.com/skienaalgorithm2007lecture12topologicalsortconnectivity-111212074924-phpapp02/95/skiena-algorithm-2007-lecture12-topological-sort-connectivity-18-728.jpg?cb=1323676776#tfbml-data%7B%22iframe_height%22%3A162%2C%22location_url%22%3A%22http%3A%2F%2Fimage.slidesharecdn.com%2Fskienaalgorithm2007lecture12topologicalsortconnectivity-111212074924-phpapp02%2F95%2Fskiena-algorithm-2007-lecture12-topological-sort-connectivity-18-728.jpg%3Fcb%3D1323676776%22%7D
		// the top vertex on the stack always has no incoming edges
		visited[k] = 1;
		stack.push(new Integer(k));//#1
		int cur = index.get(0);
		resarray[cur] = k;
		index.set(0, cur-1);
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
		Long start = System.nanoTime();
		System.out.println(" a list of edges[end,start],"+g+"\n ordering list: "+sol.topsort(g));
		Long estimate = System.nanoTime() - start;
		System.out.println("Take Time:"+estimate);//976000 // 1354000 //888000
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