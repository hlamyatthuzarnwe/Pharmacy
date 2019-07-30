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
    private String saleCustomerLevel;
    private String saleCustomerAddress;
    private String saleCustomerPhNo1;
    private String saleCustomerPhNo2;
    private String saleCustomerPhNo3;
    private String saleMedicineName;
    private String saleMedicineCode;
    private String saleCategory;
    private String saleCostPerPc;
    private String saleCostPerDz;
    private String saleQtyPerPc;
    private String saleQtyPerDz;
    private String sellingPricePerPc1;
    private String sellingPricePerDz1;
    private String sellingPricePerPc2;
    private String sellingPricePerDz2;
    private String sellingPricePerPc3;
    private String sellingPricePerDz3;
    private String sellingPricePerPc4;
    private String sellingPricePerDz4;
    private String saleSubTotalAmt;
    private String saleTotalAmt;
    private String saleBalance;
    private String saleDuedate;
    private String saleUpFront;
    private String saleNote;
    private String salePayment;
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

    public SaleModel(){}

    public SaleModel(String saleInvoiceNo, String saleInvoiceDate, String saleCustomerName, String saleCustomerLevel, String saleCustomerAddress, String saleCustomerPhNo1, String saleCustomerPhNo2, String saleCustomerPhNo3, String saleMedicineName, String saleMedicineCode, String saleCategory, String saleCostPerPc, String saleCostPerDz, String saleQtyPerPc, String saleQtyPerDz, String sellingPricePerPc1, String sellingPricePerDz1, String sellingPricePerPc2, String sellingPricePerDz2, String sellingPricePerPc3, String sellingPricePerDz3, String sellingPricePerPc4, String sellingPricePerDz4, String saleSubTotalAmt, String saleTotalAmt, String saleBalance, String saleDuedate, String saleUpFront, String saleNote, String salePayment, CustomerModel customerModel) {
        this.saleInvoiceNo = saleInvoiceNo;
        this.saleInvoiceDate = saleInvoiceDate;
        this.saleCustomerName = saleCustomerName;
        this.saleCustomerLevel = saleCustomerLevel;
        this.saleCustomerAddress = saleCustomerAddress;
        this.saleCustomerPhNo1 = saleCustomerPhNo1;
        this.saleCustomerPhNo2 = saleCustomerPhNo2;
        this.saleCustomerPhNo3 = saleCustomerPhNo3;
        this.saleMedicineName = saleMedicineName;
        this.saleMedicineCode = saleMedicineCode;
        this.saleCategory = saleCategory;
        this.saleCostPerPc = saleCostPerPc;
        this.saleCostPerDz = saleCostPerDz;
        this.saleQtyPerPc = saleQtyPerPc;
        this.saleQtyPerDz = saleQtyPerDz;
        this.sellingPricePerPc1 = sellingPricePerPc1;
        this.sellingPricePerDz1 = sellingPricePerDz1;
        this.sellingPricePerPc2 = sellingPricePerPc2;
        this.sellingPricePerDz2 = sellingPricePerDz2;
        this.sellingPricePerPc3 = sellingPricePerPc3;
        this.sellingPricePerDz3 = sellingPricePerDz3;
        this.sellingPricePerPc4 = sellingPricePerPc4;
        this.sellingPricePerDz4 = sellingPricePerDz4;
        this.saleSubTotalAmt = saleSubTotalAmt;
        this.saleTotalAmt = saleTotalAmt;
        this.saleBalance = saleBalance;
        this.saleDuedate = saleDuedate;
        this.saleUpFront = saleUpFront;
        this.saleNote = saleNote;
        this.salePayment = salePayment;
        this.customerModel = customerModel;
    }

    protected SaleModel(Parcel in) {
        saleInvoiceNo = in.readString();
        saleInvoiceDate = in.readString();
        saleCustomerName = in.readString();
        saleCustomerLevel = in.readString();
        saleCustomerAddress = in.readString();
        saleCustomerPhNo1 = in.readString();
        saleCustomerPhNo2 = in.readString();
        saleCustomerPhNo3 = in.readString();
        saleMedicineName = in.readString();
        saleMedicineCode = in.readString();
        saleCategory = in.readString();
        saleCostPerPc = in.readString();
        saleCostPerDz = in.readString();
        saleQtyPerPc = in.readString();
        saleQtyPerDz = in.readString();
        sellingPricePerPc1 = in.readString();
        sellingPricePerDz1 = in.readString();
        sellingPricePerPc2 = in.readString();
        sellingPricePerDz2 = in.readString();
        sellingPricePerPc3 = in.readString();
        sellingPricePerDz3 = in.readString();
        sellingPricePerPc4 = in.readString();
        sellingPricePerDz4 = in.readString();
        saleSubTotalAmt = in.readString();
        saleTotalAmt = in.readString();
        saleBalance = in.readString();
        saleDuedate = in.readString();
        saleUpFront = in.readString();
        saleNote = in.readString();
        salePayment = in.readString();
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

    public String getSaleCustomerLevel() {
        return saleCustomerLevel;
    }

    public void setSaleCustomerLevel(String saleCustomerLevel) {
        this.saleCustomerLevel = saleCustomerLevel;
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

    public String getSaleCustomerPhNo2() {
        return saleCustomerPhNo2;
    }

    public void setSaleCustomerPhNo2(String saleCustomerPhNo2) {
        this.saleCustomerPhNo2 = saleCustomerPhNo2;
    }

    public String getSaleCustomerPhNo3() {
        return saleCustomerPhNo3;
    }

    public void setSaleCustomerPhNo3(String saleCustomerPhNo3) {
        this.saleCustomerPhNo3 = saleCustomerPhNo3;
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

    public String getSaleCostPerPc() {
        return saleCostPerPc;
    }

    public void setSaleCostPerPc(String saleCostPerPc) {
        this.saleCostPerPc = saleCostPerPc;
    }

    public String getSaleCostPerDz() {
        return saleCostPerDz;
    }

    public void setSaleCostPerDz(String saleCostPerDz) {
        this.saleCostPerDz = saleCostPerDz;
    }

    public String getSaleQtyPerPc() {
        return saleQtyPerPc;
    }

    public void setSaleQtyPerPc(String saleQtyPerPc) {
        this.saleQtyPerPc = saleQtyPerPc;
    }

    public String getSaleQtyPerDz() {
        return saleQtyPerDz;
    }

    public void setSaleQtyPerDz(String saleQtyPerDz) {
        this.saleQtyPerDz = saleQtyPerDz;
    }

    public String getSellingPricePerPc1() {
        return sellingPricePerPc1;
    }

    public void setSellingPricePerPc1(String sellingPricePerPc1) {
        this.sellingPricePerPc1 = sellingPricePerPc1;
    }

    public String getSellingPricePerDz1() {
        return sellingPricePerDz1;
    }

    public void setSellingPricePerDz1(String sellingPricePerDz1) {
        this.sellingPricePerDz1 = sellingPricePerDz1;
    }

    public String getSellingPricePerPc2() {
        return sellingPricePerPc2;
    }

    public void setSellingPricePerPc2(String sellingPricePerPc2) {
        this.sellingPricePerPc2 = sellingPricePerPc2;
    }

    public String getSellingPricePerDz2() {
        return sellingPricePerDz2;
    }

    public void setSellingPricePerDz2(String sellingPricePerDz2) {
        this.sellingPricePerDz2 = sellingPricePerDz2;
    }

    public String getSellingPricePerPc3() {
        return sellingPricePerPc3;
    }

    public void setSellingPricePerPc3(String sellingPricePerPc3) {
        this.sellingPricePerPc3 = sellingPricePerPc3;
    }

    public String getSellingPricePerDz3() {
        return sellingPricePerDz3;
    }

    public void setSellingPricePerDz3(String sellingPricePerDz3) {
        this.sellingPricePerDz3 = sellingPricePerDz3;
    }

    public String getSellingPricePerPc4() {
        return sellingPricePerPc4;
    }

    public void setSellingPricePerPc4(String sellingPricePerPc4) {
        this.sellingPricePerPc4 = sellingPricePerPc4;
    }

    public String getSellingPricePerDz4() {
        return sellingPricePerDz4;
    }

    public void setSellingPricePerDz4(String sellingPricePerDz4) {
        this.sellingPricePerDz4 = sellingPricePerDz4;
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

    public String getSaleBalance() {
        return saleBalance;
    }

    public void setSaleBalance(String saleBalance) {
        this.saleBalance = saleBalance;
    }

    public String getSaleDuedate() {
        return saleDuedate;
    }

    public void setSaleDuedate(String saleDuedate) {
        this.saleDuedate = saleDuedate;
    }

    public String getSaleUpFront() {
        return saleUpFront;
    }

    public void setSaleUpFront(String saleUpFront) {
        this.saleUpFront = saleUpFront;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(saleInvoiceNo);
        parcel.writeString(saleInvoiceDate);
        parcel.writeString(saleCustomerName);
        parcel.writeString(saleCustomerLevel);
        parcel.writeString(saleCustomerAddress);
        parcel.writeString(saleCustomerPhNo1);
        parcel.writeString(saleCustomerPhNo2);
        parcel.writeString(saleCustomerPhNo3);
        parcel.writeString(saleMedicineName);
        parcel.writeString(saleMedicineCode);
        parcel.writeString(saleCategory);
        parcel.writeString(saleCostPerPc);
        parcel.writeString(saleCostPerDz);
        parcel.writeString(saleQtyPerPc);
        parcel.writeString(saleQtyPerDz);
        parcel.writeString(sellingPricePerPc1);
        parcel.writeString(sellingPricePerDz1);
        parcel.writeString(sellingPricePerPc2);
        parcel.writeString(sellingPricePerDz2);
        parcel.writeString(sellingPricePerPc3);
        parcel.writeString(sellingPricePerDz3);
        parcel.writeString(sellingPricePerPc4);
        parcel.writeString(sellingPricePerDz4);
        parcel.writeString(saleSubTotalAmt);
        parcel.writeString(saleTotalAmt);
        parcel.writeString(saleBalance);
        parcel.writeString(saleDuedate);
        parcel.writeString(saleUpFront);
        parcel.writeString(saleNote);
        parcel.writeString(salePayment);
        parcel.writeParcelable(customerModel, i);
    }

    public void setSaleNote(String saleNote) {
        this.saleNote = saleNote;
    }

    public String getSalePayment() {
        return salePayment;
    }

    public void setSalePayment(String salePayment) {
        this.salePayment = salePayment;
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
