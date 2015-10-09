package cn.edu.scau.hometown;

import android.app.Application;

import cn.edu.scau.hometown.bean.HmtUserBasedInfo;
import cn.edu.scau.hometown.tools.DataUtil;

/**
 * Created by Administrator on 2015/10/2 0002.
 * 初始化一些全局变量，例如用户信息【HmtUserBasedInfo】
 */
public class MyApplication extends Application {
    //用户信息的数据类
    private HmtUserBasedInfo hmtUserBasedInfo;



    public  HmtUserBasedInfo getHmtUserBasedInfo() {
        return hmtUserBasedInfo;
    }

    public void setHmtUserBasedInfo(HmtUserBasedInfo hmtUserBasedInfo) {
        this.hmtUserBasedInfo = hmtUserBasedInfo;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setHmtUserBasedInfo((HmtUserBasedInfo) DataUtil.getObject("登陆数据",this));
    }
}
