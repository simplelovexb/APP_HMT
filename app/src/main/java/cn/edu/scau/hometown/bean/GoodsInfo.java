package cn.edu.scau.hometown.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2015/10/14.
 */
public class GoodsInfo implements Serializable {

//    "secondgoods_id": "96",
//            "secondgoods_name": "超级多",
//            "secondgoods_hownew": "九成新",
//            "secondgoods_price": "78",
//            "secondgoods_delivertype": "当面交易",
//            "secondgoods_paidtype": "现金",
//            "secondgoods_goodsnums": "1",
//            "secondgoods_bewrite": "不知道什么鬼",
//            "secondgoods_efficiency": "交易正常",
//            "secondgoods_postdate": "1444066671",
//            "secondgoods_pastdate": "1444325871",
//            "secondgoods_tradetype": "收购",
//            "secondgoods_views": "0",
//            "secondgoods_picture": [

    private Integer secondgoods_id;
    private String secondgoods_name;
    private String secondgoods_hownew;
    private Double secondgoods_price;
    private String secondgoods_delivertype;
    private String secondgoods_paidtype;
    private Integer secondgoods_goodsnums;
    private String secondgoods_bewrite;
    private String secondgoods_efficiency;
    private String secondgoods_postdate;
    private String secondgoods_pastdate;
    private String secondgoods_tradetype;
    private int secondgoods_views;
    private String[] secondgoods_picture;
    private String[] GoodLinkMan;

    public GoodsInfo(Integer secondgoods_id, String secondgoods_name,
                     String secondgoods_hownew, Double secondgoods_price,
                     String secondgoods_delivertype, String secondgoods_paidtype,
                     Integer secondgoods_goodsnums, String secondgoods_bewrite,
                     String secondgoods_efficiency, String secondgoods_postdate,
                     String secondgoods_pastdate, String secondgoods_tradetype,
                     int secondgoods_views, String[] secondgoods_picture, String[] goodLinkMan) {
        this.secondgoods_id = secondgoods_id;
        this.secondgoods_name = secondgoods_name;
        this.secondgoods_hownew = secondgoods_hownew;
        this.secondgoods_price = secondgoods_price;
        this.secondgoods_delivertype = secondgoods_delivertype;
        this.secondgoods_paidtype = secondgoods_paidtype;
        this.secondgoods_goodsnums = secondgoods_goodsnums;
        this.secondgoods_bewrite = secondgoods_bewrite;
        this.secondgoods_efficiency = secondgoods_efficiency;
        this.secondgoods_postdate = secondgoods_postdate;
        this.secondgoods_pastdate = secondgoods_pastdate;
        this.secondgoods_tradetype = secondgoods_tradetype;
        this.secondgoods_views = secondgoods_views;
        this.secondgoods_picture = secondgoods_picture;
        GoodLinkMan = goodLinkMan;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "id=" + secondgoods_id +
                ", secondgoods_name='" + secondgoods_name + '\'' +
                ", secondgoods_hownew='" + secondgoods_hownew + '\'' +
                ", secondgoods_price=" + secondgoods_price +
                ", secondgoods_delivertype='" + secondgoods_delivertype + '\'' +
                ", secondgoods_paidtype='" + secondgoods_paidtype + '\'' +
                ", secondgoods_goodsnums=" + secondgoods_goodsnums +
                ", secondgoods_bewrite='" + secondgoods_bewrite + '\'' +
                ", secondgoods_efficiency='" + secondgoods_efficiency + '\'' +
                ", secondgoods_postdate='" + secondgoods_postdate + '\'' +
                ", secondgoods_pastdate='" + secondgoods_pastdate + '\'' +
                ", secondgoods_tradetype='" + secondgoods_tradetype + '\'' +
                ", secondgoods_views=" + secondgoods_views +
                ", secondgoods_picture=" + Arrays.toString(secondgoods_picture) +
                ", GoodLinkMan=" + Arrays.toString(GoodLinkMan) +
                '}';
    }

    public Integer getSecondgoods_id() {
        return secondgoods_id;
    }

    public void setSecondgoods_id(Integer secondgoods_id) {
        this.secondgoods_id = secondgoods_id;
    }

    public String getSecondgoods_name() {
        return secondgoods_name;
    }

    public void setSecondgoods_name(String secondgoods_name) {
        this.secondgoods_name = secondgoods_name;
    }

    public String getSecondgoods_hownew() {
        return secondgoods_hownew;
    }

    public void setSecondgoods_hownew(String secondgoods_hownew) {
        this.secondgoods_hownew = secondgoods_hownew;
    }

    public Double getSecondgoods_price() {
        return secondgoods_price;
    }

    public void setSecondgoods_price(Double secondgoods_price) {
        this.secondgoods_price = secondgoods_price;
    }

    public String getSecondgoods_delivertype() {
        return secondgoods_delivertype;
    }

    public void setSecondgoods_delivertype(String secondgoods_delivertype) {
        this.secondgoods_delivertype = secondgoods_delivertype;
    }

    public String getSecondgoods_paidtype() {
        return secondgoods_paidtype;
    }

    public void setSecondgoods_paidtype(String secondgoods_paidtype) {
        this.secondgoods_paidtype = secondgoods_paidtype;
    }

    public Integer getSecondgoods_goodsnums() {
        return secondgoods_goodsnums;
    }

    public void setSecondgoods_goodsnums(Integer secondgoods_goodsnums) {
        this.secondgoods_goodsnums = secondgoods_goodsnums;
    }

    public String getSecondgoods_bewrite() {
        return secondgoods_bewrite;
    }

    public void setSecondgoods_bewrite(String secondgoods_bewrite) {
        this.secondgoods_bewrite = secondgoods_bewrite;
    }

    public String getSecondgoods_efficiency() {
        return secondgoods_efficiency;
    }

    public void setSecondgoods_efficiency(String secondgoods_efficiency) {
        this.secondgoods_efficiency = secondgoods_efficiency;
    }

    public String getSecondgoods_postdate() {
        return secondgoods_postdate;
    }

    public void setSecondgoods_postdate(String secondgoods_postdate) {
        this.secondgoods_postdate = secondgoods_postdate;
    }

    public String getSecondgoods_pastdate() {
        return secondgoods_pastdate;
    }

    public void setSecondgoods_pastdate(String secondgoods_pastdate) {
        this.secondgoods_pastdate = secondgoods_pastdate;
    }

    public String getSecondgoods_tradetype() {
        return secondgoods_tradetype;
    }

    public void setSecondgoods_tradetype(String secondgoods_tradetype) {
        this.secondgoods_tradetype = secondgoods_tradetype;
    }

    public int getSecondgoods_views() {
        return secondgoods_views;
    }

    public void setSecondgoods_views(int secondgoods_views) {
        this.secondgoods_views = secondgoods_views;
    }

    public String[] getSecondgoods_picture() {
        return secondgoods_picture;
    }

    public void setSecondgoods_picture(String[] secondgoods_picture) {
        this.secondgoods_picture = secondgoods_picture;
    }

    public String[] getGoodLinkMan() {
        return GoodLinkMan;
    }

    public void setGoodLinkMan(String[] goodLinkMan) {
        GoodLinkMan = goodLinkMan;
    }

    public GoodsInfo() {

    }
}
