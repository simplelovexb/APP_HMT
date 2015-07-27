package cn.edu.scau.hometown.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.fragment.HmtForumFragment;
import cn.edu.scau.hometown.fragment.SecondaryMarketFragment;
import cn.edu.scau.hometown.fragment.MineFragment;
import cn.edu.scau.hometown.fragment.MonmentFragment;

/**
 * Created by Administrator on 2015/7/26 0026.
 */
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("二手市场", HmtForumFragment.class)
                .add("红满堂", SecondaryMarketFragment.class)
                .add("个人信息", MineFragment.class)
                .add("动态界面", MonmentFragment.class)
                .add("动态界面", MonmentFragment.class)
                .add("动态界面", MonmentFragment.class)
                .add("动态界面", MonmentFragment.class)
                .create());
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
       final  SmartTabLayout viewPagerTab= (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


               if(position==0) viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_red));
               if(position==1)  viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_green));
               if(position==2)  viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_purple));
               if(position==3)  viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_blue));
               if(position==4)  viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_green));
               if(position==5)  viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_orange));
               if(position==6)  viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_gray));

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
