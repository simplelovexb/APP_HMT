package cn.edu.scau.hometown.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.HmtForumData;
import cn.edu.scau.hometown.tools.HttpUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2015/8/31 0031.
 * 用于渲染论坛帖子列表，填充帖子列表视图的Adapter类
 */
public class InitHmtForumListViewAdapter extends BaseAdapter {
    private Context mContext;
    private HmtForumData hmtForumData;
    private RequestQueue mRequestQueue;

    public InitHmtForumListViewAdapter(Context mContext, HmtForumData hmtForumData) {
        this.mContext = mContext;
        this.hmtForumData = hmtForumData;
        mRequestQueue= Volley.newRequestQueue(this.mContext);


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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_hmt_forum_, null);
            holder.tv_title_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_titile_of_forum_threads);
            holder.tv_content_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_content_of_forum_threads);
            holder.tv_replies_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_replies_of_forum_threads);
            holder.tv_author_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_author_of_forum_threads);
            holder.tv_lastpost_of_forum_threads = (TextView) convertView.findViewById(R.id.tv_lastpost_of_forum_threads);
            holder.civ_icon_of_forum_threads = (CircleImageView) convertView.findViewById(R.id.tv_icon_of_forum_threads);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HmtForumData.ThreadlistEntity threadlistEntity =hmtForumData.getThreadlist().get(position);


        holder.tv_title_of_forum_threads.setText(threadlistEntity.getSubject());
        holder.tv_replies_of_forum_threads.setText(threadlistEntity.getReplies());
        holder.tv_author_of_forum_threads.setText(threadlistEntity.getAuthor());
        String lastpost =threadlistEntity.getLastpost();
        if (lastpost.contains("&nbsp;"))
            lastpost = lastpost.substring(0, lastpost.indexOf("n") - 1) + lastpost.substring(lastpost.indexOf(";") + 1);
        holder.tv_lastpost_of_forum_threads.setText(lastpost);


        String authorId=threadlistEntity.getAuthorid();
        HttpUtil.setUserIconTask(mRequestQueue,HttpUtil.GET_USER_ICON_BY_USER_ID+authorId, holder.civ_icon_of_forum_threads);
        return convertView;
    }

    private static class ViewHolder {
        private TextView tv_title_of_forum_threads;
        private TextView tv_content_of_forum_threads;
        private TextView tv_replies_of_forum_threads;
        private TextView tv_author_of_forum_threads;
        private TextView tv_lastpost_of_forum_threads;
        private CircleImageView civ_icon_of_forum_threads;


    }



}