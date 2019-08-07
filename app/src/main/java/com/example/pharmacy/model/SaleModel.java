package com.example.pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SaleModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private String saleInvoiceNo;

    private String saleInvoiceDate;
    private String saleCustomerName;
    private String saleCustomerAddress;
    private String saleCustomerPhNo1;
    private String saleMedicineName;
    private String saleCostPerPc;
    private String saleQtyPerPc;
    private String sellingPricePerPc1;
    private String saleSubTotalAmt;
    private String saleTotalAmt;
    private String saleNote;

    public static final Creator<SaleModel> CREATOR = new Creator<SaleModel>() {
        @Override
        public SaleModel createFromParcel(Parcel in) {
            return new SaleModel(in);
        }

        @Override
        public SaleModel[] newArray(int size) {
            return new SaleModel[size];
        }
    };
    private CustomerModel customerModel;

    public SaleModel() {
    }

    public SaleModel(String saleInvoiceNo, String saleInvoiceDate, String saleCustomerName, String saleCustomerAddress, String saleCustomerPhNo1, String saleMedicineName, String saleCostPerPc, String saleQtyPerPc, String sellingPricePerPc1, String saleSubTotalAmt, String saleTotalAmt, String saleNote, CustomerModel customerModel) {
        this.saleInvoiceNo = saleInvoiceNo;
        this.saleInvoiceDate = saleInvoiceDate;
        this.saleCustomerName = saleCustomerName;
        this.saleCustomerAddress = saleCustomerAddress;
        this.saleCustomerPhNo1 = saleCustomerPhNo1;
        this.saleMedicineName = saleMedicineName;
        this.saleCostPerPc = saleCostPerPc;
        this.saleQtyPerPc = saleQtyPerPc;
        this.sellingPricePerPc1 = sellingPricePerPc1;
        this.saleSubTotalAmt = saleSubTotalAmt;
        this.saleTotalAmt = saleTotalAmt;
        this.saleNote = saleNote;
        this.customerModel = customerModel;
    }

    protected SaleModel(Parcel in) {
        saleInvoiceNo = in.readString();
        saleInvoiceDate = in.readString();
        saleCustomerName = in.readString();
        saleCustomerAddress = in.readString();
        saleCustomerPhNo1 = in.readString();
        saleMedicineName = in.readString();
        saleCostPerPc = in.readString();
        saleQtyPerPc = in.readString();
        sellingPricePerPc1 = in.readString();
        saleSubTotalAmt = in.readString();
        saleTotalAmt = in.readString();
        saleNote = in.readString();
        customerModel = in.readParcelable(CustomerModel.class.getClassLoader());
    }

    public static Creator<SaleModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getSaleInvoiceNo() {
        return saleInvoiceNo;
    }

    public void setSaleInvoiceNo(String saleInvoiceNo) {
        this.saleInvoiceNo = saleInvoiceNo;
    }

    public String getSaleInvoiceDate() {
        return saleInvoiceDate;
    }

    public void setSaleInvoiceDate(String saleInvoiceDate) {
        this.saleInvoiceDate = saleInvoiceDate;
    }

    public String getSaleCustomerName() {
        return saleCustomerName;
    }

    public void setSaleCustomerName(String saleCustomerName) {
        this.saleCustomerName = saleCustomerName;
    }

    public String getSaleCustomerAddress() {
        return saleCustomerAddress;
    }

    public void setSaleCustomerAddress(String saleCustomerAddress) {
        this.saleCustomerAddress = saleCustomerAddress;
    }

    public String getSaleCustomerPhNo1() {
        return saleCustomerPhNo1;
    }

    public void setSaleCustomerPhNo1(String saleCustomerPhNo1) {
        this.saleCustomerPhNo1 = saleCustomerPhNo1;
    }

    public String getSaleMedicineName() {
        return saleMedicineName;
    }

    public void setSaleMedicineName(String saleMedicineName) {
        this.saleMedicineName = saleMedicineName;
    }

    public String getSaleCostPerPc() {
        return saleCostPerPc;
    }

    public void setSaleCostPerPc(String saleCostPerPc) {
        this.saleCostPerPc = saleCostPerPc;
    }


    public String getSaleQtyPerPc() {
        return saleQtyPerPc;
    }

    public void setSaleQtyPerPc(String saleQtyPerPc) {
        this.saleQtyPerPc = saleQtyPerPc;
    }

    public String getSellingPricePerPc1() {
        return sellingPricePerPc1;
    }

    public void setSellingPricePerPc1(String sellingPricePerPc1) {
        this.sellingPricePerPc1 = sellingPricePerPc1;
    }

    public String getSaleSubTotalAmt() {
        return saleSubTotalAmt;
    }

    public void setSaleSubTotalAmt(String saleSubTotalAmt) {
        this.saleSubTotalAmt = saleSubTotalAmt;
    }

    public String getSaleTotalAmt() {
        return saleTotalAmt;
    }

    public void setSaleTotalAmt(String saleTotalAmt) {
        this.saleTotalAmt = saleTotalAmt;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(saleInvoiceNo);
        parcel.writeString(saleInvoiceDate);
        parcel.writeString(saleCustomerName);
        parcel.writeString(saleCustomerAddress);
        parcel.writeString(saleCustomerPhNo1);
        parcel.writeString(saleMedicineName);
        parcel.writeString(saleCostPerPc);
        parcel.writeString(saleQtyPerPc);
        parcel.writeString(sellingPricePerPc1);
        parcel.writeString(saleSubTotalAmt);
        parcel.writeString(saleTotalAmt);
        parcel.writeString(saleNote);
        parcel.writeParcelable(customerModel, i);
    }

    public void setSaleNote(String saleNote) {
        this.saleNote = saleNote;
    }

    public String getSaleNote() {
        return saleNote;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }
}
