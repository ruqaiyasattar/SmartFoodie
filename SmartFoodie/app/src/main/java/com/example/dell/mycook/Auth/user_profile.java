package com.example.dell.mycook.Auth;

/**
 * Created by dell on 3/17/2017.
 */

public class user_profile {

    String  DisplayName;
    int Uid;
    String Email;

    public user_profile(String displayName, int uid, String email) {
        DisplayName = displayName;
        Uid = uid;
        Email = email;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
