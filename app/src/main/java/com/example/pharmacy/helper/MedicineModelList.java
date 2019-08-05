package com.example.pharmacy.helper;

import com.example.pharmacy.model.MedicineModel;

import java.util.ArrayList;

import io.realm.RealmList;

public class MedicineModelList {
    //create an object of CaseRecordModel
    private static RealmList<MedicineModel> instance;
    public static int invoiceNumber=0 ;

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
    public static int getNumber(){
        invoiceNumber += 1;
        return invoiceNumber;
    }
    public static void clear(){
        instance = null;
    }


}
