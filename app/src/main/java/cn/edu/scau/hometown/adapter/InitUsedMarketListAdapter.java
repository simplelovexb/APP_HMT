package cn.edu.scau.hometown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.UsedGoodsBaseData;

/**
 * Created by acer on 2015/9/20.
 */
public class InitUsedMarketListAdapter extends BaseAdapter {

    private Context mContext;
    private List<UsedGoodsBaseData> datas;

    public InitUsedMarketListAdapter(Context context,List<UsedGoodsBaseData> data){
        mContext = context;
        datas = data;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_listview_ware,null);
            holder = new Holder();
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        return convertView;
    }

    class  Holder{

    }

}
