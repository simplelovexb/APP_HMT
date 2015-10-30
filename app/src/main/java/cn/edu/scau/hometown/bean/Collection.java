package cn.edu.scau.hometown.bean;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.edu.scau.hometown.tools.DataUtil;


/**
 * Created by asus-pc on 2015/10/30.
 */
public class Collection implements Parcelable{
    private String secondgoods_ids;
    private String idsFileName = "collectionIds.ser";

    public Collection(){
        secondgoods_ids = getIds();//初始化secondgoods_ids
    }

    /*设置收藏 */
    public void setCollection(String secondgoods_id){
        this.secondgoods_ids = this.secondgoods_ids+secondgoods_id+'\n';
        saveIds();
    }

    /*取消收藏*/
    public void cancelCollection(String secondgoods_id){
        this.secondgoods_ids = this.secondgoods_ids.replace(secondgoods_id+'\n',"");
        saveIds();
    }

    /*存储所有商品id到文件中*/
    public void saveIds(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(idsFileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(secondgoods_ids);
        } catch (Exception e) {
            e.printStackTrace();
            //这里是保存文件产生异常
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    //fos流关闭异常
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    //oos流关闭异常
                    e.printStackTrace();
                }
            }
        }
    }

    /*获取文件中收藏的所有商品id
    * 程序打开一次，应该只执行获取操作一次（类的新建）
    */
    public String getIds(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Collection collection = null;
        try {
            fis = new FileInputStream(idsFileName);
            ois = new ObjectInputStream(fis);
            collection = (Collection) ois.readObject();
            return collection.secondgoods_ids;
        } catch (Exception e) {
            e.printStackTrace();
            //这里是读取文件产生异常
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    //fis流关闭异常
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    //ois流关闭异常
                    e.printStackTrace();
                }
            }
        }
        //读取产生异常，返回null
        return null;
    }

    /*判断指定id是否在收藏文件中*/
    public boolean isId(String secondgoods_id){
        if(this.secondgoods_ids.indexOf(secondgoods_id) != -1) return true;
        return false;
    }


    public String getIdsFileName() {
        return idsFileName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(secondgoods_ids);
    }

    public static final Creator<Collection> CREATOR = new Creator<Collection>(){
        @Override
        public Collection createFromParcel(Parcel parcel) {
            return new Collection();
        }
        @Override
        public Collection[] newArray(int i) {
            return new Collection[i];
        }
    };
}
