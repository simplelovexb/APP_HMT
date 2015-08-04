package cn.edu.scau.hometown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.activities.LoginWebViewActivity;
import cn.edu.scau.hometown.activities.SearchCourseMainActivity;
import cn.edu.scau.hometown.bean.HmtUserBasedInfo;


/**
 * Created by acer on 2015/7/24.
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    private Button btn_login_now;
    private Button btn_search_course;
    private int LOGIN_REQUEST_CODE = 11;
    private HmtUserBasedInfo hmtUserBasedInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        btn_login_now = (Button) view.findViewById(R.id.btn_login_now);
        btn_login_now.setOnClickListener(this);

        btn_search_course = (Button) view.findViewById(R.id.btn_search_course);
        btn_search_course.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        hmtUserBasedInfo = (HmtUserBasedInfo) data.getSerializableExtra("LoginResult");
        Toast.makeText(getActivity(), hmtUserBasedInfo.getData().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_now:
                Intent i1 = new Intent(getActivity(), LoginWebViewActivity.class);
                startActivityForResult(i1, LOGIN_REQUEST_CODE);
                break;
            case R.id.btn_search_course:
                Intent i2 = new Intent(getActivity(), SearchCourseMainActivity.class);
                startActivity(i2);
                break;
            default:
                break;

        }

    }
}
