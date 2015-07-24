package cn.edu.scau.hometown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.activities.LoginWebViewActivity;
import cn.edu.scau.hometown.activities.SearchCourseMainActivity;


/**
 * Created by acer on 2015/7/24.
 */
public class MineFragment extends Fragment {
    private Button btn_login_now;
    private Button btn_search_course;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        btn_login_now=(Button)view.findViewById(R.id.btn_login_now);
        btn_login_now.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginWebViewActivity.class);
                startActivity(i);
            }
        });

        btn_search_course= (Button) view.findViewById(R.id.btn_search_course);
        btn_search_course.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),SearchCourseMainActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}
