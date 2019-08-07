package com.example.pharmacy.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yy.R;

public class SharepreferenceHelper {

    private static final String PREF_OWNER = "Owner";
    private static final String PREF_STAFF = "Staff";
    private static final String PREF_IS_LOG_IN = "LogIn";

    private ContextHelper contextHelper;

    public static SharepreferenceHelper getHelper(Context context) {
        SingletonHelper.accessor.init(context);
        return SingletonHelper.accessor;
    }

    private void init(Context context) {
        contextHelper = new ContextHelper(context);
    }

    public void clearSharedPreferences() {
        getSharedPreferencesEditor().clear().apply();
    }

    public String getDoctor() {
        return getSharedPreferences().getString(PREF_OWNER, "");
    }

    public void setDoctor(String passcode) {
        getSharedPreferencesEditor().remove(PREF_OWNER).putString(PREF_OWNER, passcode).apply();
    }

    public String getStaff() {
        return getSharedPreferences().getString(PREF_STAFF, "");
    }

    public void setStaff(String passcode) {
        getSharedPreferencesEditor().remove(PREF_STAFF).putString(PREF_STAFF, passcode).apply();
    }

    public boolean isLogIn() {
        return getSharedPreferences().getBoolean(PREF_IS_LOG_IN, false);
    }

    public void setLogIn(boolean flag) {
        getSharedPreferencesEditor().putBoolean(PREF_IS_LOG_IN, flag).apply();
    }

    private SharedPreferences.Editor getSharedPreferencesEditor() {
        return getSharedPreferences().edit();
    }

    private SharedPreferences getSharedPreferences() {
        return contextHelper.getContext().getSharedPreferences(contextHelper.getContext().getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
    }


    private static class SingletonHelper {
        private static final SharepreferenceHelper accessor = new SharepreferenceHelper();
    }

    private class ContextHelper {
        private Context context;

        ContextHelper(Context context) {
            this.context = context;
        }

        Context getContext() {
            return context;
        }
    }

}
