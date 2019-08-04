package com.example.pharmacy.app;

import android.app.Application;


import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

public class PharmacyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setUpRealm();

    }

    private void setUpRealm() {
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("medicine_db")
                .schemaVersion(1)
                .migration((realm, oldVersion, newVersion) -> {

                })
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
