package cn.edu.scau.hometown.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.HmtForumData;
import cn.edu.scau.hometown.bean.HmtForumPostList;
import cn.edu.scau.hometown.tools.DataUtil;
import cn.edu.scau.hometown.tools.HttpUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2015/8/31 0031.
 * 用于渲染论坛帖子列表，填充帖子列表视图的Adapter类
 */
public class InitHmtForumListViewAdapter extends BaseAdapter {
    private Context mContext;
    private HmtForumPostList hmtForumPostList;
    private RequestQueue mRequestQueue;

    public InitHmtForumListViewAdapter(Context mContext, HmtForumPostList hmtForumPostList) {
        this.mContext = mContext;
        this.hmtForumPostList =hmtForumPostList;
        mRequestQueue= Volley.newRequestQueue(this.mContext);


    }


    @Override
    public int getCount() {
        return Integer.valueOf(hmtForumPostList.getThreads().size());
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

        HmtForumPostList.ThreadsEntity threadlistEntity =hmtForumPostList.getThreads().get(position);


        holder.tv_title_of_forum_threads.setText(threadlistEntity.getSubject());
        holder.tv_replies_of_forum_threads.setText(threadlistEntity.getReplies());
        holder.tv_author_of_forum_threads.setText(threadlistEntity.getAuthor());



        holder.tv_content_of_forum_threads.setText("         "+DataUtil.replaceBlank(threadlistEntity.getMessage()));
        String dateline =threadlistEntity.getDateline();
        holder.tv_lastpost_of_forum_threads.setText("发表于 "+DataUtil.times(dateline));



        String authorId=threadlistEntity.getAuthorid();
        HttpUtil.setUserIconTask(mRequestQueue, HttpUtil.GET_USER_ICON_BY_USER_ID + authorId, holder.civ_icon_of_forum_threads);
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