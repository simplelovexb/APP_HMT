package cn.edu.scau.hometown.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.AllComment;

/**
 * Created by laisixiang on 2015/10/4.
 */
public class NewInitCommentsViewAdapter extends RecyclerView.Adapter{
    private AllComment mAllComments;
    private List<AllComment.CommentEntity> commentList;

    public NewInitCommentsViewAdapter(AllComment mAllComments){
        this.mAllComments = mAllComments;
        this.commentList = mAllComments.getComment();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_comment,null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        AllComment.CommentEntity comment=mAllComments.getComment().get(position);
        viewHolder.position =  position; //在此得到位置，创建的时候做个标示
        viewHolder.tv_comment_class_user.setText("点评人    ："+comment.getClass_User());
        viewHolder.tv_comment_class_check.setText("点名情况："+comment.getClass_Check());
        viewHolder.tv_comment_class_homework.setText("平时作业："+comment.getClass_Homework());
        viewHolder.tv_comment_class_exam.setText("考试形式："+comment.getClass_Exam());
        viewHolder.tv_comment_class_score.setText("期末成绩："+comment.getClass_Score());
        viewHolder.tv_comment_time.setText("点评时间："+comment.getClass_Comment_Time());
        viewHolder.tv_comment_class_select.setText("选修学期："+comment.getClass_Select());
        viewHolder.tv_comment_class_ex.setText("其他说明："+comment.getClass_Ex());
    }

    @Override
    public int getItemCount() {
        return (commentList==null)?0:commentList.size();
    }



    /**
     * ViewHolder
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_comment_class_check;
        TextView tv_comment_class_homework;
        TextView tv_comment_class_exam;
        TextView tv_comment_class_score;
        TextView tv_comment_class_user;
        TextView tv_comment_time;
        TextView tv_comment_class_select;
        TextView tv_comment_class_ex;
        public View rootView;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_comment_class_check=(TextView) itemView.findViewById(R.id.tv_comment_class_check_new);
            tv_comment_class_homework=(TextView) itemView.findViewById(R.id.tv_comment_class_homework_new);
            tv_comment_class_exam=(TextView) itemView.findViewById(R.id.tv_comment_class_exam_new);
            tv_comment_class_score=(TextView) itemView.findViewById(R.id.tv_comment_class_score_new);
            tv_comment_class_user=(TextView) itemView.findViewById(R.id.tv_comment_class_user_new);
            tv_comment_time=(TextView) itemView.findViewById(R.id.tv_comment_class_time_new);
            tv_comment_class_select=(TextView) itemView.findViewById(R.id.tv_comment_class_select_new);
            tv_comment_class_ex=(TextView) itemView.findViewById(R.id.tv_comment_class_ex_new);
            rootView =  itemView.findViewById(R.id.cardview_item_comment);
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }
    }


    /**
     * 以下三个方法为完善这个recycleViewAdapter，实现监听
     */
    public static interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}
