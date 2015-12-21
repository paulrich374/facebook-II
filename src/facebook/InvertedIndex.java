package facebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * 
 * 	{0} - "Turtles love pizza"
	{1} - "I love my turtles"
	{2} - "My pizza is good"
 * 
 * Then you would store them in a Inverted Indexes like this:

            	Record Level     Fully Inverted
	"turtles"   {0, 1}           { (0, 0), (1, 3) }
	"love"      {0, 1}           { (0, 1), (1, 1) }
	"pizza"     {0, 2}           { (0, 2), (2, 1) }
	"i"         {1}              { (1, 0) }
	"my"        {1, 2}           { (1, 2), (2, 0) }
	"is"        {2}              { (2, 2) }
	"good"      {2}              { (2, 3) }
 *  	The intersection becomes a more interesting problem. 
 *  	First, you can try using Bloom Filters if accuracy isn’t 100% needed, 
 *  	Second, you can brute force the problem by doing a full scan of each set for O(M+N) time for joining two sets.
 *   
 * https://github.com/acmeism/RosettaCodeData/blob/master/Task/Inverted-index/Java/inverted-index-1.java
 * http://introcs.cs.princeton.edu/java/44st/InvertedIndex.java.html
 * file:///Users/weihung/Desktop/%E4%B9%9D%E7%AB%A0Nine%20Chapter%20SystemDesign/Systemdesign_5_20151024.pdf
 * https://nullwords.wordpress.com/2013/04/18/inverted-indexes-inside-how-search-engines-work/
 * 
 * http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
 * 
Doc1: /Users/weihung/Downloads/doc1.txt
Buffalo buffalo Buffalo buffalo buffalo buffalo Buffalo buffalo.
Doc2: /Users/weihung/Downloads/doc2.txt
Buffalo are mammals.
.....

we could construct the following inverted file index:

						// CASE1: Scanner to readline
						//Scanner sc2 = new Scanner(sc.nextLine());
						//while(sc2.hasNext()){
						//	String word = sc2.next();

Buffalo -> Doc1, Doc2
buffalo -> Doc1
buffalo. -> Doc1
are -> Doc2
mammals. -> Doc2

						// CASE2: use Regex to readline
						for(String word : sc.nextLine().split("\\W+")){

{mammals=[/Users/weihung/Downloads/doc2.txt], 
 buffalo=[/Users/weihung/Downloads/doc1.txt], 
 are=[/Users/weihung/Downloads/doc2.txt], 
 Buffalo=[/Users/weihung/Downloads/doc2.txt, /Users/weihung/Downloads/doc1.txt]}


 * 
 * 
 * */
public class InvertedIndex {
	/*Approach#1 Map and set string approach */
	// key = word, value = set of filenames containing that word
	static HashMap<String, Set<String>> map = new HashMap<String, Set<String>>();
	static List<String> query = Arrays.asList("Buffalo", "are", "chickenwing");
	List<String> stopwords = Arrays.asList("a", "able", "about",
			"across", "after", "all", "almost", "also", "am", "among", "an",
			"and", "any", "are", "as", "at", "be", "because", "been", "but",
			"by", "can", "cannot", "could", "dear", "did", "do", "does",
			"either", "else", "ever", "every", "for", "from", "get", "got",
			"had", "has", "have", "he", "her", "hers", "him", "his", "how",
			"however", "i", "if", "in", "into", "is", "it", "its", "just",
			"least", "let", "like", "likely", "may", "me", "might", "most",
			"must", "my", "neither", "no", "nor", "not", "of", "off", "often",
			"on", "only", "or", "other", "our", "own", "rather", "said", "say",
			"says", "she", "should", "since", "so", "some", "than", "that",
			"the", "their", "them", "then", "there", "these", "they", "this",
			"tis", "to", "too", "twas", "us", "wants", "was", "we", "were",
			"what", "when", "where", "which", "while", "who", "whom", "why",
			"will", "with", "would", "yet", "you", "your");
	// 避免瓶率最高 冠詞 代名詞 獨佔
	// 				if (stopwords.contains(word))
	//                  continue;
	public static void main(String[] args){
		String[] filenames = new String[]{"/Users/weihung/Downloads/doc1.txt","/Users/weihung/Downloads/doc2.txt"};

			// First: create index of all files
			InvertedIndex sol = new InvertedIndex();
			
			for (int i = 0; i < filenames.length; i++){
				String path = filenames[i];
				if (path != null){
					//Scanner sc = new Scanner(new File(path));
					Scanner sc = null;
					try {
						sc = new Scanner(new File(path));
					} catch (FileNotFoundException e){
						e.printStackTrace();
					}					
					while(sc.hasNextLine()){
						//WRONG. Scanner sc2 = sc.nextLine();
						// CASE1: Scanner to readline
						//Scanner sc2 = new Scanner(sc.nextLine());
						//while(sc2.hasNext()){
						//	String word = sc2.next();
						// CASE2: use Regex to readline
						// http://stackoverflow.com/questions/9760909/split-string-with-regex-w-w-w
						for(String word : sc.nextLine().split("\\W+")){
							// Occurs one or more times, is short for {1,}
							// \w	A word character, short for [a-zA-Z_0-9]
							// \W	A non-word character [^\w]
							// \d	Any digit, short for [0-9]
							// \D	A non-digit, short for [^0-9]
							// Capital means non-XXXX
							if (map.containsKey(word)){
								map.get(word).add(path);
							} else {
								HashSet<String> set = new HashSet<String>();
								set.add(path);
								map.put(word, set);
							}
						}
					}
				}
			}
			System.out.println(map);
			// Second: Test inverted index table and do the search/query
			System.out.println();
			for (String str: query){
				System.out.println(str+": ");
				if (map.containsKey(str)){
					for (String path: map.get(str))
						System.out.println(path);
				}else {
					System.out.println("NOT FOUND");
				}
			}
	}
	/*Approach#2
	 *   input                        split              map(parse)
	 *  0: jay eats food         0:jay eats food        jay:0
	 *  2: food & msuic                                 eat:0
	 *  7: jay attends party                            food:0
	 *                           2: food & msuic        food:2
	 *                                                  music:2
	 *                           7: jay attends party   jay:7
	 *                                                  attends:7
	 *                                                  party:7
	 *                            Map(String key, String vlaue)
	 *                            // key:the id of a document                      
	 *                            // value: the content of a document
	 *                                for each word in vlaue
	 *                                  outputtemp(word, key) 
	 *                                  
	 *   shuffle(group)    Reduce(merge, statistics)
	 *   一樣的放在一起       
	 *   attend,7          attend,7
	 *   eat,0             eat,0
	 *   food,0            food, 0,2
	 *   food,2
	 *   
	 *    jay,0            jay, 0,7        
	 *    jay,7            music, 2
	 *    music,2          party,7
	 *    party,7           
	 *                            Reduce(String key list valuelsit)
	 *                            // key: the name of a word
	 *                            // value: the appearances of this word in documents
	 *                            List sumlist;                           
	 *                            for value in vlauelist:
	 *                                sumlist.append(value);
	 *                            Output(key, sumList)                           
	 * */          
}
