package cn.edu.scau.hometown.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 解析用户登录信息的实体类
 */
public class HmtUserBasedInfo implements Serializable {


    /**
     * data : {"uid":"1","username":"sigong","email":"chixiaosheng05@163.com","adminid":"0","groupid":"14","extgroupids":[""],"allowadmincp":"0","credits":"7033","newpm":"0","ml":"57","sp":"68","avatar":"http://hometown.scau.edu.cn/bbs/uc_server/avatar.php?uid=1"}
     * status : success
     */

    private DataEntity data;
    private String status;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public static class DataEntity implements Serializable{
        /**
         * uid : 1
         * username : sigong
         * email : chixiaosheng05@163.com
         * adminid : 0
         * groupid : 14
         * extgroupids : [""]
         * allowadmincp : 0
         * credits : 7033
         * newpm : 0
         * ml : 57
         * sp : 68
         * avatar : http://hometown.scau.edu.cn/bbs/uc_server/avatar.php?uid=1
         */

        private String uid;
        private String username;
        private String email;
        private String adminid;
        private String groupid;
        private String allowadmincp;
        private String credits;
        private String newpm;
        private String ml;
        private String sp;
        private String avatar;
        private List<String> extgroupids;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setAdminid(String adminid) {
            this.adminid = adminid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public void setAllowadmincp(String allowadmincp) {
            this.allowadmincp = allowadmincp;
        }

        public void setCredits(String credits) {
            this.credits = credits;
        }

        public void setNewpm(String newpm) {
            this.newpm = newpm;
        }

        public void setMl(String ml) {
            this.ml = ml;
        }

        public void setSp(String sp) {
            this.sp = sp;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setExtgroupids(List<String> extgroupids) {
            this.extgroupids = extgroupids;
        }

        public String getUid() {
            return uid;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getAdminid() {
            return adminid;
        }

        public String getGroupid() {
            return groupid;
        }

        public String getAllowadmincp() {
            return allowadmincp;
        }

        public String getCredits() {
            return credits;
        }

        public String getNewpm() {
            return newpm;
        }

        public String getMl() {
            return ml;
        }

        public String getSp() {
            return sp;
        }

        public String getAvatar() {
            return avatar;
        }

        public List<String> getExtgroupids() {
            return extgroupids;
        }
    }
}