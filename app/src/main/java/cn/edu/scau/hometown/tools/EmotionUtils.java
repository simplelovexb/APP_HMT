package cn.edu.scau.hometown.tools;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.edu.scau.hometown.R;

public class EmotionUtils implements Serializable {
	/**
	 * key-表情文字;value-表情图片资源
	 */
	public static Map<String, Integer> emojiMap;
	
	static {
		emojiMap = new HashMap<String, Integer>();
//		emojiMap.put("{:9_814:}", R.drawable.face9_814);
//		emojiMap.put("{:9_817:}", R.drawable.face9_817);
//		emojiMap.put("{:9_824:}", R.drawable.face9_824);
//		emojiMap.put("{:9_849:}", R.drawable.face9_849);
//		emojiMap.put("{:9_866:}", R.drawable.face9_866);
//		emojiMap.put("{:9_877:}", R.drawable.face9_877);
//		emojiMap.put("{:9_885:}", R.drawable.face9_885);
//		emojiMap.put("{:9_899:}", R.drawable.face9_899);
//		emojiMap.put("{:11_999:}", R.drawable.face11_999);






	}
	
	public static int getImgByName(String imgName) {
		Integer integer = emojiMap.get(imgName);
		return integer == null ? -1 : integer;
	}
}
