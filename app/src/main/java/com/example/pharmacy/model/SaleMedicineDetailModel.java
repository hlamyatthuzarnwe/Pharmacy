package com.example.pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class SaleMedicineDetailModel extends RealmObject implements Parcelable {
    protected SaleMedicineDetailModel(Parcel in) {
    }

    public static final Creator<SaleMedicineDetailModel> CREATOR = new Creator<SaleMedicineDetailModel>() {
        @Override
        public SaleMedicineDetailModel createFromParcel(Parcel in) {
            return new SaleMedicineDetailModel(in);
        }

        @Override
        public SaleMedicineDetailModel[] newArray(int size) {
            return new SaleMedicineDetailModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
