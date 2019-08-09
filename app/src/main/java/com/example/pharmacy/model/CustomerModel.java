package com.example.pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CustomerModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private String customerId;

    private String customerName;
    private String customerAddress;
    private String customerPhNo1;
    private String customerInvoiceDate;
    private String customerNote;


    private RealmList<SaleModel> saleModels;


    public CustomerModel() {
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

    public String getCustomerInvoiceDate() {
        return customerInvoiceDate;
    }

    public void setCustomerInvoiceDate(String customerInvoiceDate) {
        this.customerInvoiceDate = customerInvoiceDate;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    protected CustomerModel(Parcel in) {
        customerId = in.readString();
        customerName = in.readString();
        customerAddress = in.readString();
        customerPhNo1 = in.readString();
        customerInvoiceDate = in.readString();
        customerNote = in.readString();
    }

    public RealmList<SaleModel> getSaleModels() {
        return saleModels;
    }

    public static Creator<CustomerModel> getCREATOR() {
        return CREATOR;
    }

    public void setSaleModels(RealmList<SaleModel> saleModels) {
        this.saleModels = saleModels;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerId);
        dest.writeString(customerName);
        dest.writeString(customerAddress);
        dest.writeString(customerPhNo1);
        dest.writeString(customerInvoiceDate);
        dest.writeString(customerNote);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
