package NineChaper6Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
/*
 *			 取代堆結構
 *           1. Stack => ArrayList(store idnex) + Array(store element)
 *    	     取代指向圖結構
 *    		 2. Directed graph adjacent list => Map adjacent list<Integer, ArrayList<Itneger>>
 * 			 沒有進來的邊先
 * 			 慢慢把其他的邊 變成沒有進來的邊
 * 			https://leetcode.com/problems/course-schedule/
 * 			https://leetcode.com/problems/course-schedule-ii/
 * 			 3. BFS iterative to replace DFS recursive\
 * 				| calculate the number of incoming edges for each vertex
 * 				| Queue zero incoming edge vertex
 * 				| BFS :update the count of zero incoming edge vertex and pop element into result array
 * 				|     decrement other vertex's incoming edge from this current pop index
 * 				|     if incoming edge count == 0, add into Queue and increment the count of zero incoming edge vertex
 * 				|     finally, every index should all become zero incoming edge
 * */

/*topsort   stack + DFS
 * 		// First: call DFS for every vertices
		// Second: copy integer from stack to list
 * */
/*dfs
* 		// First: mark current as visited
	// Second: map.containsKey(k) anf then for loop all neighbors if not visited recurse
	// third: Push current vertex to stack which stores result
* */ 
public class TopologySortingArrayCourseScheduleII {
		/*topsort   stack + DFS
		 * 		// First: call DFS for every vertices
				// Second: copy integer from stack to list
		 * */
	/*dfs
	 * 		// First: mark current as visited
			// Second: map.containsKey(k) anf then for loop all neighbors if not visited recurse
			// third: Push current vertex to stack which stores result
	 * */ 
		public ArrayList<Integer> topsort(int number, int[][] pre){
			ArrayList<Integer> res = new ArrayList<Integer>();
			//Zero: null check
			if (pre == null || number < 0)
				return res;
			// Approach# -  count the incoming edges for all vertices 
			//int[] count = new int[g.v];
			//for (int i = 0; i < g.v; i++){
			//	for (int j = 0 ; j < g.adj[i].size();j++)
			//		count[g.adj[i].get(j)]++;
			//}
			
			// Approach# - one array and one arraylist + DFS
	        // $$$ ZERO: use MAP to replace DiGRAPH adjacent list
			HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
			for (int i = 0 ; i < pre.length; i++){
				ArrayList<Integer> list = null;
				if (map.containsKey(pre[i][1])){
					list = map.get(pre[i][1]);					
				}else{
					list= new ArrayList<Integer>();
				}
				list.add(pre[i][0]);
				map.put(pre[i][1], list);
			}
			// First: call DFS for every vertices			
			ArrayList<Integer> index = new ArrayList<Integer>();//#2
			index.add(number-1);//#2
			int[] resarray = new int[number];
			boolean[] visited = new boolean[number];//DFS
			for (int k = 0 ; k < number; k++){
				if (visited[k] == false){
					// DFS
					dfs(map, k, visited, index, resarray);//#2
				}
			}
			// Second: copy integer from stack to list
			for(int a:resarray){//#2
				res.add(a);
			}
			return res;
		}
		/*dfs
		 * 		// First: mark current as visited
				// Second: map.containsKey(k) anf then for loop all neighbors if not visited recurse
				// third: Push current vertex to stack which stores result
		 * */  
		//private void dfs(Graph g, int k, boolean[] visited,  ArrayList<Integer> index, int[] resarray){
		 private void dfs( HashMap<Integer, ArrayList<Integer>> map, int k, boolean[] visited,  ArrayList<Integer> index, int[] resarray){	
			// First: mark current as visited
			visited[k] = true;
			// Second: for loop all neighbors if not visited recurse
			//Iterator<Integer> it =g.adj[k].iterator();
			//while (it.hasNext()){
			//	int w = it.next();
			//	if (!visited[w]){
			//		// NOTE: if true here, 
			//		//visited[w] =true;
			//		//dfs(g, w, visited, stack);//#1
			//		dfs(g, w, visited, stack, index, resarray);//#2
			//	}
			//}			
			if (map.containsKey(k)){
				ArrayList<Integer> list = map.get(k);
				for (int w : list){
					if (!visited[w]){
						// NOTE: if true here, 
						//visited[w] =true;
						dfs(map, w, visited, index, resarray);//#2
					}					
				}
			}
			// third: LIFO (finish neighbors)Push current vertex to arraylist which stores result
			int cur = index.get(0);
			resarray[cur] = k;
			index.set(0, cur-1);
		}
		/*topsortIterative (make all vertex with ZERO INCOMING EDGE and recoed the order)
		 * 			// Zero:
					// First: calculate the number of incoming edges for each vertex
					// Second: 
					// 			1.Queue zero incoming edge vertex if( pCounter[i] == 0 ) queue.add(i);
					//			2.update the count of zero incoming edge vertex(numNoPre = queue.size()) and pop element into result array (j=0)
					//			3.for loop decrement other vertex's incoming edge from this current pop index
					//				if incoming edge count == 0, add into Queue and increment the count of zero incoming edge vertex
					//			4.finally, every index should all become zero incoming edge
		 * 
		 * */ 
		public int[] topsortIterative(int number, int[][] pre){
		    int[] res= new int[number];
			// Zero:
			if (pre == null){
				return res;
			}
			if (number == 0){
				return res;
			}
			if (pre.length == 0){
				for (int i = 0 ; i < number; i++){
					res[i] = i;
				}
				return res;
			}
			// First: calculate the number of incoming edges for each vertex
			int[] incomingedgecount= new int[number];
			for (int j = 0 ; j < pre.length; j++){
					incomingedgecount[pre[j][0]]++;
			}
			// Second: 
			// 			1.Queue zero incoming edge vertex if( pCounter[i] == 0 ) queue.add(i);
			//			2.update the count of zero incoming edge vertex(numNoPre = queue.size()) and pop element into result array (j=0)
			//			3.for loop decrement other vertex's incoming edge from this current pop index
			//				if incoming edge count == 0, add into Queue and increment the count of zero incoming edge vertex
			//			4.finally, every index should all become zero incoming edge
			//1.
			LinkedList<Integer> queue =new LinkedList<Integer>();
			for (int k = 0 ; k < number; k++){
				if (incomingedgecount[k] == 0){
					queue.add(k);
				}
			}
			// 2.
			int numZeroIncomingEdge = queue.size();
			int start = 0;
			while (!queue.isEmpty()){
				int pop = queue.remove();// zero incoming, must be an out-going. ONLY pre store relation
				res[start++] = pop;
				// 3
				for (int m = 0 ; m < pre.length; m++){
					if (pre[m][1] == pop){
						incomingedgecount[pre[m][0]]--;// decrement incoming
						// NOTE: must be inside pre[m][1] == pop
						if (incomingedgecount[pre[m][0]] == 0){
							numZeroIncomingEdge++;
							queue.add(pre[m][0]);
						}					
					}

				}
			}
			//4.
			if (numZeroIncomingEdge == number){
				return res;
			}else{
				return new int[0];
			}
		}
		public static void main(String[] args){
			//Graph g = new Graph(6);
			//g.addEdge(5,2);
			//g.addEdge(5,0);
			//g.addEdge(4,0);
			//g.addEdge(4,1);
			//g.addEdge(2,3);
			//g.addEdge(3,1);
			int[][] pre =  new int[][]{{2,5},{0,5},{0,4},{1,4},{3,2},{1,3}};
			int number = 6;
			TopologySortingArrayCourseScheduleII sol = new TopologySortingArrayCourseScheduleII();
			System.out.println(" a list of edges[end,start],"+Arrays.toString(pre)+"\n ordering list: "+sol.topsort(number,pre));
			System.out.println("\n ordering list Iterative: "+ Arrays.toString(sol.topsortIterative(number, pre)));
			//Graph g2 = new Graph(4);
			//g2.addEdge(0,1);
			//g2.addEdge(0,2);
			//g2.addEdge(1,3);
			//g2.addEdge(2,3);
			int[][] pre2 =  new int[][]{{1,0},{2,0},{3,1},{3,2}};
			int number2 = 4;
			System.out.println(" a list of edges[end,start],"+Arrays.toString(pre2)+"\n ordering list: "+sol.topsort(number2, pre2));	
			System.out.println("\n ordering list Iterative: "+ Arrays.toString(sol.topsortIterative(number2, pre2)));
		
		}
}
