package cn.edu.scau.hometown.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.scau.hometown.R;


/**
 * Created by acer on 2015/7/24.
 */
public class HmtForumFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_secondary_market,container,false);
        return  view;
    }
}
