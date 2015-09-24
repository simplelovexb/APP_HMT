package cn.edu.scau.hometown.tools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.scau.hometown.R;

public class StringUtils {

	public static SpannableString getEmotionContent(final Context context, final TextView tv, String source) {
		SpannableString spannableString = new SpannableString(source);
		Resources res = context.getResources();


		EditQuote(spannableString);
		EditEmoj(context, tv, spannableString, res);


//		String regexUrl="\\[url\\].+\\[/url\\]";
//		Pattern patternUrl=Pattern.compile(regexUrl);
//		Matcher matcherUrl=patternUrl.matcher(spannableString);
//		while(matcherUrl.find()){
//			String url=matcherUrl.group();
//			int start =matcherUrl.start();
//			int end=start+url.length();
//			URLSpan urlSpan=new URLSpan(url);
//			spannableString.setSpan(urlSpan,start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		}


		return spannableString;
	}

	private static void EditEmoj(Context context, TextView tv, SpannableString spannableString, Resources res) {
		//找到匹配的表情字符串
		ArrayList<String> regexs = new ArrayList<String>();
		regexs.add("\\{:\\d+_\\d+:\\}");
		regexs.add(":\\w+:");


		for (int i = 0; i < regexs.size()-1; i++) {
		String	regexEmotion1 = regexs.get(i);
			Pattern patternEmotion = Pattern.compile(regexEmotion1);
			Matcher matcherEmotion = patternEmotion.matcher(spannableString);
			while (matcherEmotion.find()) {
				// 获取匹配到的具体字符
				String key = matcherEmotion.group();
				// 匹配字符串的开始位置
				int start = matcherEmotion.start();
				// 利用表情名字获取到对应的图片
				Integer imgRes = EmotionUtils.getImgByName(key);
				if (imgRes != null) {
					// 压缩表情图片
					int size = 3 * (int) tv.getTextSize();
					Bitmap bitmap = BitmapFactory.decodeResource(res, imgRes);
					try {
						Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);

						ImageSpan span = new ImageSpan(context, scaleBitmap);
						spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					} catch (Exception e) {
					}
				}
			}
		}
	}
	private static void EditQuote(SpannableString spannableString) {
		String regexQuote="\\[quote[\\s\\S]*\\[/quote\\]";
		//String regexQuote="发表于";

		Pattern patternQuote=Pattern.compile(regexQuote);
		Matcher matcherQuote=patternQuote.matcher(spannableString);
		while(matcherQuote.find()){
			String quote=matcherQuote.group();
			int start=matcherQuote.start();
			int end=matcherQuote.end();
			ForegroundColorSpan foreColorSpan=new ForegroundColorSpan(0xFF009688);
			spannableString.setSpan(foreColorSpan,start,end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);



		}
	}


}
