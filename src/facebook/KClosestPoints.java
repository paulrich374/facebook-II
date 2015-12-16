package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/*
 * Given an array containing N points 
 * find the K closest points to the origin in the 2D plane. 
 * You can assume K is much smaller than N and N is very large.
 * http://www.zrzahid.com/k-closest-points/
 */

/* // Approach#1 Heap
* // T:O(k logk)  T:(n-k logk)
* 			// First: inside the loop, move k inside the heap
* 			// Second: inside the loop, for larger than k, compare with criterion and poll,offer the heap
* // max-heap comparator
* // Approach#2 Quick Select
* // klcosestQuickSelect
	  		// First: prepare pass down in-place array
	  		// Second:  set 0 and len-1 as boundary
	// quickSelect Iterative
	  		// First: outer left boundary less than outer right boundary
	  		// second: inner left boundary less than inner right boundary. 
			//         To move inward to swap start with end if it is greater than mid's value, end--
			//         Otherwise, it is smaller just start++ to check next
			// Third: after inner check, check last start if greater than and if so, start--
			// Fourth: if start >= k, outer right as start. Otherwise, start < k, outer left = start+1
* // quickSelect Recursive
* 			// First : left boundary less than right boundary 
			// Second: get pivot and get currentKth and compare with k, =k stop; >k, pivot-1 and k; <k, pivot+1 and k - currentKth
* // partition
* 			// First : end as pivot's value
			// Second: loop to swap any less than pivot's value with SWAPINDEX (SWAPINDEX = start-1)
* 			// Third : SWAPINDEX++, swap end with SWAPINDEX and return SWAPINDEX
*/
public class KClosestPoints {
	/* Approach#1 Heap T:O(n logk), S:O(k)*/
	/* kclosestHeap
	  		// First: inside the loop, move k inside the heap
			// Second: inside the loop, for larger than k, compare with criterion and poll,offer the heap
	*/
	//public List<Point> kclosestHeap(List<Point> points, int k){
	public Point[] kclosestHeap(List<Point> points, int k){
		//List<Point> res = new ArrayList<Point>();
		Point[] res = new Point[k];
		if (points == null || points.size()==0){
			return res;
		}
		// override comparator, find k closest(smallest), use max-heap, when len < max, offer
		// S:O(k)
		PriorityQueue<Point> pq = new PriorityQueue<Point>(k,new MaxPointComparator());
		/*
		// T:O(k logk)
		for (int i = 0 ; i < k; i++){
			//System.out.println(points.get(i).toString());
			pq.offer(points.get(i));
		}
		// T:(n-k logk)
		for (int j = k; j < points.size(); j++){
			Point origin = new Point(0,0);
			if (points.get(j).getDist(origin) < pq.peek().getDist(origin) ){
				System.out.println(pq.peek()+" being replaced by "+points.get(j));
				pq.poll();
				pq.offer(points.get(j));
			}
		}
		*/
		for (int i = 0 ; i < points.size(); i++){
			Point origin = new Point(0,0);
			// First: inside the loop, move k inside the heap
			if (i < k) {
				pq.offer(points.get(i));
			// Second: inside the loop, for larger than k, compare with criterion and poll,offer the heap
			} else if (points.get(i).getDist(origin) < pq.peek().getDist(origin) ){
				pq.poll();
				pq.offer(points.get(i));				
			}
		}		
		/*
		while(!pq.isEmpty())
			res.add(pq.poll());
		*/
		return pq.toArray(res);
		//return res;
	}
	/* max-heap comparator*/
	public static class MaxPointComparator implements Comparator<Point>{
		public int compare(Point p1, Point p2)
		{
			Point origin = new Point(0,0);
			return p2.getDist(origin) - p1.getDist(origin);
		}
	}
	/* Approach#2 Quick Select T:O(n),S:O(1) in place
	 * klcosestQuickSelect
	 * 		// First: prepare pass down in-place array
	 * 		// Second: set 0 and len-1 as boundary 
	 */
	public Point[] klcosestQuickSelect(List<Point> points, int k){
		Point[] res = new Point[k];
		if (points == null || points.size()==0){
			return res;
		}		
		// First: prepare pass down in-place array
		Point[] pointsArr = new Point[points.size()];
		points.toArray(pointsArr);
		//quickSelect(pointsArr, 0, points.size()-1, k);
		// Second: set 0 and len-1 as boundary 
		quickSelectIterative(pointsArr, 0, points.size()-1, k);
		System.arraycopy(pointsArr, 0, res, 0, 4);
		return res;
	}
	/* quickSelectIterative
	 * range 0 ~ n, second time 0 ~ start or start+1 ~ n until has k elements on the left part of index k.
	 * It takes O(n), since it just keep narrowing down within n
	  		// First: outer left boundary less than outer right boundary
	  		// second: inner left boundary less than inner right boundary. 
					// To move inward to swap start with end if it is greater than mid's value, end--
					// Otherwise, it is smaller just start++ to check next
			// Third: after inner check, check last start if greater than and if so, start--
 			// Fourth: if start >= k, outer right as start. Otherwise, start < k, outer left = start+1
	 */
	private void quickSelectIterative(Point[] points, int l, int r, int k){
		// First: outer left boundary less than outer right boundary
		while (l < r){
			Point origin = new Point(0,0);
			int start = l;
			int end = r;
			Point pivotPoint = points[(start+end)>>1];
			// second: inner left boundary less than inner right boundary. 
			//         To move inward to swap start with end if it is greater than mid's value, end--
			//         Otherwise, it is smaller just start++ to check next
			while (start < end){
				if (points[start].getDist(origin) >= pivotPoint.getDist(origin)){// larger, swap
					Point temp = points[end];
					points[end] = points[start];
					points[start] = temp;
					end--;
				} else{// smaller, stay there
					start++; 
				}
			}
			// Third: after inner check, check last start if greater than and if so, start--
			if (points[start].getDist(origin) > pivotPoint.getDist(origin))
				start--;//previous smaller but now larger
			// Fourth: if start >= k, outer right as start. Otherwise, start < k, outer left = start+1
			if (k <= start){
				r = start;
			}else{
				l = start+1;
			}
		}
	}
	/* quickSelect
	 * operate within n. It takes O(n)
	  		// First: left boundary less than right boundary
	 		// Second: get pivot and get currentKth and compare with k, =k stop; >k, pivot-1 and k; <k, pivot+1 and k - currentKth
	 */
	private void quickSelect(Point[] points, int l, int r, int k){
		// First: left boundary less than right boundary 
		if (l < r){
			// Second: get pivot and get currentKth and compare with k, =k stop; >k, pivot-1 and k; <k, pivot+1 and k - currentKth
			int pivot = partition(points, l, r);
			int currentKth = pivot-l+1;
			if (currentKth == k){
				return;
			} else if (currentKth > k){
				quickSelect(points, l, pivot-1, k);
			} else {
				quickSelect(points, pivot+1, r, k-currentKth);				
			}
		}
	}
	/* partition
		// First: end as pivot's value
		// Second: any less than pivot's value, swap with SWAPINDEX
		// Third: SWAPINDEX++, swap end with SWAPINDEX and return SWAPINDEX
	 * */
	private int partition(Point[] points, int start, int end){
		// First: end as pivot's value
		Point pivotPoint = points[end];
		Point origin = new Point(0,0);
		int swapIndex = start - 1; 
		// Second: any less than pivot's value, swap with SWAPINDEX
		for (int cur = start; cur < end; cur++){
			if (points[cur].getDist(origin) < pivotPoint.getDist(origin)){
				swapIndex++;
				Point temp = points[cur];
				points[cur] = points[swapIndex];
				points[swapIndex] = temp;
			}
		}
		// Third: SWAPINDEX++, swap end with SWAPINDEX and return SWAPINDEX
		swapIndex++;
		Point temp = points[swapIndex];
		points[swapIndex] = pivotPoint;
		points[end] = temp;
		return swapIndex;
	}
	public static void main(String[] args){
		KClosestPoints sol = new KClosestPoints();
		List<Point> points = new ArrayList<Point>();
		points.add(new Point(1,1));
		points.add(new Point(1,-1));
		points.add(new Point(2,2));
		points.add(new Point(2,-2));
		points.add(new Point(-2,-2));
		points.add(new Point(-2,2));
		points.add(new Point(-1,-1));
		points.add(new Point(-1,1));	
		points.add(new Point(3,-3));
		points.add(new Point(-4,-4));		
		//List<Point> resHeap =  sol.kclosestHeap(points, 4);
		Point[] resHeap =  sol.kclosestHeap(points, 4);
		for (Point p:resHeap){
			System.out.println("kclosestHeap"+p);
		}
		Point[] resQuickSelect = sol.klcosestQuickSelect(points, 4);
		for (Point o:resQuickSelect){
			System.out.println("klcosestQuickSelect"+o);
		}		
	}
}
// public must be defined in its own file
class Point{
	int x;
	int y;
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getDist(Point p){
		return (x-p.x)*(x-p.x)+(y-p.y)*(y-p.y);
	}
	@Override
	public String toString(){
		//StringBuilder sb = new StringBuilder();
		//sb.append(this.x);
		//sb.append(",");
		//sb.append(this.y);
		//return sb.toString();
		return "(" + x + "," + y + ")";
	}
	
}
