package facebook;

import java.util.LinkedList;

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
 * */
/*Approach#1 Hash
 * 
 * Finally, traverse through the hash table and return the k words with maximum counts
 * T:O(n)
 * sort the HashMap values
 * T:O(n log n)
 * */
/*Approach#2 Heap
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
public class TopKNormalData {
	public void TopKWors(FILE file, int k){
		
	}
	private void insertTrieAndHeap(String word, Trie root, LinkedList<Integer> minHeap){
		
	}
	public static void main(String[] args){
		TopKNormalData sol = new TopKNormalData();
		String file = "Welcome to the world of Geeks This portal has been created to provide well written well thought and well explained solutions for selected questions If you like Geeks for Geeks and would like to contribute here is your chance You can write article and mail your article to contribute at geeksforgeeks org See your article appearing on the Geeks for Geeks main page and help thousands of other Geeks";
		
		sol.
	}
}
