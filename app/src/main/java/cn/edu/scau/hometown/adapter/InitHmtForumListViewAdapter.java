package cn.edu.scau.hometown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.AllCourses;
import cn.edu.scau.hometown.bean.HmtForumData;
import cn.edu.scau.hometown.interfac.SearchMethod;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
public class InitHmtForumListViewAdapter extends BaseAdapter {
    private Context mContext;
    private HmtForumData hmtForumData;

    public InitHmtForumListViewAdapter(Context mContext, HmtForumData hmtForumData) {
        this.mContext = mContext;
        this.hmtForumData = hmtForumData;


    }


    @Override
    public int getCount() {
        return Integer.valueOf(hmtForumData.getThreadlist().size());
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.hmt_forum_listview_item, null);
            holder.tv_title_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_titile_of_forum_threads);
            holder.tv_content_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_content_of_forum_threads);
            holder.tv_replies_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_replies_of_forum_threads);
            holder.tv_author_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_author_of_forum_threads);
            holder.tv_lastpost_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_lastpost_of_forum_threads);
            holder.tv_icon_of_forum_threads = (CircleImageView) convertView.findViewById(R.id.tv_icon_of_forum_threads);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tv_title_of_forum_threads.setText(hmtForumData.getThreadlist().get(position).getSubject());
        holder.tv_replies_of_forum_threads.setText(hmtForumData.getThreadlist().get(position).getReplies());
        holder.tv_author_of_forum_threads.setText(hmtForumData.getThreadlist().get(position).getAuthor());
        String lastpost = hmtForumData.getThreadlist().get(position).getLastpost();
        if (lastpost.contains("&nbsp;"))
            lastpost = lastpost.substring(0, lastpost.indexOf("n") - 1) + lastpost.substring(lastpost.indexOf(";") + 1);
        holder.tv_lastpost_of_forum_threads.setText(lastpost);

        if (position % 2 == 0)
            holder.tv_icon_of_forum_threads.setImageResource(R.drawable.picture_1);
        else if (position % 2 == 1)
            holder.tv_icon_of_forum_threads.setImageResource(R.drawable.login_ak);
        //holder.tv_content_of_forum_threads.setText();


        return convertView;
    }

    private static class ViewHolder {
        private TextView tv_title_of_forum_threads;
        private TextView tv_content_of_forum_threads;
        private TextView tv_replies_of_forum_threads;
        private TextView tv_author_of_forum_threads;
        private TextView tv_lastpost_of_forum_threads;
        private CircleImageView tv_icon_of_forum_threads;


    }


}
