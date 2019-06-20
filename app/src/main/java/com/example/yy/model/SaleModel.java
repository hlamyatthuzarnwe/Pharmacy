package com.example.yy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SaleModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private int saleInvoiceNo;

    private String saleInvoiceDate;
    private String saleCustomerName;
    private String saleCustomerAddress;
    private String saleCustomerPhNo;
    private String saleMedicineName;
    private String saleMedicineCode;
    private String saleCategory;
    private String salePrice;
    private String saleDiscount;
    private String saleQty;
    private String saleSubTotalAmt;
    private String saleTotalAmt;
    private String saleDuedate;

    public SaleModel(){}

    public SaleModel(int saleInvoiceNo, String saleInvoiceDate, String saleCustomerName, String saleCustomerAddress, String saleCustomerPhNo, String saleMedicineName, String saleMedicineCode, String saleCategory, String salePrice, String saleDiscount, String saleQty, String saleSubTotalAmt, String saleTotalAmt, String saleDuedate) {
        this.saleInvoiceNo = saleInvoiceNo;
        this.saleInvoiceDate = saleInvoiceDate;
        this.saleCustomerName = saleCustomerName;
        this.saleCustomerAddress = saleCustomerAddress;
        this.saleCustomerPhNo = saleCustomerPhNo;
        this.saleMedicineName = saleMedicineName;
        this.saleMedicineCode = saleMedicineCode;
        this.saleCategory = saleCategory;
        this.salePrice = salePrice;
        this.saleDiscount = saleDiscount;
        this.saleQty = saleQty;
        this.saleSubTotalAmt = saleSubTotalAmt;
        this.saleTotalAmt = saleTotalAmt;
        this.saleDuedate = saleDuedate;
    }

    public int getSaleInvoiceNo() {
        return saleInvoiceNo;
    }

    public void setSaleInvoiceNo(int saleInvoiceNo) {
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

    public String getSaleCustomerPhNo() {
        return saleCustomerPhNo;
    }

    public void setSaleCustomerPhNo(String saleCustomerPhNo) {
        this.saleCustomerPhNo = saleCustomerPhNo;
    }

    public String getSaleMedicineName() {
        return saleMedicineName;
    }

    public void setSaleMedicineName(String saleMedicineName) {
        this.saleMedicineName = saleMedicineName;
    }

    public String getSaleMedicineCode() {
        return saleMedicineCode;
    }

    public void setSaleMedicineCode(String saleMedicineCode) {
        this.saleMedicineCode = saleMedicineCode;
    }

    public String getSaleCategory() {
        return saleCategory;
    }

    public void setSaleCategory(String saleCategory) {
        this.saleCategory = saleCategory;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSaleDiscount() {
        return saleDiscount;
    }

    public void setSaleDiscount(String saleDiscount) {
        this.saleDiscount = saleDiscount;
    }

    public String getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(String saleQty) {
        this.saleQty = saleQty;
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

    public String getSaleDuedate() {
        return saleDuedate;
    }

    public void setSaleDuedate(String saleDuedate) {
        this.saleDuedate = saleDuedate;
    }

    public static Creator<SaleModel> getCREATOR() {
        return CREATOR;
    }

    protected SaleModel(Parcel in) {
        saleInvoiceNo =  in.readInt();
        saleInvoiceDate = in.readString();
        saleCustomerName = in.readString();
        saleCustomerAddress = in.readString();
        saleCustomerPhNo = in.readString();
        saleMedicineName = in.readString();
        saleMedicineCode = in.readString();
        saleCategory = in.readString();
        salePrice = in.readString();
        saleDiscount = in.readString();
        saleSubTotalAmt = in.readString();
        saleTotalAmt = in.readString();
        saleDuedate = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(saleInvoiceNo);
        dest.writeString(saleInvoiceDate);
        dest.writeString(saleCustomerName);
        dest.writeString(saleCustomerAddress);
        dest.writeString(saleCustomerPhNo);
        dest.writeString(saleMedicineName);
        dest.writeString(saleMedicineCode);
        dest.writeString(saleCategory);
        dest.writeString(salePrice);
        dest.writeString(saleDiscount);
        dest.writeString(saleQty);
        dest.writeString(saleSubTotalAmt);
        dest.writeString(saleTotalAmt);
        dest.writeString(saleDuedate);
    }
}
