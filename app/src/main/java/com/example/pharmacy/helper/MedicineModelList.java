package com.example.pharmacy.helper;

import com.example.pharmacy.model.MedicineModel;

import java.util.ArrayList;

public class MedicineModelList {
    //create an object of CaseRecordModel
    private static ArrayList<MedicineModel> instance;

    //make the constructor private so that this class cannot be
    //instantiated
    private MedicineModelList(){}

    //Get the only object available
    public static synchronized ArrayList<MedicineModel> getInstance(){
        if (instance == null){
            instance = new ArrayList<>();
        }
        return instance;
    }
    public static void clear(){
        instance = null;
    }
}
