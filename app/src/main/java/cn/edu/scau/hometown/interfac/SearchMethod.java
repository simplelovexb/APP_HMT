package cn.edu.scau.hometown.interfac;

import android.view.View;

import cn.edu.scau.hometown.bean.AllCourses;

/**
 * Created by Administrator on 2015/7/21 0021.
 */
public interface SearchMethod {
    void searchCourseByKeywordTask(View v);

    void searchCommentTask(AllCourses.DataEntity course);
}
