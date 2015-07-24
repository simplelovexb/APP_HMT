package cn.edu.scau.hometown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.AllComment;

/**
 * Created by Administrator on 2015/7/21 0021.
 */
public class InitCommentsViewAdapter extends BaseAdapter{
    private Context mContext;
    private AllComment mAllComments;
    private List<AllComment.CommentEntity> commentList;

    public InitCommentsViewAdapter(Context mContext,AllComment mAllComments){
        this.mContext=mContext;
        this.mAllComments=mAllComments;
        commentList=mAllComments.getComment();
    }



    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent) {


        if(position==0){View v= LayoutInflater.from(mContext).inflate(R.layout.comment_list_head,null);
            TextView tv_course_score = (TextView) v.
                    findViewById(R.id.tv_comment_course_score);
            TextView tv_course_place = (TextView)
                    v.findViewById(R.id.tv_comment_course_colleage);
            TextView tv_course_type = (TextView)
                    v.findViewById(R.id.tv_comment_course_sex);
            TextView tv_course_name = (TextView)
                    v.findViewById(R.id.tv_comment_course_name);
            TextView tv_course_teacher = (TextView)
                    v.findViewById(R.id.tv_comment_course_teacher);

            tv_course_name.setText("课程名稱:" + mAllComments.getCourse().getClass_Name());
            tv_course_type.setText("课程归属:" + mAllComments.getCourse().getClass_Sex());
            tv_course_score.setText("课程学分:" + mAllComments.getCourse().getClass_Score());
            tv_course_teacher.setText("任課教师:" + mAllComments.getCourse().getClass_Teacher());
            tv_course_place.setText("开课学院:" +mAllComments.getCourse().getClass_Collage());
            int number=(commentList==null)?0:commentList.size();
            TextView tv_comment_number=(TextView) v.findViewById(R.id.tv_comment_muber);
            tv_comment_number.setText("共"+number+"条评论");
            return v;


        }
        View v =LayoutInflater.from(mContext).inflate(
                R.layout.comment_list_item, null);

        if(mAllComments.getComment()!=null)
        {AllComment.CommentEntity comment=mAllComments.getComment().get(position-1);


            TextView tv_comment_position=(TextView) v.findViewById(R.id.comment_position);
            tv_comment_position.setText("第"+((int)position)+"条评论");
            TextView tv_comment_class_check=(TextView)v.findViewById(R.id.tv_comment_class_check);
            TextView tv_comment_class_homework=(TextView)v.findViewById(R.id.tv_comment_class_homework);
            TextView tv_comment_class_exam=(TextView)v.findViewById(R.id.tv_comment_class_exam);
            TextView tv_comment_class_score=(TextView)v.findViewById(R.id.tv_comment_class_score);
            TextView tv_comment_class_user=(TextView)v.findViewById(R.id.tv_comment_class_user);
            TextView tv_comment_time=(TextView)v.findViewById(R.id.tv_comment_time);
            TextView tv_comment_class_select=(TextView) v.findViewById(R.id.tv_comment_class_select);
            TextView tv_comment_class_ex=(TextView) v.findViewById(R.id.tv_comment_class_ex);
            tv_comment_class_user.setText("点评人    ："+comment.getClass_User());
            tv_comment_class_check.setText("点名情况："+comment.getClass_Check());
            tv_comment_class_homework.setText("平时作业："+comment.getClass_Homework());
            tv_comment_class_exam.setText("考试形式："+comment.getClass_Exam());
            tv_comment_class_score.setText("期末成绩："+comment.getClass_Score());
            tv_comment_time.setText("点评时间："+comment.getClass_Comment_Time());
            tv_comment_class_select.setText("选修学期："+comment.getClass_Select());
            tv_comment_class_ex.setText("其他说明："+comment.getClass_Ex());

        }
        return v;
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
        return (commentList==null)?1:commentList.size()+1;
    }

}

