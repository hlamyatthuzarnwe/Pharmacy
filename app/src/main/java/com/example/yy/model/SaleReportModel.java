package com.example.yy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SaleReportModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private int saleReportSaleInvoiceNo;

    private String saleReportSaleIInvoiceDate;
    private String saleReportCustomerName;
    private String saleReportAmount;
    private String saleReportProfit;

    public SaleReportModel(){}

    public int getSaleReportSaleInvoiceNo() {
        return saleReportSaleInvoiceNo;
    }

    public void setSaleReportSaleInvoiceNo(int saleReportSaleInvoiceNo) {
        this.saleReportSaleInvoiceNo = saleReportSaleInvoiceNo;
    }

    public String getSaleReportSaleIInvoiceDate() {
        return saleReportSaleIInvoiceDate;
    }

    public void setSaleReportSaleIInvoiceDate(String saleReportSaleIInvoiceDate) {
        this.saleReportSaleIInvoiceDate = saleReportSaleIInvoiceDate;
    }

    public String getSaleReportCustomerName() {
        return saleReportCustomerName;
    }

    public void setSaleReportCustomerName(String saleReportCustomerName) {
        this.saleReportCustomerName = saleReportCustomerName;
    }

    public String getSaleReportAmount() {
        return saleReportAmount;
    }

    public void setSaleReportAmount(String saleReportAmount) {
        this.saleReportAmount = saleReportAmount;
    }

    public String getSaleReportProfit() {
        return saleReportProfit;
    }

    public void setSaleReportProfit(String saleReportProfit) {
        this.saleReportProfit = saleReportProfit;
    }

    public static Creator<SaleReportModel> getCREATOR() {
        return CREATOR;
    }

    public SaleReportModel(int saleReportSaleInvoiceNo, String saleReportSaleIInvoiceDate, String saleReportCustomerName, String saleReportAmount, String saleReportProfit) {
        this.saleReportSaleInvoiceNo = saleReportSaleInvoiceNo;
        this.saleReportSaleIInvoiceDate = saleReportSaleIInvoiceDate;
        this.saleReportCustomerName = saleReportCustomerName;
        this.saleReportAmount = saleReportAmount;
        this.saleReportProfit = saleReportProfit;
    }


    protected SaleReportModel(Parcel in) {
        saleReportSaleInvoiceNo = in.readInt();
        saleReportSaleIInvoiceDate =in.readString();
        saleReportCustomerName = in.readString();
        saleReportAmount = in.readString();
        saleReportProfit = in.readString();
    }

    public static final Creator<SaleReportModel> CREATOR = new Creator<SaleReportModel>() {
        @Override
        public SaleReportModel createFromParcel(Parcel in) {
            return new SaleReportModel(in);
        }

        @Override
        public SaleReportModel[] newArray(int size) {
            return new SaleReportModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(saleReportSaleInvoiceNo);
        dest.writeString(saleReportSaleIInvoiceDate);
        dest.writeString(saleReportCustomerName);
        dest.writeString(saleReportAmount);
        dest.writeString(saleReportProfit);
    }
}
