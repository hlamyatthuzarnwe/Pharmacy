package com.example.pharmacy.helper;

import com.example.pharmacy.model.MedicineModel;

import java.util.ArrayList;

import io.realm.RealmList;

public class MedicineModelList {
    //create an object of CaseRecordModel
    private static RealmList<MedicineModel> instance;

    //make the constructor private so that this class cannot be
    //instantiated
    private MedicineModelList(){}

    //Get the only object available
    public static synchronized RealmList<MedicineModel> getInstance(){
        if (instance == null){
            instance = new RealmList<>();
        }
        return instance;
    }
    public static void clear(){
        instance = null;
    }
}
