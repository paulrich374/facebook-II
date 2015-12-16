package facebook;
/*
 * 
 *     Not Enough main memory to accommodate all words
 *     Cannot Fit into Memeory
 *     
 *     Determine the most frequent words given a terabyte of strings.
 *     
 * 11). 一个文本文件，找出前10个经常出现的词，但这次文件比较长，说是上亿行或十亿行，总之无法一次读入内存，问最优解。
    方案1：
    	首先根据用hash并求模，将文件分解(Divide)为多个小文件，
    	对于单个文件利用上题的方法(Top K Normal Data)求出每个文件件中10个最常出现的词。
    	然后再进行归并(Merge)处理，找出最终的10个最常出现的词。
    方案2：
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

Input Split Map Shuffle Reduce
0:	Deer Bear	River
1:	Car	Car	River
2:	Deer	Car	Bear
0:	Deer	Bear	River
1:	Car	Car	River
2:	Deer	Car	Bear
Deer,	1
Bear,	1
River,	1
Car,	1
Car,	1
River,	1
Car,	1
Car,	1
Car,	1
Deer,	1
Deer,	1
Bear,	1
Bear,	1
River,	1
River,	1
Deer,	2
Car,	3
Bear,	2
River,	2
Finalize
Bear,	2
Car,	3
Deer,	2
River,	2

 * */
public class TopKBigData {

}
