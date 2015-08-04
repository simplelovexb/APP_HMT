package cn.edu.scau.hometown.bean;

import java.io.Serializable;
import java.util.List;

public class HmtUserBasedInfo implements Serializable {


    /**
     * status : success
     * data : {"uid":"612998","allowadmincp":"0","username":"小泉","email":"1136535305@qq.com","extgroupids":[""],"groupid":"37","adminid":"-1"}
     */
    private String status;
    private DataEntity data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity implements Serializable {
        /**
         * uid : 612998
         * allowadmincp : 0
         * username : 小泉
         * email : 1136535305@qq.com
         * extgroupids : [""]
         * groupid : 37
         * adminid : -1
         */
        private String uid;
        private String allowadmincp;
        private String username;
        private String email;
        private List<String> extgroupids;
        private String groupid;
        private String adminid;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setAllowadmincp(String allowadmincp) {
            this.allowadmincp = allowadmincp;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setExtgroupids(List<String> extgroupids) {
            this.extgroupids = extgroupids;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public void setAdminid(String adminid) {
            this.adminid = adminid;
        }

        public String getUid() {
            return uid;
        }

        public String getAllowadmincp() {
            return allowadmincp;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public List<String> getExtgroupids() {
            return extgroupids;
        }

        public String getGroupid() {
            return groupid;
        }

        public String getAdminid() {
            return adminid;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "uid='" + uid + '\'' +
                    ", allowadmincp='" + allowadmincp + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", extgroupids=" + extgroupids +
                    ", groupid='" + groupid + '\'' +
                    ", adminid='" + adminid + '\'' +
                    '}';
        }
    }


}
