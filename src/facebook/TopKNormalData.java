package facebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/*
 *      LIKE K Closest Points
 *      Enough main memory to accommodate all words
 *      How to find the k most frequent words from a file

 *     
 *  0).上面的第8题：一个文本文件，大约有一万行，每行一个词，
    要求统计出其中最频繁出现的前10个词。其解决方法是：
    用trie树统计每个词出现的次数，时间复杂度是O(n*le)（le表示单词的平准长度），
    然后是找出出现最频繁的前10个词。
    
    
    1).有一个1G大小的一个文件，里面每一行是一个词，词的大小不超过16个字节，内存限制大小是1M。
    返回频数最高的100个词。这个数据具有很明显的特点，词的大小为16个字节，
    但是内存只有1M做hash明显不够，所以可以用来排序。内存可以当输入缓冲区使用。
 * 
 * 
 * your : 3
well : 3
and : 4
to : 4
Geeks : 6
http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/
http://stackoverflow.com/questions/4574041/read-next-word-in-java
http://stackoverflow.com/questions/5078314/isnt-the-size-of-character-in-java-2-bytes
http://stackoverflow.com/questions/5841665/override-abstract-method-compare * */
/*Approach#1 Hash  T:O(n log n)
 *           Memory needed : key:word 16 Characters*2B = 32B+ Value 1 Integer:4B, (key,value) => 36B
 *           36B*number of words = total memory
 * Space:O(n)          
 * 
 * Finally, traverse through the hash table and return the k words with maximum counts

 * sort the HashMap values
 * T:O(n log n)
 *
 * */ 
/*Approach#2 Trie and Heap  T:O(n *m)Trie, T:O(n log k)Heap
 *           Trie memory needed: (12B+26*12B)*number of words* owrd length
 *           Heap memory needed: k*(12B+12B)
 * Space: O(ALPHABET_SIZE * key_length * N) where N is number of keys in trie.
 * 
 * top k most frequent = top k largest count ?
 * Min-heap with k size, every incoming count if larger than min count then replace min with the incoming one and do the heapify.
 * T:O(n log k)
 * How to get the count?
 * Trie
 * T:O(n *m), m = average length of word
 * 1. insert every word into Trie
 * 2. simultaneously insert into Heap
 * */

/*TopKWords
 * 	// Zero: null and negative check
	// First: read the file stream by using Scanner 
	// Second: create and INIT a Trie and MinHeap to store the word
	// Third: insert each word into Heap and Trie
 * */
/* [Trie] 
 * insertTrieAndHeap T:O(26*m), where m is the length of word
 * 	//Zero: check lazy init
	// First: insert word into trie and update or init count
	// Second: insert word into minHeap
 * */
/* [Heap] Insert word into minHeap
 * insertInMinHeap
 * 	// First:  case1:  word is already in minHeap
 * 						// NOTE: heap also has its frequency, update as well
	// Second: case2: word is not present and heap is not full
	//         LIKE K個方法
		// NOTE: indexHeap initialize as count index
	// Third:  case3: word is not present and heap is full  
	//               new word's frequency > root's frequency (min/least frequency word), 
	//               replace root with new word
	//               LIKE N-K個方法
 */
/*不能一次Ｋ個 需一個一個 所以不能用之前先Ｋ個在剩下的N-K個方法來做
 * 所以需考慮飽不飽和問題*/
// [Heap] Build a heap from existing array, EVERYTIME insert a new word
/*buildMinHeap
 * 		// First: for loop every index to do the heapify
 *      //        i = (minHeap.COUNT-2) / 2
 * */
/* [Heap] minHeapify
 * 		// First: create left index and right index to compare with smallest(==current) index
	// //                注意ＣＯＵＮＴ不是ＣＡＰＡＣＩＴＹ
 * 		// Second: if smallest change, we swap two nodes and minHepaify to smallest
	//         NOTE: Update the corresponding index in Trie node  
	 
 * */
public class TopKNormalData {

	/*TopKWords
	 * 	// Zero: null and negative check
		// First: read the file stream by using Scanner 
		// Second: create a Trie and MinHeap to store the word
		// Third: insert each word into Heap and Trie
	 * */
	public void TopKWords(String path, int k){
		// Zero: null and negative check
		if (path == null || k <= 0)
			return;
		// First: read the file stream by using Scanner 
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		// Second: create a Trie and MinHeap to store the word
		MinHeap minHeap = new MinHeap(k);
		TrieNode root = new TrieNode();
		/* 		//Zero: check lazy init. WRONG with
		// NOTE: INITIALIZATION		
		if (root == null)
			root = new TrieNode();
			//root = newTrieNode();*/
		while (sc.hasNextLine()){
			Scanner sc2 = new Scanner(sc.nextLine());
			while (sc2.hasNext()){
				String word = sc2.next();
				// Third: insert each word into Heap and Trie
				insertTrieAndHeap(word, root, minHeap);
			}
		}	
		// Third: print minHeap Top K
		System.out.println("Approach#2 - Trie+Heap");
		for (int i =  minHeap.capacity -1 ; i >= 0; i--)
			System.out.println(minHeap.array[i].word +": "+ minHeap.array[i].frequency);
	}
	public void TopKWordsHash(String path, int k){
		// Zero: null and negative check
		if (path == null || k <= 0)
			return;
		// First: read the file stream by using Scanner 
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		// Second: create a HashMap to store the word
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		while (sc.hasNextLine()){
			Scanner sc2 = new Scanner(sc.nextLine());
			while (sc2.hasNext()){
				String word = sc2.next();
				// HashMap to store the word
				if (map.containsKey(word)){
					map.put(word, map.get(word)+1);
				}else{
					map.put(word, 1);
				}
			}
		}	
		// Third: Sort by Values
		//List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();
		//for (Map<String, Integer>.Entry e:map.entrySet()){
		//	list.add(e);
		//}
		List list = new ArrayList(map.entrySet());
		Collections.sort(list, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				Entry e1 = (Entry)o1;
				Entry e2 = (Entry)o2;
				return (int)e2.getValue() - (int)e1.getValue();
			}
		});
		// Third: print HashMap Top K
		System.out.println("Approach#1 - HashMap");
		for (int i = 0 ; i < k; i++)
			System.out.println(((Entry) list.get(i)).getKey() +": "+ ((Entry) list.get(i)).getValue());
	}	
	/*[Trie] insertTrieAndHeap T:O(26*m), where m is the length of word
	 * 	//Zero: check lazy init
		// First: insert word into trie and update or init count
		// Second: insert word into minHeap
	 * */
	private void insertTrieAndHeap(String word, TrieNode root, MinHeap minHeap){
		// First: insert word into trie and update or init count
		TrieNode t = root;
		TrieNode[] children = root.children;
		word = word.toLowerCase();
		for (int i = 0; i < word.length(); i++ ){
			char c = word.charAt(i);
			if (children[c - 'a'] == null){
				children[word.charAt(i) - 'a'] = new TrieNode();
			}	
			t = children[c - 'a'];
			children = t.children;
		}	
		// t.isLeaf = true; // USED TO BE
		if (t.isLeaf) { // NOTE: update or init count
			t.frequency++;
		} else {
			t.isLeaf = true;
			t.frequency = 1;
		}
		// Second: insert word into minHeap
		insertInMinHeap(minHeap, t, word);
	}
	/* [Heap] Insert word into minHeap
	 * insertInMinHeap
	 * 	// First:  case1:  word is already in minHeap
	 * 						// NOTE: heap also has its frequency, update as well
		// Second: case2: word is not present and heap is not full
		//         LIKE K個方法
			// NOTE: indexHeap initialize as count index
		// Third:  case3: word is not present and heap is full  
		//               new word's frequency > root's frequency (min/least frequency word), 
		//               replace root with new word
		//               LIKE N-K個方法
	 */
	/*不能一次Ｋ個 需一個一個 所以不能用之前先Ｋ個在剩下的N-K個方法來做
	 * 所以需考慮飽不飽和問題*/
	private void insertInMinHeap(MinHeap minHeap, TrieNode node, String word){
		// First:case1:  word is already in minHeap
		if (node.indexMinHeap != -1){
			// NOTE: heap also has its frequency, update as well
			minHeap.array[node.indexMinHeap].frequency++;
			minHeapify(minHeap, node.indexMinHeap);
		} 
		// Second: case2: word is not present and heap is not full
		//         LIKE K個方法
		else if (minHeap.count < minHeap.capacity){
			int count = minHeap.count;
			// NOTE: INITIALIZATION
			minHeap.array[count] = new MinHeapNode();			
			minHeap.array[count].frequency = node.frequency;
			minHeap.array[count].word = word;
			minHeap.array[count].root = node;
			// NOTE: indexHeap initialize as count index
			node.indexMinHeap = minHeap.count;
			minHeap.count++;
			buildMinHeap(minHeap);
		}
		// Third: case3: word is not present and heap is full
		//               new word's frequency > root's frequency (min/least frequency word), 
		//               replace root with new word
		//               LIKE N-K個方法
		else if (node.frequency > minHeap.array[0].frequency){
			minHeap.array[0].root.indexMinHeap = -1; 
			minHeap.array[0].root = node;
			minHeap.array[0].root.indexMinHeap = 0;
			minHeap.array[0].frequency = node.frequency;
			minHeap.array[0].word = word;
			minHeapify(minHeap, 0);
		}
	}
	// [Heap] Build a heap from existing array
	/*buildMinHeap
	 * 		// First: for loop every index to do the heapify
	 *      //        i = (minHeap.COUNT-2) / 2
	 * */
	private void buildMinHeap(MinHeap minHeap){
		// First: for loop every index to do the heapify
		//        i = (minHeap.capacity-2) / 2
		for (int i = (minHeap.count-2) / 2; i >= 0; i--){
			minHeapify(minHeap, i);
		}
	}
	/* [Heap] minHeapify
	 * 		// First: create left index and right index to compare with smallest(==current) index
		// //                注意ＣＯＵＮＴ不是ＣＡＰＡＣＩＴＹ
	 * 		// Second: if smallest change, we swap two nodes and minHepaify to smallest
		//         NOTE: Update the corresponding index in Trie node  
		 
	 * */
	private void minHeapify(MinHeap minHeap, int index){
		// First: create left index and right index to compare with smallest(==current) index
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int smallest = index;
		// NOTE: java.lang.NullPointerException. minHeap.count Not minHeap.capacity
		// //                注意ＣＯＵＮＴ不是ＣＡＰＡＣＩＴＹ
		if (left < minHeap.count && minHeap.array[left].frequency < minHeap.array[smallest].frequency)
			smallest = left;
		if (right < minHeap.count && minHeap.array[right].frequency < minHeap.array[smallest].frequency) {
			smallest = right;
		}
		// Second: if smallest change, we swap two nodes and minHepaify to smallest
		//         NOTE: Update the corresponding index in Trie node  
		if (smallest != index){
			// NOTE: Update the corresponding index in Trie node  
			//minHeap.array[smallest].root.indexMinHeap = index; 
			//minHeap.array[index].root.indexMinHeap = smallest; 
			MinHeapNode temp = minHeap.array[smallest];
			minHeap.array[smallest] = minHeap.array[index];
			minHeap.array[index] = temp;
			minHeap.array[smallest].root.indexMinHeap = smallest; 
			minHeap.array[index].root.indexMinHeap = index; 
			minHeapify(minHeap, smallest);
		}
	}	
	public static void main(String[] args){
		TopKNormalData sol = new TopKNormalData();
		int k = 5;
		// Case1: A file is represented as a string
			//String file = "Welcome to the world of Geeks This portal has been created to provide well written well thought and well explained solutions for selected questions If you like Geeks for Geeks and would like to contribute here is your chance You can write article and mail your article to contribute at geeksforgeeks org See your article appearing on the Geeks for Geeks main page and help thousands of other Geeks";
			//String[] words = file.split(" ");
			//for (String word:words)
			//	System.out.println(word);		
		// Case2: A file is an txt file
		String path="/Users/weihung/Downloads/File.txt";
			//File file = null;
			//try{
				//file = new File(path);
			//}catch(FileNotFoundException e){
			// This exception is never thrown from the try statement 
			//	e.printStackTrace();
			//}		
		Long start = System.nanoTime();
		sol.TopKWords(path, k);
		Long estimate = System.nanoTime() - start;
		System.out.println("take time: "+estimate/1000+"ms");
		Long start2 = System.nanoTime();
		sol.TopKWordsHash(path, k);
		Long estimate2 = System.nanoTime() - start2;
		System.out.println("take time: "+estimate2/1000+"ms");			
	}
}
class TrieNode{
	TrieNode[] children;
	boolean isLeaf;
	int frequency;
	int indexMinHeap;
	public TrieNode(){
		this.children = new TrieNode[26];
		this.isLeaf = false;
		this.frequency = 0;
		this.indexMinHeap = -1;
	}
}
class MinHeapNode{
	TrieNode root;
	int frequency;
	String word;
	public MinHeapNode(){
		this.root = new TrieNode();
		this.word = null;
		this.frequency = 0;
	}
}
class MinHeap{
	int capacity;
	int count;
	MinHeapNode[] array;
	public MinHeap(int capacity){
		this.capacity = capacity;
		this.count = 0;
		this.array = new MinHeapNode[capacity];
	}
}

/*
// [Heap] [CONSTRUCTOR] Create a array of heapnode without heapify.  USE TO BE A CONSTRUCTOR
private MinHeap createMinHeap(int capacity){
		MinHeap minHeap = new MinHeap();
		minHeap.capacity = capacity;
		minHeap.count = 0;
		minHeap.array = new MinHeapNode[minHeap.capacity];
		return minHeap;
	}
// [MinHeapNode] [CONSTRUCTOR] create a new MinHeapNode. USE TO BE A CONSTRUCTOR
private MinHeapNode newMinHeapNode(){
	MinHeapNode node = new MinHeapNode();
	node.root = null;
	node.frequency = 0;
	node.word = null;		
	return node;
}
// [Trie] [CONSTRUCTOR] create a new trienode. USE TO BE A CONSTRUCTOR
private TrieNode newTrieNode(){
	TrieNode node = new TrieNode();
	node.isLeaf = false;
	node.frequency = 0;
	node.indexMinHeap = -1;
	// Note: init before assign
	node.children = new TrieNode[26];
	for (int i = 0 ; i < 26; i++){
		node.children[i] = null;
	}
	return node;
	
}
	[Wrapper] insertTrieAndHeap 
	  insertUtil
	
	//private void insertTrieAndHeap(String word, Trie root, LinkedList<Integer> minHeap){
	private void insertTrieAndHeap(String word, TrieNode root, MinHeap minHeap){
		insertUtil(word, root, minHeap);
	}
*/
/*
 * public List<T> findTopK(List<T> inputData, int k) {
		List<T> result = new ArrayList<T>();
		// key - inputDataObject, value - its count
		Map<T, Integer> map = mapInput(inputData);
		// sort map by count values
		List<Map.Entry<T, Integer>> sortedEntries = sortMapByValue(map);
		// check top size
		if (k > sortedEntries.size())
			k = sortedEntries.size();
		// get top
		for (int i = sortedEntries.size() - k; i < sortedEntries.size(); i++) {
			result.add(sortedEntries.get(i).getKey());
		}
		return result;
	}

	public Map<T, Integer> mapInput(List<T> inputData) {
		Map<T, Integer> map = new HashMap<T, Integer>();
		for (T t : inputData) {
			if (map.containsKey(t)) {
				int count = map.get(t);
				map.put(t, ++count);
			} else {
				map.put(t, 1);
			}
		}
		return map;
	}

	public List<Map.Entry<T, Integer>> sortMapByValue(Map<T, Integer> map) {
		List<Map.Entry<T, Integer>> sortedEntries = new ArrayList<Map.Entry<T, Integer>>();
		sortedEntries.addAll(map.entrySet());
		Collections.sort(sortedEntries, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		return sortedEntries;
	}

 * */