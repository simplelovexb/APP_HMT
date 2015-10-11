package cn.edu.scau.hometown.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.adapter.InitDetailHmtForumListViewAdapter;
import cn.edu.scau.hometown.bean.HmtForumPostContent;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2015/9/2 0002.
 * 展示详细红满堂论坛回帖信息的类
 */
public class DetialHmtPostThreadsActivity extends SwipeBackActivity implements View.OnClickListener {
    //展示回帖列表的listview
    private RecyclerView lv_detail_post_threads;
    //自定义的用于返回上一级的View
    private RelativeLayout back_1;
    //下拉刷新布局
    private SwipeBackLayout mSwipeBackLayout;
    //存放所有相关数据的类
    private HmtForumPostContent hmtForumPostContent;
    //采用Volley网络通信库时需创建的请求排队对象
    private RequestQueue requestQueue;
    //帖子的标题
    private TextView post_subject;
    private String tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_hmt_post_threads);


        requestQueue = Volley.newRequestQueue(DetialHmtPostThreadsActivity.this);

        hmtForumPostContent = (HmtForumPostContent) getIntent().getSerializableExtra("hmtForumPostContent");
        tid = (String) getIntent().getSerializableExtra("tid");

        String subject = hmtForumPostContent.getThread().getSubject();
        post_subject = (TextView) findViewById(R.id.post_subject);
        post_subject.setText(subject);




        findViews();
        initPostThreadsListView();
        initSwipeBackLayout();
        setListener();

    }

    /**
     * 用于初始化回帖列表
     */
    private void initPostThreadsListView() {


        lv_detail_post_threads.setLayoutManager(new LinearLayoutManager(this));
        InitDetailHmtForumListViewAdapter adapter = new InitDetailHmtForumListViewAdapter(this, hmtForumPostContent, tid);
        lv_detail_post_threads.setAdapter(adapter);
    }

    /**
     * 用于初始化向右滑动销毁Activity用到的操作
     */
    private void initSwipeBackLayout() {

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setScrimColor(Color.TRANSPARENT);
    }

    /**
     * 是指各种监听事件
     */
    private void setListener() {
        back_1.setOnClickListener(this);
    }

    /**
     * 视图控件初始化
     */
    private void findViews() {
        lv_detail_post_threads = (RecyclerView) findViewById(R.id.lv_detail_post_threads);
        back_1 = (RelativeLayout) findViewById(R.id.back_1);
    }

    /**
     * 监听实际触发了什么事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back_1:
                this.finish();
                break;

        }
    }

    /**
     * 向右滑动销毁Activity用到的操作
     */
    @Override
    public void onBackPressed() {

        scrollToFinishActivity();
    }


}

