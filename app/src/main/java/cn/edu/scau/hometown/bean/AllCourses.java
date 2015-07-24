package cn.edu.scau.hometown.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by QUAN on 2015/6/23 0023.
 */
public class AllCourses implements Serializable {


    /**
     * count : 14
     * status : success
     * keyword : 电影
     * data : [{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"教育心理电影赏析","ID":"105","Class_Teacher":"林玲","Class_Collage":"人文与法学学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"生态文化电影赏析","ID":"112","Class_Teacher":"郑大睿","Class_Collage":"人文与法学学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"世界电影与电影文化","ID":"113","Class_Teacher":"陆杰","Class_Collage":"人文与法学学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"世界电影与电影文化","ID":"114","Class_Teacher":"姚海燕","Class_Collage":"人文与法学学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"电影经典鉴赏","ID":"179","Class_Teacher":"张步中","Class_Collage":"艺术学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"电影音乐欣赏","ID":"180","Class_Teacher":"胡远慧","Class_Collage":"艺术学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"电影音乐欣赏","ID":"181","Class_Teacher":"袁薇","Class_Collage":"艺术学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"电影音乐赏析","ID":"279","Class_Teacher":"袁薇","Class_Collage":"艺术学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"电影经典赏析","ID":"327","Class_Teacher":"胡辉","Class_Collage":"艺术学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"电影经典鉴赏","ID":"328","Class_Teacher":"胡辉","Class_Collage":"艺术学院"},{"Class_Score":"2","Class_Sex":"人文社会科学类","Class_Name":"电影音乐赏析","ID":"333","Class_Teacher":"胡远慧","Class_Collage":"艺术学院"},{"Class_Score":"1","Class_Sex":"专业选修课","Class_Name":"英语电影视听说","ID":"595","Class_Teacher":"曾照莲","Class_Collage":"理学院"},{"Class_Score":"1","Class_Sex":"英语/汉语系列","Class_Name":"英语电影视听说","ID":"601","Class_Teacher":"钟志英","Class_Collage":"外国语学院"},{"Class_Score":"1","Class_Sex":"英语/汉语系列","Class_Name":"英语电影视听说","ID":"603","Class_Teacher":"燕晓黎","Class_Collage":"外国语学院"}]
     */
    private int count;
    private String status;
    private String keyword;
    private List<DataEntity> data;

    public void setCount(int count) {
        this.count = count;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public String getStatus() {
        return status;
    }

    public String getKeyword() {
        return keyword;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Serializable {
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
}
