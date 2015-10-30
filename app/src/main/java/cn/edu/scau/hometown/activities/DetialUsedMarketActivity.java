package cn.edu.scau.hometown.activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.adapter.DetialUsedMarketAdapter;
import cn.edu.scau.hometown.bean.Collection;
import cn.edu.scau.hometown.bean.GoodsInfo;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


public class DetialUsedMarketActivity extends SwipeBackActivity {
    private ViewPager pagers;
    private RadioGroup dots;
    private Toolbar toolbar ;
    private Intent intent;
    private RelativeLayout back_1;
    private SwipeBackLayout mSwipeBackLayout;
    private TextView secondgoods_name,secondgoods_hownew,
            secondgoods_price,secondgoods_delivertype,
            secondgoods_paidtype,secondgoods_goodsnums,
            secondgoods_bewrite,secondgoods_efficiency,
            secondgoods_postdate,secondgoods_pastdate,
            secondgoods_tradetype,
            members_username,members_mobie,members_qqnum;
    private int[] imageIds = new int[]{
            R.drawable.menu_1,
            R.drawable.menu_1_0,
            R.drawable.menu_1_1,
            R.drawable.menu_1_2,
            R.drawable.menu_1_3
    };
    private List images;
    private Collection collection;
    private String secondgoods_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detial_used_market);
        findView();
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        GoodsInfo goodsInfo = (GoodsInfo) bundle.getSerializable("GoodInfo");
        if (goodsInfo!=null)
            setData(goodsInfo);
        collection = bundle.getParcelable("collection");
        secondgoods_id = goodsInfo.getSecondgoods_id().toString();
        changeCollectionBtn(secondgoods_id);
        initSwipeBackLayout();
        pagers = (ViewPager) findViewById(R.id.viewpager);
        dots = (RadioGroup) findViewById(R.id.radiogroup);
        images = new ArrayList<>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        pagers.setAdapter(new DetialUsedMarketAdapter(images));
        pagers.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                ((RadioButton) dots.getChildAt(i)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        findViewById(R.id.btn_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(collection.isId(secondgoods_id)) collection.setCollection(secondgoods_id);
                else collection.cancelCollection(secondgoods_id);
                changeCollectionBtn(secondgoods_id);
                Toast.makeText(getApplicationContext(),secondgoods_id,Toast.LENGTH_SHORT).show();
            }
        });
        back_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findView(){
        members_username = (TextView) findViewById(R.id.members_username);
        secondgoods_hownew = (TextView) findViewById(R.id.secondgoods_hownew);
        secondgoods_price = (TextView) findViewById(R.id.secondgoods_price);
        secondgoods_delivertype = (TextView) findViewById(R.id.secondgoods_delivertype);
        secondgoods_paidtype = (TextView) findViewById(R.id.secondgoods_paidtype);
        secondgoods_goodsnums = (TextView) findViewById(R.id.secondgoods_goodsnums);
        secondgoods_bewrite = (TextView) findViewById(R.id.secondgoods_bewrite);
        secondgoods_efficiency = (TextView) findViewById(R.id.secondgoods_efficiency);
        secondgoods_pastdate = (TextView) findViewById(R.id.secondgoods_pastdate);
        secondgoods_tradetype = (TextView) findViewById(R.id.secondgoods_tradetype);
        members_mobie = (TextView) findViewById(R.id.members_mobie);
        members_qqnum = (TextView) findViewById(R.id.members_qqnum);

        back_1 = (RelativeLayout) findViewById(R.id.back_1);
    }

    private void setData(GoodsInfo data){
        secondgoods_price.setText(data.getSecondgoods_price()+" ￥");
        secondgoods_hownew.setText(data.getSecondgoods_hownew());
        secondgoods_bewrite.setText(data.getSecondgoods_bewrite());
        secondgoods_delivertype.setText(data.getSecondgoods_delivertype());
        secondgoods_efficiency.setText(data.getSecondgoods_efficiency());
        secondgoods_paidtype.setText(data.getSecondgoods_paidtype());
        secondgoods_goodsnums.setText(data.getSecondgoods_goodsnums()+"");
        secondgoods_pastdate.setText(data.getSecondgoods_pastdate());
        secondgoods_tradetype.setText(data.getSecondgoods_tradetype());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initSwipeBackLayout() {

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setScrimColor(Color.TRANSPARENT);
    }

    private void changeCollectionBtn(String secondgoods_id){
        if(collection.isId(secondgoods_id)){
            /*
            收藏按钮图片切换为已收藏
             */
        }
        else{
            /*
            收藏按钮图片切换为未收藏
             */
        }
    }
}
