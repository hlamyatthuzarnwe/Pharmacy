package com.example.pharmacy.helper;

import com.example.pharmacy.model.MedicineModel;

public class SupplierAddList {
    private static MedicineModel instance;

    private SupplierAddList() {
    }

    public static synchronized MedicineModel getSaleItem() {
        if (instance == null) {
            instance = new MedicineModel();
        }
        return instance;
    }

    public static void clear() {
        instance = null;
    }
}
