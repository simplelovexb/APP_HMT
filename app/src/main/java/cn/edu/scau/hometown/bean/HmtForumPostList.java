package cn.edu.scau.hometown.bean;


import java.io.Serializable;
import java.util.List;
/**
 * Created by Administrator on 2015/9/15 0015.
 * 解析论坛主题的实体类
 */
public class HmtForumPostList implements Serializable {


    /**
     * forum : {"name":"PT讨论区/求种","threads":"1788","posts":"18704"}
     * threads : [{"tid":"646470","author":"九阳珠链","authorid":"513149","subject":"【个人邀请】HDArea*50~100 截止5.15【已关邀】","dateline":"1431332780","lastpost":"1431450502","lastposter":"九阳珠链","views":"737","replies":"2","message":"测试]"}]
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

    public static class ForumEntity implements Serializable {
        /**
         * name : PT讨论区/求种
         * threads : 1788
         * posts : 18704
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

    public static class ThreadsEntity implements Serializable {
        /**
         * tid : 646470
         * author : 九阳珠链
         * authorid : 513149
         * subject : 【个人邀请】HDArea*50~100 截止5.15【已关邀】
         * dateline : 1431332780
         * lastpost : 1431450502
         * lastposter : 九阳珠链
         * views : 737
         * replies : 2
         * message : 测试]
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
        private String message;

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

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTid() {
            return tid;
        }

        public String getAuthor() {
            return author.equals("")?"匿名":author;
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

        public String getMessage() {
            return message;
        }
    }
}
