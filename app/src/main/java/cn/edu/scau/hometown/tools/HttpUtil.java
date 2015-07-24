package cn.edu.scau.hometown.tools;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import cn.edu.scau.hometown.bean.HmtUserBasedInfo;

public class HttpUtil {
    public static HttpClient httpClient = new DefaultHttpClient();
    public static final String BASE_URL_KEY_WORD = "http://hometown.scau.edu.cn/course/index.php?s=/Api&keyword=";
    public static final String BASE_URL_COURSE_ID = "http://hometown.scau.edu.cn/course/index.php?s=/Api&course=";
    public static final String GET_HMT_USER_BASE_INFOMATION_URL_BY_USER_ID = "http://hometown.scau.edu.cn/bbs/plugin.php?id=iltc_userinfoapi&action=user&type=uid&key=";

    /**
     * @param url 發送請求的url
     * @return 服務器響應請求發送的字符串
     * @throws Exception 拋出網絡連接時的異常
     */
    public static String getRequest(final String url) throws Exception {

        FutureTask<String> task = new FutureTask<String>(
                new Callable<String>() {

                    @Override
                    public String call() throws Exception {

                        HttpGet get = new HttpGet(url);
                        HttpResponse httpResponse = httpClient.execute(get);
                        if (httpResponse.getStatusLine().getStatusCode() == 200) {

                            String result = EntityUtils.toString(httpResponse
                                    .getEntity());
                            return result;
                        }
                        return null;

                    }

                });

        new Thread(task).start();
        return task.get();

    }


    /**
     * @return 返回手機的IP地址，在Oauth登录时需要填写的redirect_uri的值
     */
    public static String getPsdnIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * @param json
     * @return 用户的JSON数据字符串
     */
    public static HmtUserBasedInfo analyzeUserInfoJSON(final String json) {
        HmtUserBasedInfo basedInfo = null;
        String status = "";
        String uid = "";
        String username = "";
        String email = "";
        String adminid = "";
        String groupid = "";
        JSONArray extgroupids = null;
        String allowadmincp = "";

        JSONTokener object = new JSONTokener(json);
        try {
            JSONObject information = (JSONObject) object.nextValue();
            status = information.getString("status");
            if (status.equals("fail"))
                return null;
            JSONObject data = information.getJSONObject("data");
            uid = data.getString("uid");
            username = data.getString("username");
            email = data.getString("email");
            adminid = data.getString("adminid");
            groupid = data.getString("groupid");
            extgroupids = data.getJSONArray("extgroupids");
            allowadmincp = data.getString("allowadmincp");

            basedInfo = new HmtUserBasedInfo(uid, username, email, adminid,
                    groupid, extgroupids, allowadmincp);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return basedInfo;
    }

}
