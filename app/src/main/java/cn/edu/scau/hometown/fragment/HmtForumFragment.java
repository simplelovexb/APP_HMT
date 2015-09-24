package cn.edu.scau.hometown.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.activities.DetialHmtPostThreadsActivity;
import cn.edu.scau.hometown.adapter.InitHmtForumListViewAdapter;
import cn.edu.scau.hometown.bean.HmtForumData;
import cn.edu.scau.hometown.bean.HmtForumPostContent;
import cn.edu.scau.hometown.library.com.javis.abslidingpagerview.AbOnItemClickListener;
import cn.edu.scau.hometown.library.com.javis.abslidingpagerview.AbSlidingPlayView;
import cn.edu.scau.hometown.tools.HttpUtil;


/**
 * Created by acer on 2015/7/24.
 */
public class HmtForumFragment extends Fragment {
    //轮播照片的视图
    private SwipeRefreshLayout lo_swiper;
    private View view;
    //存储首页轮播的界面
    private AbSlidingPlayView vp_ab;
    //首页轮播的界面的资源
    private ArrayList<View> allListView;
    // 相片的载入
    private int[] resId = {R.drawable.menu_viewpager_1, R.drawable.menu_viewpager_2, R.drawable.menu_viewpager_3, R.drawable.menu_viewpager_4, R.drawable.menu_viewpager_5};
     //显示热门帖子的ListView
    private ListView lv_hmt_forum;
    //渲染热门帖子列表视图的自定义Adapter
    private InitHmtForumListViewAdapter initHmtForumListViewAdapter;
    //Volley网络请求会用到的工具
    private RequestQueue mRequestQueue;
    //网络申请得到帖子数据后转化而成的帖子数据类
    private HmtForumData hmtForumData;
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
        initLayoutSwiper();
       // hmtForumData= (HmtForumData) DataUtil.getObject("热门帖子最新数据",getActivity());
        SearchHmtForumDataTask();
        return view;
    }

    /**
     * 初始化下拉刷新的设置
     */
    private void initLayoutSwiper() {
        lo_swiper = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container_hmt_forum);
        lo_swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                SearchHmtForumDataTask();
            }
        });
        lo_swiper.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


    /**
     * 初始化热门论坛照片轮播
     */
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
            String url = "http://hometown.scau.edu.cn/bbs/static/image/common/logo.png";
            //TODO 加载真正要的图片
            setImageForAb(url,imageView);
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
                vp_ab.stopPlay();
                Toast.makeText(getActivity(), "position=" + position, Toast.LENGTH_LONG).show();
                vp_ab.startPlay();
            }
        });
    }

    /**
     * 初始化热门帖子列表视图
     */
    private void initHmtForumListView() {
        lv_hmt_forum = (ListView) view.findViewById(R.id.lv_hmt_forum);
        initHmtForumListViewAdapter = new InitHmtForumListViewAdapter(getActivity(), hmtForumData);
        lv_hmt_forum.setAdapter(initHmtForumListViewAdapter);
        lv_hmt_forum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tid=hmtForumData.getThreadlist().get(position).getTid();
               SearchPostContentTask(tid);
            }
        });
    }

    /**
     * 获取热门帖子列表数据任务
     */
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
                        if(lv_hmt_forum==null)
                        {  initHmtForumListView();}
                        initHmtForumListViewAdapter.notifyDataSetChanged();
                        lo_swiper.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Boolean connected1=HttpUtil.isNetworkConnected(getActivity());
                        Boolean connected2=HttpUtil.isWifiConnected(getActivity());
                        if (connected1==false&&connected2==false)
                            Toast.makeText(getActivity(), "请检查网络！", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(), "(*@ο@*) 哇～  很抱歉！服务器出问题了～", Toast.LENGTH_LONG).show();
                        lo_swiper.setRefreshing(false);

                    }
                });
        mRequestQueue.add(mJsonRequest);
    }

    /**
     * 获取指定的帖子的回帖数据的任务
     * @param tid
     */
    private void SearchPostContentTask(String tid){

        String url=HttpUtil.GET_HMT_FORUM_POSTS_CONTENT_BY_TID+tid;
        JsonObjectRequest mJsonRequest=new JsonObjectRequest(url, null,


                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String json = response.toString();
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<HmtForumPostContent>() {
                        }.getType();
                        HmtForumPostContent hmtForumPostContent= gson.fromJson(json, type);

                        Intent intent=new Intent(getActivity(), DetialHmtPostThreadsActivity.class);
                        intent.putExtra("hmtForumPostContent",hmtForumPostContent);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.keep);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        mRequestQueue.add(mJsonRequest);
    }

    /**
     * 给滚动图片下载图片
     * @param url
     * @param imageView
     */
    private void setImageForAb(String url,ImageView imageView) {

        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        final LruCache<String, Bitmap> mImageCache = new LruCache<String, Bitmap>(20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String key, Bitmap value) {
                mImageCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
                return mImageCache.get(key);
            }
        };
        ImageLoader mImageLoader = new ImageLoader(mRequestQueue, imageCache);
        // imageView是一个ImageView实例
        // ImageLoader.getImageListener的第二个参数是默认的图片resource id
        // 第三个参数是请求失败时候的资源id，可以指定为0
        ImageLoader.ImageListener listener = ImageLoader
                .getImageListener(imageView, android.R.drawable.ic_menu_rotate,
                        android.R.drawable.ic_delete);
        mImageLoader.get(url, listener);
    }
}
