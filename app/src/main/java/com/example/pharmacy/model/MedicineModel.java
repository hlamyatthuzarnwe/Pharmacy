package com.example.pharmacy.model;

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
    private String medicineCostPerPc;
    private String medicineCostPerDz;
    private String medicineQtyPerPc;
    private String medicineQtyPerDz;
    private String medicineCompanyName;
    private String medicineSupplierName;
    private String medicineContactPh1;
    private String medicineContactPh2;
    private String medicineContactPh3;
    private String medicineViberPh;
    private String medicinePayment;
    private String medicineSalePcPerPrice1;
    private String medicineSaleDzPerPrice1;
    private String medicineSalePcPerPrice2;
    private String medicineSaleDzPerPrice2;
    private String medicineSalePcPerPrice3;
    private String medicineSaleDzPerPrice3;
    private String medicineSalePcPerPrice4;
    private String medicineSaleDzPerPrice4;
    private String medicineReceivedDate;
    private String medicineExpDate;
    private String medicineNote;

    public static Creator<MedicineModel> getCREATOR() {
        return CREATOR;
    }

    public MedicineModel(){}

    public MedicineModel(String medcineId, String medicineName, String medicineCode, String medicineCategory, String medicineCostPerPc, String medicineCostPerDz, String medicineQtyPerPc, String medicineQtyPerDz, String medicineCompanyName, String medicineSupplierName, String medicineContactPh1, String medicineContactPh2, String medicineContactPh3, String medicineViberPh, String medicinePayment, String medicineSalePcPerPrice1, String medicineSaleDzPerPrice1, String medicineSalePcPerPrice2, String medicineSaleDzPerPrice2, String medicineSalePcPerPrice3, String medicineSaleDzPerPrice3, String medicineSalePcPerPrice4, String medicineSaleDzPerPrice4, String medicineReceivedDate, String medicineExpDate, String medicineNote) {
        this.medcineId = medcineId;
        this.medicineName = medicineName;
        this.medicineCode = medicineCode;
        this.medicineCategory = medicineCategory;
        this.medicineCostPerPc = medicineCostPerPc;
        this.medicineCostPerDz = medicineCostPerDz;
        this.medicineQtyPerPc = medicineQtyPerPc;
        this.medicineQtyPerDz = medicineQtyPerDz;
        this.medicineCompanyName = medicineCompanyName;
        this.medicineSupplierName = medicineSupplierName;
        this.medicineContactPh1 = medicineContactPh1;
        this.medicineContactPh2 = medicineContactPh2;
        this.medicineContactPh3 = medicineContactPh3;
        this.medicineViberPh = medicineViberPh;
        this.medicinePayment = medicinePayment;
        this.medicineSalePcPerPrice1 = medicineSalePcPerPrice1;
        this.medicineSaleDzPerPrice1 = medicineSaleDzPerPrice1;
        this.medicineSalePcPerPrice2 = medicineSalePcPerPrice2;
        this.medicineSaleDzPerPrice2 = medicineSaleDzPerPrice2;
        this.medicineSalePcPerPrice3 = medicineSalePcPerPrice3;
        this.medicineSaleDzPerPrice3 = medicineSaleDzPerPrice3;
        this.medicineSalePcPerPrice4 = medicineSalePcPerPrice4;
        this.medicineSaleDzPerPrice4 = medicineSaleDzPerPrice4;
        this.medicineReceivedDate = medicineReceivedDate;
        this.medicineExpDate = medicineExpDate;
        this.medicineNote = medicineNote;
    }

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

    public String getMedicineCostPerPc() {
        return medicineCostPerPc;
    }

    public void setMedicineCostPerPc(String medicineCostPerPc) {
        this.medicineCostPerPc = medicineCostPerPc;
    }

    public String getMedicineCostPerDz() {
        return medicineCostPerDz;
    }

    public void setMedicineCostPerDz(String medicineCostPerDz) {
        this.medicineCostPerDz = medicineCostPerDz;
    }

    public String getMedicineQtyPerPc() {
        return medicineQtyPerPc;
    }

    public void setMedicineQtyPerPc(String medicineQtyPerPc) {
        this.medicineQtyPerPc = medicineQtyPerPc;
    }

    public String getMedicineQtyPerDz() {
        return medicineQtyPerDz;
    }

    public void setMedicineQtyPerDz(String medicineQtyPerDz) {
        this.medicineQtyPerDz = medicineQtyPerDz;
    }

    public String getMedicineCompanyName() {
        return medicineCompanyName;
    }

    public void setMedicineCompanyName(String medicineCompanyName) {
        this.medicineCompanyName = medicineCompanyName;
    }

    public String getMedicineSupplierName() {
        return medicineSupplierName;
    }

    public void setMedicineSupplierName(String medicineSupplierName) {
        this.medicineSupplierName = medicineSupplierName;
    }

    public String getMedicineContactPh1() {
        return medicineContactPh1;
    }

    public void setMedicineContactPh1(String medicineContactPh1) {
        this.medicineContactPh1 = medicineContactPh1;
    }

    public String getMedicineContactPh2() {
        return medicineContactPh2;
    }

    public void setMedicineContactPh2(String medicineContactPh2) {
        this.medicineContactPh2 = medicineContactPh2;
    }

    public String getMedicineContactPh3() {
        return medicineContactPh3;
    }

    public void setMedicineContactPh3(String medicineContactPh3) {
        this.medicineContactPh3 = medicineContactPh3;
    }

    public String getMedicineViberPh() {
        return medicineViberPh;
    }

    public void setMedicineViberPh(String medicineViberPh) {
        this.medicineViberPh = medicineViberPh;
    }

    public String getMedicinePayment() {
        return medicinePayment;
    }

    public void setMedicinePayment(String medicinePayment) {
        this.medicinePayment = medicinePayment;
    }

    public String getMedicineSalePcPerPrice1() {
        return medicineSalePcPerPrice1;
    }

    public void setMedicineSalePcPerPrice1(String medicineSalePcPerPrice1) {
        this.medicineSalePcPerPrice1 = medicineSalePcPerPrice1;
    }

    public String getMedicineSaleDzPerPrice1() {
        return medicineSaleDzPerPrice1;
    }

    public void setMedicineSaleDzPerPrice1(String medicineSaleDzPerPrice1) {
        this.medicineSaleDzPerPrice1 = medicineSaleDzPerPrice1;
    }

    public String getMedicineSalePcPerPrice2() {
        return medicineSalePcPerPrice2;
    }

    public void setMedicineSalePcPerPrice2(String medicineSalePcPerPrice2) {
        this.medicineSalePcPerPrice2 = medicineSalePcPerPrice2;
    }

    public String getMedicineSaleDzPerPrice2() {
        return medicineSaleDzPerPrice2;
    }

    public void setMedicineSaleDzPerPrice2(String medicineSaleDzPerPrice2) {
        this.medicineSaleDzPerPrice2 = medicineSaleDzPerPrice2;
    }

    public String getMedicineSalePcPerPrice3() {
        return medicineSalePcPerPrice3;
    }

    public void setMedicineSalePcPerPrice3(String medicineSalePcPerPrice3) {
        this.medicineSalePcPerPrice3 = medicineSalePcPerPrice3;
    }

    public String getMedicineSaleDzPerPrice3() {
        return medicineSaleDzPerPrice3;
    }

    public void setMedicineSaleDzPerPrice3(String medicineSaleDzPerPrice3) {
        this.medicineSaleDzPerPrice3 = medicineSaleDzPerPrice3;
    }

    public String getMedicineSalePcPerPrice4() {
        return medicineSalePcPerPrice4;
    }

    public void setMedicineSalePcPerPrice4(String medicineSalePcPerPrice4) {
        this.medicineSalePcPerPrice4 = medicineSalePcPerPrice4;
    }

    public String getMedicineSaleDzPerPrice4() {
        return medicineSaleDzPerPrice4;
    }

    public void setMedicineSaleDzPerPrice4(String medicineSaleDzPerPrice4) {
        this.medicineSaleDzPerPrice4 = medicineSaleDzPerPrice4;
    }

    public String getMedicineReceivedDate() {
        return medicineReceivedDate;
    }

    public void setMedicineReceivedDate(String medicineReceivedDate) {
        this.medicineReceivedDate = medicineReceivedDate;
    }

    public String getMedicineExpDate() {
        return medicineExpDate;
    }

    public void setMedicineExpDate(String medicineExpDate) {
        this.medicineExpDate = medicineExpDate;
    }

    public String getMedicineNote() {
        return medicineNote;
    }

    public void setMedicineNote(String medicineNote) {
        this.medicineNote = medicineNote;
    }

    protected MedicineModel(Parcel in) {
        medcineId = in.readString();
        medicineName = in.readString();
        medicineCode = in.readString();
        medicineCategory = in.readString();
        medicineCostPerPc = in.readString();
        medicineCostPerDz = in.readString();
        medicineQtyPerPc = in.readString();
        medicineQtyPerDz = in.readString();
        medicineCompanyName = in.readString();
        medicineSupplierName = in.readString();
        medicineContactPh1 = in.readString();
        medicineContactPh2 = in.readString();
        medicineContactPh3 = in.readString();
        medicineViberPh = in.readString();
        medicinePayment = in.readString();
        medicineSalePcPerPrice1 = in.readString();
        medicineSaleDzPerPrice1 = in.readString();
        medicineSalePcPerPrice2 = in.readString();
        medicineSaleDzPerPrice2 = in.readString();
        medicineSalePcPerPrice3 = in.readString();
        medicineSaleDzPerPrice3 = in.readString();
        medicineSalePcPerPrice4 = in.readString();
        medicineSaleDzPerPrice4 = in.readString();
        medicineReceivedDate = in.readString();
        medicineExpDate = in.readString();
        medicineNote = in.readString();
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
        dest.writeString(medicineCostPerPc);
        dest.writeString(medicineCostPerDz);
        dest.writeString(medicineQtyPerPc);
        dest.writeString(medicineQtyPerDz);
        dest.writeString(medicineCompanyName);
        dest.writeString(medicineSupplierName);
        dest.writeString(medicineContactPh1);
        dest.writeString(medicineContactPh2);
        dest.writeString(medicineContactPh3);
        dest.writeString(medicineViberPh);
        dest.writeString(medicinePayment);
        dest.writeString(medicineSalePcPerPrice1);
        dest.writeString(medicineSaleDzPerPrice1);
        dest.writeString(medicineSalePcPerPrice2);
        dest.writeString(medicineSaleDzPerPrice2);
        dest.writeString(medicineSalePcPerPrice3);
        dest.writeString(medicineSaleDzPerPrice3);
        dest.writeString(medicineSalePcPerPrice4);
        dest.writeString(medicineSaleDzPerPrice4);
        dest.writeString(medicineReceivedDate);
        dest.writeString(medicineExpDate);
        dest.writeString(medicineNote);
    }
}
