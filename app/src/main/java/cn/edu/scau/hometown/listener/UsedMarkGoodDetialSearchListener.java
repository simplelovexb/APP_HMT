package cn.edu.scau.hometown.listener;

import com.android.volley.VolleyError;

import cn.edu.scau.hometown.bean.GoodsInfo;

/**
 * Created by Administrator on 2015/10/14.
 */
public interface UsedMarkGoodDetialSearchListener {

    public void succee(GoodsInfo data);

    public void failor(Exception e);

    public void error(VolleyError error);

}
