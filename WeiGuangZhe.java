package libs;
import java.util.ArrayList;

public class WeiGuangZhe {
	public static void main(String[] args) {
		//source为输入的待匹配的字符串
		String source = "wwwwwwww";
		findWord(source);
	}
	//
	private void findWord(String source){
		//词长取值列表
		ArrayList<String> words = new ArrayList<String>();
		for(int i = 1; i <= str.length(); i++){
			int s = str.length() - i + 1;
			String w1 = "";
			String w2 = "";
			if(s == 1){
				//将字符串逐字切分
				if(words.size() == 0){
					//词长取值为空值
				}else{
					//将所有符合条件的词长按值从大到小排序，令程序出事匹配最大长度wordmax = maxL2i+1,j????
					words = sortWords(words);
					for(int k = 0; k < words.size(); k++){
						int wordmax = words.get(k).length();
						//将以Wi 为首字，长度为wordmax的字符串与以WiWi+1为首二字的对应长度词进行匹配
						if(w1 != "" && w2 != "" && match(w1 + w2, wordmax)){
							//匹配成功
							//切词
							String removeWord = str.subString(i, wordmax - 1);
							String strLeft = getLeftWord(removeWord);
							String strRight = getRightWord(removeWord);
							if(strLeft != ""){
								findWord(strLeft);
							}
							if(strRight != ""){
								findWord(strRight);
							}
						}
					}
					//将字符串逐字切分
					split(source);
				}
			}
			w1 = str.charAt(i - 1);
			//在词典中哈希查找Wi
			if(findWordInHashTable(w1)){
				//存在W1
				w2 = str.charAt(i);
				//在以Wi为首字的哈希表中查找Wi+1
				if(findWordInHashTable(w1, w2)){
					//存在Wi+1
					findWordsInDictionary(w1 + w2);
				}else{
					//切出Wi
					str = str.subString(i + 1);
				}
			}else{
				//切出w1
				str = str.subString(i+1);
			}
		}
	}
	//在词典中哈希查找w
	private boolean findWordInDictionary(String w){
		//哈希查找算法的具体实现
		return true;
	}
	//在以w1为首字的哈希表中查找w2
	private boolean findWordInDictionary(String w1,String w2){
		//算法实现？？？
		return true;
	}
	//依次计算词典中以w12为首儿子的不大于s的所有词长
	private ArrayList<String> findWordsInDictionary(String w12){
		ArrayList<String> words = new ArrayList<String>();
		//算法实现？？？
		return words;
	}
	//将所有符合条件的词长按值从大到小排序，令程序出事匹配最大长度wordmax = maxL2i+1,j
	private ArrayList<String> sortWords(ArrayList<String> words){
		//算法实现？？？
		return words;
	}
	//将以Wi 为首字，长度为wordmax的字符串与以WiWi+1为首二字的对应长度词进行匹配
	private boolean match(String w12,int wordmax){
		//算法实现？？？
		return true;
	}
	//获取左边剩余字符串
	private String getLeftWord(String removeWord){
		String left = "";
		//算法实现？？？
		return left;
	}
	//获取右边剩余字符串
	private String getRightWord(String removeWord){
		String right = "";
		//算法实现？？？
		return right;
	}
	//将字符串逐字切分
	private void splitWord(String word){
		//算法实现？？？
	}
}