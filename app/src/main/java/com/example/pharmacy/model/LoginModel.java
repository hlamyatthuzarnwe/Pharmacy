package com.example.pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LoginModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private String loginPin;

    private String userName;

    public LoginModel() {
    }

    public LoginModel(String loginPin, String userName) {
        this.loginPin = loginPin;
        this.userName = userName;
    }

    public String getLoginPin() {
        return loginPin;
    }

    public void setLoginPin(String loginPin) {
        this.loginPin = loginPin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static Creator<LoginModel> getCREATOR() {
        return CREATOR;
    }

    protected LoginModel(Parcel in) {
        loginPin = in.readString();
        userName = in.readString();
    }

    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loginPin);
        dest.writeString(userName);
    }
}
