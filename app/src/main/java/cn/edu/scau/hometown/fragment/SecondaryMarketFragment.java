package cn.edu.scau.hometown.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.adapter.InitUsedMarketListAdapter;
import cn.edu.scau.hometown.bean.UsedGoodsBaseData;


/**
 * Created by acer on 2015/7/24.
 * @author simple
 */
public class SecondaryMarketFragment extends Fragment {

    private RadioGroup rg;
    private RadioButton rb_home;
    List<UsedGoodsBaseData> data = new ArrayList<>();
    private SwipeRefreshLayout lo_swiper;
    private View view;
    private ListView listView;
    //存储首页轮播的界面
    private ArrayList<View> allListView;
    // 相片的载入
    InitUsedMarketListAdapter adapter;

    private PopupWindow pwMyPopWindow;// popupwindow
    List<Map<String, String>> moreList;
    private ListView lvPopupList;// popupwindow中的ListView
    private int NUM_OF_VISIBLE_LIST_ROWS =4;// 指定popupwindow中Item的数量



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_secondary_market,container,false);
        iniData();
        iniPopupWindow();
        initRedioGroup();
        initLayoutSwiper();
        initListView();
        return  view;
    }

    private void iniData() {

        moreList = new ArrayList<>();
        Map<String, String> map;
        map = new HashMap<>();
        map.put("share_key", "数码类");
        moreList.add(map);
        map = new HashMap<>();
        map.put("share_key", "二手单车");
        moreList.add(map);
        map = new HashMap<>();
        map.put("share_key", "旧书摊");
        moreList.add(map);
        map = new HashMap<>();
        map.put("share_key", "日常用品");
        moreList.add(map);
        map = new HashMap<>();
        map.put("share_key", "招聘兼职");
        moreList.add(map);
        map = new HashMap<>();
        map.put("share_key", "房屋租赁");
        moreList.add(map);
        map = new HashMap<>();
        map.put("share_key", "其他");
        moreList.add(map);
    }
    @SuppressLint("InflateParams")
    @SuppressWarnings("deprecation")
    private void iniPopupWindow() {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.task_detail_popupwindow, null);
        lvPopupList = (ListView) layout.findViewById(R.id.lv_popup_list);
        pwMyPopWindow = new PopupWindow(layout);
        pwMyPopWindow.setFocusable(true);// 加上这个popupwindow中的ListView才可以接收点击事件

        lvPopupList.setAdapter(new SimpleAdapter(getActivity(), moreList,
                R.layout.list_item_popupwindow, new String[]{"share_key"},
                new int[]{R.id.tv_list_item}));
        lvPopupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(getActivity(),
                        moreList.get(position).get("share_key"),
                        Toast.LENGTH_LONG).show();
                listView.smoothScrollToPosition(0);
            }
        });

        // 控制popupwindow的宽度和高度自适应
        lvPopupList.measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        pwMyPopWindow.setWidth(lvPopupList.getMeasuredWidth());
        pwMyPopWindow.setHeight((lvPopupList.getMeasuredHeight())
                * NUM_OF_VISIBLE_LIST_ROWS);

        // 控制popupwindow点击屏幕其他地方消失

        pwMyPopWindow.setBackgroundDrawable(this.getResources().getDrawable(
                R.drawable.bg_popub_window));// 设置背景图片，不能在布局中设置，要通过代码来设置
        pwMyPopWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失。这个要求你的popupwindow要有背景图片才可以成功，如上
    }

    private void initRedioGroup(){

        rg = (RadioGroup)view.findViewById(R.id.rg);
        rb_home= (RadioButton)view.findViewById(R.id.rb_home);
        rb_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwMyPopWindow.isShowing()) {

                    pwMyPopWindow.dismiss();// 关闭
                } else {
                    int yoff = pwMyPopWindow.getHeight() + rg.getHeight();
                    pwMyPopWindow.showAsDropDown(rb_home, -20, -yoff);// 显示
                }
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            }
        });
    }

    private void  initListView(){
        listView = (ListView) view.findViewById(R.id.lv_used_maket);
//        View lvHead = getActivity().getLayoutInflater().inflate(R.layout.listview_head_used_market, null);
//        listView.addHeaderView(lvHead);
        View lvFoot = getActivity().getLayoutInflater().inflate(R.layout.listview_foot_used_market,null);
        lvFoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refleshMoreData(data.size(), 10);
            }
        });
        listView.addFooterView(lvFoot);

        for (int i = 0 ; i <10;i++){

            data.add(new UsedGoodsBaseData());
        }
        adapter = new InitUsedMarketListAdapter(getActivity(),data);
        listView.setAdapter(adapter);
    }

    private void refleshMoreData(int size,int num){
        for (int i = size;i<size+num;i++){

            data.add(new UsedGoodsBaseData());
        }

        adapter.notifyDataSetChanged();
    }


    private void initLayoutSwiper() {
        lo_swiper = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container_used_market);
        lo_swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
            }
        });

        lo_swiper.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


}
