package com.zeronight.templet.module.address.list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AddressDetialBean implements Parcelable {

    private String addressId;
    private String user;
    private String phone;
    private String city;
    private String addressDetial;
    private int isdefault;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressDetial() {
        return addressDetial;
    }

    public void setAddressDetial(String addressDetial) {
        this.addressDetial = addressDetial;
    }

    public int getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(int isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.addressId);
        dest.writeString(this.user);
        dest.writeString(this.phone);
        dest.writeString(this.city);
        dest.writeString(this.addressDetial);
        dest.writeInt(this.isdefault);
    }

    public AddressDetialBean() {
    }

    public AddressDetialBean(String addressId, String user, String phone, String city, String addressDetial, int isdefault) {
        this.addressId = addressId;
        this.user = user;
        this.phone = phone;
        this.city = city;
        this.addressDetial = addressDetial;
        this.isdefault = isdefault;
    }

    protected AddressDetialBean(Parcel in) {
        this.addressId = in.readString();
        this.user = in.readString();
        this.phone = in.readString();
        this.city = in.readString();
        this.addressDetial = in.readString();
        this.isdefault = in.readInt();
    }

    public static final Parcelable.Creator<AddressDetialBean> CREATOR = new Parcelable.Creator<AddressDetialBean>() {
        @Override
        public AddressDetialBean createFromParcel(Parcel source) {
            return new AddressDetialBean(source);
        }

        @Override
        public AddressDetialBean[] newArray(int size) {
            return new AddressDetialBean[size];
        }
    };

    @Override
    public String toString() {
        return "AddressDetialBean{" +
                "addressId='" + addressId + '\'' +
                ", user='" + user + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", addressDetial='" + addressDetial + '\'' +
                ", isdefault=" + isdefault +
                '}';
    }

}
