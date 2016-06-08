package libs;
import java.util.ArrayList;

public class WeiGuangZhe {
	public static void main(String[] args) {
	}
	private ArrayList<String> results = new ArrayList<String>();
	public String findWord(String str) {
		int n = 0;
		if (str == null || str.length() == 0) {
			return "";
		}
		n = str.length();
		if (n == 1) {
			return str;
		}
		//
		for (int i = 0; i < n; i++) {
			int s = n - i - 1;
			if (s == 1) {
				String subString = str.substring(i + 1, n);
				/**
				 * 
				 * 
				 * 词长取值为空值？？？？？不懂
				 * 
				 * 
				 * 
				 */
				if (subString == null || subString.length() == 0) {
					// 将字符串逐字切分
					/**
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 */
					return "";
				} else {
					// 将所有符合条件的词长按值从大到小排序，令程序初始匹配最大长度wordmax = maxL
					results = sort(results);
					int wordMax = results.get(0).length();
					for (String result : results) {
						if(match(result)){
							//匹配成功
							String leftWord = "";
							findWord(leftWord);
							String rightWord = "";
							findWord(rightWord);
						}else{
							if(wordMax == 2){
								String leftWord = "";
								findWord(leftWord);
								String rightWord = "";
								findWord(rightWord);
							}
						}
					}
				}
			} else {
				//
				String w1 = str.substring(i, i + 1);
				if (isWordExistInHashTable(w1)) {
					String w2 = str.substring(i + 1, i + 2);
					if (isWordExistInHashTable2(w2)) {
						findWordsInDictionary(w1 + w2);
						continue;
					} else {
						continue;
					}
				} else {
					continue;
				}
			}
		}
		return "";
	}
	/**
	 * 在词典中哈希查找word
	 */
	private boolean isWordExistInHashTable(String w1) {
		/*
		 * 具体实现
		 */
		return false;
	}
	/**
	 * 在以w1为首字的次字哈希表中查找w2
	 */
	private boolean isWordExistInHashTable2(String w2) {
		/*
		 * 具体实现
		 */
		return false;
	}
	/**
	 *	依次计算词典中以word为首二字的不大于s的所有词长
	 */
	private ArrayList<String> findWordsInDictionary(String word) {
		results.add("word1");
		/*
		 * 具体实现
		 */
		results.add("word2");
		return results;
	}
	/**
	 * 将所有符合条件的词长按值从大到小排序，令程序初始匹配最打茶馆难度wordmax=... 
	 */
	private ArrayList<String> sort(ArrayList<String> result){
		/*
		 * 排序
		 */
		return result;
	}
	/**
	 * 将长度为wordmax的以wi为首字的字符串与以WiW(i+1)为首二字的词进行匹配
	 */
	private boolean match(String ww){
		/*
		 * 匹配 
		 */
		return false;
	}
}
