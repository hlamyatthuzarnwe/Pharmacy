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
    private String customerLevel1;
    private String customerLevel2;
    private String customerLevel3;
    private String customerLevel4;
    private String customerAddress;
    private String customerPhNo1;
    private String customerPhNo2;
    private String customerPhNo3;
    private String customerPhNo4;
    private String customerPhNo5;
    private String customerTotalAmt;
    private String customerUpFront;
    private String customerBalance;
    private String customerInvoiceDate;
    private String customerDueDate;
    private String customerNote;

    private RealmList<MedicineModel> medicineLists;


    public CustomerModel(){}

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

    public String getCustomerLevel1() {
        return customerLevel1;
    }

    public void setCustomerLevel1(String customerLevel1) {
        this.customerLevel1 = customerLevel1;
    }

    public String getCustomerLevel2() {
        return customerLevel2;
    }

    public void setCustomerLevel2(String customerLevel2) {
        this.customerLevel2 = customerLevel2;
    }

    public String getCustomerLevel3() {
        return customerLevel3;
    }

    public void setCustomerLevel3(String customerLevel3) {
        this.customerLevel3 = customerLevel3;
    }

    public String getCustomerLevel4() {
        return customerLevel4;
    }

    public void setCustomerLevel4(String customerLevel4) {
        this.customerLevel4 = customerLevel4;
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

    public String getCustomerTotalAmt() {
        return customerTotalAmt;
    }

    public void setCustomerTotalAmt(String customerTotalAmt) {
        this.customerTotalAmt = customerTotalAmt;
    }

    public String getCustomerUpFront() {
        return customerUpFront;
    }

    public void setCustomerUpFront(String customerUpFront) {
        this.customerUpFront = customerUpFront;
    }

    public String getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(String customerBalance) {
        this.customerBalance = customerBalance;
    }

    public String getCustomerInvoiceDate() {
        return customerInvoiceDate;
    }

    public void setCustomerInvoiceDate(String customerInvoiceDate) {
        this.customerInvoiceDate = customerInvoiceDate;
    }

    public String getCustomerDueDate() {
        return customerDueDate;
    }

    public void setCustomerDueDate(String customerDueDate) {
        this.customerDueDate = customerDueDate;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public RealmList<MedicineModel> getMedicineLists() {
        return medicineLists;
    }

    public void setMedicineLists(RealmList<MedicineModel> medicineLists) {
        this.medicineLists = medicineLists;
    }

    public CustomerModel(String customerId, String customerName, String customerLevel1, String customerLevel2, String customerLevel3, String customerLevel4, String customerAddress, String customerPhNo1, String customerPhNo2, String customerPhNo3, String customerPhNo4, String customerPhNo5, String customerTotalAmt, String customerUpFront, String customerBalance, String customerInvoiceDate, String customerDueDate, String customerNote, RealmList<MedicineModel> medicineLists) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerLevel1 = customerLevel1;
        this.customerLevel2 = customerLevel2;
        this.customerLevel3 = customerLevel3;
        this.customerLevel4 = customerLevel4;
        this.customerAddress = customerAddress;
        this.customerPhNo1 = customerPhNo1;
        this.customerPhNo2 = customerPhNo2;
        this.customerPhNo3 = customerPhNo3;
        this.customerPhNo4 = customerPhNo4;
        this.customerPhNo5 = customerPhNo5;
        this.customerTotalAmt = customerTotalAmt;
        this.customerUpFront = customerUpFront;
        this.customerBalance = customerBalance;
        this.customerInvoiceDate = customerInvoiceDate;
        this.customerDueDate = customerDueDate;
        this.customerNote = customerNote;
        this.medicineLists = medicineLists;
    }

    public static Creator<CustomerModel> getCREATOR() {
        return CREATOR;
    }

    protected CustomerModel(Parcel in) {
        customerId = in.readString();
        customerName = in.readString();
        customerInvoiceDate = in.readString();
        customerLevel1 = in.readString();
        customerLevel2 = in.readString();
        customerLevel3 = in.readString();
        customerLevel4 = in.readString();
        customerAddress = in.readString();
        customerPhNo1 = in.readString();
        customerPhNo2 = in.readString();
        customerPhNo3 = in.readString();
        customerPhNo4 = in.readString();
        customerPhNo5 = in.readString();
        customerTotalAmt = in.readString();
        customerUpFront = in.readString();
        customerBalance = in.readString();
        customerNote = in.readString();
        customerDueDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerId);
        dest.writeString(customerName);
        dest.writeString(customerInvoiceDate);
        dest.writeString(customerLevel1);
        dest.writeString(customerLevel2);
        dest.writeString(customerLevel3);
        dest.writeString(customerLevel4);
        dest.writeString(customerAddress);
        dest.writeString(customerPhNo1);
        dest.writeString(customerPhNo2);
        dest.writeString(customerPhNo3);
        dest.writeString(customerPhNo4);
        dest.writeString(customerPhNo5);
        dest.writeString(customerTotalAmt);
        dest.writeString(customerUpFront);
        dest.writeString(customerBalance);
        dest.writeString(customerDueDate);
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
