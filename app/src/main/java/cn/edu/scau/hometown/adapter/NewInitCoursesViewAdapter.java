package cn.edu.scau.hometown.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.AllCourses;
import cn.edu.scau.hometown.interfac.SearchMethod;

/**
 * Created by laisixiang on 2015/10/5.
 */
public class NewInitCoursesViewAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private AllCourses mAllCourse;
    private SearchMethod searchMethodInterface;

    public NewInitCoursesViewAdapter(Context mContext,AllCourses mAllCourse, SearchMethod searchMethodInterface){
        this.mContext = mContext;
        this.mAllCourse = mAllCourse;
        this.searchMethodInterface = searchMethodInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_course,null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AllCourses.DataEntity course = mAllCourse.getData().get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.position =  position; //在此得到位置，创建的时候做个标示
        viewHolder.tv_course_name.setText( course.getClass_Name());
        viewHolder.tv_course_teacher.setText( course.getClass_Teacher());
        viewHolder.tv_course_owner.setText( course.getClass_Sex());
        viewHolder.tv_course_college.setText(course.getClass_Collage());
        viewHolder.tv_course_scroe.setText("(" + course.getClass_Score()+"学分)");
//        viewHolder.tv_course_count.setText(mAllCourse.getCount()+"条评论");
    }

    @Override
    public int getItemCount() {
        return mAllCourse.getCount();
    }



    /**
     * ViewHolder
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_course_name;
        public TextView tv_course_teacher;
        public TextView tv_course_owner;
        public TextView tv_course_college;
        public TextView tv_course_scroe;
        public TextView tv_course_count;
        public View rootView;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_course_name = (TextView) itemView.findViewById(R.id.course_name_new);
            tv_course_teacher = (TextView) itemView.findViewById(R.id.course_teacher_new);
            tv_course_owner = (TextView) itemView.findViewById(R.id.course_owner_new);
            tv_course_college = (TextView) itemView.findViewById(R.id.course_college_new);
            tv_course_scroe = (TextView) itemView.findViewById(R.id.course_score_new);
            tv_course_count = (TextView) itemView.findViewById(R.id.course_count_new);
            rootView =  itemView.findViewById(R.id.cardview_item_course);
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            searchMethodInterface.searchCommentTask(mAllCourse.getData().get(position));
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
