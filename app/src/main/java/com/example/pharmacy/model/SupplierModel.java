package com.example.pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SupplierModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private String supplierId;
    private String supplierName;
    private String companyName;
    private String companyAddress;
    private String suplier_phno1;
    private String supplierNote;

    private RealmList<SupplierModel> supplierModels;

    public SupplierModel() {

    }

    public SupplierModel(String supplierId, String supplierName, String companyName, String companyAddress, String suplier_phno1, String supplierNote) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.suplier_phno1 = suplier_phno1;
        this.supplierNote = supplierNote;
    }

    protected SupplierModel(Parcel in) {
        supplierId = in.readString();
        supplierName = in.readString();
        companyName = in.readString();
        companyAddress = in.readString();
        suplier_phno1 = in.readString();
        supplierNote = in.readString();
    }

    public void setSupplierModel(RealmList<SupplierModel> supplierModels) {
        this.supplierModels = supplierModels;
    }

    public RealmList<SupplierModel> getSupplierModels() {
        return supplierModels;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public static Creator<SupplierModel> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<SupplierModel> CREATOR = new Creator<SupplierModel>() {
        @Override
        public SupplierModel createFromParcel(Parcel in) {
            return new SupplierModel(in);
        }

        @Override
        public SupplierModel[] newArray(int size) {
            return new SupplierModel[size];
        }
    };

    public String getSuplierId() {
        return supplierId;
    }

    public void setSuplierId(String suplierId) {
        this.supplierId = suplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getSuplier_phno1() {
        return suplier_phno1;
    }

    public void setSuplier_phno1(String suplier_phno1) {
        this.suplier_phno1 = suplier_phno1;
    }

    public String getSupplierNote() {
        return supplierNote;
    }

    public void setSupplierNote(String supplierNote) {
        this.supplierNote = supplierNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(supplierId);
        dest.writeString(supplierName);
        dest.writeString(companyName);
        dest.writeString(companyAddress);
        dest.writeString(suplier_phno1);
        dest.writeString(supplierNote);
    }
}
