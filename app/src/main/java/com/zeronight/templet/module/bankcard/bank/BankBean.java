package com.zeronight.templet.module.bankcard.bank;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/9/3.
 */

public class BankBean implements Parcelable {

    /**
     * id : 2
     * title : 1111
     * image : data/upload/admin/2017-10-19/59e86eb9a727d.jpg
     */

    private String id;
    private String title;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.image);
    }

    public BankBean() {
    }

    protected BankBean(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.image = in.readString();
    }

    public static final Creator<BankBean> CREATOR = new Creator<BankBean>() {
        @Override
        public BankBean createFromParcel(Parcel source) {
            return new BankBean(source);
        }

        @Override
        public BankBean[] newArray(int size) {
            return new BankBean[size];
        }
    };
}
