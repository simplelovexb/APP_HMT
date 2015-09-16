package cn.edu.scau.hometown.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.adapter.InitCommentsViewAdapter;
import cn.edu.scau.hometown.bean.AllComment;
import cn.edu.scau.hometown.library.com.tjerkw.slideexpandable.SlideExpandableListAdapter;

/**
 * 用于展示课程评论的类
 */
public class ShowCommentsActivity extends Activity {

    private AllComment mAllComments = null;
    private ListView lv_show_comment;
    private ListAdapter adapter;
    private ListAdapter adapter_slideExpand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mAllComments = (AllComment) getIntent().getSerializableExtra("评论");


        findViews();
        initCommentListView();
    }

    private void findViews() {
        lv_show_comment = (ListView) findViewById(R.id.comment_listview);
    }

    /**
     * 以下方法用于初始化显示查看课程相关评论的ListView的适配器，以及设置每个ListItem项的视图
     */
    private void initCommentListView() {
        adapter = new InitCommentsViewAdapter(this, mAllComments);
        adapter_slideExpand = new SlideExpandableListAdapter(adapter, R.id.comment_expand,
                R.id.comment_hide_view);
        lv_show_comment.setAdapter(adapter_slideExpand);

    }

    /**
     * 用户评论时若未登录时跳转到登录界面
     * @param v 登录按钮
     */
    public void login(View v) {
        Intent i = new Intent(ShowCommentsActivity.this, LoginWebViewActivity.class);
        startActivity(i);
    }

}
