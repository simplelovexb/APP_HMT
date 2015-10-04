package cn.edu.scau.hometown.tools;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/9/2 0002.
 */
public class DataUtil {
    public static void saveObject(String name, Object sod, Context context) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(name, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(sod);
        } catch (Exception e) {
            e.printStackTrace();
            //这里是保存文件产生异常
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    //fos流关闭异常
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    //oos流关闭异常
                    e.printStackTrace();
                }
            }
        }
    }


    public static Object getObject(String name, Context context) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(name);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            //这里是读取文件产生异常
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    //fis流关闭异常
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    //ois流关闭异常
                    e.printStackTrace();
                }
            }
        }
        //读取产生异常，返回null
        return null;
    }

    public static String getGroupNmae(String groupid) {
        if (groupid.equals("1")) return "管理員";
        else if (groupid.equals("2")) return "超級版主";
        else if (groupid.equals("3")) return "版主";
        else if (groupid.equals("4")) return "禁止發言";
        else if (groupid.equals("5")) return "禁止訪問";
        else if (groupid.equals("6")) return "禁止 IP";
        else if (groupid.equals("7")) return "遊客";
        else if (groupid.equals("8")) return "等待驗證會員";
        else if (groupid.equals("9")) return "红薯學前班";
        else if (groupid.equals("10")) return "红薯小學生";
        else if (groupid.equals("11")) return "红薯高中生";
        else if (groupid.equals("12")) return "红薯本科生";
        else if (groupid.equals("13")) return "红薯碩士生";
        else if (groupid.equals("14")) return "红薯博士生";
        else if (groupid.equals("15")) return "红薯博士後";
        else if (groupid.equals("16")) return "红薯副教授";
        else if (groupid.equals("17")) return "红薯教授";
        else if (groupid.equals("21")) return "靈魂人物";
        else if (groupid.equals("25")) return "红薯初中生";
        else if (groupid.equals("26")) return "实习版主";
        else if (groupid.equals("30")) return "广告杀手";
        else if (groupid.equals("32")) return "专长红薯";
        else if (groupid.equals("33")) return "PT组";
        else if (groupid.equals("34")) return "版主";
        else if (groupid.equals("35")) return "Metc专用组";
        else if (groupid.equals("37")) return "红满堂应用部";
        else if (groupid.equals("41")) return "保卫处";
        else if (groupid.equals("42")) return "Ad专用组";
        else if (groupid.equals("43")) return "视角专员";
        else if (groupid.equals("44")) return "退休人员";
        else if (groupid.equals("45")) return "QQ游客";
        else if (groupid.equals("46")) return "红满堂维护组";
  return null;

    }




    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time
     * @return
     */
    public static String times(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-M-dd   HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
