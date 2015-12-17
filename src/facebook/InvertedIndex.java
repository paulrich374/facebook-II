package facebook;
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
 * 
Doc1:
Buffalo buffalo Buffalo buffalo buffalo buffalo Buffalo buffalo.
Doc2:
Buffalo are mammals.
we could construct the following inverted file index:
Buffalo -> Doc1, Doc2
buffalo -> Doc1
buffalo. -> Doc1
are -> Doc2
mammals. -> Doc2
 * 
 * 
 * */
public class InvertedIndex {
	public static void main(String[] args){
		try{
			InvertedIndex sol = new InvertedIndex();
			
		} catch(){
			
		}
	}
}
