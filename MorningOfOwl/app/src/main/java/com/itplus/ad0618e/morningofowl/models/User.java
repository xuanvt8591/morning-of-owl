package com.itplus.ad0618e.morningofowl.models;

import java.util.ArrayList;

public class User {
    String userName;
    String userPass;
    boolean userViewLater;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public boolean isUserViewLater() {
        return userViewLater;
    }

    public void setUserViewLater(boolean userViewLater) {
        this.userViewLater = userViewLater;
    }
}
