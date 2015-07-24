package cn.edu.scau.hometown.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import cn.edu.scau.hometown.tools.HttpUtil;

@SuppressLint("SetJavaScriptEnabled")
@SuppressWarnings("deprecation")
/**
 * 这是测试在Android Studio上同步更新项目！
 **/
public class LoginWebViewActivity extends Activity {
    private WebView webView;
    private String client_id = "client_id=11";
    private String redirect_url = "redirect_url=" + HttpUtil.getPsdnIp();
    private String state = "state=1ebUaX94z0QAdrX4G2t2eTidkI1jOfhi";
    private String response_type = "response_type=token";
    private String access_token;
    private String uid;
    private String expires_in;
    private String get_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle("");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

        webView = new WebView(this);
        webView.loadUrl("http://hometown.scau.edu.cn/open/OAuth/authorize?"
                + client_id + "&" + redirect_url + "&" + state + "&"
                + response_type);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.requestFocus();
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true); // 支持缩放
        webView.getSettings().setBuiltInZoomControls(true); // 显示放大缩小
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);// 解决缓存问题
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {

                Log.d("website", url);
                if (url.contains("uid")) {
                    uid = url.substring(url.indexOf("&uid") + 5);
                    // access_token=url.substring(url.indexOf("#access_token")+14,url.indexOf("&expires_in"));
                    // expires_in=url.substring(url.indexOf("&expires_in")+12,url.indexOf("&scope"));
                    try {
                        Toast.makeText(
                                getApplicationContext(),
                                HttpUtil.analyzeUserInfoJSON(
                                        HttpUtil.getRequest(HttpUtil.GET_HMT_USER_BASE_INFOMATION_URL_BY_USER_ID
                                                + uid)).toString(),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

        CookieSyncManager.createInstance(getApplicationContext());
        CookieManager.getInstance().removeAllCookie();//清除Cookie，让用户每一次登录都是全新的登录状态，不保存用户登录信息
        setContentView(webView);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;

        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
