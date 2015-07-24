package cn.edu.scau.hometown.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.fragment.ChatFragment;
import cn.edu.scau.hometown.fragment.ContactFragment;
import cn.edu.scau.hometown.fragment.MineFragment;
import cn.edu.scau.hometown.fragment.MonmentFragment;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;

    private ActionBar mActionBar;
    private ArrayList<MyTab> tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOverflowShowAlways();
        setContentView(R.layout.activity_main);
        tabs = new ArrayList<>(4);
        tabs.add(new MyTab(getResources().getString(R.string.chart), ChatFragment.class));
        tabs.add(new MyTab(getResources().getString(R.string.friend), ContactFragment.class));
        tabs.add(new MyTab(getResources().getString(R.string.moment), MonmentFragment.class));
        tabs.add(new MyTab(getResources().getString(R.string.more), MineFragment.class));
        initActionBar();
    }

    private void initActionBar() {


        mViewPager = (ViewPager) this.findViewById(R.id.viewpage);

        mActionBar = getSupportActionBar();

        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (MyTab tab : tabs) {
            ActionBar.Tab t = mActionBar.newTab();
            t.setText(tab.getText());
            t.setTabListener(this);

            mActionBar.addTab(t);

        }
        mViewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));

        mViewPager.setOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mActionBar.selectTab(mActionBar.getTabAt(i));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }


    class MyTab {
        private String text;
        private Class fragement;

        public MyTab(String name, Class fragement) {
            this.text = name;
            this.fragement = fragement;
        }

        public void setText(String name) {
            this.text = name;
        }

        public void setFragement(Class fragement) {
            this.fragement = fragement;
        }

        public String getText() {

            return text;
        }

        public Class getFragement() {
            return fragement;
        }
    }


    class TabFragmentPagerAdapter extends FragmentPagerAdapter {


        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {


            try {

                return (Fragment) tabs.get(i).getFragement().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        public int getCount() {
            return tabs.size();
        }
    }


    private void setOverflowShowAlways() {


        try {

            ViewConfiguration configuration = ViewConfiguration.get(this);
            Field menuKey = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");

            menuKey.setAccessible(true);
            menuKey.setBoolean(configuration, false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {


        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {

                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }


        return super.onMenuOpened(featureId, menu);
    }
}
