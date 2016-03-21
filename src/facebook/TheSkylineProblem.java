package facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 
 * The Skyline problem is like this: Given a series of building's position and height (start, end, height), 
 * return the combined skyline.
 * 
 * For instance, 
 * the dimensions of all buildings in Figure A are recorded as: 
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
15   |         __
12   |        |    |____     
10   |      __|_ | |__    |      ___ 
     |      | |  | |    |   |      |  _| _   
 8   |      | |  | |    |   |  |   |  |    |
     |______|_|_ | |__  |_ |___|___|  |_  |_
            2 3 7  9   12   15  20  24
                                        19
For instance, the skyline in Figure B should be represented as:
[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
15   |          ____
12   |         |    |______     
10   |       _|            |        _______ 
     |      |              |       |      | _   
 8   |      |              |       |         |
     |______|__      __ ___|_     _|___ __ _ |
              2 3 7  9   12   15  20  24
                                        19
 * 
 * http://homecox.com/pub/f2013-12-13-2012_skyline.php
 * 
 * Merge Intervals
 *  Time:O(nlogn+n), Space:O(1)
 * https://leetcode.com/problems/merge-intervals/
 * Insert Interval
 * https://leetcode.com/problems/insert-interval/
 *                   a                        a
 *  ____ _____     _____                   __________         
 *    a    b          ____                    ____
 *                      b                       b
 *  b.start>a.end    b.start <= a.end     b.start <= a.end 
 *                        merge                 merge            
 * Each round we can remove exactly one interval, 
 * so the algorithm ends, with the correct result. 
 * Obviously, this is a nlog(n) algorithm.


 * */



/*skyline  O(nlogn+n)
 * 		// First: sort the interval

 * 		// Second: compare interval with previous interval to merge, split
	// Second: compare interval with previous interval to merge, split
	//         if(prev.end < later.start)far apart, add prev and prev = later
	//         (prev.end >= later.start) overlap, else if (prev.end <= later.end ) later cover last
	//                                            height ==, merge, prev = later
	//                                            prev shorter, split prev(prev.e=later.s) and add in res, prev = later
	//                                            later shorter, split(later.s = prev.e) and add in res, prev = later.SORT since later change
	//         (prev.end >= later.start) overlap, else    (prev.end > later.end ) prev cover last
	//                                            later shorter, merge in, prev = later
	//                                            prev shorter, split into 3, 1st prev.e=later.s and add in res, 2nd still, 3rd b.e+a.e put in interval and SORT, prev =later
	
 * */


/*skylineII  O(nlogn+n)
 * 		// First: split building into two edge and sort
	// Second: create a max-heap with first height 0, we offer height and poll height
	// Third: for every edge, - put in(new skyline), + remove it(old skyline), if current max height not the same as before we add in
	// init 0, max height so far, if change, skyline redraw		//
 * */
public class TheSkylineProblem {
	public class IntervalComparator implements Comparator<Interval>{
		@Override
		public int compare(Interval a, Interval b){
			if (a.start != b.start) return a.start - b.start;
			return a.end - b.end;
		}
	}
	
	
	
	/*skyline
	 * 		// First: sort the interval

	 * 		// Second: compare interval with previous interval to merge, split
		// Second: compare interval with previous interval to merge, split
		//         if(prev.end < later.start)far apart, add prev and prev = later
		//         (prev.end >= later.start) overlap, else if (prev.end <= later.end ) later cover last
		//                                            height ==, merge, prev = later
		//                                            prev shorter, split prev(prev.e=later.s) and add in res, prev = later
		//                                            later shorter, split(later.s = prev.e) and add in res, prev = later.SORT since later change
		//         (prev.end >= later.start) overlap, else    (prev.end > later.end ) prev cover last
		//                                            later shorter, merge in, prev = later
		//                                            prev shorter, split into 3, 1st prev.e=later.s and add in res, 2nd still, 3rd b.e+a.e put in interval and SORT, prev =later
		
	 * */
	//public List<int[]> skyline(List<Interval> intervals){
	public List<Interval> skyline(List<Interval> intervals){
		//List<int[]> res=  new ArrayList<int[]>();
		List<Interval> resInterval = new ArrayList<Interval>();
		if (intervals == null || intervals.size() ==0){
			return resInterval;
		}		
		// First: sort the interval
		Collections.sort(intervals, new IntervalComparator());
		System.out.println(intervals);
		Interval a = intervals.get(0);
		// Second: compare interval with previous interval to merge, split
		//         if(prev.end < later.start)far apart, add prev and prev = later
		//         (prev.end >= later.start) overlap, else if (prev.end <= later.end ) later cover last
		//                                            height ==, merge, prev = later
		//                                            prev shorter, split prev(prev.e=later.s) and add in res, prev = later
		//                                            later shorter, split(later.s = prev.e) and add in res, prev = later.SORT since later change
		//         (prev.end >= later.start) overlap, else    (prev.end > later.end ) prev cover last
		//                                            later shorter, merge in, prev = later
		//                                            prev shorter, split into 3, 1st prev.e=later.s and add in res, 2nd still, 3rd b.e+a.e put in interval and SORT, prev =later
		
		for (int i = 1; i < intervals.size(); i++){
			Interval b = intervals.get(i);
			if (a.end < b.start){
				resInterval.add(a);
				a = b;
			} else if (a.end <= b.end ){ // a.end >= b.start => overlap, b cover last
				if (a.height == b.height){//merge a into b
					b.start = a.start;
					a = b;
				} else if (a.height < b.height){//split a since a shorter
					a.end = b.start;
					resInterval.add(a);
					a = b;
				} else { // split b since b shorter, b change "sort"
					b.start = a.end;
					resInterval.add(a);
					a = b;
					Collections.sort(intervals, new IntervalComparator());
				}
			} else {// a.end >= b.start => overlap, a cover last
				if (a.height > b.height){// merge b into a
					b.start = a.start;
					b.end = a.end;
					a = b;
				}else { //split into 3, a push in, b renew, c insert and sort
					Interval c = new Interval(b.end, a.end, a.height);
					a.end = b.start;
					resInterval.add(a);
					intervals.add(c);
					a= b;
					Collections.sort(intervals, new IntervalComparator());	
				}
			}
		}
		resInterval.add(a);
		return resInterval;		
	}
	
	
	
	
	/*skylineII
	 * 把每一个building拆成两个edge，
	 * 一个入一个出。
	 * 所有的edge加入到一个list中。
	 * 再对这个list进行排序，排序顺序为：
	 * 如果两个边的position不一样，那么按pos排，
	 * 否则根据edge是入还是出来排。
	 * 根据position从前到后扫描每一个edge，
	 * 将edge根据是入还是出来将当前height加入或者移除heap。
	 * 再得到当前最高点来决定是否加入最终结果。

	 * */
	public class pqComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer a, Integer b){
			return b-a;//descend, max-heap		
		}
	}	
	/*skylineII
	 * 		// First: split building into two edge and sort
		// Second: create a max-heap with first height 0, we offer height and poll height
		// Third: for every edge, - put in(new skyline), + remove it(old skyline), if current max height not the same as before we add in
		// init 0, max height so far, if change, skyline redraw		//
	 * */
	public List<int[]> skylineII(List<Interval> intervals){
		List<int[]> res =  new ArrayList<int[]>();
		if (intervals == null || intervals.size() ==0){
			return res;
		}
		// First: split building into two edge and sort
		List<int[]> height = new ArrayList<int[]>();
		for (Interval item:intervals){
			height.add(new int[]{item.start,-item.height});//start, -height
			height.add(new int[]{item.end,item.height});// end, height
		}
		Collections.sort(height, new Comparator<int[]>(){
			public int compare(int[] a, int[] b){
				if (a[0] != b[0]) return a[0] - b[0];
				return a[1] - b[1];// start BEFORE end, height small BEFORE height large 
				// BOTH START -10,-5=> -10->-5
				// BOTH END 10, 5=>5->10 
			}
		});
		// Second: create a max-heap with first height 0, we offer height and poll height
		// 根据position从前到后扫描每一个edge
		// 将edge根据是入还是出来将当前height加入或者移除heap
		// 再得到当前最高点(max-heap)来决定是否加入最终结果。
		
		// 把所有的turning points 放在一起，根据coordination从小到大sort 。
		// 再用max-heap, 把所有的turning points扫一遍，遇到start turning point, 
		// 把 volume放入max-heap. 
		// 遇到end turning point，把对应的volume从max-heap中取出。
		// max-heap的max 值就是对应区间的最大volume		
		// Input : [2,-10][3,-15][5,-12][7,15][9,10][12,12][15,-10][19,-8][20,10][24,8]
		// Result: [2 10],[3 15],[7 12],[12 0],[15 10],[20 8],[24, 0]		
		// Event{true,0,200}, Event{false,10,200}
		// ==> Event{0,200}, Event{10,-200}
		// 按照time排序，time相同，is_in = false的优先
		// 然后你扫过去， is_in=true的你就加mem,is_in=false的你就-mem.每个事件点，
		// 你会加或减一次，每加或减一次后，就check是不是超过总的
		PriorityQueue<Integer> pq= new PriorityQueue<Integer>(10,new pqComparator());
		// Avoid empty heap, still has ZERO value
		pq.offer(0);
		int prev = 0;
		// Third: for every edge, - put in(new skyline), + remove it(old skyline), if current max height not the same as before we add in
		// init 0, max height so far, if change, skyline redraw
		for (int[] item:height){
			// START, ADD
			if (item[1] < 0 )
				pq.offer(-item[1]);
			// END, REMOVE
			else 
				pq.remove(item[1]);
			int max = pq.peek();
			if (prev != max){
				res.add(new int[]{item[0], max});
				prev = max;
			}
		}
		return res;
	}	
	
	
	
	
	
	
	public static void main(String[] args){
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(2, 9, 10));
		intervals.add(new Interval(3, 7, 15));
		intervals.add(new Interval(15, 20, 10));
		intervals.add(new Interval(19, 24, 8));		
		intervals.add(new Interval(5, 12, 12));

		System.out.println(intervals);
		TheSkylineProblem sol = new TheSkylineProblem();
		//List<int[]> res= sol.skyline(intervals);
		//for (int[] item:res){
		//	System.out.println(item[0]+" "+item[1]);
		//}
		List<Interval> res = sol.skyline(intervals);
		System.out.println("skyline "+res);		
		
		
		List<Interval> intervals2 = new ArrayList<Interval>();
		intervals2.add(new Interval(0, 1, 3));		
		
		List<int[]> res2= sol.skylineII(intervals2);
		System.out.print("skylineII ");
		for (int[] item:res2){
			System.out.print("["+item[0]+","+item[1]+"]");
			System.out.print(", ");
		}		
	}
}
class Interval {
	int start;
	int end;
	int height;
	public Interval(int s, int e, int h){
		this.start = s;
		this.end =e;
		this.height = h;
	}
	public String toString(){
		return "["+start+","+end+","+height+"]";
	}
}
