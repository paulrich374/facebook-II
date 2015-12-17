package facebook;
/*
 * 
 *     Not Enough main memory to accommodate all words
 *     Cannot Fit into Memeory
 *     
 *     Determine the most frequent words given a terabyte of strings.
 *     
 * 11). 一个文本文件，找出前10个经常出现的词，但这次文件比较长，说是上亿行或十亿行，总之无法一次读入内存，问最优解。
    方案1：Hash and External Sort(Divide and Merge)
    	首先根据用hash并求模，将文件分解(Divide)为多个小文件，
    	对于单个文件利用上题的方法(Top K Normal Data)求出每个文件件中10个最常出现的词。
    	然后再进行归并(Merge)处理，找出最终的10个最常出现的词。
    方案2：MapReduce
    	MapReduce
Map(	string	key,	string	value)
//key:	the	id	of	a	line
//value:	the	content	of	the	line
for	each	word	in	value:
OutputTemp(	 word,	1);

Reduce(	string	key,	list  valueList)
//key:	the	name	of	a	word
//value List:	the	appearances	of	this	word
int	sum	=	0;
for	value in valueList:
sum+=value;
OutputFinal(key,sum);
 * */
public class TopKBigData {
	 /*Approach#1 Hash and External Sort(Divide and Merge)
	  * =====  hash并求模，将文件分解(Divide)为多个小文件 ======
	  * hash(word)%2^18 = go to 文件No.#?
	  * 1 terabyte of strings = 10^12B=1TB=1000GB
	  * 2. Assume 100 MB memory only
	  * 3. 2^40 strings in total and suppose 100MB can hold 2^22 strings
	  * 4. 2^40 / 2^22 = 2^18 txt file
	  * =====  对于单个文件利用上题的方法(Top K Normal Data)求出每个文件件中10个最常出现的词 =====
	  * 5. Each file do a hashMap O(n) frequency statistics (key, value)=(word, frequency)
	  * 6. Each file do a sorted map by value O(n log n), the most frequent words on the top
	  * =====  然后再进行归并(Merge)处理，找出最终的10个最常出现的词 ======
	  * 7. pick each file most frequent do a hashMapO(n+nlogn) statistics or heapO(nlogk)
	  * 8.
	  * */
	 
     /*Approach#2 MapReduce*/
	 // input
		//0:	Deer Bear	River
		//1:	Car	Car	River
		//2:	Deer	Car	Bear
	 // split (parallel/pipeline)
		//0:	Deer Bear	River
	
		//1:	Car	Car	River
	
		//2:	Deer	Car	Bear	
	 // Map (pipeline map like hashMap)
		// 0.  Deer, 1; Bear, 1; River,	1
	
		// 1.  Car, 1; Car, 1; River, 1
	
		// 2.  Deer, 1; Car, 1; Bear, 1
	 // Shuffle(pipeline grouping )
	 	// Bear, 1; Bear, 1
	
	  	// Car, 1; Car, 1; Car, 1; 
	
		// Deer, 1; Deer, 1
	
		// River, 1; River, 1
	 // Reduce(pipeline aggregate)
		// Bear 2
	
		// Car 3
	
		// Deer 2
	
		// River 2
	 // Finalize (together)
		// Bear 2
		// Car 3
		// Deer 2
		// River 2
	/*
	Map(String key, String value){ // Split
		//key: the id of a line
		//value: the content of the line
		for each word in value:
			OutputTemp(word, 1);
	}
	Reduce (String key, list valueList){
		//key: the name of a word
		//valueList: the apperances of this word
		int sum = 0;
		for value in valueList:
			sum += value;
		OutputFinal(key, sum);
	}
	*/
}
