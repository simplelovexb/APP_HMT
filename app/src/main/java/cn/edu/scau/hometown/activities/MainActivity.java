package cn.edu.scau.hometown.activities;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.fragment.HmtForumFragment;
import cn.edu.scau.hometown.fragment.MineFragment;
import cn.edu.scau.hometown.fragment.MonmentFragment;
import cn.edu.scau.hometown.fragment.SecondaryMarketFragment;

/**
 * Created by Administrator on 2015/7/26 0026.
 */
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("二手市场", SecondaryMarketFragment.class)
                .add("红满堂", HmtForumFragment.class)
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

                ArgbEvaluator evaluator = new ArgbEvaluator();
                if (position== 0) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_red));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_pink),getResources().getColor(R.color.tab_red));
                    viewPagerTab.setBackgroundColor(evaluate);
                }
                if(0<position&&position<1)
                {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_red));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_red),getResources().getColor(R.color.tab_pink));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if (position== 1) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_purple));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_red),getResources().getColor(R.color.tab_purple));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if(1<position&&position<2)
                {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_purple));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_purple),getResources().getColor(R.color.tab_red));
                    viewPagerTab.setBackgroundColor(evaluate);
                }


                if (position== 2) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_blue));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_purple),getResources().getColor(R.color.tab_blue));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if(2<position&&position<3)
                {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_blue));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_blue),getResources().getColor(R.color.tab_purple));
                    viewPagerTab.setBackgroundColor(evaluate);
                }


                if (position== 3) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_green));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_blue),getResources().getColor(R.color.tab_green));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if(3<position&&position<4)
                {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_green));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_green),getResources().getColor(R.color.tab_blue));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if (position== 4) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_grey));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_green),getResources().getColor(R.color.tab_grey));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if(4<position&&position<5)
                {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_grey));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_grey),getResources().getColor(R.color.tab_green));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if (position== 5) {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_gray));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_grey),getResources().getColor(R.color.tab_gray));
                    viewPagerTab.setBackgroundColor(evaluate);
                }

                if(5<position&&position<6)
                {
                    viewPagerTab.setBackgroundColor(getResources().getColor(R.color.tab_gray));
                    int evaluate = (Integer) evaluator.evaluate(positionOffset,getResources().getColor(R.color.tab_gray),getResources().getColor(R.color.tab_grey));
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
