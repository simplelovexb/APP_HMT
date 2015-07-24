package cn.edu.scau.hometown.provider;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import cn.edu.scau.hometown.R;

/**
 * Created by acer on 2015/7/24.
 */
public class PlusActionProvider extends ActionProvider {


    private Context mContext;

    public PlusActionProvider(Context context) {
        super(context);

        this.mContext = context;
    }

    @Override
    public View onCreateActionView() {

        return  null;
    }




    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {

        subMenu.clear();

        subMenu.add(R.string.plus_group_chat)
                .setIcon(R.drawable.actionbar_group_chat_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Log.i("PlusActionProvider", ".....");
                        return false;
                    }
                });


        subMenu.add(R.string.plus_add_friend)
                .setIcon(R.drawable.actionbar_friedn_add_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Log.i("PlusActionProvider",".....");
                        return false;
                    }
                });


        subMenu.add(R.string.plus_video_chat)
                .setIcon(R.drawable.actionbar_video_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Log.i("PlusActionProvider",".....");
                        return false;
                    }
                });


        subMenu.add(R.string.plus_scan)
                .setIcon(R.drawable.actionbar_group_chat_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Log.i("PlusActionProvider", ".....");
                        return false;
                    }
                });




        super.onPrepareSubMenu(subMenu);
    }


    @Override
    public boolean hasSubMenu() {
        return true;
    }
}
