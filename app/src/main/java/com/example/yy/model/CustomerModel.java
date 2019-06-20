package com.example.yy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CustomerModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private String customerId;

    private String customerName;
    private String customerLevel;
    private String customerAddress;
    private String customerPhNo1;
    private String customerPhNo2;
    private String customerPhNo3;
    private String customerPhNo4;
    private String customerPhNo5;
    private String customerNote;

    public CustomerModel(){}

    public CustomerModel(String customerId, String customerName, String customerLevel, String customerAddress, String customerPhNo1, String customerPhNo2, String customerPhNo3, String customerPhNo4, String customerPhNo5, String customerNote) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerLevel = customerLevel;
        this.customerAddress = customerAddress;
        this.customerPhNo1 = customerPhNo1;
        this.customerPhNo2 = customerPhNo2;
        this.customerPhNo3 = customerPhNo3;
        this.customerPhNo4 = customerPhNo4;
        this.customerPhNo5 = customerPhNo5;
        this.customerNote = customerNote;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhNo1() {
        return customerPhNo1;
    }

    public void setCustomerPhNo1(String customerPhNo1) {
        this.customerPhNo1 = customerPhNo1;
    }

    public String getCustomerPhNo2() {
        return customerPhNo2;
    }

    public void setCustomerPhNo2(String customerPhNo2) {
        this.customerPhNo2 = customerPhNo2;
    }

    public String getCustomerPhNo3() {
        return customerPhNo3;
    }

    public void setCustomerPhNo3(String customerPhNo3) {
        this.customerPhNo3 = customerPhNo3;
    }

    public String getCustomerPhNo4() {
        return customerPhNo4;
    }

    public void setCustomerPhNo4(String customerPhNo4) {
        this.customerPhNo4 = customerPhNo4;
    }

    public String getCustomerPhNo5() {
        return customerPhNo5;
    }

    public void setCustomerPhNo5(String customerPhNo5) {
        this.customerPhNo5 = customerPhNo5;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public static Creator<CustomerModel> getCREATOR() {
        return CREATOR;
    }

    protected CustomerModel(Parcel in) {
        customerId = in.readString();
        customerName = in.readString();
        customerLevel = in.readString();
        customerAddress = in.readString();
        customerPhNo1 = in.readString();
        customerPhNo2 = in.readString();
        customerPhNo3 = in.readString();
        customerPhNo4 = in.readString();
        customerPhNo5 = in.readString();
        customerNote = in.readString();
    }

    public static final Creator<CustomerModel> CREATOR = new Creator<CustomerModel>() {
        @Override
        public CustomerModel createFromParcel(Parcel in) {
            return new CustomerModel(in);
        }

        @Override
        public CustomerModel[] newArray(int size) {
            return new CustomerModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerId);
        dest.writeString(customerName);
        dest.writeString(customerLevel);
        dest.writeString(customerAddress);
        dest.writeString(customerPhNo1);
        dest.writeString(customerPhNo2);
        dest.writeString(customerPhNo3);
        dest.writeString(customerPhNo4);
        dest.writeString(customerPhNo5);
        dest.writeString(customerNote);
    }
}
