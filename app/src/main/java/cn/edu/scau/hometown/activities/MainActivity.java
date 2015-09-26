package cn.edu.scau.hometown.activities;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.fragment.HmtForumFragment;
import cn.edu.scau.hometown.fragment.MineFragment;
import cn.edu.scau.hometown.fragment.SecondaryMarketFragment;

/**
 * Created by Administrator on 2015/7/26 0026.
 * 程序已启动时展示的主界面
 */
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("二手市场", SecondaryMarketFragment.class)
                .add("红满堂", HmtForumFragment.class)
                .add("基本设置", MineFragment.class)
                .create());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                ArgbEvaluator evaluator = new ArgbEvaluator();
                if (position == 0) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_blue));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.tab_green), getResources().getColor(R.color.tab_blue));
                    viewPagerTab.setBackgroundColor(evaluate);
                }
                if (0 < position && position < 1) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_blue));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.tab_blue), getResources().getColor(R.color.tab_green));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if (position == 1) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_purple));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.tab_blue), getResources().getColor(R.color.tab_purple));

                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if (1 < position && position < 2) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_purple));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.tab_purple), getResources().getColor(R.color.tab_blue));
                    viewPagerTab.setBackgroundColor(evaluate);
                }


                if (position == 2) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_blue));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.tab_purple), getResources().getColor(R.color.tab_blue));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if (2 < position && position < 3) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_blue));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.tab_blue), getResources().getColor(R.color.tab_purple));
                    viewPagerTab.setBackgroundColor(evaluate);
                }


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
