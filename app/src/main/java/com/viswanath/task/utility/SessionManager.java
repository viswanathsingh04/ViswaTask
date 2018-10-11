package com.viswanath.task.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    static SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    final String PREF_NAME = "LoginPreference";

    public static final String IS_LOGIN = "Login";
    public static final String IS_SELECTED = "true";
    public static final String USERID = "user_id";

    public static final String USER_OTP = "user_otp";
    public static final String USERMOBILE = "user_mob";
    public static final String USERNAME = "user_name";
    public static final String GENDER = "gender";
    public static final String BIRTHDAY = "birthday";
    public static final String CITY = "city_id";
    public static final String STATE = "state_id";

    public static final String USEREMAIL = "user_email";
    public static final String USERPASSWORD = "user_password";
    public static final String USERFIRSTNAME = "user_firstname";
    public static final String USERLASTNAME = "user_lastname";


    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        if (this._context == null)
            this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createLoginSession(String userid, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(USERID, userid);
        //editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(USEREMAIL, email);

        // commit changes
        editor.commit();
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(USERID, pref.getString(USERID, null));

        // user email id
        user.put(USEREMAIL, pref.getString(USEREMAIL, null));

        // return user
        return user;
    }
    @SuppressLint("USERMOBILE")
    public void setUserMobilre(String mob) {
        editor.putString(USERMOBILE, mob);
        editor.commit();
    }
    @SuppressLint("GENDER")
    public void setGender(String mob) {
        editor.putString(GENDER, mob);
        editor.commit();
    }
    @SuppressLint("BIRTHDAY")
    public void setBirthday(String mob) {
        editor.putString(BIRTHDAY, mob);
        editor.commit();
    }
    @SuppressLint("CITY")
    public void setCity(String mob) {
        editor.putString(CITY, mob);
        editor.commit();
    }
    @SuppressLint("STATE")
    public void setState(String mob) {
        editor.putString(STATE, mob);
        editor.commit();
    }


    @SuppressLint("USEREMAIL")
    public void setUseremail(String email) {
        editor.putString(USEREMAIL, email);
        editor.commit();
    }

    @SuppressLint("USERPASSWORD")
    public void setUser_password(String user_password) {
        editor.putString(USERPASSWORD, user_password);
        editor.commit();
    }

    @SuppressLint("USERFIRSTNAME")
    public void setUserfirstname(String userid) {
        editor.putString(USERLASTNAME, userid);
        editor.commit();
    }

    @SuppressLint("USERLASTNAME")
    public void setUserlastname(String adhar) {
        editor.putString(USERLASTNAME, adhar);
        editor.commit();
    }

    @SuppressLint("USERNAME")
    public void setUsereName(String name) {
        editor.putString(USERNAME, name);
        editor.commit();
    }

    @SuppressLint("USERID")
    public void setUserid(String user_id) {
        editor.putString(USERID, user_id);
        editor.commit();
    }







    @SuppressLint("IS_SELECTED")
    public void setSelected(String selected) {
        editor.putString(IS_SELECTED, selected);
        editor.commit();
    }


    @SuppressLint("Is Login")
    public void setLogin(boolean b) {
        editor.putBoolean(IS_LOGIN, b);
        editor.commit();
    }

    //----------------get--------------------

    @SuppressLint("USEROTP")
    public String getUserotp() {
        return pref.getString(USER_OTP, "");
    }
    @SuppressLint("GENDER")
    public String getGender() {
        return pref.getString(GENDER, "");
    }
    @SuppressLint("BIRTHDAY")
    public String getBirthday() {
        return pref.getString(BIRTHDAY, "");
    }
    @SuppressLint("STATE")
    public String getState() {
        return pref.getString(STATE, "");
    }
    @SuppressLint("CITY")
    public String getCity() {
        return pref.getString(CITY, "");
    }
    @SuppressLint("USERID")
    public String getUserid() {
        return pref.getString(USERID, "");
    }

    @SuppressLint("USERMOBILE")
    public String getUsermobile() {
        return pref.getString(USERMOBILE, "");
    }

    @SuppressLint("USEREMAIL")
    public String getUseremail() {
        return pref.getString(USEREMAIL, "");
    }

    @SuppressLint("USERFIRSTNAME")
    public String getUserfirstname() {
        return pref.getString(USERFIRSTNAME, "");
    }

    @SuppressLint("USERNAME")
    public String getUsername() {
        return pref.getString(USERNAME, "");
    }

    @SuppressLint("USERLASTNAME")
    public String getUserlastname() {
        return pref.getString(USERLASTNAME, "");
    }

    @SuppressLint("USERPASSWORD")
    public String getUserpassword() {
        return pref.getString(USERPASSWORD, "");
    }








    @SuppressLint("IS_SELECTED")
    public String getSelected() {
        return pref.getString(IS_SELECTED, "true");
    }

    @SuppressLint("IS_LOGIN")
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

}