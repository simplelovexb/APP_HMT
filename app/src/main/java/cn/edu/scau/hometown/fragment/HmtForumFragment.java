package cn.edu.scau.hometown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.activities.DetialHmtPostThreadsActivity;
import cn.edu.scau.hometown.activities.LoginWebViewActivity;
import cn.edu.scau.hometown.adapter.InitHmtForumListViewAdapter;
import cn.edu.scau.hometown.bean.HmtForumData;
import cn.edu.scau.hometown.library.com.javis.abslidingpagerview.AbOnItemClickListener;
import cn.edu.scau.hometown.library.com.javis.abslidingpagerview.AbSlidingPlayView;
import cn.edu.scau.hometown.library.com.tjerkw.slideexpandable.SlideExpandableListAdapter;
import cn.edu.scau.hometown.tools.DataUtil;
import cn.edu.scau.hometown.tools.HttpUtil;


/**
 * Created by acer on 2015/7/24.
 */
public class HmtForumFragment extends Fragment {
    private SwipeRefreshLayout lo_swiper;
    private View view;

    private AbSlidingPlayView vp_ab;
    /**
     * 存储首页轮播的界面
     */
    private ArrayList<View> allListView;
    /**
     * 首页轮播的界面的资源
     */
    //TODO 相片的载入
    private int[] resId = {R.drawable.menu_viewpager_1, R.drawable.menu_viewpager_2, R.drawable.menu_viewpager_3, R.drawable.menu_viewpager_4, R.drawable.menu_viewpager_5};

    private ListView lv_hmt_forum;
    /**
     * 显示热门帖子的ListView
     */
    private InitHmtForumListViewAdapter initHmtForumListViewAdapter;
    /**
     * 渲染热门帖子列表视图的自定义Adapter
     */
    private RequestQueue mRequestQueue;
    /**
     * 网络请求会用到的工具
     */
    private HmtForumData hmtForumData;

    /**
     * 网络申请得到帖子数据后转化而成的帖子数据类
     */


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(getActivity());


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hmt_forum, container, false);
        init_abSlidingPagerView();

        lo_swiper = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container_hmt_forum);
        lo_swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                SearchHmtForumDataTask();
            }
        });
        // 设置进度条的颜色主题，最多能设置四种
        lo_swiper.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        SearchHmtForumDataTask();


        return view;
    }

    private void init_abSlidingPagerView() {
        vp_ab = (AbSlidingPlayView) view.findViewById(R.id.vp_ab);


        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        allListView = new ArrayList<View>();

        for (int i = 0; i < resId.length; i++) {
            //导入ViewPager的布局,前三行是把图片封装成一个ImageView
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pic_item_for_hmtfragment, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item_for_hmtfragment);
            imageView.setImageResource(resId[i]);
            allListView.add(view);
        }


        vp_ab.addViews(allListView);
        //开始轮播
        vp_ab.makesurePosition();
        vp_ab.creatIndex();
        vp_ab.startPlay();
        vp_ab.setOnItemClickListener(new AbOnItemClickListener() {
            @Override
            public void onClick(int position) {
                //跳转到详情界面
//				Intent intent = new Intent(getActivity(), BabyActivity.class);
//				startActivity(intent);
                vp_ab.stopPlay();
                Log.d("MyDebug", "position=========" + position);
                Toast.makeText(getActivity(), "position=" + position, Toast.LENGTH_LONG).show();
                vp_ab.startPlay();
            }
        });
    }

    private void initHmtForumListView() {
        lv_hmt_forum = (ListView) view.findViewById(R.id.lv_hmt_forum);
        initHmtForumListViewAdapter = new InitHmtForumListViewAdapter(getActivity(), hmtForumData);
        lv_hmt_forum.setAdapter(initHmtForumListViewAdapter);
        lv_hmt_forum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), DetialHmtPostThreadsActivity.class));
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.keep);
            }
        });
    }

    private void SearchHmtForumDataTask() {
        lo_swiper.setRefreshing(true);
        String url = "http://hometown.scau.edu.cn/bbs/plugin.php?id=iltc_open:forum_guide";
        JsonObjectRequest mJsonRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String json = response.toString();
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<HmtForumData>() {
                        }.getType();
                        hmtForumData = gson.fromJson(json, type);
                        RefreshHmtListView();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if((HttpUtil.isNetworkConnected(getActivity())==false)&&(HttpUtil.isWifiConnected(getActivity())==false))
                            Toast.makeText(getActivity(), "请检查网络！", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(), "(*@ο@*) 哇～  很抱歉！服务器出问题了～", Toast.LENGTH_LONG).show();





                        //没有数据时的临时操作
                        String json = "{\"threadcount\":\"59\",\"threadlist\":[{\"tid\":\"804740\",\"fid\":\"11\",\"posttableid\":\"0\",\"typeid\":\"10\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"\\u964c\\u8defLMH\",\"authorid\":\"368741\",\"subject\":\"\\u4fdd\\u7814\\u6c42\\u89e3\\uff1f\\uff1f\\uff1f\",\"dateline\":\"5 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastpost\":\"\\u534a\\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"\\u77f3\\u57ce\\u68ee\\u6797\",\"views\":\"52\",\"replies\":\"4\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"0\",\"recommend_add\":\"0\",\"recommend_sub\":\"0\",\"heats\":\"4\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"5\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"%CA%AF%B3%C7%C9%AD%C1%D6\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"804740\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"1\",\"dbdateline\":\"1441008672\",\"dblastpost\":\"1441027041\",\"id\":\"normalthread_804740\",\"rushreply\":\"0\"},{\"tid\":\"804589\",\"fid\":\"7\",\"posttableid\":\"0\",\"typeid\":\"0\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"xiersi2012\",\"authorid\":\"639848\",\"subject\":\"\\u597d\\u591a\\u8272\\u60c5\\u5e7f\\u544a \\u4e25\\u91cd\\u5371\\u5bb3\\u5b66\\u751f \\u793e\\u4f1a\",\"dateline\":\"10 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastpost\":\"\\u534a\\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"xiersi2012\",\"views\":\"64\",\"replies\":\"6\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"0\",\"recommend_add\":\"0\",\"recommend_sub\":\"0\",\"heats\":\"3\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"20\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"7\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"xiersi2012\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"804589\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"1\",\"dbdateline\":\"1440992603\",\"dblastpost\":\"1441025480\",\"id\":\"normalthread_804589\",\"rushreply\":\"0\"},{\"tid\":\"804773\",\"fid\":\"54\",\"posttableid\":\"0\",\"typeid\":\"49\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"H-R\",\"authorid\":\"510778\",\"subject\":\"HR\\u5b9e\\u4e60\\u751f(\\u4e24\\u540d\\uff09+\\u5c97\\u9876\\u9f99\\u53e3\\u4e1c\\u8def+talebase\",\"dateline\":\"4 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastpost\":\"3 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"mingjie1991\",\"views\":\"81\",\"replies\":\"1\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"2\",\"recommend_add\":\"4\",\"recommend_sub\":\"2\",\"heats\":\"5\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"2\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"mingjie1991\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"804773\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"1\",\"dbdateline\":\"1441011246\",\"dblastpost\":\"1441016875\",\"id\":\"normalthread_804773\",\"rushreply\":\"0\"},{\"tid\":\"797369\",\"fid\":\"7\",\"posttableid\":\"0\",\"typeid\":\"0\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"\\u6df1\\u6d77_\",\"authorid\":\"490341\",\"subject\":\"\\u7ec8\\u4e8e\\u56de\\u5230\\u5e7f\\u5dde\\u4e86\",\"dateline\":\"4 \\u5929\\u524d<\\/span>\",\"lastpost\":\"3 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"\\u9e21\\u7ffcSit\",\"views\":\"173\",\"replies\":\"19\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"1\",\"recommend_sub\":\"0\",\"heats\":\"8\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"1441018953\\t660998,656433\",\"maxposition\":\"20\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"%BC%A6%D2%EDSit\",\"multipage\":\" ...2<\\/a>\",\"pages\":\"2\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"797369\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440690398\",\"dblastpost\":\"1441016299\",\"id\":\"normalthread_797369\",\"rushreply\":\"0\"},{\"tid\":\"804841\",\"fid\":\"101\",\"posttableid\":\"0\",\"typeid\":\"0\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"xiupi233\",\"authorid\":\"639597\",\"subject\":\"\\u8fd9\\u5b63\\u7684\\u79cb\\uff0c\\u662f\\u4ea4\\u4e86\\u5fc3\\uff0c\\u4ed8\\u4e86\\u60c5\\u7684\",\"dateline\":\"3 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastpost\":\"3 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"wayl521\",\"views\":\"17\",\"replies\":\"1\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"0\",\"recommend_add\":\"1\",\"recommend_sub\":\"1\",\"heats\":\"3\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"2\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"wayl521\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"804841\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"1\",\"dbdateline\":\"1441014726\",\"dblastpost\":\"1441015476\",\"id\":\"normalthread_804841\",\"rushreply\":\"0\"},{\"tid\":\"804840\",\"fid\":\"101\",\"posttableid\":\"0\",\"typeid\":\"0\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"xiupi233\",\"authorid\":\"639597\",\"subject\":\"\\u66fe\\u7ecf\\u97f6\\u534e\\u7684\\u8273\\u4e3d\\uff0c\\u50cf\\u6781\\u4e86\\u538b\\u7bb1\\u5e95\\u7684\\u7167\\u7247\",\"dateline\":\"4 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastpost\":\"3 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"wayl521\",\"views\":\"20\",\"replies\":\"4\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"1\",\"recommend_sub\":\"0\",\"heats\":\"3\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"5\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"wayl521\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"804840\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"1\",\"dbdateline\":\"1441014422\",\"dblastpost\":\"1441015425\",\"id\":\"normalthread_804840\",\"rushreply\":\"0\"},{\"tid\":\"798761\",\"fid\":\"36\",\"posttableid\":\"0\",\"typeid\":\"0\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"xiuer\",\"authorid\":\"512589\",\"subject\":\"\\u6211\\u662f\\u4e30\\u987a\\u4eba\",\"dateline\":\"3 \\u5929\\u524d<\\/span>\",\"lastpost\":\"5 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"xiuer\",\"views\":\"268\",\"replies\":\"6\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"4\",\"recommend_add\":\"5\",\"recommend_sub\":\"1\",\"heats\":\"10\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"7\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"xiuer\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"798761\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440740018\",\"dblastpost\":\"1441008180\",\"id\":\"normalthread_798761\",\"rushreply\":\"0\"},{\"tid\":\"804707\",\"fid\":\"101\",\"posttableid\":\"0\",\"typeid\":\"0\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"ljh517\",\"authorid\":\"509600\",\"subject\":\"\\u766b\\u75eb\\u75c5\\u6bd4\\u8f83\\u5e38\\u89c1\\u7684\\u6cbb\\u7597\\u65b9\\u6cd5\\u6709\\u4ec0\\u4e48\",\"dateline\":\"6 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastpost\":\"5 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"dys\",\"views\":\"28\",\"replies\":\"1\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"2\",\"recommend_sub\":\"1\",\"heats\":\"3\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"2\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"dys\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"804707\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"1\",\"dbdateline\":\"1441007039\",\"dblastpost\":\"1441008083\",\"id\":\"normalthread_804707\",\"rushreply\":\"0\"},{\"tid\":\"799379\",\"fid\":\"54\",\"posttableid\":\"0\",\"typeid\":\"49\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"\\u5f77\\u5fa8\",\"authorid\":\"9961\",\"subject\":\"\\u7f51\\u6613\\u5bb6\\u5c45\\u8bba\\u575b\\u5b9e\\u4e60\\u7f16\\u8f91\\uff08\\u6b63\\u5728\\u62db\\u8058\\uff09\",\"dateline\":\"3 \\u5929\\u524d<\\/span>\",\"lastpost\":\"6 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"\\u5f77\\u5fa8\",\"views\":\"135\",\"replies\":\"1\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"3\",\"recommend_add\":\"4\",\"recommend_sub\":\"1\",\"heats\":\"5\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"2\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"%E1%DD%E1%E5\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"799379\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440748362\",\"dblastpost\":\"1441005240\",\"id\":\"normalthread_799379\",\"rushreply\":\"0\"},{\"tid\":\"793737\",\"fid\":\"54\",\"posttableid\":\"0\",\"typeid\":\"49\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"SharpS\",\"authorid\":\"296689\",\"subject\":\"\\u5e7f\\u5dde\\u9886\\u4ec1\\u7f51\\u7edc\\u79d1\\u6280\\u6709\\u9650\\u516c\\u53f8 \\u5b9e\\u4e60\\u751f\\u62db\\u8058\",\"dateline\":\"6 \\u5929\\u524d<\\/span>\",\"lastpost\":\"6 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"meeloun\",\"views\":\"300\",\"replies\":\"7\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"3\",\"recommend_sub\":\"2\",\"heats\":\"9\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"9\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"meeloun\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"793737\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440468706\",\"dblastpost\":\"1441005231\",\"id\":\"normalthread_793737\",\"rushreply\":\"0\"},{\"tid\":\"802368\",\"fid\":\"47\",\"posttableid\":\"0\",\"typeid\":\"0\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"\\u82b1\\u5f20\",\"authorid\":\"102787\",\"subject\":\"\\u7231\\u4f60\\u5982\\u8bd7\\u7f8e\\u4e3d\",\"dateline\":\"\\u524d\\u5929 23:23<\\/span>\",\"lastpost\":\"6 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"sylar\\u7070\",\"views\":\"48\",\"replies\":\"3\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"1\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"1\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"1\",\"recommend_sub\":\"0\",\"heats\":\"3\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"7\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"4\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"sylar%BB%D2\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"802368\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440861822\",\"dblastpost\":\"1441004019\",\"id\":\"normalthread_802368\",\"rushreply\":\"0\"},{\"tid\":\"797368\",\"fid\":\"79\",\"posttableid\":\"0\",\"typeid\":\"61\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"Dante-Yang\",\"authorid\":\"508538\",\"subject\":\"\\u4e50\\u8bc4\\uff1a\\u5341. \\u846c\\u8eab\\u4eba\\u8179\",\"dateline\":\"4 \\u5929\\u524d<\\/span>\",\"lastpost\":\"9 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"Dante-Yang\",\"views\":\"105\",\"replies\":\"4\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"1\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"0\",\"recommend_add\":\"2\",\"recommend_sub\":\"2\",\"heats\":\"8\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"6\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"Dante-Yang\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"797368\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440689784\",\"dblastpost\":\"1440994582\",\"id\":\"normalthread_797368\",\"rushreply\":\"0\"},{\"tid\":\"802369\",\"fid\":\"79\",\"posttableid\":\"0\",\"typeid\":\"61\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"\\u4e00\\u4e66and\\u4e00\\u4e16\\u754c\",\"authorid\":\"613630\",\"subject\":\"\\u6709\\u8c01\\u8fd8\\u5728\\u542c\\u6768\\u5343\\u5b05\\u7684\\u300a\\u91ce\\u5b69\\u5b50\\u300b\\uff1f\\u6700\\u8fd1\\u53c8\\u91cd\\u65b0\\u542c\\u4e86\\uff0c\\u597d\\u597d\\u542c\",\"dateline\":\"\\u524d\\u5929 23:36<\\/span>\",\"lastpost\":\"10 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"xiersi2012\",\"views\":\"42\",\"replies\":\"1\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"1\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"0\",\"recommend_add\":\"1\",\"recommend_sub\":\"1\",\"heats\":\"3\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"2\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"xiersi2012\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"802369\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440862579\",\"dblastpost\":\"1440992400\",\"id\":\"normalthread_802369\",\"rushreply\":\"0\"},{\"tid\":\"795012\",\"fid\":\"119\",\"posttableid\":\"0\",\"typeid\":\"83\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"benny_yang621\",\"authorid\":\"513046\",\"subject\":\"2016\\u730e\\u5934\\u62db\\u8058\\u91cd\\u8fd4\\u6821\\u56ed\",\"dateline\":\"5 \\u5929\\u524d<\\/span>\",\"lastpost\":\"10 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"benny_yang621\",\"views\":\"210\",\"replies\":\"5\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"2\",\"recommend_sub\":\"1\",\"heats\":\"7\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"6\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"benny_yang621\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"795012\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440567106\",\"dblastpost\":\"1440992175\",\"id\":\"normalthread_795012\",\"rushreply\":\"0\"},{\"tid\":\"804585\",\"fid\":\"142\",\"posttableid\":\"0\",\"typeid\":\"105\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"zxzjx5131420\",\"authorid\":\"301389\",\"subject\":\"\\u4eb2\\uff0c\\u56de\\u5b66\\u6821\\u4e86\\u5417\\uff1f\\u5feb\\u770b\\u770b\\u5468\\u4e00\\u3001\\u56db\\u4e0b\\u5348\\u6709\\u6ca1\\u6709\\u8bfe\\u5427\\uff0c\\u6709\\u517c\\u804c\\uff08\\u516c\\u6587\\u534e\\u666f\\u6559\\u80b2\\u4e2d\\u5fc3\\uff09\",\"dateline\":\"10 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastpost\":\"10 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"zxzjx5131420\",\"views\":\"63\",\"replies\":\"0\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"2\",\"recommend_sub\":\"1\",\"heats\":\"3\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"0\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"zxzjx5131420\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"804585\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"1\",\"dbdateline\":\"1440991789\",\"dblastpost\":\"1440991789\",\"id\":\"normalthread_804585\",\"rushreply\":\"0\"},{\"tid\":\"804582\",\"fid\":\"142\",\"posttableid\":\"0\",\"typeid\":\"105\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"\\u76ca\\u5bb6\\u4eba\",\"authorid\":\"514807\",\"subject\":\"\\u5e08\\u59d0\\u8f6c\\u8ba9\\u517c\\u804c\",\"dateline\":\"10 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastpost\":\"10 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"\\u76ca\\u5bb6\\u4eba\",\"views\":\"96\",\"replies\":\"0\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"2\",\"recommend_sub\":\"1\",\"heats\":\"3\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"0\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"%D2%E6%BC%D2%C8%CB\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"804582\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"1\",\"dbdateline\":\"1440991192\",\"dblastpost\":\"1440991192\",\"id\":\"normalthread_804582\",\"rushreply\":\"0\"},{\"tid\":\"798504\",\"fid\":\"142\",\"posttableid\":\"0\",\"typeid\":\"105\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"\\u5f20\\u751c\",\"authorid\":\"634825\",\"subject\":\"\\u4e00\\u7ebf\\u54c1\\u724c\\uff08\\u65f6\\u5c1a\\u884c\\u4e1a\\uff09\\u5b9e\\u4e60\\u751f\\u62db\\u8058\",\"dateline\":\"3 \\u5929\\u524d<\\/span>\",\"lastpost\":\"10 \\u5c0f\\u65f6\\u524d<\\/span>\",\"lastposter\":\"benny_yang621\",\"views\":\"108\",\"replies\":\"2\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"0\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"0\",\"recommend_add\":\"1\",\"recommend_sub\":\"1\",\"heats\":\"4\",\"status\":\"34\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"3\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"benny_yang621\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"798504\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440731140\",\"dblastpost\":\"1440991126\",\"id\":\"normalthread_798504\",\"rushreply\":\"0\"},{\"tid\":\"802370\",\"fid\":\"142\",\"posttableid\":\"0\",\"typeid\":\"105\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"471518270\",\"authorid\":\"639842\",\"subject\":\"\\u6ce8\\u518c\\u5e94\\u7528\\u62a5\\u916c20\\u5143\\u30025\\u5206\\u949f\\u53ef\\u5b8c\\u6210\",\"dateline\":\"\\u524d\\u5929 23:54<\\/span>\",\"lastpost\":\"\\u524d\\u5929 23:54<\\/span>\",\"lastposter\":\"471518270\",\"views\":\"51\",\"replies\":\"0\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"2\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"2\",\"recommend_sub\":\"1\",\"heats\":\"3\",\"status\":\"34\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"20\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"0\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"471518270\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"802370\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440863650\",\"dblastpost\":\"1440863650\",\"id\":\"normalthread_802370\",\"rushreply\":\"0\"},{\"tid\":\"796783\",\"fid\":\"54\",\"posttableid\":\"0\",\"typeid\":\"49\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"\\u6e05-Windy\",\"authorid\":\"222428\",\"subject\":\"\\u672a\\u6765\\u7684\\u6280\\u672f\\u5927\\u725b\\u3001\\u8bbe\\u8ba1\\u5927\\u89e6\\u3001\\u8fd0\\u8425\\u5927\\u795e\\uff0c\\u5feb\\u5230\\u4f73\\u5b66\\u7897\\u91cc\\u6765~~\",\"dateline\":\"4 \\u5929\\u524d<\\/span>\",\"lastpost\":\"\\u524d\\u5929 23:51<\\/span>\",\"lastposter\":\"471518270\",\"views\":\"199\",\"replies\":\"2\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"2\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"2\",\"recommend_add\":\"2\",\"recommend_sub\":\"0\",\"heats\":\"4\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"-1\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"3\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"471518270\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"796783\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440660281\",\"dblastpost\":\"1440863481\",\"id\":\"normalthread_796783\",\"rushreply\":\"0\"},{\"tid\":\"802272\",\"fid\":\"54\",\"posttableid\":\"0\",\"typeid\":\"49\",\"sortid\":\"0\",\"readperm\":\"0\",\"price\":\"0\",\"author\":\"\\u4f73\\u5b66jaxus\",\"authorid\":\"639829\",\"subject\":\"\\u3010\\u5168\\u804c\\/\\u5b9e\\u4e60\\u3011\\u62db\\u5175\\u4e70\\u9a6c\\uff0c\\u9c9c\\u8089\\u5feb\\u5230\\u3010\\u4f73\\u5b66\\u3011\\u6765\",\"dateline\":\"\\u524d\\u5929 16:22<\\/span>\",\"lastpost\":\"\\u524d\\u5929 23:50<\\/span>\",\"lastposter\":\"471518270\",\"views\":\"104\",\"replies\":\"1\",\"displayorder\":\"0\",\"highlight\":\"\",\"digest\":\"0\",\"rate\":\"0\",\"special\":\"0\",\"attachment\":\"2\",\"moderated\":\"0\",\"closed\":\"0\",\"stickreply\":\"0\",\"recommends\":\"1\",\"recommend_add\":\"2\",\"recommend_sub\":\"1\",\"heats\":\"3\",\"status\":\"32\",\"isgroup\":\"0\",\"favtimes\":\"0\",\"sharetimes\":\"0\",\"stamp\":\"-1\",\"icon\":\"20\",\"pushedaid\":\"0\",\"cover\":\"0\",\"replycredit\":\"0\",\"relatebytag\":\"0\",\"maxposition\":\"2\",\"bgcolor\":\"\",\"comments\":\"0\",\"hidden\":\"0\",\"lastposterenc\":\"471518270\",\"multipage\":\"\",\"recommendicon\":\"\",\"heatlevel\":\"0\",\"moved\":\"0\",\"icontid\":\"802272\",\"folder\":\"common\",\"weeknew\":\"\",\"istoday\":\"0\",\"dbdateline\":\"1440836537\",\"dblastpost\":\"1440863459\",\"id\":\"normalthread_802272\",\"rushreply\":\"0\"}],\"status\":\"success\"}";
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<HmtForumData>() {
                        }.getType();
                        hmtForumData = gson.fromJson(json, type);
                        RefreshHmtListView();
                    }
                });
        mRequestQueue.add(mJsonRequest);
    }

    private void RefreshHmtListView() {
        if (initHmtForumListViewAdapter == null)
            initHmtForumListView();                             //第一次初始化控件
        else
            initHmtForumListViewAdapter.notifyDataSetChanged();//之后只需更新数据

        lo_swiper.setRefreshing(false);
    }

}
