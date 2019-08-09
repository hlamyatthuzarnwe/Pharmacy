package com.example.pharmacy.app;

import android.app.Application;


import com.example.pharmacy.helper.RealmMigration;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;

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
                .migration(new RealmMigration())
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
