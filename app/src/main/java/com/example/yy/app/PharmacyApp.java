package com.example.yy.app;

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
                .migration(new RealmMigration() {
                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                    }
                })
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
