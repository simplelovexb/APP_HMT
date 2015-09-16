package cn.edu.scau.hometown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.bean.HmtForumPostContent;
import cn.edu.scau.hometown.tools.HttpUtil;
import cn.edu.scau.hometown.tools.StringUtils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2015/9/2 0002.
 * 用于渲染论坛帖子回帖列表，填充帖子回帖列表视图的Adapter类
 */
public class InitDetailHmtForumListViewAdapter extends BaseAdapter {
    private Context context;
    private HmtForumPostContent hmtForumPostContent;
    private RequestQueue mRequestQueue;

    public InitDetailHmtForumListViewAdapter(Context context,HmtForumPostContent hmtForumPostContent) {
        this.hmtForumPostContent=hmtForumPostContent;
        this.context = context;
        mRequestQueue= Volley.newRequestQueue(this.context);
    }

    @Override
    public int getCount() {
        return hmtForumPostContent.getPosts().size()-1;
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
            convertView =LayoutInflater.from(context).inflate(R.layout.listview_item_detail_post_threads, null);
            holder.tv_floor = (TextView) convertView.findViewById(R.id.tv_floor);
            holder.tv_content_of_detial_forum_threads= (EditText) convertView.findViewById(R.id.tv_content_of_detial_forum_threads);
            holder.tv_huitie_author_name= (TextView) convertView.findViewById(R.id.tv_huitie_author_name);
            holder.cig_huitie_author_icon=(CircleImageView)convertView.findViewById(R.id.cig_huitie_author_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


       HmtForumPostContent.PostsEntity postsEntity= hmtForumPostContent.getPosts().get(position+1);
        String content=postsEntity.getMessage();
        String name=postsEntity.getAuthor();
        String authorId=postsEntity.getAuthorid();



        holder.tv_floor.setText((position+1)+"楼");
       holder.tv_content_of_detial_forum_threads.setText("      "+content);


       // holder.tv_content_of_detial_forum_threads.setText(StringUtils.getEmotionContent(context, holder.tv_content_of_detial_forum_threads, "      " + content));
        holder.tv_huitie_author_name.setText(name);
        HttpUtil.setUserIconTask(mRequestQueue, HttpUtil.GET_USER_ICON_BY_USER_ID + authorId, holder.cig_huitie_author_icon);


        return convertView;

    }
    private static class ViewHolder {
        private TextView tv_floor;
        private EditText tv_content_of_detial_forum_threads;
        private TextView tv_huitie_author_name;
        private CircleImageView cig_huitie_author_icon;

    }
}
