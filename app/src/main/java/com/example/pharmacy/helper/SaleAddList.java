package com.example.pharmacy.helper;

import com.example.pharmacy.model.SaleModel;

public class SaleAddList {

    private static SaleModel instance;

    private SaleAddList() {
    }

    public static synchronized SaleModel getSaleItems() {
        if (instance == null) {
            instance = new SaleModel();
        }
        return instance;
    }

    public static void clear() {
        instance = null;
    }

}
