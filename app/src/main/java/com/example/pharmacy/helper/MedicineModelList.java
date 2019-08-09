package com.example.pharmacy.helper;

import com.example.pharmacy.model.MedicineModel;

import java.util.ArrayList;

import io.realm.RealmList;

public class MedicineModelList {

    private static  final MedicineModelList instance = new MedicineModelList();
   // create an object of CaseRecordModel
    private  RealmList<MedicineModel> medicineModelRealmList;

    private MedicineModelList() {
    }

    public static MedicineModelList getInstance() {
        return instance;
    }

    public  RealmList<MedicineModel> getMedicineModelRealmList() {

        if (this.medicineModelRealmList == null){
            medicineModelRealmList = new RealmList<>();
        }

        return medicineModelRealmList;
    }

    public  void clear() {
        this.medicineModelRealmList = null;
    }

    //    //make the constructor private so that this class cannot be
//    //instantiated
//    private MedicineModelList() {
//    }
//
//    //Get the only object available
//    public static synchronized RealmList<MedicineModel> getInstance() {
//        if (instance == null) {
//            instance = new RealmList<>();
//        }
//        return instance;
//    }
//
//
//
//    public static void clear() {
//        instance = null;
//    }


}
