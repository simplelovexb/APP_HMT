package cn.edu.scau.hometown.tools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Parcel;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
//	private  RequestQueue requestQueue;
//	private  String tid;
//	private  HmtThreadsAttachment  hmtThreadsAttachment;
//	private  SpannableString spannableString;
//	private  TextView textView;
//	private  Context context;
//	private  Resources res;

//	public StringUtils(final Context context, final TextView tv, String source,String tid){
//
//		requestQueue= Volley.newRequestQueue(context);
//		spannableString = new SpannableString(source);
//		textView=tv;
//		this.tid=tid;
//		this.context=context;
//		res = this.context.getResources();
//
//	}
//

	public static  SpannableString getEmotionContent(final Context context, final TextView tv, String source) {
		SpannableString spannableString=new SpannableString(source);
		EditQuote(spannableString);
		EditEmoj(spannableString,context,tv);
		EditUrl(spannableString);
		EditModified(spannableString);

		return spannableString;
	}

	public static  void EditUrl(SpannableString spannableString) {
		String regexUrl="\\[url\\].+\\[/url\\]";
		Pattern patternUrl=Pattern.compile(regexUrl);
		Matcher matcherUrl=patternUrl.matcher(spannableString);
		while(matcherUrl.find()){
			String url=matcherUrl.group();
			int start =matcherUrl.start();
			int end=start+url.length();
			URLSpan urlSpan=new URLSpan(url);
			spannableString.setSpan(urlSpan,start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
	}
	public static  void EditEmoj(SpannableString spannableString,Context context, final TextView tv) {
		Resources res=context.getResources();
		//找到匹配的表情字符串
		ArrayList<String> regexs = new ArrayList<String>();
		regexs.add("\\{:\\d+_\\d+:\\}");
		regexs.add(":\\w+:");


		for (int i = 0; i < 2; i++) {
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
	public static  void EditQuote(SpannableString spannableString) {
		String regexQuote="【回复：[\\s\\S]*】";
		Pattern patternQuote=Pattern.compile(regexQuote);
		Matcher matcherQuote=patternQuote.matcher(spannableString);
		while(matcherQuote.find()){
			int start=matcherQuote.start();
			int end=matcherQuote.end();
			ForegroundColorSpan foreColorSpan=new ForegroundColorSpan(0xbb009688);
			Parcel p = Parcel.obtain();
			p.writeInt(Typeface.BOLD_ITALIC);
			p.setDataPosition(0);
			StyleSpan ss = new StyleSpan(p);
			spannableString.setSpan(foreColorSpan,start,end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			spannableString.setSpan(ss,start,end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

		}
	}

//	private  void EditAttachment(){
//		String regexAttachment="\\【attach\\】.+\\【/attach\\】";
//		Pattern patternAttachment=Pattern.compile(regexAttachment);
//		Matcher matcherAttachment=patternAttachment.matcher(spannableString);
//		while(matcherAttachment.find()){
//			String attach=matcherAttachment.group();
//			int start =matcherAttachment.start();
//			int end=start+attach.length();
//
//
//		}
//
//	}

    public static SpannableString EditModified(SpannableString spannableString){

		String regexQuote="【 本帖最后由[\\s\\S]+编辑 】";
		Pattern patternQuote=Pattern.compile(regexQuote);
		Matcher matcherQuote=patternQuote.matcher(spannableString);
		while(matcherQuote.find()){
			int start=matcherQuote.start();
			int end=matcherQuote.end();
			ForegroundColorSpan foreColorSpan=new ForegroundColorSpan(0xbb9575cd);
			Parcel p = Parcel.obtain();
			p.writeInt(Typeface.BOLD_ITALIC);
			p.setDataPosition(0);
			StyleSpan ss = new StyleSpan(p);
			spannableString.setSpan(foreColorSpan,start,end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			spannableString.setSpan(ss,start,end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

		}

		return  spannableString;
	}


//   private void getAttachMentInfoTask(String aid){
//       String url=HttpUtil.GET_POST_THREADS_ATTACHMENT_BY_TID_AND_AID+tid+"&aid="+aid;
//	   JsonObjectRequest mJsonRequest = new JsonObjectRequest(url, null,
//			   new Response.Listener<JSONObject>() {
//				   @Override
//				   public void onResponse(JSONObject response) {
//					   String json = response.toString();
//					   Gson gson = new Gson();
//					   java.lang.reflect.Type type = new TypeToken<HmtThreadsAttachment>() {
//					   }.getType();
//					   hmtThreadsAttachment = gson.fromJson(json, type);
//					   String attachUrl =hmtThreadsAttachment.getData().getAttachment();
//					   getAttachMentImagesTask(attachUrl);
//
//					   }
//			   },
//			   new Response.ErrorListener() {
//				   @Override
//				   public void onErrorResponse(VolleyError error) {
//
//				   }
//			   }
//	   );
//
//	   requestQueue.add(mJsonRequest);
//
//   }



//	private void getAttachMentImagesTask(String url){
//
//		ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
//			@Override
//			public void onResponse(Bitmap response) {
//
//				try {
//
//					ImageSpan span = new ImageSpan(context, scaleBitmap);
//					spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//				} catch (Exception e) {
//				}
//
//
//			}
//		}, 300, 200, Bitmap.Config.ARGB_8888,
//				new Response.ErrorListener() {
//					@Override
//					public void onErrorResponse(VolleyError error) {
//
//					}
//				});
//		requestQueue.add(imageRequest);
//
//
//	}
//


}
