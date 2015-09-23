package cn.edu.scau.hometown.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.adapter.InitDetailHmtForumListViewAdapter;
import cn.edu.scau.hometown.bean.HmtForumPostContent;
import cn.edu.scau.hometown.tools.HttpUtil;
import cn.edu.scau.hometown.tools.StringUtils;
import de.hdodenhof.circleimageview.CircleImageView;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2015/9/2 0002.
 * 展示详细红满堂论坛回帖信息的类
 */
public class DetialHmtPostThreadsActivity extends SwipeBackActivity implements View.OnClickListener, View.OnLayoutChangeListener {
    //展示回帖列表的listview
    private ListView lv_detail_post_threads;
    //自定义的用于返回上一级的View
    private RelativeLayout back_1;
    //下拉刷新布局
    private SwipeBackLayout mSwipeBackLayout;
    //用于输入对于某一帖子的评论的输入框
    private EditText comment_content;
    //触发添加评论事件视图
    private TextView item_action_comment;
    //输入评论的主布局，只有当用户点击了回复后才会出现
    private LinearLayout area_commit;
    //存放所有相关数据的类
    private HmtForumPostContent hmtForumPostContent;
    //采用Volley网络通信库时需创建的请求排队对象
    private RequestQueue requestQueue;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_hmt_postt_hreads);


        requestQueue= Volley.newRequestQueue(DetialHmtPostThreadsActivity.this);

        hmtForumPostContent= (HmtForumPostContent) getIntent().getSerializableExtra("hmtForumPostContent");

        findViews();
        initPostThreadsListView();
        initSwipeBackLayout();
        setListener();


        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
    }


    /**
     * 用于初始化回帖列表
     */
    private void initPostThreadsListView() {
        lv_detail_post_threads.setAdapter(new InitDetailHmtForumListViewAdapter(this,hmtForumPostContent));
        View headView = getLayoutInflater().inflate(R.layout.listview_head_detail_post_threads, null);


        ImageView imageView= (ImageView) headView.findViewById(R.id.iv_author_logo);
        String url=HttpUtil.GET_USER_ICON_BY_USER_ID+hmtForumPostContent.getThread().getAuthorid();
        HttpUtil.setUserIconTask(requestQueue, url, imageView);

        TextView tv_author_of_post_content= (TextView) headView.findViewById(R.id.tv_author_of_post_content);
        tv_author_of_post_content.setText(hmtForumPostContent.getThread().getAuthor());

        EditText tv_message_of_post_content=(EditText)headView.findViewById(R.id.tv_message_of_post_content);
        tv_message_of_post_content.setText("      "+hmtForumPostContent.getPosts().get(0).getMessage());

        //tv_message_of_post_content.setText(StringUtils.getEmotionContent(DetialHmtPostThreadsActivity.this, tv_message_of_post_content,"      " +hmtForumPostContent.getPosts().get(0).getMessage()));

        item_action_comment = (TextView) headView.findViewById(R.id.item_action_comment);
        lv_detail_post_threads.addHeaderView(headView);
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
        area_commit.addOnLayoutChangeListener(this);
        back_1.setOnClickListener(this);
        item_action_comment.setOnClickListener(this);
    }

    /**
     * 视图控件初始化
     */
    private void findViews() {
        lv_detail_post_threads = (ListView) findViewById(R.id.lv_detail_post_threads);
        back_1 = (RelativeLayout) findViewById(R.id.back_1);
        comment_content = (EditText) findViewById(R.id.comment_content);
        area_commit = (LinearLayout) findViewById(R.id.area_commit);
    }


    /**
     * 监听实际触发了什么事件
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back_1:
                this.finish();
                break;
            case R.id.item_action_comment:
                onClickComment();
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

    /**
     * 点击回复时触发的事件
     */
    private void onClickComment() {
        // TODO Auto-generated method stub
        area_commit.setVisibility(View.VISIBLE);
        comment_content.requestFocus();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(comment_content, 0);
    }


    /**
     * 用于监听软键盘的出现和消失，做到只有当用户点击评论时才会弹出输入框
     * @param v
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param oldLeft
     * @param oldTop
     * @param oldRight
     * @param oldBottom
     */
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {


        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {

            area_commit.setVisibility(View.INVISIBLE);
        }
    }



}
