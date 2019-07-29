package com.example.pharmacy.helper;

import com.example.pharmacy.model.SaleListModel;

public class SaleAddList {

    private static SaleListModel instance;

    private SaleAddList() {
    }

    public static synchronized SaleListModel getSaleItems() {
        if (instance == null) {
            instance = new SaleListModel();
        }
        return instance;
    }

    public static void clear() {
        instance = null;
    }

}
