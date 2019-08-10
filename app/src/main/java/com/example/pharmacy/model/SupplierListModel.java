package com.example.pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SupplierListModel implements Parcelable {

    private String supName;
    private String compName;
    private String compAddress;
    private String supPh;


    public SupplierListModel(){

    }

    protected SupplierListModel(Parcel in) {
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompAddress() {
        return compAddress;
    }

    public void setCompAddress(String compAddress) {
        this.compAddress = compAddress;
    }

    public String getSupPh() {
        return supPh;
    }

    public void setSupPh(String supPh) {
        this.supPh = supPh;
    }

    public static Creator<SupplierListModel> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<SupplierListModel> CREATOR = new Creator<SupplierListModel>() {
        @Override
        public SupplierListModel createFromParcel(Parcel in) {
            return new SupplierListModel(in);
        }

        @Override
        public SupplierListModel[] newArray(int size) {
            return new SupplierListModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
