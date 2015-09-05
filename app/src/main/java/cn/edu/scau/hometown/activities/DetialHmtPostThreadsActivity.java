package cn.edu.scau.hometown.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.adapter.InitDetailHmtForumListViewAdapter;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2015/9/2 0002.
 */
public class DetialHmtPostThreadsActivity extends SwipeBackActivity implements View.OnClickListener {
    private ListView lv_detail_post_threads;
    private RelativeLayout back_1;
    private SwipeBackLayout mSwipeBackLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detialhmtpostthreads);

        //向右滑动销毁Activity用到的操作
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setScrimColor(Color.TRANSPARENT);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);



        lv_detail_post_threads = (ListView) findViewById(R.id.lv_detail_post_threads);
        lv_detail_post_threads.setAdapter(new InitDetailHmtForumListViewAdapter(this));

        back_1= (RelativeLayout) findViewById(R.id.back_1);
        back_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_1:
                this.finish();
                break;

    }}

    /**
     * 向右滑动销毁Activity用到的操作
     */
    @Override
    public void onBackPressed() {

        scrollToFinishActivity();
    }
}
