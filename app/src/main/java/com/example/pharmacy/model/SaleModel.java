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
    private String saleCustomerLevel1;
    private String saleCustomerLevel2;
    private String saleCustomerLevel3;
    private String saleCustomerLevel4;
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

    public SaleModel(String saleInvoiceNo, String saleInvoiceDate, String saleCustomerName, String saleCustomerLevel1, String saleCustomerLevel2, String saleCustomerLevel3, String saleCustomerLevel4, String saleCustomerAddress, String saleCustomerPhNo1, String saleCustomerPhNo2, String saleCustomerPhNo3, String saleMedicineName, String saleMedicineCode, String saleCategory, String saleCostPerPc, String saleCostPerDz, String saleQtyPerPc, String saleQtyPerDz, String sellingPricePerPc1, String sellingPricePerDz1, String sellingPricePerPc2, String sellingPricePerDz2, String sellingPricePerPc3, String sellingPricePerDz3, String sellingPricePerPc4, String sellingPricePerDz4, String saleSubTotalAmt, String saleTotalAmt, String saleBalance, String saleDuedate, String saleUpFront, String saleNote, String salePayment) {
        this.saleInvoiceNo = saleInvoiceNo;
        this.saleInvoiceDate = saleInvoiceDate;
        this.saleCustomerName = saleCustomerName;
        this.saleCustomerLevel1 = saleCustomerLevel1;
        this.saleCustomerLevel2 = saleCustomerLevel2;
        this.saleCustomerLevel3 = saleCustomerLevel3;
        this.saleCustomerLevel4 = saleCustomerLevel4;
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
    }

    public SaleModel(){}


    public static Creator<SaleModel> getCREATOR() {
        return CREATOR;
    }

    protected SaleModel(Parcel in) {
        saleInvoiceNo =  in.readString();
        saleInvoiceDate = in.readString();
        saleCustomerName = in.readString();
        saleCustomerAddress = in.readString();
        saleCustomerLevel1 = in.readString();
        saleCustomerLevel2 = in.readString();
        saleCustomerLevel3 = in.readString();
        saleCustomerLevel4 = in.readString();
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
        saleSubTotalAmt = in.readString();
        saleTotalAmt = in.readString();
        saleBalance = in.readString();
        saleDuedate = in.readString();
        saleUpFront = in.readString();
        saleNote = in.readString();
        salePayment = in.readString();
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

    public String getSaleCustomerLevel1() {
        return saleCustomerLevel1;
    }

    public void setSaleCustomerLevel1(String saleCustomerLevel1) {
        this.saleCustomerLevel1 = saleCustomerLevel1;
    }

    public String getSaleCustomerLevel2() {
        return saleCustomerLevel2;
    }

    public void setSaleCustomerLevel2(String saleCustomerLevel2) {
        this.saleCustomerLevel2 = saleCustomerLevel2;
    }

    public String getSaleCustomerLevel3() {
        return saleCustomerLevel3;
    }

    public void setSaleCustomerLevel3(String saleCustomerLevel3) {
        this.saleCustomerLevel3 = saleCustomerLevel3;
    }

    public String getSaleCustomerLevel4() {
        return saleCustomerLevel4;
    }

    public void setSaleCustomerLevel4(String saleCustomerLevel4) {
        this.saleCustomerLevel4 = saleCustomerLevel4;
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

    public String getSaleNote() {
        return saleNote;
    }

    public String getSaleUpFront() {
        return saleUpFront;
    }

    public void setSaleUpFront(String saleUpFront) {
        this.saleUpFront = saleUpFront;
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
        dest.writeString(saleInvoiceNo);
        dest.writeString(saleInvoiceDate);
        dest.writeString(saleCustomerName);
        dest.writeString(saleCustomerAddress);
        dest.writeString(saleCustomerLevel1);
        dest.writeString(saleCustomerLevel2);
        dest.writeString(saleCustomerLevel3);
        dest.writeString(saleCustomerLevel4);
        dest.writeString(saleCustomerPhNo1);
        dest.writeString(saleCustomerPhNo2);
        dest.writeString(saleCustomerPhNo3);
        dest.writeString(saleMedicineName);
        dest.writeString(saleMedicineCode);
        dest.writeString(saleCategory);
        dest.writeString(saleCostPerPc);
        dest.writeString(saleCostPerDz);
        dest.writeString(saleQtyPerPc);
        dest.writeString(saleQtyPerDz);
        dest.writeString(sellingPricePerPc1);
        dest.writeString(sellingPricePerDz1);
        dest.writeString(sellingPricePerPc2);
        dest.writeString(sellingPricePerDz2);
        dest.writeString(sellingPricePerPc3);
        dest.writeString(sellingPricePerDz3);
        dest.writeString(sellingPricePerPc4);
        dest.writeString(sellingPricePerDz4);
        dest.writeString(saleSubTotalAmt);
        dest.writeString(saleTotalAmt);
        dest.writeString(saleBalance);
        dest.writeString(saleDuedate);
        dest.writeString(saleUpFront);
        dest.writeString(salePayment);
    }
}
