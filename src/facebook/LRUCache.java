package facebook;

import java.util.HashMap;
/*
 *  
 *   LRU least recently used cache system
 *   HashMap + Doubly Linked List
 *   HashMap
 *   - int count 
 *   - int capacity
 *   Doubly linked list
 *   - first, last null
 *   get()
 *   	1. hash O(1)
 *   		case 1-1 if null, return 0
 *   		case 1-2 if not null, 
 *   				delete(cur): remove current from its prev and its next O(1)
 *   				setHead(cur): O(1)
 *   				return cur.val
 *   set()
 *   	1. hash O(1)
 *   		case 1-1 if null, 
 *   				 case 1-1-1 check count== capacity -1 
 *                              if no capacity
 *                                 int lastVal = last.val
 *                                 delete(last): 
 *                                 map.remove(last.val)
 *                                 count--;
 *                                 new node cur
 *                                 setHead(cur):
 *                                 map.put(key,cur.val)
 *                                 count++
 *                              if with capacity
 *                                 new node cur
 *                                 setHead(cur):
 *                                 map.put(key, cur.val)
 *                                 count++
 *   		case 1-2 if not null,
 *   				change value
 *   				delete(cur): remove current from its prev and its next O(1)
 *   				setHead(cur): O(1)
 *    
 * */
public class LRUCache {
	
	HashMap<Integer, ListNode> map = new HashMap<Integer, ListNode>();
	ListNode first, last;
	int capacity;
	int count;
	public LRUCache(int capacity){
		first = null;
		last = null;
		this.capacity = capacity;// Exception in thread "main" java.lang.NullPointerException
		count = 0;
	}
	public int get(int key){
		// First: Hash
		// case 1-1  null
		if (!map.containsKey(key)){
			return 0;
		}
		// case 1-2 not null
		else {
			ListNode cur = map.get(key);
			// Delete
			delete(cur);
			// SetHead
			setHead(cur);
			return cur.val;
		}
	}
	/*
	 * 
	 *             	map.remove(lastKey);//NOTE: sol.get(1): 1
	 *                     		count--;// NOTE:
            	map.put(key, cur);// NOTE:
	 * */
	public void set(int key, int value){
		// First: hash
		// case 1-1  null
		if (!map.containsKey(key)){
			// case 1-1-1 check count== capacity -1 
			// if withOut capacity
            if (count == this.capacity){
            	 // Delete(): last
            	int lastKey = last.val;
            	delete(last);
            	map.remove(lastKey);//NOTE: sol.get(1): 1
        		count--;// NOTE:
            	 // setHead():
            	ListNode cur = new ListNode(value);
            	setHead(cur);
            	map.put(key, cur);// NOTE:
        		count++;
            }
            // if with capacity
            else {
    			// SetHead():    
            	ListNode cur = new ListNode(value);
            	setHead(cur);
            	map.put(key, cur);     
        		count++;
            }

		}
		// case 1-2 not null
		else {
			// update
		    ListNode cur = map.get(key);
		    cur.val = value;
       	 	// Delete(): current
		    delete(cur);
       	 	// setHead():
		    setHead(cur);
		}		
	}
	/*delete
	 * 		// First: temp prev and next
	 * 
	 * */
	private void delete(ListNode cur){
		// First: temp prev and next
		ListNode prev = cur.prev;
		ListNode next = cur.next;
		if (prev == null){
			next.prev= null;
			first = next;
		} else {
			prev.next = next;
		}
		if (next == null){
			prev.next = null;
			last = prev;
		} else {
			next.prev = prev;
		}
	}
	/*setHead
	 * 		// First: temp next as first to check null or not 
	 * 			next.prev = cur;// NOTE:

	 * */
	private void setHead(ListNode cur){
		// First: temp next as first to check null or not 
		ListNode next = first;
		if (next == null){
			first = cur;
			last = cur;
		}else {
			next.prev = cur;// NOTE:
			cur.prev = null;
			cur.next = next;
			first = cur;
		}
	}
	public static void main(String[] args){
		LRUCache sol = new LRUCache(10);
		sol.set(1,1);
		sol.set(2,2);
		sol.set(3,3);
		sol.set(4,4);
		sol.set(5,5);
		sol.set(6,6);
		sol.set(7,7);	
		sol.set(8,8);	
		sol.set(9,9);	
		sol.set(10,10);
		System.out.println("First:"+sol.first.val+". Last: "+sol.last.val);
		sol.set(11,11);			
		System.out.println("First:"+sol.first.val+". Last: "+sol.last.val);
		sol.set(12,12);			
		System.out.println("First:"+sol.first.val+". Last: "+sol.last.val);		
		System.out.println("sol.get(1): "+sol.get(1));
		System.out.println("First:"+sol.first.val+". Last: "+sol.last.val);		
		System.out.println("sol.get(3): "+sol.get(3));
		System.out.println("First:"+sol.first.val+". Last: "+sol.last.val);	
		System.out.println("sol.get(4): "+sol.get(4));
		System.out.println("First:"+sol.first.val+". Last: "+sol.last.val);			
	}
}
class ListNode{
	int val;
	ListNode prev, next;
	ListNode(int val){
		prev = null;
		next = null;
		this.val = val;
	}
}
