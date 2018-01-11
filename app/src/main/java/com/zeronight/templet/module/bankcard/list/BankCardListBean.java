package com.zeronight.templet.module.bankcard.list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/10/16.
 */

public class BankCardListBean implements Parcelable {

    /**
     * id : 1
     * number : 230124166411131
     * open_bank : 中国人民银行
     * cardholder : 测试商家1
     * image : data/upload/admin/2017-10-19/59e87701d1368.jpg
     * bank_type_id : 5
     */

    private String id;
    private String number;
    private String open_bank;
    private String cardholder;
    private String image;
    private String bank_type_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOpen_bank() {
        return open_bank;
    }

    public void setOpen_bank(String open_bank) {
        this.open_bank = open_bank;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBank_type_id() {
        return bank_type_id;
    }

    public void setBank_type_id(String bank_type_id) {
        this.bank_type_id = bank_type_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.number);
        dest.writeString(this.open_bank);
        dest.writeString(this.cardholder);
        dest.writeString(this.image);
        dest.writeString(this.bank_type_id);
    }

    public BankCardListBean() {
    }

    protected BankCardListBean(Parcel in) {
        this.id = in.readString();
        this.number = in.readString();
        this.open_bank = in.readString();
        this.cardholder = in.readString();
        this.image = in.readString();
        this.bank_type_id = in.readString();
    }

    public static final Creator<BankCardListBean> CREATOR = new Creator<BankCardListBean>() {
        @Override
        public BankCardListBean createFromParcel(Parcel source) {
            return new BankCardListBean(source);
        }

        @Override
        public BankCardListBean[] newArray(int size) {
            return new BankCardListBean[size];
        }
    };

}
