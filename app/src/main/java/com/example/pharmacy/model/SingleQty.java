package com.example.pharmacy.model;

import java.util.ArrayList;

public class SingleQty {

    private static ArrayList<String> instance;

    private SingleQty() {
    }

    public static synchronized ArrayList<String> getQty() {
        if (instance == null) {
            instance = new ArrayList<>();
        }
        return instance;
    }

    public static void clear() {
        instance = null;
    }
}
