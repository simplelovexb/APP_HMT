package cn.edu.scau.hometown.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/9/15 0015.
 * 解析论坛主题的实体类
 */
public class HmtForumPostList implements Serializable {

    /**
     * forum : {"name":"2015华南农业大学新生专区","threads":"9201","posts":"188836"}
     * threads : [{"tid":"325024","author":"小此","authorid":"148006","subject":"乐一乐，高考作文盘点。。","dateline":"1340461281","lastpost":"1340461281","lastposter":"小此","views":"11","replies":"0"},{"tid":"325029","author":"小此","authorid":"148006","subject":"乐一乐，高考作文盘点","dateline":"1340462260","lastpost":"1340462260","lastposter":"小此","views":"6","replies":"0"},{"tid":"337208","author":"华农自强社","authorid":"115755","subject":"学生公益社团\u2014\u2014新长城华南农业大学自强社邀你加入新的征途","dateline":"1342501866","lastpost":"1342501866","lastposter":"华农自强社","views":"14","replies":"0"},{"tid":"339216","author":"哈哈的小芳芳","authorid":"295490","subject":"12级新生必看！！让你正真成长的协会！！！！","dateline":"1342934014","lastpost":"1342934014","lastposter":"哈哈的小芳芳","views":"4","replies":"0"},{"tid":"341348","author":"qienmei","authorid":"295315","subject":"旋出来的人生最有意义,打出来的辉煌才能分高下,欢迎加入乒乓球团队!!!!!","dateline":"1343220580","lastpost":"1343220580","lastposter":"qienmei","views":"3","replies":"0"},{"tid":"343568","author":"华农大社联","authorid":"267234","subject":"【社团部落格】2012华农社联社团咨询帖","dateline":"1343395503","lastpost":"1343395503","lastposter":"华农大社联","views":"6","replies":"0"},{"tid":"343570","author":"看你妹啊","authorid":"181014","subject":"请问今年工程新生住哪里哦？","dateline":"1343395954","lastpost":"1343395954","lastposter":"看你妹啊","views":"5","replies":"0"},{"tid":"345586","author":"英语口语协会","authorid":"211851","subject":"【华南农业大学英语口语协会】招新工作火爆进行中~~~~~","dateline":"1343971040","lastpost":"1343971040","lastposter":"英语口语协会","views":"2","replies":"0"},{"tid":"348198","author":"Cc、cheng","authorid":"287800","subject":"额","dateline":"1344337893","lastpost":"1344337893","lastposter":"Cc、cheng","views":"1","replies":"0"},{"tid":"348960","author":"gzfzh","authorid":"229462","subject":"cc招新","dateline":"1344678245","lastpost":"1344678245","lastposter":"gzfzh","views":"1","replies":"0"},{"tid":"492738","author":"蛋炒麵_紫見","authorid":"298960","subject":"0..0","dateline":"1373911471","lastpost":"1373911471","lastposter":"蛋炒麵_紫見","views":"1","replies":"0"},{"tid":"496036","author":"SJ-beR_伟鸿","authorid":"346878","subject":"招新","dateline":"1374302939","lastpost":"1374302939","lastposter":"SJ-beR_伟鸿","views":"1","replies":"0"},{"tid":"499415","author":"华农璃羽漫协","authorid":"290731","subject":"璃羽漫画协会招新啦----协会活动回顾---第一弹之【岁末祭】","dateline":"1374748799","lastpost":"1374748799","lastposter":"华农璃羽漫协","views":"5","replies":"0"},{"tid":"500524","author":"彩容or小七","authorid":"218151","subject":"旅行","dateline":"1375024338","lastpost":"1375024338","lastposter":"彩容or小七","views":"1","replies":"0"},{"tid":"504341","author":"快乐的耿先生_","authorid":"294734","subject":"【工程艺术团招新啦】 各位工程的师弟师妹快点进来看看吧！","dateline":"1375162136","lastpost":"1375162136","lastposter":"快乐的耿先生_","views":"2","replies":"0"},{"tid":"504793","author":"YinLL","authorid":"227334","subject":"09某人给即将入学的13师弟师妹的几点建议","dateline":"1375275002","lastpost":"1375275002","lastposter":"YinLL","views":"1","replies":"0"},{"tid":"516817","author":"潜水鱼","authorid":"296645","subject":"2013年华南农业大学学生工作通讯社招新帖","dateline":"1376120060","lastpost":"1376120060","lastposter":"潜水鱼","views":"23","replies":"0"},{"tid":"516920","author":"ERP研究社","authorid":"303979","subject":"各位亲，想知道ERP研究社是什么吗？速速围观第八届ERP研究社！走过路过不要错过咯","dateline":"1376233058","lastpost":"1376233058","lastposter":"ERP研究社","views":"4","replies":"0"},{"tid":"516925","author":"ERP研究社","authorid":"303979","subject":"第八届ERP研究社","dateline":"1376235395","lastpost":"1376235395","lastposter":"ERP研究社","views":"12","replies":"0"},{"tid":"518289","author":"铁血eeeeee","authorid":"253907","subject":"大一的新生注意了,大三的师兄出售旧书啦!!!!!!!!!!!!!!!!!!!","dateline":"1377096250","lastpost":"1377096250","lastposter":"铁血eeeeee","views":"5","replies":"0"}]
     * status : success
     */

    private ForumEntity forum;
    private String status;
    private List<ThreadsEntity> threads;

    public void setForum(ForumEntity forum) {
        this.forum = forum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setThreads(List<ThreadsEntity> threads) {
        this.threads = threads;
    }

    public ForumEntity getForum() {
        return forum;
    }

    public String getStatus() {
        return status;
    }

    public List<ThreadsEntity> getThreads() {
        return threads;
    }

    public static class ForumEntity implements Serializable{
        /**
         * name : 2015华南农业大学新生专区
         * threads : 9201
         * posts : 188836
         */

        private String name;
        private String threads;
        private String posts;

        public void setName(String name) {
            this.name = name;
        }

        public void setThreads(String threads) {
            this.threads = threads;
        }

        public void setPosts(String posts) {
            this.posts = posts;
        }

        public String getName() {
            return name;
        }

        public String getThreads() {
            return threads;
        }

        public String getPosts() {
            return posts;
        }
    }

    public static class ThreadsEntity implements Serializable{
        /**
         * tid : 325024
         * author : 小此
         * authorid : 148006
         * subject : 乐一乐，高考作文盘点。。
         * dateline : 1340461281
         * lastpost : 1340461281
         * lastposter : 小此
         * views : 11
         * replies : 0
         */

        private String tid;
        private String author;
        private String authorid;
        private String subject;
        private String dateline;
        private String lastpost;
        private String lastposter;
        private String views;
        private String replies;

        public void setTid(String tid) {
            this.tid = tid;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public void setLastpost(String lastpost) {
            this.lastpost = lastpost;
        }

        public void setLastposter(String lastposter) {
            this.lastposter = lastposter;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public void setReplies(String replies) {
            this.replies = replies;
        }

        public String getTid() {
            return tid;
        }

        public String getAuthor() {
            return author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public String getSubject() {
            return subject;
        }

        public String getDateline() {
            return dateline;
        }

        public String getLastpost() {
            return lastpost;
        }

        public String getLastposter() {
            return lastposter;
        }

        public String getViews() {
            return views;
        }

        public String getReplies() {
            return replies;
        }
    }
}
