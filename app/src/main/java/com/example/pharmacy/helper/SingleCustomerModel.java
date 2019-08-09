package com.example.pharmacy.helper;

import com.example.pharmacy.model.CustomerModel;

public class SingleCustomerModel {

    private static CustomerModel instance;

    private SingleCustomerModel() {
    }

    public static synchronized CustomerModel getCustomer() {
        if (instance == null) {
            instance = new CustomerModel();
        }
        return instance;
    }

    public static void clear() {
        instance = null;
    }
}
