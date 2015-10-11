package cn.edu.scau.hometown.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.adapter.NewInitCommentsViewAdapter;
import cn.edu.scau.hometown.bean.AllComment;

/**
 * Created by laisixiang on 2015/10/4.
 */
public class NewShowCommentsActivity extends Activity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewHeader header;

    private RecyclerViewHeader mCardView;

    private AllComment mAllComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_new);

        mAllComments = (AllComment) getIntent().getSerializableExtra("评论");

        initCardView();
        initRecyclerView();
    }

    private void initCardView(){
        mCardView = RecyclerViewHeader.fromXml(this, R.layout.cardview_item_course_head);
        //mCardView = (CardView) findViewById(R.id.cardview_item_course);
        ((TextView) mCardView.findViewById(R.id.course_name_new)).setText(mAllComments.getCourse().getClass_Name());
        ((TextView) mCardView.findViewById(R.id.course_teacher_new)).setText(mAllComments.getCourse().getClass_Teacher());
        ((TextView) mCardView.findViewById(R.id.course_owner_new)).setText(mAllComments.getCourse().getClass_Sex());
        ((TextView) mCardView.findViewById(R.id.course_college_new)).setText(mAllComments.getCourse().getClass_Collage());
        ((TextView) mCardView.findViewById(R.id.course_score_new)).setText(" "+mAllComments.getCourse().getClass_Score()+"学分");
        ((TextView) mCardView.findViewById(R.id.course_count_new)).setText(mAllComments.getComment().size()+" 条评论");
        mCardView.findViewById(R.id.button_writecommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"writer come up",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_commment_new);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mCardView.attachTo(mRecyclerView);

        mAdapter = new NewInitCommentsViewAdapter(mAllComments);
        mRecyclerView.setAdapter(mAdapter);
    }
}
