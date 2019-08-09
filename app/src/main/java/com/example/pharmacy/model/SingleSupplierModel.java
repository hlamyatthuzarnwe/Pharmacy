package com.example.pharmacy.model;

public class SingleSupplierModel {

    private static SupplierModel instance;

    private SingleSupplierModel(){}

    public static synchronized SupplierModel getSupplier(){
        if (instance == null) {
            instance = new SupplierModel();
        }
        return instance;
    }
    public static void clear() {
        instance = null;
    }


}
