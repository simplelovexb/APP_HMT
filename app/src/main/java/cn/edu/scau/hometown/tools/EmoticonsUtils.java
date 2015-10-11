package cn.edu.scau.hometown.tools;

import android.content.Context;
import android.text.TextUtils;

import com.keyboard.bean.EmoticonBean;
import com.keyboard.bean.EmoticonSetBean;
import com.keyboard.db.DBHelper;
import com.keyboard.utils.EmoticonsKeyboardBuilder;
import com.keyboard.utils.Utils;
import com.keyboard.utils.imageloader.ImageBase;

import java.util.ArrayList;
import java.util.Map;

public class EmoticonsUtils {

    private static String[] emoji1Array1=new String[138];
    private static String[] emoji2Array2=new String[123];
    private static String[] emoji3Array3=new String[74];
    private static String[] emoji4Array4=new String[53];
    private static String[] emoji5Array5=new String[149];
    private static String[] emoji6Array6=new String[79];
    private static String[] emoji7Array7=new String[37];

    /**
     * 初始化表情数据库
     *
     * @param context
     */
    public static void initEmoticonsDB(final Context context) {

        initEmoijArray();
        if (!Utils.isInitDb(context)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DBHelper dbHelper = new DBHelper(context);

                    /**
                     * FROM DRAWABLE
                     */
                    ArrayList<EmoticonBean> emojiArray1 = ParseData(emoji1Array1, EmoticonBean.FACE_TYPE_NOMAL, ImageBase.Scheme.DRAWABLE);
                    EmoticonSetBean emojiEmoticonSetBean1 = new EmoticonSetBean("emoji1", 4, 4);
                    emojiEmoticonSetBean1.setIconUri("drawable://ali001");
                    emojiEmoticonSetBean1.setItemPadding(15);
                    emojiEmoticonSetBean1.setVerticalSpacing(10);
                    emojiEmoticonSetBean1.setShowDelBtn(true);
                    emojiEmoticonSetBean1.setEmoticonList(emojiArray1);
                    dbHelper.insertEmoticonSet(emojiEmoticonSetBean1);


                    ArrayList<EmoticonBean> emojiArray2 = ParseData(emoji2Array2, EmoticonBean.FACE_TYPE_NOMAL, ImageBase.Scheme.DRAWABLE);
                    EmoticonSetBean emojiEmoticonSetBean2 = new EmoticonSetBean("emoji2", 4, 4);
                    emojiEmoticonSetBean2.setIconUri("drawable://king001");
                    emojiEmoticonSetBean2.setItemPadding(12);
                    emojiEmoticonSetBean2.setVerticalSpacing(10);
                    emojiEmoticonSetBean2.setShowDelBtn(true);
                    emojiEmoticonSetBean2.setEmoticonList(emojiArray2);
                    dbHelper.insertEmoticonSet(emojiEmoticonSetBean2);



                    ArrayList<EmoticonBean> emojiArray3 = ParseData(emoji3Array3, EmoticonBean.FACE_TYPE_NOMAL, ImageBase.Scheme.DRAWABLE);
                    EmoticonSetBean emojiEmoticonSetBean3 = new EmoticonSetBean("emoji3", 4, 4);
                    emojiEmoticonSetBean3.setIconUri("drawable://yoyo");
                    emojiEmoticonSetBean3.setItemPadding(15);
                    emojiEmoticonSetBean3.setVerticalSpacing(10);
                    emojiEmoticonSetBean3.setShowDelBtn(true);
                    emojiEmoticonSetBean3.setEmoticonList(emojiArray3);
                    dbHelper.insertEmoticonSet(emojiEmoticonSetBean3);

                    ArrayList<EmoticonBean> emojiArray4 = ParseData(emoji4Array4, EmoticonBean.FACE_TYPE_NOMAL, ImageBase.Scheme.DRAWABLE);
                    EmoticonSetBean emojiEmoticonSetBean4 = new EmoticonSetBean("emoji4", 4, 4);
                    emojiEmoticonSetBean4.setIconUri("drawable://bz001");
                    emojiEmoticonSetBean4.setItemPadding(15);
                    emojiEmoticonSetBean4.setVerticalSpacing(10);
                    emojiEmoticonSetBean4.setShowDelBtn(true);
                    emojiEmoticonSetBean4.setEmoticonList(emojiArray4);
                    dbHelper.insertEmoticonSet(emojiEmoticonSetBean4);


                    ArrayList<EmoticonBean> emojiArray5 = ParseData(emoji5Array5, EmoticonBean.FACE_TYPE_NOMAL, ImageBase.Scheme.DRAWABLE);
                    EmoticonSetBean emojiEmoticonSetBean5 = new EmoticonSetBean("emoji5", 4, 4);
                    emojiEmoticonSetBean5.setIconUri("drawable://face_1");
                    emojiEmoticonSetBean5.setItemPadding(15);
                    emojiEmoticonSetBean5.setVerticalSpacing(10);
                    emojiEmoticonSetBean5.setShowDelBtn(true);
                    emojiEmoticonSetBean5.setEmoticonList(emojiArray5);
                    dbHelper.insertEmoticonSet(emojiEmoticonSetBean5);


                    ArrayList<EmoticonBean> emojiArray6 = ParseData(emoji6Array6, EmoticonBean.FACE_TYPE_NOMAL, ImageBase.Scheme.DRAWABLE);
                    EmoticonSetBean emojiEmoticonSetBean6 = new EmoticonSetBean("emoji6", 4, 4);
                    emojiEmoticonSetBean6.setIconUri("drawable://tkp038");
                    emojiEmoticonSetBean6.setItemPadding(12);
                    emojiEmoticonSetBean6.setVerticalSpacing(5);
                    emojiEmoticonSetBean6.setShowDelBtn(true);
                    emojiEmoticonSetBean6.setEmoticonList(emojiArray6);
                    dbHelper.insertEmoticonSet(emojiEmoticonSetBean6);


                    ArrayList<EmoticonBean> emojiArray7 = ParseData(emoji7Array7, EmoticonBean.FACE_TYPE_NOMAL, ImageBase.Scheme.DRAWABLE);
                    EmoticonSetBean emojiEmoticonSetBean7 = new EmoticonSetBean("emoji7", 4, 4);
                    emojiEmoticonSetBean7.setIconUri("drawable://suannai");
                    emojiEmoticonSetBean7.setItemPadding(15);
                    emojiEmoticonSetBean7.setVerticalSpacing(8);
                    emojiEmoticonSetBean7.setShowDelBtn(true);
                    emojiEmoticonSetBean7.setEmoticonList(emojiArray7);
                    dbHelper.insertEmoticonSet(emojiEmoticonSetBean7);


                    dbHelper.cleanup();
                    Utils.setIsInitDb(context, true);
                }
            }).start();
        }
    }

    public static EmoticonsKeyboardBuilder getSimpleBuilder(Context context) {

        DBHelper dbHelper = new DBHelper(context);
        ArrayList<EmoticonSetBean> mEmoticonSetBeanList = dbHelper.queryEmoticonSet("emoji1", "emoji2","emoji3","emoji4","emoji5","emoji6","emoji7");
        dbHelper.cleanup();

        ArrayList<AppBean> mAppBeanList = new ArrayList<AppBean>();
        String[] funcArray = context.getResources().getStringArray(com.keyboard.view.R.array.apps_func);
        String[] funcIconArray = context.getResources().getStringArray(com.keyboard.view.R.array.apps_func_icon);
        for (int i = 0; i < funcArray.length; i++) {
            AppBean bean = new AppBean();
            bean.setId(i);
            bean.setIcon(funcIconArray[i]);
            bean.setFuncName(funcArray[i]);
            mAppBeanList.add(bean);
        }

        return new EmoticonsKeyboardBuilder.Builder()
                .setEmoticonSetBeanList(mEmoticonSetBeanList)
                .build();
    }

    public static EmoticonsKeyboardBuilder getBuilder(Context context) {

        DBHelper dbHelper = new DBHelper(context);
        ArrayList<EmoticonSetBean> mEmoticonSetBeanList = dbHelper.queryAllEmoticonSet();
        dbHelper.cleanup();

        ArrayList<AppBean> mAppBeanList = new ArrayList<AppBean>();
        String[] funcArray = context.getResources().getStringArray(com.keyboard.view.R.array.apps_func);
        String[] funcIconArray = context.getResources().getStringArray(com.keyboard.view.R.array.apps_func_icon);
        for (int i = 0; i < funcArray.length; i++) {
            AppBean bean = new AppBean();
            bean.setId(i);
            bean.setIcon(funcIconArray[i]);
            bean.setFuncName(funcArray[i]);
            mAppBeanList.add(bean);
        }

        return new EmoticonsKeyboardBuilder.Builder()
                .setEmoticonSetBeanList(mEmoticonSetBeanList)
                .build();
    }

    public static ArrayList<EmoticonBean> ParseData(String[] arry, long eventType, ImageBase.Scheme scheme) {
        try {
            ArrayList<EmoticonBean> emojis = new ArrayList<EmoticonBean>();
            for (int i = 0; i < arry.length; i++) {
                if (!TextUtils.isEmpty(arry[i])) {
                    String temp = arry[i].trim().toString();
                    String[] text = temp.split(",");
                    if (text != null && text.length == 2) {
                        String fileName = null;
                        if (scheme == ImageBase.Scheme.DRAWABLE) {
                            if (text[0].contains(".")) {
                                fileName = scheme.toUri(text[0].substring(0, text[0].lastIndexOf(".")));
                            } else {
                                fileName = scheme.toUri(text[0]);
                            }
                        } else {
                            fileName = scheme.toUri(text[0]);
                        }
                        String content = text[1];
                        EmoticonBean bean = new EmoticonBean(eventType, fileName, content);
                        emojis.add(bean);
                    }
                }
            }
            return emojis;
        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }

        return null;
    }

    private static void initEmoijArray() {

        int k1=0;
        int k2=0;
        int k3=0;
        int k4=0;
        int k5=0;
        int k6=0;
        int k7=0;
        int count = 0;
        for (Map.Entry<String, Integer> entry : EmotionUtils.emojiMap.entrySet()) {
              if(count>=609)
              {emoji7Array7[k7] = (entry.getValue() + "").replaceAll("R.drawable.", "") + ".png," + entry.getKey();
                k7++;
              }
            else if(count>=531){

                  emoji6Array6[k6] = (entry.getValue() + "").replaceAll("R.drawable.", "") + ".png," + entry.getKey();
                  k6++;
              }
              else if(count>=384){

                  emoji5Array5[k5] = (entry.getValue() + "").replaceAll("R.drawable.", "") + ".png," + entry.getKey();
                  k5++;
              }
              else if(count>=331){

                  emoji4Array4[k4] = (entry.getValue() + "").replaceAll("R.drawable.", "") + ".png," + entry.getKey();
                  k4++;
              }
              else if(count>=259){

                  emoji3Array3[k3] = (entry.getValue() + "").replaceAll("R.drawable.", "") + ".png," + entry.getKey();
                  k3++;
              }
              else if(count>=137){

                  emoji2Array2[k2] = (entry.getValue() + "").replaceAll("R.drawable.", "") + ".png," + entry.getKey();
                  k2++;
              }
              else if(count>=0){

                  emoji1Array1[k1] = (entry.getValue() + "").replaceAll("R.drawable.", "") + ".png," + entry.getKey();
                  k1++;
              }

                   count++;
        }

    }



}
