package com.example.pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class SaleListModel implements Parcelable {

    private String invoiceDate;
    private String customerName;
    private String customerAddress;
    private String phone;

    public SaleListModel() {

    }

    protected SaleListModel(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SaleListModel> CREATOR = new Creator<SaleListModel>() {
        @Override
        public SaleListModel createFromParcel(Parcel in) {
            return new SaleListModel(in);
        }

        @Override
        public SaleListModel[] newArray(int size) {
            return new SaleListModel[size];
        }
    };

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static Creator<SaleListModel> getCREATOR() {
        return CREATOR;
    }
}
