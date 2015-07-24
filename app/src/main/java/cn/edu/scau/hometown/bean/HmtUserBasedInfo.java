package cn.edu.scau.hometown.bean;

import org.json.JSONArray;

public class HmtUserBasedInfo {
    private String uid;
    private String username;
    private String email;
    private String adminid;
    private String groupid;
    private JSONArray extgroupids;
    private String allowadmincp;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public JSONArray getExtgroupids() {
        return extgroupids;
    }

    public void setExtgroupids(JSONArray extgroupids) {
        this.extgroupids = extgroupids;
    }

    public String getAllowadmincp() {
        return allowadmincp;
    }

    public void setAllowadmincp(String allowadmincp) {
        this.allowadmincp = allowadmincp;
    }

    public HmtUserBasedInfo(String uid, String username, String email,
                            String adminid, String groupid, JSONArray extgroupids2, String allowadmincp) {
        super();
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.adminid = adminid;
        this.groupid = groupid;
        this.extgroupids = extgroupids2;
        this.allowadmincp = allowadmincp;
    }

    @Override
    public String toString() {
        return "HmtUserBasedInfo [uid=" + uid + ", username=" + username
                + ", email=" + email + ", adminid=" + adminid + ", groupid="
                + groupid + ", extgroupids=" + extgroupids + ", allowadmincp="
                + allowadmincp + "]";
    }


}
