package cn.edu.scau.hometown.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by QUAN on 2015/7/13 0013.
 * 解析关于某一具体课程的评论的实体类
 */
public class AllComment implements Serializable {

    /**
     * course : {"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"教育心理电影赏析","ID":"105","Class_Teacher":"林玲","Class_Collage":"人文与法学学院"}
     * status : success
     * value : 2.6666666666667
     * comment : [{"Class_Homework":"无","Class_ID":"105","Class_Value":"5","Class_User_See":"0","Class_Score":"95","Class_Exam":"电影观后感+小组形式+一人上台演讲ppt","Class_User":"喑崖无","ID":"2584","Class_Check":"基本不点","Class_Select":"2013 至 2014 年 第 2 学期","Class_Ex":"很不错的课啊，上课基本是给你看一部电影，然后再讲。\n说实话，放的电影都很有意思啊。额，我觉得光是看这些电影也会有很大的收获\n影片类型：剧情·、推理、教育、治愈\n或以上相加","Class_Comment_Time":"2015-01-08"},{"Class_Homework":"不明","Class_ID":"105","Class_Value":"0","Class_User_See":"1","Class_Score":"0","Class_Exam":"论文","Class_User":"","ID":"337","Class_Check":"不明","Class_Select":"11年第一学期","Class_Ex":"开口第一句，你们自己10人分个小组，然后推一个人上来作自我介绍，然后第一次课的第一节走了一半","Class_Comment_Time":"2011-08-30"},{"Class_Homework":"写一次电影观后感，并上台发表","Class_ID":"105","Class_Value":"3","Class_User_See":"1","Class_Score":"92","Class_Exam":"四选一的1000字论文，提前一个月公布题目，最后一节课当堂完成，可以带参考资料（就是抄你之前准备好的论文）","Class_User":"Towis","ID":"308","Class_Check":"一开始时点了一次","Class_Select":"10年第二学期","Class_Ex":"上课基本上是看电影（挺有深度的电影），老师只是稍稍点评一下。上课是两小节，但往往会延迟到三小节的时间（晚上），因为电影太长了，这也是老师点评很短的原因~喜欢内涵丰富的电影的，去吧！希望从中学到些心理哲学的，去吧！\r\n PS：有好几个师兄姐是上过再上的（当然不是重修）","Class_Comment_Time":"2011-07-08"}]
     */
    private CourseEntity course;
    private String status;
    private double value;
    private List<CommentEntity> comment;

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setComment(List<CommentEntity> comment) {
        this.comment = comment;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public String getStatus() {
        return status;
    }

    public double getValue() {
        return value;
    }

    public List<CommentEntity> getComment() {
        return comment;
    }

    public int getCommentSize(){
        return comment==null? 0:comment.size();
    }

    public static class CourseEntity implements Serializable {
        /**
         * Class_Score : 2
         * Class_Sex : 人文社会科学类
         * Class_Name : 教育心理电影赏析
         * ID : 105
         * Class_Teacher : 林玲
         * Class_Collage : 人文与法学学院
         */
        private String Class_Score;
        private String Class_Sex;
        private String Class_Name;
        private String ID;
        private String Class_Teacher;
        private String Class_Collage;

        public void setClass_Score(String Class_Score) {
            this.Class_Score = Class_Score;
        }

        public void setClass_Sex(String Class_Sex) {
            this.Class_Sex = Class_Sex;
        }

        public void setClass_Name(String Class_Name) {
            this.Class_Name = Class_Name;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setClass_Teacher(String Class_Teacher) {
            this.Class_Teacher = Class_Teacher;
        }

        public void setClass_Collage(String Class_Collage) {
            this.Class_Collage = Class_Collage;
        }

        public String getClass_Score() {
            return Class_Score;
        }

        public String getClass_Sex() {
            return Class_Sex;
        }

        public String getClass_Name() {
            return Class_Name;
        }

        public String getID() {
            return ID;
        }

        public String getClass_Teacher() {
            return Class_Teacher;
        }

        public String getClass_Collage() {
            return Class_Collage;
        }
    }

    public static class CommentEntity implements Serializable {
        /**
         * Class_Homework : 无
         * Class_ID : 105
         * Class_Value : 5
         * Class_User_See : 0
         * Class_Score : 95
         * Class_Exam : 电影观后感+小组形式+一人上台演讲ppt
         * Class_User : 喑崖无
         * ID : 2584
         * Class_Check : 基本不点
         * Class_Select : 2013 至 2014 年 第 2 学期
         * Class_Ex : 很不错的课啊，上课基本是给你看一部电影，然后再讲。
         * 说实话，放的电影都很有意思啊。额，我觉得光是看这些电影也会有很大的收获
         * 影片类型：剧情·、推理、教育、治愈
         * 或以上相加
         * Class_Comment_Time : 2015-01-08
         */
        private String Class_Homework;
        private String Class_ID;
        private String Class_Value;
        private String Class_User_See;
        private String Class_Score;
        private String Class_Exam;
        private String Class_User;
        private String ID;
        private String Class_Check;
        private String Class_Select;
        private String Class_Ex;
        private String Class_Comment_Time;

        public void setClass_Homework(String Class_Homework) {
            this.Class_Homework = Class_Homework;
        }

        public void setClass_ID(String Class_ID) {
            this.Class_ID = Class_ID;
        }

        public void setClass_Value(String Class_Value) {
            this.Class_Value = Class_Value;
        }

        public void setClass_User_See(String Class_User_See) {
            this.Class_User_See = Class_User_See;
        }

        public void setClass_Score(String Class_Score) {
            this.Class_Score = Class_Score;
        }

        public void setClass_Exam(String Class_Exam) {
            this.Class_Exam = Class_Exam;
        }

        public void setClass_User(String Class_User) {
            this.Class_User = Class_User;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setClass_Check(String Class_Check) {
            this.Class_Check = Class_Check;
        }

        public void setClass_Select(String Class_Select) {
            this.Class_Select = Class_Select;
        }

        public void setClass_Ex(String Class_Ex) {
            this.Class_Ex = Class_Ex;
        }

        public void setClass_Comment_Time(String Class_Comment_Time) {
            this.Class_Comment_Time = Class_Comment_Time;
        }

        public String getClass_Homework() {
            return Class_Homework;
        }

        public String getClass_ID() {
            return Class_ID;
        }

        public String getClass_Value() {
            return Class_Value;
        }

        public String getClass_User_See() {
            return Class_User_See;
        }

        public String getClass_Score() {
            return Class_Score;
        }

        public String getClass_Exam() {
            return Class_Exam;
        }

        public String getClass_User() {
            return Class_User;
        }

        public String getID() {
            return ID;
        }

        public String getClass_Check() {
            return Class_Check;
        }

        public String getClass_Select() {
            return Class_Select;
        }

        public String getClass_Ex() {
            return Class_Ex;
        }

        public String getClass_Comment_Time() {
            return Class_Comment_Time;
        }
    }

}
