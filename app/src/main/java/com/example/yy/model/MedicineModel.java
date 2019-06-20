package com.example.yy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MedicineModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private String medcineId;

    private String medicineName;
    private String medicineCode;
    private String medicineCategory;
    private String medicineDescription;
    private String medicinePrice1;
    private String medicinePrice2;
    private String medicinePrice3;
    private String medicinePrice4;
    private String medicinePrice5;
    private String medicineSellingPrice;
    private String receivedDate;
    private String expDate;
    private String medicineCost;
    private String medicineQty;
    private String medicineQtyLeft;
    private String medicineTotalAmt;
    private String supplierName;

    public static Creator<MedicineModel> getCREATOR() {
        return CREATOR;
    }

    public MedicineModel(){}

    public String getMedcineId() {
        return medcineId;
    }

    public void setMedcineId(String medcineId) {
        this.medcineId = medcineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public MedicineModel(String medcineId, String medicineName, String medicineCode, String medicineCategory, String medicineDescription, String medicinePrice1, String medicinePrice2, String medicinePrice3, String medicinePrice4, String medicinePrice5, String medicineSellingPrice, String receivedDate, String expDate, String medicineCost, String medicineQty, String medicineQtyLeft, String medicineTotalAmt, String supplierName) {
        this.medcineId = medcineId;
        this.medicineName = medicineName;
        this.medicineCode = medicineCode;
        this.medicineCategory = medicineCategory;
        this.medicineDescription = medicineDescription;
        this.medicinePrice1 = medicinePrice1;
        this.medicinePrice2 = medicinePrice2;
        this.medicinePrice3 = medicinePrice3;
        this.medicinePrice4 = medicinePrice4;
        this.medicinePrice5 = medicinePrice5;
        this.medicineSellingPrice = medicineSellingPrice;
        this.receivedDate = receivedDate;
        this.expDate = expDate;
        this.medicineCost = medicineCost;
        this.medicineQty = medicineQty;
        this.medicineQtyLeft = medicineQtyLeft;
        this.medicineTotalAmt = medicineTotalAmt;
        this.supplierName = supplierName;
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getMedicineCategory() {
        return medicineCategory;
    }

    public void setMedicineCategory(String medicineCategory) {
        this.medicineCategory = medicineCategory;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }

    public String getMedicinePrice1() {
        return medicinePrice1;
    }

    public void setMedicinePrice1(String medicinePrice1) {
        this.medicinePrice1 = medicinePrice1;
    }

    public String getMedicinePrice2() {
        return medicinePrice2;
    }

    public void setMedicinePrice2(String medicinePrice2) {
        this.medicinePrice2 = medicinePrice2;
    }

    public String getMedicinePrice3() {
        return medicinePrice3;
    }

    public void setMedicinePrice3(String medicinePrice3) {
        this.medicinePrice3 = medicinePrice3;
    }

    public String getMedicinePrice4() {
        return medicinePrice4;
    }

    public void setMedicinePrice4(String medicinePrice4) {
        this.medicinePrice4 = medicinePrice4;
    }

    public String getMedicinePrice5() {
        return medicinePrice5;
    }

    public void setMedicinePrice5(String medicinePrice5) {
        this.medicinePrice5 = medicinePrice5;
    }

    public String getMedicineSellingPrice() {
        return medicineSellingPrice;
    }

    public void setMedicineSellingPrice(String medicineSellingPrice) {
        this.medicineSellingPrice = medicineSellingPrice;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getMedicineCost() {
        return medicineCost;
    }

    public void setMedicineCost(String medicineCost) {
        this.medicineCost = medicineCost;
    }

    public String getMedicineQty() {
        return medicineQty;
    }

    public void setMedicineQty(String medicineQty) {
        this.medicineQty = medicineQty;
    }

    public String getMedicineQtyLeft() {
        return medicineQtyLeft;
    }

    public void setMedicineQtyLeft(String medicineQtyLeft) {
        this.medicineQtyLeft = medicineQtyLeft;
    }

    public String getMedicineTotalAmt() {
        return medicineTotalAmt;
    }

    public void setMedicineTotalAmt(String medicineTotalAmt) {
        this.medicineTotalAmt = medicineTotalAmt;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    protected MedicineModel(Parcel in) {
        medcineId = in.readString();
        medicineName = in.readString();
        medicineCode = in.readString();
        medicineCategory = in.readString();
        medicineDescription = in.readString();
        medicinePrice1 = in.readString();
        medicinePrice2 = in.readString();
        medicinePrice3 = in.readString();
        medicinePrice4 = in.readString();
        medicinePrice5 = in.readString();
        medicineSellingPrice = in.readString();
        supplierName = in.readString();
        receivedDate = in.readString();
        expDate = in.readString();
        medicineCost = in.readString();
        medicineQty = in.readString();
        medicineQtyLeft = in.readString();
        medicineTotalAmt = in.readString();
    }

    public static final Creator<MedicineModel> CREATOR = new Creator<MedicineModel>() {
        @Override
        public MedicineModel createFromParcel(Parcel in) {
            return new MedicineModel(in);
        }

        @Override
        public MedicineModel[] newArray(int size) {
            return new MedicineModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(medcineId);
        dest.writeString(medicineName);
        dest.writeString(medicineCode);
        dest.writeString(medicineCategory);
        dest.writeString(medicineDescription);
        dest.writeString(medicinePrice1);
        dest.writeString(medicinePrice2);
        dest.writeString(medicinePrice3);
        dest.writeString(medicinePrice4);
        dest.writeString(medicinePrice5);
        dest.writeString(medicineSellingPrice);
        dest.writeString(supplierName);
        dest.writeString(receivedDate);
        dest.writeString(expDate);
        dest.writeString(medicineCost);
        dest.writeString(medicineQty);
        dest.writeString(medicineQtyLeft);
        dest.writeString(medicineTotalAmt);
    }
}
