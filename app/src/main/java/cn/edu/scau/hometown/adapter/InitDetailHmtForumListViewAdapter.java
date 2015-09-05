package cn.edu.scau.hometown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.AllCourses;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2015/9/2 0002.
 */
public class InitDetailHmtForumListViewAdapter extends BaseAdapter {
    private Context context;

    public InitDetailHmtForumListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView =LayoutInflater.from(context).inflate(R.layout.detail_post_threads_listview_item, null);
            holder.tv_floor = (TextView) convertView.findViewById(R.id.tv_floor);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_floor.setText("#"+(position+1));

        return convertView;

    }
    private static class ViewHolder {
        private TextView tv_floor;


    }
}
