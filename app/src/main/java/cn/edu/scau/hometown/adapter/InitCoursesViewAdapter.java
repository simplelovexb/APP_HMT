package cn.edu.scau.hometown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.AllCourses;
import cn.edu.scau.hometown.interfac.SearchMethod;

/**
 * Created by Administrator on 2015/7/21 0021.
 * 用于渲染课程列表，填充搜索后得到的课程列表视图的Adapter类
 */
public class InitCoursesViewAdapter extends BaseAdapter {
    private Context mContext;
    private AllCourses mAllCourse;
    private SearchMethod searchMethodInterface;

    public InitCoursesViewAdapter(Context mContext, AllCourses mAllCourse, SearchMethod searchMethodInterface) {
        this.mAllCourse = mAllCourse;
        this.mContext = mContext;
        this.searchMethodInterface = searchMethodInterface;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_slide_expandable, null);
            holder.tv_course_id = (TextView) convertView.findViewById(R.id.tv_course_id);
            holder.tv_course_score = (TextView) convertView.findViewById(R.id.tv_course_score);
            holder.tv_course_place = (TextView) convertView.findViewById(R.id.tv_course_place);
            holder.tv_course_type = (TextView) convertView.findViewById(R.id.tv_course_type);
            holder.tv_course_name = (TextView) convertView.findViewById(R.id.course_name);
            holder.tv_course_teacher = (TextView) convertView.findViewById(R.id.course_teacher);
            holder.btn_searchComment = (Button) convertView.findViewById(R.id.circularButton1);
            holder.tv_full_course_name=(TextView)convertView.findViewById(R.id.tv_full_course_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final AllCourses.DataEntity course = mAllCourse.getData().get(position);
        holder.tv_course_id.setText("课程ID:" + course.getID());
        holder.tv_course_name.setText("课程:" + course.getClass_Name());
        holder.tv_full_course_name.setText("课程全称:" + course.getClass_Name());
        holder.tv_course_type.setText("课程归属:" + course.getClass_Sex());
        holder.tv_course_score.setText("课程学分:" + course.getClass_Score());
        holder.tv_course_teacher.setText("教师:" + course.getClass_Teacher());
        holder.tv_course_place.setText("开课学院:" + course.getClass_Collage());
        holder.btn_searchComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMethodInterface.searchCommentTask(course);
            }
        });

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public int getCount() {
        if (mAllCourse.getData() == null) return 0;
        return mAllCourse.getData().size();
    }


    private static class ViewHolder {
        Button btn_searchComment;
        TextView tv_course_id;
        TextView tv_course_score;
        TextView tv_course_place;
        TextView tv_course_type;
        TextView tv_course_name;
        TextView tv_course_teacher;
        TextView tv_full_course_name;
    }
}

