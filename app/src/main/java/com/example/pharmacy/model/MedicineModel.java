package com.example.pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MedicineModel extends RealmObject implements Parcelable {

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
    @PrimaryKey
    private String medcineId;
    private String medicineName;
    private String medicineCostPerPc;
    private String medicineTotalQty;
    private String medicineSalePcPerPrice1;
    private String medicineReceivedDate;
    private String medicineExpDate;
    private String medicineNote;
    private SupplierModel supplierModel;
    private String medicineSubAmt;

    public MedicineModel() {
    }

    protected MedicineModel(Parcel in) {
        medcineId = in.readString();
        medicineName = in.readString();
        medicineCostPerPc = in.readString();
        medicineTotalQty = in.readString();
        medicineSalePcPerPrice1 = in.readString();
        medicineReceivedDate = in.readString();
        medicineExpDate = in.readString();
        medicineNote = in.readString();
        supplierModel = in.readParcelable(SupplierModel.class.getClassLoader());

        medicineSubAmt = in.readString();
    }

    public static Creator<MedicineModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(medcineId);
        parcel.writeString(medicineName);
        parcel.writeString(medicineCostPerPc);
        parcel.writeString(medicineTotalQty);
        parcel.writeString(medicineSalePcPerPrice1);
        parcel.writeString(medicineReceivedDate);
        parcel.writeString(medicineExpDate);
        parcel.writeString(medicineNote);
        parcel.writeParcelable(supplierModel, i);

        parcel.writeString(medicineSubAmt);
    }

    public String getMedicineTotalQty() {
        return medicineTotalQty;
    }

    public void setMedicineTotalQty(String medicineTotalQty) {
        this.medicineTotalQty = medicineTotalQty;
    }

    public String getMedicineSubAmt() {
        return medicineSubAmt;
    }

    public void setMedicineSubAmt(String medicineSubAmt) {
        this.medicineSubAmt = medicineSubAmt;
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

    public String getMedicineCostPerPc() {
        return medicineCostPerPc;
    }

    public void setMedicineCostPerPc(String medicineCostPerPc) {
        this.medicineCostPerPc = medicineCostPerPc;
    }

    public String getMedicineSalePcPerPrice1() {
        return medicineSalePcPerPrice1;
    }

    public void setMedicineSalePcPerPrice1(String medicineSalePcPerPrice1) {
        this.medicineSalePcPerPrice1 = medicineSalePcPerPrice1;
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

    public SupplierModel getSupplierModel() {
        return supplierModel;
    }

    public void setSupplierModel(SupplierModel supplierModel) {
        this.supplierModel = supplierModel;
    }

}
